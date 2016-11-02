package koptevda.meteor_app;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MainPage extends AppItem{
    public MainPage() {
        fragment_name = "Main";
        MainActivity.CURRENT_PAGE = MainActivity.PAGE_TYPE.PAGE_MAIN;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.content_main, container, false);

        return rootView;
    }
}
