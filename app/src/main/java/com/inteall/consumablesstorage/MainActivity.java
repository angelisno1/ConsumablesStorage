package com.inteall.consumablesstorage;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.inteall.consumablesstorage.Listener.RvOnClickItemListener;
import com.inteall.consumablesstorage.adapter.MainMenuAdapter;
import com.inteall.consumablesstorage.entity.MainMenu;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.rv_main_list)
    RecyclerView rvMainList;
    List<MainMenu> mainMenus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        //设置布局管理器
        initMainMenu();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvMainList.setLayoutManager(linearLayoutManager);
        MainMenuAdapter menuAdapter = new MainMenuAdapter(this, mainMenus);
        menuAdapter.setOnItemClickListener(new RvOnClickItemListener() {
            @Override
            public void onClickItemListener(View view, int position) {
                Toast.makeText(MainActivity.this,mainMenus.get(position).getTitle(),Toast.LENGTH_SHORT).show();
            }
        });
        rvMainList.setAdapter(menuAdapter);
    }

    private void initMainMenu() {
        mainMenus = new ArrayList<MainMenu>();
        MainMenu outMenu = new MainMenu();
        outMenu.setTitle("出库");
        outMenu.setImageSrc(R.drawable.storage_out_logo);
        MainMenu inMenu = new MainMenu();
        inMenu.setTitle("入库");
        inMenu.setImageSrc(R.drawable.storage_out_logo);
        MainMenu checkMenu = new MainMenu();
        checkMenu.setTitle("盘点");
        checkMenu.setImageSrc(R.drawable.storage_out_logo);
        mainMenus.add(outMenu);
        mainMenus.add(inMenu);
        mainMenus.add(checkMenu);
    }
}
