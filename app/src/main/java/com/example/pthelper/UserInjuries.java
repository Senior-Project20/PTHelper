package com.example.pthelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class UserInjuries extends Fragment {

    DatabaseHelper myDB;
    LinearLayout injuryList;
    TextView site;
    TextView severity;
    TextView timeline;
    TextView tracker;
    FloatingActionButton addInjury;
    int injuryCount;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.fragment_user_injuries);


        injuryCount = 0;


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_user_injuries, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        addInjury = getView().findViewById(R.id.floatingActionButton);
        injuryList = getView().findViewById(R.id.injuryList);
        addInjury();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        myDB = new DatabaseHelper(getActivity());
    }

    public void addInjury() {
        addInjury.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {

                //make a new injury object and get the text views of the children
                final LinearLayout newInjury = (LinearLayout) getLayoutInflater().inflate(R.layout.injury, injuryList, false);
                LinearLayout text = (LinearLayout) newInjury.getChildAt(1);
                site = (TextView) text.getChildAt(0);
                severity = (TextView) text.getChildAt(1);
                timeline = (TextView) text.getChildAt(2);
                tracker = (TextView) text.getChildAt(3);

                //create the add injury pop up
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                final LinearLayout dialogInputs = (LinearLayout) getLayoutInflater().inflate(R.layout.injury_dialog, null, false);
                builder.setTitle("Add an Injury");
                builder.setMessage("Enter injury information");
                builder.setView(dialogInputs);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EditText site_input = (EditText) dialogInputs.getChildAt(0);
                        EditText severity_input = (EditText) dialogInputs.getChildAt(1);
                        EditText timeline_input = (EditText) dialogInputs.getChildAt(2);
                        EditText tracker_input = (EditText) dialogInputs.getChildAt(3);
                        site.setText(site_input.getText());
                        severity.setText(severity_input.getText());
                        timeline.setText(timeline_input.getText());
                        tracker.setText(tracker_input.getText());
                        injuryList.addView(newInjury);
                        addInjuryToDB();
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();
            }
        });
    }

    public void addInjuryToDB() {
        boolean result = myDB.insertInjury(
                site.getText().toString(),
                severity.getText().toString(),
                timeline.getText().toString(),
                tracker.getText().toString()
        );
        if(result) {
            Toast.makeText(getActivity(), "DATA INSERTED", Toast.LENGTH_LONG).show();
            ViewAllInjuries();
        }
        else {
            Toast.makeText(getActivity(), "DATA NOT INSERTED", Toast.LENGTH_LONG).show();
        }
    }

    public void ViewAllInjuries() {

        Cursor res = myDB.getAllInjuryData();

        if(res.getCount() == 0 ) {
            showMessage("Error", "Nothing found");
            return;
        }

        StringBuffer buffer = new StringBuffer();

        while(res.moveToNext()) {
            buffer.append("injury_id : " + res.getString(0) + "\n");
            buffer.append("site : " + res.getString(1) + "\n");
            buffer.append("severity : " + res.getString(2) + "\n");
            buffer.append("timeline : " + res.getString(3) + "\n");
            buffer.append("tracker : " + res.getString(4) + "\n\n");
        }

        showMessage("data", buffer.toString());
    }

    public void showMessage(String title, String message) {
        Log.d("SQLTEST", message);
    }
}
