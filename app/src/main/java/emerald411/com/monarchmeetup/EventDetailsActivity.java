package emerald411.com.monarchmeetup;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
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

public class EventDetailsActivity extends AppCompatActivity {

    @Bind(R.id.TVeventAttendees)
    TextView tvAttendees;
    @Bind(R.id.TVeventDate)
    TextView tvEventDate;
    @Bind(R.id.TVeventDetails)
    TextView tvDetails;
    @Bind(R.id.TVeventLocation)
    TextView tvLocation;
    @Bind(R.id.TVeventTime)
    TextView tvTime;
    @Bind(R.id.btnSignUp)
    Button btnSignup;
    @Bind(R.id.btnPassword)
    Button btnPassword;
    @Bind(R.id.eventName)
    TextView tvEventName;

    private EventModel event;
    private boolean isSignedUp;
    private SharedPreferences sPref;
    private String eventID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);
        ButterKnife.bind(this);

        Bundle bundle = getIntent().getExtras();
        eventID = bundle.getString("id");

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://emerald-cs411.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        JsonApiConnections REST_CLIENT = retrofit.create(JsonApiConnections.class);

        Call<EventModel> call;

        call = REST_CLIENT.getEvent(eventID);
        call.enqueue(new Callback<EventModel>() {
            @Override
            public void onResponse(Call<EventModel> call, Response<EventModel> response) {
                event = response.body();

                displayData();
            }

            @Override
            public void onFailure(Call<EventModel> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Could not retrieve event data.", Toast.LENGTH_LONG).show();
            }

        });

        sPref = getSharedPreferences("secrets", Context.MODE_PRIVATE);

        if(sPref.getString("ids", "").contains(eventID)) {
            btnSignup.setText("Cancel Attendence");
            btnPassword.setVisibility(View.VISIBLE);
            isSignedUp = true;
        } else {
            btnPassword.setVisibility(View.GONE);
            isSignedUp = false;
        }

    }

    private void displayData() {
        tvAttendees.setText("0");
        tvDetails.setText(event.getDescription());
        tvEventDate.setText(event.getDateString());
        tvLocation.setText("Quad in front of Strome Center");
        tvTime.setText(event.getTime());
        tvEventName.setText(event.getName());
    }

    @OnClick(R.id.btnSignUp)
    public void buttonClick() {
        if(isSignedUp) {
            btnPassword.setVisibility(View.GONE);
            btnSignup.setText("Sign me up!");
            isSignedUp = false;
            unregisterUser();
        } else {
            btnPassword.setVisibility(View.VISIBLE);
            btnSignup.setText("Cancel Attendence");
            isSignedUp = true;
            registerUser();
        }
    }

    private void registerUser() {
        String savedData = sPref.getString("ids", "") + "-" + eventID;
        sPref.edit().putString("ids", savedData).commit();

        //PUT request to edit attendence value
    }

    private void unregisterUser() {
        String savedData = sPref.getString("ids", "");
        System.out.println("DATA: " + savedData);
        savedData = savedData.replace("-" + eventID, "");
        sPref.edit().putString("ids", savedData).commit();
        System.out.println("DATA: " + savedData);

        //PUT request to remove attendence value
    }

    @OnClick(R.id.btnPassword)
    public void checkPassword() {
// get prompts.xml view
        LayoutInflater li = LayoutInflater.from(this);
        View promptsView = li.inflate(R.layout.password_prompt, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);

        // set prompts.xml to alertdialog builder
        alertDialogBuilder.setView(promptsView);

        final EditText userInput = (EditText) promptsView
                .findViewById(R.id.editTextDialogUserInput);

        // set dialog message
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                if(userInput.getText().toString().equals(event.getPassword())) {
                                    Toast.makeText(getApplicationContext(), "Successful, points have been added.", Toast.LENGTH_LONG).show();

                                    Gson gson = new Gson();
                                    UserModel user = gson.fromJson(sPref.getString("userInfo", ""), UserModel.class);
                                    user.setAttended(user.getAttended() + 1);
                                    user.setPoints(user.getPoints() + 3);
                                    sPref.edit().putString("userInfo", gson.toJson(user)).apply();
                                } else {
                                    Toast.makeText(getApplicationContext(), "Incorrect password.", Toast.LENGTH_LONG).show();
                                }

                            }
                        })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                dialog.cancel();
                            }
                        });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();
    }
}
