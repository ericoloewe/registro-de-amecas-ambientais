package br.com.loewe.registrodeamecasambientais.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Érico de Souza Loewe on 30/10/2016.
 */
public class DbPersistor {
    public static String getTableName(Class base) {
        return base.getSimpleName().toUpperCase();
    }

    public static String buildCreateTableCommand(Class base) {
        StringBuilder sql = new StringBuilder();
        sql.append("CREATE TABLE IF NOT EXISTS ");
        sql.append(getTableName(base));
        sql.append("(");

        for (Field atr : base.getDeclaredFields()) {
            sql.append(createTableField(atr));
        }
        sql.append(")");

        return sql.toString();
    }

    private static String createTableField(Field atr) {
        String nome = atr.getName();
        String tipo = atr.getType().getSimpleName();
        String tipoDoBanco = "";

        if (tipo.equals("String")) {
            tipoDoBanco = "TEXT";
        } else if (tipo.equals("int") || tipo.equals("Integer")) {
            tipoDoBanco = "INTEGER";
        }
        if (isPrimaryKey(atr)) {
            return String.format("\n%s %s NULL,", nome, tipoDoBanco);
        } else {
            return String.format("\n%s %s PRIMARY KEY AUTOINCREMENT,", nome, tipoDoBanco);
        }
    }

    public static List<String> getColumNames(Class base) {
        List<String> colums = new ArrayList<String>();

        for (Field atr : base.getDeclaredFields()) {
            colums.add(atr.getName());
        }

        return colums;
    }

    public static Field getPrimaryKey(Class base) {
        for (Field atr : base.getDeclaredFields()) {
            if (isPrimaryKey(atr)) {
                return atr;
            }
        }
        throw new IllegalArgumentException("Não contem primary key!");
    }

    public static boolean isPrimaryKey(Field atr) {
        return atr.getName().toLowerCase().equals("id") || atr.getName().substring(atr.getName().length() - 2, atr.getName().length()).toLowerCase().equals("id") || atr.getName().substring(0, 2).toLowerCase().equals("id");
    }
}
