package com.example.gettingrecentdateandtime;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity
{
    EditText edtxtdate, edtxtmonth, edtxtexp, edtxtamt, edtxtyear;
    Button btnshow, btnsave, btnReport;

    DBManager db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db=new DBManager(this);

        TypeCasting();

      /*  btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               // getandsave();


                //////////////
            }
        });  */
    }

    private void TypeCasting()
    {
        edtxtdate=findViewById(R.id.edtxtDate);
        edtxtmonth=findViewById(R.id.edtxtMonth);
        edtxtamt=findViewById(R.id.edtxtAmount);
        edtxtyear=findViewById(R.id.edtxtYear);
        edtxtexp=findViewById(R.id.edtxtExp);
        btnshow=findViewById(R.id.btndate);
        btnReport=findViewById(R.id.btnReports);
    }

    public void ShowDate(View view)
    {
        Calendar calendar=Calendar.getInstance();
        SimpleDateFormat simpleDateFormat2=new SimpleDateFormat("YYYY");
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("MMMM");
        SimpleDateFormat simpleDateFormat1=new SimpleDateFormat("dd");

        String dateTime=simpleDateFormat.format(calendar.getTime());
        String dateTime1=simpleDateFormat1.format(calendar.getTime());
        String dateTime2=simpleDateFormat2.format(calendar.getTime());

        edtxtmonth.setText(dateTime);
        edtxtdate.setText(dateTime1);
        edtxtyear.setText(dateTime2);

    }

    public void getandsave(View view)
    {
        String year=edtxtyear.getText().toString();
        String month=edtxtmonth.getText().toString();
        String day=edtxtdate.getText().toString();
        String expense=edtxtexp.getText().toString();
        String amount=edtxtamt.getText().toString();

        ProcessInsert(year, month, day, expense, amount);
    }

    private void ProcessInsert(String year, String month, String day, String expense, String amount)
    {
        db=new DBManager(this);
        String res=db.AddData(year, month, day, expense, amount);
        resetall();
        Toast.makeText(getApplicationContext(),res,Toast.LENGTH_SHORT).show();

    }

    private void resetall()
    {
        edtxtmonth.setText(null);
        edtxtyear.setText(null);
        edtxtdate.setText(null);
        edtxtexp.setText(null);
        edtxtamt.setText(null);
    }


    public void ShowReports(View view)
    {
        Intent intent=new Intent(this,AllReportsMainActivity.class);
        startActivity(intent);
    }
}