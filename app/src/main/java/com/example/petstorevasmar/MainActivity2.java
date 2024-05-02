package com.example.petstorevasmar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import android.view.View;
import android.widget.Adapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity2 extends AppCompatActivity {
    private ProgressBar mProgressBar;
    RecyclerView mRecyclerView;

    List<PetStore> mFlowers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);



        mFlowers = new ArrayList<>();

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);

        PetAdapter adapter = new PetAdapter(mFlowers);
        mRecyclerView.setAdapter(adapter);


        PetStoreAPI flowersAPI = PetStoreAPI.retrofit.create(PetStoreAPI.class);
        final Call<List<PetStore>> call = flowersAPI.getData();
        call.enqueue(new Callback<List<PetStore>>() {
                         @Override
                         public void onResponse(Call<List<PetStore>> call, Response<List<PetStore>> response) {
                             // response.isSuccessfull() возвращает true если код ответа 2xx
                             if (response.isSuccessful()) {
                                 mFlowers.addAll(response.body());
                                 mRecyclerView.getAdapter().notifyDataSetChanged();

                             } else {
                                 // Обрабатываем ошибку
                                 ResponseBody errorBody = response.errorBody();
                                 try {
                                     Toast.makeText(MainActivity2.this, errorBody.string(),
                                             Toast.LENGTH_SHORT).show();

                                 } catch (IOException e) {
                                     e.printStackTrace();
                                 }
                             }
                         }

                         @Override
                         public void onFailure(Call<List<PetStore>> call, Throwable throwable) {
                             Toast.makeText(MainActivity2.this, "Что-то пошло не так",
                                     Toast.LENGTH_SHORT).show();

                         }
                     }
        );
    }
}
