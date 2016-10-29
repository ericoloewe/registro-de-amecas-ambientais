package br.com.loewe.registrodeamecasambientais.activities.threat;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import br.com.loewe.registrodeamecasambientais.R;

public class ThreatListActivity extends ActionBarActivity {

    private Button btnUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_threat_list);

        loadViewElements();
        bindUiEvents();
    }

    private void loadViewElements() {
        btnUpdate = (Button) findViewById(R.id.btnUpdate);
    }

    private void bindUiEvents() {
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
    }
}