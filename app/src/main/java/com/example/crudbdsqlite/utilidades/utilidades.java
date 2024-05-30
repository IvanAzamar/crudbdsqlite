package com.example.crudbdsqlite.utilidades;

public class utilidades {
    public static final String TABLA_USUARIO = "usuario";
    public static final String CAMPO_ID = "id";
    public static final String CAMPO_NOMBRE = "nombre";
    public static final String CAMPO_DOMICILIO = "domicilio";
    public static final String CAMPO_EMAIL = "email";
    public static final String CAMPO_PASSWORD = "password";

    public static final String CREAR_TABLA_USUARIO = "CREATE TABLE usuario " + "(" + CAMPO_ID + " integer primary key autoincrement,"
            + CAMPO_NOMBRE + " text," + CAMPO_DOMICILIO + " text," + CAMPO_EMAIL + " text," + CAMPO_PASSWORD + " text)";
}
