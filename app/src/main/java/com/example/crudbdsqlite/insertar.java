package com.example.crudbdsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.crudbdsqlite.utilidades.utilidades;
import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Pattern;

public class insertar extends AppCompatActivity {

    EditText etNombre, etDomicilio, etEmail,etPassword;
    TextInputLayout tilNombre, tilDomicilio, tilEmail, tilPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertar);

        tilNombre =(TextInputLayout) findViewById(R.id.tilNombre);
        tilDomicilio=(TextInputLayout) findViewById(R.id.tilDomicilio);
        tilEmail = (TextInputLayout) findViewById(R.id.tilEmail);
        tilPassword = (TextInputLayout) findViewById(R.id.tilPassword);

    }

    private boolean esNombreValido(String otronombre){
        if(otronombre.equals("") || otronombre.length() >= 30){
            tilNombre.setError("Nombre invalido");
            return false;
        }else{
            tilNombre.setError(null);
        }

        return true;
    }

    private boolean esDireccionValido(String direccion){
        Pattern patron = Pattern.compile("[0-9a-zA-Z]");

        if(direccion.equals("") || direccion.length() >= 30){
            tilDomicilio.setError("Direccion invalida");
            return false;
        }else{
            tilDomicilio.setError(null);
        }

        return true;
    }

    private boolean esCorreoValido(String correo){
        Pattern patron = Pattern.compile("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}");

        if(correo.equals("") || !patron.matcher(correo).matches()){
            tilEmail.setError("Correo electronico invalido");
            return false;
        }else{
            tilEmail.setError(null);
        }

        return true;
    }

    public void registrarUsuarios(View view){
        final String nombre1 = tilNombre.getEditText().getText().toString();
        final String domicilio1 = tilDomicilio.getEditText().getText().toString();
        final String email = tilEmail.getEditText().getText().toString();

        boolean a = esNombreValido(nombre1);
        boolean b = esDireccionValido(domicilio1);
        boolean c = esCorreoValido(email);

        if(a && b && c){
            ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this, "bd_usuario", null, 1);
            SQLiteDatabase db = conn.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(utilidades.CAMPO_NOMBRE, tilNombre.getEditText().getText().toString());
            values.put(utilidades.CAMPO_DOMICILIO, tilDomicilio.getEditText().getText().toString());
            values.put(utilidades.CAMPO_EMAIL, tilEmail.getEditText().getText().toString());
            values.put(utilidades.CAMPO_PASSWORD, tilPassword.getEditText().getText().toString());
            Long idResultante = db.insert(utilidades.TABLA_USUARIO, utilidades.CAMPO_NOMBRE, values);

            Toast.makeText(this,"Numbero de Registro: " + idResultante, Toast.LENGTH_LONG).show();
            db.close();
        }else{
            Toast.makeText(this,"DATOS INVALIDOS ", Toast.LENGTH_LONG).show();
        }
    }


}