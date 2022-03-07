package uz.itech.mentalarifmetika.language;

import static android.content.Context.MODE_PRIVATE;

import static io.paperdb.Paper.put;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.util.Log;

import java.util.List;
import java.util.Locale;

import io.paperdb.Paper;
import uz.itech.mentalarifmetika.Constant.Constans;

public class LocaleManager {

    public static Context setLocale(Context mContext){
        return updateResources(mContext, getLanguagePref(mContext));
    }

    public static Context setNewLocale(Context mContext, String language){
        setLanguagePref(mContext, language);
        return updateResources(mContext,language);
    }

    private static String getLanguagePref(Context mContext) {
        return  Paper.book().read(Constans.SharedPref);
    }

    private static void setLanguagePref(Context mContext, String localeKey) {
         Paper.book().write(Constans.SharedPref,localeKey);
    }

    private static Context updateResources(Context mContext, String languagePref) {
        Locale locale= new Locale(languagePref);
        Locale.setDefault(locale);

        Resources res = mContext.getResources();

        Configuration config = new Configuration(res.getConfiguration());
        if (Build.VERSION.SDK_INT>=17){
            config.setLocale(locale);
            mContext=mContext.createConfigurationContext(config);
            res.updateConfiguration(config,res.getDisplayMetrics());
        }else {
            config.locale=locale;
            res.updateConfiguration(config, res.getDisplayMetrics());
        }
        return mContext;
    }

    public static Locale getLocale(Resources resources){
        Configuration config=resources.getConfiguration();
        return Build.VERSION.SDK_INT>=24 ? config.getLocales().get(0) :config.locale;
    }
}