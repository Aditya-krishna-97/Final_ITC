package com.example.final_itc;

import android.app.DatePickerDialog;
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

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;

    public class Second extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
        String[] country ={"Select Block","Krishna","Godavari"};
        String[] timeline = {"Select Timeline","Last 7 days","Last month","Last year"};
        Button fetch;
        Data data;
        String fd,block,d,y,m,c;
        EditText u,flatno;
        int year,day,month;
        TextView td;
        Spinner spin,tl;

        private TextView mDisplayDate;
        private DatePickerDialog.OnDateSetListener mDateSetListener;

        DocumentReference db = FirebaseFirestore.getInstance().collection("ITC").document("Residents");

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            Toast.makeText(getApplicationContext(),"In fetch",Toast.LENGTH_SHORT).show();
            mDisplayDate = (TextView)findViewById(R.id.tvdate);
            flatno=(EditText)findViewById(R.id.FT);
            fetch = (Button)findViewById(R.id.get);
            td=(TextView)findViewById(R.id.textDisplay);
             //below is for timeline spinner
            tl = (Spinner)findViewById(R.id.timeline);
            tl.setOnItemSelectedListener(this);
            ArrayAdapter<String> art1 = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,timeline);
            art1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            tl.setAdapter(art1);

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

                    DatePickerDialog dialog = new DatePickerDialog(com.example.final_itc.Second.this,
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


// below is fetch function
            fetch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    db.collection("park").document(block).collection(flatno.getText().toString()).document(y).collection(m).document(d)
                            .get()
                            .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                @Override
                                public void onSuccess(DocumentSnapshot documentSnapshot) {
                                    if (documentSnapshot.exists()) {
                                        String title = documentSnapshot.getString("units");

                                        //Map<String,Object> note = documentSnapshot.getData();
                                        Toast.makeText(getApplicationContext(), "units are " + title, Toast.LENGTH_LONG).show();
                                        // textViewData.setText("Units" +title + "\n" + "Description" +description);

                                    }
                                    else {
                                        Toast.makeText(getApplicationContext(), "Document does not exist", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            });






        }


        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            if(parent.getId() == R.id.spinner) {
                if (!country[position].equals(country[0])) {
                    block = country[position];
                    Log.i(block, "block is selected");
                    Toast.makeText(getApplicationContext(), block, Toast.LENGTH_LONG).show();
                }
            }
        }
        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }