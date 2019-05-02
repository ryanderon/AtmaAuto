package p3l_8980.com.atmaauto.UI;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import okhttp3.ResponseBody;
import p3l_8980.com.atmaauto.Controller.ApiClient;
import p3l_8980.com.atmaauto.Controller.Detail;
import p3l_8980.com.atmaauto.Controller.ProcurementDetail;
import p3l_8980.com.atmaauto.Controller.Sparepart;
import p3l_8980.com.atmaauto.Controller.SparepartList;
import p3l_8980.com.atmaauto.Controller.Supplier;
import p3l_8980.com.atmaauto.Controller.SupplierList;
import p3l_8980.com.atmaauto.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import java.util.List;

public class VerificationProcurement extends AppCompatActivity {

    ImageView backButton;
    Button addButton, editButton, sparepartButton;
    EditText procurementDate,amount,sell_price,purchase_price;
    Spinner supplier,sales,sparepart,status;
    TextView title;
    Calendar myCalendar;
    int idsupplier,idsales;
    private String idSparepart,status_procurement="Finish";
    private int simpan = -1;

    RecyclerView rview;
    private AdapterVerification adapter;
    private RecyclerView.LayoutManager layout;
    private List<Detail> DetailBundleFull;
    private List<ProcurementDetail> details = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification_procurement);
        init();

        Retrofit retrofit= new retrofit2.Retrofit.Builder()
                .baseUrl("https://p3l.yafetrakan.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiClient apiClient = retrofit.create(ApiClient.class);

        Call<Detail> procurementDetailGet = apiClient.getProcurementDetail(getIntent().getIntExtra("id_procurement",0));
        procurementDetailGet.enqueue(new Callback<Detail>() {
            @Override
            public void onResponse(Call<Detail> call, Response<Detail> response) {
                try {
                    adapter = new AdapterVerification(response.body().getData(),VerificationProcurement.this);
                    details = response.body().getData();
                    rview.setAdapter(adapter);
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Tidak Ada Procurement Detail!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Detail> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Fail", Toast.LENGTH_SHORT).show();
            }

        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verif();
            }
        });
    }

    private void  verif(){
        final ProgressDialog progressDialog = new ProgressDialog(VerificationProcurement.this, R.style.AppTheme_Dark_Dialog);
        progressDialog.setMessage("Memproses Data...");
        progressDialog.show();
        Retrofit retrofit = new retrofit2.Retrofit.Builder()
                .baseUrl("https://p3l.yafetrakan.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiClient apiClient = retrofit.create(ApiClient.class);
        Log.d("zz",String.valueOf(getIntent().getIntExtra("id_sales",0)));
        Call<ResponseBody> updateProcurement = apiClient.updateProcurement(getIntent().getIntExtra("id_procurement",0),getIntent().getStringExtra("date"),status_procurement,getIntent().getIntExtra("id_sales",0));

        updateProcurement.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                try {
//                    JSONObject jsonRes = new JSONObject(response.body().string());
//                    String idprocurement = jsonRes.getJSONObject("data").getString("id_procurement");
//                    Log.d("idprocurementz", idprocurement);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }

                for(int x=0;x<details.size();x++)
                {
                    Log.d("masuk", "masssuk");
                    Retrofit retrofit = new retrofit2.Retrofit.Builder()
                            .baseUrl("https://p3l.yafetrakan.com/api/")
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

                    ApiClient apiClient = retrofit.create(ApiClient.class);

                    Call<ResponseBody> addProcurementDetail = apiClient.addProcurementDetail(details.get(x).getPrice(),details.get(x).getAmount(),details.get(x).getSubtotal(),details.get(x).getIdSparepart(),getIntent().getIntExtra("id_procurement",0));

                    addProcurementDetail.enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            Log.d("successe", "hehehe");
                            try {
                                JSONObject jsonRes = new JSONObject(response.body().string());
                                String iddetailprocurement =  jsonRes.getJSONObject("data").getString("id_procurement_detail");
                                Log.d("idprocurement", iddetailprocurement);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                        }
                    });
                }

                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), "Verifikasi Data Berhasil", Toast.LENGTH_SHORT).show();
                final Intent intent = new Intent(VerificationProcurement.this, Beranda.class);
                intent.putExtra("addDialog", 3);
                startActivity(intent);


            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void init(){
        backButton = findViewById(R.id.btnBack);
        addButton = findViewById(R.id.btnAdd);
        editButton = findViewById(R.id.btnEdit);
//        sell_price = findViewById(R.id.sell_price);
//        purchase_price = findViewById(R.id.purchase_price);
//        title = findViewById(R.id.title);
//        amount =  findViewById(R.id.amount);
//        sparepart = findViewById(R.id.sparepart);
//        sparepartButton = findViewById(R.id.btnInputSparepart);
        rview = findViewById(R.id.procurement_list);
        rview.setHasFixedSize(true);
        layout = new LinearLayoutManager(getApplicationContext());
        rview.setLayoutManager(layout);

    }

}
