package com.ym.charts;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.tony.energymanagement.energymanagement.R;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.listener.ColumnChartOnValueSelectListener;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Column;
import lecho.lib.hellocharts.model.ColumnChartData;
import lecho.lib.hellocharts.model.SubcolumnValue;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.ColumnChartView;

/**
 * Created by yangmiao on 2016/12/8.
 */

public class ShowColumnCharts extends Activity {
    private ColumnChartView mColumnChartView;
    private ColumnChartData mColumnChartData;
    private static final int DEFAULT_DATA = 0;
    private static final int SUBCOLUMNS_DATA = 1;
    private static final int STACKED_DATA = 2;
    private static final int NEGATIVE_SUBCOLUMNS_DATA = 3;
    private static final int NEGATIVE_STACKED_DATA = 4;
    private boolean hasAxes = true;
    private boolean hasAxesNames = true;
    private boolean hasLabels = true;
    private boolean hasLabelForSelected = false;
    private int dataType = DEFAULT_DATA;
    // private int dataType = SUBCOLUMNS_DATA;
    // private int dataType = STACKED_DATA;
    // private int dataType = NEGATIVE_SUBCOLUMNS_DATA;
    // private int dataType = NEGATIVE_STACKED_DATA;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_columncharts);
        mColumnChartView = (ColumnChartView) findViewById(R.id.column);
//        mColumnChartView.setOnValueTouchListener(new ValueTouchListener());
        generateData();
    }
    private void generateData() {
        switch (dataType) {
            case DEFAULT_DATA:
                generateDefaultData();
                break;
            case SUBCOLUMNS_DATA:
                generateSubcolumnsData();
                break;
            case STACKED_DATA:
                generateStackedData();
                break;
            case NEGATIVE_SUBCOLUMNS_DATA:
                generateNegativeSubcolumnsData();
                break;
            case NEGATIVE_STACKED_DATA:
                generateNegativeStackedData();
                break;
            default:
                generateDefaultData();
                break;
        }
    }
    private void reset() {
        hasAxes = true;
        hasAxesNames = true;
        hasLabels = false;
        hasLabelForSelected = false;
        dataType = DEFAULT_DATA;
        mColumnChartView.setValueSelectionEnabled(hasLabelForSelected);
    }
    private void generateDefaultData() {
        int numSubcolumns = 1;//设置每个柱状图显示的颜色数量(每个柱状图显示多少块)
        int numColumns = 8;//柱状图的数量
        // Column can have many subcolumns, here by default I use 1 subcolumn in each of 8 columns.
        List<Column> columns = new ArrayList<Column>();
        List<SubcolumnValue> values;
        for (int i = 0; i < numColumns; ++i) {
            values = new ArrayList<SubcolumnValue>();
            for (int j = 0; j < numSubcolumns; ++j) {
                SubcolumnValue value = new SubcolumnValue((float) Math.random() * 50f + 5, ChartUtils.pickColor());//第一个值是数值(值>0 方向朝上，值<0，方向朝下)，第二个值是颜色
                //    SubcolumnValue value = new SubcolumnValue((float) Math.random() * 50f + 5, Color.parseColor("#00000000"));//第一个值是数值，第二个值是颜色
                //    values.add(new SubcolumnValue((float) Math.random() * 50f + 5, ChartUtils.pickColor()));
                values.add(value);
            }
            Column column = new Column(values);//一个柱状图的实例
            column.setHasLabels(hasLabels);//设置是否显示每个柱状图的高度，
            column.setHasLabelsOnlyForSelected(hasLabelForSelected);//点击的时候是否显示柱状图的高度，和setHasLabels()和用的时候，setHasLabels()失效
            columns.add(column);
        }
        mColumnChartData = new ColumnChartData(columns);//表格的数据实例
        if (hasAxes) {
            Axis axisX = new Axis();
            //   axisX.setInside(true);//是否显示在里面，默认为false
            AxisValue value1 = new AxisValue(0f);//值是在哪显示 0 是指 第0个柱状图
            value1.setLabel("[0-15]");//设置显示的文本，默认为柱状图的位置
            AxisValue value2 = new AxisValue(1.0f);
            value2.setLabel("[15-30]");
            AxisValue value3 = new AxisValue(2.0f);
            value3.setLabel("[30-35]");
            AxisValue value4 = new AxisValue(3.0f);
            value4.setLabel("[35-45]");
            AxisValue value5 = new AxisValue(4.0f);
            value5.setLabel("[45-50]");
            AxisValue value6 = new AxisValue(5.0f);
            value6.setLabel("[50-55]");
            AxisValue value7 = new AxisValue(6.0f);
            value7.setLabel("[55-60]");
            AxisValue value8 = new AxisValue(7.0f);
            value8.setLabel("[65-70]");
            List<AxisValue> axisValues = new ArrayList<AxisValue>();
            axisValues.add(value1);
            axisValues.add(value2);
            axisValues.add(value3);
            axisValues.add(value4);
            axisValues.add(value5);
            axisValues.add(value6);
            axisValues.add(value7);
            axisValues.add(value8);
            axisX.setValues(axisValues);
            Axis axisY = new Axis().setHasLines(true);
            if (hasAxesNames) {
                axisX.setName("Axis X(涂涂的坐标轴)");//设置X轴的注释
                axisY.setName("Axis Y");//设置Y轴的注释
            }
            mColumnChartData.setAxisXBottom(axisX);//设置X轴显示的位置
            mColumnChartData.setAxisYLeft(axisY);//设置Y轴显示的位置
        } else {
            mColumnChartData.setAxisXBottom(null);
            mColumnChartData.setAxisYLeft(null);
        }
        mColumnChartView.setColumnChartData(mColumnChartData);//为View设置数据
    }
    /**
     * Generates columns with subcolumns, columns have larger separation than subcolumns.
     */
    private void generateSubcolumnsData() {
        int numSubcolumns = 4;
        int numColumns = 4;
        // Column can have many subcolumns, here I use 4 subcolumn in each of 8 columns.
        List<Column> columns = new ArrayList<Column>();
        List<SubcolumnValue> values;
        for (int i = 0; i < numColumns; ++i) {
            values = new ArrayList<SubcolumnValue>();
            for (int j = 0; j < numSubcolumns; ++j) {
                values.add(new SubcolumnValue((float) Math.random() * 50f + 5, ChartUtils.pickColor()));
            }
            Column column = new Column(values);
            column.setHasLabels(hasLabels);
            column.setHasLabelsOnlyForSelected(hasLabelForSelected);
            columns.add(column);
        }
        mColumnChartData = new ColumnChartData(columns);
        if (hasAxes) {
            Axis axisX = new Axis();
            Axis axisY = new Axis().setHasLines(true);
            if (hasAxesNames) {
                axisX.setName("Axis X");
                axisY.setName("Axis Y");
            }
            mColumnChartData.setAxisXBottom(axisX);
            mColumnChartData.setAxisYLeft(axisY);
        } else {
            mColumnChartData.setAxisXBottom(null);
            mColumnChartData.setAxisYLeft(null);
        }
        mColumnChartView.setColumnChartData(mColumnChartData);
    }
    /**
     * Generates columns with stacked subcolumns.
     */
    private void generateStackedData() {
        int numSubcolumns = 4;
        int numColumns = 8;
        // Column can have many stacked subcolumns, here I use 4 stacke subcolumn in each of 4 columns.
        List<Column> columns = new ArrayList<Column>();
        List<SubcolumnValue> values;
        for (int i = 0; i < numColumns; ++i) {
            values = new ArrayList<SubcolumnValue>();
            for (int j = 0; j < numSubcolumns; ++j) {
                values.add(new SubcolumnValue((float) Math.random() * 20f + 5, ChartUtils.pickColor()));
            }
            Column column = new Column(values);
            column.setHasLabels(hasLabels);
            column.setHasLabelsOnlyForSelected(hasLabelForSelected);
            columns.add(column);
        }
        mColumnChartData = new ColumnChartData(columns);
        // Set stacked flag.
        mColumnChartData.setStacked(true);
        if (hasAxes) {
            Axis axisX = new Axis();
            Axis axisY = new Axis().setHasLines(true);
            if (hasAxesNames) {
                axisX.setName("Axis X");//设置X轴的注释
                axisY.setName("Axis Y");//设置Y轴的注释
            }
            mColumnChartData.setAxisXBottom(axisX);//设置X轴显示的位置
            mColumnChartData.setAxisYLeft(axisY);//设置Y轴显示的位置
        } else {
            mColumnChartData.setAxisXBottom(null);
            mColumnChartData.setAxisYLeft(null);
        }
        mColumnChartView.setColumnChartData(mColumnChartData);
    }
    private void generateNegativeSubcolumnsData() {
        int numSubcolumns = 4;
        int numColumns = 4;
        List<Column> columns = new ArrayList<Column>();
        List<SubcolumnValue> values;
        for (int i = 0; i < numColumns; ++i) {
            values = new ArrayList<SubcolumnValue>();
            for (int j = 0; j < numSubcolumns; ++j) {
                int sign = getSign();
                values.add(new SubcolumnValue((float) Math.random() * 50f * sign + 5 * sign, ChartUtils.pickColor
                        ()));
            }
            Column column = new Column(values);
            column.setHasLabels(hasLabels);
            column.setHasLabelsOnlyForSelected(hasLabelForSelected);
            columns.add(column);
        }
        mColumnChartData = new ColumnChartData(columns);
        if (hasAxes) {
            Axis axisX = new Axis();
            Axis axisY = new Axis().setHasLines(true);
            if (hasAxesNames) {
                axisX.setName("Axis X");
                axisY.setName("Axis Y");
            }
            mColumnChartData.setAxisXBottom(axisX);
            mColumnChartData.setAxisYLeft(axisY);
        } else {
            mColumnChartData.setAxisXBottom(null);
            mColumnChartData.setAxisYLeft(null);
        }
        mColumnChartView.setColumnChartData(mColumnChartData);
    }
    private void generateNegativeStackedData() {
        int numSubcolumns = 4;
        int numColumns = 8;
        // Column can have many stacked subcolumns, here I use 4 stacke subcolumn in each of 4 columns.
        List<Column> columns = new ArrayList<Column>();
        List<SubcolumnValue> values;
        for (int i = 0; i < numColumns; ++i) {
            values = new ArrayList<SubcolumnValue>();
            for (int j = 0; j < numSubcolumns; ++j) {
                int sign = getSign();
                values.add(new SubcolumnValue((float) Math.random() * 20f * sign + 5 * sign, ChartUtils.pickColor()));
            }
            Column column = new Column(values);
            column.setHasLabels(hasLabels);
            column.setHasLabelsOnlyForSelected(hasLabelForSelected);
            columns.add(column);
        }
        mColumnChartData = new ColumnChartData(columns);
        // Set stacked flag.
        mColumnChartData.setStacked(true);
        if (hasAxes) {
            Axis axisX = new Axis();
            Axis axisY = new Axis().setHasLines(true);
            if (hasAxesNames) {
                axisX.setName("Axis X");
                axisY.setName("Axis Y");
            }
            mColumnChartData.setAxisXBottom(axisX);
            mColumnChartData.setAxisYLeft(axisY);
        } else {
            mColumnChartData.setAxisXBottom(null);
            mColumnChartData.setAxisYLeft(null);
        }
        mColumnChartView.setColumnChartData(mColumnChartData);
    }
    private int getSign() {
        int[] sign = new int[]{-1, 1};
        return sign[Math.round((float) Math.random())];
    }
    private void toggleLabels() {
        hasLabels = !hasLabels;
        if (hasLabels) {
            hasLabelForSelected = false;
            mColumnChartView.setValueSelectionEnabled(hasLabelForSelected);
        }
        generateData();
    }
    private void toggleLabelForSelected() {
        hasLabelForSelected = !hasLabelForSelected;
        mColumnChartView.setValueSelectionEnabled(hasLabelForSelected);
        if (hasLabelForSelected) {
            hasLabels = false;
        }
        generateData();
    }
    private void toggleAxes() {
        hasAxes = !hasAxes;
        generateData();
    }
    private void toggleAxesNames() {
        hasAxesNames = !hasAxesNames;
        generateData();
    }
    /**
     * To animate values you have to change targets values and then call
     *
     * method(don't confuse with View.animate()).
     */
    private void prepareDataAnimation() {
        for (Column column : mColumnChartData.getColumns()) {
            for (SubcolumnValue value : column.getValues()) {
                value.setTarget((float) Math.random() * 100);
            }
        }
    }
    private class ValueTouchListener implements ColumnChartOnValueSelectListener {
        @Override
        public void onValueSelected(int columnIndex, int subcolumnIndex, SubcolumnValue value) {
            Toast.makeText(ShowColumnCharts.this, "Selected: " + value, Toast.LENGTH_SHORT).show();
        }
        @Override
        public void onValueDeselected() {
        }
    }
}
