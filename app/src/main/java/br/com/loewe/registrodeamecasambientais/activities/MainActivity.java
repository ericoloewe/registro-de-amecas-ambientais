package br.com.loewe.registrodeamecasambientais.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import br.com.loewe.registrodeamecasambientais.R;
import br.com.loewe.registrodeamecasambientais.activities.threat.ThreatListActivity;

public class MainActivity extends AppCompatActivity {

    private ImageButton btnGetInThreatList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadViewElements();
        bindUiEvents();
    }

    private void loadViewElements() {
        btnGetInThreatList = (ImageButton) findViewById(R.id.btnGetInThreatList);
    }

    private void bindUiEvents() {
        btnGetInThreatList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(getBaseContext(), ThreatListActivity.class);
                startActivity(it);
            }
        });
    }
}
