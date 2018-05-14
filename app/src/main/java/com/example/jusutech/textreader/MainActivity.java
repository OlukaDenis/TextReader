package com.example.jusutech.textreader;

import android.app.Activity;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends Activity {
    TextToSpeech t;
    EditText ed;
    Button b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ed = (EditText) findViewById(R.id.editText);
        b = (Button) findViewById(R.id.button);

        t = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener(){
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR){
                    t.setLanguage(Locale.ENGLISH);
                }
            }
        });

        b.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String toSpeak = ed.getText().toString();
               Toast.makeText(getApplicationContext(), toSpeak, Toast.LENGTH_SHORT).show();
                t.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);

            }
        });
    }
    public void onPause(){
        if(t !=null){
            t.stop();
            t.shutdown();
        }
        super.onPause();
    }
}
