package p3l_8980.com.atmaauto.UI;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
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
import android.widget.TextView;
import android.widget.Toast;


import com.jaredrummler.materialspinner.MaterialSpinner;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import p3l_8980.com.atmaauto.Controller.ApiClient;
import p3l_8980.com.atmaauto.Controller.Place;
import p3l_8980.com.atmaauto.Controller.Position;
import p3l_8980.com.atmaauto.Controller.Sparepart;
import p3l_8980.com.atmaauto.Controller.SparepartData;
import p3l_8980.com.atmaauto.Controller.SparepartType;
import p3l_8980.com.atmaauto.Controller.SparepartTypeList;
import p3l_8980.com.atmaauto.Controller.Supplier;
import p3l_8980.com.atmaauto.Controller.SupplierData;
import p3l_8980.com.atmaauto.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

public class AddSparepart extends AppCompatActivity {

    Bitmap ImageBitmap;
    ImageView backButton;
    Button addButton, btn_add_image, editButton, deleteButton;
    ImageView add_sparepart_image;
    int Image_Request_Code = 1;
    Uri FilePathUri,FilePathUri2;
    Spinner spinnerType,spinnerPosition,spinnerPlace;
    private Context context;
    EditText idSparepart, sparepartName, merk_sparepart, stock_sparepart, minStock, purchasePrice, sellPrice, placement_sparepart, position_sparepart, place_sparepart, number_sparepart, idSparepartType;
    TextView title;
    private List<Sparepart> SparepartBundle = new ArrayList<>();
    private int simpan = -1,idType;
    private String cek,idPosition,idPlace;

    private List<String> listNameType = new ArrayList<String>();
    private List<Integer> listIdType = new ArrayList<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sparepart);

        init();
        simpan = getIntent().getIntExtra("simpan",-1);

        btn_add_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Creating intent.
                Intent intent = new Intent();

                // Setting intent type as image to select image from phone storage.
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Please Select Image"), Image_Request_Code);

            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Retrofit retrofit = new retrofit2.Retrofit.Builder()
                        .baseUrl("https://p3l.yafetrakan.com/api/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                ApiClient apiClient = retrofit.create(ApiClient.class);
                final Intent intent = new Intent(AddSparepart.this, Beranda.class);
                intent.putExtra("addDialog", 2);
                startActivity(intent);

                Call<ResponseBody> deleteSparepart = apiClient.deleteSparepart(idSparepart.getText().toString());
                deleteSparepart.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.code() == 200){
                            Toast.makeText(getApplicationContext(), "Berhasil hapus data Sparepart", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(getApplicationContext(), "Gagal hapus data Sparepart", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Gagal hapus data pengguna", Toast.LENGTH_SHORT).show();
                    }

                });

//                SparepartBundle.getData();
//                notifyItemRemoved(ifinal);
//                notifyItemRangeChanged(ifinal, getItemCount());
            }
        });

        spinnerPosition.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                Position position1 = (Position) parent.getSelectedItem();
                idPosition = position1.getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        spinnerPlace.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                Place place1 = (Place) parent.getSelectedItem();
                idPlace = place1.getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        spinnerType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                SparepartType sp = (SparepartType) parent.getSelectedItem();
                idType = sp.getIdSparepartType();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        if(simpan > -1){
//            final Supplier data = SupplierBundle.get(simpan);
            title.setText(getIntent().getStringExtra("name"));
            idSparepart.setText(getIntent().getStringExtra("id"));
            EditText idsparepart = (EditText) findViewById(R.id.idSparepart);
            idsparepart.setFocusable(false);
            idsparepart.setClickable(true);

            sparepartName.setText(getIntent().getStringExtra("name"));
            EditText sparepartname = (EditText) findViewById(R.id.sparepartName);
            sparepartname.setFocusable(false);
            sparepartname.setClickable(true);

            merk_sparepart.setText(getIntent().getStringExtra("merk"));
            EditText merksparepart = (EditText) findViewById(R.id.merk_sparepart);
            merksparepart.setFocusable(false);
            merksparepart.setClickable(true);

            stock_sparepart.setText(""+getIntent().getIntExtra("stock", 0 ));
            EditText stocksparepart = (EditText) findViewById(R.id.stock_sparepart);
            stocksparepart.setFocusable(false);
            stocksparepart.setClickable(true);

            minStock.setText(""+getIntent().getIntExtra("minstock", 0 ));
            EditText minstock = (EditText) findViewById(R.id.minStock);
            minstock.setFocusable(false);
            minstock.setClickable(true);

            purchasePrice.setText(""+getIntent().getDoubleExtra("purchaseprice", 0.00 ));
            EditText purchaseprice = (EditText) findViewById(R.id.purchasePrice);
            purchaseprice.setFocusable(false);
            purchaseprice.setClickable(true);

            sellPrice.setText(""+getIntent().getDoubleExtra("sellprice", 0.00 ));
            EditText sellprice = (EditText) findViewById(R.id.sellPrice);
            sellprice.setFocusable(false);
            sellprice.setClickable(true);

            placement_sparepart.setText(getIntent().getStringExtra("placement"));
            EditText placement = (EditText) findViewById(R.id.placement_sparepart);
            placement.setFocusable(false);
            placement.setClickable(true);

            Log.d("type",getIntent().getStringExtra("type"));
            Spinner type = (Spinner) findViewById(R.id.idSparepartType);
            type.setFocusable(false);
            type.setClickable(true);

            spinnerPosition.setSelection(getIndex(spinnerPosition,getIntent().getStringExtra("position")));
            Spinner postition = (Spinner) findViewById(R.id.position_sparepart);
            postition.setFocusable(false);
            postition.setClickable(true);

            spinnerPlace.setSelection(getIndex(spinnerPlace,getIntent().getStringExtra("place")));
            Spinner place = (Spinner) findViewById(R.id.place_sparepart);
            place.setFocusable(false);
            place.setClickable(true);

            number_sparepart.setText(""+getIntent().getIntExtra("number", 0 ));
            EditText number = (EditText) findViewById(R.id.number_sparepart);
            number.setFocusable(false);
            number.setClickable(true);


            btn_add_image.setVisibility(View.GONE);

            addButton.setVisibility(View.GONE);
            addButton.setText("UBAH");

            Picasso.get().load("https://p3l.yafetrakan.com/images/"+getIntent().getStringExtra("image")).memoryPolicy(MemoryPolicy.NO_CACHE) .networkPolicy(NetworkPolicy.NO_CACHE).into(add_sparepart_image);
        }
        else{
            deleteButton.setVisibility(View.GONE);
            editButton.setVisibility(View.GONE);
        }

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent = new Intent(AddSparepart.this, Beranda.class);
                intent.putExtra("addDialog", 1);
                startActivity(intent);
            }
        });

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addButton.setVisibility(View.VISIBLE);
                addButton.setText("UBAH");

                editButton.setVisibility(View.VISIBLE);

                idSparepart.setText(getIntent().getStringExtra("id"));
                EditText idsparepart = (EditText) findViewById(R.id.idSparepart);
                idsparepart.setFocusableInTouchMode(true);

                sparepartName.setText(getIntent().getStringExtra("name"));
                EditText sparepartname = (EditText) findViewById(R.id.sparepartName);
                sparepartname.setFocusableInTouchMode(true);

                merk_sparepart.setText(getIntent().getStringExtra("merk"));
                EditText merksparepart = (EditText) findViewById(R.id.merk_sparepart);
                merksparepart.setFocusableInTouchMode(true);

                stock_sparepart.setText(""+getIntent().getIntExtra("stock", 0 ));
                EditText stocksparepart = (EditText) findViewById(R.id.stock_sparepart);
                stocksparepart.setFocusableInTouchMode(true);

                minStock.setText(""+getIntent().getIntExtra("minstock", 0 ));
                EditText minstock = (EditText) findViewById(R.id.minStock);
                minstock.setFocusableInTouchMode(true);

                purchasePrice.setText(""+getIntent().getDoubleExtra("purchaseprice", 0.00 ));
                EditText purchaseprice = (EditText) findViewById(R.id.purchasePrice);
                purchaseprice.setFocusableInTouchMode(true);

                sellPrice.setText(""+getIntent().getDoubleExtra("sellprice", 0.00 ));
                EditText sellprice = (EditText) findViewById(R.id.sellPrice);
                sellprice.setFocusableInTouchMode(true);

                placement_sparepart.setText(getIntent().getStringExtra("placement"));
                EditText placement = (EditText) findViewById(R.id.placement_sparepart);
                placement.setFocusableInTouchMode(true);


                Log.d("type", getIntent().getStringExtra("type"));
                Spinner type = (Spinner) findViewById(R.id.idSparepartType);
                type.setFocusableInTouchMode(true);

                spinnerPosition.setSelection(getIndex(spinnerPosition, getIntent().getStringExtra("position")));
                Spinner postition = (Spinner) findViewById(R.id.position_sparepart);
                postition.setFocusableInTouchMode(true);

                spinnerPlace.setSelection(getIndex(spinnerPlace, getIntent().getStringExtra("place")));
                Spinner place = (Spinner) findViewById(R.id.place_sparepart);
                place.setFocusableInTouchMode(true);

                number_sparepart.setText("" + getIntent().getIntExtra("number", 0));
                EditText number = (EditText) findViewById(R.id.number_sparepart);
                number.setFocusableInTouchMode(true);

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


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) { // Mendapatkan data gambar yang dipilih

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Image_Request_Code && resultCode == RESULT_OK && data != null && data.getData() != null) {

            FilePathUri = data.getData();
//            Log.d("Test1",FilePathUri.toString());

            try {

                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), FilePathUri);
                ImageBitmap=bitmap;
                add_sparepart_image.setImageBitmap(bitmap);

            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private int getIndex(Spinner spinner, String myString){
        for (int i=0;i<spinner.getCount();i++){
            if(myString.equalsIgnoreCase("BLK"))
            {
                myString="Belakang";
            }
            else if(myString.equalsIgnoreCase("TGH"))
            {
                myString="Tengah";
            }
            else if(myString.equalsIgnoreCase("TGH"))
            {
                myString="Tengah";
            }
            else if(myString.equalsIgnoreCase("KACA"))
            {
                myString="Rak Kaca";
            }
            else if(myString.equalsIgnoreCase("KAYU"))
            {
                myString="Rak Kayu";
            }
            else if(myString.equalsIgnoreCase("BAN"))
            {
                myString="Tumpukan Ban";
            }
            else if(myString.equalsIgnoreCase("DUS"))
            {
                myString="Tumpukan Dus";
            }
            else
            {
                myString=myString;
            }
            if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(myString)){

                return i;
            }
        }

        return 0;
    }

    private void init(){
        backButton = findViewById(R.id.btnBack);
        addButton = findViewById(R.id.btnAdd);
        editButton = findViewById(R.id.btnEdit);
        deleteButton = findViewById(R.id.btnDelete);
        idSparepart = findViewById(R.id.idSparepart);
        sparepartName = findViewById(R.id.sparepartName);
        merk_sparepart = findViewById(R.id.merk_sparepart);
        stock_sparepart = findViewById(R.id.stock_sparepart);
        minStock = findViewById(R.id.minStock);
        purchasePrice = findViewById(R.id.purchasePrice);
        sellPrice = findViewById(R.id.sellPrice);
        placement_sparepart = findViewById(R.id.placement_sparepart);
        spinnerPosition = findViewById(R.id.position_sparepart);
        spinnerPlace =  findViewById(R.id.place_sparepart);
        number_sparepart = findViewById(R.id.number_sparepart);
        add_sparepart_image = findViewById(R.id.add_sparepart_image);
        btn_add_image = findViewById(R.id.btn_add_image);
        spinnerType = findViewById(R.id.idSparepartType);
        title = findViewById(R.id.title);

        Retrofit retrofit = new retrofit2.Retrofit.Builder()
                .baseUrl("https://p3l.yafetrakan.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiClient apiClient = retrofit.create(ApiClient.class);
        Call<SparepartTypeList> call = apiClient.getSparepartTypes();
        call.enqueue(new Callback<SparepartTypeList>() {
            @Override
            public void onResponse(Call<SparepartTypeList> call, Response<SparepartTypeList> response) {
                List<SparepartType> spinnerArrayList = response.body().getData();
                ArrayList<SparepartType> sparepartTypes = new ArrayList<>();
                for (int i = 0; i < response.body().getData().size(); i++) {
                    sparepartTypes.add(new SparepartType(response.body().getData().get(i).getIdSparepartType(),response.body().getData().get(i).getSparepartTypeName()));
                }

                ArrayAdapter<SparepartType> adapter = new ArrayAdapter<SparepartType>(getApplicationContext(),
                        android.R.layout.simple_spinner_dropdown_item
                        , sparepartTypes);

                spinnerType.setAdapter(adapter);
                Log.d("items",spinnerType.getItemAtPosition(0).toString());
                if(simpan > -1) {
                    spinnerType.setSelection(getIndex(spinnerType, getIntent().getStringExtra("type")));
                }

            }

            @Override
            public void onFailure(Call<SparepartTypeList> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Data Dropdown gagal diambil", Toast.LENGTH_SHORT).show();
            }
        });

        ArrayList<Position> positionList = new ArrayList<>();
        positionList.add(new Position("DPN","Depan"));
        positionList.add(new Position("BLK","Belakang"));
        positionList.add(new Position("TGH","Tengah"));
        ArrayAdapter<Position> adapter = new ArrayAdapter<Position>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, positionList);
        spinnerPosition.setAdapter(adapter);

        ArrayList<Place> placeList = new ArrayList<>();
        placeList.add(new Place("DUS","Tumpukan Dus"));
        placeList.add(new Place("BAN","Tumpukan Ban"));
        placeList.add(new Place("KACA","Rak Kaca"));
        placeList.add(new Place("KAYU","Rak Kayu"));
        ArrayAdapter<Place> adapter2 = new ArrayAdapter<Place>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, placeList);
        spinnerPlace.setAdapter(adapter2);


    }

    public void store(){

        if(idSparepart.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(), "id sparepart tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }
        else if(sparepartName.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(), "nama sparepart tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }
        else if(merk_sparepart.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(), "merk sparepart tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }
        else if(stock_sparepart.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(), "stock sparepart tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }
        else if(Integer.parseInt(stock_sparepart.getText().toString())==0){
            Toast.makeText(getApplicationContext(), "stock sparepart tidak boleh 0", Toast.LENGTH_SHORT).show();
        }
        else if(minStock.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(), "stock sparepart tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }
        else if(Integer.parseInt(minStock.getText().toString())==0){
            Toast.makeText(getApplicationContext(), "stock minimal tidak boleh 0", Toast.LENGTH_SHORT).show();
        }
        else if(purchasePrice.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(), "harga beli tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }
        else if(Double.parseDouble(purchasePrice.getText().toString()) == 0){
            Toast.makeText(getApplicationContext(), "harga beli tidak boleh 0", Toast.LENGTH_SHORT).show();
        }
        else if(sellPrice.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(), "harga beli tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }
        else if(Double.parseDouble(sellPrice.getText().toString())== 0){
            Toast.makeText(getApplicationContext(), "harga jual tidak boleh 0", Toast.LENGTH_SHORT).show();
        }
        else if(Double.parseDouble(sellPrice.getText().toString()) == Double.parseDouble(purchasePrice.getText().toString())){
            Toast.makeText(getApplicationContext(), "harga jual tidak boleh sama dengan harga beli", Toast.LENGTH_SHORT).show();
        }
        else if(number_sparepart.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(),"nomor sparepart tidak boleh kosong",Toast.LENGTH_SHORT).show();
        }
        else if(Integer.parseInt(number_sparepart.getText().toString())==0){
            Toast.makeText(getApplicationContext(), "nomor sparepart tidak boleh 0", Toast.LENGTH_SHORT).show();
        }
        else{
            try{
                final ProgressDialog progressDialog = new ProgressDialog(AddSparepart.this, R.style.AppTheme_Dark_Dialog);
                progressDialog.setMessage("Memproses Data...");
                progressDialog.show();
                String place = idPosition +'-'+idPlace+'-'+number_sparepart.getText().toString();
                Retrofit retrofit = new retrofit2.Retrofit.Builder()
                        .baseUrl("https://p3l.yafetrakan.com/api/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                ApiClient apiClient = retrofit.create(ApiClient.class);

                if(ImageBitmap!=null) {
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    ImageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                    byte[] data = baos.toByteArray();

                    RequestBody requestFile = RequestBody.create(MediaType.parse("image/jpeg"), data);

                    RequestBody id_sparepart =
                            RequestBody.create(
                                    MediaType.parse("multipart/form-data"), idSparepart.getText().toString());

                    RequestBody sparepart_name =
                            RequestBody.create(
                                    MediaType.parse("multipart/form-data"), sparepartName.getText().toString());

                    RequestBody merk =
                            RequestBody.create(
                                    MediaType.parse("multipart/form-data"), merk_sparepart.getText().toString());

                    RequestBody stock =
                            RequestBody.create(
                                    MediaType.parse("multipart/form-data"), stock_sparepart.getText().toString());

                    RequestBody min_stock =
                            RequestBody.create(
                                    MediaType.parse("multipart/form-data"), minStock.getText().toString());

                    RequestBody purchase_price =
                            RequestBody.create(
                                    MediaType.parse("multipart/form-data"), purchasePrice.getText().toString());

                    RequestBody sell_price =
                            RequestBody.create(
                                    MediaType.parse("multipart/form-data"), sellPrice.getText().toString());

                    RequestBody placement =
                            RequestBody.create(
                                    MediaType.parse("multipart/form-data"), place);


                    RequestBody id_sparepart_type =
                            RequestBody.create(
                                    MediaType.parse("multipart/form-data"), String.valueOf(idType));

                    MultipartBody.Part image = MultipartBody.Part.createFormData("image", "image.jpg", requestFile);

                    Call<ResponseBody> call = apiClient.addSparepart(image,id_sparepart, sparepart_name, merk, stock, min_stock, purchase_price, sell_price, placement, id_sparepart_type);

                    call.enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {

//                mProgressBar.setVisibility(View.GONE);

                            if (response.isSuccessful()) {

                                ResponseBody responseBody = response.body();
                                Log.d("SUKSES",responseBody.toString());
                                Toast.makeText(AddSparepart.this, "Sukses", Toast.LENGTH_SHORT).show();
                                final Intent intent = new Intent(AddSparepart.this, Beranda.class);
                                intent.putExtra("addDialog", 2);
                                progressDialog.dismiss();
                                startActivity(intent);
//                    mBtImageShow.setVisibility(View.VISIBLE);
//                     mImageUrl = URL + responseBody.getPath();
//                    Snackbar.make(findViewById(R.id.content), responseBody.getMessage(),Snackbar.LENGTH_SHORT).show();

                            } else {
                                progressDialog.dismiss();
                                Log.d( "onResponse: ",response.message());
                                Toast.makeText(AddSparepart.this, "Gagal", Toast.LENGTH_SHORT).show();

                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            progressDialog.dismiss();
                            Log.d("onFailure: ",t.toString());

//                mProgressBar.setVisibility(View.GONE);
//                Log.d(TAG, "onFailure: "+t.getLocalizedMessage());
                        }
                    });
                }
                else{
                    RequestBody id_sparepart =
                            RequestBody.create(
                                    MediaType.parse("multipart/form-data"), idSparepart.getText().toString());

                    RequestBody sparepart_name =
                            RequestBody.create(
                                    MediaType.parse("multipart/form-data"), sparepartName.getText().toString());

                    RequestBody merk =
                            RequestBody.create(
                                    MediaType.parse("multipart/form-data"), merk_sparepart.getText().toString());

                    RequestBody stock =
                            RequestBody.create(
                                    MediaType.parse("multipart/form-data"), stock_sparepart.getText().toString());

                    RequestBody min_stock =
                            RequestBody.create(
                                    MediaType.parse("multipart/form-data"), minStock.getText().toString());

                    RequestBody purchase_price =
                            RequestBody.create(
                                    MediaType.parse("multipart/form-data"), purchasePrice.getText().toString());

                    RequestBody sell_price =
                            RequestBody.create(
                                    MediaType.parse("multipart/form-data"), sellPrice.getText().toString());

                    RequestBody placement =
                            RequestBody.create(
                                    MediaType.parse("multipart/form-data"), place);


                    RequestBody id_sparepart_type =
                            RequestBody.create(
                                    MediaType.parse("multipart/form-data"), String.valueOf(idType));

                    MultipartBody.Part image = null;

                    Call<ResponseBody> call = apiClient.addSparepart(image,id_sparepart, sparepart_name, merk, stock, min_stock, purchase_price, sell_price, placement, id_sparepart_type);

                    call.enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {

//                mProgressBar.setVisibility(View.GONE);

                            if (response.isSuccessful()) {

                                ResponseBody responseBody = response.body();
                                Log.d("SUKSES",responseBody.toString());
                                Toast.makeText(AddSparepart.this, "Sukses", Toast.LENGTH_SHORT).show();
                                final Intent intent = new Intent(AddSparepart.this, Beranda.class);
                                intent.putExtra("addDialog", 2);
                                progressDialog.dismiss();
                                startActivity(intent);
//                    mBtImageShow.setVisibility(View.VISIBLE);
//                     mImageUrl = URL + responseBody.getPath();
//                    Snackbar.make(findViewById(R.id.content), responseBody.getMessage(),Snackbar.LENGTH_SHORT).show();

                            } else {
                                progressDialog.dismiss();
                                Log.d( "onResponse: ",response.message());
                                Toast.makeText(AddSparepart.this, "Gagal", Toast.LENGTH_SHORT).show();

                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            progressDialog.dismiss();
                            Log.d("onFailure: ",t.toString());

//                mProgressBar.setVisibility(View.GONE);
//                Log.d(TAG, "onFailure: "+t.getLocalizedMessage());
                        }
                    });
                }

            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void update(){
        if(idSparepart.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(), "id sparepart tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }
        else if(sparepartName.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(), "nama sparepart tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }
        else if(merk_sparepart.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(), "merk sparepart tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }
        else if(stock_sparepart.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(), "stock sparepart tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }
        else if(Integer.parseInt(stock_sparepart.getText().toString())==0){
            Toast.makeText(getApplicationContext(), "stock sparepart tidak boleh 0", Toast.LENGTH_SHORT).show();
        }
        else if(minStock.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(), "stock sparepart tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }
        else if(Integer.parseInt(minStock.getText().toString())==0){
            Toast.makeText(getApplicationContext(), "stock minimal tidak boleh 0", Toast.LENGTH_SHORT).show();
        }
        else if(purchasePrice.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(), "harga beli tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }
        else if(Double.parseDouble(purchasePrice.getText().toString()) == 0){
            Toast.makeText(getApplicationContext(), "harga beli tidak boleh 0", Toast.LENGTH_SHORT).show();
        }
        else if(sellPrice.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(), "harga beli tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }
        else if(Double.parseDouble(sellPrice.getText().toString())== 0){
            Toast.makeText(getApplicationContext(), "harga jual tidak boleh 0", Toast.LENGTH_SHORT).show();
        }
        else if(Double.parseDouble(sellPrice.getText().toString()) == Double.parseDouble(purchasePrice.getText().toString())){
            Toast.makeText(getApplicationContext(), "harga jual tidak boleh sama dengan harga beli", Toast.LENGTH_SHORT).show();
        }
        else if(number_sparepart.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(),"nomor sparepart tidak boleh kosong",Toast.LENGTH_SHORT).show();
        }
        else if(Integer.parseInt(number_sparepart.getText().toString())==0){
            Toast.makeText(getApplicationContext(), "nomor sparepart tidak boleh 0", Toast.LENGTH_SHORT).show();
        }
        else{
            try{
                final ProgressDialog progressDialog = new ProgressDialog(AddSparepart.this, R.style.AppTheme_Dark_Dialog);
                progressDialog.setMessage("Memproses Data...");
                progressDialog.show();
                String place = idPosition +"-"+idPlace+"-"+number_sparepart.getText().toString();
                Retrofit retrofit = new retrofit2.Retrofit.Builder()
                        .baseUrl("https://p3l.yafetrakan.com/api/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                ApiClient apiClient = retrofit.create(ApiClient.class);
                if(ImageBitmap!=null) {

                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    ImageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                    byte[] data = baos.toByteArray();

                    RequestBody requestFile = RequestBody.create(MediaType.parse("image/jpeg"), data);


                    RequestBody id_sparepart =
                            RequestBody.create(
                                    MediaType.parse("multipart/form-data"), idSparepart.getText().toString());

                    RequestBody sparepart_name =
                            RequestBody.create(
                                    MediaType.parse("multipart/form-data"), sparepartName.getText().toString());

                    RequestBody merk =
                            RequestBody.create(
                                    MediaType.parse("multipart/form-data"), merk_sparepart.getText().toString());

                    RequestBody stock =
                            RequestBody.create(
                                    MediaType.parse("multipart/form-data"), stock_sparepart.getText().toString());

                    RequestBody min_stock =
                            RequestBody.create(
                                    MediaType.parse("multipart/form-data"), minStock.getText().toString());

                    RequestBody purchase_price =
                            RequestBody.create(
                                    MediaType.parse("multipart/form-data"), purchasePrice.getText().toString());

                    RequestBody sell_price =
                            RequestBody.create(
                                    MediaType.parse("multipart/form-data"), sellPrice.getText().toString());
                    RequestBody placement =
                            RequestBody.create(
                                    MediaType.parse("multipart/form-data"), place);

                    RequestBody id_sparepart_type =
                            RequestBody.create(
                                    MediaType.parse("multipart/form-data"), String.valueOf(idType));

                    MultipartBody.Part image = MultipartBody.Part.createFormData("image", "image.jpg", requestFile);


                    Call<ResponseBody> call = apiClient.updateSparepart(idSparepart.getText().toString(),image, sparepart_name, merk, stock, min_stock, purchase_price, sell_price, placement, id_sparepart_type);

                    call.enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {

                            //                mProgressBar.setVisibility(View.GONE);

                            if (response.isSuccessful()) {

                                ResponseBody responseBody = response.body();
                                Log.d("SUKSES", responseBody.toString());
                                Toast.makeText(AddSparepart.this, "Sukses", Toast.LENGTH_SHORT).show();
                                final Intent intent = new Intent(AddSparepart.this, Beranda.class);
                                intent.putExtra("addDialog", 2);
                                progressDialog.dismiss();
                                startActivity(intent);
                                //                    mBtImageShow.setVisibility(View.VISIBLE);
                                //                     mImageUrl = URL + responseBody.getPath();
                                //                    Snackbar.make(findViewById(R.id.content), responseBody.getMessage(),Snackbar.LENGTH_SHORT).show();

                            } else {
                                progressDialog.dismiss();
                                Log.d("onResponse: ", response.message());
                                Toast.makeText(AddSparepart.this, response.toString(), Toast.LENGTH_SHORT).show();

                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            progressDialog.dismiss();
                            Log.d("onFailure: ", t.toString());

                            //                mProgressBar.setVisibility(View.GONE);
                            //                Log.d(TAG, "onFailure: "+t.getLocalizedMessage());
                        }
                    });
                }
                else
                {

                    RequestBody id_sparepart =
                            RequestBody.create(
                                    MediaType.parse("multipart/form-data"), idSparepart.getText().toString());

                    RequestBody sparepart_name =
                            RequestBody.create(
                                    MediaType.parse("multipart/form-data"), sparepartName.getText().toString());

                    RequestBody merk =
                            RequestBody.create(
                                    MediaType.parse("multipart/form-data"), merk_sparepart.getText().toString());

                    RequestBody stock =
                            RequestBody.create(
                                    MediaType.parse("multipart/form-data"), stock_sparepart.getText().toString());

                    RequestBody min_stock =
                            RequestBody.create(
                                    MediaType.parse("multipart/form-data"), minStock.getText().toString());

                    RequestBody purchase_price =
                            RequestBody.create(
                                    MediaType.parse("multipart/form-data"), purchasePrice.getText().toString());

                    RequestBody sell_price =
                            RequestBody.create(
                                    MediaType.parse("multipart/form-data"), sellPrice.getText().toString());

                    RequestBody placement =
                            RequestBody.create(
                                    MediaType.parse("multipart/form-data"), place);

                    RequestBody id_sparepart_type =
                            RequestBody.create(
                                    MediaType.parse("multipart/form-data"), String.valueOf(idType));

                    MultipartBody.Part image = null;

                    Call<ResponseBody> call = apiClient.updateSparepart(idSparepart.getText().toString(),image, sparepart_name, merk, stock, min_stock, purchase_price, sell_price, placement, id_sparepart_type);

                    call.enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {


                            if (response.isSuccessful()) {

                                ResponseBody responseBody = response.body();
                                Log.d("SUKSES", responseBody.toString());
                                Toast.makeText(AddSparepart.this, "Sukses", Toast.LENGTH_SHORT).show();
                                final Intent intent = new Intent(AddSparepart.this, Beranda.class);
                                intent.putExtra("addDialog", 2);
                                progressDialog.dismiss();
                                startActivity(intent);
                                //                    mBtImageShow.setVisibility(View.VISIBLE);
                                //                     mImageUrl = URL + responseBody.getPath();
                                //                    Snackbar.make(findViewById(R.id.content), responseBody.getMessage(),Snackbar.LENGTH_SHORT).show();

                            } else {
                                progressDialog.dismiss();
                                Log.d("onResponse: ", response.message());
                                Toast.makeText(AddSparepart.this, response.toString(), Toast.LENGTH_SHORT).show();

                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            progressDialog.dismiss();
                            Log.d("onFailure: ", t.toString());

                            //                mProgressBar.setVisibility(View.GONE);
                            //                Log.d(TAG, "onFailure: "+t.getLocalizedMessage());
                        }
                    });
                }

            }catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

}

//        Spinner positionSpinner = (Spinner) findViewById(R.id.position);
//        Spinner placeSpinner = (Spinner) findViewById(R.id.place);
//
//        ArrayAdapter<String> positionAdapter = new ArrayAdapter<String>(AddSparepart.this, android.R.layout.simple_list_item_1,
//                getResources().getStringArray(R.array.position));
//        positionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        positionSpinner.setAdapter(positionAdapter);
//
//        ArrayAdapter<String> placeAdapter = new ArrayAdapter<String>(AddSparepart.this, android.R.layout.simple_list_item_1,
//                getResources().getStringArray(R.array.place));
//        placeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        positionSpinner.setAdapter(placeAdapter);
