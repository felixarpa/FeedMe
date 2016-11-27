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

        String type = getIntent().getExtras().getString(ViewCtrlUtils.FOOD_KIND, "blank");

        System.out.println("LOL " + type);
        int color = -1;
        if (type.equals("coffee")) {
            color = ViewCtrlUtils.getColor(this, R.color.md_brown_100);
        } else if (type.equals("pizza")) {
            color = ViewCtrlUtils.getColor(this, R.color.md_orange_100);
        } else if (type.equals("veggie")) {
            color = ViewCtrlUtils.getColor(this, R.color.md_green_100);
        } else if (type.equals("hambuger")) {
            color = ViewCtrlUtils.getColor(this, R.color.md_yellow_100);
        } else if (type.equals("meat")) {
            color = ViewCtrlUtils.getColor(this, R.color.md_red_100);
        } else if (type.equals("sushi")) {
            color = ViewCtrlUtils.getColor(this, R.color.md_green_100);
        } else if (type.equals("pasta")) {
            color = ViewCtrlUtils.getColor(this, R.color.md_brown_100);
        } else if (type.equals("mexican")) {
            color = ViewCtrlUtils.getColor(this, R.color.md_red_100);
        } else if (type.equals("cocktail")) {
            color = ViewCtrlUtils.getColor(this, R.color.md_blue_100);
        } else if (type.equals("fish")) {
            color = ViewCtrlUtils.getColor(this, R.color.md_blue_100);
        }

        if (color != -1) {
            list.setBackgroundColor(color);
        }

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
