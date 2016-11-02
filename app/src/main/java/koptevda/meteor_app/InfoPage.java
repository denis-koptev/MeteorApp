package koptevda.meteor_app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class InfoPage extends AppItem {

    public InfoPage() {
        fragment_name = "Info";
        MainActivity.CURRENT_PAGE = MainActivity.PAGE_TYPE.PAGE_INFO;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.content_info, container,
                false);

        return rootView;
    }
}
