package z6_17;

import java.io.Serializable;
import java.util.ArrayList;


public class Traction implements Serializable {
    private static final long serialVersionUID = 1L;

    public static class ArgException extends Exception {
        ArgException(String message) {
            super(AppLocale.getString(AppLocale.invalid_argument) + ": " + message);
        }
    }
    private ArrayList<Route> routeArrayList = new ArrayList<Route>();
    private int interval;
    public void setInterval(int interval){this.interval = interval;}
    public int getInterval(){return this.interval;}

    public Route getTranspotByIndex(int inx)
    {
        return routeArrayList.get(inx);
    }

    public int getSize(){return routeArrayList.size();}
    public Traction(){}
    public Traction(ArrayList<Route> routes, int interval) throws ArgException {
        if(routes.size() == 0)
            throw new ArgException(AppLocale.getString(AppLocale.noTransport));
        
        this.routeArrayList = routes;
        if(interval < 1 || interval > 24*60)
        	throw new ArgException(AppLocale.getString(AppLocale.interval));
        this.interval = interval;
    }
    public void printTraction(Traction tractions) throws ArgException {
        if ( tractions.getSize() <=0 ) {
            throw new ArgException(AppLocale.getString(AppLocale.noTransport));
        }
        System.out.println(AppLocale.getString(AppLocale.interval) + ": " + getInterval());
        for (int i = 0; i < tractions.getSize(); i++) {
            System.out.println(tractions.getTranspotByIndex(i).toString());
        }
    }
}
