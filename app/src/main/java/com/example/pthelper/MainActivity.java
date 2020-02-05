package com.example.pthelper;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper myDB;
    EditText username_et;
    EditText age_et;
    EditText gender_et;
    EditText height_et;
    EditText weight_et;
    Button register_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username_et = (EditText)findViewById(R.id.username_et);
        age_et = (EditText)findViewById(R.id.age_et);
        gender_et = (EditText)findViewById(R.id.gender_et);
        height_et = (EditText)findViewById(R.id.height_et);
        weight_et = (EditText)findViewById(R.id.weight_et);
        register_button = (Button)findViewById(R.id.register_button);

        myDB = new DatabaseHelper(this);

        RegisterUser();
    }

    public void RegisterUser() {
        register_button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean result = myDB.insertUser(
                                username_et.getText().toString(),
                                gender_et.getText().toString(),
                                Integer.parseInt(age_et.getText().toString()),
                                Integer.parseInt(height_et.getText().toString()),
                                Integer.parseInt(weight_et.getText().toString())
                        );
                        if(result) {
                            Toast.makeText(MainActivity.this, "DATA INSERTED", Toast.LENGTH_LONG).show();
                            ViewAll();
                        }
                        else {
                            Toast.makeText(MainActivity.this, "DATA NOT INSERTED", Toast.LENGTH_LONG).show();
                        }
                    }
                }
        );
    }

    public void ViewAll() {

        Cursor res = myDB.getAllUserData();
        if(res.getCount() == 0 ) {
            showMessage("Error", "NOthing found");
            return;
        }
        StringBuffer buffer = new StringBuffer();
        while(res.moveToNext()) {
            buffer.append("user_id : " + res.getString(0) + "\n");
            buffer.append("username : " + res.getString(1) + "\n");
            buffer.append("age : " + res.getString(2) + "\n");
            buffer.append("gender : " + res.getString(3) + "\n\n");
        }

        showMessage("data", buffer.toString());
    }

    public void showMessage(String title, String message) {
        Log.d("SQLTEST", message);
    }
}
