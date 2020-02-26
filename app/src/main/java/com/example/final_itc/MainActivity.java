package com.example.final_itc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;




import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.core.Query;

import java.lang.reflect.Member;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nullable;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
String[] country ={"Select Block","Krishna","Godavari"};
Button save,fetch;
String fd,block,d,y,m,c;
EditText u,flatno;
int year,day,month;
TextView td;
    Spinner spin;

private TextView mDisplayDate;
private DatePickerDialog.OnDateSetListener mDateSetListener;

DocumentReference db = FirebaseFirestore.getInstance().collection("ITC").document("Residents");
DocumentReference user = db.collection("park").document("Krishna").collection("52").document("2020").collection("1").document("22");
//private CollectionReference f = db.collection("ITC");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDisplayDate = (TextView)findViewById(R.id.tvdate);
        save = (Button)findViewById(R.id.button_send);
        u =(EditText)findViewById(R.id.un);
        flatno=(EditText)findViewById(R.id.FT);
        fetch = (Button)findViewById(R.id.get);
        td=(TextView)findViewById(R.id.textDisplay);

//Getting the instance of Spinner and applying OnItemSelectedListener on it
        spin =(Spinner)findViewById(R.id.spinner);
        spin.setOnItemSelectedListener(this);

        //Creating the ArrayAdapter instance having the country list
ArrayAdapter<String> arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,country);
arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//Setting the ArrayAdapter data on the Spinner
spin.setAdapter(arrayAdapter);

        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                year = cal.get(Calendar.YEAR);
                month = cal.get(Calendar.MONTH);
                day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(MainActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });


        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                d= Integer.toString(dayOfMonth);
                c="/";
                m = Integer.toString(month+1);
                y = Integer.toString(year);
                fd = d+c+m+c+y;
                mDisplayDate.setText(fd);
                Log.d("Selected Date","onDateSet:date: " +dayOfMonth +"/"+month +"/"+year);
                Toast.makeText(getApplicationContext(),fd,Toast.LENGTH_LONG).show();
            }
        };

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(block.isEmpty() || fd.isEmpty() || flatno.getText().toString().isEmpty() || u.getText().toString().isEmpty())
                {
                    Toast.makeText(getApplicationContext(),"Enter all fields",Toast.LENGTH_LONG).show();
                    return;
                }
   // Creating an object for data class             
                Data city = new Data(block, flatno.getText().toString(), fd, u.getText().toString());
               //db.collection("park").document(block).collection(flatno.getText().toString()).document(y).collection(m).document(d)
                      db.collection("park").document(block).collection(flatno.getText().toString()).document(y)
                              .collection(m).document(d).set(city)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(getApplicationContext(),flatno.getText().toString(),Toast.LENGTH_LONG).show();
                                Toast.makeText(getApplicationContext(),"Data submitted successfully",Toast.LENGTH_LONG).show();
                                u.setText("");
                                flatno.setText("");
                                mDisplayDate.setText("");
                                spin.setSelection(0);
                            }
                        });
            }
        });

//paste fetch on click function


    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(!country[position].equals(country[0])) {
            block = country[position];
            Log.i(block,"block is selected");
            Toast.makeText(getApplicationContext(), block, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    public void next()
    {
        Intent a= new Intent(this,Second.class);
    }

public void movetofetch(View view){
        Toast.makeText(getApplicationContext(),"Clicked on fetch",Toast.LENGTH_SHORT).show();
        Intent a = new Intent(this,Second.class);
            startActivity(a);
}


}