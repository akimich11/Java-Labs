package z7_7;

import java.io.*;
import java.text.ParseException;
import java.util.*;

public class Test {

    public static void main(String[] args) {
        try {
            if ( args.length >= 1 ) {
                if ( args[0].compareTo( "-a" ) == 0 )
                    append_file();
                else if ( args[0].compareTo( "-p" ) == 0 )
                    print_file();
                else if ( args[0].compareTo( "-d" ) == 0 )
                    delete_file();
                else {
                    System.err.println( "Option is not realised: " + args[0] );
                    System.exit(1);
                }
            }
            else
                System.err.println( "Bills: Nothing to do!" );
        }
        catch ( Exception e ) {
            System.err.println( "Run/time error: " + e );
            System.exit(1);
        }
        System.out.println( "\nBills finished..." );	
	System.exit(0);
    }

    static final String filename = "Bills.dat";
	
    private static Scanner s = new Scanner( System.in );

    static Bill read_bill() throws ParseException {
        if ( s.hasNextLine())
            return Bill.read(s);
        return null;
    }
	
    static void delete_file() {
        File f = new File( filename );
        f.delete();
    }
	
    static void append_file() throws FileNotFoundException, IOException, ParseException {
        Bill bill;
        System.out.println("Enter bill date (DD.MM.YYYY):");
        try ( RandomAccessFile raf = new RandomAccessFile( filename, "rw" )) {
            while (( bill = read_bill()) != null )
                Buffer.writeObject( raf, bill );
        }
    }

    static void print_file() 
            throws FileNotFoundException, IOException, ClassNotFoundException {
        try ( RandomAccessFile raf = new RandomAccessFile( filename, "rw" )) {
            long pos;
            while (( pos = raf.getFilePointer()) < raf.length() ) {
                Bill bill = (Bill) Buffer.readObject( raf, pos );
                System.out.println( pos + ": " + bill );
            }
        }		
    }
}
