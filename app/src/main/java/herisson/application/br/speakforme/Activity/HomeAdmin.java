package herisson.application.br.speakforme.Activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.NavigationView.OnNavigationItemSelectedListener;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import herisson.application.br.speakforme.R;

public class HomeAdmin extends AppCompatActivity implements OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_adm);;


        CardView cvEdicaoIdioma = (CardView) findViewById(R.id.cvEdicaoIdioma);
        CardView cvEdicaoExpressao = (CardView) findViewById(R.id.cvEdicaoExpressao);
        CardView cvInsercaoIdioma = (CardView) findViewById(R.id.cvInsercaoIdioma);
        CardView cvInsercaoExpressao = (CardView) findViewById(R.id.cvInsercaoExpressao);

        cvEdicaoIdioma.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent (HomeAdmin.this , EdicaoIdiomas.class);
                startActivity(intent);
            }
        });

        cvEdicaoExpressao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeAdmin.this, EdicaoExpressao.class);
                startActivity(intent);
            }
        });

        cvInsercaoIdioma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (HomeAdmin.this, InsercaoIdiomas.class);
                startActivity(intent);
            }
        });

        cvInsercaoExpressao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (HomeAdmin.this, InsercaoExpressao.class);
                startActivity(intent);
            }
        });


        drawerLayout = (DrawerLayout)findViewById(R.id.drawerLayout);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }

    @Override
    public void onBackPressed(){
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawerLayout);

        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        if(toggle.onOptionsItemSelected(item)){
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu){

        getMenuInflater().inflate(R.menu.drawermenu, menu);
        return true;
    }
    */

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item){

        int id = item.getItemId();

        if(id == R.id.menuInserirIdioma){
            Intent intent = new Intent(HomeAdmin.this, InsercaoIdiomas.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawerLayout);
        drawer.closeDrawer(GravityCompat.START);
        return true;



    }
}
