package herisson.application.br.speakforme.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import herisson.application.br.speakforme.DAO.UsuarioDAO;
import herisson.application.br.speakforme.R;

public class MainActivity extends AppCompatActivity {

    /*
    *
    *  TODO Alterar o hint/text HardCode dos XML para Strings
    *
    *  Alterar os HardCode para Strings
    *
    */


    private UsuarioDAO usuarioDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btSignUp = (Button) findViewById(R.id.btSignUp);
        Button btEntrar = (Button) findViewById(R.id.btEntrar);

        usuarioDAO = new UsuarioDAO(this);

        btSignUp.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent (MainActivity.this , CadastroUsuarioActivity.class);
                startActivity(intent);
            }
        });

        btEntrar.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){

                TextView loginSignIn = (TextView) findViewById(R.id.loginSignIn);
                TextView senhaSignIn = (TextView) findViewById(R.id.senhaSignIn);

                String login = loginSignIn.getText().toString();
                String senha = senhaSignIn.getText().toString();

                if(usuarioDAO.validaSenha(login).equals(senha)) {

                    if(login.equals("herisson@speakforme.com") || login.equals("fernando@speakforme.com") || login.equals("juliane@speakforme.com")){
                        Intent intent = new Intent(MainActivity.this, HomeAdmin.class);
                        finish();
                        startActivity(intent);

                        Toast.makeText(MainActivity.this, "BEM VINDO(A) "+usuarioDAO.retornaNome(login), Toast.LENGTH_LONG).show();

                    }else{
                        Intent intent = new Intent(MainActivity.this, HomeUser.class);
                        finish();
                        startActivity(intent);

                        Toast.makeText(MainActivity.this, "BEM VINDO(A) "+usuarioDAO.retornaNome(login), Toast.LENGTH_LONG).show();

                    }

                }else{
                    Toast.makeText(MainActivity.this, "Usuário ou senha inválidos", Toast.LENGTH_SHORT).show();
                }
            }

        });

    }




}
