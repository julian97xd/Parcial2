package com.example.parcial2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class daoContacto {
    SQLiteDatabase cx;
    ArrayList<Contacto> Lista = new ArrayList<Contacto>();
    Contacto c;
    Context ct;
    String nombreBD= "BDContactos";
    String tabla ="create table if not exists contacto(id integer primary key autoincrement, Cedula int, Nombre text, Estrato int, Salario text, NivelEducativo text)";
    public daoContacto(Context c){
        this.ct = c;
        cx = c.openOrCreateDatabase(nombreBD, c.MODE_PRIVATE, null);
        cx.execSQL(tabla);
    }
    public boolean insertar(Contacto c) {
        ContentValues contenedor = new ContentValues();
        contenedor.put("Cedula", c.getCedula());
        contenedor.put("Nombre", c.getNombre());
        contenedor.put("Estrato", c.getEstrato());
        contenedor.put("Salario", c.getSalario());
        contenedor.put("NivelEducativo",c.getNivelEducativo());
        return (cx.insert("contacto", null,contenedor))>0;

    }
    public boolean editar(Contacto c) {
        ContentValues contenedor = new ContentValues();
        contenedor.put("Cedula", c.getCedula());
        contenedor.put("Nombre", c.getNombre());
        contenedor.put("Estrato", c.getEstrato());
        contenedor.put("Salario", c.getSalario());
        contenedor.put("NivelEducativo",c.getNivelEducativo());
        return (cx.update("contacto", contenedor,"id"+c.getId(),null)) > 0;

    }

    public boolean eliminar(int id) {
        return (cx.delete("contacto", "id="+id, null))>0;
    }



    public ArrayList<Contacto> verTodos() {
        Lista.clear();
        Cursor cursor=cx.rawQuery("select * from contacto", null);
        if(cursor!=null &&cursor.getCount()>0){
            cursor.moveToFirst();
            do{
                Lista.add(new Contacto(cursor.getInt(0),
                        cursor.getInt(1),
                        cursor.getString(2),
                        cursor.getInt(3),
                        cursor.getString(4),
                        cursor.getString(5)
                        ));
            }while(cursor.moveToNext());

        }
        return Lista;
    }

    public Contacto verUno(int posicion) {
        Cursor cursor=cx.rawQuery("select * from contacto", null);
        cursor.moveToPosition(posicion);
        c=new Contacto(cursor.getInt(0),
                cursor.getInt(1),
                cursor.getString(2),
                cursor.getInt(3),
                cursor.getString(4),
                cursor.getString(5));
        return c;
    }

}
