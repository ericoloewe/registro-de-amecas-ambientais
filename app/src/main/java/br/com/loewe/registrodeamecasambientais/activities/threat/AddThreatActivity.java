package br.com.loewe.registrodeamecasambientais.activities.threat;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.com.loewe.registrodeamecasambientais.R;
import br.com.loewe.registrodeamecasambientais.model.Threat;
import br.com.loewe.registrodeamecasambientais.repository.ThreatRepository;

public class AddThreatActivity extends AppCompatActivity {

    private ThreatRepository threatRepository;
    private Button btnSubmit;
    private EditText inputDescription;
    private EditText inputAddress;
    private EditText inputDistrict;
    private EditText inputPotential;

    public AddThreatActivity() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_thread);

        threatRepository = new ThreatRepository(this);

        loadViewElements();
        bindUiEvents();
    }

    private void loadViewElements() {
        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        inputDescription = (EditText) findViewById(R.id.inputDescription);
        inputAddress = (EditText) findViewById(R.id.inputAddress);
        inputDistrict = (EditText) findViewById(R.id.inputDistrict);
        inputPotential = (EditText) findViewById(R.id.inputPotential);
    }

    private void bindUiEvents() {
        final AddThreatActivity self = this;
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                self.saveThreat();
            }
        });
    }

    private void saveThreat() {
        Threat threat = new Threat();

        threat.setDescription(inputDescription.getText().toString());
        threat.setAddress(inputAddress.getText().toString());
        threat.setDistrict(inputDistrict.getText().toString());
        threat.setPotential(Integer.valueOf(inputPotential.getText().toString()));

        threatRepository.insert(threat);
        finish();
    }
}
