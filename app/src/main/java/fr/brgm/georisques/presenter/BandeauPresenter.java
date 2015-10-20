package fr.brgm.georisques.presenter;

import fr.brgm.georisques.ui.event.CarteMapEvent;
import fr.brgm.georisques.ui.event.GpsMapEvent;

/*
 * Created by a601912 on 11/05/2015.
 */

/*
 * BandeauPresenter Interface
 */
public interface BandeauPresenter {

    public void updateAdress(String adress);

    public void updatePointFromCarte(CarteMapEvent cme);

    public void updatePointFromGPS(GpsMapEvent gme);

    public void OnResume();

    public void OnPause();
}
