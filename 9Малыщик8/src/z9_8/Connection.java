package z9_8;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Connection extends Thread {

    public Socket socket;
    public ObjectInputStream ois;
    public ObjectOutputStream oos;

    public Connection(Socket s) {
        socket = s;
        try {
            ois = new ObjectInputStream(s.getInputStream());
            oos = new ObjectOutputStream(s.getOutputStream());
            start();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        super.run();
        try {
            while (!socket.isClosed() && ServerMain.flag) {
                oos.writeObject(ServerThread.getNews(ois.readInt()));
                oos.flush();
            }
        }
        catch (Exception e) {
            ServerMain.connections.remove(this);
            System.err.println("User was disconnected");
        }
    }
}
