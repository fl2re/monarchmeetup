package emerald411.com.monarchmeetup;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UserActivity extends AppCompatActivity {

    @Bind(R.id.tvUsername)
    TextView tvUsername;
    @Bind(R.id.tvPoints)
    TextView tvPoints;
    @Bind(R.id.tvAttendedCount)
    TextView tvAttended;
    @Bind(R.id.tvCreatedCount)
    TextView tvCreated;
    @Bind(R.id.tvUserDescription)
    TextView tvUserDescription;

    private UserModel user;
    private SharedPreferences sPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        ButterKnife.bind(this);

        Gson gson = new Gson();
        sPref = getSharedPreferences("secrets", Context.MODE_PRIVATE);
        user = gson.fromJson(sPref.getString("userInfo", ""), UserModel.class);

        tvUsername.setText(user.getUsername());
        tvAttended.setText(String.valueOf(user.getAttended()));
        tvCreated.setText(String.valueOf(user.getCreated()));
        tvPoints.setText(String.valueOf(user.getPoints()));

        if(!user.getDescription().isEmpty())
            tvUserDescription.setText(user.getDescription());
    }

    @OnClick(R.id.tvUserDescription)
    public void editDescription() {
        LayoutInflater li = LayoutInflater.from(this);
        View promptsView = li.inflate(R.layout.password_prompt, null);

        TextView textView = (TextView) promptsView.findViewById(R.id.tvInstr);
        textView.setText("Enter new description.");

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

                                updateUserDescription(userInput.getText().toString());
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

    private void updateUserDescription(String desc) {
        user.setDescription(desc);
        tvUserDescription.setText(desc);

        Gson gson = new Gson();
        UserModel user = gson.fromJson(sPref.getString("userInfo", ""), UserModel.class);
        user.setDescription(desc);
        sPref.edit().putString("userInfo", gson.toJson(user)).apply();
    }
}
