package com.example.appy;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    EditText et, et2, et3;
    TextView tv, tv2;
    Button button, button2;
    ImageView iv;
    FirebaseDatabase database = FirebaseDatabase.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        setContentView(R.layout.activity_main);
        et = (EditText) findViewById(R.id.input1);
        et.setBackgroundColor(Color.WHITE);
        et2 = (EditText) findViewById(R.id.input2);
        et2.setBackgroundColor(Color.WHITE);
        et3 = (EditText) findViewById(R.id.input3);
        et3.setBackgroundColor(Color.WHITE);
        tv = (TextView) findViewById(R.id.text1);
        tv.setBackgroundColor(Color.WHITE);
        tv.setTextSize(25);
        tv2 = (TextView) findViewById(R.id.text2);
        tv2.setBackgroundColor(Color.WHITE);
        tv2.setTextSize(25);
        iv = (ImageView) findViewById(R.id.logo);
        button = (Button) findViewById(R.id.button1);
        button.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                    if ((et.getText().toString().equals("")) || (et2.getText().toString().equals("")) || (et3.getText().toString().equals(""))) {
                        Toast.makeText(getApplicationContext(), ("Empty text field"), Toast.LENGTH_LONG).show();
                    } else if ((et.getText().toString() != null) && (et2.getText().toString() != null) && (et3.getText().toString() != null))//check whether the entered text is not null
                    {
                        String ta = et.getText().toString();
                        double a = Double.parseDouble(ta);
                        String tb = et2.getText().toString();
                        double b = Double.parseDouble(tb);
                        String tc = et3.getText().toString();
                        double c = Double.parseDouble(tc);
                        double quad = (b * b) - (4 * a * c);
                        double srt = Math.sqrt(quad);
                        double btm = 2 * a;
                        double pq = ((-b) + (srt)) / (btm);
                        double nq = ((-b) - (srt)) / (btm);
                        String ee = ta + "_" + tb + "_" + tc;
                        if(quad < 0){
                            DatabaseReference myRef = database.getReference("Not Quad");
                            Toast.makeText(getApplicationContext(), ("CANNOT SOLVE: Not Quad"), Toast.LENGTH_LONG).show();
                            myRef.child(ee).child("a").setValue(a);
                            myRef.child(ee).child("b").setValue(b);
                            myRef.child(ee).child("c").setValue(c);
                        } else {
                            //tv.setText("a = " +et.getText().toString()+"  b = " +et2.getText().toString()+ "  c = "+et3.getText().toString() + " q1 = " + pq + " q2 "+ nq);
                            tv.setText("q1 = " + pq);
                            tv2.setText("q2 = " + nq);

                            DatabaseReference myRef = database.getReference("Quad");
                            myRef.child(ee).child("q1").setValue(pq);
                            myRef.child(ee).child("q2").setValue(nq);


                            Toast.makeText(getApplicationContext(), ("SOLVED: q1 = " + pq + " q2 = " + nq), Toast.LENGTH_LONG).show();
                            //Toast.makeText(getApplicationContext(), et2.getText().toString(), Toast.LENGTH_LONG).show();
                            //Toast.makeText(getApplicationContext(), et3.getText().toString(), Toast.LENGTH_LONG).show();//display the text that you entered in edit text
                        }

                }

            }
        });
        button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Cleared", Toast.LENGTH_LONG).show();
                et.setText("");
                et2.setText("");
                et3.setText("");
                tv.setText("");
                tv2.setText("");
            }
        });

    }

}
