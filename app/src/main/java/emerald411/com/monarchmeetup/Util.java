package emerald411.com.monarchmeetup;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by alexander.dohrn on 11/30/16.
 */

public class Util {

    public static String timeStampToDate(String stamp) {
        String formatString = "";

        if (stamp != null && !stamp.isEmpty()) {

            String s = stamp.replace("Z", "+00:00");
            try {
                s = s.substring(0, 22) + s.substring(23);  // to get rid of the ":"
            } catch (IndexOutOfBoundsException e) {

            }

            Date date = new Date();

            try {
                date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.sssZ").parse(s);
                String dateString = new SimpleDateFormat("MM/dd/yy").format(date);
                return dateString;
            } catch (ParseException e) {
                // Error parsing date. Return Timestamp
                return stamp;
            }
        }

        return formatString;
    }

    public static String timeStampToTimeString(String stamp) {
        String formatString = "";

        if (stamp != null && !stamp.isEmpty()) {

            String s = stamp.replace("Z", "+00:00");
            try {
                s = s.substring(0, 22) + s.substring(23);  // to get rid of the ":"
            } catch (IndexOutOfBoundsException e) {

            }

            Date date = new Date();

            try {
                date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.sssZ").parse(s);
                String timeString = new SimpleDateFormat("h:mm a").format(date);
                return timeString;
            } catch (ParseException e) {
                // Error parsing date. Return Timestamp
                return stamp;
            }
        }

        return formatString;
    }
}
