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


public class Login extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    String uEmail;
    String uPass;

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

                if(uEmail.equals("") || uPass.equals("")){
                    toastMessage("Fill in all fields");
                }
                if(radTU.isChecked()) {
                    db.collection("tutors")
                            .get()
                            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                    if (task.isSuccessful()) {
                                        for (QueryDocumentSnapshot document : task.getResult()) {
                                            String tEmail = document.getData().get("email").toString();
                                            String tPass = document.getData().get("password").toString();

                                            if (tEmail.equals(uEmail)) {
                                                if(!tPass.equals(uPass)){
                                                    toastMessage("Incorrect password");
                                                    pass.setText("");
                                                }
                                                else {
                                                    //String tBalance = document.getData().get("balance").toString();
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
                                                    //intent.putExtra("balance", tBalance);
                                                    //Login.this.startActivity(intent);
                                                    startActivity(intent);
                                                    break;
                                                }
                                            }
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
                                if (task.isSuccessful()) {
                                    for (QueryDocumentSnapshot document : task.getResult()) {
                                        String sEmail = document.getData().get("email").toString();
                                        String sPass = document.getData().get("password").toString();

                                        if(sEmail.equals(uEmail)){
                                            if(!sPass.equals(uPass)){
                                                toastMessage("Incorrect password");
                                                pass.setText("");
                                            }
                                            else {
                                                startActivity(new Intent(Login.this, Homepage.class));
                                                break;
                                            }
                                        }
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

}