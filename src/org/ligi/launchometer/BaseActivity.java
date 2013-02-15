package org.ligi.launchometer;

import android.app.Activity;

/**
 * User: ligi
 * Date: 2/15/13
 * Time: 10:37 PM
 */
public class BaseActivity extends Activity {

    public ApplicationContext getApp() {
        return (ApplicationContext)getApplicationContext();
    }

}
