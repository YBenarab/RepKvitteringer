package com.example.youssefbenesma.kvitteringer;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    private static final String[] REPARATIONER = new String[]{
            "Skærm", "Batteri", "Bagcover", "Opladningsport", "Tastatur", "Vandskade", "Software opdatering", "HDMI port"
    };

    FloatingActionButton reset;
    Button btn_mainActivity;
    Typeface myfont;
    TextView virksomhedsnavn, afhentning;
    EditText fornavn, kundetlf, produkt, pris, pris2, pris3, imei;
    AutoCompleteTextView rep, rep2, rep3;
    android.support.v7.widget.Toolbar toolbar;
    ImageView btnAfhentning;
    int day, month, year, hour, minute;
    int dayFinal, monthFinal, yearFinal, hourFinal, minuteFinal;

    Calendar c = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_mainActivity = findViewById(R.id.btn_activityMain);
        fornavn = findViewById(R.id.et_fornavn);
        kundetlf = findViewById(R.id.et_kundetlf);
        produkt = findViewById(R.id.et_produkt);
        imei = findViewById(R.id.et_imei);
        rep = findViewById(R.id.et_repair);
        rep2 = findViewById(R.id.et_repair2);
        rep3 = findViewById(R.id.et_repair3);
        pris = findViewById(R.id.et_pris);
        pris2 = findViewById(R.id.et_pris2);
        pris3 = findViewById(R.id.et_pris3);
        virksomhedsnavn = findViewById(R.id.tv_virksomhedsnavn);
        btnAfhentning = findViewById(R.id.btnAfhentning);
        afhentning = findViewById(R.id.tv_afhentning);
        reset = findViewById(R.id.btn_reset);

        setSupportActionBar(toolbar);

        myfont = Typeface.createFromAsset(this.getAssets(), "fonts/TheBewlay.otf");
        virksomhedsnavn.setTypeface(myfont);

        btn_mainActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ReceiptActivity.class);
                String kunde_fornavn = fornavn.getText().toString();
                String kunde_tlf = kundetlf.getText().toString();
                String kunde_produkt = produkt.getText().toString();
                String kunde_imei = imei.getText().toString();
                String kunde_rep = rep.getText().toString();
                String kunde_rep2 = rep2.getText().toString();
                String kunde_rep3 = rep3.getText().toString();
                String kunde_pris = pris.getText().toString();
                String kunde_pris2 = pris2.getText().toString();
                String kunde_pris3 = pris3.getText().toString();
                String kunde_afhentning = afhentning.getText().toString();
                Bundle bundle = new Bundle();
                bundle.putString("kundefornavn", kunde_fornavn);
                bundle.putString("kundetlf", kunde_tlf);
                bundle.putString("kundeprodukt", kunde_produkt);
                bundle.putString("kundeimei", kunde_imei);
                bundle.putString("kundejob", kunde_rep);
                bundle.putString("kundejob2", kunde_rep2);
                bundle.putString("kundejob3", kunde_rep3);
                bundle.putString("kundepris", kunde_pris);
                bundle.putString("kundepris2", kunde_pris2);
                bundle.putString("kundepris3", kunde_pris3);
                bundle.putString("kundeafhentning", kunde_afhentning);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        ArrayAdapter<String> adapter_reparationer = new ArrayAdapter<String>(this,
                R.layout.custom_autocompletetextview, R.id.text_view_list_item, REPARATIONER);
        rep.setAdapter(adapter_reparationer);
        rep2.setAdapter(adapter_reparationer);
        rep3.setAdapter(adapter_reparationer);

        afhentning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                day = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this, R.style.datePickerTheme, MainActivity.this,year,month,day);
                datePickerDialog.show();
            }
        });

        btnAfhentning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                day = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this, R.style.datePickerTheme, MainActivity.this,year,month,day);
                datePickerDialog.show();

            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fornavn.setText("");
                kundetlf.setText("");
                produkt.setText("");
                imei.setText("");
                afhentning.setText("");
                pris.setText("");
                pris2.setText("");
                pris3.setText("");
                rep.setText("");
                rep2.setText("");
                rep3.setText("");
                fornavn.requestFocus();
            }
        });

    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        yearFinal = year;
        monthFinal = month + 1;
        dayFinal = dayOfMonth;

        hour = c.get(Calendar.HOUR_OF_DAY);
        minute = c.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(MainActivity.this, R.style.timePickerTheme, MainActivity.this,
                hour, minute, true);
        timePickerDialog.show();
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        hourFinal = hourOfDay;
        minuteFinal = minute;

        String monthString = String.valueOf(monthFinal);
        String minuteString = String.valueOf(minuteFinal);
        String hourString = String.valueOf(hourFinal);

        if (monthString.equals("1")){
            monthString = "JAN.";
        } else if (monthString.equals("2")){
            monthString = "FEB.";
        } else if (monthString.equals("3")){
            monthString = "MAR.";
        } else if (monthString.equals("4")){
            monthString = "APR.";
        } else if (monthString.equals("5")){
            monthString = "MAJ";
        } else if (monthString.equals("6")){
            monthString = "JUN.";
        } else if (monthString.equals("7")){
            monthString = "JUL.";
        } else if (monthString.equals("8")){
            monthString = "AUG.";
        } else if (monthString.equals("9")){
            monthString = "SEP.";
        } else if (monthString.equals("10")){
            monthString = "OKT.";
        } else if (monthString.equals("11")){
            monthString = "NOV.";
        } else if (monthString.equals("12")){
            monthString = "DEC.";
        }

        if (minuteFinal < 10){
            minuteString = "0" + minuteString;
        } else {
            minuteString = String.valueOf(minuteFinal);
        }

        if (hourFinal < 10){
            hourString = "0" + hourString;
        } else {
            hourString = String.valueOf(hourFinal);
        }

        afhentning.setText("D. " + dayFinal + ". " + monthString + " " + yearFinal + ", KL. " + hourString + ":" + minuteString);
    }

    public void onBackPressed(){

        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);

    }
}
