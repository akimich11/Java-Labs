package z7_7;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Bill implements Serializable {
	
	private static final long serialVersionUID = 2632848647979170750L;
	
    int house_number, flat_number, pay_sum, prosrochka;
	double percent;
	String address, fio;
	Date bill_date;
	
    public static Bill read( Scanner s ) throws ParseException {
        Bill bill = new Bill();
        
        DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        bill.bill_date = format.parse(s.nextLine());
        
        System.out.println("Enter address:");
        if ( ! s.hasNextLine()) return null;
        bill.address = s.nextLine();
        
        System.out.println("Enter house number:");
        if ( ! s.hasNextLine()) return null;
        bill.house_number = Integer.parseInt(s.nextLine());
        
        System.out.println("Enter flat number:");
        if ( ! s.hasNextLine()) return null;
        bill.flat_number = Integer.parseInt(s.nextLine());
        
        System.out.println("Enter fio:");
        if ( ! s.hasNextLine()) return null;
        bill.fio = s.nextLine();
        
        System.out.println("Enter pay sum:");
        if ( ! s.hasNextLine()) return null;
        bill.pay_sum = Integer.parseInt(s.nextLine());
        
        System.out.println("Enter percent:");
        if ( ! s.hasNextLine()) return null;
        bill.percent = Double.parseDouble(s.nextLine());
        
        System.out.println("Enter prosrochka:");
        if ( ! s.hasNextLine()) return null;
        bill.prosrochka = Integer.parseInt(s.nextLine());
        
        System.out.println("OK. Enter bill date (DD.MM.YYYY):");
        
        return bill;
    }
	
    public Bill() {}

    public String toString() {
        return new String (
        		new SimpleDateFormat("EEE, d MMM yyyy").format(bill_date) + " | " +
            address + " | " +
            house_number + " | " +
            flat_number + " | " +
            fio + " | " +
            pay_sum + " | " +
            percent + " | " +
            prosrochka
        );
    }
}
