package br.com.loewe.registrodeamecasambientais.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import br.com.loewe.registrodeamecasambientais.R;
import br.com.loewe.registrodeamecasambientais.activities.threat.AddThreadActivity;
import br.com.loewe.registrodeamecasambientais.activities.threat.ThreatListActivity;

public class MainActivity extends AppCompatActivity {

    private ImageButton btnGetInThreatList;
    private ImageButton btnGetInNewThreat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadViewElements();
        bindUiEvents();
    }

    private void loadViewElements() {
        btnGetInThreatList = (ImageButton) findViewById(R.id.btnGetInThreatList);
        btnGetInNewThreat = (ImageButton) findViewById(R.id.btnGetInNewThreat);
    }

    private void bindUiEvents() {
        btnGetInThreatList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(getBaseContext(), ThreatListActivity.class);
                startActivity(it);
            }
        });

        btnGetInNewThreat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(getBaseContext(), AddThreadActivity.class);
                startActivity(it);
            }
        });
    }
}
