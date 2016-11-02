package koptevda.meteor_app;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

/**
 * Abstract class for app pages;
 * MainActivity changes this fragments when user click menu items in Navigation View;
 * This separate class lets one fragment jump to another fragment without users actions
 * in Navigation View menu;
 */
public class AppItem extends Fragment {

    protected String fragment_name;

    public AppItem(){
        Log.d("MY_APP", "APP PAGE CREATED");
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((Toolbar)getActivity().findViewById(R.id.toolbar)).setTitle(fragment_name);
    }

    public void jump_to(int item_id, Fragment to_what) {
        NavigationView navigationView = (NavigationView)getActivity().findViewById(R.id.nav_view);
        navigationView.setCheckedItem(item_id);
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame, to_what).commit();
    }
}
