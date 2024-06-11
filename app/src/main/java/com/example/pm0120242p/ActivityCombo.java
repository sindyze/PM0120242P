package com.example.pm0120242p;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

import Configuracion.Personas;
import Configuracion.SQLiteConexion;
import Configuracion.Trans;

public class ActivityCombo extends AppCompatActivity {

    SQLiteConexion conexion;
    Spinner combopersonas;
    EditText Nombres, Apellidos, Correo;
    ArrayList<Personas> lista;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_combo);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        conexion = new SQLiteConexion(this, Trans.DBname, null, Trans.Version);
        combopersonas = findViewById(R.id.spinner);
        Nombres = findViewById(R.id.cbnombre);
        Apellidos = findViewById(R.id.cbapellido);
        Correo = findViewById(R.id.cbcorreo);

        ObtenerInfo();

    }

    private void ObtenerInfo() {
        SQLiteDatabase db = conexion.getReadableDatabase();
        lista = new ArrayList<>();

        // Cursor para recorrer los datos de la tabla
        Cursor cursor = db.rawQuery(Trans.SelectAllPerson, null);

        while (cursor.moveToNext()) {
            Personas person = new Personas();
            person.setId(cursor.getInt(0));
            person.setNombres(cursor.getString(1));
            person.setApellidos(cursor.getString(2));
            person.setCorreo(cursor.getString(4));

            lista.add(person);
        }

        cursor.close();

        FillData();
    }

    private void FillData() {
        ArrayAdapter<Personas> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, lista);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        combopersonas.setAdapter(adapter);

        combopersonas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Personas selectedPerson = (Personas) parent.getSelectedItem();
                Nombres.setText(selectedPerson.getNombres());
                Apellidos.setText(selectedPerson.getApellidos());
                Correo.setText(selectedPerson.getCorreo());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}