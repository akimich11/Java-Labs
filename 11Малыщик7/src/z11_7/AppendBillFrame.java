package z11_7;

import javax.swing.*;
import java.awt.*;

public class AppendBillFrame extends JDialog
{
    private final JTextField houseNumber;
    private final JTextField flatNumber;
    private final JTextField name;
    private final JTextField address;
    private final JTextField date;
    private final JTextField billSum;
    private final JTextField percent;
    private final JTextField deadline;

    public boolean flag;
    private static final long serialVersionUID = 1L;

    public AppendBillFrame(JFrame owner)
    {
        super(owner,"Key",true);
        setSize(350, 500);
        Toolkit kit=Toolkit.getDefaultToolkit();
        setLocation(kit.getScreenSize().width / 2 - 400,
                kit.getScreenSize().height/2 - 275);
        Container contentPane=getContentPane();
        setResizable(false);
        setLayout(new FlowLayout(FlowLayout.CENTER,100,5));

        contentPane.add(new JLabel("House number"));
        houseNumber = new JTextField("",25);
        contentPane.add(houseNumber);

        contentPane.add(new JLabel("Flat number"));
        flatNumber = new JTextField("",25);
        contentPane.add(flatNumber);

        contentPane.add(new JLabel("Name"));
        name = new JTextField("",25);
        contentPane.add(name);

        contentPane.add(new JLabel("Address"));
        address = new JTextField("",25);
        contentPane.add(address);

        contentPane.add(new JLabel("Date (YYYY.MM.DD)"));
        date = new JTextField("",25);
        contentPane.add(date);

        contentPane.add(new JLabel("Bill Sum"));
        billSum = new JTextField("",25);
        contentPane.add(billSum);

        contentPane.add(new JLabel("Percent"));
        percent = new JTextField("",25);
        contentPane.add(percent);

        contentPane.add(new JLabel("Deadline (days)"));
        deadline = new JTextField("",25);
        contentPane.add(deadline);

        flag=false;
        JButton ok = new JButton("Ok");
        contentPane.add(ok);
        ok.addActionListener(event -> {
            flag=true;
            setVisible(false);
        });
        JButton cancel=new JButton("Cancel");
        contentPane.add(cancel);
        cancel.addActionListener(event -> setVisible(false));
    }
    public String getHouseNumber(){ return this.houseNumber.getText(); }
    public String getFlatNumber(){ return this.flatNumber.getText(); }
    public String getAddress(){ return this.address.getText(); }
    public String getDate(){ return this.date.getText(); }
    public String getBillSum(){ return this.billSum.getText(); }
    public String getPercent(){ return this.percent.getText(); }
    public String getDeadline(){ return this.deadline.getText(); }
    public String getNameText(){ return this.name.getText(); }

}

