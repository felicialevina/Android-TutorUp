package com.example.tutorup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    String tName;
    String tEmail;
    String feeCheck;
    double tFee;
    String tPass;
    String tConf;
    String tCourse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        final EditText name = findViewById(R.id.txtName2);
        final EditText email = findViewById(R.id.txtEmail3);
        final EditText fee = findViewById(R.id.txtFee);
        final EditText pass = findViewById(R.id.txtPass3);
        final EditText conf = findViewById(R.id.txtConfPass2);
        final Spinner coursesGroup = findViewById(R.id.spnGroup);
        final RadioButton radBA = findViewById(R.id.radBA);
        final RadioButton radMA = findViewById(R.id.radMA);
        final RadioButton radPH = findViewById(R.id.radPH);
        Button button = (Button) findViewById(R.id.btnRegister2);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tPass = pass.getText().toString();
                tConf = conf.getText().toString();

                if(!tPass.equals(tConf)){
                    toastMessage("Password does not match");
                }
                else{
                    tName = name.getText().toString();
                    tEmail = email.getText().toString();
                    feeCheck = fee.getText().toString();

                    if(tName.equals("") || tEmail.equals("") || feeCheck.equals("")){
                        toastMessage("Fill in all fields"); }
                    else if(!radBA.isChecked() && !radMA.isChecked() && !radPH.isChecked()){
                        toastMessage("Choose a degree level");
                    }
                    else{
                        tFee = Double.parseDouble(feeCheck);
                        tCourse = coursesGroup.getSelectedItem().toString();

                        //FIRESTORE
                        Map<String, Object> tutor = new HashMap<>();
                        tutor.put("name", tName);
                        tutor.put("email", tEmail);
                        tutor.put("fee", tFee);
                        tutor.put("password", tPass);
                        if(radBA.isChecked()){tutor.put("degree", radBA.getText()); }
                        else if(radMA.isChecked()){tutor.put("degree", radMA.getText()); }
                        else{tutor.put("degree", radPH.getText()); }
                        tutor.put("course", tCourse);
                        tutor.put("balance", 0);
                        tutor.put("rating", 0);

                        ArrayList<Double> arrayExample = new ArrayList<>();
                        tutor.put("ratingList", arrayExample);

                        db.collection("tutors")
                                .add(tutor)
                                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                    @Override
                                    public void onSuccess(DocumentReference documentReference) {
                                        toastMessage("Registration Successful");
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        toastMessage("Registration Error, Try Again");
                                    }
                                });
                        startActivity(new Intent(Register.this, MainActivity.class));}
                }
    }});}
    private void toastMessage(String message)
    {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}