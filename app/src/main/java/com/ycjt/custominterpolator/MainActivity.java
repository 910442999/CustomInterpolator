package com.ycjt.custominterpolator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends AppCompatActivity {

    @Bind(R.id.fragment)
    Button mFragment;
    @Bind(R.id.activity1)
    Button mActivity;
    @Bind(R.id.activity2)
    Button mActivity2;
    private Intent mIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.activity1,R.id.activity2, R.id.fragment})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.activity1:
                //自定义选项卡
                mIntent = new Intent(this, ActivityInterpolator.class);
                startActivity(mIntent);
                break;
            //利用三方库
            case R.id.activity2:
                mIntent = new Intent(this, ActivityInterpolator2.class);
                startActivity(mIntent);
                break;
            case R.id.fragment:
                mIntent = new Intent(this, FragmentInterpolator.class);
                startActivity(mIntent);
                break;
        }
    }
}
