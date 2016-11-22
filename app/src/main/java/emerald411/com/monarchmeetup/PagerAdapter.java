package emerald411.com.monarchmeetup;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

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
                frag = new EventPagerFragment();
                break;
            case 1:
                frag = new EventPagerFragment();
                break;
            case 2:
                frag = new EventPagerFragment();
                break;
            case 3:
                frag = new EventPagerFragment();
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