package com.example.com.recyclerview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by oudehuitong on 2016/12/6.
 */

public class XiuGaiActivity extends Activity implements View.OnClickListener{
    private TextView textview;
    private Button save;
    String name;
    String position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xiugai);
        save= (Button) findViewById(R.id.save);
        textview= (TextView) findViewById(R.id.textview);
        name=textview.getText().toString();
        if (getIntent().hasExtra("weizhi")){
            position= getIntent().getStringExtra("weizhi");
        }
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.save:
                startActivity(new Intent(this,MainActivity.class).putExtra("name",name).putExtra("wz",position));
                this.finish();
                break;
        }
    }
}
