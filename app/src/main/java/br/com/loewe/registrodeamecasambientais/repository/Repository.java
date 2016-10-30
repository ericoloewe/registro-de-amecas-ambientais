package br.com.loewe.registrodeamecasambientais.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.List;

import br.com.loewe.registrodeamecasambientais.utils.DbPersistor;

/**
 * Created by Érico de Souza Loewe on 29/10/2016.
 */
public class Repository<T extends Object> {

    private final RepositoryDBHelper dbHelper;
    private final Class<T> base;
    private final String TABLE_NAME;

    public Repository(Context context, Class<T> base) {
        this.TABLE_NAME = DbPersistor.getTableName(base);
        this.dbHelper = new RepositoryDBHelper<T>(context, TABLE_NAME, DbPersistor.buildCreateTableCommand(base));
        this.base = base;
    }

    public Cursor find(Integer id) {
        String colId = DbPersistor.getPrimaryKey(base).getName();
        return this.dbHelper.getDb().rawQuery(String.format("SELECT FROM %s * WHERE %s = ?", TABLE_NAME, colId), new String[]{id.toString()});
    }

    public void insert(ContentValues values) {
        this.dbHelper.getDb().insert(TABLE_NAME, null, values);
    }

    public void update(ContentValues values) {
        String colId = DbPersistor.getPrimaryKey(base).getName();
        this.dbHelper.getDb().update(TABLE_NAME, values, String.format("%s=?", colId), new String[]{values.getAsString(colId)});
    }

    public Integer delete(Integer id) {
        String colId = DbPersistor.getPrimaryKey(base).getName();
        return this.dbHelper.getDb().delete(TABLE_NAME, colId + "=" + id, null);
    }

    public Cursor list() {
        String colId = DbPersistor.getPrimaryKey(base).getName();
        List<String> columNames = DbPersistor.getColumNames(base);
        String[] colums = columNames.toArray(new String[columNames.size()]);

        return this.dbHelper.getDb().query(TABLE_NAME, colums, null, null, null, null, colId);
    }

    public Integer empty() {
        return this.dbHelper.getDb().delete(TABLE_NAME, null, null);
    }

    private class RepositoryDBHelper<T extends Object> extends SQLiteOpenHelper {
        private static final String DB_NAME = "threat.db";
        private static final int DB_VERSION = 1;
        private final String TABLE_NAME;
        private final String SQL_CREATE_TABLE;
        private SQLiteDatabase db;

        public RepositoryDBHelper(Context context, String tableName, String sqlCreateTable) {
            super(context, DB_NAME, null, DB_VERSION);
            this.TABLE_NAME = tableName;
            this.SQL_CREATE_TABLE = sqlCreateTable;
        }

        public void open() {
            db = getWritableDatabase();
        }

        public void close() {
            db.close();
        }

        public SQLiteDatabase getDb() {
            return db;
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(SQL_CREATE_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(db);
        }
    }
}
