package fr.brgm.georisques;


import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by a601912 on 07/05/2015.
 */

@Module(
        injects = {
                App.class
        },
        library = true
)
public class AppModule {
    private final App app;

    public AppModule(App app) {
        this.app = app;
    }


    @Provides
    @Singleton
    Application provideApplication() {
        return app;
    }
}