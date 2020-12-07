package z6_17;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Main {

    public static Traction createTraction() throws Traction.ArgException {
        System.out.println(AppLocale.getString(AppLocale.enterInterval));
        Scanner scanner = new Scanner(System.in);
        int interval = scanner.nextInt();
        ArrayList<Route> routes = new ArrayList<Route>();
        routes.add(new Bus(Route.State.WORKS));
        routes.add(new Tram(Route.State.WORKS));
        routes.add(new Trolleybus(Route.State.RESERVE));
        Traction tractions = new Traction(routes,interval);
        routes.get(0).transportBreak(tractions,0);
        routes.get(1).transportBreak(tractions,1);

        tractions.printTraction(tractions);
        scanner.close();
        return tractions;
    }
    static Locale createLocale(String[] args )	{
        if ( args.length == 2 ) {
            return new Locale( args[0], args[1] );
        } else if( args.length == 4 ) {
            return new Locale( args[2], args[3] );
        }
        return null;
    }
    static void setupConsole(String[] args) {
        if ( args.length >= 2 ) {
            if ( args[0].compareTo("-encoding")== 0 ) {
                try {
                    System.setOut( new PrintStream( System.out, true, args[1] ));
                } catch ( UnsupportedEncodingException ex ) {
                    System.err.println( "Unsupported encoding: " + args[1] );
                    System.exit(1);
                }
            }
        }
    }

    public static void main(String[] args) {

        try {
            setupConsole( args );
            Locale loc = createLocale( args );
            if ( loc == null ) {
                System.err.println(
                        "Invalid argument(s)\n" +
                                "Syntax: [-encoding ENCODING_ID] language country\n" +
                                "Example: -encoding Cp855 be BY" );
                System.exit(1);
            }
            AppLocale.set( loc );
            Connector con = new Connector("route.dat");
            con.write( createTraction());
            Traction tractions = con.read();
            tractions.printTraction(tractions);
            
        }
        catch ( Exception e ) {
            System.err.println(e);
        }
    }
}
