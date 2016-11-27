package hackupc.felixarpa.feedme.presentation;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import hackupc.felixarpa.feedme.R;
import hackupc.felixarpa.feedme.presentation.fragment.FoodPagerAdapter;

public class MainViewController extends AppCompatActivity {

    private ViewPager pager;
    private ProgressBar progress;
    private ImageView settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_view);

        settings = (ImageView) findViewById(R.id.settings);
        progress = (ProgressBar) findViewById(R.id.progress);
        pager = (ViewPager) findViewById(R.id.view_pager);

        progress.setVisibility(View.GONE);
        pager.setVisibility(View.VISIBLE);

        pager.setAdapter(new FoodPagerAdapter(getSupportFragmentManager()));

        settings.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(getApplicationContext(), SettingsViewController.class));
                    }
                }
        );

        SharedPreferences.Editor editor = getSharedPreferences(
                ViewCtrlUtils.SHARED_PREFERENCES_NAME,
                Context.MODE_PRIVATE
        ).edit();

        editor.putFloat(ViewCtrlUtils.LATITUDE, (float) 60.165452);
        editor.putFloat(ViewCtrlUtils.LONGITUDE, (float) 24.967455);

        editor.apply();
    }
}
