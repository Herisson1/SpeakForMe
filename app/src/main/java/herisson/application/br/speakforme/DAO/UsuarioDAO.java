package herisson.application.br.speakforme.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import herisson.application.br.speakforme.Usuario;

public class UsuarioDAO {

    private DBHelper conexao;
    private SQLiteDatabase database;

    public UsuarioDAO (Context context){

       conexao  = new DBHelper(context);
       database = conexao.getWritableDatabase();


    }

    public long inserir(Usuario usuario){

        ContentValues valores = new ContentValues();

        valores.put("nome", usuario.getNome());
        valores.put("email", usuario.getEmail());
        valores.put("senha", usuario.getSenha());
        valores.put("idioma", usuario.getIdioma());

        return database.insert("usuario", null, valores);
    }

    public void atualizar(Usuario usuario){
        ContentValues valores = new ContentValues();

        valores.put("nome", usuario.getNome());
        valores.put("email", usuario.getEmail());
        valores.put("senha", usuario.getSenha());
        valores.put("idioma", usuario.getIdioma());

        database.update("usuario", valores, "_id = ?", new String[]{""+usuario.getId()});
    }

    public void deletar(Usuario usuario){
       database.delete("usuario", "_id = "+usuario.getId(), null);
    }



    public List<Usuario> buscar(){
        List<Usuario> usuarios = new ArrayList<>();

        String[] colunas = new String[]{"_id", "nome", "email"};
        Cursor cursor = database.query("usuario", colunas, null, null, null, null, "email ASC");

        while(cursor.moveToNext()){

            Usuario usuario = new Usuario();

            usuario.setId(cursor.getInt(0));
            usuario.setNome(cursor.getString(1));
            usuario.setEmail(cursor.getString(2));

            usuarios.add(usuario);
        }

        return usuarios;
    }

    public String validaSenha(String usuario){

        String query = "select email,senha from usuario";
        Cursor cursor = database.rawQuery(query, null);

        String usuarioValidacao;
        String senhaValidacao;

        senhaValidacao = "Senha inválida.";

        if(cursor.moveToFirst()){
            do {
                usuarioValidacao = cursor.getString(0);

                if (usuarioValidacao.equals(usuario)){
                    senhaValidacao = cursor.getString(1);
                    break;
                }
            }
            while (cursor.moveToNext());
        }

        return senhaValidacao;
    }

    public boolean validaEmail (String email){

        String query = "select email from usuario where email = ?";
        Cursor cursor = database.rawQuery(query, new String[]{email.toString()});

        if(cursor != null && cursor.moveToFirst()){
            return true;
        }else{
            return false;
        }

    }

    public String retornaNome (String email){


        String nomeUsuario = "Nome não encontrado para o email "+email;

        String query = "select nome from usuario where email = ?";
        Cursor cursor = database.rawQuery(query, new String[]{email});

        if(cursor != null && cursor.moveToFirst()){

            nomeUsuario = cursor.getString(0);

        }

        return nomeUsuario;

    }


}
