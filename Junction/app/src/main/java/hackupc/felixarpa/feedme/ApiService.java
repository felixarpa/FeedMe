package hackupc.felixarpa.feedme;

import android.content.Context;
import android.nakima.requestslibrary.OnFailure;
import android.nakima.requestslibrary.OnSuccess;
import android.nakima.requestslibrary.Request;

public class ApiService {

    private static ApiService ourInstance = new ApiService();

    public static ApiService getInstance(Context context) {
        Request.initialize(context);
        return ourInstance;
    }

    private ApiService() {

    }


    public void findPlaces(Double latitude, Double longitude, OnSuccess onSuccess, OnFailure onFailure) {
        Request request = new Request(Request.GET, Config.BASE_URL);

        request.addParam("key", Config.API_KEY);
        request.addParam("location", String.valueOf(latitude) + "," + String.valueOf(longitude));
        request.addParam("radius", 2000);
        request.addParam("type", "restaurant");

        request.setOnSuccess(onSuccess);
        request.setOnFailure(onFailure);

        request.send();

    }

}
