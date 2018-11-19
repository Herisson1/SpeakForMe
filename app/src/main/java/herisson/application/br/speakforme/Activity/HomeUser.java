package herisson.application.br.speakforme.Activity;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.lang.reflect.Array;
import java.util.ArrayList;

import herisson.application.br.speakforme.DAO.ExpressaoDAO;
import herisson.application.br.speakforme.DAO.IdiomaDAO;
import herisson.application.br.speakforme.R;

import static herisson.application.br.speakforme.R.id.menuConfiguracoes;


public class HomeUser extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;

    private IdiomaDAO idiomaDAO;
    private ExpressaoDAO expressaoDAO;



    @Override
    public void onCreate(Bundle savedInstanceState ){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_user);

        Spinner spinnerIdiomaHome = findViewById(R.id.spinnerIdiomaHome);
        Spinner spinnerCategoriaHome = findViewById(R.id.spinnerCategoriaHome);

        idiomaDAO = new IdiomaDAO(this);
        expressaoDAO = new ExpressaoDAO(this);

        // Idioma

        ArrayList<String> arrayIdiomas = idiomaDAO.idiomaList();

        ArrayAdapter<CharSequence> adapterIdiomaHome = new ArrayAdapter(this, android.R.layout.simple_spinner_item, arrayIdiomas);
        adapterIdiomaHome.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerIdiomaHome.setAdapter(adapterIdiomaHome);
        spinnerIdiomaHome.setOnItemSelectedListener(this);


        // Categoria

        ArrayAdapter<CharSequence> adapterCategoriaHome = ArrayAdapter.createFromResource(this, R.array.Categoria, android.R.layout.simple_spinner_item);
        adapterCategoriaHome.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategoriaHome.setAdapter(adapterCategoriaHome);
        spinnerCategoriaHome.setOnItemSelectedListener(this);

        drawerLayout = (DrawerLayout)findViewById(R.id.drawerLayout);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        if(toggle.onOptionsItemSelected(item)){

            return true;

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
