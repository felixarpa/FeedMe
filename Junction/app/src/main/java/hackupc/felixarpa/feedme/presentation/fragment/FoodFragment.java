package hackupc.felixarpa.feedme.presentation.fragment;

import android.content.Intent;
import android.nakima.requestslibrary.OnFailure;
import android.nakima.requestslibrary.OnSuccess;
import android.nakima.requestslibrary.Response;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import hackupc.felixarpa.feedme.R;
import hackupc.felixarpa.feedme.domain.ApiService;
import hackupc.felixarpa.feedme.presentation.PlacesViewController;
import hackupc.felixarpa.feedme.presentation.ViewCtrlUtils;

public class FoodFragment extends Fragment implements View.OnClickListener {

    private String keyWord;
    private int layoutResource;
    private String errorMessage;

    private ImageView action;
    private ProgressBar progressBar;
    private TextView text;

    public FoodFragment(String keyWord, int layoutResource, String errorMessage) {
        this.keyWord = keyWord;
        this.layoutResource = layoutResource;
        this.errorMessage = errorMessage;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(layoutResource, container, false);
        action = (ImageView) rootView.findViewById(R.id.action_image);
        progressBar = (ProgressBar) rootView.findViewById(R.id.progress);
        text = (TextView) rootView.findViewById(R.id.text);
        action.setOnClickListener(this);
        text.setOnClickListener(this);
//        rootView.setOnClickListener(this);
        return rootView;
    }


    @Override
    public void onClick(View view) {
        action.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
        text.setVisibility(View.GONE);
        ApiService.getInstance(getActivity()).findPlaces(
                keyWord,
                new OnSuccess() {
                    @Override
                    public void onSuccess(Response response) {
                        action.setVisibility(View.VISIBLE);
                        progressBar.setVisibility(View.GONE);
                        text.setVisibility(View.VISIBLE);
                        Intent intent = new Intent(getContext(), PlacesViewController.class);
                        intent.putExtra(ViewCtrlUtils.JSON, response.getMessage());
                        intent.putExtra(ViewCtrlUtils.FOOD_KIND, keyWord);
                        getActivity().startActivity(intent);
                    }
                },
                new OnFailure() {
                    @Override
                    public void onFailure(Response response) {
                        action.setVisibility(View.VISIBLE);
                        progressBar.setVisibility(View.GONE);
                        text.setVisibility(View.VISIBLE);
                        Toast.makeText(getActivity(), errorMessage, Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }
}
