package z9_8;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Calendar;
import java.util.Scanner;
import java.util.Vector;

public class ServerThread extends Thread {
    static Vector<NewsMessage> news;

    public ServerThread(String filename) {
        news = new Vector<>();
        String[] line;
        File f = new File(filename);
        try (Scanner file_sc = new Scanner(f)) {
            while (file_sc.hasNextLine()) {
                line = file_sc.nextLine().split(":");
                if (line.length > 0)
                    news.add(new NewsMessage(line[1], Integer.parseInt(line[0])));
            }
        } catch (FileNotFoundException e) {
            System.err.print("Файл с базой новостей не найден");
        }
        start();
    }

    public static void addNews(NewsMessage nm) {
        news.add(nm);
    }

    public static Vector<NewsMessage> getNews(int day) {
        Vector<NewsMessage> nws = new Vector<>();
        for (NewsMessage nm: news) {
            if(nm.day_of_month == day)
                nws.add(nm);
        }
        return nws;
    }

    @Override
    public void run() {
        super.run();
        try (Scanner in = new Scanner(System.in)) {
            while (true) {
                System.out.print("add/quit > ");
                String s = in.nextLine();
                if(s.equals("add")) {
                    System.out.print("Enter news: ");
                    s = in.nextLine();
                    NewsMessage msg = new NewsMessage(s, Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
                    addNews(msg);
                    Vector<NewsMessage> current_news = new Vector<>();
                    current_news.add(msg);
                    System.out.println("Added.");
                    for (Connection cs: ServerMain.connections) {
                        cs.oos.writeObject(current_news);
                        cs.oos.flush();
                    }
                }
                else if(s.equals("quit")) {
                    System.out.print("Stopping... ");
                    ServerMain.flag = false;
                    break;
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
