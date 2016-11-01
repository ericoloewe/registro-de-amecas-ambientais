package br.com.loewe.registrodeamecasambientais.activities.threat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.com.loewe.registrodeamecasambientais.R;
import br.com.loewe.registrodeamecasambientais.model.Threat;
import br.com.loewe.registrodeamecasambientais.repository.ThreatRepository;

public class UpdateThreatActivity extends AppCompatActivity {

    private ThreatRepository threatRepository;
    private Threat actualThreat;
    private Button btnSubmit;
    private EditText inputDescription;
    private EditText inputAddress;
    private EditText inputDistrict;
    private EditText inputPotential;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_threat);
        Bundle bundle = getIntent().getExtras();

        threatRepository = new ThreatRepository(this);
        Integer threatId = bundle.getInt("threatId");
        actualThreat = threatRepository.find(threatId);

        loadViewElements();
        populateViewFields();
        bindUiEvents();
    }

    private void loadViewElements() {
        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        inputDescription = (EditText) findViewById(R.id.inputDescription);
        inputAddress = (EditText) findViewById(R.id.inputAddress);
        inputDistrict = (EditText) findViewById(R.id.inputDistrict);
        inputPotential = (EditText) findViewById(R.id.inputPotential);
    }

    private void populateViewFields() {
        inputDescription.setText(actualThreat.getDescription());
        inputAddress.setText(actualThreat.getAddress());
        inputDistrict.setText(actualThreat.getDistrict());
        inputPotential.setText(String.format("%d", actualThreat.getPotential()));
    }

    private void bindUiEvents() {
        final UpdateThreatActivity self = this;
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                self.updateThreat();
            }
        });
    }

    private void updateThreat() {
        Threat threat = new Threat();

        threat.setId(actualThreat.getId());
        threat.setDescription(inputDescription.getText().toString());
        threat.setAddress(inputAddress.getText().toString());
        threat.setDistrict(inputDistrict.getText().toString());
        threat.setPotential(Integer.valueOf(inputPotential.getText().toString()));

        threatRepository.update(threat);
        finish();
    }
}
