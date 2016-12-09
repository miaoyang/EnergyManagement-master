package com.tony.energymanagement.em;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.tony.energymanagement.energymanagement.R;

public class Main extends AppCompatActivity implements View.OnClickListener{
    private Button btn_overview;
    private Button btn_energyMonitor;
    private Button btn_energyStat;
    private Button btn_energyAnalysis;
    private Button btn_airMonitor;
    private Button btn_dianlutu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_overview = (Button) findViewById(R.id.btn_overview);
        btn_energyMonitor = (Button) findViewById(R.id.btn_emonitor);
        btn_energyStat = (Button) findViewById(R.id.btn_estatistic);
        btn_energyAnalysis = (Button) findViewById(R.id.btn_eanalysis);
        btn_airMonitor = (Button) findViewById(R.id.btn_airMonitor);
        btn_dianlutu = (Button) findViewById(R.id.btn_dianlutu);

    }

    //设置监听
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_overview:
                break;

        }
    }
}
