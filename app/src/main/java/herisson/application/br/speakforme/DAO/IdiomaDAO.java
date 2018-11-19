package herisson.application.br.speakforme.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.EditText;

import java.io.Serializable;
import java.util.ArrayList;

import herisson.application.br.speakforme.Idioma;

public class IdiomaDAO implements Serializable {

    private DBHelper conexao;
    private SQLiteDatabase database;

    public IdiomaDAO (Context context){

        conexao  = new DBHelper(context);
        database = conexao.getWritableDatabase();

    }

    public long inserir(Idioma idioma){

        ContentValues valores = new ContentValues();

        valores.put("nome", idioma.getNome());

        return database.insert("idioma", null, valores);

    }

    public long atualizar(Idioma idioma, String idiomaAntigo){
        ContentValues valores = new ContentValues();

        valores.put("nome", idioma.getNome());

        return database.update("idioma", valores, "nome = ?", new String[]{""+idiomaAntigo});

    }

    public long deletar(Idioma idioma){
        return database.delete("idioma", "nome = ?", new String[]{""+idioma.getNome().toString()});

    }


    public ArrayList<String> idiomaList () {

        ArrayList<String> idiomas = new ArrayList<String>();

        String query = "select nome from idioma";
        Cursor cursor = database.rawQuery(query, null);

        if(cursor != null && cursor.moveToFirst())
            do{

                idiomas.add(cursor.getString(cursor.getColumnIndex("nome")));

            } while(cursor.moveToNext());

        return idiomas;
    }

    public boolean validaIdioma (EditText nomeIdioma){

        String query = "select nome from idioma where nome = ?";
        Cursor cursor = database.rawQuery(query, new String[] {nomeIdioma.getText().toString()});

        if(cursor != null && cursor.moveToFirst()){
            return true;
        } else {
            return false;
        }
    }

}
