package com.example.tutorup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class SignUp extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    String sName;
    String sEmail;
    String sPass;
    String sConf;
    String sEdu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        final EditText name = findViewById(R.id.txtName);
        final EditText email = findViewById(R.id.txtEmail2);
        final EditText pass = findViewById(R.id.txtPass2);
        final EditText conf = findViewById(R.id.txtConfPass);
        final RadioButton radHS = findViewById(R.id.radHS);
        final RadioButton radPS = findViewById(R.id.radPS);
        Button button = (Button) findViewById(R.id.btnSignup2);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            sPass = pass.getText().toString();
            sConf = conf.getText().toString();

            if(!sPass.equals(sConf)){
                toastMessage("Password does not match");
            }
            else{
                sName = name.getText().toString();
                sEmail = email.getText().toString();

                if(sName.equals("") || sEmail.equals("")){
                    toastMessage("Fill in all fields"); }
                else if(!radHS.isChecked() && !radPS.isChecked()){
                    toastMessage("Choose an education level");
                }
                else{
                    //FIRESTORE
                    Map<String, Object> student = new HashMap<>();
                    student.put("Name", sName);
                    student.put("Email", sEmail);
                    student.put("Password", sPass);
                    if(radHS.isChecked()){
                        student.put("Education", radHS.getText());
                    }
                    else{
                        student.put("Education", radPS.getText());
                    }
                    student.put("Balance", 100);

                    db.collection("students")
                            .add(student)
                            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                @Override
                                public void onSuccess(DocumentReference documentReference) {
                                    toastMessage("Sign Up Successful");
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    toastMessage("Sign Up Error, Try Again");
                                }
                            });

                    startActivity(new Intent(SignUp.this, MainActivity.class));}}
                }
        });}

    private void toastMessage(String message)
    {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}