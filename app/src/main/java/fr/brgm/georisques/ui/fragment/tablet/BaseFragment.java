package fr.brgm.georisques.ui.fragment.tablet;

import android.app.Fragment;
import android.os.Bundle;

import fr.brgm.georisques.ui.activity.common.BaseActivity;

/**
 * Created by a601912 on 07/05/2015.
 */
public class BaseFragment extends Fragment {

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ((BaseActivity) getActivity()).inject(this);
    }
}
