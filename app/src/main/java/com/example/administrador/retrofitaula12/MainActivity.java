package com.example.administrador.retrofitaula12;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends Activity {


    Public Consultar (View view){

        Log.i("teste","iniciando...");
        //Retrofit
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        Log.i("teste","objeto retrofit criado...");
        ApiEndpoint apiService = retrofit.create(ApiEndpoint.class);
        Log.i("teste","chamando api...");
        Integer userid = 0;
        EditText ed = findViewById(R.id.editText);
        userid = Integer.parseInt(ed.getText().toString());
        Call<UserClass> call = apiService.ObterPosts(userid);
        //chamada assíncrona
        call.enqueue(new Callback<UserClass>() {
            @Override
            public void onResponse(Call<UserClass> call, Response<UserClass> response) {
                int statusCode = response.code();
                UserClass user = response.body();

                Log.i("teste","statuscode: " + statusCode);
                Log.i("teste", "Cidade do usuário: " + user.getTitle());
            }
            @Override
            public void onFailure(Call<UserClass> call, Throwable t) {
                // Log error here since request failed
                Log.i("teste",t.toString());
            }
        });


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}