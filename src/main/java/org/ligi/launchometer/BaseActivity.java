package org.ligi.launchometer;

import android.app.Activity;
import com.google.analytics.tracking.android.EasyTracker;

/**
 * User: ligi
 * Date: 2/15/13
 * Time: 10:37 PM
 */
public class BaseActivity extends Activity {

    @Override
    protected void onStart() {
        super.onStart();
        EasyTracker.getInstance().activityStart(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EasyTracker.getInstance().activityStop(this);
    }

    public ApplicationContext getApp() {
        return (ApplicationContext)getApplicationContext();

    }

}
