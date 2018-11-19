package herisson.application.br.speakforme.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import herisson.application.br.speakforme.DAO.UsuarioDAO;
import herisson.application.br.speakforme.R;
import herisson.application.br.speakforme.Usuario;

public class CadastroUsuarioActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    // Variáveis
    private EditText nome;
    private EditText email;
    private EditText senha;
    private EditText confirmarSenha;
    private String idioma;

    private UsuarioDAO usuarioDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_usuario);

        final Spinner spinnerIdioma = findViewById(R.id.idiomaUsuario);

        ArrayAdapter<CharSequence> adapterIdioma = ArrayAdapter.createFromResource(this, R.array.Idioma, android.R.layout.simple_spinner_item);
        adapterIdioma.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerIdioma.setAdapter(adapterIdioma);
        spinnerIdioma.setOnItemSelectedListener(this);

        /* --- Variáveis --- */

        nome = (EditText) findViewById(R.id.nomeUsuario);
        email = (EditText) findViewById(R.id.emailUsuario);
        senha = (EditText) findViewById(R.id.senhaUsuario);
        confirmarSenha = (EditText) findViewById(R.id.confSenhaUsario);
        idioma = spinnerIdioma.getSelectedItem().toString();



        usuarioDAO = new UsuarioDAO(this);

        /*
        *  TODO Método de validação para inserção de um nome válido
        *
        *  Garantir que o nome não contenha caracteres especiais e/ou números
        *
        * */


        final Button btHome = (Button) findViewById(R.id.btHome);
        final Button btCadUsuario = (Button) findViewById(R.id.btCadUsuario);

        btHome.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent (CadastroUsuarioActivity.this , MainActivity.class);
                startActivity(intent);
            }
        });


       btCadUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(senha.length() < 4 || confirmarSenha.length() < 4){

                    Toast.makeText(CadastroUsuarioActivity.this, "A senha deve conter no mínimo 4 caracteres", Toast.LENGTH_LONG).show();

                }else if(!email.getText().toString().contains("@") || !email.getText().toString().contains(".")){

                    Toast.makeText(CadastroUsuarioActivity.this, "Insira um e-mail válido", Toast.LENGTH_LONG).show();

                } else if (!senha.getText().toString().equals(confirmarSenha.getText().toString())) {

                    Toast.makeText(CadastroUsuarioActivity.this, "As senhas informadas não coincidem.", Toast.LENGTH_SHORT).show();

                }else{

                    if(!nome.getText().toString().isEmpty() && !email.getText().toString().isEmpty() && !idioma.toString().isEmpty()){


                        if(!usuarioDAO.validaEmail(email.getText().toString())){

                            Usuario usuario = new Usuario();

                            usuario.setNome(nome.getText().toString());
                            usuario.setEmail(email.getText().toString());
                            usuario.setIdioma(idioma.toString());
                            usuario.setSenha(senha.getText().toString());

                            usuarioDAO.inserir(usuario);

                            Intent intent = new Intent(CadastroUsuarioActivity.this, ListarUsuarios.class);
                            finish();
                            startActivity(intent);

                            Toast.makeText(CadastroUsuarioActivity.this, "Usuário cadastrado com sucesso.", Toast.LENGTH_LONG).show();

                        }else{

                            Toast.makeText(CadastroUsuarioActivity.this, "O e-mail informado já está cadastrado.", Toast.LENGTH_SHORT).show();

                        }


                    } else {

                        Toast.makeText(CadastroUsuarioActivity.this, "Por gentileza preencher todos os campos", Toast.LENGTH_SHORT).show();

                    }



                    //Toast.makeText(CadastroUsuarioActivity.this, "Aluno inserido com o ID " + id, Toast.LENGTH_SHORT).show();

                    //Intent intent = new Intent(CadastroUsuarioActivity.this, ListarUsuarios.class);
                    //startActivity(intent);
                }

            }
        });



    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}

