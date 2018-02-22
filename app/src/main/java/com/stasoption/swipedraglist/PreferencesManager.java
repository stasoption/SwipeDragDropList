package com.stasoption.swipedraglist;

import android.content.Context;
import android.support.annotation.NonNull;
import com.orhanobut.hawk.Hawk;
import com.stasoption.swipedraglist.Model.Avenger;

import java.util.List;


/**
 * @author Stas Averin
 */

public class PreferencesManager {

    private static final String AVENGERS = "avengers";

    private static PreferencesManager sPreferencesManager;

    public static void init(@NonNull Context context) {
        sPreferencesManager = new PreferencesManager(context);
    }

    public static PreferencesManager getInstance() {
        return sPreferencesManager;
    }

    private PreferencesManager(@NonNull Context context) {
        Hawk.init(context).build();
    }

    public void saveAvengers(@NonNull List<Avenger> list) {
        Hawk.put(AVENGERS, list);
    }

    @NonNull
    public List<Avenger> getAvengers() {
        return Hawk.get(AVENGERS,  Avenger.getAvengers());
    }

    public void clear() {
        Hawk.delete(AVENGERS);

    }
}
