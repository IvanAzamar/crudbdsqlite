package com.example.crudbdsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.crudbdsqlite.entidades.usuario;
import com.example.crudbdsqlite.utilidades.utilidades;

import java.util.ArrayList;

public class ConsultarListView extends AppCompatActivity {
    ListView lvPersonas;
    ArrayList<String> listaInformacion;
    ArrayList<usuario> listaUsuarios;
    ConexionSQLiteHelper conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_list_view);

        Toast.makeText(this, "VISUALIZACIÓN DE TODOS LOS REGISTROS", Toast.LENGTH_LONG).show();

        conn = new ConexionSQLiteHelper(this, "bd_usuario", null, 1);

        lvPersonas = findViewById(R.id.lvPersonas);

        consultarListaPersonas();
        obtenerLista();

        ArrayAdapter<String> adaptador = new ArrayAdapter<>
                (this, android.R.layout.simple_list_item_1, listaInformacion);
        lvPersonas.setAdapter(adaptador);

        lvPersonas.setOnItemClickListener((adapterView, view, pos, l) -> {
            String informacion = "ID: " + listaUsuarios.get(pos).getId() + "\n";
            informacion += "NOMBRE: " + listaUsuarios.get(pos).getNombre() + "\n";
            informacion += "DOMICILIO: " + listaUsuarios.get(pos).getDomicilio() + "\n";
            informacion += "EMAIL: " + listaUsuarios.get(pos).getEmail() + "\n";
            informacion += "PASSWORD: " + listaUsuarios.get(pos).getPassword() + "\n";

            Toast.makeText(this, informacion, Toast.LENGTH_LONG).show();
        });
    }
    private void consultarListaPersonas() {
        SQLiteDatabase db = conn.getReadableDatabase();
        listaUsuarios = new ArrayList<>();

        Cursor cursor = db.rawQuery("SELECT * FROM " + utilidades.TABLA_USUARIO, null);

        while (cursor.moveToNext()) {
            usuario usuario = new usuario();
            usuario.setId(cursor.getInt(0));
            usuario.setNombre(cursor.getString(1)); // Corregir los índices para obtener los valores correctos
            usuario.setDomicilio(cursor.getString(2));
            usuario.setEmail(cursor.getString(3));
            usuario.setPassword(cursor.getString(4));
            listaUsuarios.add(usuario);
        }
        cursor.close(); // Cerrar el cursor cuando ya no se necesite
    }

    private void obtenerLista() {
        listaInformacion = new ArrayList<>();

        for (int i = 0; i < listaUsuarios.size(); i++) {
            listaInformacion.add(listaUsuarios.get(i).getId() + " - "
                    + listaUsuarios.get(i).getNombre());
        }
    }
}