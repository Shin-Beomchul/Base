package com.godbeom.baseapp.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;

import android.os.LocaleList;
import android.preference.PreferenceManager;
import android.webkit.WebView;

import androidx.annotation.RequiresApi;

import com.godbeom.baseapp.Application;

import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.Set;

import static android.os.Build.VERSION_CODES.JELLY_BEAN_MR1;
import static android.os.Build.VERSION_CODES.N;

/**
  * 비엄격모드 : 언어코드로 리소스 변경
  *
  *  용도 : 예상치 못한 국가에서 접근 했을때도 나라는 구별할 수 없지만 언어는 보여짐.
  *
  *  단점 : 1. 언어만 제어 가능.
  *
  *  use : 리소스를 언어별로 생성.
  *
 */
public class LanguageManager {

    public static final  String LANGUAGE_ENGLISH   = "en";
    public static final  String LANGUAGE_RUSSIAN   = "ru";
    public static final  String LANGUAGE_KOREAN   = "ko";
    public static final  String LANGUAGE_CHINA   = "zh";
    private static final String LANGUAGE_KEY       = "language_key";

    private final SharedPreferences prefs;

    public LanguageManager(Context context) {
        prefs = PreferenceManager.getDefaultSharedPreferences(context);
    }

	private boolean requireWorkaround = true;
	public void setLocaleWithWebView(Context context) {
		if (requireWorkaround) {
			requireWorkaround = false;
			new WebView(context).destroy();
			Application.languageManager.setLocale(context);
		}
	}

    public Context setLocale(Context c) {
        return updateResources(c, getLanguage(c));
    }

    public Context setNewLocale(Context c, String language) {
        persistLanguage(language);
        return updateResources(c, language);
    }

    public String getLanguage(Context c) {
        return prefs.getString(LANGUAGE_KEY, getDeviceLanguage(c));
    }

    public String getDeviceLanguage(Context c){
        Locale currentLocale = c.getResources().getConfiguration().locale;
        return currentLocale.getLanguage();
    }

    @SuppressLint("ApplySharedPref")
    private void persistLanguage(String language) {
        // use commit() instead of apply(), because sometimes we kill the application process
        // immediately that prevents apply() from finishing
        prefs.edit().putString(LANGUAGE_KEY, language).commit();
    }

    private Context updateResources(Context context, String language) {
        Locale locale = new Locale(language);
        Locale.setDefault(locale);

        Resources res = context.getResources();
        Configuration config = new Configuration(res.getConfiguration());
        if (Utility.isAtLeastVersion(N)) {
            setLocaleForApi24(config, locale);
            context = context.createConfigurationContext(config);
        } else if (Utility.isAtLeastVersion(JELLY_BEAN_MR1)) {
            config.setLocale(locale);
            context = context.createConfigurationContext(config);
        } else {
            config.locale = locale;
            res.updateConfiguration(config, res.getDisplayMetrics());
        }
        return context;
    }

    @RequiresApi(api = N)
    private void setLocaleForApi24(Configuration config, Locale target) {
        Set<Locale> set = new LinkedHashSet<>();
        // bring the target locale to the front of the list
        set.add(target);

        LocaleList all = LocaleList.getDefault();
        for (int i = 0; i < all.size(); i++) {
            // append other locales supported by the user
            set.add(all.get(i));
        }

        Locale[] locales = set.toArray(new Locale[0]);
        config.setLocales(new LocaleList(locales));
    }

    public static Locale getLocale(Resources res) {
        Configuration config = res.getConfiguration();
        return Utility.isAtLeastVersion(N) ? config.getLocales().get(0) : config.locale;
    }


//    public static Drawable getFlagDrawableByLocale(Context context, String localeCode){
//
//         Drawable drawable = context.getDrawable(R.drawable.ic_launcher_foreground);
//
//         if(localeCode.contains("KR")){
//             drawable =context.getDrawable(R.drawable.ic_kr);
//         }else if(localeCode.contains("US")){
//             drawable = context.getDrawable(R.drawable.ic_us);
//         }else if(localeCode.contains("RU")){
//             drawable =context.getDrawable(R.drawable.ic_ru);
//         }else if(localeCode.contains("CN")){
//             drawable =context.getDrawable(R.drawable.ic_cn);
//         }
//
//         return drawable;
//
//    }
//
//    public static Drawable getFlagDrawableByLang(Context context, String langCode){
//
//         Drawable drawable = context.getDrawable(R.drawable.ic_launcher_foreground);
//
//         if(langCode.contains(LANGUAGE_KOREAN)){
//             drawable =context.getDrawable(R.drawable.ic_kr);
//         }else if(langCode.contains(LANGUAGE_ENGLISH)){
//             drawable = context.getDrawable(R.drawable.ic_us);
//         }else if(langCode.contains(LANGUAGE_RUSSIAN)){
//             drawable =context.getDrawable(R.drawable.ic_ru);
//         }else if(langCode.contains(LANGUAGE_CHINA)){
//             drawable =context.getDrawable(R.drawable.ic_cn);
//         }
//
//         return drawable;
//	}
}