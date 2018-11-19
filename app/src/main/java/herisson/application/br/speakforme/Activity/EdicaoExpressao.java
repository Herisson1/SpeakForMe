package herisson.application.br.speakforme.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
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
import herisson.application.br.speakforme.Expressao;
import herisson.application.br.speakforme.Idioma;
import herisson.application.br.speakforme.R;

public class EdicaoExpressao extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;

    private IdiomaDAO idiomaDAO;
    private ExpressaoDAO expressaoDAO;

    private EditText nome;

    @Override
    protected void onCreate(Bundle savedInstanceStated) {
        super.onCreate(savedInstanceStated);
        setContentView(R.layout.activity_edit_expressoes);

        idiomaDAO = new IdiomaDAO(this);
        expressaoDAO = new ExpressaoDAO(this);

        nome = (EditText) findViewById(R.id.txtAtualizarExpressao);


        final Button btAlterarExpressao = (Button) findViewById(R.id.btAlterarExpressao);
        final Button btExcluirExpressao = (Button) findViewById(R.id.btExcluirExpressao);

        // Spinner List

        final Spinner spinnerIdioma = findViewById(R.id.spinnerIdiomas);
        final Spinner spinnerExpressao = findViewById(R.id.spinnerExpressao);
        final Spinner spinnerCategoria = findViewById(R.id.spinnerCategoria);

        // Idioma

        ArrayList<String> arrayIdiomas = idiomaDAO.idiomaList();

        ArrayAdapter<CharSequence> adapterIdioma = new ArrayAdapter(this, android.R.layout.simple_spinner_item, arrayIdiomas);
        adapterIdioma.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerIdioma.setAdapter(adapterIdioma);

        // Expressão



        ArrayList<String> arrayExpressoes = expressaoDAO.expressaoList();

        ArrayAdapter<CharSequence> adapterExpressao = new ArrayAdapter(this, android.R.layout.simple_spinner_item, arrayExpressoes);
        adapterExpressao.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerExpressao.setAdapter(adapterExpressao);
        spinnerExpressao.setOnItemSelectedListener(this);

        // Categoria

        ArrayAdapter<CharSequence> adapterCategoria = ArrayAdapter.createFromResource(this, R.array.Categoria, android.R.layout.simple_spinner_item);
        adapterCategoria.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategoria.setAdapter(adapterCategoria);
        spinnerCategoria.setOnItemSelectedListener(this);



        // Action Bar & Navigation Bar

        drawerLayout = (DrawerLayout)findViewById(R.id.drawerLayout);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        /*NavigationView navigationView = (NavigationView)*/


        btAlterarExpressao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (spinnerIdioma.getSelectedItemId() >= 0 & spinnerCategoria.getSelectedItemId() >= 0 & spinnerExpressao.getSelectedItemId() >= 0) {

                    if (!nome.getText().toString().isEmpty()) {

                        String expressaoEscolhida;

                        Expressao expressao = new Expressao();

                        expressao.setIdioma(spinnerIdioma.getSelectedItem().toString());
                        expressao.setCategoria(spinnerCategoria.getSelectedItem().toString());
                        expressao.setNome(nome.getText().toString());

                        expressaoEscolhida = spinnerExpressao.getSelectedItem().toString();

                        if (!expressaoDAO.validaExpressao(expressao)) {

                            expressaoDAO.atualizar(expressao, expressaoEscolhida);

                            Toast.makeText(EdicaoExpressao.this, "Expressao '" + spinnerExpressao.getSelectedItem().toString() + "' atualizado para '" + nome.getText().toString() + "'", Toast.LENGTH_SHORT).show();

                        } else {

                            Toast.makeText(EdicaoExpressao.this, "Expressão já existente", Toast.LENGTH_SHORT).show();

                        }
                    } else {

                        Toast.makeText(EdicaoExpressao.this, "Por gentileza inserir uma expressão válida", Toast.LENGTH_SHORT).show();

                    }

                }else{

                    Toast.makeText(EdicaoExpressao.this, "Não existem registros a serem alterados nesse momento, ou os dados selecionados não coincidem", Toast.LENGTH_LONG).show();
                }
            }
        });

        btExcluirExpressao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (spinnerIdioma.getSelectedItemId() >= 0 & spinnerCategoria.getSelectedItemId() >= 0 & spinnerExpressao.getSelectedItemId() >= 0) {

                    AlertDialog.Builder alert = new AlertDialog.Builder(EdicaoExpressao.this);
                    alert.setMessage("Você deseja excluir a expressão informada?");
                    alert.setCancelable(false);

                    alert.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            Expressao expressao = new Expressao();

                            expressao.setIdioma(spinnerIdioma.getSelectedItem().toString());
                            expressao.setCategoria(spinnerCategoria.getSelectedItem().toString());
                            expressao.setNome(spinnerExpressao.getSelectedItem().toString());

                            if (!expressaoDAO.validaExpressao(expressao)) {

                                expressaoDAO.deletar(expressao);

                                Intent intent = getIntent();
                                finish();
                                startActivity(intent);

                                Toast.makeText(EdicaoExpressao.this, "Expressao '" + spinnerExpressao.getSelectedItem().toString().toLowerCase() + "' deletado", Toast.LENGTH_LONG).show();

                            } else {

                                Toast.makeText(EdicaoExpressao.this, "Expressão não existente para esse Idioma e/ou Categoria", Toast.LENGTH_LONG).show();

                            }

                        }
                    });

                    alert.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });

                    alert.create().show();

                } else {
                    Toast.makeText(EdicaoExpressao.this, "Não existem registros a serem excluidos nesse momento.", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    // Spinner Item

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
