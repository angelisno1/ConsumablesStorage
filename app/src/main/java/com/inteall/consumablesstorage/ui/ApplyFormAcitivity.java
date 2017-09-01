package com.inteall.consumablesstorage.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.inteall.consumablesstorage.Listener.RvOnClickItemListener;
import com.inteall.consumablesstorage.R;
import com.inteall.consumablesstorage.adapter.ApplyFormAdapter;
import com.inteall.consumablesstorage.api.ApiService;
import com.inteall.consumablesstorage.entity.ApplyForm;
import com.inteall.consumablesstorage.entity.HttpResult;
import com.inteall.consumablesstorage.http.RetrofitUtils;
import com.inteall.consumablesstorage.view.SpaceItemDecoration;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ApplyFormAcitivity extends AppCompatActivity {
    @BindView(R.id.apply_toolbar)
    Toolbar applyToolbar;
    @BindView(R.id.search_view)
    MaterialSearchView searchView;
    @BindView(R.id.rv_apply_list)
    RecyclerView rvApplyList;
    List<ApplyForm> applyForms;
    ApplyFormAdapter formAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_applyform);
        ButterKnife.bind(this);
        setSupportActionBar(applyToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        searchView.setVoiceSearch(false);
        searchView.setCursorDrawable(R.drawable.custom_cursor);
        searchView.setEllipsize(true);
        getApplyFormList();
//        initData();
    }
    /**
     * 获取待出库的申请单列表
     */
    private void getApplyFormList() {
        RetrofitUtils.getInstance().getRetrofit("http://10.0.3.2:5072")
                .create(ApiService.class)
                .getApplyForm()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<HttpResult<List<ApplyForm>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }
                    @Override
                    public void onNext(final HttpResult<List<ApplyForm>> value) {
                        formAdapter = new ApplyFormAdapter(ApplyFormAcitivity.this, value.getData());
                        formAdapter.setOnClickItemListener(new RvOnClickItemListener() {
                            @Override
                            public void onClickItemListener(View view, int position) {

                            }
                        });
                        rvApplyList.setLayoutManager(new LinearLayoutManager(ApplyFormAcitivity.this));
                        rvApplyList.addItemDecoration(new SpaceItemDecoration(10));
                        rvApplyList.setAdapter(formAdapter);
                    }
                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        searchView.setMenuItem(item);
        return true;
    }
}
