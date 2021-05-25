package com.example.gettingrecentdateandtime;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class PieChartActivity extends AppCompatActivity
{
    Button btnback, btnbacktomain;
    PieChart pieChart;

    int id;
    String year, month, day, expense, amount;

    DBManager dbm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie_chart);

        dbm=new DBManager(this);

        TypeCasting();

        // PIE CHART CONFIGURATIONS START FROM HERE

        pieChart.setUsePercentValues(true);
        pieChart.getDescription().setEnabled(false);
        pieChart.setExtraOffsets(5,10,5,5);

        pieChart.setDragDecelerationFrictionCoef(0.99f);
        pieChart.setHoleColor(Color.CYAN);
        pieChart.setTransparentCircleRadius(7);

        //ARRAYLIST CREATION
        ArrayList<PieEntry>DailyExpense=new ArrayList<>();
        Cursor cursor=dbm.GettingDataBack();

        if (cursor.getCount()!=0)
        {
            while (cursor.moveToNext())
            {
                id = cursor.getInt(0);
                year = cursor.getString(1);
                month = cursor.getString(2);
                day = cursor.getString(3);
                expense = cursor.getString(4);
                amount = cursor.getString(5);

                float days = Float.parseFloat(day);
                float amounts = Float.parseFloat(amount);
                DailyExpense.add(new PieEntry(amounts,days));

            }






        }

        //pieChart.animateY(1000,Easing.EasingFunction.);
        PieDataSet dataSet=new PieDataSet(DailyExpense, "Expense on daily basis");
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);

        PieData data=new PieData(dataSet);
        data.setValueTextSize(25f);
        data.setValueTextColor(Color.BLACK);

        pieChart.setData(data);






    }

    private void TypeCasting()
    {
        btnback=findViewById(R.id.btnbackToR);
        btnbacktomain=findViewById(R.id.btnbackToMain);
        pieChart=(PieChart)findViewById(R.id.piechart);
    }


    public void StepBack(View view)
    {
        switch (view.getId())
        {
            case R.id.btnbackToR:
                startActivity ( new Intent(PieChartActivity.this,AllReportsMainActivity.class) );
                break;

            case R.id.btnbackToMain:
                startActivity(new Intent(PieChartActivity.this,MainActivity.class));
                break;



        }






    }
}