package com.example.owenchen.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by owenchen on 2/5/16.
 */
public class AboutActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        Bundle extra = getIntent().getExtras();

        TextView tv = (TextView)findViewById((R.id.textView_activity_about));
        tv.setText(extra.getString("Message"));
    }
}
