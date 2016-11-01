package br.com.loewe.registrodeamecasambientais.activities.threat;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ListViewCompat;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import br.com.loewe.registrodeamecasambientais.R;
import br.com.loewe.registrodeamecasambientais.adapters.ThreatListAdapter;

public class ThreatListActivity extends AppCompatActivity {

    private ThreatListAdapter threatListAdapter;
    private ListView threatList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_threat);

        loadViewElements();

        updateAdapter();
        bindUiEvents();
    }

    private void loadViewElements() {
        threatList = (ListView) findViewById(R.id.threatList);
    }

    private void bindUiEvents() {
    }

    private void updateAdapter() {
        threatListAdapter = new ThreatListAdapter(this);
        threatList.setAdapter(threatListAdapter);
    }
}
