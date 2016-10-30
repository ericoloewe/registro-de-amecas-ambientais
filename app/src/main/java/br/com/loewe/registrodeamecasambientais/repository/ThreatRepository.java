package br.com.loewe.registrodeamecasambientais.repository;

import android.content.Context;

import br.com.loewe.registrodeamecasambientais.model.Threat;

/**
 * Created by Érico de Souza Loewe on 29/10/2016.
 */
public class ThreatRepository extends Repository<Threat> {

    public ThreatRepository(Context context) {
        super(context, Threat.class);
    }
}
