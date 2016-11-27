package emerald411.com.monarchmeetup;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

/**
 * Created by alexander.dohrn on 11/16/16.
 */

public class PagerAdapter extends FragmentStatePagerAdapter {
    public PagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        Fragment frag = null;
        switch (position){
            case 0:
                frag = EventPagerFragment.newInstance(new ArrayList<EventModel>());
                break;
            case 1:
                frag = EventPagerFragment.newInstance(new ArrayList<EventModel>());
                break;
            case 2:
                frag = EventPagerFragment.newInstance(new ArrayList<EventModel>());
                break;
            case 3:
                frag = EventPagerFragment.newInstance(new ArrayList<EventModel>());
                break;
            default:
                break;
        }
        return frag;
    }

    @Override
    public int getCount() {
        return 4;
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
        }

        return title;
    }
}