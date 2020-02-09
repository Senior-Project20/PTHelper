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

        viewUserData();
        //username_tv.setText(viewUserData());
        /*gender_tv.setText(userData[0]);
        age_tv.setText(userData[0]);
        height_tv.setText(userData[0]);
        weight_tv.setText(userData[0]);*/

    }


    public void viewUserData() {

        Cursor res = myDB.getUser();
        if (res.getCount() == 0){
            return ;
        }
        /*String[] data= new String[] {
                res.getString(res.getColumnIndex("username")),
                res.getString(res.getColumnIndex("gender")),
                res.getString(res.getColumnIndex("age")),
                res.getString(res.getColumnIndex("height")),
                res.getString(res.getColumnIndex("weight"))
        };*/
        Log.e("SQL", res.getString(0));
        return ;

    }
}
