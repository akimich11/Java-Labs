package z9_8;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.Vector;

public class ClientMain {


    public static void streaming(Scanner in, ObjectInputStream ois) {
        Vector<NewsMessage> vn;
        while (true) {
            try {
                vn = (Vector<NewsMessage>) ois.readObject();
                System.out.println(vn.get(0).news_msg);
                System.out.println("\nContinue?");
                System.out.print("y/n > ");
                if(!in.nextLine().equals("n"))
                    System.out.println("\n“рансл€ци€ продолжаетс€. Ctrl + Z to exit\n");
                else
                    return;
            }
            catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public static void printing(Scanner in, ObjectOutputStream oos, ObjectInputStream ois) {
        try {
            System.out.print("Enter day of month: ");
            int d = in.nextInt();
            while (d < 1 || d > 31) {
                System.out.print("Incorrect day. Enter day from 1 to 31: ");
                d = in.nextInt();
            }
            oos.writeInt(d);
            oos.flush();
            Vector<NewsMessage> news = (Vector<NewsMessage>) ois.readObject();

            if(news.size() != 0) {
                System.out.println("News for " + d + "th day of month: ");
                int k = 0;
                for (NewsMessage nm : news) {
                    k++;
                    System.out.println(k + ". " + nm.news_msg);
                }
            }
            else
                System.out.println("News for " + d + "th day of month not found.");
        }
        catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        try {
            Socket user_socket = new Socket("localhost", 80);
            ObjectOutputStream oos = new ObjectOutputStream(user_socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(user_socket.getInputStream());

            Scanner in = new Scanner(System.in);
            System.err.println("Connected.");
            System.out.println("Ќаблюдать за трансл€цией / распечатать новости за день / выход");

            while (true) {
                String command = "";
                while (!command.equals("stream") && !command.equals("print") && !command.equals("quit")) {
                    System.out.print("\nstream/print/quit > ");
                    command = in.nextLine();
                }

                switch (command) {
                    case "stream":
                        System.out.println("¬ключЄн режим наблюдени€ за трансл€цией. Ctrl + Z to exit");
                        streaming(in, ois);
                        break;
                    case "print":
                        printing(in, oos, ois);
                        break;
                    case "quit":
                        System.out.print("Disconnecting... ");
                        oos.close();
                        ois.close();
                        user_socket.close();
                        return;
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            System.out.println("Done.");
        }
    }
}
