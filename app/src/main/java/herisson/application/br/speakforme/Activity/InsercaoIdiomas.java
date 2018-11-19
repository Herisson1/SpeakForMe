package herisson.application.br.speakforme.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import herisson.application.br.speakforme.DAO.IdiomaDAO;
import herisson.application.br.speakforme.Idioma;
import herisson.application.br.speakforme.R;

public class InsercaoIdiomas extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


    // Variáveis
    private EditText nome;
    private IdiomaDAO idiomaDAO;


    // Action Bar
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad_idiomas);

        nome = (EditText) findViewById(R.id.textIdioma);

        idiomaDAO = new IdiomaDAO(this);

        // Action Bar & Navigation Bar

        drawerLayout = (DrawerLayout)findViewById(R.id.drawerLayout);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final Button btCadastrarIdioma = (Button) findViewById(R.id.btCadastrarIdioma);

        btCadastrarIdioma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Idioma idioma = new Idioma();

                 if(!nome.getText().toString().isEmpty()) {

                     if(!idiomaDAO.validaIdioma(nome)){

                         idioma.setNome(nome.getText().toString());

                         idiomaDAO.inserir(idioma);

                         Toast.makeText(InsercaoIdiomas.this, "Idioma inserido com sucesso.", Toast.LENGTH_SHORT).show();

                     }else{
                         Toast.makeText(InsercaoIdiomas.this, "O idioma informado já existe.", Toast.LENGTH_SHORT).show();
                     }

                }else{
                    Toast.makeText(InsercaoIdiomas.this, "Por gentileza inserir um idioma válido.", Toast.LENGTH_SHORT).show();
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        if(toggle.onOptionsItemSelected(item)){

            return true;

        }

        return super.onOptionsItemSelected(item);
    }

}
