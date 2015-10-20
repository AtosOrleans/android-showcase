package fr.brgm.georisques.presenter;

import android.graphics.Color;
import android.widget.TextView;
import javax.inject.Inject;
import fr.brgm.georisques.ui.fragment.tablet.LocalisationFragment;
import fr.brgm.georisques.ui.fragment.tablet.WeatherFragment;
import fr.brgm.georisques.view.MenuView;

/**
 * Created by a601912 on 11/05/2015.
 */

/*
 * MenuPresenter Implementation
 */
public class MenuPresenterImpl implements MenuPresenter {
    /*
     * View
     */
    private MenuView menuView;

    /*
     * Annotate the constructor that Dagger should use to create instances of a class
     */
    @Inject
    public MenuPresenterImpl(MenuView menuView){
        this.menuView = menuView;
    }

    /*
     * Create tabHost with specific fragment
     */
    @Override
    public void createTab() {
        // Here we load the content for each tab.
        menuView.getmTabsAdapter().addTab(menuView.getmTabHost().newTabSpec("one").setIndicator("Température"), WeatherFragment.class, null);
        menuView.getmTabsAdapter().addTab(menuView.getmTabHost().newTabSpec("three").setIndicator("Localisation"), LocalisationFragment.class, null);

        //Change the textColor
        for (int i = 0; i < menuView.getmTabHost().getTabWidget().getChildCount(); i++) {
            TextView tv = (TextView) menuView.getmTabHost().getTabWidget().getChildAt(i).findViewById(android.R.id.title);
            tv.setTextColor(Color.parseColor("#FFFFFF"));
        }
    }
}
