package z9_8;

import java.io.Serializable;

public class NewsMessage implements Serializable {
	private static final long serialVersionUID = 1L;
	public String news_msg;
    public int day_of_month;

    public NewsMessage(String nm, int day) {
        news_msg = nm;
        day_of_month = day;
    }
}
