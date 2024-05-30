package com.example.crudbdsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.crudbdsqlite.utilidades.utilidades;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class consultar extends AppCompatActivity {
    TextInputLayout tilID, tilNombre, tilDomicilio, tilEmail, tilPassword;
    TextInputEditText tietNombre, tietDomicilio, tietEmail, tietPassword;
    ConexionSQLiteHelper conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar);

        conn = new ConexionSQLiteHelper(getApplicationContext(), "bd_usuario", null, 1);

        tilID = (TextInputLayout) findViewById(R.id.tilID);
        tilNombre = (TextInputLayout) findViewById(R.id.tilNombre);
        tilDomicilio = (TextInputLayout) findViewById(R.id.tilDomicilio);
        tilEmail = (TextInputLayout) findViewById(R.id.tilEmail);
        tilPassword = (TextInputLayout) findViewById(R.id.tilPassword);

        tietNombre = findViewById(R.id.tietNombre);
        tietDomicilio = findViewById(R.id.tietDomicilio);
        tietEmail = findViewById(R.id.tietEmail);
        tietPassword = findViewById(R.id.tietPassword);
    }

    public  void consultar (View view){
        SQLiteDatabase db = conn.getReadableDatabase();
        String[] parametros = {tilID.getEditText().getText().toString()};
        String[] campos = {utilidades.CAMPO_NOMBRE,utilidades.CAMPO_DOMICILIO,
                utilidades.CAMPO_EMAIL, utilidades.CAMPO_PASSWORD};

        try{
            Cursor cursor = db.query(utilidades.TABLA_USUARIO, campos, utilidades.CAMPO_ID + "=?",
                    parametros, null, null,null);

            cursor.moveToFirst();

            tietNombre.setText((cursor.getString(0)));
            tietDomicilio.setText((cursor.getString(1)));
            tietEmail.setText((cursor.getString(2)));
            tietPassword.setText((cursor.getString(3)));

            tilNombre.setHint((cursor.getString(0)));
            tilDomicilio.setHint((cursor.getString(1)));
            tilEmail.setHint((cursor.getString(2)));
            tilPassword.setHint((cursor.getString(3)));
            cursor.close();
        }catch (Exception e){
            Toast.makeText(this, "El ID no existe", Toast.LENGTH_SHORT).show();
            tietNombre.setText("");
            tietDomicilio.setText("");
            tietEmail.setText("");
            tietPassword.setText("");

            tilNombre.setHint("");
            tilDomicilio.setHint("");
            tilEmail.setHint("");
            tilPassword.setHint("");
            limpiar();
        }
    }
    private void limpiar(){
        tietNombre.setText("");
        tietDomicilio.setText("");
        tietEmail.setText("");
        tietPassword.setText("");
    }

    public void actualizarUsuario(View view){
        SQLiteDatabase db = conn.getWritableDatabase();

        String[] parametros = {tilID.getEditText().getText().toString()};
        ContentValues values = new ContentValues();

        values.put(utilidades.CAMPO_NOMBRE, tilNombre.getEditText().getText().toString());
        values.put(utilidades.CAMPO_DOMICILIO, tilDomicilio.getEditText().getText().toString());
        values.put(utilidades.CAMPO_EMAIL, tilEmail.getEditText().getText().toString());
        values.put(utilidades.CAMPO_PASSWORD, tilPassword.getEditText().getText().toString());

        db.update(utilidades.TABLA_USUARIO, values, utilidades.CAMPO_ID + "=?", parametros);
        Toast.makeText(this, "Datos Actualizados", Toast.LENGTH_SHORT).show();
        db.close();
    }


    public void eliminarUsuario(View view){
        SQLiteDatabase db = conn.getWritableDatabase();
        String[] parametros = {tilID.getEditText().getText().toString()};

        db.delete(utilidades.TABLA_USUARIO, utilidades.CAMPO_ID + "=?", parametros);
        Toast.makeText(this, "Usuario Eliminado", Toast.LENGTH_SHORT).show();

        tilID.setHint("");

        tietNombre.setText("");
        tietDomicilio.setText("");
        tietEmail.setText("");
        tietPassword.setText("");


        tilNombre.setHint("");
        tilDomicilio.setHint("");
        tilEmail.setHint("");
        tilPassword.setHint("");

        db.close();
    }
}