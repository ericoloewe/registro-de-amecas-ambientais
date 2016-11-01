package br.com.loewe.registrodeamecasambientais.activities.threat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import br.com.loewe.registrodeamecasambientais.R;
import br.com.loewe.registrodeamecasambientais.model.Threat;
import br.com.loewe.registrodeamecasambientais.repository.ThreatRepository;

public class UpdateThreatActivity extends AppCompatActivity {

    private ThreatRepository threatRepository;
    private Threat actualThreat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_threat);
        Bundle bundle = getIntent().getExtras();

        threatRepository = new ThreatRepository(this);
        Integer threatId = bundle.getInt("threatId");
        actualThreat = threatRepository.find(threatId);
    }
}
