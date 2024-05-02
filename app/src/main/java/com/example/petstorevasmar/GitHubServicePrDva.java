package com.example.petstorevasmar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
public interface GitHubServicePrDva {
    @GET("/pet/{id}")
    Call<PetStore> getPet(
            @Path("id") int id
    );

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://petstore.swagger.io/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}
