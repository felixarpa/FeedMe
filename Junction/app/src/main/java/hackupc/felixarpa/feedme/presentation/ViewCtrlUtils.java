package hackupc.felixarpa.feedme.presentation;

import android.content.Context;
import android.os.Build;

public class ViewCtrlUtils {

    public static final String SHARED_PREFERENCES_NAME = "FEED-ME-SP";
    public static final String LATITUDE = "lat";
    public static final String LONGITUDE = "lng";
    public static final String JSON = "json";
    public static final String FOOD_KIND = "fk";

    public static int getColor(Context context, int resourceId) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return context.getColor(resourceId);
        } else {
            return context.getResources().getColor(resourceId, null);
        }
    }



}
