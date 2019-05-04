package p3l_8980.com.atmaauto.UI;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.codetroopers.betterpickers.calendardatepicker.CalendarDatePickerDialogFragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import okhttp3.ResponseBody;
import p3l_8980.com.atmaauto.Controller.ApiClient;
import p3l_8980.com.atmaauto.Controller.Detail;
import p3l_8980.com.atmaauto.Controller.Procurement;
import p3l_8980.com.atmaauto.Controller.ProcurementDetail;
import p3l_8980.com.atmaauto.Controller.ProcurementList;
import p3l_8980.com.atmaauto.Controller.Sales;
import p3l_8980.com.atmaauto.Controller.SalesList;
import p3l_8980.com.atmaauto.Controller.Sparepart;
import p3l_8980.com.atmaauto.Controller.SparepartData;
import p3l_8980.com.atmaauto.Controller.SparepartList;
import p3l_8980.com.atmaauto.Controller.SparepartType;
import p3l_8980.com.atmaauto.Controller.SparepartTypeList;
import p3l_8980.com.atmaauto.Controller.Supplier;
import p3l_8980.com.atmaauto.Controller.SupplierData;
import p3l_8980.com.atmaauto.Controller.SupplierList;
import p3l_8980.com.atmaauto.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddProcurement extends AppCompatActivity {

    ImageView backButton;
    Button addButton, editButton, sparepartButton;
    EditText procurementDate,amount;
    Spinner supplier,sales,sparepart,status;
    TextView title;
    Calendar myCalendar;
    int idsupplier,idsales;
    String idSparepart;
    private int simpan = -1;

    RecyclerView rview;
    private AdapterSparepartOrder adapter;
    private RecyclerView.LayoutManager layout;
    private List<Detail> DetailBundleFull;
    private List<ProcurementDetail> details = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_procurement);
        myCalendar = Calendar.getInstance();
        init();

        simpan = getIntent().getIntExtra("simpan",-1);

        if(simpan > -1){
            procurementDate.setText(getIntent().getStringExtra("date"));
            status.setSelection(getIndex(status, getIntent().getStringExtra("procurement_status")));

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
                        adapter = new AdapterSparepartOrder(response.body().getData());
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

        }
        else{
            editButton.setVisibility(View.GONE);
        }

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent = new Intent(AddProcurement.this, Beranda.class);
                intent.putExtra("addDialog", 3);
                startActivity(intent);
            }
        });

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub

                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };

        procurementDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(AddProcurement.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        supplier.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                Supplier sp = (Supplier) parentView.getSelectedItem();
                idsupplier = sp.getIdSupplier();

                Retrofit retrofit = new retrofit2.Retrofit.Builder()
                        .baseUrl("https://p3l.yafetrakan.com/api/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                ApiClient apiClient = retrofit.create(ApiClient.class);
                Call<SalesList> call = apiClient.getSales();
                call.enqueue(new Callback<SalesList>() {
                    @Override
                    public void onResponse(Call<SalesList> call, Response<SalesList> response) {
                        ArrayList<Sales> sales1 = new ArrayList<>();
                        for (int i = 0; i < response.body().getData().size(); i++) {
                            if(response.body().getData().get(i).getIdSales()==idsupplier) {
                                sales1.add(new Sales(response.body().getData().get(i).getIdSales(), response.body().getData().get(i).getSalesName()));
                            }
                        }

                        ArrayAdapter<Sales> adapter = new ArrayAdapter<Sales>(getApplicationContext(),
                                android.R.layout.simple_spinner_dropdown_item
                                , sales1);

                        sales.setAdapter(adapter);
                        Log.d("items",sales.getItemAtPosition(0).toString());
                    if(simpan > -1) {
                        sales.setSelection(getIndex(sales, getIntent().getStringExtra("sales")));
                    }

                    }

                    @Override
                    public void onFailure(Call<SalesList> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Data Dropdown gagal diambil", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });


        sales.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                Sales sp = (Sales) parentView.getSelectedItem();
                idsales = sp.getIdSales();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

        sparepart.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                Sparepart sp = (Sparepart) parentView.getSelectedItem();
                idSparepart = sp.getIdSparepart();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

        sparepartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToCart();
            }
        });

        if(simpan > -1){
            addButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    update();
                }
            });
        }else {
            addButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    save();
                }
            });
        }


    }

    private void addToCart()
    {
        Log.d("idsparepart", idSparepart);
        Retrofit retrofit = new retrofit2.Retrofit.Builder()
                .baseUrl("https://p3l.yafetrakan.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiClient apiClient = retrofit.create(ApiClient.class);
        Call<SparepartData> call = apiClient.getSparepart(idSparepart);
        call.enqueue(new Callback<SparepartData>() {
            @Override
            public void onResponse(Call<SparepartData> call, Response<SparepartData> response) {
                Log.d("sparepartname", response.body().getData().getSparepartName());
                details.add(new ProcurementDetail(response.body().getData().getPurchasePrice(),Integer.parseInt(amount.getText().toString()),
                        response.body().getData().getPurchasePrice()*Integer.parseInt(amount.getText().toString()),response.body().getData().getIdSparepart(),response.body().getData().getSparepartName()));
                Log.d("detail",String.valueOf(details.get(0).getSparepartName()));
                adapter = new AdapterSparepartOrder(details);
                rview.setAdapter(adapter);
                amount.setText("");

            }

            @Override
            public void onFailure(Call<SparepartData> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void init(){
        backButton = findViewById(R.id.btnBack);
        addButton = findViewById(R.id.btnAdd);
        editButton = findViewById(R.id.btnEdit);
        procurementDate = findViewById(R.id.procurementDate);
        supplier = findViewById(R.id.Supplier);
        sales = findViewById(R.id.Sales);
        title = findViewById(R.id.title);
        amount =  findViewById(R.id.amount);
        sparepart = findViewById(R.id.sparepart);
        sparepartButton = findViewById(R.id.btnInputSparepart);
        rview = findViewById(R.id.procurement_list);
        rview.setHasFixedSize(true);
        layout = new LinearLayoutManager(getApplicationContext());
        rview.setLayoutManager(layout);
        status = findViewById(R.id.Status);

        Retrofit retrofit = new retrofit2.Retrofit.Builder()
                .baseUrl("https://p3l.yafetrakan.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiClient apiClient = retrofit.create(ApiClient.class);
        Call<SupplierList> call = apiClient.getSuppliers();
        call.enqueue(new Callback<SupplierList>() {
            @Override
            public void onResponse(Call<SupplierList> call, Response<SupplierList> response) {
                ArrayList<Supplier> suppliers = new ArrayList<>();
                for (int i = 0; i < response.body().getData().size(); i++) {
                    suppliers.add(new Supplier(response.body().getData().get(i).getIdSupplier(),response.body().getData().get(i).getSupplierName()));
                }

                ArrayAdapter<Supplier> adapter = new ArrayAdapter<Supplier>(getApplicationContext(),
                        android.R.layout.simple_spinner_dropdown_item
                        , suppliers);

                supplier.setAdapter(adapter);
                Log.d("items",supplier.getItemAtPosition(0).toString());
                if(simpan > -1) {
                    supplier.setSelection(getIndex(supplier, getIntent().getStringExtra("supplier")));
                }

            }

            @Override
            public void onFailure(Call<SupplierList> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Data Dropdown gagal diambil", Toast.LENGTH_SHORT).show();
            }
        });

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
//                if(simpan > -1) {
//                    spinnerType.setSelection(getIndex(spinnerType, getIntent().getStringExtra("type")));
//                }

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

    private void updateLabel() {
        String myFormat = "yyyy-MM-dd"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        procurementDate.setText(sdf.format(myCalendar.getTime()));
    }

    private void save() {
        if(procurementDate.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(), "tanggal pesan tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }

        else{
            try {
                final ProgressDialog progressDialog = new ProgressDialog(AddProcurement.this, R.style.AppTheme_Dark_Dialog);
                progressDialog.setMessage("Memproses Data...");
                progressDialog.show();
                Retrofit retrofit = new retrofit2.Retrofit.Builder()
                        .baseUrl("https://p3l.yafetrakan.com/api/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                ApiClient apiClient = retrofit.create(ApiClient.class);

                Call<ResponseBody> addProcurement = apiClient.addProcurement(procurementDate.getText().toString(),status.getSelectedItem().toString(),idsales);

                addProcurement.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        try {
                            JSONObject jsonRes = new JSONObject(response.body().string());
                            String idprocurement =  jsonRes.getJSONObject("data").getString("id_procurement");
                            Log.d("idprocurement", idprocurement);
                            for(int x=0;x<details.size();x++)
                            {
                                Log.d("masuk", "masssuk");
                                Retrofit retrofit = new retrofit2.Retrofit.Builder()
                                        .baseUrl("https://p3l.yafetrakan.com/api/")
                                        .addConverterFactory(GsonConverterFactory.create())
                                        .build();

                                ApiClient apiClient = retrofit.create(ApiClient.class);

                                Call<ResponseBody> addProcurementDetail = apiClient.addProcurementDetail(details.get(x).getPrice(),details.get(x).getAmount(),details.get(x).getSubtotal(),details.get(x).getIdSparepart(),Integer.parseInt(idprocurement));

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
                            Toast.makeText(getApplicationContext(), "Berhasil Input Data", Toast.LENGTH_SHORT).show();
                            final Intent intent = new Intent(AddProcurement.this, Beranda.class);
                            intent.putExtra("addDialog", 3);
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
                        Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void update() {
        try {
            final ProgressDialog progressDialog = new ProgressDialog(AddProcurement.this, R.style.AppTheme_Dark_Dialog);
            progressDialog.setMessage("Memproses Data...");
            progressDialog.show();
            Retrofit retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl("https://p3l.yafetrakan.com/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            ApiClient apiClient = retrofit.create(ApiClient.class);

            Call<ResponseBody> updateProcurement = apiClient.updateProcurement(getIntent().getIntExtra("id_procurement",0),procurementDate.getText().toString(),status.getSelectedItem().toString(),idsales);

            updateProcurement.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                        JSONObject jsonRes = new JSONObject(response.body().string());
//                        String idprocurement =  jsonRes.getJSONObject("data").getString("id_procurement");
//                        Log.d("idprocurement", idprocurement);
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
                        Toast.makeText(getApplicationContext(), "Berhasil Input Data", Toast.LENGTH_SHORT).show();
                        final Intent intent = new Intent(AddProcurement.this, Beranda.class);
                        intent.putExtra("addDialog", 3);
                        startActivity(intent);


                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    progressDialog.dismiss();
                    Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

}
