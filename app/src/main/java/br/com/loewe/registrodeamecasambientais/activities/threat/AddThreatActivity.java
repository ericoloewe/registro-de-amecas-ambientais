package br.com.loewe.registrodeamecasambientais.activities.threat;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import br.com.loewe.registrodeamecasambientais.IO.ImageIO;
import br.com.loewe.registrodeamecasambientais.R;
import br.com.loewe.registrodeamecasambientais.model.Threat;
import br.com.loewe.registrodeamecasambientais.repository.ThreatRepository;

public class AddThreatActivity extends AppCompatActivity {

    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private ThreatRepository threatRepository;
    private Button btnSubmit;
    private Button btnAddPhoto;
    private EditText inputDescription;
    private EditText inputAddress;
    private EditText inputDistrict;
    private EditText inputPotential;
    private Bitmap threatImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_thread);

        threatRepository = new ThreatRepository(this);

        loadViewElements();
        bindUiEvents();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            threatImage = (Bitmap) extras.get("data");
        }
    }

    private void loadViewElements() {
        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        btnAddPhoto = (Button) findViewById(R.id.btnAddPhoto);
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
        btnAddPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                self.openCamera();
            }
        });
    }

    private void openCamera() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    private void saveThreat() {
        Threat threat = new Threat();

        threat.setDescription(inputDescription.getText().toString());
        threat.setAddress(inputAddress.getText().toString());
        threat.setDistrict(inputDistrict.getText().toString());
        threat.setPotential(Integer.valueOf(inputPotential.getText().toString()));

        saveThreatImage(threat);
        threatRepository.insert(threat);
        finish();
    }

    private void saveThreatImage(Threat threat) {
        threat.setImage(String.format("THREAT_%s_%s.jpg", threat.getDescription(), new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date())));
        new ImageIO(this)
                .setDirectoryName("THREAT")
                .setFileName(threat.getImage())
                .save(threatImage);
    }
}
