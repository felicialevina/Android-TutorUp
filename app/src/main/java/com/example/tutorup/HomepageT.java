package com.example.tutorup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;


public class HomepageT extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage_t);

        TextView info = findViewById(R.id.txtInfo2);
        TextView info2 = findViewById(R.id.txtInfo3);
        TextView lbl = findViewById(R.id.lblInfo);
        TextView name = findViewById(R.id.txtTutName2);
        Button button = (Button) findViewById(R.id.btnDeactivate);

        final Intent intent = getIntent();
        String tutFee = intent.getStringExtra("fee");
        String tutName = intent.getStringExtra("name");
        final String tutEmail = intent.getStringExtra("email");
        String tutDegree = intent.getStringExtra("degree");
        String tutRating = intent.getStringExtra("rating");

        name.setText(tutName);
        lbl.setText("Email\n\n\n" + "Degree\n\n\n" + "Fee\n\n\n" + "Rating\n\n\n");
        String result = tutEmail + "\n\n\n\n" + tutDegree;
        String result2 = tutFee + "\n\n\n\n" + tutRating;
        info.setText(result);
        info2.setText(result2);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            AlertDialog.Builder dialog = new AlertDialog.Builder(HomepageT.this);
            dialog.setTitle("Are you sure you want to delete your account?");
            dialog.setIcon(R.drawable.ic_baseline_delete_forever_24);
            dialog.setCancelable(false);

            dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    db.collection("tutors")
                            .get()
                            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                    if (task.isSuccessful()) {
                                        for (QueryDocumentSnapshot document : task.getResult()) {
                                            String tEmail = document.getData().get("email").toString();

                                            if(tEmail.equals(tutEmail)) {
                                                db.collection("tutors")
                                                        .document(document.getId())
                                                        .delete();
                                                break;
                                            }
                                        }
                                        startActivity(new Intent(HomepageT.this, MainActivity.class));}
                                    else {
                                        Log.w("HomepageT", "Error getting the document", task.getException());
                                    }
                                }
                            });
                }
            });
            dialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });

            AlertDialog alertDialog = dialog.create();
            alertDialog.show();
            }
        });
    }
}