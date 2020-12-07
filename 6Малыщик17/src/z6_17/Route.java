package z6_17;
import java.io.Serializable;
import java.text.DateFormat;
import java.util.Date;

public class Route extends Traction implements Serializable {

    public static class ArgException extends Exception {
        ArgException(String message) {
            super(AppLocale.getString(AppLocale.invalid_argument) + ": " + message);
        }
    }

    public final Date creationDate = new Date();
    public String getCreationDate() {
        DateFormat dateFormatter = DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.FULL,AppLocale.get());
        String dateOut = dateFormatter.format(creationDate);
        return dateOut;
    }
    public enum Type { BUS, TRAM, TROLLEYBUS };
    private Type type;
    public Type getType() { return type; }

    public enum State {WORKS, BROKEN, RESERVE}
    private State state;
    public State getState() { return state; }
    public void setState(State state1) { this.state = state1; }

    
    public String convertType(Type type) {
    	if (type == Type.BUS)
    		return AppLocale.getString(AppLocale.BUS);
    	else if (type == Type.TROLLEYBUS)
    		return AppLocale.getString(AppLocale.TROLLEYBUS);
    	return AppLocale.getString(AppLocale.TRAM);
    }
    public String convertState(State state) {
    	if (state == State.WORKS)
    		return AppLocale.getString(AppLocale.WORKS);
    	else if (state == State.BROKEN)
    		return AppLocale.getString(AppLocale.BROKEN);
    	return AppLocale.getString(AppLocale.RESERVE);
    }

    public String toString()
    {
        String str = AppLocale.getString( AppLocale.type ) + " : " + convertType(type) + " " +
                AppLocale.getString( AppLocale.state ) + " : " + convertState(state) + " " +
                AppLocale.getString( AppLocale.creation ) + ": " + getCreationDate();
        return str;
    }
    public Route(){}

    protected Route(Type type, State state){
        this.type = type;
        this.state = state;
    }

    public void transportBreak( Traction traction, int index) {
        Route route1  = traction.getTranspotByIndex(index);
        if(route1.state == State.WORKS)
        {
            boolean check = false;
            traction.getTranspotByIndex(index).state = State.BROKEN;
            System.out.println(convertType(traction.getTranspotByIndex(index).getType()) + " " + AppLocale.getString(AppLocale.broken) );
            for ( int i =0; i< traction.getSize();i++ ) {
                if(traction.getTranspotByIndex(i).getState()==State.RESERVE) {
                    check = true;
                    traction.getTranspotByIndex(i).setState(State.WORKS);
                    System.out.println(convertType(traction.getTranspotByIndex(i).getType()) + " " + AppLocale.getString(AppLocale.replace));
                    break;
                }
            }
            if(!check){
                System.out.println(AppLocale.getString(AppLocale.noReserve));
                traction.setInterval(traction.getInterval()*2);
            }
        }else
        {
            System.out.println(AppLocale.getString(AppLocale.backup));
        }
    }
}
