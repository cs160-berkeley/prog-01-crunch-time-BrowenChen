package com.example.owenchen.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {


    private static RadioGroup radioG_metrics;
    private static RadioButton radio_metric;

    private static RadioGroup radioG_activities;
    private static RadioButton radio_activity;

    private static Map<String, Double> calorieDictionary = new HashMap<String, Double>(){{
        put("Pushups", 350.0);
        put("Situps", 200.0);
        put("JumpingJacks", 10.0);
        put("Jogging", 12.0);
    }};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        onClickListenerButton();
        //ActionBar
        ActionBar actionBar = getSupportActionBar();


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }


    public void onClickListenerButton(){
        radioG_metrics = (RadioGroup)findViewById(R.id.radioGroupMetric);
        radioG_activities = (RadioGroup)findViewById(R.id.radioGroupActivities);
        Button button_update = (Button)findViewById(R.id.button2);

        button_update.setOnClickListener(
          new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  int selected_id = radioG_metrics.getCheckedRadioButtonId();
                  radio_metric = (RadioButton)findViewById(selected_id);

                  int second_selected_id = radioG_activities.getCheckedRadioButtonId();
                  radio_activity = (RadioButton)findViewById(second_selected_id);

                  EditText txtInput = (EditText) findViewById(R.id.txtInput);
                  TextView lblOutput = (TextView) findViewById(R.id.lblOutput);
                  TextView lblOut2 = (TextView) findViewById(R.id.lblOut2);


                  Double txtInputInt = Double.parseDouble(txtInput.getText().toString());

                  String metricType = radio_metric.getText().toString();
                  String activityType = radio_activity.getText().toString();

                  Double resultCalories;

                  if (metricType.equals("Reps")){
                      if (activityType.equals("Pushups") || activityType.equals("Situps") ) {
                          resultCalories = (txtInputInt / calorieDictionary.get(activityType)) * 100.0;
                          lblOutput.setText("Burned " + resultCalories.intValue() + " calories with " + activityType + " for " + txtInput.getText().toString() + " " + metricType);

                      }
                      else {
                          System.out.println("This is not a valid combination");
                          resultCalories = 0.0;
                          lblOutput.setText("You have not selected a valid combination.");
                      }
                  }

                  else if (metricType.equals("Minutes")){
                      if (activityType.equals("JumpingJacks") || activityType.equals("Jogging") ){
                          resultCalories = (txtInputInt / calorieDictionary.get(activityType)) * 100.0;
                          lblOutput.setText("Burned " + resultCalories.intValue() + " calories with " + activityType + " for " + txtInput.getText().toString() + " " + metricType );
                      }
                      else {
                          System.out.println("This is not a valid combination");
                          resultCalories = 0.0;
                          lblOutput.setText("You have not selected a valid combination.");
                      }
                  }

                  else {
                      resultCalories = 0.0;
                  }

                  Double pushEquiv = resultCalories / 100.0 * calorieDictionary.get("Pushups");
                  Double sitEquiv = resultCalories / 100.0 * calorieDictionary.get("Situps");
                  Double jumpEquiv = resultCalories / 100.0 * calorieDictionary.get("JumpingJacks");
                  Double jogEquiv = resultCalories / 100.0 * calorieDictionary.get("Jogging");
                  lblOut2.setText("Equalivent exercises: " + pushEquiv.intValue() + " reps of pushups " + sitEquiv.intValue() + " reps of situps " + jumpEquiv.intValue() + " minutes of jumping jacks and " + jogEquiv.intValue() + " minutes of jogging.");


              }
          }
        );
    };


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }


        if (id == R.id.action_about) {
            Intent intent = new Intent(this, AboutActivity.class);
            intent.putExtra("Message", "Hey wassp");
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void btnConvertCalories(View v){
        EditText txtInput = (EditText) findViewById(R.id.txtInput);
        TextView lblOutput = (TextView) findViewById(R.id.lblOutput);

        int txtInputInt = Integer.parseInt(txtInput.getText().toString());
        lblOutput.setText("Calories Burned - " + txtInput.getText().toString());
    }


}
