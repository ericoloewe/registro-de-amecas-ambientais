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
    private static final String COL_IMAGE = "image";

    public ThreatRepository(Context context) {
        super(context, Threat.class);
    }

    public Threat find(Long id) {
        return convertToThreat(super.findCursor(id));
    }

    public void insert(Threat threat) {
        ContentValues values = new ContentValues();

        values.put(COL_DESCRIPTION, threat.getDescription());
        values.put(COL_ADDRESS, threat.getAddress());
        values.put(COL_DISTRICT, threat.getDistrict());
        values.put(COL_POTENTIAL, threat.getPotential());
        values.put(COL_IMAGE, threat.getImage());

        super.insert(values, threat);
    }

    public void update(Threat threat) {
        ContentValues values = new ContentValues();

        values.put(COL_ID, threat.getId());
        values.put(COL_DESCRIPTION, threat.getDescription());
        values.put(COL_ADDRESS, threat.getAddress());
        values.put(COL_DISTRICT, threat.getDistrict());
        values.put(COL_POTENTIAL, threat.getPotential());
        values.put(COL_IMAGE, threat.getImage());

        super.update(values, threat);
    }

    public void saveOrUpdate(Threat threat) {
        if(threat.getId() == null) {
            insert(threat);
        } else {
            update(threat);
        }
    }

    public void delete(Long id) {
        super.deleteById(id);
    }

    public List<Threat> list() {
        List<Threat> threatList = new ArrayList<>();
        Cursor cursor = super.listByCursor();

        cursor.moveToFirst();
        for (int i = 0; i < cursor.getCount(); i++) {
            cursor.moveToPosition(i);
            threatList.add(convertToThreat(cursor));
        }

        return threatList;
    }

    private Threat convertToThreat(Cursor cursor) {
        Threat threat = new Threat();

        threat.setId(cursor.getLong(cursor.getColumnIndex(COL_ID)));
        threat.setDescription(cursor.getString(cursor.getColumnIndex(COL_DESCRIPTION)));
        threat.setAddress(cursor.getString(cursor.getColumnIndex(COL_ADDRESS)));
        threat.setDistrict(cursor.getString(cursor.getColumnIndex(COL_DISTRICT)));
        threat.setPotential(cursor.getInt(cursor.getColumnIndex(COL_POTENTIAL)));
        threat.setImage(cursor.getString(cursor.getColumnIndex(COL_IMAGE)));

        return threat;
    }
}
