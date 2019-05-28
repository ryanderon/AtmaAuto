package p3l_8980.com.atmaauto.UI;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
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
import java.util.List;

import okhttp3.ResponseBody;
import p3l_8980.com.atmaauto.Controller.ApiClient;
import p3l_8980.com.atmaauto.Controller.Detail;
import p3l_8980.com.atmaauto.Controller.DetailService;
import p3l_8980.com.atmaauto.Controller.DetailSparepart;
import p3l_8980.com.atmaauto.Controller.Employee;
import p3l_8980.com.atmaauto.Controller.EmployeeList;
import p3l_8980.com.atmaauto.Controller.Motorcycle;
import p3l_8980.com.atmaauto.Controller.MotorcycleList;
import p3l_8980.com.atmaauto.Controller.ProcurementDetail;
import p3l_8980.com.atmaauto.Controller.Sales;
import p3l_8980.com.atmaauto.Controller.SalesList;
import p3l_8980.com.atmaauto.Controller.Service;
import p3l_8980.com.atmaauto.Controller.Services;
import p3l_8980.com.atmaauto.Controller.ServicesData;
import p3l_8980.com.atmaauto.Controller.ServicesList;
import p3l_8980.com.atmaauto.Controller.Sparepart;
import p3l_8980.com.atmaauto.Controller.SparepartData;
import p3l_8980.com.atmaauto.Controller.SparepartList;
import p3l_8980.com.atmaauto.Controller.SparepartTransaction;
import p3l_8980.com.atmaauto.Controller.SparepartType;
import p3l_8980.com.atmaauto.Controller.SparepartTypeList;
import p3l_8980.com.atmaauto.Controller.Supplier;
import p3l_8980.com.atmaauto.Controller.SupplierList;
import p3l_8980.com.atmaauto.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddTransaction extends AppCompatActivity {

    EditText customerName,amount;
    TextView subtitle3, subtitle4, subtitle1, subtitle2;
    Button addService,addSpareparts,btnAdd;
    Spinner transactionStatus,transactionType,mechanic,service,motorcycleUser,sparepart,sparepatType,mechanic2,motorcycleUser2;
    ImageView backButton;
    double total=0;
    int idService,idMotorcycle,idMotorcycle2,idEmployee,IdEmployee2,idsparepartType;
    String license_number,idSparepart,license_number2;
    private int simpan = -1;

    RecyclerView rview,rview2;
    private AdapterDetailService adapterService;
    private AdapterDetailSparepart adapterSparepart;
    private RecyclerView.LayoutManager layout,layout2;

    private List<Service> detailServices = new ArrayList<>();
    private List<SparepartTransaction> detailSpareparts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_transaction);
        init();

        simpan = getIntent().getIntExtra("simpan",-1);

        if(simpan > -1){
            customerName.setText(getIntent().getStringExtra("name"));
            transactionStatus.setSelection(getIndex(transactionStatus, getIntent().getStringExtra("status")));
            transactionType.setSelection(getIndex(transactionType, getIntent().getStringExtra("type")));

            Retrofit retrofit= new retrofit2.Retrofit.Builder()
                    .baseUrl("http://192.168.19.140/8991/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            ApiClient apiClient = retrofit.create(ApiClient.class);

            Call<DetailService> detailServiceGet = apiClient.getDetailService(getIntent().getStringExtra("id_transaction"));
            detailServiceGet.enqueue(new Callback<DetailService>() {
                @Override
                public void onResponse(Call<DetailService> call, Response<DetailService> response) {
                    try {
                        adapterService = new AdapterDetailService(response.body().getData());
                        detailServices = response.body().getData();
                        rview.setAdapter(adapterService);
                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), "Tidak Ada Detail Service!", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<DetailService> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "Fail", Toast.LENGTH_SHORT).show();
                }

            });

            ApiClient apiClient2 = retrofit.create(ApiClient.class);

            Call<DetailSparepart> detailSparepartGet = apiClient2.getDetailSparepart(getIntent().getStringExtra("id_transaction"));
            detailSparepartGet.enqueue(new Callback<DetailSparepart>() {
                @Override
                public void onResponse(Call<DetailSparepart> call, Response<DetailSparepart> response) {
                    try {
                        adapterSparepart = new AdapterDetailSparepart(response.body().getData());
                        detailSpareparts = response.body().getData();
                        rview2.setAdapter(adapterSparepart);
                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), "Tidak Ada Detail Service!", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<DetailSparepart> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "Fail", Toast.LENGTH_SHORT).show();
                }

            });

        }

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent = new Intent(AddTransaction.this, MenuCustomer.class);
                intent.putExtra("addDialog", 4);
                startActivity(intent);
            }
        });

        transactionType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                subtitle3.setVisibility(View.VISIBLE);
                subtitle4.setVisibility(View.VISIBLE);
                mechanic.setVisibility(View.VISIBLE);
                service.setVisibility(View.VISIBLE);
                motorcycleUser.setVisibility(View.VISIBLE);
                addService.setVisibility(View.VISIBLE);
                rview.setVisibility(View.VISIBLE);
                subtitle1.setVisibility(View.VISIBLE);
                subtitle2.setVisibility(View.VISIBLE);
                mechanic2.setVisibility(View.VISIBLE);
                sparepart.setVisibility(View.VISIBLE);
                motorcycleUser2.setVisibility(View.VISIBLE);
                addSpareparts.setVisibility(View.VISIBLE);
                rview2.setVisibility(View.VISIBLE);
                sparepatType.setVisibility(View.VISIBLE);
                amount.setVisibility(View.VISIBLE);
                if(parentView.getSelectedItem().toString().equalsIgnoreCase("Sparepart"))
                {
                    subtitle3.setVisibility(View.GONE);
                    subtitle4.setVisibility(View.GONE);
                    mechanic.setVisibility(View.GONE);
                    service.setVisibility(View.GONE);
                    motorcycleUser.setVisibility(View.GONE);
                    addService.setVisibility(View.GONE);
                    rview.setVisibility(View.GONE);
                    detailServices.clear();
                }
                else if(parentView.getSelectedItem().toString().equalsIgnoreCase("Service"))
                {
                    subtitle1.setVisibility(View.GONE);
                    subtitle2.setVisibility(View.GONE);
                    mechanic2.setVisibility(View.GONE);
                    sparepart.setVisibility(View.GONE);
                    amount.setVisibility(View.GONE);
                    motorcycleUser2.setVisibility(View.GONE);
                    addSpareparts.setVisibility(View.GONE);
                    rview2.setVisibility(View.GONE);
                    sparepatType.setVisibility(View.GONE);
                    detailSpareparts.clear();
                }
                else
                {
                    subtitle3.setVisibility(View.VISIBLE);
                    subtitle4.setVisibility(View.VISIBLE);
                    mechanic.setVisibility(View.VISIBLE);
                    service.setVisibility(View.VISIBLE);
                    motorcycleUser.setVisibility(View.VISIBLE);
                    addService.setVisibility(View.VISIBLE);
                    rview.setVisibility(View.VISIBLE);
                    subtitle1.setVisibility(View.VISIBLE);
                    subtitle2.setVisibility(View.VISIBLE);
                    mechanic2.setVisibility(View.VISIBLE);
                    sparepart.setVisibility(View.VISIBLE);
                    motorcycleUser2.setVisibility(View.VISIBLE);
                    addSpareparts.setVisibility(View.VISIBLE);
                    rview2.setVisibility(View.VISIBLE);
                    sparepatType.setVisibility(View.VISIBLE);
                    amount.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

        service.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                Services sp = (Services) parentView.getSelectedItem();
                idService = sp.getIdService();
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

        mechanic.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                Employee sp = (Employee) parentView.getSelectedItem();
                idEmployee = sp.getIdEmployee();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

        mechanic2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                Employee sp = (Employee) parentView.getSelectedItem();
                IdEmployee2 = sp.getIdEmployee();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

        motorcycleUser.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                Motorcycle sp = (Motorcycle) parentView.getSelectedItem();
                idMotorcycle = sp.getIdMotorcycle();
                license_number = sp.getLicenseNumber();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

        motorcycleUser2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                Motorcycle sp = (Motorcycle) parentView.getSelectedItem();
                idMotorcycle2 = sp.getIdMotorcycle();
                license_number2 = sp.getLicenseNumber();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

        sparepatType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                SparepartType sp = (SparepartType) parentView.getSelectedItem();
                idsparepartType = sp.getIdSparepartType();

                Retrofit retrofit = new retrofit2.Retrofit.Builder()
                        .baseUrl("http://192.168.19.140/8991/api/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                ApiClient apiClient = retrofit.create(ApiClient.class);
                Call<SparepartList> call = apiClient.getSpareparts();
                call.enqueue(new Callback<SparepartList>() {
                    @Override
                    public void onResponse(Call<SparepartList> call, Response<SparepartList> response) {
                        ArrayList<Sparepart> spareparts = new ArrayList<>();
                        for (int i = 0; i < response.body().getData().size(); i++) {
                            if(response.body().getData().get(i).getIdSparepartType()==idsparepartType) {
                                spareparts.add(new Sparepart(response.body().getData().get(i).getIdSparepart(),response.body().getData().get(i).getSparepartName().concat("-").concat(response.body().getData().get(i).getMerk())));
                            }
                        }

                        ArrayAdapter<Sparepart> adapter = new ArrayAdapter<Sparepart>(getApplicationContext(),
                                android.R.layout.simple_spinner_dropdown_item
                                , spareparts);

                        sparepart.setAdapter(adapter);
//                        Log.d("items",sales.getItemAtPosition(0).toString());
//                        if(simpan > -1) {
//                            sales.setSelection(getIndex(sales, getIntent().getStringExtra("sales")));
//                        }

                    }

                    @Override
                    public void onFailure(Call<SparepartList> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Data Dropdown gagal diambil", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

        addService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToServiceCart();
            }
        });

        addSpareparts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToSparepartCart();
            }
        });

        customerName.setText(getIntent().getStringExtra("name"));

        if(simpan > -1){
            btnAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    update();
                }
            });
        }else {
            btnAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    save();
                }
            });
        }

    }

    private int getIndex(Spinner spinner, String myString){
        for (int i=0;i<spinner.getCount();i++){
            if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(myString)){

                return i;
            }
        }

        return 0;
    }

    private void addToServiceCart()
    {
        Retrofit retrofit = new retrofit2.Retrofit.Builder()
                .baseUrl("http://192.168.19.140/8991/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiClient apiClient = retrofit.create(ApiClient.class);
        Call<ServicesData> call = apiClient.getService(idService);
        call.enqueue(new Callback<ServicesData>() {
            @Override
            public void onResponse(Call<ServicesData> call, Response<ServicesData> response) {

                detailServices.add(new Service(1,response.body().getData().getPrice(),response.body().getData().getPrice(),idService,response.body().getData().getServiceName(),idEmployee,idMotorcycle,license_number));
                adapterService = new AdapterDetailService(detailServices);
                rview.setAdapter(adapterService);
                total = total + response.body().getData().getPrice();
//                amount.setText("");
            }

            @Override
            public void onFailure(Call<ServicesData> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void addToSparepartCart()
    {
        Retrofit retrofit = new retrofit2.Retrofit.Builder()
                .baseUrl("http://192.168.19.140/8991/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiClient apiClient = retrofit.create(ApiClient.class);
        Call<SparepartData> call = apiClient.getSparepart(idSparepart);
        call.enqueue(new Callback<SparepartData>() {
            @Override
            public void onResponse(Call<SparepartData> call, Response<SparepartData> response) {

                detailSpareparts.add(new SparepartTransaction(Integer.parseInt(amount.getText().toString()),response.body().getData().getSellPrice(),response.body().getData().getSellPrice()*Integer.parseInt(amount.getText().toString()),
                        idSparepart,response.body().getData().getSparepartTypeName(),response.body().getData().getSparepartName(),response.body().getData().getMerk(),IdEmployee2,idMotorcycle2));
                adapterSparepart = new AdapterDetailSparepart(detailSpareparts);
                rview2.setAdapter(adapterSparepart);
                total = total + response.body().getData().getSellPrice()*Integer.parseInt(amount.getText().toString());
//                amount.setText("");
            }

            @Override
            public void onFailure(Call<SparepartData> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void init(){
        backButton = findViewById(R.id.btnBack);
        subtitle3 = findViewById(R.id.subtitle3);
        subtitle4 = findViewById(R.id.subtitle4);
        subtitle1 = findViewById(R.id.subtitle1);
        subtitle2 = findViewById(R.id.subtitle2);
        btnAdd = findViewById(R.id.btnAdd);
        customerName = findViewById(R.id.customerName);
        transactionStatus = findViewById(R.id.transitionStatus);
        transactionType = findViewById(R.id.transactionType);
        mechanic = findViewById(R.id.mechanic);
        motorcycleUser = findViewById(R.id.motorcycleUser);
        mechanic2 = findViewById(R.id.mechanic2);
        motorcycleUser2 = findViewById(R.id.motorcycleUser2);
        service = findViewById(R.id.service);
        amount = findViewById(R.id.amount);
        sparepart = findViewById(R.id.sparepart);
        sparepatType = findViewById(R.id.sparepartType);
        addService = findViewById(R.id.btnInputService);
        addSpareparts = findViewById(R.id.btnInputSparepart);
        rview = findViewById(R.id.detailservice_list);
        rview.setHasFixedSize(true);
        rview2 = findViewById(R.id.detailsparepart_list);
        rview2.setHasFixedSize(true);
        layout = new LinearLayoutManager(getApplicationContext());
        layout2 = new LinearLayoutManager(getApplicationContext());
        rview.setLayoutManager(layout);
        rview2.setLayoutManager(layout2);

        Retrofit retrofit = new retrofit2.Retrofit.Builder()
                .baseUrl("http://192.168.19.140/8991/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiClient apiClient = retrofit.create(ApiClient.class);
        Call<ServicesList> call = apiClient.getServices();
        call.enqueue(new Callback<ServicesList>() {
            @Override
            public void onResponse(Call<ServicesList> call, Response<ServicesList> response) {
                ArrayList<Services> services = new ArrayList<>();
                for (int i = 0; i < response.body().getData().size(); i++) {
                    services.add(new Services(response.body().getData().get(i).getIdService(),response.body().getData().get(i).getServiceName(),response.body().getData().get(i).getPrice()));
                }

                ArrayAdapter<Services> adapter = new ArrayAdapter<Services>(getApplicationContext(),
                        android.R.layout.simple_spinner_dropdown_item
                        , services);

                service.setAdapter(adapter);
//                Log.d("items",supplier.getItemAtPosition(0).toString());
//                if(simpan > -1) {
//                    supplier.setSelection(getIndex(supplier, getIntent().getStringExtra("supplier")));
//                }

            }

            @Override
            public void onFailure(Call<ServicesList> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Data Dropdown gagal diambil", Toast.LENGTH_SHORT).show();
            }
        });

        ApiClient apiClient2 = retrofit.create(ApiClient.class);
        Call<EmployeeList> call2 = apiClient2.getEmployee();
        call2.enqueue(new Callback<EmployeeList>() {
            @Override
            public void onResponse(Call<EmployeeList> call, Response<EmployeeList> response) {
                ArrayList<Employee> employees= new ArrayList<>();
                for (int i = 0; i < response.body().getData().size(); i++) {
                    if(response.body().getData().get(i).getIdRole()==4) {
                        employees.add(new Employee(response.body().getData().get(i).getIdEmployee(), response.body().getData().get(i).getName()));
                    }
                }

                ArrayAdapter<Employee> adapter = new ArrayAdapter<Employee>(getApplicationContext(),
                        android.R.layout.simple_spinner_dropdown_item
                        , employees);

                mechanic.setAdapter(adapter);
                mechanic2.setAdapter(adapter);
//                Log.d("items",sparepart.getItemAtPosition(0).toString());
//                if(simpan > -1) {
//                    spinnerType.setSelection(getIndex(spinnerType, getIntent().getStringExtra("type")));
//                }

            }

            @Override
            public void onFailure(Call<EmployeeList> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Data Dropdown gagal diambil", Toast.LENGTH_SHORT).show();
            }
        });

        ApiClient apiClient3 = retrofit.create(ApiClient.class);
        Call<MotorcycleList> call3 = apiClient3.getMotorcycle(getIntent().getIntExtra("id",0));
        call3.enqueue(new Callback<MotorcycleList>() {
            @Override
            public void onResponse(Call<MotorcycleList> call, Response<MotorcycleList> response) {
                ArrayList<Motorcycle> motorcycles= new ArrayList<>();
                for (int i = 0; i < response.body().getData().size(); i++) {
                    motorcycles.add(new Motorcycle(response.body().getData().get(i).getIdMotorcycle(), response.body().getData().get(i).getLicenseNumber()));
                }

                ArrayAdapter<Motorcycle> adapter = new ArrayAdapter<Motorcycle>(getApplicationContext(),
                        android.R.layout.simple_spinner_dropdown_item
                        , motorcycles);

                motorcycleUser.setAdapter(adapter);
                motorcycleUser2.setAdapter(adapter);
//                Log.d("items",sparepart.getItemAtPosition(0).toString());
//                if(simpan > -1) {
//                    spinnerType.setSelection(getIndex(spinnerType, getIntent().getStringExtra("type")));
//                }

            }

            @Override
            public void onFailure(Call<MotorcycleList> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Data Dropdown gagal diambil", Toast.LENGTH_SHORT).show();
            }
        });

        ApiClient apiClient4 = retrofit.create(ApiClient.class);
        Call<SparepartTypeList> call4 = apiClient4.getSparepartTypes();
        call4.enqueue(new Callback<SparepartTypeList>() {
            @Override
            public void onResponse(Call<SparepartTypeList> call, Response<SparepartTypeList> response) {
//                List<SparepartType> spinnerArrayList = response.body().getData();
                ArrayList<SparepartType> sparepartTypes = new ArrayList<>();
                for (int i = 0; i < response.body().getData().size(); i++) {
                    sparepartTypes.add(new SparepartType(response.body().getData().get(i).getIdSparepartType(),response.body().getData().get(i).getSparepartTypeName()));
                }

                ArrayAdapter<SparepartType> adapter = new ArrayAdapter<SparepartType>(getApplicationContext(),
                        android.R.layout.simple_spinner_dropdown_item
                        , sparepartTypes);

                sparepatType.setAdapter(adapter);
//                Log.d("items",spinnerType.getItemAtPosition(0).toString());
//                if(simpan > -1) {
//                    spinnerType.setSelection(getIndex(spinnerType, getIntent().getStringExtra("type")));
//                }

            }

            @Override
            public void onFailure(Call<SparepartTypeList> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Data Dropdown gagal diambil", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void save() {
        if(customerName.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(), "nama customer tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }
        else{
            try {
                final ProgressDialog progressDialog = new ProgressDialog(AddTransaction.this, R.style.AppTheme_Dark_Dialog);
                progressDialog.setMessage("Memproses Data...");
                progressDialog.show();
                Retrofit retrofit = new retrofit2.Retrofit.Builder()
                        .baseUrl("http://192.168.19.140/8991/api/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                ApiClient apiClient = retrofit.create(ApiClient.class);

                Call<ResponseBody> addTransaction = apiClient.addTransaction(transactionType.getSelectedItem().toString(),transactionStatus.getSelectedItem().toString(),
                        total,getIntent().getIntExtra("id",0));

                addTransaction.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        try {
                            JSONObject jsonRes = new JSONObject(response.body().string());
                            String idtransaction =  jsonRes.getJSONObject("data").getString("id_transaction");
                            Log.d("idtransaction", idtransaction);
                            if(!detailServices.isEmpty()) {
                                for (int x = 0; x < detailServices.size(); x++) {
                                    Log.d("masuk", "masssuk");
                                    Retrofit retrofit = new retrofit2.Retrofit.Builder()
                                            .baseUrl("http://192.168.19.140/8991/api/")
                                            .addConverterFactory(GsonConverterFactory.create())
                                            .build();

                                    ApiClient apiClient = retrofit.create(ApiClient.class);

                                    Call<ResponseBody> addDetailService = apiClient.addDetailService(detailServices.get(x).getDetailServicePrice(), detailServices.get(x).getDetailServiceAmount(), detailServices.get(x).getDetailServiceSubtotal(), detailServices.get(x).getIdMotorcycle(),detailServices.get(x).getIdMechanic(), detailServices.get(x).getIdService(),idtransaction);

                                    addDetailService.enqueue(new Callback<ResponseBody>() {
                                        @Override
                                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                            Log.d("successe", "hehehe");
//                                            try {
//                                                JSONObject jsonRes = new JSONObject(response.body().string());
//                                                String iddetailprocurement = jsonRes.getJSONObject("data").getString("id_procurement_detail");
//                                                Log.d("idprocurement", iddetailprocurement);
//                                            } catch (JSONException e) {
//                                                e.printStackTrace();
//                                            } catch (IOException e) {
//                                                e.printStackTrace();
//                                            }
                                        }

                                        @Override
                                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                                        }
                                    });
                                }
                            }

                            if(!detailSpareparts.isEmpty()) {
                                for (int x = 0; x < detailSpareparts.size(); x++) {
                                    Log.d("masuk", "masssuk");
                                    Retrofit retrofit = new retrofit2.Retrofit.Builder()
                                            .baseUrl("http://192.168.19.140/8991/api/")
                                            .addConverterFactory(GsonConverterFactory.create())
                                            .build();

                                    ApiClient apiClient = retrofit.create(ApiClient.class);

                                    Call<ResponseBody> addDetailSparepart = apiClient.addDetailSparepart(detailSpareparts.get(x).getDetailSparepartPrice(), detailSpareparts.get(x).getDetailSparepartAmount(), detailSpareparts.get(x).getDetailSparepartSubtotal(), detailSpareparts.get(x).getIdMotorcycle(),detailSpareparts.get(x).getIdMechanic(), detailSpareparts.get(x).getIdSparepart(),idtransaction);

                                    addDetailSparepart.enqueue(new Callback<ResponseBody>() {
                                        @Override
                                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                            Log.d("successe", "hehehe");
//                                            try {
//                                                JSONObject jsonRes = new JSONObject(response.body().string());
//                                                String iddetailprocurement = jsonRes.getJSONObject("data").getString("id_procurement_detail");
//                                                Log.d("idprocurement", iddetailprocurement);
//                                            } catch (JSONException e) {
//                                                e.printStackTrace();
//                                            } catch (IOException e) {
//                                                e.printStackTrace();
//                                            }
                                        }

                                        @Override
                                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                                        }
                                    });
                                }
                            }

                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(), "Berhasil Input Data", Toast.LENGTH_SHORT).show();
                            final Intent intent = new Intent(AddTransaction.this, Beranda.class);
                            intent.putExtra("addDialog", 4);
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
            final ProgressDialog progressDialog = new ProgressDialog(AddTransaction.this, R.style.AppTheme_Dark_Dialog);
            progressDialog.setMessage("Memproses Data...");
            progressDialog.show();
            Retrofit retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl("http://192.168.19.140/8991/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            ApiClient apiClient = retrofit.create(ApiClient.class);

            Call<ResponseBody> updateTransaction = apiClient.updateTransaction(getIntent().getStringExtra("id_transaction"),transactionType.getSelectedItem().toString(),transactionStatus.getSelectedItem().toString(),
                    total,getIntent().getIntExtra("id",0));

            updateTransaction.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    try {
                        Log.d("WDWD","XW");
                        JSONObject jsonRes = new JSONObject(response.body().string());
                        String idtransaction =  jsonRes.getJSONObject("data").getString("id_transaction");
                        Log.d("idtransaction", idtransaction);
                        if(!detailServices.isEmpty()) {
                            for (int x = 0; x < detailServices.size(); x++) {
                                Log.d("masuk", "masssuk");
                                Retrofit retrofit = new retrofit2.Retrofit.Builder()
                                        .baseUrl("http://192.168.19.140/8991/api/")
                                        .addConverterFactory(GsonConverterFactory.create())
                                        .build();

                                ApiClient apiClient = retrofit.create(ApiClient.class);

                                Log.d("PRICE", String.valueOf(detailServices.get(x).getDetailServicePrice()));
                                Log.d("AMOUNT", String.valueOf(detailServices.get(x).getDetailServiceAmount()));
                                Log.d("SUBTOTAL", String.valueOf(detailServices.get(x).getDetailServiceSubtotal()));
                                Call<ResponseBody> addDetailService = apiClient.addDetailService(detailServices.get(x).getDetailServicePrice(), detailServices.get(x).getDetailServiceAmount(), detailServices.get(x).getDetailServiceSubtotal(), detailServices.get(x).getIdMotorcycle(),detailServices.get(x).getIdMechanic(), detailServices.get(x).getIdService(),idtransaction);

                                addDetailService.enqueue(new Callback<ResponseBody>() {
                                    @Override
                                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                        Log.d("successe", "hehehe");
                                            try {
                                                JSONObject jsonRes = new JSONObject(response.body().string());
                                                String idDetailService = jsonRes.getJSONObject("data").getString("id_detail_service");
                                                Log.d("iddetailservice", idDetailService);
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
                        }

                        if(!detailSpareparts.isEmpty()) {
                            for (int x = 0; x < detailSpareparts.size(); x++) {
                                Log.d("masuk", "masssuk");
                                Retrofit retrofit = new retrofit2.Retrofit.Builder()
                                        .baseUrl("http://192.168.19.140/8991/api/")
                                        .addConverterFactory(GsonConverterFactory.create())
                                        .build();

                                ApiClient apiClient = retrofit.create(ApiClient.class);

                                Call<ResponseBody> addDetailSparepart = apiClient.addDetailSparepart(detailSpareparts.get(x).getDetailSparepartPrice(), detailSpareparts.get(x).getDetailSparepartAmount(), detailSpareparts.get(x).getDetailSparepartSubtotal(), detailSpareparts.get(x).getIdMotorcycle(),detailSpareparts.get(x).getIdMechanic(), detailSpareparts.get(x).getIdSparepart(),idtransaction);

                                addDetailSparepart.enqueue(new Callback<ResponseBody>() {
                                    @Override
                                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                        Log.d("successe", "hehehe");
//                                            try {
//                                                JSONObject jsonRes = new JSONObject(response.body().string());
//                                                String iddetailprocurement = jsonRes.getJSONObject("data").getString("id_procurement_detail");
//                                                Log.d("idprocurement", iddetailprocurement);
//                                            } catch (JSONException e) {
//                                                e.printStackTrace();
//                                            } catch (IOException e) {
//                                                e.printStackTrace();
//                                            }
                                    }

                                    @Override
                                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                                    }
                                });
                            }
                        }

                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(), "Berhasil Input Data", Toast.LENGTH_SHORT).show();
                        final Intent intent = new Intent(AddTransaction.this, Beranda.class);
                        intent.putExtra("addDialog", 4);
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
