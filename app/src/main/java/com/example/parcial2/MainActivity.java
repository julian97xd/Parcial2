package com.example.parcial2;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;
import android.os.Bundle;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView estado;
    TextView estado2;
    Spinner comboEstrato;
    Spinner comboNivelE;
    daoContacto dao;
    Adaptador adapter;
    ArrayList<Contacto> lista;
    Contacto c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        estado= (TextView) findViewById(R.id.etiSeleccion);
        comboEstrato=(Spinner) findViewById(R.id.Estrato);
        estado2= (TextView) findViewById(R.id.etiSeleccion2);
        comboNivelE=(Spinner) findViewById(R.id.NivelEducativo);
        ArrayAdapter<CharSequence> adapter1=ArrayAdapter.createFromResource(this,R.array.combo_estrato,android.R.layout.simple_spinner_item);
        comboEstrato.setAdapter(adapter1);
        ArrayAdapter<CharSequence> adapter2=ArrayAdapter.createFromResource(this,R.array.combo_nivelEducativo,android.R.layout.simple_spinner_item);
        comboNivelE.setAdapter(adapter2);
        comboEstrato.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(parent.getContext(),"Seleccionado:"+parent.getItemAtPosition(position).toString(),Toast.LENGTH_LONG).show();

                estado.setText("Seleccion"+parent.getItemAtPosition(position).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        comboNivelE.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(parent.getContext(),"Seleccionado:"+parent.getItemAtPosition(position).toString(),Toast.LENGTH_LONG).show();

                estado2.setText("Seleccion"+parent.getItemAtPosition(position).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        dao=new daoContacto( MainActivity.this);
        lista=dao.verTodos();
        adapter=new Adaptador( this,lista,dao);
        ListView list=(ListView)findViewById(R.id.lista);
        Button agregar=(Button)findViewById(R.id.Agregar);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Dialogo para ver vista previa de registro fragment_second.xml

            }
        });
        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                //Dialogo de agregar content_main.xml
                final Dialog dialogo= new Dialog(MainActivity.this);
                dialogo.setTitle("Nuevo Registro");
                dialogo.setCancelable(true);
                dialogo.setContentView(R.layout.content_main);
                dialogo.show();
                @SuppressLint("WrongViewCast")final EditText Cedula=(EditText)dialogo.findViewById(R.id.Cedula);
                @SuppressLint("WrongViewCast")final EditText Nombre =(EditText)dialogo.findViewById(R.id.Nombre);
                @SuppressLint("WrongViewCast")final EditText Estrato=(EditText)dialogo.findViewById(R.id.etiSeleccion);
                @SuppressLint("WrongViewCast")final EditText Salario= (EditText) dialogo.findViewById(R.id.Salario);
                @SuppressLint("WrongViewCast")final EditText NivelEducativo=(EditText)dialogo.findViewById(R.id.etiSeleccion2);
                Button guardar=(Button)dialogo.findViewById(R.id.d_agregar);
                guardar.setText("Agregar");
                Button cancelar=(Button)dialogo.findViewById(R.id.d_Cancelar);
                guardar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        try{
                            c=new Contacto(Integer.parseInt(Cedula.getText().toString()),Nombre.getText().toString(),
                                    Integer.parseInt(Estrato.getText().toString()),Salario.getText().toString(),NivelEducativo.getText().toString());
                            dao.insertar(c);
                            lista=dao.verTodos();
                            adapter.notifyDataSetChanged();
                            dialogo.dismiss();
                        }catch (Exception e){
                            Toast.makeText(getApplication(),"ERROR",Toast.LENGTH_SHORT).show();
                        }

                    }
                });
                cancelar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialogo.dismiss();

                    }
                });


            }
        });

    }
}
