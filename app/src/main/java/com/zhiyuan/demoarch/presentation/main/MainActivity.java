package com.zhiyuan.demoarch.presentation.main;

import android.app.Dialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.zhiyuan.demoarch.R;
import com.zhiyuan.demoarch.utils.AppUtils;
import com.zhiyuan.demoarch.vo.main.UserInfoBean;
import com.zhiyuan.demoarch.vo.main.UserSettingInfo;

import java.util.List;

/**
 * 主界面
 * @author zhiyuan
 */
public class MainActivity extends AppCompatActivity implements MainContract.View {

    private LinearLayout mLlContainer;
    private TextView mTvName, mTvUserType;
    private ProgressBar mPb;

    private MainContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new MainPresenter(this);
        initView();
        initData();
    }

    /**
     * 初始化view
     */
    private void initView() {
        mLlContainer = findViewById(R.id.ll_container);
        mTvName = findViewById(R.id.tv_username);
        mTvUserType = findViewById(R.id.tv_usertype);
        mPb = findViewById(R.id.pb);
    }

    /**
     * 初始化data
     */
    private void initData() {
        mPresenter.loadSettingData();
        mPresenter.loadUserData();
    }

    @Override
    public void setPresenter(MainContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showLoading(boolean show) {
        if (show) {
            mPb.setVisibility(View.VISIBLE);
        } else {
            mPb.setVisibility(View.GONE);
        }
    }

    @Override
    public void setUserData(@NonNull final UserInfoBean userData) {
        mTvUserType.setText(AppUtils.getUserTypeName(userData.currentUserType));
        mTvName.setText(userData.name);

        //因为弹出对话框和数据相关，这里在setData方法里设置点击
        mTvUserType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showChangeUserTypeDialog(userData.userTypes);
            }
        });
    }

    @Override
    public void setSettingData(@NonNull UserSettingInfo settingInfo) {
        if (settingInfo.badges == null || settingInfo.titles == null) {
            throw new NullPointerException("传入数据不能为空");
        }
        if (settingInfo.titles.length != settingInfo.badges.length) {
            throw new IllegalArgumentException("标题长度：" + settingInfo.titles.length +
                    "  与数字长度：" + settingInfo.badges.length + " 不一致");
        }
        mLlContainer.removeAllViews();
        int length = settingInfo.titles.length;

        for (int i = 0; i < length; i++) {
            View v = LayoutInflater.from(this).inflate(R.layout.view_main_setting_row, mLlContainer,
                    false);
            TextView tvTitle = v.findViewById(R.id.tv_title);
            TextView tvBadge = v.findViewById(R.id.tv_badge);
            if (settingInfo.badges[i] <= 0) {
                tvBadge.setVisibility(View.GONE);
            } else {
                tvBadge.setText(String.valueOf(settingInfo.badges[i]));
                tvBadge.setVisibility(View.VISIBLE);
            }

            tvTitle.setText(settingInfo.titles[i]);
            mLlContainer.addView(v);
        }

    }

    @Override
    public void showToast(@NonNull String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * 显示用户改变身份对话框
     * @param userTypes
     */
    private void showChangeUserTypeDialog(List<Integer> userTypes) {

        final Dialog dialog = new Dialog(this);

        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        for (final Integer userType : userTypes) {
            TextView tv = new TextView(this);
            tv.setText(AppUtils.getUserTypeName(userType));
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    mPresenter.onUserClickChangeUserType(userType);
                }
            });
            linearLayout.addView(tv);
        }

        dialog.setContentView(linearLayout);
        dialog.setCancelable(true);
        dialog.show();
    }

}
