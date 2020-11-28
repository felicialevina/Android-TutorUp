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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.math.BigInteger;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class Login extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    String uEmail;
    String uPass, uPass2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final EditText email = findViewById(R.id.txtEmail);
        final EditText pass = findViewById(R.id.txtPass);
        Button button = (Button) findViewById(R.id.btnLogin2);
        final RadioButton radST = findViewById(R.id.radST);
        final RadioButton radTU = findViewById(R.id.radTU);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uEmail = email.getText().toString();
                uPass = pass.getText().toString();
                uPass2 = md5(uPass);

                if(uEmail.equals("") || uPass.equals("")){
                    toastMessage("Fill in all fields");
                }
                if(radTU.isChecked()) {
                    db.collection("tutors")
                            .get()
                            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                    int notif = 0;
                                    if (task.isSuccessful()) {
                                        for (QueryDocumentSnapshot document : task.getResult()) {
                                            String tEmail = document.getData().get("email").toString();
                                            String tPass = document.getData().get("password").toString();

                                            if (tEmail.equals(uEmail)) {
                                                if(!tPass.equals(uPass2)){
                                                    notif++;
                                                    toastMessage("Incorrect password");
                                                    pass.setText("");
                                                }
                                                else {
                                                    String tFee = document.getData().get("fee").toString();
                                                    String tDegree = document.getData().get("degree").toString();
                                                    String tName = document.getData().get("name").toString();
                                                    String tRating = document.getData().get("rating").toString();

                                                    Intent intent = new Intent(Login.this, HomepageT.class);
                                                    intent.putExtra("name", tName);
                                                    intent.putExtra("email", tEmail);
                                                    intent.putExtra("degree", tDegree);
                                                    intent.putExtra("fee", tFee);
                                                    intent.putExtra("rating", tRating);
                                                    notif++;
                                                    startActivity(intent);
                                                    break;
                                                }
                                            }
                                        }
                                        if(notif == 0){
                                            toastMessage("Tutor account not found");
                                        }
                                    } else {
                                        Log.w("Login", "Error getting the document", task.getException());
                                    }
                                }
                            });
                }
                else if(radST.isChecked()){
                db.collection("students")
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                int notif = 0;
                                if (task.isSuccessful()) {
                                    for (QueryDocumentSnapshot document : task.getResult()) {
                                        String sEmail = document.getData().get("email").toString();
                                        String sPass = document.getData().get("password").toString();

                                        if(sEmail.equals(uEmail)){
                                            if(!sPass.equals(uPass2)){
                                                notif++;
                                                toastMessage("Incorrect password");
                                                pass.setText("");
                                            }
                                            else {
                                                notif++;
                                                startActivity(new Intent(Login.this, Homepage.class));
                                                break;
                                            }
                                        }
                                    }
                                    if(notif == 0){
                                        toastMessage("Student account not found");
                                    }
                                } else {
                                    Log.w("Login", "Error getting the document", task.getException());
                                }
                            }
                        });}
                else{
                    toastMessage("Select tutor or student");
                }
            }
        });
    }

    private void toastMessage(String message)
    {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public static String md5(String s)
    {
        MessageDigest digest;
        try
        {
            digest = MessageDigest.getInstance("MD5");
            digest.update(s.getBytes(Charset.forName("US-ASCII")),0,s.length());
            byte[] magnitude = digest.digest();
            BigInteger bi = new BigInteger(1, magnitude);
            String hash = String.format("%0" + (magnitude.length << 1) + "x", bi);
            return hash;
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        return "";
    }
}