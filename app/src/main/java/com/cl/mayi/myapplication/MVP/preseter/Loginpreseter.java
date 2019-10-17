package com.cl.mayi.myapplication.MVP.preseter;



import com.cl.mayi.myapplication.MVP.Model.LoginModle;
import com.cl.mayi.myapplication.MVP.contract.Logincontract;
import com.cl.mayi.myapplication.base.BasePresenter;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.util.Map;


import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static com.cl.mayi.myapplication.utils.jiami.encryption;



/**
 * Created by yang on 2019/4/24.
 */

public class Loginpreseter extends BasePresenter<Logincontract.View> implements Logincontract.Presenter {
    private Logincontract.Model model;
    private final CompositeDisposable mDisposable;

    public Loginpreseter() {
        this.model = new LoginModle();
        mDisposable = new CompositeDisposable();
    }

    @Override
    public void getlogin(Map<String, String> maps, final int type) {


        model.getlogin(encryption(maps))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<JsonObject>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mDisposable.add(d);
                    }

                    @Override
                    public void onNext(JsonObject request) {


                        try {
                            JSONObject jsonobject = new JSONObject(request.toString());
                            int status = jsonobject.getInt("code");
                            if (status == 0) {
                                String data = jsonobject.getString("data");

                                mView.loginSuccess(data, type);
                            } else {
                                mView.loginFail(jsonobject.getString("message"), status);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }

                    @Override
                    public void onError(Throwable e) {

                        if (e instanceof SocketTimeoutException) {
                            mView.loginFail("请求超时", 0);

                        } else if (e instanceof ConnectException) {
                            mView.loginFail("没有网络，请检查您的网络状态", 0);

                        } else if (e.getMessage().contains("No address associated with hostname")) {
                            mView.loginFail("没有网络，请检查您的网络状态", 0);

                        } else {
                            mView.loginFail("服务器异常", 0);

                        }

                    }

                    @Override
                    public void onComplete() {
                    }
                });

    }

    @Override
    public void getregister(Map<String, String> maps, final int type) {
        model.getregister(encryption(maps))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<JsonObject>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mDisposable.add(d);
                    }

                    @Override
                    public void onNext(JsonObject request) {


                        try {
                            JSONObject jsonobject = new JSONObject(request.toString());
                            int status = jsonobject.getInt("code");
                            if (status == 0) {
                                String data = jsonobject.getString("data");

                                mView.loginSuccess(data, type);
                            } else {
                                mView.loginFail(jsonobject.getString("message"), status);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }

                    @Override
                    public void onError(Throwable e) {

                        if (e instanceof SocketTimeoutException) {
                            mView.loginFail("请求超时", 0);

                        } else if (e instanceof ConnectException) {
                            mView.loginFail("没有网络，请检查您的网络状态", 0);

                        } else if (e.getMessage().contains("No address associated with hostname")) {
                            mView.loginFail("没有网络，请检查您的网络状态", 0);

                        } else {
                            mView.loginFail("服务器异常", 0);

                        }

                    }

                    @Override
                    public void onComplete() {
                    }
                });

    }

    @Override
    public void getisValid(Map<String, String> maps) {


        model.getisValid(encryption(maps))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<JsonObject>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mDisposable.add(d);
                    }

                    @Override
                    public void onNext(JsonObject request) {
                        try {
                            JSONObject jsonobject = new JSONObject(request.toString());
                            int status = jsonobject.getInt("code");
                            if (status == 0) {
                                String data = jsonobject.getString("data");

                                mView.loginSuccess(data, 0);
                            } else {
                                mView.loginFail(jsonobject.getString("message"), status);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }

                    @Override
                    public void onError(Throwable e) {

                        if (e instanceof SocketTimeoutException) {
                            mView.loginFail("请求超时", 0);

                        } else if (e instanceof ConnectException) {
                            mView.loginFail("没有网络，请检查您的网络状态", 0);

                        } else if (e.getMessage().contains("No address associated with hostname")) {
                            mView.loginFail("没有网络，请检查您的网络状态", 0);

                        } else {
                            mView.loginFail("服务器异常", 0);

                        }

                    }

                    @Override
                    public void onComplete() {
                    }
                });


    }

    public void getloginpwd(Map<String, String> maps, final int type) {

        model.getloginpwd(encryption(maps))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<JsonObject>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mDisposable.add(d);
                    }

                    @Override
                    public void onNext(JsonObject request) {


                        try {
                            JSONObject jsonobject = new JSONObject(request.toString());
                            int status = jsonobject.getInt("code");
                            if (status == 0) {
                                String data = jsonobject.getString("data");

                                mView.loginSuccess(data, type);
                            } else {
                                mView.loginFail(jsonobject.getString("message"), status);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }

                    @Override
                    public void onError(Throwable e) {

                        if (e instanceof SocketTimeoutException) {
                            mView.loginFail("请求超时", 0);

                        } else if (e instanceof ConnectException) {
                            mView.loginFail("没有网络，请检查您的网络状态", 0);

                        } else if (e.getMessage().contains("No address associated with hostname")) {
                            mView.loginFail("没有网络，请检查您的网络状态", 0);

                        } else {
                            mView.loginFail("服务器异常", 0);

                        }

                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    @Override
    public void appClient(Map<String, String> maps, final int type) {
        model.appClient(encryption(maps))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<JsonObject>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mDisposable.add(d);
                    }

                    @Override
                    public void onNext(JsonObject request) {


                        try {
                            JSONObject jsonobject = new JSONObject(request.toString());
                            int status = jsonobject.getInt("code");
                            if (status == 0) {
                                String data = jsonobject.getString("data");

                                mView.loginSuccess(jsonobject.toString(), type);
                            } else {
                                mView.loginFail(jsonobject.getString("message"), status);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }

                    @Override
                    public void onError(Throwable e) {

                        if (e instanceof SocketTimeoutException) {
                            mView.loginFail("请求超时", 0);

                        } else if (e instanceof ConnectException) {
                            mView.loginFail("没有网络，请检查您的网络状态", 0);

                        } else if (e.getMessage().contains("No address associated with hostname")) {
                            mView.loginFail("没有网络，请检查您的网络状态", 0);

                        } else {
                            mView.loginFail("服务器异常", 0);

                        }

                    }

                    @Override
                    public void onComplete() {
                    }
                });

    }


    @Override
    public void detachView() {
        super.detachView();
        mDisposable.clear();
    }


}
