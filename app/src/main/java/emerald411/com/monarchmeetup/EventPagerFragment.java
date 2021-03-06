package emerald411.com.monarchmeetup;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.ListViewAutoScrollHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class EventPagerFragment extends Fragment {

    @Bind(R.id.lvEvents)
    ListView lvEvents;

    private List<EventModel> events;

    private OnEventClickListener mListener;

    private EventListAdapter adapter;

    private SharedPreferences sPref;

    public EventPagerFragment() {
        // Required empty public constructor
    }

    public static EventPagerFragment newInstance(List<EventModel> events) {
        EventPagerFragment fragment = new EventPagerFragment();

        fragment.setEvents(events);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        adapter = new EventListAdapter(getActivity(), R.layout.event_card, events);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_event_pager, container, false);

        lvEvents = (ListView) view.findViewById(R.id.lvEvents);

        lvEvents.setAdapter(adapter);

        lvEvents.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                mListener.onFragmentInteraction(events.get(position));
            }
        });

        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnEventClickListener) {
            mListener = (OnEventClickListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public void setEvents(List<EventModel> events) {
        this.events = events;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnEventClickListener {
        void onFragmentInteraction(EventModel event);
    }

}
