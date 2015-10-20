package fr.brgm.georisques.ui.activity.common;

import android.app.Activity;
import android.os.Bundle;

import java.util.List;

import dagger.ObjectGraph;
import fr.brgm.georisques.App;

/**
 * Created by a601912 on 07/05/2015.
 */
public abstract class BaseActivity extends Activity {

    private ObjectGraph activityGraph;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityGraph = ((App) getApplication()).createScopedGraph(getModules().toArray());
        activityGraph.inject(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        activityGraph = null;
    }

    protected abstract List<Object> getModules();

    public void inject(Object object) {
        activityGraph.inject(object);
    }
}
