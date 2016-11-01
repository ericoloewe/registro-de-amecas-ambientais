package br.com.loewe.registrodeamecasambientais.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
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
        final MainActivity self = this;

        btnGetInThreatList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                self.goToActivity(ThreatListActivity.class);
            }
        });

        btnGetInNewThreat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                self.goToActivity(AddThreadActivity.class);
            }
        });
    }

    private void goToActivity(Class activityClass) {
        Intent it = new Intent(getBaseContext(), activityClass);
        startActivity(it);
    }
}
