package fr.brgm.georisques.presenter;

import android.support.v4.app.FragmentActivity;
import android.widget.EditText;
import android.widget.TextView;

import junit.framework.Assert;

import org.junit.Test;

import fr.brgm.georisques.view.BandeauView;
import fr.brgm.georisques.view.LocalisationView;

/**
 * Created by a601912 on 13/05/2015.
 */
public class LocalisationPresenterTest {

 /*   @Test
    public void TestLocalisationPresenter() {

        final EditText codePostal = new EditText(null);
        final EditText commune = new EditText(null);
        final EditText voie = new EditText(null);
        final EditText numero = new EditText(null);

        final EditText latitude = new EditText(null);
        final EditText longitude = new EditText(null);
        final FragmentActivity fragActivity = new FragmentActivity();

        codePostal.setText("45000");
        commune.setText("Loiret");
        voie.setText("Rue du test");
        numero.setText("5");
        latitude.setText("1.9");
        longitude.setText("47.9");

        final LocalisationView l = new LocalisationView() {
            @Override
            public EditText getLatitude() {
                return latitude;
            }

            @Override
            public EditText getLongitude() {
                return longitude;
            }

            @Override
            public EditText getCode() {
                return codePostal;
            }

            @Override
            public EditText getCommune() {
                return commune;
            }

            @Override
            public EditText getVoie() {
                return voie;
            }

            @Override
            public EditText getNumero() {
                return numero;
            }

            @Override
            public FragmentActivity getFragActivity() {
                return fragActivity;
            }
        };*/
    /*
     * Instanciate LocalisationPresenterImpl
     */
    //    LocalisationPresenterImpl localisationPresenterImp = new LocalisationPresenterImpl(l);
    /*
     * Get parameters of the constructor
     */
    //    LocalisationView loc = localisationPresenterImp.locView;

    /*
     * Get latitude, longitude and fragment values from l
     */
     /*   EditText codePostalResult = l.getCode();
        EditText communeResult = l.getCommune();
        EditText voieResult = l.getVoie();
        EditText numeroResult = l.getNumero();
        EditText latitudeResult = l.getLatitude();
        EditText longitudeResult = l.getLongitude();
        FragmentActivity fragActivityResult = l.getFragActivity();

        Assert.assertEquals(l, loc);
        Assert.assertEquals(codePostal, codePostalResult);
        Assert.assertEquals(commune, communeResult);
        Assert.assertEquals(voie, voieResult);
        Assert.assertEquals(numero, numeroResult);
        Assert.assertEquals(latitude, latitudeResult);
        Assert.assertEquals(longitude, longitudeResult);
        Assert.assertEquals(fragActivity, fragActivityResult);
    }*/
}
