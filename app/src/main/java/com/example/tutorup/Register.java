package com.example.tutorup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
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

import java.math.BigInteger;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Register extends AppCompatActivity {
    String tName;
    String tEmail;
    String feeCheck;
    double tFee;
    String tPass, tPass2;
    String tConf;
    String tCourse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        final FirebaseFirestore db = FirebaseFirestore.getInstance();
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
                tPass2 = md5(tPass);
                tConf = conf.getText().toString();

                if(!tPass.equals(tConf)){
                    toastMessage("Password does not match");
                }
                else{
                    tName = name.getText().toString();
                    tEmail = email.getText().toString();
                    feeCheck = fee.getText().toString();
                    boolean eChecker = isValidEmail(tEmail);
                    boolean pChecker = isValidPassword(tPass);

                    if(tName.equals("") || tEmail.equals("") || feeCheck.equals("")){
                        toastMessage("Fill in all fields"); }
                    else if(eChecker == false){
                        toastMessage("Invalid email address");}
                    else if(pChecker == false || tPass.length() < 8){
                        toastMessageL("Password needs to contain at least 8 characters including 1 letter and 1 number");}
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
                        tutor.put("password", tPass2);
                        if(radBA.isChecked()){tutor.put("degree", radBA.getText()); }
                        else if(radMA.isChecked()){tutor.put("degree", radMA.getText()); }
                        else{tutor.put("degree", radPH.getText()); }
                        tutor.put("course", tCourse);
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
    private void toastMessageL(String message)
    {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }
    public boolean isValidPassword(final String password) {
        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=\\S+$).{4,}$";
        Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
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