package p3l_8980.com.atmaauto.UI;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import p3l_8980.com.atmaauto.Controller.ApiClient;
import p3l_8980.com.atmaauto.Controller.Employee;
import p3l_8980.com.atmaauto.Controller.EmployeeList;
import p3l_8980.com.atmaauto.Controller.Motorcycle;
import p3l_8980.com.atmaauto.Controller.MotorcycleList;
import p3l_8980.com.atmaauto.Controller.Sales;
import p3l_8980.com.atmaauto.Controller.Service;
import p3l_8980.com.atmaauto.Controller.Services;
import p3l_8980.com.atmaauto.Controller.ServicesList;
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

public class AddTransaction extends AppCompatActivity {

    EditText customerName;
    Button addService;
    Spinner transactionStatus,transactionType,mechanic,service,motorcycleUser;
    ImageView backButton;
    int idService,idMotorcycle,idEmployee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_transaction);
        init();
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent = new Intent(AddTransaction.this, MenuCustomer.class);
                intent.putExtra("addDialog", 4);
                startActivity(intent);
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

        motorcycleUser.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                Motorcycle sp = (Motorcycle) parentView.getSelectedItem();
                idMotorcycle = sp.getIdMotorcycle();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

        customerName.setText(getIntent().getStringExtra("name"));

    }
    private void init(){
        backButton = findViewById(R.id.btnBack);
        customerName = findViewById(R.id.customerName);
        transactionStatus = findViewById(R.id.transitionStatus);
        transactionType = findViewById(R.id.transactionType);
        mechanic = findViewById(R.id.mechanic);
        motorcycleUser = findViewById(R.id.motorcycleUser);
        service = findViewById(R.id.service);

        Retrofit retrofit = new retrofit2.Retrofit.Builder()
                .baseUrl("https://p3l.yafetrakan.com/api/")
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
    }
}
