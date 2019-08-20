package com.gedgonz.retrofitapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.gedgonz.retrofitapp.Interface.IJsonplaceholderApi;
import com.gedgonz.retrofitapp.Model.Posts;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {


    private TextView mJsonTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mJsonTextView = (TextView) findViewById(R.id.jsontext);
        getPosts();
    }

    private void getPosts()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        IJsonplaceholderApi api =retrofit.create(IJsonplaceholderApi.class);

        Call<List<Posts>> call = api.getPosts();

        call.enqueue(new Callback<List<Posts>>() {
            @Override
            public void onResponse(Call<List<Posts>> call, Response<List<Posts>> response) {

                if(!response.isSuccessful()){
                    mJsonTextView.setText("Codigo: "+response.code());
                    return;
                }
                
                List<Posts> poastsList = response.body();

                for (Posts posts :poastsList) {
                    String content ="";
                    content += "UserId: "+posts.getUserId()+" \n";
                    content += "Id: "+posts.getId()+" \n";
                    content += "Titulo: "+posts.getTitle()+" \n";
                    content += "Cuerpo: "+posts.getBody()+" \n\n";

                    mJsonTextView.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Posts>> call, Throwable t) {

            }
        });
    }
}
