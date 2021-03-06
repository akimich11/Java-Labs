package z11_7;

import java.io.*;
import java.util.*;
import java.util.zip.*;

class KeyComp implements Comparator<String> {
    public int compare(String o1, String o2) {
        // right order:	
        return o1.compareTo(o2);
    }
}

class KeyCompReverse implements Comparator<String> {
    public int compare(String o1, String o2) {
        // reverse order:
        return o2.compareTo(o1);
    }
}

interface IndexBase {
    String[] getKeys( Comparator<String> comp );
    boolean contains( String key );
    long[] get( String key );
}

class IndexOne2One implements Serializable, IndexBase {
    // Unique keys
    // class release version:
    private static final long serialVersionUID = 1L;
    
    private final TreeMap<String,Long> map;
    
    public IndexOne2One() {
        map = new TreeMap<>();
    }
	
    public String[] getKeys( Comparator<String> comp ) {
        String[] result = map.keySet().toArray( new String[0] );
        Arrays.sort( result, comp );
        return result;
    }
	
    public void put( String key, long value ) {
        map.put(key, value);
    }

    public boolean contains( String key ) {
        return map.containsKey(key);
    }
	
    public long[] get( String key ) {
        long pos = map.get(key);
        return new long[] {pos};
    }
}

class IndexOne2N implements Serializable, IndexBase {
    // Not unique keys
    // class release version:
    private static final long serialVersionUID = 1L;

    private final TreeMap<String,long[]> map;
	
    public IndexOne2N() {
        map = new TreeMap<>();
    }
	
    public String[] getKeys( Comparator<String> comp ) {
        String[] result = map.keySet().toArray( new String[0] );
        Arrays.sort( result, comp );
        return result;
    }
	
    public void put( String key, long value ) {
        long[] arr = map.get(key);
        arr = ( arr != null ) ?
            Index.InsertValue( arr, value ) :
            new long[] {value};		
	map.put(key, arr);
    }

    public boolean contains( String key ) {
        return map.containsKey(key);
    }
	
    public long[] get( String key ) {
        return map.get( key );
    }
}

class KeyNotUniqueException extends Exception {
    // class release version:
    private static final long serialVersionUID = 1L;
    
    public KeyNotUniqueException( String key ) {
        super("Key is not unique: " + key);
    }
}

public class Index implements Serializable, Closeable {
    // class release version:
    private static final long serialVersionUID = 1L;

    public static long[] InsertValue( long[] arr, long value ) {
        int length = ( arr == null ) ? 0 : arr.length;
        long [] result = new long[length + 1];
        assert arr != null;
        System.arraycopy(arr, 0, result, 0, length);
        result[length] = value;
        return result;
    }
	
    IndexOne2One          dates;
    IndexOne2N            names;
    IndexOne2N            houseNumbers;
    IndexOne2N            flatNumbers;
	
    public void test( Bill park ) throws KeyNotUniqueException {
        assert( park != null );
        if ( dates.contains(park.date)) {
            throw new KeyNotUniqueException( park.date);
        }	
    }
	
    public void put(Bill park, long value ) throws KeyNotUniqueException {
        test( park );
        houseNumbers.put(park.houseNumber, value);
        flatNumbers.put(park.flatNumber, value);
        names.put( park.name, value);
        dates.put( park.date, value );
    }
	
    public Index()  {
        dates = new IndexOne2One();
        names 	= new IndexOne2N();
        houseNumbers = new IndexOne2N();
        flatNumbers = new IndexOne2N();
    }
	
    public static Index load( String name ) 
            throws IOException, ClassNotFoundException {
        Index obj;
        try {
            FileInputStream file = new FileInputStream( name );
            try( ZipInputStream zis = new ZipInputStream( file )) {
                ZipEntry zen = zis.getNextEntry();
                if (!zen.getName().equals(Buffer.zipEntryName)) {
                    throw new IOException("Invalid block format");
                }
                try ( ObjectInputStream ois = new ObjectInputStream( zis )) {
                    obj = (Index) ois.readObject();
                }
            }
        } catch ( FileNotFoundException e ) {
            obj = new Index();
        }
        if ( obj != null ) {
            obj.save( name );
        }
        return obj;
    }
	
    private transient String filename = null; 
	
    public void save( String name ) {
        filename = name;
    }
	
    public void saveAs( String name ) throws IOException {
        FileOutputStream file = new FileOutputStream( name );
        try ( ZipOutputStream zos = new ZipOutputStream( file )) {
            zos.putNextEntry( new ZipEntry( Buffer.zipEntryName ));
            zos.setLevel( ZipOutputStream.DEFLATED );
            try ( ObjectOutputStream oos = new ObjectOutputStream( zos )) {
                oos.writeObject( this );
                oos.flush();
                zos.closeEntry();
                zos.flush();
            }
        }
    }
	
    public void close() throws IOException {
        saveAs( filename );
    }
}
