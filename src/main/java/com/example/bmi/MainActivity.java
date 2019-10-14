package com.example.bmi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private EditText mEtHeight;
    private EditText mEtWeight;
    private Button mBtCount;
    private Button mBtExit;
    private TextView mTvShow;

    public Double wight;
    public Double height;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mEtHeight = findViewById(R.id.et_height);
        mEtWeight = findViewById(R.id.et_weight);
        mBtCount = findViewById(R.id.bt_count);
        mBtExit = findViewById(R.id.bt_Exit);
        mTvShow = findViewById(R.id.tv_show);
        setListener();
    }

    private void setListener() {
        OnClick onClick = new OnClick();
        mBtCount.setOnClickListener(onClick);
        mBtExit.setOnClickListener(onClick);

    }

    private class OnClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            /*wight = 0.0;
            height = 0.0;
            //对空值处理
            if ((mEtHeight.getText().length() != 0) && (mEtWeight.getText().length() != 0)) {
                height = Double.parseDouble(mEtHeight.getText().toString().trim());
                wight = Double.parseDouble(mEtWeight.getText().toString().trim());
            }else{
                Toast.makeText(MainActivity.this,"请输入身高或体重！！",Toast.LENGTH_SHORT).show();
                return;
            }*/
            String sWeight=mEtWeight.getText().toString().trim();
            String sHeight=mEtHeight.getText().toString().trim();
            if ("".equals(sHeight)){
                mEtHeight.setError("身高不能为空！");
                return;
            }
            if("".equals(sWeight)){
                mEtWeight.setError("体重不能为空！");
                return;
            }
            height=Double.parseDouble(sHeight);
            wight=Double.parseDouble(sWeight);
            switch (v.getId()) {
                case R.id.bt_count:
                    show(wight,height/100);
                    break;
                case R.id.bt_Exit:
                    System.exit(0);
                    break;
                default:
                    break;
            }

        }

        private void show(double wight, double height) {
            if (wight > 200 || height < 0.200 || height > 2.50 || wight < 10) {
                Toast.makeText(MainActivity.this, "输入有误，重新输入！", Toast.LENGTH_SHORT).show();
            } else {
                double sum = wight / ((height * height));
                DecimalFormat df = new DecimalFormat("####.00");
                if (0 < sum && sum < 18.5) {
                    mTvShow.setText("BMI指数为:" + df.format(sum) + "，体重过轻，注意饮食");
                } else if (sum >= 18.5 && sum <= 23.9) {
                    mTvShow.setText("BMI指数为:" + df.format(sum) + "，体重正常");
                } else if (sum >= 24 && sum <= 27.9) {
                    mTvShow.setText("BMI指数为:" + df.format(sum) + "，体重过重，注意饮食");
                } else if (sum >= 28 && sum <= 32) {
                    mTvShow.setText("BMI指数为:" + df.format(sum) + "，身体过胖，请减肥");
                } else {
                    mTvShow.setText("BMI指数为:" + df.format(sum) + "，身体非常肥胖，请减肥");
                }
                //mTvShow.setText(""+sum);
            }

        }
    }
}
