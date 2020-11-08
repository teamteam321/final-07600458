package com.akzaki.speedrecords;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.akzaki.speedrecords.db.RecordDatabase;
import com.akzaki.speedrecords.util.AppExecutors;
import com.akzaki.speedrecords.adapter.*;
import com.akzaki.speedrecords.model.*;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    final AppExecutors exe = new AppExecutors();


    private RecyclerView mreCyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView total = (TextView) findViewById(R.id.total_text);
        final TextView overlimit = (TextView)findViewById(R.id.over_text);


        mreCyclerView = findViewById(R.id.record_recycler);
        //mreCyclerView.setAdapter(new RecordAdapter(MainActivity.this,out));
        mreCyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));



        Button add_button = findViewById(R.id.add_record_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,AddRecord.class);
                startActivity(i);
            }
        });


    }
    protected  void onResume() {
        super.onResume();

        exe.diskIO().execute(new Runnable() {
            @Override
            public void run() {
                RecordDatabase db = RecordDatabase.getInstance(MainActivity.this);
                final Record[] out = db.recordDao().getAllRecord();
                int overnum = 0;
                int totalq = 0;
                for (Record f : out) {
                    totalq ++;

                    if(((f.distance/1000) / (f.second/3600)) >= 80.0)
                        overnum++;
                }
                final int totalo = totalq;
                final int overo = overnum;
                final TextView total = (TextView) findViewById(R.id.total_text);
                final TextView overlimit = (TextView)findViewById(R.id.over_text);
                exe.mainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        //on main thread
                        RecordAdapter adapter = new RecordAdapter(MainActivity.this, out);
                        mreCyclerView.setAdapter(adapter);
                        total.setText("Total: "+totalo);
                        overlimit.setText("OVER LIMIT: "+overo);


                    }
                });
            }
        });

    }
}