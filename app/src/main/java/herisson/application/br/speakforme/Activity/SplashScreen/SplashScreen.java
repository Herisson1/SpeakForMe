package herisson.application.br.speakforme.Activity.SplashScreen;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import herisson.application.br.speakforme.Activity.MainActivity;
import herisson.application.br.speakforme.R;
import pl.droidsonroids.gif.GifImageView;

public class SplashScreen extends AppCompatActivity{

    private GifImageView gifImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Thread timer = new Thread() {
            public void run() {
                try {
                    sleep(2000); // Duração da tela - 2000 = 2s
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                    startActivity(intent);
                }
            }

        };

        timer.start();
    }


    @Override
    public void onPause(){
        super.onPause();
        finish();
    }

}
