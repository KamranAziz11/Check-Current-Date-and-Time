package com.example.gettingrecentdateandtime;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;

public class AllReportsMainActivity extends AppCompatActivity
{
    Button btngo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_reports_main);


        TypeCasting();
    }

    private void TypeCasting()
    {
        btngo=findViewById(R.id.btnBarChart);
    }


    public void gotoActivity(View view)
    {

       switch (view.getId()){

           case R.id.btnBarChart:
               startActivity(new Intent(this,BarChartActivity.class));
               break;
           case R.id.btnPieChart:
               startActivity(new Intent(this,PieChartActivity.class));








       }




    }
}