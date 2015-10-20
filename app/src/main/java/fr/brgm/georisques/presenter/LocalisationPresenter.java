package fr.brgm.georisques.presenter;


import fr.brgm.georisques.ui.event.GpsMapEvent;
import fr.brgm.georisques.view.LocalisationView;

/**
 * Created by a601912 on 06/05/2015.
 */

/*
 * LocalisationPresenter Interface
 */
public interface LocalisationPresenter {

    public void updateOnClick();

    public void updateCoordonneeOnClick();

    public void deleteOnClick();

    public void deleteCoordonneeOnClick();

}
