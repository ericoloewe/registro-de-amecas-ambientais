package br.com.loewe.registrodeamecasambientais.repository;

import java.util.List;

import br.com.loewe.registrodeamecasambientais.model.FirebaseModel;

/**
 * Created by Érico de Souza Loewe on 03/12/2016.
 */
public class RepositoryCallback {

    public interface OnFind<T extends FirebaseModel> {
        void onFind(T value);
    }

    public interface OnFindAll<T extends FirebaseModel> {
        void onFindAll(List<T> values);
    }

    public interface OnCreate<T extends FirebaseModel> {
        void onCreate(T value);
    }


    public interface OnUpdate<T extends FirebaseModel> {
        void onUpdate(T value);
    }

    public interface OnDelete<T extends FirebaseModel> {
        void onDelete(T value);
    }

}
