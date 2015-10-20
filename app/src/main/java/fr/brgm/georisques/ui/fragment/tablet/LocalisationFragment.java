package fr.brgm.georisques.ui.fragment.tablet;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import fr.brgm.georisques.R;
import fr.brgm.georisques.presenter.LocalisationPresenterImpl;
import fr.brgm.georisques.util.BusProvider;
import fr.brgm.georisques.view.LocalisationView;

/**
 * Created by a601912 on 14/04/2015.
 */

/*
 * Fragment for the search with gps
 */

public class LocalisationFragment extends Fragment implements LocalisationView {

    //public EditText CodePostal;
    public EditText Ville;
    public EditText Adresse;
   // public EditText Numero;
    public Button ValidateAdress;
    public Button DeleteAdress;

    public EditText lat;
    public EditText lon;
    public Button ValidateGps;
    public Button DeleteGps;

    public LocalisationPresenterImpl locPresenter;

    public LocalisationFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View locView = inflater.inflate(R.layout.fragment_localisation, container, false);

        BusProvider.getInstance().register(this);
        locPresenter = new LocalisationPresenterImpl(this);

        //CodePostal = (EditText) locView.findViewById(R.id.form_coordonnee_code);
        Ville = (EditText) locView.findViewById(R.id.form_coordonnee_commune);
        Adresse = (EditText) locView.findViewById(R.id.form_coordonnee_voie);
        //Numero = (EditText) locView.findViewById(R.id.form_coordonnee_numero);
        ValidateAdress = (Button) locView.findViewById(R.id.bouton_coordonnee_valider);
        ValidateAdress.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                locPresenter.updateCoordonneeOnClick();
                }
        });

        DeleteAdress = (Button) locView.findViewById(R.id.bouton_coordonnee_effacer);
        DeleteAdress.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                locPresenter.deleteCoordonneeOnClick();
            }
        });

        lat = (EditText) locView.findViewById(R.id.form_gps_latitude);
        lon = (EditText) locView.findViewById(R.id.form_gps_longitude);
        ValidateGps = (Button) locView.findViewById(R.id.bouton_gps_valider);
        ValidateGps.setOnClickListener(new View.OnClickListener(){

        @Override
        public void onClick(View v){
            locPresenter.updateOnClick();
            }
        });

        DeleteGps = (Button) locView.findViewById((R.id.bouton_gps_effacer));
        DeleteGps.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                locPresenter.deleteOnClick();
            }
        });

        return locView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
    }

    /*
     * Getter
     */
    @Override
    public EditText getLatitude() {
        return lat;
    }

    @Override
    public EditText getLongitude() {
        return lon;
    }

   /* @Override
    public EditText getCode() {
        return CodePostal;
    }*/

    @Override
    public EditText getVille() {
        return Ville;
    }

    @Override
    public EditText getAdresse() {
        return Adresse;
    }

   /* @Override
    public EditText getNumero() {
        return Numero;
    }*/

    @Override
    public FragmentActivity getFragActivity() {
        return getActivity();
    }
}
