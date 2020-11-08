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
    int size = 0;

    private static final String TAG = "SignUpActivity";
    public static final String NameKey = "Name";
    public static final String EmailKey = "Email";
    public static final String PassKey = "Password";
    public static final String EduKey = "Education";
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
                toastMessage("password does not match");
            }
            else{
                sName = name.getText().toString();
                sEmail = email.getText().toString();

                if(!radHS.isChecked() && !radPS.isChecked()){
                    toastMessage("choose an education level");
                }
                else if(sName.equals("") || sEmail.equals("")){
                    toastMessage("Fill in all fields");
                }
                else{
                    //FIRESTORE
                    Map<String, Object> student = new HashMap<>();
                    student.put(NameKey, sName);
                    student.put(EmailKey, sEmail);
                    student.put(PassKey, sPass);
                    if(radHS.isChecked()){
                        student.put(EduKey, radHS.getText());
                    }
                    else{
                        student.put(EduKey, radPS.getText());
                    }

                    db.collection("students")
                            .add(student)
                            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                @Override
                                public void onSuccess(DocumentReference documentReference) {
                                    toastMessage("Sign Up Successful");

                                   // Log.d(TAG, "DocumentSnapshot added with ID " + documentReference.getId());
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