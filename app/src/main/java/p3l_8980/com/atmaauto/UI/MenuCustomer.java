package p3l_8980.com.atmaauto.UI;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import p3l_8980.com.atmaauto.R;

public class MenuCustomer extends AppCompatActivity {

    LinearLayout oldCustomer, newCustomer;
    ImageView backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_customer);
        init();
        newCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent = new Intent(MenuCustomer.this, AddCustomer.class);
                intent.putExtra("addDialog", 4);
                startActivity(intent);
            }
        });

        oldCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent = new Intent(MenuCustomer.this, CustomerOld.class);
                intent.putExtra("addDialog", 4);
                startActivity(intent);
            }
        });
    }

    private void init(){
        oldCustomer = findViewById(R.id.oldCustomer);
        newCustomer = findViewById(R.id.newCustomer);
        backButton = findViewById(R.id.btnBack);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuCustomer.this, Beranda.class);
                intent.putExtra("addDialog", 4);
                startActivity(intent);
            }
        });
    }
}
