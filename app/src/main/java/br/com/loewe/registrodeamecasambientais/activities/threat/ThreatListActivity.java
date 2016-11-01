package br.com.loewe.registrodeamecasambientais.activities.threat;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ListViewCompat;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import br.com.loewe.registrodeamecasambientais.R;

public class ThreatListActivity extends AppCompatActivity {

    private Button btnUpdate;
    private ListView threatList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_threat);

        loadViewElements();
        bindUiEvents();
    }

    private void loadViewElements() {
        threatList = (ListView) findViewById(R.id.threatList);
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
