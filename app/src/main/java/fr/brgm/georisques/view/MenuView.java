package fr.brgm.georisques.view;

import android.support.v4.view.ViewPager;
import android.widget.TabHost;

import fr.brgm.georisques.adapter.TabsAdapter;

/**
 * Created by a601912 on 11/05/2015.
 */
public interface MenuView {

    public TabHost getmTabHost();

    public ViewPager getmViewPager();

    public TabsAdapter getmTabsAdapter();

}
