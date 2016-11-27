package emerald411.com.monarchmeetup;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.ListViewAutoScrollHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link EventPagerFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link EventPagerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EventPagerFragment extends Fragment {

    @Bind(R.id.lvEvents)
    ListView lvEvents;

    private List<EventModel> events;

    private OnEventClickListener mListener;

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

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_event_pager, container, false);

        lvEvents = (ListView) view.findViewById(R.id.lvEvents);

        lvEvents.setAdapter(new EventListAdapter(getActivity(), R.layout.event_card, events));

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
        void onFragmentInteraction(String eventId);
    }
}
