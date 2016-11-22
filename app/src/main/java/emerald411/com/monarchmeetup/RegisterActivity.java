package emerald411.com.monarchmeetup;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends AppCompatActivity {

    @Bind(R.id.etPasswordRegister)
    EditText etPassword;
    @Bind(R.id.etPassword2Register)
    EditText etPassword2;
    @Bind(R.id.etEmailRegister)
    EditText etEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnRegister)
    private void registerUser() {
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

    }
}
