package p3l_8980.com.atmaauto.UI;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import p3l_8980.com.atmaauto.R;

public class AddTransaction extends AppCompatActivity {

    ImageView backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_transaction);
        init();
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent = new Intent(AddTransaction.this, Customer.class);
                intent.putExtra("addDialog", 4);
                startActivity(intent);
            }
        });
    }
    private void init(){
        backButton = findViewById(R.id.btnBack);
    }
}
