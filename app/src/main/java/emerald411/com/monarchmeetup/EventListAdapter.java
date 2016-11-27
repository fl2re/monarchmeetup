package emerald411.com.monarchmeetup;

/**
 * Created by alexander.dohrn on 11/27/16.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Adapter used on Explore page to display paid products.
 * Created by alexander.dohrn on 7/11/16.
 */

class EventListAdapter extends ArrayAdapter<EventModel> {

    @Bind(R.id.tvEventNameCard)
    TextView tvName;
    @Bind(R.id.tvTimeCard)
    TextView tvTime;
    @Bind(R.id.tvDateCard)
    TextView tvDate;

    private List<EventModel> events;

    public EventListAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    public EventListAdapter(Context context, int resource, List<EventModel> events) {
        super(context, resource, events);

        this.events = new ArrayList<>();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if (v == null) {
            LayoutInflater vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.event_card, null);

//            tvName.setText(events.get(position).getName());
//            tvTime.setText(events.get(position).getTime());
//            tvDate.setText(events.get(position).getDateString());
        }

        return v;
    }

    @Override
    public int getCount() {
        return 10;
    }
}
