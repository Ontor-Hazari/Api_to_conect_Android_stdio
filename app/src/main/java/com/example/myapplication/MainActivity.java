package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.myapplication.Model.ApiModel;
import com.example.myapplication.Model.Example;
import com.example.myapplication.Model.Result;
import com.example.myapplication.Network.ApiInterfce;
import com.example.myapplication.Network.Apiclient;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    Retrofit retrofit;
    ApiInterfce apiInterfce;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        textView = (TextView) findViewById(R.id.textView);


        retrofit = Apiclient.getClient();

        apiInterfce = retrofit.create(ApiInterfce.class);


        apiInterfce.getPosts().enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                if (!response.isSuccessful()) {
                    textView.setText(response.code());
                    return;
                }

                List<Result> list = response.body().getResult();

                String data = "";
                for (int i = 0; i < list.size(); i++) {
                    data += "Name : " + list.get(i).getName()+"\n";
                    data += "Email : " + list.get(i).getEmail()+"\n\n";
                }

                textView.setText("" + data);
            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {

                textView.setText(t.getMessage());

            }
        });


       /* apiInterfce.getPosts().enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    Log.d("MainActivity", "onResponse: " + response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
            }
        });*/


    }
}