package br.com.loewe.registrodeamecasambientais.model;

import java.io.Serializable;

/**
 * Created by Érico de Souza Loewe on 03/12/2016.
 */
public interface FirebaseModel extends Serializable {
    Long getId();
    void setId(Long uuid);
    String getUUID();
    void setUUID(String uuid);
}
