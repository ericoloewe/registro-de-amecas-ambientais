package br.com.loewe.registrodeamecasambientais.activities.threat;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import br.com.loewe.registrodeamecasambientais.R;

public class AddThreadActivity extends AppCompatActivity {

    Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_thread);

        loadViewElements();
        bindUiEvents();
    }

    private void loadViewElements() {
        btnSubmit = (Button) findViewById(R.id.btnSubmit);
    }

    private void bindUiEvents() {
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
    }
}
