package p3l_8980.com.atmaauto.UI;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import p3l_8980.com.atmaauto.Controller.ApiClient;
import p3l_8980.com.atmaauto.Controller.Sparepart;
import p3l_8980.com.atmaauto.Controller.SparepartData;
import p3l_8980.com.atmaauto.Controller.Supplier;
import p3l_8980.com.atmaauto.Controller.SupplierData;
import p3l_8980.com.atmaauto.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddSparepart extends AppCompatActivity {

    ImageView backButton;
    Button addButton;
    EditText idSparepart, sparepartName, merk, stock, minStock, purchasePrice, sellPrice, placement, position, place, number, sparepartTypeName, idSparepartType;
    TextView title;
    private List<Sparepart> SparepartBundle = new ArrayList<>();
    private int simpan = -1;
    private String cek;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sparepart);
        init();
        simpan = getIntent().getIntExtra("simpan",-1);

        if(simpan > -1){
//            final Supplier data = SupplierBundle.get(simpan);
            title.setText("Ubah Supplier");
            idSparepart.setText(getIntent().getStringExtra("id"));
            sparepartName.setText(getIntent().getStringExtra("name"));
            merk.setText(getIntent().getStringExtra("merk"));
            stock.setText(getIntent().getStringExtra("stock"));
            minStock.setText(getIntent().getStringExtra("minstock"));
            purchasePrice.setText(getIntent().getStringExtra("purchaseprice"));
            sellPrice.setText(getIntent().getStringExtra("sellprice"));
            placement.setText(getIntent().getStringExtra("placement"));
            position.setText(getIntent().getStringExtra("position"));
            place.setText(getIntent().getStringExtra("place"));
            number.setText(getIntent().getStringExtra("number"));
            sparepartTypeName.setText(getIntent().getStringExtra("type"));
            idSparepartType.setText(getIntent().getStringExtra("idtype"));
        }

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent = new Intent(AddSparepart.this, Beranda.class);
                intent.putExtra("addDialog", 1);
                startActivity(intent);
            }
        });

        if(simpan > -1){
            addButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    update();
                }
            });
        }else{
            addButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    store();
                }
            });
        }


    }

    private void init(){
        backButton = findViewById(R.id.btnBack);
        addButton = findViewById(R.id.btnAdd);
        idSparepart = findViewById(R.id.idSparepart);
        sparepartName = findViewById(R.id.sparepartName);
        merk = findViewById(R.id.merk);
        stock = findViewById(R.id.stock);
        minStock = findViewById(R.id.minStock);
        purchasePrice = findViewById(R.id.purchasePrice);
        sellPrice = findViewById(R.id.sellPrice);
        placement = findViewById(R.id.placement);
        position = findViewById(R.id.position);
        place = findViewById(R.id.place);
        number = findViewById(R.id.number);
        sparepartTypeName = findViewById(R.id.sparepartTypeName);
        idSparepartType = findViewById(R.id.idSparepartType);
        title = findViewById(R.id.title);
    }

    public void store(){
        if(idSparepart.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "id sparepart tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }
        else if(sparepartName.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "nama sparepart tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }
        else if(merk.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "merk sparepart tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }
        else if(stock.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "stok sparepart tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }
        else if(minStock.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "stok minimal sparepart tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }
        else if(purchasePrice.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "harga beli sparepart tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }
        else if(sellPrice.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "harga jual sparepart tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }
        else if(placement.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "penempatan sparepart tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }
        else if(position.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "posisi sparepart tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }
        else if(place.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "tempat sparepart tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }
        else if(number.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "nomor sparepart tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }
        else if(sparepartTypeName.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "tipe sparepart tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }
        else if(idSparepartType.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "id tipe sparepart tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }
        else {
            try {
                final ProgressDialog progressDialog = new ProgressDialog(AddSparepart.this, R.style.AppTheme_Dark_Dialog);
                progressDialog.setMessage("Memproses Data...");
                progressDialog.show();
                Retrofit retrofit = new retrofit2.Retrofit.Builder()
                        .baseUrl("https://p3l.yafetrakan.com/api/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                ApiClient apiClient = retrofit.create(ApiClient.class);

                Call<SparepartData> addSparepart = apiClient.addSparepart(idSparepart.getText().toString(), sparepartName.getText().toString(), merk.getText().toString(), stock.getText().toString(), minStock.getText().toString(), purchasePrice.getText().toString(), sellPrice.getText().toString(), placement.getText().toString(), position.getText().toString(), place.getText().toString(), number.getText().toString(), sparepartTypeName.getText().toString(), idSparepartType.getText().toString());

                addSparepart.enqueue(new Callback<SparepartData>() {
                    @Override
                    public void onResponse(Call<SparepartData> call, Response<SparepartData> response) {
                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(), "Berhasil Input Data", Toast.LENGTH_SHORT).show();
                        final Intent intent = new Intent(AddSparepart.this, Beranda.class);
                        intent.putExtra("addDialog", 1);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<SparepartData> call, Throwable t) {
                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(), "Gagal Input Data", Toast.LENGTH_SHORT).show();
                    }
                });
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void update(){
        if(idSparepart.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "id sparepart tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }
        else if(sparepartName.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "nama sparepart tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }
        else if(merk.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "merk sparepart tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }
        else if(stock.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "stok sparepart tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }
        else if(minStock.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "stok minimal sparepart tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }
        else if(purchasePrice.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "harga beli sparepart tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }
        else if(sellPrice.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "harga jual sparepart tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }
        else if(placement.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "penempatan sparepart tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }
        else if(position.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "posisi sparepart tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }
        else if(place.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "tempat sparepart tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }
        else if(number.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "nomor sparepart tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }
        else if(sparepartTypeName.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "tipe sparepart tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }
        else if(idSparepartType.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "id tipe sparepart tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }
        else {
            try {
                final ProgressDialog progressDialog = new ProgressDialog(AddSparepart.this, R.style.AppTheme_Dark_Dialog);
                progressDialog.setMessage("Memproses Data...");
                progressDialog.show();
                Retrofit retrofit = new retrofit2.Retrofit.Builder()
                        .baseUrl("https://p3l.yafetrakan.com/api/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                ApiClient apiClient = retrofit.create(ApiClient.class);

                Call<SparepartData> updateSparepart = apiClient.updateSparepart(idSparepart.getText().toString(), sparepartName.getText().toString(), merk.getText().toString(), stock.getText().toString(), minStock.getText().toString(), purchasePrice.getText().toString(), sellPrice.getText().toString(), placement.getText().toString(), position.getText().toString(), place.getText().toString(), number.getText().toString(), sparepartTypeName.getText().toString(), idSparepartType.getText().toString());

                updateSparepart.enqueue(new Callback<SparepartData>() {
                    @Override
                    public void onResponse(Call<SparepartData> call, Response<SparepartData> response) {
                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(), "Berhasil Input Data", Toast.LENGTH_SHORT).show();
                        final Intent intent = new Intent(AddSparepart.this, Beranda.class);
                        intent.putExtra("addDialog", 1);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<SparepartData> call, Throwable t) {
                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(), "Gagal Input Data", Toast.LENGTH_SHORT).show();
                    }
                });
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
