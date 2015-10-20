package fr.brgm.georisques.ui.activity.tablet;



import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;

import android.content.res.Configuration;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Surface;

import android.view.View;
import android.view.WindowManager;

import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;

import android.widget.SearchView;

import fr.brgm.georisques.R;
import fr.brgm.georisques.presenter.MainActivityTabletPresenterImpl;
import fr.brgm.georisques.view.MainActivityTabletView;

/**
 * Created by a601912 on 14/04/2015.
 */

/*
 * For tablet
 */
public class MainActivityTablet extends FragmentActivity implements MainActivityTabletView {

    public static FragmentManager fragmentManager;

    public MainActivityTabletPresenterImpl mainPresenter;

    private static final String[] COUNTRIES = new String[] { "Belgium",
            "France", "France_", "Italy", "Germany", "Spain" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity_tablette);
        fragmentManager = getSupportFragmentManager();

        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowCustomEnabled(true);

        LayoutInflater inflator = (LayoutInflater) this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflator.inflate(R.layout.actest, null);

        actionBar.setCustomView(v);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, COUNTRIES);
        AutoCompleteTextView textView = (AutoCompleteTextView) v
                .findViewById(R.id.editText1);
        textView.setAdapter(adapter);



        mainPresenter = new MainActivityTabletPresenterImpl(this);
    }


    @Override public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        // Checks the orientation of the screen for landscape and portrait and set portrait mode always
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            //Lanscape
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
           //Portrait
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        //Check item selected
        if (item.isChecked()) item.setChecked(false);
        else item.setChecked(true);
        switch (item.getItemId()) {
            case R.id.radioFondTopologique:
                mainPresenter.updateMap(0);
                return true;
            case R.id.radioFondSatellite:
                mainPresenter.updateMap(1);
                return true;
            case R.id.MenulimiteUtilisation:
                startBrowser(getResources().getString(R.string.URL_LIMITE_UTILISATION));
                return true;
            case R.id.MenuPortailBRGM:
                startBrowser(getResources().getString(R.string.URL_PORTAIL_BRGM));
                return true;
            case R.id.MenuPortailGeoRisques:
                startBrowser(getResources().getString(R.string.URL_PORTAIL_GEORISQUES));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    private void startBrowser(String url) {
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }


    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem searchViewMenuItem = menu.findItem(R.id.action_search);

        SearchView mSearchView = (SearchView) searchViewMenuItem.getActionView();

        int searchImgId = getResources().getIdentifier("android:id/search_button", null, null);
        ImageView img = (ImageView) mSearchView.findViewById(searchImgId);
        img.setImageResource(R.drawable.ic_magnifier);

        return super.onPrepareOptionsMenu(menu);
    }


    public int getDeviceDefaultOrientation() {

        WindowManager windowManager =  (WindowManager) getSystemService(Context.WINDOW_SERVICE);

        Configuration config = getResources().getConfiguration();

        int rotation = windowManager.getDefaultDisplay().getRotation();

        if ( ((rotation == Surface.ROTATION_0 || rotation == Surface.ROTATION_180) &&
                config.orientation == Configuration.ORIENTATION_LANDSCAPE)
             /*   || ((rotation == Surface.ROTATION_90 || rotation == Surface.ROTATION_270) &&
                config.orientation == Configuration.ORIENTATION_PORTRAIT)*/) {
            return Configuration.ORIENTATION_LANDSCAPE;
        } else {
            return Configuration.ORIENTATION_PORTRAIT;
        }
    }

}
