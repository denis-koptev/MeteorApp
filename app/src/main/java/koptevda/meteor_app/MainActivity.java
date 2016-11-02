package koptevda.meteor_app;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.ContextMenu;
import android.view.DragEvent;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    protected DrawerLayout drawer;
    protected NavigationView navigationView;

    public enum PAGE_TYPE {
        PAGE_MAIN, PAGE_SETTINGS, PAGE_INFO, PAGE_SIGN_IN
    }
    public static PAGE_TYPE CURRENT_PAGE = PAGE_TYPE.PAGE_MAIN;

    // Ids from xml, to make possible both creation ways (with code / with xml)
    private final int MENU_ITEM_1 = R.id.item1, MENU_ITEM_2 = R.id.item2, MENU_ITEM_3 = R.id.item3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Main");
       // toolbar.setLogo(R.drawable.icon);
        setSupportActionBar(toolbar);

       /* FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        // Registering items for context menu by long clicking
        registerForContextMenu(findViewById(R.id.context_menu_item));

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        //drawer.setDrawerListener(toggle);
        toggle.syncState();
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Log.v("MY_APP", "OnCreate() finished");
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        //super.onCreateContextMenu(menu, v, menuInfo);
        Log.v("MY_APP", "Context menu created");
        /*switch (v.getId()) {                                     // Creating menu programmatically
            case R.id.context_menu_item: {
                menu.add(0, MENU_ITEM_1, 0, "Item 1");
                menu.add(0, MENU_ITEM_2, 0, "Item 2");
                menu.add(0, MENU_ITEM_3, 0, "Item 3");
            }
        }*/
        getMenuInflater().inflate(R.menu.context_menu, menu);              // Creating menu from xml
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        Log.v("MY_APP", "Context item selected");
        switch (item.getItemId()) {
            case MENU_ITEM_1: {
                Toast.makeText(this, "Item 1 selected", Toast.LENGTH_SHORT).show();
                break;
            }
            case MENU_ITEM_2: {
                Toast.makeText(this, "Item 2 selected", Toast.LENGTH_SHORT).show();
                break;
            }
            case MENU_ITEM_3: {
                Toast.makeText(this, "Item 3 selected", Toast.LENGTH_SHORT).show();
                break;
            }
            default: {
                Toast.makeText(this, "NOTE: Set listener for this item!", Toast.LENGTH_SHORT).show();
                break;
            }
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) { // will open navigation view when menu key pressed
        if (keyCode == KeyEvent.KEYCODE_MENU) {
            event.startTracking();
            drawer.openDrawer(GravityCompat.START);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        Log.d("MY_APP", "PREPARE_OPTIONS_MENU, PAGE: " + CURRENT_PAGE);
        if (CURRENT_PAGE == PAGE_TYPE.PAGE_SETTINGS) {
            Log.d("MY_APP", "PREPARE_OPTIONS_MENU FROM SETTINGS");
            menu.findItem(R.id.action_search).setVisible(false);
            menu.findItem(R.id.action_apply).setVisible(true);

        } else {
            menu.findItem(R.id.action_search).setVisible(true);
            menu.findItem(R.id.action_apply).setVisible(false);
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.d("MY_APP", "ON_CREATE_OPTIONS_MENU");

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        // Associate searchable configuration with the SearchView
        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                System.err.println("DATA SEARCHED: " + query);
                Toast.makeText(getApplicationContext(),
                        "Sorry, but now we can't search '" + query + "'", Toast.LENGTH_LONG).show();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case R.id.action_search: {
                return true;
            }
            case R.id.action_apply: {
                // Write some code here to apply settings...
                Toast.makeText(this, "Applied", Toast.LENGTH_SHORT).show();
                // Global variable isn't a good choice...
                navigationView.setCheckedItem(R.id.main_page);
                onNavigationItemSelected(navigationView.getMenu().getItem(0));
            }
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        // Handle navigation view item clicks here.
        int id = item.getItemId();

        Fragment fragment = null;

        if (id == R.id.main_page) {
            // Handle the camera action
            fragment = new MainPage();
        } else if (id == R.id.settings_page) {
            fragment = new SettingsPage();
        } else if (id == R.id.info_page) {
            fragment = new InfoPage();
        } else if (id == R.id.sign_in) {
            if (SignPage.SIGNED) {
                SignPage.SIGNED = false;
                Toast.makeText(this, "Signed out", Toast.LENGTH_SHORT).show();
            } else {
                fragment = new SignPage();
            }
        }

        if (fragment != null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frame, fragment).commit();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }

}
