package emerald411.com.monarchmeetup;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.Random;

import butterknife.Bind;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CreateEventActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    @Bind(R.id.eventName)
    EditText etName;
    @Bind(R.id.eventDescription)
    EditText etDescription;
    @Bind(R.id.eventTime)
    EditText etTime;
    @Bind(R.id.eventCategories)
    Spinner spinner;
    @Bind(R.id.eventDate)
    EditText etDate;
    @Bind(R.id.location)
    EditText etLocation;
    @Bind(R.id.eventPassword)
    EditText etPassword;

    private String type = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.Categories, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
    }

    @OnClick(R.id.Create)
    public void submitEvent() {

        if(!isValid()) {
            Toast.makeText(this, "Please fill out all forms correctly.", Toast.LENGTH_LONG).show();
            return;
        }

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://emerald-cs411.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        JsonApiConnections REST_CLIENT = retrofit.create(JsonApiConnections.class);

        Call<EventModel> call;

        final EventModel event = new EventModel();
        event.setAttendence(1);
        event.setType(type);
        event.setPassword(etPassword.getText().toString());
        event.setDateString(etDate.getText().toString());
        event.setDescription(etDescription.getText().toString());
        event.setName(etName.getText().toString());
        event.setId(String.valueOf(generateId()));
        event.setTime(etTime.getText().toString());

        call = REST_CLIENT.saveEvents();
        call.enqueue(new Callback<EventModel>() {
            @Override
            public void onResponse(Call<EventModel> call, Response<EventModel> response) {
                if(response.code() == 201) {
                    Toast.makeText(getApplicationContext(), "You saved an event!", Toast.LENGTH_LONG).show();
                    registerUser(event.getId());
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

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        type = (String) adapterView.getItemAtPosition(i);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        type = "";
    }

    private int generateId() {
        int min = 1;
        int max = 1000000;

        Random r = new Random();
        return r.nextInt(max - min + 1) + min;
    }

    private boolean isValid() {

        if(etName.getText().toString().isEmpty() || etDate.getText().toString().isEmpty() || etDescription.getText().toString().isEmpty()
                || etLocation.getText().toString().isEmpty() || etTime.getText().toString().isEmpty() || etPassword.getText().toString().isEmpty()
                || type.equals(""))
            return false;

        return true;
    }

    private void registerUser(String id) {
        SharedPreferences sPref = getSharedPreferences("secrets", Context.MODE_PRIVATE);
        String savedData = sPref.getString("ids", "") + "-" + id;
        sPref.edit().putString("ids", savedData).commit();

        Gson gson = new Gson();
        UserModel user = gson.fromJson(sPref.getString("userInfo", ""), UserModel.class);
        user.setCreated(user.getCreated() + 1);
        user.setPoints(user.getPoints() + 1);
        sPref.edit().putString("userInfo", gson.toJson(user)).apply();
    }
}
