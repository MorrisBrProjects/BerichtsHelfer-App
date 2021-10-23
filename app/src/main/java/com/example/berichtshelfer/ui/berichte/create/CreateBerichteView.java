package com.example.berichtshelfer.ui.berichte.create;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.example.berichtshelfer.R;
import com.example.berichtshelfer.loader.Networking;
import com.example.berichtshelfer.loader.objects.Bericht;
import com.example.berichtshelfer.loader.objects.DayBericht;
import com.example.berichtshelfer.loader.objects.JsonConverter;
import com.example.berichtshelfer.loader.objects.Veriables;
import com.example.berichtshelfer.ui.berichte.BerichteFragment;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CreateBerichteView extends Activity {

    public CreateBerichteView() {}
    private Networking networking = new Networking();
    private TextView content;
    private Button send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_createberichte);

        content = (TextView) findViewById(R.id.contentTextView);
        send = (Button) findViewById(R.id.save);

        content.setSingleLine(false); //neu

        send.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @SuppressLint({"NewApi", "LocalSuppress"})
            @Override
            public void onClick(View v) {

                LocalDateTime dateNow = LocalDateTime.now();
                LocalDateTime dateEnding = LocalDateTime.now().plusWeeks(1L);

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

                String formattedDateNow = dateNow.format(formatter);
                String formattedDateEnding = dateEnding.format(formatter);

                Bericht bericht = new Bericht("(" + formattedDateNow + " - " + formattedDateEnding + ")"); //Bericht bericht = new Bericht(name.getText().toString());
                DayBericht dayBericht = new DayBericht("test");
                dayBericht.addTask("task", content.getText().toString());
                bericht.addDay(Veriables.MONDAY, dayBericht);
                System.out.println(content.getText().toString());

                networking.createBericht(JsonConverter.objectToJsonString(bericht)); //MUSS SPÄTER ZU CREATEBERICHT!
                BerichteFragment.instance.refresh();
                finish();
            }
        });
    }




}