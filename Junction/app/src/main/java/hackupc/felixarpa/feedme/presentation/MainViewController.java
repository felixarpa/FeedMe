package hackupc.felixarpa.feedme.presentation;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

import hackupc.felixarpa.feedme.R;
import hackupc.felixarpa.feedme.presentation.fragment.FoodPagerAdapter;

public class MainViewController extends AppCompatActivity {

    int progressChanged = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_view);

        ImageView settings = (ImageView) findViewById(R.id.settings);
        ProgressBar progress = (ProgressBar) findViewById(R.id.progress);
        ViewPager pager = (ViewPager) findViewById(R.id.view_pager);

        progress.setVisibility(View.GONE);
        pager.setVisibility(View.VISIBLE);

        pager.setAdapter(new FoodPagerAdapter(getSupportFragmentManager()));

        settings.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        View v = getLayoutInflater().inflate(R.layout.distance, null);
                        SeekBar seekBar = (SeekBar) v.findViewById(R.id.seekBar);
                        final TextView textView = (TextView) v.findViewById(R.id.distance_text);
                        textView.setText("1 km");
                        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser){
                                progressChanged = progress;
                                textView.setText((progress + 1) + " km");
                            }

                            public void onStartTrackingTouch(SeekBar seekBar) {
                            }

                            public void onStopTrackingTouch(SeekBar seekBar) {
                            }
                        });


                        new AlertDialog.Builder(MainViewController.this)
                                .setTitle("Set search radius in KM")
                                .setView(v)
                                .setPositiveButton(
                                "Ok",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        SharedPreferences.Editor editor = getSharedPreferences(
                                                ViewCtrlUtils.SHARED_PREFERENCES_NAME,
                                                Context.MODE_PRIVATE
                                        ).edit();

                                        editor.putInt(ViewCtrlUtils.RADIUS, progressChanged + 1);
                                        System.out.println("LOL " + progressChanged);

                                        editor.apply();
                                    }
                                })
                                .setNeutralButton(
                                "Cancel",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dialogInterface.dismiss();
                                    }
                                })
                                .show();
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
