package fr.brgm.georisques.ui.fragment.tablet;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import fr.brgm.georisques.R;

/**
 * Created by a601912 on 14/04/2015.
 */

/*
 * Fragment which display risks
 */
public class MesRisquesFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_mes_risques, container, false);
    }
}

