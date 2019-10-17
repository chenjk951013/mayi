package com.cl.mayi.myapplication.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;


import com.cl.mayi.myapplication.R;

import butterknife.ButterKnife;


/**
 * Created by Administrator on 2018/3/20.
 */

public class LoadingDialog extends Dialog {

    public LoadingDialog(@NonNull Context context) {
        super(context, R.style.UniversalDialogStyle);
    }

    public LoadingDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, R.style.UniversalDialogStyle);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Window window = this.getWindow();
        assert window != null;
        window.requestFeature(Window.FEATURE_NO_TITLE);
        // window.setWindowAnimations(R.style.keyboard_dialog_anim);
        setContentView(R.layout.dialog_loading2);
        ButterKnife.bind(this);
        WindowManager.LayoutParams lp = window.getAttributes(); // 获取对话框当前的参数值
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.CENTER;
        setCancelable(false);
        window.setAttributes(lp);
    }

}
