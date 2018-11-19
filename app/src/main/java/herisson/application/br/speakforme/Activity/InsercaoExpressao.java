package herisson.application.br.speakforme.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
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
import herisson.application.br.speakforme.R;

public class InsercaoExpressao extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ActionBarDrawerToggle toggle;

    private IdiomaDAO idiomaDAO;
    private ExpressaoDAO expressaoDAO;

    private EditText nome;

    @Override
    public void onCreate(Bundle onSavedInstanceState){
        super.onCreate(onSavedInstanceState);
        setContentView(R.layout.activity_cad_expressoes);

        expressaoDAO = new ExpressaoDAO(this);
        idiomaDAO = new IdiomaDAO(this);

        nome = (EditText) findViewById(R.id.textExpressao);

        final Button btCadastrarExpressao = (Button) findViewById(R.id.btCadastrarExpressao);

        final Spinner spinnerIdiomas = (Spinner) findViewById(R.id.spinnerIdiomas);
        final Spinner spinnerCategoria = (Spinner) findViewById(R.id.spinnerCategorias);

        // Spinner Idiomas

        ArrayList<String> arrayIdiomas = idiomaDAO.idiomaList();
        ArrayAdapter adapterIdiomas = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, arrayIdiomas);
        adapterIdiomas.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerIdiomas.setAdapter(adapterIdiomas);

        // Spinner Categoria

        ArrayAdapter<CharSequence> adapterCategoria = ArrayAdapter.createFromResource(this, R.array.Categoria, android.R.layout.simple_spinner_item);
        adapterCategoria.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategoria.setAdapter(adapterCategoria);
        /*spinnerCategoria.setOnItemSelectedListener(this);*/


        // Action Bar & Navigation Bar

        drawerLayout = (DrawerLayout)findViewById(R.id.drawerLayout);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        navigationView = (NavigationView) findViewById(R.id.navView);
        navigationView.setNavigationItemSelectedListener(this);


        btCadastrarExpressao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!nome.getText().toString().isEmpty()) {
                    Expressao expressao = new Expressao();

                    expressao.setIdioma(spinnerIdiomas.getSelectedItem().toString());
                    expressao.setCategoria(spinnerCategoria.getSelectedItem().toString());
                    expressao.setNome(nome.getText().toString());

                    if(!expressaoDAO.validaExpressao(expressao)){

                        expressaoDAO.inserir(expressao);

                        Intent intent = getIntent();
                        finish();
                        startActivity(intent);

                        Toast.makeText(InsercaoExpressao.this, "Expressao inserida com sucesso.", Toast.LENGTH_LONG).show();

                    }else{
                        Toast.makeText(InsercaoExpressao.this, "A expressão informada já existe para esse Idioma e Categoria.", Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Toast.makeText(InsercaoExpressao.this, "Por gentileza inserir uma expressão válida.",Toast.LENGTH_LONG).show();
                }
            }
        });

    }



   /* @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }*/

    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        if(toggle.onOptionsItemSelected(item)){

            return true;

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()){
            case R.id.menuInserirIdioma:{
                Toast.makeText(this, "Inserir idioma", Toast.LENGTH_LONG).show();
                break;
            }
            case R.id.menuInserirExpressao:{
                Toast.makeText(this, "Inserir Expressao", Toast.LENGTH_LONG).show();
                break;
            }
            case R.id.menuEditIdioma:{
                Toast.makeText(this, "Altera idioma", Toast.LENGTH_LONG).show();
                break;
            }
            case R.id.menuEditExpressao:{
                Toast.makeText(this, "Alterar expressao", Toast.LENGTH_LONG).show();
                break;
            }
            default:{
                Toast.makeText(this, "Menu padrão", Toast.LENGTH_LONG).show();
                break;
            }
        }

        drawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}

