package br.com.loewe.registrodeamecasambientais.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import br.com.loewe.registrodeamecasambientais.R;
import br.com.loewe.registrodeamecasambientais.activities.threat.ThreatListActivity;
import br.com.loewe.registrodeamecasambientais.model.Threat;
import br.com.loewe.registrodeamecasambientais.repository.ThreatRepository;

/**
 * Created by Érico de Souza Loewe on 31/10/2016.
 */
public class ThreatListAdapter extends BaseAdapter {

    private ThreatRepository threatRepository;
    private List<Threat> threatList;
    private ThreatListActivity context;

    public ThreatListAdapter(ThreatListActivity context) {
        this.context = context;
        threatRepository = new ThreatRepository(context);
        updateThreatList();
    }

    private void updateThreatList() {
        threatList = threatRepository.list();
    }

    public ThreatRepository getThreatRepository() {
        return threatRepository;
    }

    @Override
    public int getCount() {
        return threatRepository.list().size();
    }

    @Override
    public Object getItem(int position) {
        return threatRepository.find(position);
    }

    @Override
    public long getItemId(int position) {
        Threat threat = threatRepository.find(position);
        return threat != null ? threat.getId() : 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        updateThreatList();

        LayoutInflater inflater = context.getLayoutInflater();
        View row = inflater.inflate(R.layout.activity_list_threat_item, parent, false);

        TextView description = (TextView) row.findViewById(R.id.description);
        TextView address = (TextView) row.findViewById(R.id.address);
        TextView district = (TextView) row.findViewById(R.id.district);
        TextView potential = (TextView) row.findViewById(R.id.potential);

        description.setText(threatList.get(position).getDescription());
        address.setText(threatList.get(position).getAddress());
        district.setText(threatList.get(position).getDistrict());
        potential.setText(String.format("%d", threatList.get(position).getPotential()));

        return row;
    }
}
