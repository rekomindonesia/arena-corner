package com.mediasoftindonesia.taniapps;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mediasoftindonesia.taniapps.api.ApiClient;
import com.mediasoftindonesia.taniapps.api.Produk;
import com.mediasoftindonesia.taniapps.api.ProdukJadi;
import com.mediasoftindonesia.taniapps.api.ProdukJadiService;
import com.mediasoftindonesia.taniapps.api.ProdukService;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KatalogFragment extends AppCompatActivity implements View.OnClickListener {

    private final String TAG = this.getClass().getName();

    EditText mNamaProduk, mHargaProduk, mDeskripsiProduk;
    ImageView mFoto, mCamera, mGaleri, mSilang;
    Button mUnggah, mUnggah2;
    private String id_produk;

    private Calendar calendar;
    private TextView dateView;
    private int year, month, day;

    final int CAMERA_REQUEST = 13323;
    final int GALLERY_REQUEST = 22131;
    private static final int IMG_REQUEST = 777;
    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_katalog_fragment);

        dateView = (TextView) findViewById(R.id.txt_kalender);
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);

        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        showDate(year, month + 1, day);

        mNamaProduk = (EditText) findViewById(R.id.editTextNamaProduk);
        mHargaProduk = (EditText) findViewById(R.id.editTextHargaProduk);
        mDeskripsiProduk = (EditText) findViewById(R.id.editTextDeskripsiProduk);

        mFoto = (ImageView) findViewById(R.id.imageViewFotoProduk);
        mCamera = (ImageView) findViewById(R.id.camera);
        mGaleri = (ImageView) findViewById(R.id.gallery);
        mSilang = (ImageView) findViewById(R.id.imageViewSilang);
        mSilang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(KatalogFragment.this, MainActivity.class);
                startActivity(in);
            }
        });

        mUnggah = (Button) findViewById(R.id.buttonUnggah);
        mUnggah2 = (Button) findViewById(R.id.buttonUnggah2);

        mGaleri.setOnClickListener(this);
        mCamera.setOnClickListener(this);
        mUnggah.setOnClickListener(this);
        mUnggah2.setOnClickListener(this);
    }

    private void showDate(int year, int month, int day) {
        dateView.setText(new StringBuilder().append(year).append("/")
                .append(month).append("/").append(day));
    }

    @SuppressWarnings("deprecation")
    public void setDate(View view){
        showDialog(999);
        //akan menampilkan teks ketika kalendar muncul setelah menekan tombol
        Toast.makeText(getApplicationContext(), "Pilih Tangal", Toast.LENGTH_SHORT)
                .show();
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 999) {
            return new DatePickerDialog(this, myDateListener, year, month, day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
            // TODO Auto-generated method stub
            // arg1 = year
            // arg2 = month
            // arg3 = day
            showDate(arg1, arg2+1, arg3);
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==IMG_REQUEST && resultCode==RESULT_OK && data!=null){
            Uri path = data.getData();

            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),path);
                mFoto.setImageBitmap(bitmap);
                mFoto.setVisibility(View.VISIBLE);
                mGaleri.setEnabled(false);
                mUnggah.setEnabled(true);
                mUnggah2.setEnabled(true);
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    private void uploadImage(){

        final String foto = imageToString();
        final String id_petani = id_produk;
        final String nama = mNamaProduk.getText().toString();
        final String harga = mHargaProduk.getText().toString();
        final String deskripsi = mDeskripsiProduk.getText().toString();
        final String tanggal = dateView.getText().toString();

        if (nama.equals("") || harga.equals("") || deskripsi.equals("") || foto.equals("")) {
            Toast.makeText(KatalogFragment.this, "Isi nama produk, harga, deskripsi, dan foto", Toast.LENGTH_LONG).show();
        }else{
            Intent intent = new Intent(KatalogFragment.this, MainActivity.class);
            startActivity(intent);
            Toast.makeText(KatalogFragment.this, "Produk mentah berhasil di tambahkan", Toast.LENGTH_LONG).show();

            ProdukService produkService = ApiClient.getApiClient().create(ProdukService.class);

            Call<Produk> call = produkService.uploadImage(id_petani, nama,harga,deskripsi,foto,tanggal);
            call.enqueue(new Callback<Produk>() {
                @Override
                public void onResponse(Call<Produk> call, Response<Produk> response) {

                    Produk produk = response.body();
                    Toast.makeText(KatalogFragment.this,"Server Response: "+produk.getResponse(),Toast.LENGTH_LONG).show();
                    mFoto.setVisibility(View.GONE);
                    mGaleri.setEnabled(true);
                    mUnggah.setEnabled(false);
                }

                @Override
                public void onFailure(Call<Produk> call, Throwable throwable) {
                }
            });
        }
    }

    private void uploadImage2(){

        final String foto = imageToString();
        final String id_petani = id_produk;
        final String nama = mNamaProduk.getText().toString();
        final String harga = mHargaProduk.getText().toString();
        final String deskripsi = mDeskripsiProduk.getText().toString();
        final String tanggal = dateView.getText().toString();

        if (nama.equals("") || harga.equals("") || deskripsi.equals("") || foto.equals("")) {
            Toast.makeText(KatalogFragment.this, "Isi nama produk, harga, deskripsi, dan foto", Toast.LENGTH_LONG).show();
        }else{
            Intent intent = new Intent(KatalogFragment.this, MainActivity.class);
            startActivity(intent);
            Toast.makeText(KatalogFragment.this, "Produk jadi berhasil di tambahkan", Toast.LENGTH_LONG).show();

            ProdukJadiService produkJadiService = ApiClient.getApiClient().create(ProdukJadiService.class);

            Call<ProdukJadi> call = produkJadiService.uploadImage2(id_petani, nama,harga,deskripsi,foto,tanggal);
            call.enqueue(new Callback<ProdukJadi>() {
                @Override
                public void onResponse(Call<ProdukJadi> call, Response<ProdukJadi> response) {

                    ProdukJadi produk = response.body();
                    Toast.makeText(KatalogFragment.this,"Server Response: "+produk.getResponse(),Toast.LENGTH_LONG).show();
                    mFoto.setVisibility(View.GONE);
                    mGaleri.setEnabled(true);
                    mUnggah.setEnabled(false);
                }

                @Override
                public void onFailure(Call<ProdukJadi> call, Throwable throwable) {
                }
            });
        }
    }

    private void selectImage(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,IMG_REQUEST);
    }

    /*private void selectImageCamera(){
        try {
            startActivityForResult(cameraPhoto.takePhotoIntent(), CAMERA_REQUEST);
            cameraPhoto.addToGallery();
        } catch (IOException e) {
            Toast.makeText(getApplicationContext(),
                    "Something Wrong while taking photos", Toast.LENGTH_SHORT).show();
        }
    }*/

    private String imageToString(){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        byte[] imgByte = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(imgByte,Base64.DEFAULT);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.gallery:
                selectImage();
                break;

            case R.id.camera:
                //selectImageCamera();
                break;

            case R.id.buttonUnggah:
                uploadImage();
                break;

            case R.id.buttonUnggah2:
                uploadImage2();
                break;
        }
    }
}
