package herisson.application.br.speakforme.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    private static final String NOME_BD = "DBSPEAKFORME";
    private static final int VERSAO_BD = 8;


    public DBHelper(Context context) {

        super(context, NOME_BD, null, VERSAO_BD);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String tabelaUsuario = "create table usuario (_id integer primary key autoincrement not null, nome varchar(50) not null, email varchar(50) not null, senha text not null, idioma varchar(50) not null);";
        String tabelaPais = "create table pais (_id integer primary key autoincrement not null, nome varchar(50) not null);";
        String tabelaIdioma = "create table idioma (_id integer primary key autoincrement not null, nome varchar(50) not null);";
        String tabelaExpressao = "create table expressao (_id integer primary key autoincrement not null, nome varchar(50) not null, idioma varchar(50) not null, categoria varchar(50) not null);";

        db.execSQL(tabelaUsuario);
        db.execSQL(tabelaPais);
        db.execSQL(tabelaIdioma);
        db.execSQL(tabelaExpressao);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table usuario");
        db.execSQL("drop table pais");
        db.execSQL("drop table idioma");
        db.execSQL("drop table expressao");
        onCreate(db);
    }



}
