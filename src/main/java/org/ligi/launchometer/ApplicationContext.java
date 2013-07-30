package org.ligi.launchometer;

import android.app.Application;
import com.google.analytics.tracking.android.EasyTracker;

import java.util.ArrayList;
import java.util.List;

/**
 * User: ligi
 * Date: 2/15/13
 * Time: 10:13 PM
 */
public class ApplicationContext extends Application {

    public int fouls=0;
    public List<Integer> results;

    @Override
    public void onCreate() {
        super.onCreate();
        reset();
        EasyTracker.getInstance().setContext(this);
    }

    public void reset() {
        fouls=0;
        results=new ArrayList<Integer>();
    }

}
