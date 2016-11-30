package emerald411.com.monarchmeetup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CreateEvent extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);
    }
    private void submitEvent() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://emerald-cs411.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        JsonApiConnections REST_CLIENT = retrofit.create(JsonApiConnections.class);

        Call<EventModel> call;

        EventModel user = new EventModel();


        call = REST_CLIENT.saveEvents();
        call.enqueue(new Callback<EventModel>() {
            @Override
            public void onResponse(Call<EventModel> call, Response<EventModel> response) {
                if(response.code() == 201) {
                    Toast.makeText(getApplicationContext(), "You saved an event!", Toast.LENGTH_LONG).show();
                } else {
                    System.out.println(response.message());
                }
            }

            @Override
            public void onFailure(Call<EventModel> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Error connecting with server.", Toast.LENGTH_LONG).show();
            }

        });
    }
}
