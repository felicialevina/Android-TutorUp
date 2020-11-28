package com.example.tutorup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.math.BigInteger;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUp extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    String sName;
    String sEmail;
    String sPass, sPass2;
    String sConf;

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
            sPass2 = md5(sPass);
            sConf = conf.getText().toString();

            if(!sPass.equals(sConf)){
                toastMessage("Password does not match");
            }
            else{
                sName = name.getText().toString();
                sEmail = email.getText().toString();
                boolean eChecker = isValidEmail(sEmail);
                boolean pChecker = isValidPassword(sPass);

                if(sName.equals("") || sEmail.equals("")){
                    toastMessage("Fill in all fields"); }
                else if(!radHS.isChecked() && !radPS.isChecked()){
                    toastMessage("Choose an education level");
                }
                else if(eChecker == false){
                    toastMessage("Invalid email address");}
                else if(pChecker == false || sPass.length() < 8){
                    toastMessageL("Password needs to contain at least 8 characters including 1 letter and 1 number");}
                else{
                    //FIRESTORE
                    Map<String, Object> student = new HashMap<>();
                    student.put("name", sName);
                    student.put("email", sEmail);
                    student.put("password", sPass2);
                    if(radHS.isChecked()){
                        student.put("education", radHS.getText());
                    }
                    else{
                        student.put("education", radPS.getText());
                    }

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

    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }
    public boolean isValidPassword(final String password) {
        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=\\S+$).{4,}$";
        Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
    private void toastMessage(String message)
    {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
    private void toastMessageL(String message)
    {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
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