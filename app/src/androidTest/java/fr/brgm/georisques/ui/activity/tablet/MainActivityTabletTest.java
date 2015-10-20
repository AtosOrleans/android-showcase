package fr.brgm.georisques.ui.activity.tablet;


import android.content.ClipData;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;
import fr.brgm.georisques.R;
import fr.brgm.georisques.adapter.ItemAdapter;
import fr.brgm.georisques.presenter.LocalisationPresenterImpl;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.swipeDown;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.action.ViewActions.swipeRight;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasSibling;
import static android.support.test.espresso.matcher.ViewMatchers.isChecked;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isSelected;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.anything;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasToString;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.startsWith;


/**
 * Created by a601912 on 12/05/2015.
 */
public class MainActivityTabletTest extends ActivityInstrumentationTestCase2<MainActivityTablet> {

    public MainActivityTabletTest() {
        super(MainActivityTablet.class);
    }

    @Override
    protected void setUp() throws Exception
    {
        getActivity();
        super.setUp();
    }

    /*
    * Verification si l'action bar s'affiche et est fonctionnelle
     */
    public void testKeyboard() throws InterruptedException {

        onView(withId(R.id.action_search)).check(matches(isDisplayed()));

        onView(withId(R.id.action_search)).perform(click());
        onView(withId(R.id.action_search)).perform(typeText("Mode Landscape"), closeSoftKeyboard());
        Thread.sleep(50);

        // On test le changement d'orientation en portrait
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        onView(withId(R.id.action_search)).perform(click());
        onView(withId(R.id.action_search)).perform(typeText("Mode Portrait"), closeSoftKeyboard());
        Thread.sleep(50);
        }

    /*
   * Verification si le menu des fonds s'affiche et est fonctionnel
    */
    public void testMenuFond() throws InterruptedException {

            onView(withId(R.id.action_settings)).check(matches(isDisplayed()));

            onView(withId(R.id.action_settings)).perform(click());
            Thread.sleep(50);

            //WithId Don't work here
            onView(withText("Fond Satellite")).perform(click());
            Thread.sleep(50);

            onView(withId(R.id.action_settings)).perform(click());
            Thread.sleep(50);

        //TODO
        //Verify item selected on the menu
        //onView(withText("Fond Satellite")).check(matches(isSelected()));
        //Thread.sleep(50);
        //Avoid menu slide to disappear !!!

    }

    /*
   * Verification si le menu des liens extérieur s'affiche et est fonctionnel
    */
    public void testMenuLink(){

        onView(withId(R.id.menu_new_form)).check(matches(isDisplayed()));

       // onView(withId(R.id.menu_new_form)).perform(click());

        //onView(withId(R.id.MenulimiteUtilisation)).perform(click());

        //onView(withId(R.id.MenuPortailBRGM)).perform(click());

       // onView(withId(R.id.MenuPortailGeoRisques)).perform(click());



    }

    /*
    * Verification lorsque l'on insere des donnees dans l'onglet localisation pour les coordonnees (Landscape - Portrait)
     */
    public void testLocalisationCoordonnees() throws InterruptedException {

        //Pour Portrait
        if (super.getActivity().getDeviceDefaultOrientation() == Configuration.ORIENTATION_PORTRAIT) {

            onView(withId(R.id.handle)).check(matches(isDisplayed()));

            // Si on est en portrait, on clique sur le bouton pour derouler le menu
             onView(withId(R.id.handle)).perform(click());
        }

            // On slide pour aller dans le fragment qui nous interesse
            onView(withId(R.id.form_menugauche)).perform(swipeLeft());

            // On verifie que les EditText sont vides au départ

            //Par coordonnees
            onView(withId(R.id.form_coordonneePostale)).check(matches(withText("Par Coordonnées Postales")));
            onView(withId(R.id.form_coordonnee_commune)).check(matches(withText("")));
            onView(withId(R.id.form_coordonnee_voie)).check(matches(withText("")));


            // On ecris dans les editText coordonnees
            onView(withId(R.id.form_coordonnee_commune)).perform(typeText("Code"));

        if (super.getActivity().getDeviceDefaultOrientation() == Configuration.ORIENTATION_LANDSCAPE) {

                 // On test le changement d'orientation en portrait
                getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                onView(withId(R.id.handle)).check(matches(isDisplayed()));

                // Si on est en portrait, on clique sur le bouton pour derouler le menu
                onView(withId(R.id.handle)).perform(click());
                // On slide pour aller dans le fragment qui nous interesse
                onView(withId(R.id.form_menugauche)).perform(swipeLeft());

        }
        if (super.getActivity().getDeviceDefaultOrientation() == Configuration.ORIENTATION_PORTRAIT) {

            onView(withId(R.id.form_coordonnee_voie)).perform(typeText("Voie"), closeSoftKeyboard());
            Thread.sleep(50);

            // On verifie que tout est correct pour coordonnees
            onView(withId(R.id.form_coordonnee_commune)).check(matches(withText("Commune")));

            // On clique sur le bouton effacer de coordonnees
            onView(withId(R.id.bouton_coordonnee_effacer)).perform(click());
            //On verifie que tous les champs ont été effacés
            onView(withId(R.id.form_coordonnee_commune)).check(matches(withText("")));
            onView(withId(R.id.form_coordonnee_voie)).check(matches(withText("")));

            //click du bouton valider (non fonctionel actuellement)
            //onView(withId(R.id.bouton_coordonnee_valider)).perform(click());
        }
    }

    /*
   * Verification lorsque l'on insere des donnees dans l'onglet localisation pour le gps (Landscape - Portrait)
    */
    public void testLocalisationGps() throws InterruptedException {
        //Pour Portrait
        if (super.getActivity().getDeviceDefaultOrientation() == Configuration.ORIENTATION_PORTRAIT) {

            // Si on est en portrait, on clique sur le bouton pour derouler le menu
            onView(withId(R.id.handle)).perform(click());
        }


        // On slide pour aller dans le fragment qui nous interesse
        onView(withId(R.id.form_menugauche)).perform(swipeLeft());
        // Par Gps
        onView(withId(R.id.form_coordonneeGps)).check(matches(withText("Par GPS")));
        onView(withId(R.id.form_gps_latitude)).check(matches(withText("")));
        onView(withId(R.id.form_gps_longitude)).check(matches(withText("")));

        // On ecris dans les editText gps
        onView(withId(R.id.form_gps_latitude)).perform(typeText("45"), closeSoftKeyboard());
        Thread.sleep(50);

        if (super.getActivity().getDeviceDefaultOrientation() == Configuration.ORIENTATION_LANDSCAPE) {
            // On test le changement d'orientation en portrait
            getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            onView(withId(R.id.handle)).check(matches(isDisplayed()));

            // Si on est en portrait, on clique sur le bouton pour derouler le menu
            onView(withId(R.id.handle)).perform(click());
            // On slide pour aller dans le fragment qui nous interesse
            onView(withId(R.id.form_menugauche)).perform(swipeLeft());
        }
        if (super.getActivity().getDeviceDefaultOrientation() == Configuration.ORIENTATION_PORTRAIT) {
            onView(withId(R.id.form_gps_longitude)).perform(typeText("3"), closeSoftKeyboard());
            Thread.sleep(50);

            // On clique sur le bouton effacer
            onView(withId(R.id.bouton_gps_effacer)).perform(click());
            //On verifie que tous les champs ont été effacés
            onView(withId(R.id.form_gps_latitude)).check(matches(withText("")));
            onView(withId(R.id.form_gps_longitude)).check(matches(withText("")));

            // On test le changement d'orientation en landscape avant d'afficher le point sur la map
            getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

            //click du bouton valider
            onView(withId(R.id.bouton_gps_valider)).perform(click());

            //TODO
            //Verifier bon point sur la map
            //onView(withId(R.id.fragment_carte)).check(matches(withText("")));
            //onView(withId(R.id.fragment_carte)).perform(new Marker);
        }
    }
    /*
     * Test Stress switch orientation
     */

    public void testStress() throws InterruptedException {
        for(int i =  0 ; i <  15 ; ++i){
            if(i%2 == 0){
                getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            }else{
                getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            }
            Log.d("toto", "toto"+i);
            Thread.sleep(2000);
        }
    }
    /*
 * Verification si les coordonnées gps sont corrects
  */
    public void testAdressGpsValues() throws InterruptedException {
        //Pour Portrait
        if (super.getActivity().getDeviceDefaultOrientation() == Configuration.ORIENTATION_PORTRAIT) {

            // Si on est en portrait, on clique sur le bouton pour derouler le menu
            onView(withId(R.id.handle)).perform(click());
        }

        // On slide pour aller dans le fragment qui nous interesse
        onView(withId(R.id.form_menugauche)).perform(swipeLeft());
        onView(withId(R.id.form_gps_latitude)).check(matches(withText("")));
        onView(withId(R.id.form_gps_longitude)).check(matches(withText("")));
        // pour tester si les valeurs du gps du bandeau sont coherentes
        onView(withId(R.id.form_gps_latitude)).perform(typeText("48"), closeSoftKeyboard());
        Thread.sleep(50);

        if (super.getActivity().getDeviceDefaultOrientation() == Configuration.ORIENTATION_LANDSCAPE) {
            // On test le changement d'orientation en portrait
            getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            onView(withId(R.id.handle)).check(matches(isDisplayed()));

            // Si on est en portrait, on clique sur le bouton pour derouler le menu
            onView(withId(R.id.handle)).perform(click());
            // On slide pour aller dans le fragment qui nous interesse
            onView(withId(R.id.form_menugauche)).perform(swipeLeft());

        }
        if (super.getActivity().getDeviceDefaultOrientation() == Configuration.ORIENTATION_PORTRAIT) {
            onView(withId(R.id.form_gps_longitude)).perform(typeText("2"), closeSoftKeyboard());
            Thread.sleep(50);
            //click du bouton valider
            onView(withId(R.id.bouton_gps_valider)).perform(click());
            //onView(withId(R.id.gps)).check(matches(isDisplayed()));
            onView(withId(R.id.adress)).check(matches(withText("Route de Lamberté" + "\n\n" + "Rebréchien")));
            onView(withId(R.id.gps)).check(matches(withText("GPS:lat/lng: (48.0,2.0)")));
        }
    }

    public void testWeather() throws InterruptedException {
        //Pour Portrait
        if (super.getActivity().getDeviceDefaultOrientation() == Configuration.ORIENTATION_PORTRAIT) {

            // Si on est en portrait, on clique sur le bouton pour derouler le menu
            onView(withId(R.id.handle)).perform(click());
        }

        // On slide pour aller dans le fragment qui nous interesse
        onView(withId(R.id.form_menugauche)).perform(swipeLeft());

        // On ecris dans les editText gps
        onView(withId(R.id.form_gps_latitude)).perform(typeText("45"), closeSoftKeyboard());
        Thread.sleep(50);
        onView(withId(R.id.form_gps_longitude)).perform(typeText("3"), closeSoftKeyboard());
        Thread.sleep(50);

        //click du bouton valider
        onView(withId(R.id.bouton_gps_valider)).perform(click());

        // On slide pour aller dans le fragment de depart
        onView(withId(R.id.form_menugauche)).perform(swipeRight());

        //On refresh le fragment
        onView(withId(R.id.form_menugauche)).perform(swipeDown());


        onView(withId(R.id.location_name)).check(matches(withText("Tanavelle")));

    }
}
