package z9_8;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class ServerMain {

    static Vector<Connection> connections = new Vector<>();
    static ServerSocket ss;
    static boolean flag = true;

    public static void main(String[] args) {
        try {
            System.err.println("Initialized.");
            System.out.println("add - ���������� �������, quit - �����.");

            //�������� �� ���� ���� � ����� news.txt
            new ServerThread("D:\\Workspace\\Eclipse\\9��������������8\\src\\news.txt");

            ss = new ServerSocket(80, 100);
            while( flag ) {
                Socket s = ss.accept();
                connections.add(new Connection(s));
                System.err.println("\nUser was connected");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            System.out.println("Done.");
        }


    }
}
