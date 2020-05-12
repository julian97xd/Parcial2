package com.example.parcial2;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
public class Adaptador extends BaseAdapter {
    ArrayList<Contacto> Lista;
    daoContacto dao;
    Contacto c;
    Activity a;
    int id;

    public Adaptador(Activity a, ArrayList<Contacto> lista, daoContacto dao) {
        this.Lista = lista;
        this.a = a;
        this.dao = dao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int getCount() {
        return Lista.size();
    }

    @Override
    public Contacto getItem(int i) {
        c = Lista.get(i);
        return null;
    }

    @Override
    public long getItemId(int i) {
        c = Lista.get(i);
        return c.getId();
    }

    @Override
    public View getView(int posicion, View view, ViewGroup viewGroup) {
        View v = view;
        if (v == null) {
            LayoutInflater li = (LayoutInflater) a.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = li.inflate(R.layout.fragment_first, null);
        }
        c = Lista.get(posicion);
        final TextView Cedula=(TextView)v.findViewById(R.id.t_Cedula);
        final TextView Nombre =(TextView)v.findViewById(R.id.t_Nombre);
        final TextView Estrato=(TextView)v.findViewById(R.id.t_etiSeleccion);
        final TextView Salario=(TextView)v.findViewById(R.id.t_Salario);
        final TextView NivelEducativo=(TextView)v.findViewById(R.id.t_etiSeleccion2);
        Button editar=(Button)v.findViewById(R.id.Editar);
        Button eliminar=(Button)v.findViewById(R.id.Eliminar);
        Cedula.setText(""+c.getCedula());
        Nombre.setText(c.getNombre());
        Estrato.setText(""+c.getEstrato());
        Salario.setText(c.getSalario());
        NivelEducativo.setText(c.getNivelEducativo());
        editar.setTag(posicion);
        eliminar.setTag(posicion);
        editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Dialogo de editar content_main.xml
                int pos=Integer.parseInt(view.getTag().toString());
                final Dialog dialogo= new Dialog(a);
                dialogo.setTitle("Editar Registro");
                dialogo.setCancelable(true);
                dialogo.setContentView(R.layout.content_main);
                dialogo.show();
                @SuppressLint("WrongViewCast") final EditText Cedula=(EditText)dialogo.findViewById(R.id.Cedula);
                @SuppressLint("WrongViewCast")final EditText Nombre =(EditText)dialogo.findViewById(R.id.Nombre);
                @SuppressLint("WrongViewCast")final EditText Estrato=(EditText)dialogo.findViewById(R.id.etiSeleccion);
                @SuppressLint("WrongViewCast")final EditText Salario= (EditText) dialogo.findViewById(R.id.Salario);
                @SuppressLint("WrongViewCast")final EditText NivelEducativo=(EditText)dialogo.findViewById(R.id.etiSeleccion2);
                Button guardar=(Button)dialogo.findViewById(R.id.d_agregar);
                guardar.setText("Agregar");
                Button cancelar=(Button)dialogo.findViewById(R.id.d_Cancelar);
                c=Lista.get(pos);
                setId(c.getId());
               Cedula.setText(""+c.getCedula());
               Nombre.setText(c.getNombre());
               Estrato.setText(""+c.getEstrato());
               Salario.setText(c.getSalario());
               NivelEducativo.setText(c.getNivelEducativo());
                guardar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        try{
                            c=new Contacto(getId(),Integer.parseInt(Cedula.getText().toString()),Nombre.getText().toString(),
                                    Integer.parseInt(Estrato.getText().toString()),Salario.getText().toString(),NivelEducativo.getText().toString());
                            dao.editar(c);
                            Lista=dao.verTodos();
                            notifyDataSetChanged();
                            dialogo.dismiss();
                        }catch (Exception e){
                            Toast.makeText(a,"ERROR",Toast.LENGTH_SHORT).show();
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
        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //dialogo para confirmar Si/No
                int pos=Integer.parseInt(view.getTag().toString());
                c=Lista.get(pos);
                setId(c.getId());
                AlertDialog.Builder del=new AlertDialog.Builder(a);
                del.setMessage(("EStas seguro de eliminar el visitante"));
                del.setCancelable(false);
                del.setPositiveButton("SI", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dao.eliminar(getId());
                        Lista=dao.verTodos();
                        notifyDataSetChanged();

                    }
                });
                del.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                del.show();

            }
        });


        return v;
    }

    }

