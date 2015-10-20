package fr.brgm.georisques.ui.fragment.tablet;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost;

import fr.brgm.georisques.R;
import fr.brgm.georisques.adapter.TabsAdapter;
import fr.brgm.georisques.presenter.MenuPresenterImpl;
import fr.brgm.georisques.view.MenuView;


/**
 * Created by a601912 on 20/04/2015.
 */

/*
 * Fragment for portait orientation
 */
public class MenuCentralFragment extends Fragment implements MenuView{

    private TabHost mTabHost;
    private ViewPager mViewPager;
    private TabsAdapter mTabsAdapter;
    public MenuPresenterImpl menuPresenter;

    public MenuCentralFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_menu_main, container, false);

        mTabHost = (TabHost) v.findViewById(android.R.id.tabhost);
        mTabHost.setup();

        mViewPager = (ViewPager) v.findViewById(R.id.pager);
        mTabsAdapter = new TabsAdapter(getActivity(), mTabHost, mViewPager);

        menuPresenter = new MenuPresenterImpl(this);
        menuPresenter.createTab();
        return v;
    }

    /*
     * Getter
     */
    @Override
    public TabHost getmTabHost() {
        return mTabHost;
    }

    @Override
    public ViewPager getmViewPager() {
        return mViewPager;
    }

    @Override
    public TabsAdapter getmTabsAdapter() {
        return mTabsAdapter;
    }
}
