package com.example.retrofitdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
TextView tv;
String Url ="https://jsonplaceholder.typicode.com/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv=findViewById(R.id.tv);
        tv.setText("");

       Retrofit retrofit = new Retrofit.Builder()
               .baseUrl(Url)
               .addConverterFactory(GsonConverterFactory.create())
               .build();

       myapi api = retrofit.create(myapi.class);
       Call<List<model>> call = api.getmodels();

       call.enqueue(new Callback<List<model>>() {
           @Override
           public void onResponse(Call<List<model>> call, Response<List<model>> response) {
               List<model> data = response.body();
               for (int i=0;i<data.size();i++)
               {
                   tv.append("SL No :"+data.get(i).id+"\n"+data.get(i).title+"\n\n\n");
               }
           }

           @Override
           public void onFailure(Call<List<model>> call, Throwable t) {

           }
       });


    }
}