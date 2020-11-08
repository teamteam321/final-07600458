package com.akzaki.speedrecords;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.akzaki.speedrecords.db.RecordDatabase;
import com.akzaki.speedrecords.model.Record;
import com.akzaki.speedrecords.util.AppExecutors;

public class AddRecord extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_record);

        Button save = findViewById(R.id.save_button);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double distance ;
                double duration;
                try{
                    if(String.valueOf(((EditText)findViewById(R.id.enter_distance)).getText()).length() == 0
                            || String.valueOf(((EditText)findViewById(R.id.enter_duration)).getText()).length() == 0){
                        Toast t = Toast.makeText(AddRecord.this,"Please Enter Distance and duration.",1);
                        t.show();
                        return;
                    }

                    distance = Double.parseDouble(String.valueOf(((EditText)findViewById(R.id.enter_distance)).getText()));
                    duration = Double.parseDouble(String.valueOf(((EditText)findViewById(R.id.enter_duration)).getText()));

                    final Record ad = new Record(0,distance,duration);
                    AppExecutors exe = new AppExecutors();
                    exe.diskIO().execute(new Runnable() {
                        @Override
                        public void run() {
                            RecordDatabase db = RecordDatabase.getInstance(AddRecord.this);
                            db.recordDao().addRecord(ad);
                            finish();
                        }
                    });

                }catch(Exception e){

                }






            }
        });

    }
}