package herisson.application.br.speakforme.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import herisson.application.br.speakforme.Expressao;
import herisson.application.br.speakforme.Idioma;

public class ExpressaoDAO {

    private DBHelper conexao;
    private SQLiteDatabase database;

    public ExpressaoDAO (Context context){

        conexao  = new DBHelper(context);
        database = conexao.getWritableDatabase();

    }


    public long inserir (Expressao expressao){

        ContentValues valores = new ContentValues();

        valores.put("nome", expressao.getNome());
        valores.put("idioma", expressao.getIdioma());
        valores.put("categoria", expressao.getCategoria());

        return database.insert("expressao", null, valores);

    }

    public void atualizar(Expressao expressao, String expressaoAntiga){
        ContentValues valores = new ContentValues();

        valores.put("nome", expressao.getNome());

        database.update("expressao", valores, "nome = ? and idioma = ? and categoria = ? and _id = ?", new String[]{expressao.getNome(), expressao.getIdioma(), expressao.getCategoria(), expressaoAntiga});
    }

    public void deletar(Expressao expressao){
        database.delete("expressao", "nome = ? and idioma = ? and categoria = ?", new String[] {expressao.getNome(), expressao.getIdioma(), expressao.getCategoria()});
    }

    public ArrayList<String> expressaoList(){

        ArrayList<String> expressoes = new ArrayList<String>();

        String query = "select nome from expressao";
        Cursor cursor = database.rawQuery(query, null);

        if(cursor != null && cursor.moveToFirst())
            do{

                expressoes.add(cursor.getString(cursor.getColumnIndex("nome")));

            } while(cursor.moveToNext());

        return expressoes;

    }

    public boolean validaExpressao(Expressao expressao){

        String query = "select nome from expressao where idioma = ? and categoria = ? and nome = ?";
        Cursor cursor = database.rawQuery(query, new String[] {expressao.getIdioma(),expressao.getCategoria(),expressao.getNome()});

        if(cursor != null && cursor.moveToFirst()){
            return true;
        } else {
            return false;
        }
    }

    /* Idioma */

    public void deletarCascata(Idioma idioma){
        database.delete("expressao", "idioma = ?", new String[] {idioma.getNome()});
    }

    public boolean validaExpressao(Idioma idioma){
        String query = "select * from expressao where idioma = ?";
        Cursor cursor = database.rawQuery(query, new String[] {idioma.getNome()});

        if(cursor != null && cursor.moveToFirst()){
            return true; /* possui expressões para aquele idioma */
        } else {
            return false; /* não tem expressões para aquele idioma */
        }

    }

}
