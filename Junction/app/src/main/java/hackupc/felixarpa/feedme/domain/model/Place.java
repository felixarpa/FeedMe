package hackupc.felixarpa.feedme.domain.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Place {

    private Double latitude;
    private Double longitude;
    private String icon;
    private String id;
    private String name;
    private Boolean openNow;
    private Double rating;

    public Place() {
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getOpenNow() {
        return openNow;
    }

    public void setOpenNow(Boolean openNow) {
        this.openNow = openNow;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public static Place fromJSONObject(JSONObject object) throws JSONException {
        Place result = new Place();

        result.setIcon(object.getString("icon"));
        result.setId(object.getString("id"));
        result.setLatitude(object.getJSONObject("geometry").getJSONObject("location").getDouble("lat"));
        result.setLongitude(object.getJSONObject("geometry").getJSONObject("location").getDouble("lng"));
        result.setName(object.getString("name"));
        result.setOpenNow(object.getJSONObject("opening_hours").getBoolean("open_now"));
        result.setRating(object.getDouble("rating"));

        return result;
    }

    public static ArrayList<Place> fromJSONArray(JSONArray array) {
        ArrayList<Place> result = new ArrayList<>();
        int length = array.length();
        for (int i = 0; i < length; i++) {
            try {
                result.add(Place.fromJSONObject(array.getJSONObject(i)));
            } catch (JSONException ignored) {
            }
        }
        return result;
    }
}
