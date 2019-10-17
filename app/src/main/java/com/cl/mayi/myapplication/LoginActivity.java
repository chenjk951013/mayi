package com.cl.mayi.myapplication;

import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cl.mayi.myapplication.MVP.contract.Logincontract;
import com.cl.mayi.myapplication.MVP.preseter.Loginpreseter;
import com.cl.mayi.myapplication.base.BaseActivity;
import com.cl.mayi.myapplication.network.APIParam;
import com.cl.mayi.myapplication.user.User;
import com.cl.mayi.myapplication.user.UserUtil;
import com.cl.mayi.myapplication.utils.TimeUtil;
import com.cl.mayi.myapplication.utils.convertutils;
import com.google.gson.Gson;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.cl.mayi.myapplication.utils.ActivityManagement.finishAllActivity;
import static com.cl.mayi.myapplication.utils.ToastUtil.showToast;
import static com.cl.mayi.myapplication.utils.ToastUtil.showresultToast;


/**
 * Created by yang on 2019/9/6.
 */

public class LoginActivity extends BaseActivity<Loginpreseter> implements Logincontract.View, View.OnClickListener {

    @Override
    protected Loginpreseter createPresenter() {
        return new Loginpreseter();
    }

    @Bind(R.id.phone)
    EditText phone;
    @Bind(R.id.loginpwd_password)
    EditText loginpwd_password;
    @Bind(R.id.code)
    EditText code;
    int posinn = 0;
    @Bind(R.id.login_isValid)
    TextView login_isValid;
    //密码点
    @Bind(R.id.account_line)
    TextView account_line;
    //账号点
    @Bind(R.id.msg_line)
    TextView msg_line;
    @Bind(R.id.msg_lay)
    LinearLayout msg_lay;
    @Bind(R.id.pwd_lay)
    LinearLayout pwd_lay;
    boolean isHideFirst = false;
    @Bind(R.id.login_yan_img)
    ImageView login_yan_img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        // initView();
    }
///15807920231
    //123456

    @OnClick({R.id.loginCommit, R.id.login_msg, R.id.lay_account, R.id.login_yan,R.id.login_isValid,R.id.login_register})
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            //密码
            case R.id.lay_account:
                posinn = 0;
                pwd_lay.setVisibility(View.VISIBLE);
                msg_lay.setVisibility(View.GONE);
                account_line.setVisibility(View.VISIBLE);
                msg_line.setVisibility(View.INVISIBLE);
                break;
            //账号
            case R.id.login_msg:
                posinn = 1;
                pwd_lay.setVisibility(View.GONE);
                msg_lay.setVisibility(View.VISIBLE);
                msg_line.setVisibility(View.VISIBLE);
                account_line.setVisibility(View.INVISIBLE);
                break;
            case R.id.loginCommit:
                String mobile = phone.getText().toString().trim();
                String password = loginpwd_password.getText().toString().trim();
                String codeTxt = code.getText().toString();



                if (posinn == 0) {
                    boolean verifyWalletInfo = verifyInfo(mobile, password, codeTxt);
                    if (verifyWalletInfo) {
                        showLoadingMessage();
                        try {
                            Map<String, String> maps = new HashMap<>();
                            maps.put("data", APIParam.loginpwd(mobile, password, ""));
                            mPresenter.getloginpwd(maps, 1);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                } else {

                    if (TextUtils.isEmpty(codeTxt)) {
                        showToast(getString(R.string.login_input_code));
                        // 同时判断强弱

                    } else {
                        showLoadingMessage();
                        try {
                            Map<String, String> maps = new HashMap<>();
                            maps.put("data", APIParam.login(mobile, codeTxt, ""));
                            mPresenter.getlogin(maps, 1);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }


                }
                //       startActivity(MainActivity.class);
                break;
            case R.id.login_isValid:
                String mobile1 = phone.getText().toString().trim();

                if (convertutils.isCellphone(mobile1) == false && convertutils.isEmail(mobile1) == false) {
                    showToast(getString(R.string.login_input_ct_phone));
                } else {
                    WeakReference<TextView> tvVerifyCode = new WeakReference<>(login_isValid);
                    TimeUtil.startTimer(tvVerifyCode, getString(R.string.login_huo_code), 60, 1);
                    Map<String, String> map1 = new HashMap<>();
                    try {
                        map1.put("data", APIParam.registerisValid(phone.getText().toString().trim()));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    // 创建元素并添加到集合

                    mPresenter.getisValid(map1);
                }

                break;
            case R.id.login_yan:
                if (isHideFirst == true) {
                    login_yan_img.setImageResource(R.mipmap.login_yan_gone);
                    loginpwd_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    isHideFirst = false;
                } else {

                    login_yan_img.setImageResource(R.mipmap.register_display);
                    //密文
                    loginpwd_password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    isHideFirst = true;
                }
                break;
            case R.id.login_register:
                startActivity(RegisterActivity.class);
                break;

        }

    }

    private boolean verifyInfo(String mobile, String password, String codeTxt) {
        if (TextUtils.isEmpty(mobile)) {
            showToast(getString(R.string.login_input_ct_phone));

            return false;
        } else if (TextUtils.isEmpty(password)) {
            showToast(getString(R.string.login_input_pwd));
            // 同时判断强弱
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

        closeLoadingMessage();

        if (type==1){

        Gson gson = new Gson();
        User user = gson.fromJson(data, User.class);
        UserUtil.commitUser(user);
        startActivity(MainActivity.class);
        finishAllActivity();
        }
    }
}
