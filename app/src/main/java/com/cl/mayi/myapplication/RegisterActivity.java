package com.cl.mayi.myapplication;

import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.cl.mayi.myapplication.MVP.contract.Logincontract;
import com.cl.mayi.myapplication.MVP.preseter.Loginpreseter;
import com.cl.mayi.myapplication.base.BaseActivity;
import com.cl.mayi.myapplication.network.APIParam;
import com.cl.mayi.myapplication.utils.TimeUtil;
import com.cl.mayi.myapplication.utils.convertutils;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.cl.mayi.myapplication.utils.ToastUtil.showLongToast;
import static com.cl.mayi.myapplication.utils.ToastUtil.showToast;
import static com.cl.mayi.myapplication.utils.ToastUtil.showresultToast;


/**
 * Created by yang on 2019/6/12.
 */

public class RegisterActivity extends BaseActivity<Loginpreseter> implements Logincontract.View, View.OnClickListener {
    @Bind(R.id.register_back)
    LinearLayout register_back;
    //手机号
    @Bind(R.id.register_mobile)
    EditText register_mobile;
    //短信码
    @Bind(R.id.register_mobileCode)
    EditText register_mobileCode;
    //用户密码
    @Bind(R.id.register_password)
    EditText register_password;
    //确定用户密码
    @Bind(R.id.register_password1)
    EditText register_password1;
    //用户邀请码
    @Bind(R.id.register_inviteCode)
    EditText register_inviteCode;
    //交易密码
    @Bind(R.id.register_transPassword)
    EditText register_transPassword;

    String mobile, mobileCode, password, inviteCode, transPassword, password1;
    @Bind(R.id.register_yan)
    LinearLayout register_yan;
    @Bind(R.id.register_yan_img)
    ImageView register_yan_img;

    @Bind(R.id.register_isValid)
    TextView register_isValid;
    boolean isHideFirst = false;

 int  xingzuoid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        ButterKnife.bind(this);
        initview();
    }

    private void initview() {
//获得控件ID


    }

    @Override
    protected Loginpreseter createPresenter() {
        return new Loginpreseter();
    }

    @OnClick({R.id.register_back, R.id.register_bt, R.id.register_yan, R.id.register_isValid})
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.register_back:
                finish();
                break;
            case R.id.register_isValid:
                String mobile1 = register_mobile.getText().toString().trim();

                if (convertutils.isCellphone(mobile1) == false && convertutils.isEmail(mobile1) == false) {
                    showToast(getString(R.string.login_input_ct_phone));
                } else {
                    WeakReference<TextView> tvVerifyCode = new WeakReference<>(register_isValid);
                    TimeUtil.startTimer(tvVerifyCode, getString(R.string.login_huo_code), 60, 1);
                    Map<String, String> map1 = new HashMap<>();
                    try {
                        map1.put("data", APIParam.registerisValid(register_mobile.getText().toString().trim()));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    // 创建元素并添加到集合

                    mPresenter.getisValid(map1);
                }

                break;
            case R.id.register_yan:
                if (isHideFirst == true) {
                    register_yan_img.setImageResource(R.mipmap.login_yan_gone);


                    register_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    isHideFirst = false;
                } else {
                    register_yan_img.setImageResource(R.mipmap.register_display);
                    //密文
                    register_password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    isHideFirst = true;
                }
                break;

            case R.id.register_bt:
                mobile = register_mobile.getText().toString().trim();
                mobileCode = register_mobileCode.getText().toString().trim();
                password = register_password.getText().toString().trim();
                password1 = register_password1.getText().toString().trim();
                inviteCode = register_inviteCode.getText().toString().trim();
                transPassword = register_transPassword.getText().toString().trim();
                boolean verifyWalletInfo = verifyInfo(mobile, mobileCode, password, password1, inviteCode, transPassword);
                if (verifyWalletInfo) {
                    showLoadingMessage();
                    try {
                        Map<String, String> maps = new HashMap<>();
                        maps.put("data", APIParam.register(mobile, mobileCode, password, inviteCode, transPassword,xingzuoid));
                        mPresenter.getregister(maps, 1);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                break;
        }

    }

    private boolean verifyInfo(String mobile, String mobileCode, String password, String password1, String inviteCode, String transPassword) {
        if (TextUtils.isEmpty(mobile)) {
            showToast(getString(R.string.login_input_ct_phone));
            // 同时判断强弱
            return false;
        } else if (TextUtils.isEmpty(mobileCode)) {
            showToast(getString(R.string.login_input_code));
            return false;
        } else if (TextUtils.isEmpty(password)) {
            showToast(getString(R.string.password));

        } else if (TextUtils.isEmpty(password1)) {
            showToast(getString(R.string.register_confirm_qpwd));
        } else if (TextUtils.isEmpty(inviteCode)) {
            showToast(getString(R.string.login_input_ycode));
        } else if (TextUtils.isEmpty(transPassword)) {
            showToast(getString(R.string.login_input_jpwd));
        } else if (password.length() < 6) {
            showToast(getString(R.string.register_tips1));
            return false;
        } else if (!TextUtils.equals(password, password1)) {
            showToast(getString(R.string.login_input_nopwd));
            return false;
        }
        return true;
    }

    @Override
    public void loginFail(String msg, int status) {
        closeLoadingMessage();
        showresultToast(msg, status);
    }

    @Override
    public void loginSuccess(String data, int type) {
        if (type == 1) {
            closeLoadingMessage();
            showLongToast(getString(R.string.register_su));
            finish();
        }

    }
}
