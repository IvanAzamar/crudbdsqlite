package com.example.crudbdsqlite;

import androidx.appcompat.app.AppCompatActivity;
//otro
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void ActivityInsertar(View view){
        Toast.makeText(this, "Abriendo Pagina Espere. . .", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, insertar.class);
        startActivity(intent);
    }

    public void ActivityConsultar(View view){
        Toast.makeText(this, "Abriendo Pagina Espere. . .", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, consultar.class);
        startActivity(intent);
    }

    public void ActivityListView(View view){
        Toast.makeText(this, "Abriendo Pagina Espere. . .", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, ConsultarListView.class);
        startActivity(intent);
    }

}
