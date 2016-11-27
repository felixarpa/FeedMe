package hackupc.felixarpa.feedme.domain;

import android.content.Context;
import android.content.SharedPreferences;
import android.nakima.requestslibrary.OnFailure;
import android.nakima.requestslibrary.OnSuccess;
import android.nakima.requestslibrary.Request;
import android.util.Pair;

import hackupc.felixarpa.feedme.presentation.ViewCtrlUtils;

public class ApiService {

    private static Context serviceContext;

    private final static String BASE_URL = "https://maps.googleapis.com/maps/api/place/nearbysearch/json";
    private final static String API_KEY = "AIzaSyBPJnmI875t0xrKThJuLzIIZ8NckFHHqI0";

    private static ApiService ourInstance = new ApiService();

    public static ApiService getInstance(Context context) {
        serviceContext = context;
        Request.initialize(context);
        return ourInstance;
    }

    private ApiService() {

    }

    // 60.165805, 24.966704

    public void findPlaces(String keyword, OnSuccess onSuccess, OnFailure onFailure) {

        Request request = new Request(Request.GET, BASE_URL);

        Pair<Float, Float> pair = getCurrentLocation();

        Float latitude = pair.first;
        Float longitude = pair.second;

        request.addParam("key", API_KEY);
        request.addParam("location", String.valueOf(latitude) + "," + String.valueOf(longitude));
        request.addParam("radius", 2000);
        request.addParam("type", "restaurant");
        request.addParam("keyword", keyword);

        request.setOnSuccess(onSuccess);
        request.setOnFailure(onFailure);

        request.send();

    }

    public Pair<Float, Float> getCurrentLocation() {

        SharedPreferences sp = serviceContext.getSharedPreferences(
                ViewCtrlUtils.SHARED_PREFERENCES_NAME,
                Context.MODE_PRIVATE
        );

        Float latitude = sp.getFloat(ViewCtrlUtils.LATITUDE, 0);
        Float longitude = sp.getFloat(ViewCtrlUtils.LONGITUDE, 0);

        return new Pair<>(latitude, longitude);
    }
}
