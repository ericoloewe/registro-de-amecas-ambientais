package br.com.loewe.registrodeamecasambientais.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import br.com.loewe.registrodeamecasambientais.model.Threat;

/**
 * Created by Érico de Souza Loewe on 29/10/2016.
 */
public class ThreatRepository extends Repository<Threat> {

    private static final String COL_ID = "id";
    private static final String COL_DESCRIPTION = "description";
    private static final String COL_ADDRESS = "address";
    private static final String COL_DISTRICT = "district";
    private static final String COL_POTENTIAL = "potential";

    public ThreatRepository(Context context) {
        super(context, Threat.class);
    }

    public Threat find(Integer id) {
        return convertToThreat(super.findCursor(id));
    }

    public void insert(Threat threat) {
        ContentValues values = new ContentValues();

        values.put(COL_DESCRIPTION, threat.getDescription());
        values.put(COL_ADDRESS, threat.getAddress());
        values.put(COL_DISTRICT, threat.getDistrict());
        values.put(COL_POTENTIAL, threat.getPotential());

        super.insert(values);
    }

    public void update(Threat threat) {
        ContentValues values = new ContentValues();

        values.put(COL_ID, threat.getId());
        values.put(COL_DESCRIPTION, threat.getDescription());
        values.put(COL_ADDRESS, threat.getAddress());
        values.put(COL_DISTRICT, threat.getDistrict());
        values.put(COL_POTENTIAL, threat.getPotential());

        super.update(values);
    }

    public void delete(Integer id) {
        super.deleteById(id);
    }

    public List<Threat> list() {
        List<Threat> threatList = new ArrayList<>();
        Cursor cursor = super.listByCursor();

        cursor.moveToFirst();
        for (int i = 0; i <= cursor.getCount(); i++) {
            cursor.move(i);
            threatList.add(convertToThreat(cursor));
        }

        return threatList;
    }

    private Threat convertToThreat(Cursor cursor) {
        Threat threat = new Threat();

        threat.setId(cursor.getInt(cursor.getColumnIndex(COL_ID)));
        threat.setDescription(cursor.getString(cursor.getColumnIndex(COL_DESCRIPTION)));
        threat.setAddress(cursor.getString(cursor.getColumnIndex(COL_ADDRESS)));
        threat.setDistrict(cursor.getString(cursor.getColumnIndex(COL_DISTRICT)));
        threat.setPotential(cursor.getInt(cursor.getColumnIndex(COL_POTENTIAL)));

        return threat;
    }
}
