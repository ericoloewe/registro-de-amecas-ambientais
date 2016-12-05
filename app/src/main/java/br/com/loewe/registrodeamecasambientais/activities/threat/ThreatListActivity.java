package br.com.loewe.registrodeamecasambientais.activities.threat;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
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

        threatListAdapter = new ThreatListAdapter(this);
        threatList.setAdapter(threatListAdapter);
        bindUiEvents();
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateAdapterList();
    }

    private void loadViewElements() {
        threatList = (ListView) findViewById(R.id.threatList);
    }

    private void bindUiEvents() {
        final ThreatListActivity self = this;
        threatList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("ID", "" + id);
                Bundle b = new Bundle();
                b.putLong("threatId", id);
                self.goToActivity(UpdateThreatActivity.class, b);
            }
        });

        threatList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("ID", "" + id);
                self.showDeleteMessage(id);
                return true;
            }
        });
    }

    private void showDeleteMessage(final Long id) {
        final ThreatListActivity self = this;

        new AlertDialog.Builder(this)
                .setTitle("Deletar ameaça")
                .setMessage("Você tem certeza que deseja deletar este item?")
                .setNegativeButton("Não", null)
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        self.threatListAdapter.getThreatRepository().delete(id.intValue());
                        self.updateAdapterList();
                    }
                })
                .show();
    }

    private void updateAdapterList() {
        threatListAdapter.updateThreatList();
    }

    private void goToActivity(Class activityClass) {
        goToActivity(activityClass, new Bundle());
    }

    private void goToActivity(Class activityClass, Bundle bundle) {
        Intent it = new Intent(getBaseContext(), activityClass);
        it.putExtras(bundle);
        startActivity(it);
    }
}
