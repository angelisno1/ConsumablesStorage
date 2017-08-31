package com.inteall.consumablesstorage.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

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

//        searchView.setSuggestions(getResources().getStringArray(R.array.query_suggestions));
//        getApplyFormList();
        initData();
        rvApplyList.setLayoutManager(new LinearLayoutManager(this));
        rvApplyList.addItemDecoration(new SpaceItemDecoration(10));
        rvApplyList.setAdapter(new ApplyFormAdapter(this,applyForms));
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
                    public void onNext(HttpResult<List<ApplyForm>> value) {
                        Toast.makeText(getApplicationContext(), value.getData().get(0).getDepartment_Name(), Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void initData() {
        applyForms = new ArrayList<ApplyForm>();
        for (int i = 0; i < 10; i++) {
            ApplyForm applyForm = new ApplyForm();
            applyForm.setDepartment_Name("放射科" + i + "室");
            applyForms.add(applyForm);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        searchView.setMenuItem(item);
        return true;
    }
}
