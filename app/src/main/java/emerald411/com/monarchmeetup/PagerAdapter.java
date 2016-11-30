package emerald411.com.monarchmeetup;


import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexander.dohrn on 11/16/16.
 */

public class PagerAdapter extends FragmentStatePagerAdapter {

    private List<EventModel> allEvents, sportEvents, entertainmentEvents, greekEvents;

    private SharedPreferences sPref;

    public PagerAdapter(FragmentManager fm, List<EventModel> events, SharedPreferences sPref) {
        super(fm);

        this.sPref = sPref;
        this.allEvents = events;
        categorizeEvents();
    }

    @Override
    public Fragment getItem(int position) {

        Fragment frag = null;
        switch (position){
            case 0:
                frag = EventPagerFragment.newInstance(allEvents);
                break;
            case 1:
                frag = EventPagerFragment.newInstance(sportEvents);
                break;
            case 2:
                frag = EventPagerFragment.newInstance(entertainmentEvents);
                break;
            case 3:
                frag = EventPagerFragment.newInstance(greekEvents);
                break;
            case 4:
                List<EventModel> filteredEvents = filterEvents();
                frag = EventPagerFragment.newInstance(filteredEvents);
                break;
            default:
                break;
        }
        return frag;
    }

    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title=" ";
        switch (position){
            case 0:
                title = "All";
                break;
            case 1:
                title = "Sports";
                break;
            case 2:
                title = "Entertainment";
                break;
            case 3:
                title = "Greek";
                break;
            case 4:
                title = "Your Events";
                break;
        }

        return title;
    }

    private void categorizeEvents() {

        sportEvents = new ArrayList<>();
        entertainmentEvents = new ArrayList<>();
        greekEvents = new ArrayList<>();

        if(allEvents.size() > 0) {
            for(int i = 0; i < allEvents.size(); i++) {

                String type = allEvents.get(i).getType();

                if (type != null) {
                    if (type.toLowerCase().equals("entertainment"))
                        entertainmentEvents.add(allEvents.get(i));
                    else if(type.toLowerCase().equals("greek"))
                        greekEvents.add(allEvents.get(i));
                    else if(type.toLowerCase().equals("sports"))
                        sportEvents.add(allEvents.get(i));
                }

            }
        }
    }

    private List<EventModel> filterEvents() {
        List<EventModel> temp = new ArrayList<EventModel>();

        String savedData = sPref.getString("ids", "");

        for(int i = 0; i < allEvents.size(); i++) {
            if(savedData.contains(allEvents.get(i).getId()))
                temp.add(allEvents.get(i));
        }

        return temp;
    }
}