package fr.brgm.georisques.ui.activity.common;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;


import fr.brgm.georisques.R;
import fr.brgm.georisques.ui.activity.handset.MainActivityHandset;
import fr.brgm.georisques.ui.activity.tablet.MainActivityTablet;

/*
 * Main Activity
 */
public class EntryPointActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.entry_point);
        setTitle(R.string.app_name);
        Intent i;

        if (findViewById(R.id.isTablette) != null) {
            i = new Intent(EntryPointActivity.this, MainActivityTablet.class);
        } else {
            i = new Intent(EntryPointActivity.this, MainActivityHandset.class);
        }
        startActivity(i);
        finish();
    }
}
