package koptevda.meteor_app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;

public class SettingsPage extends AppItem {


    public SettingsPage() {
        fragment_name = "Settings";
        MainActivity.CURRENT_PAGE = MainActivity.PAGE_TYPE.PAGE_SETTINGS;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.content_settings, container,
                false);

        ScrollView settings_list = (ScrollView) rootView.findViewById(R.id.settings_list);

        return rootView;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) { // This will help to reload options menu
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }
}
