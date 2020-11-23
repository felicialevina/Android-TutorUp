package com.example.tutorup;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class Payment extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        final EditText hours = findViewById(R.id.txtHours);
        final TextView result = findViewById(R.id.txtResult);

        Intent intent = getIntent();
        final Double fee = intent.getDoubleExtra("fee",0.0);
        final String date = intent.getStringExtra("date");

        Button save = findViewById(R.id.btnSave);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String check = hours.getText().toString();
                if(check.equals("") || check.equals("0")){
                    toastMessageS("Insert valid number of hour(s)");
                    result.setText("Your session is registered on:\n\n\n Your total fee is:\n");
                }
                else{
                    double noHours = Double.parseDouble(check);
                    if(noHours > 3){
                        toastMessageS("Maximum number of hours is 3");
                    }
                    else{
                        final double total = fee * noHours;
                        result.setText("Your session is registered on:\n"+date+"\n\nYour total fee is: \n$"+total);
                        toastMessageL("Email tutor for their payment preference");
                    }
                }
            }
        });
    }
    private void toastMessageS(String message)
    {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
    private void toastMessageL(String message)
    {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}