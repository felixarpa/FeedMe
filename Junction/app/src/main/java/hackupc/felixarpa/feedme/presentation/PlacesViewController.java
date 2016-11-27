package hackupc.felixarpa.feedme.presentation;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import hackupc.felixarpa.feedme.R;
import hackupc.felixarpa.feedme.domain.ApiService;
import hackupc.felixarpa.feedme.domain.model.Place;

public class PlacesViewController extends AppCompatActivity implements View.OnClickListener {

    private ArrayList<Place> places;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.places_view);

        LinearLayout list = (LinearLayout) findViewById(R.id.places_list);

        try {
            String jsonStr = getIntent().getExtras().getString(ViewCtrlUtils.JSON, "asdf");
            JSONObject jsonObject = new JSONObject(jsonStr);
            JSONArray results = jsonObject.getJSONArray("results");
            places = Place.fromJSONArray(results);
        } catch (JSONException e) {
            System.out.println("JSONException");
            finish();
        }

        ImageLoader.getInstance().init(ImageLoaderConfiguration.createDefault(this));
        int n = places.size();
        LayoutInflater inflater = getLayoutInflater();
        for (int i = 0; i < n; i++) {
            View veiw = inflater.inflate(R.layout.place_view, null);
            TextView index = (TextView) veiw.findViewById(R.id.index);
            TextView name = (TextView) veiw.findViewById(R.id.place_name);
            TextView rating = (TextView) veiw.findViewById(R.id.place_rating);
            ImageView imageView = (ImageView) veiw.findViewById(R.id.logo);

            index.setText(String.valueOf(i));
            name.setText(places.get(i).getName());
            rating.setText(String.valueOf(places.get(i).getRating()));
            ImageLoader.getInstance().displayImage(places.get(i).getIcon(), imageView);

            veiw.setOnClickListener(this);

            list.addView(veiw);

        }


    }

    @Override
    public void onClick(View view) {
        TextView indexText = (TextView) view.findViewById(R.id.index);
        final int index = Integer.parseInt(indexText.getText().toString());
        new AlertDialog.Builder(this)
                .setTitle(places.get(index).getName())
                .setMessage("Get indications to " + places.get(index).getName())
                .setPositiveButton(
                "Go",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        Pair<Float, Float> pair = ApiService.getInstance(getApplicationContext()).getCurrentLocation();

                        String url = "https://www.google.es/maps/dir/"
                                + pair.first + "," + pair.second + "/"
                                + places.get(index).getLatitude() + "," + places.get(index).getLongitude();

                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse(url));
                        startActivity(intent);
                    }
                })
                .setNeutralButton(
                    "Cancel",
                    new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                })
                .show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        ImageLoader.getInstance().init(ImageLoaderConfiguration.createDefault(this));
    }

    @Override
    protected void onPause() {
        ImageLoader.getInstance().destroy();
        super.onPause();
    }
}
