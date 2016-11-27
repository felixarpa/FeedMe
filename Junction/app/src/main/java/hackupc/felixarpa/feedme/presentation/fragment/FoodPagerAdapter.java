package hackupc.felixarpa.feedme.presentation.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import hackupc.felixarpa.feedme.R;

public class FoodPagerAdapter extends FragmentPagerAdapter {

    public FoodPagerAdapter(FragmentManager supportFragmentManager) {
        super(supportFragmentManager);
    }

    @Override
    public int getCount() {
        return 10;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;

        switch (position) {
            case 0:
                fragment = new FoodFragment(
                        "coffee",
                        R.layout.coffee_fragment,
                        "There are no cafeterias nearby"
                );
                break;

            case 1:
                fragment = new FoodFragment(
                        "pizza",
                        R.layout.pizza_fragment,
                        "There are no pizza restaurants nearby"
                );
                break;

            case 2:
                fragment = new FoodFragment(
                        "veggie",
                        R.layout.veggie_fragment,
                        "There are no veggie restaurants nearby"
                );
                break;

            case 3:
                fragment = new FoodFragment(
                        "hamburger_fragment",
                        R.layout.hamburger_fragment,
                        "There are no hamburger_fragment restaurants nearby"
                );
                break;

            case 4:
                fragment = new FoodFragment(
                        "meat",
                        R.layout.meat_fragment,
                        "There are no meat restaurant nearby"
                );
                break;

            case 5:
                fragment = new FoodFragment(
                        "sushi",
                        R.layout.sushi_fragment,
                        "There are no japanese restaurant nearby"
                );
                break;

            case 6:
                fragment = new FoodFragment(
                        "pasta",
                        R.layout.pasta_fragment,
                        "There are no pasta restaurant nearby"
                );
                break;

            case 7:
                fragment = new FoodFragment(
                        "mexican",
                        R.layout.mexican_fragment,
                        "There are no mexican restaurant nearby"
                );
                break;

            case 8:
                fragment = new FoodFragment(
                        "cocktail",
                        R.layout.cocktail_fragment,
                        "There are no cocktail bars nearby"
                );
                break;

            case 9:
                fragment = new FoodFragment(
                        "fish",
                        R.layout.fish_fragment,
                        "There are no fish restaurants nearby"
                );
                break;
        }

        return fragment;
    }
}
