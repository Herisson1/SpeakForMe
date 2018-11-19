package herisson.application.br.speakforme.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import herisson.application.br.speakforme.DAO.UsuarioDAO;
import herisson.application.br.speakforme.R;
import herisson.application.br.speakforme.Usuario;

public class ListarUsuarios extends AppCompatActivity {


    private ListView listView;
    private UsuarioDAO usuarioDAO;
    private List<Usuario> usuarios;
    private List<Usuario> usuariosAux = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_usuarios);

        listView = findViewById(R.id.lista_usuarios);
        usuarioDAO = new UsuarioDAO(this);

        usuarios = usuarioDAO.buscar();
        usuariosAux.addAll(usuarios);

        ArrayAdapter<Usuario> adapter = new ArrayAdapter<Usuario>(this, android.R.layout.simple_list_item_1, usuarios);
        listView.setAdapter(adapter);


    }
}
