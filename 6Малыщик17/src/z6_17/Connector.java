package z6_17;
import java.io.*;

public class Connector {
    private String filename;

    public Connector(String filename1 ) { this.filename = filename1; }

    public void write( Traction routes) throws IOException {
        FileOutputStream fos = new FileOutputStream (filename);
        try ( ObjectOutputStream oos = new ObjectOutputStream( fos )) {
            oos.writeObject(routes);
            oos.flush();
        }
    }
    public Traction read() throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(filename);
        try ( ObjectInputStream oin = new ObjectInputStream(fis)) {
            Traction traction = (Traction) oin.readObject();
            return  traction;
        }
    }
}
