package com.inteall.consumablesstorage.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.inteall.consumablesstorage.MainActivity;
import com.inteall.consumablesstorage.R;
import com.inteall.consumablesstorage.api.ApiService;
import com.inteall.consumablesstorage.entity.UserInfo;
import com.inteall.consumablesstorage.entity.HttpResult;
import com.inteall.consumablesstorage.http.RetrofitUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.et_userName)
    EditText etUserName;
    @BindView(R.id.et_passWord)
    EditText etPassWord;
    @BindView(R.id.btn_login)
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.btn_login)
    public void login() {
        String userName = etUserName.getText().toString().trim();
        String passWord = etPassWord.getText().toString().trim();
        if (TextUtils.isEmpty(userName) || TextUtils.isEmpty(passWord)) {
            Toast.makeText(this, "请填写用户名或密码！", Toast.LENGTH_LONG).show();
            return;
        }
        String baseUrl = "http://10.0.3.2:5072";
        RetrofitUtils.getInstance().getRetrofit(baseUrl)
                .create(ApiService.class)
                .login(userName, passWord)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HttpResult<UserInfo>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(HttpResult<UserInfo> value) {
                        if (value.getMessage().equals("Success")) {
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                            LoginActivity.this.finish();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        LoginActivity.this.finish();
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

}
