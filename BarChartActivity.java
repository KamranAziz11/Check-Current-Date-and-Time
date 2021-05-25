package com.example.gettingrecentdateandtime;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class BarChartActivity extends AppCompatActivity
{
    Button btnback;
    BarChart barChart;
    public String newmonth="april";
    int id;
    String year, month, day, expense, amount;
    ArrayList<BarEntry>ExpenseReport;

    DBManager dbm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_chart);
        getSupportActionBar ().hide ();
        dbm = new DBManager(this);

        TypeCasting();

        barChart.setDrawBarShadow(false);
        barChart.setDrawValueAboveBar(true);
        barChart.setMaxVisibleValueCount(50);
        barChart.setPinchZoom(false);
        barChart.setDrawGridBackground(true);
        /////////////////////////////////////////////

        dbm = new DBManager(this);
        ArrayList<BarEntry> ExpenseReport = new ArrayList<>();

        Cursor cursor = dbm.GettingDataBack();
       // Cursor cursor=dbm.getdataWithMonth();
        if (cursor.getCount() != 0) {
            while (cursor.moveToNext()) {
                id = cursor.getInt(0);
                year = cursor.getString(1);
                month = cursor.getString(2);
                day = cursor.getString(3);
                expense = cursor.getString(4);
                amount = cursor.getString(5);

              /*  day=cursor.getString(0);
                amount=cursor.getString(1); */

                int days = Integer.parseInt(day);
                int amounts = Integer.parseInt(amount);
                ExpenseReport.add(new BarEntry(days, amounts));
            }
            ////////////////////////////////////////////////////////
       /* BarProperties();
        BarEntries();
      //  DataSetBar();
        BarDataSet barDataSet=new BarDataSet(ExpenseReport,"Report");
        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        BarData data=new BarData(barDataSet);
        data.setBarWidth(0.9f);
        barChart.setData(data); */
        }

      /*  BarDataSet barDataSet=new BarDataSet(ExpenseReport,"Report");
        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        BarData data=new BarData(barDataSet);
        data.setBarWidth(0.9f);
        barChart.setData(data); */

        BarDataSet barDataSet=new BarDataSet(ExpenseReport,"Daily Expenses");
        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        barDataSet.setValueTextColor(Color.RED);
        barDataSet.setValueTextSize(16f);

        BarData barData=new BarData(barDataSet);

        barChart.setFitBars(true);
        barChart.setData(barData);
        barChart.getDescription().setText(" Bar Chart Fot the Month of "+month);
        barChart.animateY(2000);
    }

  /*  private void DataSetBar()
    {
        BarDataSet barDataSet=new BarDataSet(ExpenseReport,"Day Wise Report");
        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);

        BarData data=new BarData(barDataSet);
        data.setBarWidth(0.9f);
        barChart.setData(data);

    }

    private void BarEntries()
    {
        dbm=new DBManager(this);
        ArrayList<BarEntry>ExpenseReport=new ArrayList<>();

        Cursor cursor=dbm.GettingDataBack();
        if (cursor.getCount()!=0)
        {
            while (cursor.moveToNext())
            {
                id=cursor.getInt(0);
                year=cursor.getString(1);
                month=cursor.getString(2);
                day=cursor.getString(3);
                expense=cursor.getString(4);
                amount=cursor.getString(5);

                int days=Integer.parseInt(day);
                int amounts=Integer.parseInt(amount);
                ExpenseReport.add(new BarEntry(days,amounts));

                BuildingArrayList1();
            }
        }
    }

    private void BuildingArrayList1()
    {
        int days=Integer.parseInt(day);
        int amounts=Integer.parseInt(amount);
        ExpenseReport.add(new BarEntry(days,amounts));
    }

    private void BarProperties()
    {
        barChart.setDrawBarShadow(false);
        barChart.setDrawValueAboveBar(true);
        barChart.setMaxVisibleValueCount(50);
        barChart.setPinchZoom(false);
        barChart.setDrawGridBackground(true);
    } */

    private void TypeCasting()
    {
        barChart=(BarChart)findViewById(R.id.BarChart);
        btnback=(Button)findViewById(R.id.btnbackToR);
    }

    public void StepBack(View view)
    {
        switch (view.getId())
        {
            case R.id.btnbackToR:
                startActivity ( new Intent(BarChartActivity.this,AllReportsMainActivity.class) );
                break;

            case R.id.btnbackToMain:
                startActivity(new Intent(BarChartActivity.this,MainActivity.class));
                break;



        }






    }
}