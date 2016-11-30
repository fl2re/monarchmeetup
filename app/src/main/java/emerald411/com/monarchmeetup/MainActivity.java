package emerald411.com.monarchmeetup;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements EventPagerFragment.OnEventClickListener {

    @Bind(R.id.tabLayout)
    TabLayout tabLayout;

    @Bind(R.id.viewPager)
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        tabLayout.addTab(tabLayout.newTab().setText("All"));
        tabLayout.addTab(tabLayout.newTab().setText("Sports"));
        tabLayout.addTab(tabLayout.newTab().setText("Entertainment"));
        tabLayout.addTab(tabLayout.newTab().setText("Greek"));

        getEvents();

    }

    private void getEvents() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://emerald-cs411.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        JsonApiConnections REST_CLIENT = retrofit.create(JsonApiConnections.class);

        Call<List<EventModel>> call;

        call = REST_CLIENT.getEvents();
        call.enqueue(new Callback<List<EventModel>>() {
            @Override
            public void onResponse(Call<List<EventModel>> call, Response<List<EventModel>> response) {

                // Fragment manager to add fragment in viewpager we will pass object of Fragment manager to adpater class.
                FragmentManager manager = getSupportFragmentManager();

                //object of PagerAdapter passing fragment manager object as a parameter of constructor of PagerAdapter class.
                PagerAdapter adapter = new PagerAdapter(manager, response.body());

                //set Adapter to view pager
                viewPager.setAdapter(adapter);

                //set tablayout with viewpager
                tabLayout.setupWithViewPager(viewPager);

                // adding functionality to tab and viewpager to manage each other when a page is changed or when a tab is selected
                viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
            }

            @Override
            public void onFailure(Call<List<EventModel>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Failed to retrieve event data.", Toast.LENGTH_LONG).show();
            }

        });
    }

    @Override
    public void onFragmentInteraction(String eventId) {
        Intent detailsIntent = new Intent(this, EventDetailsActivity.class);
        detailsIntent.putExtra("id", eventId);
        startActivity(detailsIntent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mainmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.profile_menu:
                showProfile();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void showProfile() {

    }

}
