package z11_7;

import javax.swing.*;
import java.awt.*;

public class KeyEnterFrame extends JDialog
{
	private final JTextField name;
	private String data;
	public boolean flag;
	private static final long serialVersionUID = 1L;
	
	public KeyEnterFrame(JFrame owner)
	{
		super(owner,"Key",true);
		setSize(350, 150);
		Toolkit kit=Toolkit.getDefaultToolkit();
		setLocation(kit.getScreenSize().width / 2 - 400,
				kit.getScreenSize().height/2 - 275);
		Container contentPane=getContentPane();
		setResizable(false);
		setLayout(new FlowLayout(FlowLayout.CENTER,100,5));
		contentPane.add(new JLabel("Enter key value"));
		name = new JTextField("",25);
		contentPane.add(name);

		flag=false;
		JButton ok = new JButton("Ok");
		contentPane.add(ok);
		ok.addActionListener(event -> {
		   this.data = name.getText();
		   flag=true;
		   setVisible(false);
		});
		JButton cancel=new JButton("Cancel");
		contentPane.add(cancel);
		cancel.addActionListener(event -> setVisible(false));
	}
	public String getData(){ return this.data; }
}

