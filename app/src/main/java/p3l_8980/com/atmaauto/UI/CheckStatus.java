package p3l_8980.com.atmaauto.UI;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import p3l_8980.com.atmaauto.Controller.ApiClient;
import p3l_8980.com.atmaauto.Controller.Detail;
import p3l_8980.com.atmaauto.Controller.MotorcycleBrand;
import p3l_8980.com.atmaauto.Controller.MotorcycleBrandList;
import p3l_8980.com.atmaauto.Controller.MotorcycleData;
import p3l_8980.com.atmaauto.Controller.ProcurementDetail;
import p3l_8980.com.atmaauto.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CheckStatus extends AppCompatActivity {

    Button checkButton;
    EditText license_number,phone_number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_status);
        init();
        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                store();
            }
        });
    }

    private void init(){
        license_number = findViewById(R.id.motorLicenseNumber);
        phone_number = findViewById(R.id.customerNumber);
        checkButton = findViewById(R.id.btnCheck);
    }

    public void store(){
        if(license_number.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "plat nomor tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }
        else if(phone_number.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "nomor telepon tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }
        else {
            final Intent intent = new Intent(CheckStatus.this, HistoryTransaction.class);
            intent.putExtra("license_number", license_number.getText().toString());
            intent.putExtra("phone_number", phone_number.getText().toString());
            startActivity(intent);
        }
    }
}
