package emerald411.com.monarchmeetup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
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

public class RegisterActivity extends AppCompatActivity {

    @Bind(R.id.etPasswordRegister)
    EditText etPassword;
    @Bind(R.id.etPassword2Register)
    EditText etPassword2;
    @Bind(R.id.etEmailRegister)
    EditText etEmail;
    @Bind(R.id.etUsernameRegister)
    EditText etUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnRegister)
    public void registerUser() {
        if(etPassword.getText().toString().length() < 6) {
            Toast.makeText(this, "Password must be longer than 5 characters.", Toast.LENGTH_LONG).show();
            return;
        }
        if(!etPassword.getText().toString().equals(etPassword2.getText().toString())) {
            Toast.makeText(this, "Passwords do not match. Please correct.", Toast.LENGTH_LONG).show();
            return;
        }
        if(!isValidEmail(etEmail.getText().toString())) {
            Toast.makeText(this, "Invalid email. Please correct.", Toast.LENGTH_LONG).show();
            return;
        }

        submitUser();
    }

    private final static boolean isValidEmail(CharSequence target) {
        if (TextUtils.isEmpty(target)) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }

    private void submitUser() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://emerald-cs411.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        JsonApiConnections REST_CLIENT = retrofit.create(JsonApiConnections.class);

        Call<UserModel> call;

        UserModel user = new UserModel();
        user.setEmail(etEmail.getText().toString());
        user.setPassword(etPassword.getText().toString());
        user.setUsername(etUsername.getText().toString());

        call = REST_CLIENT.submitNewUser(user);
        call.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                if(response.code() == 201) {
                    Intent mainActivityIntent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(mainActivityIntent);
                } else {
                    System.out.println(response.message());
                }
            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Error connecting with server.", Toast.LENGTH_LONG).show();
            }

        });
    }
}
