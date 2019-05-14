package p3l_8980.com.atmaauto.UI;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import p3l_8980.com.atmaauto.R;

public class HomePengguna extends AppCompatActivity {

    ImageView motorHome, sparepartHome, btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_pengguna);

        init();
    }

    private void init(){
        motorHome = findViewById(R.id.motorHome);
        sparepartHome = findViewById(R.id.sparepartHome);
        btnBack = findViewById(R.id.btnBack);

        sparepartHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePengguna.this, HomeSparepart.class);
                startActivity(intent);
            }
        });

        motorHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePengguna.this, CheckStatus.class);
                startActivity(intent);
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePengguna.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
