package emerald411.com.monarchmeetup;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    @Bind(R.id.tvLoginBtn)
    TextView tvLoginBtn;

    @Bind(R.id.etUsername)
    EditText etUsername;

    @Bind(R.id.etPassword)
    EditText etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        tvLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://emerald-cs411.herokuapp.com/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                JsonApiConnections REST_CLIENT = retrofit.create(JsonApiConnections.class);

                Call<UserModel> call;

                call = REST_CLIENT.checkUser(etUsername.getText().toString().toLowerCase());
                call.enqueue(new Callback<UserModel>() {
                    @Override
                    public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                        if(response.body().getUsername().toLowerCase().equals(etUsername.getText().toString().toLowerCase()) &&
                                response.body().getPassword().toLowerCase().equals(etPassword.getText().toString().toLowerCase())) {
                            SharedPreferences sPref = getSharedPreferences("secrets", Context.MODE_PRIVATE);
                            Gson gson = new Gson();
                            UserModel user = gson.fromJson(sPref.getString("userInfo", ""), UserModel.class);
                            if(user == null)
                                user = new UserModel();
                            user.setUsername(etUsername.getText().toString());
                            sPref.edit().putString("userInfo", gson.toJson(user)).apply();

                            Intent mainActivityIntent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(mainActivityIntent);
                        } else {
                            Toast.makeText(getApplicationContext(), "Invalid username or password.", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<UserModel> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Invalid username or password.", Toast.LENGTH_LONG).show();
                    }

                });

            }
        });
    }

    @OnClick(R.id.tvRegister)
    public void registerUser() {
        Intent registerActivityIntent = new Intent(getApplicationContext(), RegisterActivity.class);
        startActivity(registerActivityIntent);
    }

}
