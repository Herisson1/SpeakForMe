package herisson.application.br.speakforme.Activity;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import herisson.application.br.speakforme.DAO.ExpressaoDAO;
import herisson.application.br.speakforme.DAO.IdiomaDAO;
import herisson.application.br.speakforme.Idioma;
import herisson.application.br.speakforme.R;


/**
 * Classe de edição dos Idiomas
 * @author Herisson
 *
*/
public class EdicaoIdiomas extends AppCompatActivity implements AdapterView.OnItemSelectedListener{


    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;

    private IdiomaDAO idiomaDAO;
    private ExpressaoDAO expressaoDAO;
    private EditText atualizarIdioma;

    @Override
    protected void onCreate(Bundle savedInstanceStated) {
        super.onCreate(savedInstanceStated);
        setContentView(R.layout.activity_edit_idiomas);

        idiomaDAO = new IdiomaDAO(this);
        expressaoDAO = new ExpressaoDAO(this);

        atualizarIdioma = (EditText) findViewById(R.id.txtAtualizarIdioma);

        final Button btAlterarIdioma = (Button) findViewById(R.id.btAlterarIdioma);
        final Button btExcluirIdioma = (Button) findViewById(R.id.btExcluirIdioma);

        // Spinner List

        final Spinner spinnerIdioma = findViewById(R.id.spinnerIdiomas);

        // Idioma

        ArrayList<String> arrayIdiomas = idiomaDAO.idiomaList();

        ArrayAdapter<CharSequence> adapterIdioma = new ArrayAdapter(this, android.R.layout.simple_spinner_item, arrayIdiomas);
        adapterIdioma.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerIdioma.setAdapter(adapterIdioma);

        // Action Bar & Navigation Bar

        drawerLayout = (DrawerLayout)findViewById(R.id.drawerLayout);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        btAlterarIdioma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Idioma idioma = new Idioma();

                if (spinnerIdioma.getSelectedItemId() >= 0) {

                    if (!idiomaDAO.validaIdioma(atualizarIdioma)) {

                        String idiomaEscolhido;

                        idioma.setId(spinnerIdioma.getSelectedItemId());
                        idioma.setNome(atualizarIdioma.getText().toString());

                        idiomaEscolhido = spinnerIdioma.getSelectedItem().toString();

                        long id = idiomaDAO.atualizar(idioma, idiomaEscolhido);

                        Toast.makeText(EdicaoIdiomas.this, "Idioma '" + spinnerIdioma.getSelectedItem().toString() + "' atualizado para '" + atualizarIdioma.getText().toString() + "'", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(EdicaoIdiomas.this, "Idioma já existente.", Toast.LENGTH_LONG).show();
                    }


                }else{

                    Toast.makeText(EdicaoIdiomas.this, "Não existem registros a serem alterados nesse momento.", Toast.LENGTH_LONG).show();

                }
            }
        });

        btExcluirIdioma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (spinnerIdioma.getSelectedItemId() >= 0) {

                    AlertDialog.Builder alert = new AlertDialog.Builder(EdicaoIdiomas.this);
                    alert.setMessage("Você deseja excluir o idioma informado? As expressões vínculadas serão excluídas.");
                    alert.setCancelable(false);

                    alert.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            final Idioma idioma = new Idioma();

                            idioma.setId(spinnerIdioma.getSelectedItemId());
                            idioma.setNome(spinnerIdioma.getSelectedItem().toString());

                            long id = idiomaDAO.deletar(idioma);

                            if(expressaoDAO.validaExpressao(idioma)){

                                expressaoDAO.deletarCascata(idioma);

                            }

                            Intent intent = getIntent();
                            finish();
                            startActivity(intent);

                            Toast.makeText(EdicaoIdiomas.this, "Idioma '" + spinnerIdioma.getSelectedItem().toString().toLowerCase() + "' deletado", Toast.LENGTH_LONG).show();

                        }
                    });

                    alert.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });

                    alert.create().show();

                }else{
                    Toast.makeText(EdicaoIdiomas.this, "Não existem registros a serem excluídos nesse momento.", Toast.LENGTH_LONG).show();
                }
            }
        });




    }

    // Spinner Item

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.drawermenu, menu);

        return true;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
       // String text = parent.getItemAtPosition(position).toString();
       // Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    // Action Bar & Navigation Bar

    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        if(toggle.onOptionsItemSelected(item)){

            return true;

        }

        return super.onOptionsItemSelected(item);
    }

}
