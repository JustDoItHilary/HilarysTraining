package com.example.testgreen;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by Hilary on 2016/9/18.
 */
public class MySQLActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView tv=(TextView)findViewById(R.id.edit_input);
//        SQLiteDatabase sqLiteDatabase=SQLiteDatabase.openOrCreateDatabase()
    }
}
