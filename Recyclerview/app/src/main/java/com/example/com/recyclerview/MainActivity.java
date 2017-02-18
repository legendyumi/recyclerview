package com.example.com.recyclerview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity implements View.OnClickListener{

    private RecyclerView recy_view;
    private List<String> data;
    private ViewHoderAdaapters adaapters;
    private Button add_but,rm_but,save;
    String name;
    int wz;
    int yu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        if(getIntent().hasExtra("name")){
            name=getIntent().getStringExtra("name");
        }
        if(getIntent().hasExtra("wz")){
            wz=(Integer.parseInt(getIntent().getStringExtra("wz"))) ;
        }
        data.remove(wz);
        data.add(name);
    }

    private void init() {
        add_but =  (Button) findViewById(R.id.add_but);
        add_but.setOnClickListener(this);
        rm_but =  (Button) findViewById(R.id.rm_but);
        rm_but.setOnClickListener(this);
        save =  (Button) findViewById(R.id.save);
        save.setOnClickListener(this);

        recy_view= (RecyclerView)findViewById(R.id.recy_view);
        //默认列表
        LinearLayoutManager LM = new LinearLayoutManager(this);
        //横向滑动
        recy_view.setLayoutManager(LM);


        //样式二,对应类DividerItemDecoration02
        recy_view.addItemDecoration(new DividerItemDecoration02(this,LinearLayoutManager.HORIZONTAL,R.drawable.style02));

        initData();
        adaapters = new ViewHoderAdaapters(MainActivity.this,data);

        adaapters.setOnItemListener(new ViewHoderAdaapters.OnItemClickListener() {
            public void onLongClick(int position) {
//                startActivity(new Intent(getApplicationContext(),XiuGaiActivity.class).putExtra("weizhi",position));
                Toast.makeText(MainActivity.this,"您点击了第："+position+"个Item",Toast.LENGTH_SHORT).show();
            }
            public void onClick(int position) {
                Toast.makeText(MainActivity.this,"您点击了第："+position+"个Item",Toast.LENGTH_SHORT).show();
            }
        });
        recy_view.setAdapter(adaapters);
    }


    private void initData() {
        data = new ArrayList<String>();
        for (int i=0; i < 3; i++) {
            data.add("我是熊大"+i);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.add_but:
                adaapters.addData(data.size());
                break;

            case R.id.rm_but:
                adaapters.removeData(1);
                break;
            case R.id.save:
                Toast.makeText(this,data.toString(),Toast.LENGTH_LONG).show();
                break;
        }
    }
}
