package fr.brgm.georisques;

/**
 * Created by a601912 on 07/05/2015.
 */
public class Modules {

    private Modules() {
        // No instances
    }


    static Object[] list(App ngswApp) {
        return new Object[]{
                new AppModule(ngswApp),
        };
    }

}