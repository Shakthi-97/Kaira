package com.example.rentride;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class retreive extends AppCompatActivity {

    EditText txtCardNo, txtExpiry, txtCvv;
    Button btnUpdate,btnDelete;
    DatabaseReference dbRef;
    Payment pay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retreive);
        txtCardNo = findViewById(R.id.EtCN);
        txtExpiry = findViewById(R.id.EtED);
        txtCvv = findViewById(R.id.EtCVV);

        btnUpdate = findViewById(R.id.BtnUpdate);
        btnDelete = findViewById(R.id.BtnDelete);

        pay = new Payment();

        dbRef = FirebaseDatabase.getInstance().getReference().child("Payment").child("Payment1");
        dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChildren()){
                    txtCardNo.setText(dataSnapshot.child("cardNo").getValue().toString());
                    txtExpiry.setText(dataSnapshot.child("expiry").getValue().toString());
                    txtCvv.setText(dataSnapshot.child("cvv").getValue().toString());

                }else {
                    Toast.makeText(getApplicationContext(),"No source to display",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbRef = FirebaseDatabase.getInstance().getReference();

                dbRef.child("Payment").child("Payment1").child("cardNo").setValue(txtCardNo.getText().toString().trim());
                dbRef.child("Payment").child("Payment1").child("cvv").setValue(txtCvv.getText().toString().trim());
                dbRef.child("Payment").child("Payment1").child("expiry").setValue(txtExpiry.getText().toString().trim());

                Toast.makeText(getApplicationContext(),"Payment updated successfully",Toast.LENGTH_SHORT).show();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(retreive.this);
                alert.setTitle("Delete");
                alert.setMessage("Are you sure to delete the Payment Data");
                alert.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dbRef=FirebaseDatabase.getInstance().getReference().child("Payment").child("Payment1");
                        dbRef.removeValue();
                        clearControls();
                        Toast.makeText(getApplicationContext(),"Payment deleted successfully",Toast.LENGTH_SHORT);
                    }
                });

                alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        Toast.makeText(getApplicationContext(),"Continue",Toast.LENGTH_SHORT).show();
                    }
                });
                alert.create();
                alert.show();
            }
        });

    }

    private void clearControls() {
        txtCardNo.setText("");
        txtExpiry.setText("");
        txtCvv.setText("");
    }

}