package br.com.loewe.registrodeamecasambientais.activities.threat;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import br.com.loewe.registrodeamecasambientais.R;

public class ThreatListActivity extends ActionBarActivity {

    private Button btnAdd;
    private Button btnUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_threat_list);
    }

    private void loadViewElements() {
        btnAdd = (Button) findViewById(R.id.btnAdd);
    }

    private void bindUiEvents() {
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
    }
}
