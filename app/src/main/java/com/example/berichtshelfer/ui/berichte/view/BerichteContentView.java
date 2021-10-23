package com.example.berichtshelfer.ui.berichte.view;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.berichtshelfer.R;
import com.example.berichtshelfer.loader.Networking;
import com.example.berichtshelfer.loader.objects.Bericht;
import com.example.berichtshelfer.loader.objects.JsonConverter;
import com.example.berichtshelfer.loader.objects.Veriables;

public class BerichteContentView extends Activity {

    private Bericht bericht;
    private Networking networking = new Networking();
    private TextView content;
    private TextView title;



    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_berichtecontent);

        String queryParamBerichtName = getIntent().getExtras().getString("name");
        String queryJsonBericht = networking.getBericht(queryParamBerichtName);

        bericht = (Bericht) JsonConverter.jsonStringToObject(queryJsonBericht, Bericht.class);

        content = (TextView) findViewById(R.id.contentTextView);
        title = (TextView) findViewById(R.id.titleText);

        content.setText(content.getText() + " " + bericht.getDayByID(Veriables.MONDAY).getTaskContent("task"));
        title.setText(title.getText() + " " + bericht.getTitle());


    }




}