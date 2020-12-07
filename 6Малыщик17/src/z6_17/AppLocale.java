package z6_17;

import java.util.Locale;
import java.util.ResourceBundle;

public class AppLocale {

    private static final String strMsg = "Msg";
    private static Locale loc = Locale.getDefault();
    private static ResourceBundle res = ResourceBundle.getBundle( AppLocale.strMsg, AppLocale.loc );

    
    public static final String BUS="BUS";
    public static final String TROLLEYBUS="TROLLEYBUS";
    public static final String TRAM="TRAM";
    public static final String WORKS="WORKS";
    public static final String BROKEN="BROKEN";
    public static final String RESERVE="RESERVE";
    
    public static final String type="type";
    public static final String state="state";
    public static final String creation="creation";
    public static final String broken="broken";
    public static final String noReserve="noReserve";
    public static final String backup="backup";
    public static final String replace="replace";
    public static final String invalid_argument="invalid_argument";
    public static final String noTransport="noTransport";
    public static final String interval="interval";
    public static final String enterInterval="enterInterval";

    static Locale get() {
        return AppLocale.loc;
    }

    static void set( Locale loc ) {
        AppLocale.loc = loc;
        res = ResourceBundle.getBundle( AppLocale.strMsg, AppLocale.loc );
    }

    static ResourceBundle getBundle() {
        return AppLocale.res;
    }

    static String getString( String key ) {
        return AppLocale.res.getString(key);
    }

}
