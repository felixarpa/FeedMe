package hackupc.felixarpa.feedme;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

public class MainViewController extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_view);

        ImageView settings = (ImageView) findViewById(R.id.settings);
        ViewPager pager = (ViewPager) findViewById(R.id.view_pager);

        pager.setAdapter(new FoodPagerAdapter(getSupportFragmentManager(), this));

        settings.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
//        startActivity(new Intent(this, SettingsViewController.class));
    }
}
