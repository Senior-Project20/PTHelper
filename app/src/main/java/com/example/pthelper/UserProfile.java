package com.example.pthelper;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class UserProfile extends AppCompatActivity {

    DatabaseHelper myDB;
    TextView username_tv;
    TextView gender_tv;
    TextView age_tv;
    TextView height_tv;
    TextView weight_tv;
    ImageButton edit_button1;
    ImageButton edit_button2;
    ImageButton edit_button3;
    ImageButton edit_button4;
    ImageButton edit_button5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);


        myDB = new DatabaseHelper(this);

        username_tv = (TextView)findViewById(R.id.username_tv);
        gender_tv = (TextView)findViewById(R.id.gender_tv);
        age_tv = (TextView)findViewById(R.id.age_tv);
        height_tv = (TextView)findViewById(R.id.height_tv);
        weight_tv = (TextView)findViewById(R.id.weight_tv);
        edit_button1 = (ImageButton)findViewById(R.id.edit_button1);
        edit_button2 = (ImageButton)findViewById(R.id.edit_button2);
        edit_button3 = (ImageButton)findViewById(R.id.edit_button3);
        edit_button4 = (ImageButton)findViewById(R.id.edit_button4);
        edit_button5 = (ImageButton)findViewById(R.id.edit_button5);

        //Set the text view elements to show user data
        String[] ret = viewUserData();
        if(ret[0] != null)
        username_tv.setText(ret[0]);
        if(ret[1] != null)
        gender_tv.setText(ret[1]);
        if(ret[2] != null)
        age_tv.setText(ret[2]);
        if(ret[3] != null)
        height_tv.setText(ret[3]);
        if(ret[4] != null)
        weight_tv.setText(ret[4]);
    }


    //Query database for user info to display to the user profile page
    public String[] viewUserData() {
        Cursor res = myDB.getUser();
        String username, gender, age, height, weight ;
        String[] ret = new String[5];
        int i = 0;
        if (res.moveToFirst()) {
            username = res.getString(res.getColumnIndex("username"));
            ret[0] = username;
            gender = res.getString(res.getColumnIndex("gender"));
            ret[1] = gender;
            age = res.getString(res.getColumnIndex("age"));
            ret[2] = age;
            height = res.getString(res.getColumnIndex("height"));
            ret[3] = height;
            weight = res.getString(res.getColumnIndex("weight"));
            ret[4] = weight;
        }
        return ret;

    }
}
