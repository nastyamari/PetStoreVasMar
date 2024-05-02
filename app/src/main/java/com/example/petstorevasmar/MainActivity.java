package com.example.petstorevasmar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private ProgressBar mProgressBar;
    private TextView mTextView;
    private EditText mEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main2);

        mTextView = (TextView) findViewById(R.id.textView2);
        mEditText = (EditText) findViewById(R.id.editTextText);
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar2);
        mProgressBar.setVisibility(View.INVISIBLE);
    }


    public void onClick(View view) {
        mProgressBar.setVisibility(View.VISIBLE);


        GitHubServicePrDva gitHubService = GitHubServicePrDva.retrofit.create(GitHubServicePrDva.class);
        int id = Integer.parseInt(mEditText.getText().toString());
        final Call<PetStore> call = gitHubService.getPet(id);

        call.enqueue(new Callback<PetStore>() {
            @Override
            public void onResponse(Call<PetStore> call, Response<PetStore> response) {
                // response.isSuccessfull() is true if the response code is 2xx
                if (response.isSuccessful()) {
               PetStore user = response.body();

                    // Получаем json из github-сервера и конвертируем его в удобный вид
                   mTextView.setText(user.getName());

                    mProgressBar.setVisibility(View.INVISIBLE);
                } else {
                    int statusCode = response.code();

                    // handle request errors yourself
                    ResponseBody errorBody = response.errorBody();
                    try {
                        mTextView.setText(errorBody.string());
                        mProgressBar.setVisibility(View.INVISIBLE);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<PetStore> call, Throwable throwable) {
                mTextView.setText("Что-то пошло не так: " + throwable.getMessage());
            }
        });
    }


}