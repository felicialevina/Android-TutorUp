package com.example.tutorup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Payment extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        final EditText hours = findViewById(R.id.txtHours);
        final TextView result = findViewById(R.id.txtResult);

        Intent intent = getIntent();
        final Double fee = intent.getDoubleExtra("fee",0.0);
        final String email = intent.getStringExtra("email");
        final String date = intent.getStringExtra("date");

        Button save = findViewById(R.id.btnSave);
        final Button home = findViewById(R.id.btnHome);
        home.setVisibility(View.INVISIBLE);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Payment.this, Homepage.class));
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String check = hours.getText().toString();
                if(check.equals("") || check.equals("0")){
                    toastMessage("Insert valid number of hour(s)");
                    result.setText("Your session is registered on:\n\n\n Your total fee is:\n");
                }
                else{
                    double noHours = Double.parseDouble(check);
                    if(noHours > 3){
                        toastMessage("Maximum number of hours is 3");
                    }
                    else{
                        final double total = fee * noHours;
                        result.setText("Your session is registered on:\n"+date+"\n\nYour total fee is: \n$"+total+"\n\nEmail tutor for payment info:\n"+email);
                        JavaMailAPI javaMailAPI = new JavaMailAPI(Payment.this, email, "Tutor Up Schedule", "A student has set up a TutorUp session with you on "+ date);
                        javaMailAPI.execute();
                        home.setVisibility(View.VISIBLE);
                    }
                }
            }
        });
    }
    private void toastMessage(String message)
    {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}