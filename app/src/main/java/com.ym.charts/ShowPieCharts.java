package com.ym.charts;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.tony.energymanagement.energymanagement.R;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.listener.PieChartOnValueSelectListener;
import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.PieChartView;

/**
 * Created by yangmiao on 2016/12/8.
 */

public class ShowPieCharts extends Activity {
    private PieChartView chart;
    private PieChartData data;
    private boolean hasLabels = true;
    private boolean hasLabelsOutside = true;
    private boolean hasCenterCircle = true;
    private boolean hasCenterText1 = true;//环形中间的文字1
    private boolean hasCenterText2 = false;
    private boolean isExploded = true;
    private boolean hasLabelForSelected = true;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_piecharts);
     /*  当然也可以在java代码中直接创建：
       LineChartView chart = new LineChartView(context);
       layout.addView(chart);*/
        initViews();
        initDates();
    }

    private void initDates() {
        generateData();
    }

    private void initViews() {
        chart=(PieChartView)findViewById(R.id.pie_charts);
//        chart.setOnValueTouchListener(new ValueTouchListener());//添加点击事件
        chart.setCircleFillRatio(0.9f);//设置图所占整个view的比例  当有外面的数据时使用，防止数据显示不全
        btn=(Button)findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prepareDataAnimation();//更新数据，并添加动画
            }
        });
    }

    /**
     * 生成数据
     */
    private void generateData() {
        int numValues = 10;//分成的块数

        List<SliceValue> values = new ArrayList<SliceValue>();
        for (int i = 0; i < numValues; ++i) {
            SliceValue sliceValue = new SliceValue(20.0f, ChartUtils.pickColor());//每一块的值和颜色，图标根据值自动进行比例分配
            values.add(sliceValue);
        }
        data = new PieChartData(values);
        data.setHasLabels(true);//显示数据
        data.setHasLabelsOnlyForSelected(false);//不用点击显示占的百分比
        data.setHasLabelsOutside(true);//占的百分比是否显示在饼图外面
        data.setHasCenterCircle(true);;//是否是环形显示
        data.setCenterCircleScale(0.5f);////设置环形的大小级别
        data.setValueLabelBackgroundColor(Color.TRANSPARENT);////设置值得背景透明
        data.setValueLabelBackgroundEnabled(false);//数据背景不显示
        data.setValueLabelsTextColor(Color.BLACK);

        //data.setValues(values);//填充数据
        if (isExploded) {
            data.setSlicesSpacing(1);//设置间隔为0
        }

        if (hasCenterText1) {
            data.setCenterText1("Hello!");

            // Get roboto-italic font.
            //      Typeface tf = Typeface.createFromAsset(MainActivity.this.getAssets(), "Roboto-Italic.ttf");//设置字体
            //   data.setCenterText1Typeface(tf);

            // Get font size from dimens.xml and convert it to sp(library uses sp values).
            data.setCenterText1FontSize(ChartUtils.px2sp(getResources().getDisplayMetrics().scaledDensity,
                    (int) getResources().getDimension(R.dimen.activity_horizontal_margin)));
            data.setCenterText1Color(Color.BLACK);////设置值得颜色*/
        }



        chart.setPieChartData(data);
    }


    /**
     * To animate values you have to change targets values and then call}
     * method(don't confuse with View.animate()).
     */
    private void prepareDataAnimation() {
        for (SliceValue value : data.getValues()) {
            value.setTarget((float) Math.random() * 30 + 15);//更新数据
        }
        chart.startDataAnimation();
    }


    private class ValueTouchListener implements PieChartOnValueSelectListener {

        @Override
        public void onValueSelected(int arcIndex, SliceValue value) {
//            Toast.makeText(ShowLineCharts.this, "Selected: " + value, Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onValueDeselected() {

        }

    }
}
