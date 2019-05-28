package p3l_8980.com.atmaauto.UI;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import okhttp3.ResponseBody;
import p3l_8980.com.atmaauto.Controller.ApiClient;
import p3l_8980.com.atmaauto.Controller.Sparepart;
import p3l_8980.com.atmaauto.Controller.SparepartList;
import p3l_8980.com.atmaauto.Controller.Supplier;
import p3l_8980.com.atmaauto.Controller.SupplierData;
import p3l_8980.com.atmaauto.Controller.SupplierList;
import p3l_8980.com.atmaauto.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UpdateVerification extends AppCompatActivity {

    ImageView backButton;
    Button addButton, editButton, sparepartButton;
    EditText procurementDate,amount,sell_price,purchase_price;
    Spinner supplier,sales,sparepart,status;
    TextView title;
    Calendar myCalendar;
    int idsupplier,idsales;
    String idSparepart;
    private int simpan = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_verification);
        init();
        amount.setText(String.valueOf(getIntent().getIntExtra("amount",0)));
        sell_price.setText(String.valueOf(getIntent().getDoubleExtra("sell_price",0)));
        purchase_price.setText(String.valueOf(getIntent().getDoubleExtra("purchase_price",0)));
        sparepartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input();
            }
        });
    }

    public void input() {
        try {
            final ProgressDialog progressDialog = new ProgressDialog(UpdateVerification.this, R.style.AppTheme_Dark_Dialog);
            progressDialog.setMessage("Memproses Data...");
            progressDialog.show();
            Retrofit retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl("http://192.168.19.140/8991/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            ApiClient apiClient = retrofit.create(ApiClient.class);

            Call<ResponseBody> verifSparepart = apiClient.updateSparepartVerif(getIntent().getStringExtra("id_sparepart"),Double.parseDouble(sell_price.getText().toString()),Double.parseDouble(purchase_price.getText().toString()),Integer.parseInt(amount.getText().toString()));

            verifSparepart.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                    JSONObject jsonRes = new JSONObject(response.body().string());
//                    String iddetailprocurement =  jsonRes.getJSONObject("data").getString("id_procurement_detail");
                    Log.d("verifs", "masuuk");

                    Retrofit retrofit = new retrofit2.Retrofit.Builder()
                            .baseUrl("http://192.168.19.140/8991/api/")
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

                    ApiClient apiClient = retrofit.create(ApiClient.class);
                    Log.d("id_proc",String.valueOf(Double.parseDouble(purchase_price.getText().toString())));
                    Call<ResponseBody> updateProcurementDetail = apiClient.updateProcurementDetail(getIntent().getIntExtra("id_procurement_detail",0),Double.parseDouble(purchase_price.getText().toString())*Integer.parseInt(amount.getText().toString()),Double.parseDouble(purchase_price.getText().toString()),Integer.parseInt(amount.getText().toString()));

                    updateProcurementDetail.enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            try {
                                JSONObject jsonRes = new JSONObject(response.body().string());
                                String iddetailprocurement =  jsonRes.getJSONObject("data").getString("id_procurement_detail");
                                Log.d("idprocurement", iddetailprocurement);
                                progressDialog.dismiss();
                                Toast.makeText(getApplicationContext(), "Berhasil Input Data", Toast.LENGTH_SHORT).show();
                                final Intent intent = new Intent(UpdateVerification.this, VerificationProcurement.class);
                                intent.putExtra("addDialog", 1);
                                intent.putExtra("id_procurement",getIntent().getIntExtra("id_procurement",0));
                                startActivity(intent);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(), "Gagal Input Data", Toast.LENGTH_SHORT).show();
                        }
                    });

//                    progressDialog.dismiss();
//                    Toast.makeText(getApplicationContext(), "Berhasil Input Data", Toast.LENGTH_SHORT).show();
//                    final Intent intent = new Intent(UpdateVerification.this, VerificationProcurement.class);
//                    intent.putExtra("addDialog", 1);
//                    startActivity(intent);
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    progressDialog.dismiss();
                    Toast.makeText(getApplicationContext(), "Gagal Input Data", Toast.LENGTH_SHORT).show();
                }
            });
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void init(){
        backButton = findViewById(R.id.btnBack);
        editButton = findViewById(R.id.btnEdit);
        sell_price = findViewById(R.id.sell_price);
        purchase_price = findViewById(R.id.purchase_price);
        title = findViewById(R.id.title);
        amount =  findViewById(R.id.amount);
        sparepart = findViewById(R.id.sparepart);
        sparepartButton = findViewById(R.id.btnInputSparepart);

        Retrofit retrofit = new retrofit2.Retrofit.Builder()
                .baseUrl("http://192.168.19.140/8991/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiClient apiClient2 = retrofit.create(ApiClient.class);
        Call<SparepartList> call2 = apiClient2.getSpareparts();
        call2.enqueue(new Callback<SparepartList>() {
            @Override
            public void onResponse(Call<SparepartList> call, Response<SparepartList> response) {
                ArrayList<Sparepart> spareparts = new ArrayList<>();
                for (int i = 0; i < response.body().getData().size(); i++) {
                    spareparts.add(new Sparepart(response.body().getData().get(i).getIdSparepart(),response.body().getData().get(i).getSparepartName().concat("-").concat(response.body().getData().get(i).getMerk())));
                }

                ArrayAdapter<Sparepart> adapter = new ArrayAdapter<Sparepart>(getApplicationContext(),
                        android.R.layout.simple_spinner_dropdown_item
                        , spareparts);

                sparepart.setAdapter(adapter);
                Log.d("items",sparepart.getItemAtPosition(0).toString());

                sparepart.setSelection(getIndex(sparepart, getIntent().getStringExtra("sparepart_name")));


            }

            @Override
            public void onFailure(Call<SparepartList> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Data Dropdown gagal diambil", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private int getIndex(Spinner spinner, String myString){
        for (int i=0;i<spinner.getCount();i++){
            if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(myString)){

                return i;
            }
        }

        return 0;
    }
}
