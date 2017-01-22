package com.example.testkryo;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.net.URL;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView textView = (TextView) findViewById(R.id.text);
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                KryoTest kryoTest = new KryoTest();
                OtherKryoTest otherKryoTest=new OtherKryoTest();
                otherKryoTest.test001();
                startRecyclerViewApp();
//                kryoTest.test001();
//                kryoTest.testList();
//                textView.setText(kryoTest.testObject());


            }
        });
    }

    private void startRecyclerViewApp() {
        Uri uri=Uri.parse("aa://bb");
        Intent in=new Intent(Intent.ACTION_VIEW,uri);
        startActivity(in);

    }
}
