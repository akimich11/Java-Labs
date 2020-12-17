package z11_7;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.util.*;
import java.io.*;
public class MainFrame extends JFrame {
	private static final long serialVersionUID = 1L;

	private static final Vector<Object> components = new Vector<>();

	private static final JTextArea text = new JTextArea(25,50);

	private String key = "hn";

	public static void setDefaultFont (Font font) {
		for (Object key : components) {
			if (key instanceof JButton)
				((JButton) key).setFont(font);
			else if (key instanceof JMenuItem)
				((JMenuItem) key).setFont(font);
			else if (key instanceof JPanel)
				((JPanel) key).setFont(font);
			else if (key instanceof JTextArea)
				((JTextArea) key).setFont(font);
			else if (key instanceof JRadioButton)
				((JRadioButton) key).setFont(font);
		}
		for (Object key : Collections.list(UIManager.getDefaults().keys())) {
			Object value = UIManager.get(key);
			if (value instanceof javax.swing.plaf.FontUIResource) {
				UIManager.put(key, font);
			}
		}
		getFrames()[0].repaint();
	}

	void create_menu() {
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu menu_file = new JMenu("File");
		JMenuItem append_from_file = new JMenuItem("Append data from file");
		append_from_file.addActionListener(event -> {
			JFileChooser chooser = new JFileChooser();
			chooser.setCurrentDirectory(new File("."));
			chooser.setSelectedFile(new File("input.txt"));
			int result=chooser.showOpenDialog(MainFrame.this);

			if(result == JFileChooser.APPROVE_OPTION) {
				String filename = chooser.getSelectedFile().getPath();
				try {
					if(appendFile(new String[]{"-a", filename}))
						JOptionPane.showMessageDialog(MainFrame.this, "Done");
				} catch (IOException e) {
					JOptionPane.showMessageDialog(MainFrame.this, e.getMessage(),
							"Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		JMenuItem exitItem = new JMenuItem("Quit");

		exitItem.addActionListener(e -> System.exit(0));

		JMenu menu2 = new JMenu("View");
		JMenu font_menu = new JMenu("Font");
		String[] fonts = new String[] {"Arial", "Times New Roman", "Calibri", "Serif"};
		for(String font : fonts) {
			JMenuItem fontItem = new JMenuItem(font);
			fontItem.addActionListener(e -> setDefaultFont(new Font(font, FontUIResource.PLAIN, 14)));
			font_menu.add(fontItem);
			components.add(fontItem);
		}

		JMenu look_and_feel = new JMenu("Look and Feel");
		JMenuItem windows = new JMenuItem("Windows");
		JMenuItem java = new JMenuItem("Java");
		windows.addActionListener(event -> {
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				getFrames()[0].repaint();
			}
			catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
				JOptionPane.showMessageDialog(MainFrame.this, e.getMessage(),
						"Error", JOptionPane.ERROR_MESSAGE);
			}
		});
		java.addActionListener(event -> {
			try {
				UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
				getFrames()[0].repaint();
			}
			catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
				JOptionPane.showMessageDialog(MainFrame.this, e.getMessage(),
						"Error", JOptionPane.ERROR_MESSAGE);
			}
		});
		look_and_feel.add(windows);
		look_and_feel.add(java);

		JMenu menu3 = new JMenu("Help");
		JMenuItem about = new JMenuItem("About");

		about.addActionListener(event -> JOptionPane.showMessageDialog(MainFrame.this,
				"Лабораторная работа 11.\n" +
						"Создать графическое приложение Java, реализующее GUI для  задания №8\n\n" +
						"Малыщик Аким, 2020, ФПМИ БГУ, 2 курс 11 группа"));

		menu_file.add(append_from_file);
		menu_file.add(exitItem);
		menu2.add(font_menu);
		menu2.add(look_and_feel);
		menu3.add(about);

		menuBar.add(menu_file);
		menuBar.add(menu2);
		menuBar.add(menu3);

		components.add(about);
		components.add(windows);
		components.add(java);
		components.add(exitItem);
		components.add(menu_file);
		components.add(menu2);
		components.add(menu3);
		components.add(look_and_feel);
		components.add(font_menu);
		components.add(append_from_file);
	}

	void create_buttons(Container container, GridBagConstraints constraints) {
		String[] buttons = new String[]{"Append data", "Clear data", "Delete by key", "Print",
				"Print sorted", "Reverse sort", "Find by key", "Find > key", "Find < key" };
		int i = 0;
		for(String button: buttons) {
			JButton buttonItem = new JButton(button);
			constraints.gridy = i;
			container.add(buttonItem, constraints);
			components.add(buttonItem);
			i++;
		}
		((JButton) components.get(components.size() - i)).addActionListener(event -> {
			AppendBillFrame dialog = new AppendBillFrame(MainFrame.this);
			dialog.setVisible(true);
			if(dialog.flag) {
				if (!appendFile(dialog))
					JOptionPane.showMessageDialog(MainFrame.this, "Appending data failed", "Error", JOptionPane.ERROR_MESSAGE);
				else
					JOptionPane.showMessageDialog(MainFrame.this, "Done");
			}
		});
		((JButton) components.get(components.size() - i + 1)).addActionListener(event -> {
			if(deleteFile())
				JOptionPane.showMessageDialog(MainFrame.this, "Done");
			else
				JOptionPane.showMessageDialog(MainFrame.this, "Nothing to clear", "Warning",
						JOptionPane.WARNING_MESSAGE);
		});
		((JButton) components.get(components.size() - i + 2)).addActionListener(event -> {
			KeyEnterFrame dialog = new KeyEnterFrame(MainFrame.this);
			dialog.setVisible(true);
			if(dialog.flag) {
				try {
					if (!deleteFile(new String[]{"-dk", key, dialog.getData()}))
						JOptionPane.showMessageDialog(MainFrame.this, "Key not found: " +
								dialog.getData(), "Error", JOptionPane.ERROR_MESSAGE);

				} catch (ClassNotFoundException | IOException | KeyNotUniqueException e) {
					JOptionPane.showMessageDialog(MainFrame.this, e.getMessage(),
							"Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		((JButton) components.get(components.size() - i + 3)).addActionListener(event -> {
			text.setText("");
			try {
				printFile();
			} catch (IOException | ClassNotFoundException e) {
				JOptionPane.showMessageDialog(MainFrame.this, e.getMessage(),
						"Error", JOptionPane.ERROR_MESSAGE);
			}
		});
		((JButton) components.get(components.size() - i + 4)).addActionListener(event -> {
			text.setText("");
			try {
				if (printFile(new String[]{"-ps", key}, false)) {
					System.exit(1);
				}
			} catch (IOException | ClassNotFoundException e) {
				JOptionPane.showMessageDialog(MainFrame.this, e.getMessage(),
						"Error", JOptionPane.ERROR_MESSAGE);
			}
		});
		((JButton) components.get(components.size() - i + 5)).addActionListener(event -> {
			text.setText("");
			try {
				if (printFile(new String[]{"-psr", key}, true)) {
					System.exit(1);
				}
			} catch (IOException | ClassNotFoundException e) {
				JOptionPane.showMessageDialog(MainFrame.this, e.getMessage(),
						"Error", JOptionPane.ERROR_MESSAGE);
			}
		});
		((JButton) components.get(components.size() - i + 6)).addActionListener(event -> {
			KeyEnterFrame dialog = new KeyEnterFrame(MainFrame.this);
			dialog.setVisible(true);
			if(dialog.flag) {
				try {
					text.setText("");
					if (!findByKey(new String[]{"-f", key, dialog.getData()})) {
						JOptionPane.showMessageDialog(MainFrame.this, "Key not found: " +
								dialog.getData(), "Error", JOptionPane.ERROR_MESSAGE);
					}
				} catch (ClassNotFoundException | IOException e) {
					JOptionPane.showMessageDialog(MainFrame.this, e.getMessage(),
							"Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		((JButton) components.get(components.size() - i + 7)).addActionListener(event -> {
			KeyEnterFrame dialog = new KeyEnterFrame(MainFrame.this);
			dialog.setVisible(true);
			if(dialog.flag) {
				try {
					text.setText("");
					if (findByKey(new String[]{"-f", key, dialog.getData()}, new KeyCompReverse())) {
						JOptionPane.showMessageDialog(MainFrame.this, "Nothing was found",
								"Error", JOptionPane.ERROR_MESSAGE);
					}
				} catch (ClassNotFoundException | IOException e) {
					JOptionPane.showMessageDialog(MainFrame.this, e.getMessage(),
							"Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		((JButton) components.get(components.size() - i + 8)).addActionListener(event -> {
			KeyEnterFrame dialog = new KeyEnterFrame(MainFrame.this);
			dialog.setVisible(true);
			if(dialog.flag) {
				try {
					text.setText("");
					if (findByKey(new String[]{"-f", key, dialog.getData()}, new KeyComp())) {
						JOptionPane.showMessageDialog(MainFrame.this, "Nothing was found",
								"Error", JOptionPane.ERROR_MESSAGE);
					}
				} catch (ClassNotFoundException | IOException e) {
					JOptionPane.showMessageDialog(MainFrame.this, e.getMessage(),
							"Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}
       
	public MainFrame() {
		super();
		final int WIDTH = 800;
		final int HEIGHT = 600;
		setSize(WIDTH, HEIGHT);
		Toolkit kit=Toolkit.getDefaultToolkit();
		setLocation(kit.getScreenSize().width/2 - WIDTH/2,
				kit.getScreenSize().height/2 - HEIGHT/2);
		setTitle("Лабораторная работа 11");
		setResizable(false);

		Container container = getContentPane();
		container.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		container.setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();

		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.insets = new Insets(0,5,5,5);
		constraints.weightx = 0.5;

		text.setText("You can append data from input file using \"File -> Append data from file\"");
		text.setEditable(false);
		components.add(text);

		create_buttons(container, constraints);

		JPanel panelCheck = new JPanel(new GridLayout(0, 1, 20, 5));
		panelCheck.setBorder(BorderFactory.createTitledBorder("Key"));
		final String[] names = { "House number", "Flat number", "Name", "Date" };
		final String[] keys = { "hn", "fn", "name", "date"};
		ButtonGroup bg = new ButtonGroup();
		for (int i = 0; i < names.length; i++) {
			String s = names[i];
			JRadioButton check = new JRadioButton(s);
			int finalI = i;
			check.addActionListener(e -> key = keys[finalI]);
			if (i == 0)
				check.setSelected(true);
			panelCheck.add(check);
			components.add(check);
			bg.add(check);
		}
		constraints.gridy = 9;
		container.add(panelCheck, constraints);
		components.add(panelCheck);

		JScrollPane scroll = new JScrollPane(text);
		constraints.gridx = 2;
		constraints.gridy = 0;
		constraints.gridheight = 10;

		container.add(scroll, constraints);

		create_menu();
	}


	static final String filename    = "Bills.dat";
	static final String filename_backup = "Bills.~dat";
	static final String index_name = "Bills.idx";
	static final String index_name_backup = "Bills.~idx";

	// input file encoding:
	private static String encoding = "Cp866";
	private static PrintStream billOut = System.out;

	static Bill readBill(Scanner fin ) throws IOException {
		return Bill.nextRead( fin, billOut )
				? Bill.read( fin, billOut ) : null;
	}

	private static boolean deleteBackup() {
		return new File(filename_backup).delete() && new File(index_name_backup).delete();
	}

	static boolean deleteFile() {
		return new File(filename).delete() && new File(index_name).delete();
	}

	private static boolean backup() {
		deleteBackup();
		return new File(filename).renameTo(new File(filename_backup)) &&
				new File(index_name).renameTo(new File(index_name_backup));
	}

	boolean deleteFile( String[] args )
			throws ClassNotFoundException, IOException, KeyNotUniqueException {
		long[] poss;
		try ( Index idx = Index.load(index_name)) {
			IndexBase pidx = indexByArg( args[1], idx );
			if ( pidx == null ) {
				return false;
			}
			if (!pidx.contains(args[2]))
				return false;
			poss = pidx.get(args[2]);
		}
		backup();
		Arrays.sort( poss );
		try (Index idx = Index.load(index_name);
			 RandomAccessFile fileBak= new RandomAccessFile(filename_backup, "rw");
			 RandomAccessFile file = new RandomAccessFile( filename, "rw")) {
			boolean[] wasZipped = new boolean[] {false};
			long pos;
			while (( pos = fileBak.getFilePointer()) < fileBak.length() ) {
				Bill bill = (Bill)
						Buffer.readObject( fileBak, pos, wasZipped );
				if ( Arrays.binarySearch(poss, pos) < 0 ) { // if not found in deleted
					long ptr = Buffer.writeObject( file, bill, wasZipped[0] );
					idx.put( bill, ptr );
				}
			}
		}
		return true;
	}

	boolean appendFile( String[] args )
			throws IOException {
		if ( args.length >= 2 ) {
			FileInputStream stdin = new FileInputStream( args[1] );
			System.setIn( stdin );
			if (args.length == 3) {
				encoding = args[2];
			}
			// hide output:
			billOut = new PrintStream("nul");
		}
		return appendFile();
	}

	boolean appendFile() {
		Scanner fin = new Scanner( System.in, encoding );
		try (Index idx = Index.load(index_name);
			 RandomAccessFile raf = new RandomAccessFile( filename, "rw" )) {
			for(;;) {
				Bill bill = readBill( fin );
				if ( bill == null )
					break;
				idx.test( bill );
				long pos = Buffer.writeObject( raf, bill, false);
				idx.put( bill, pos );
			}
		} catch (IOException | ClassNotFoundException |
				KeyNotUniqueException e) {
			JOptionPane.showMessageDialog(MainFrame.this, e.getMessage(),
					"Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}

	public Bill read(AppendBillFrame dialog) {
		Bill bill = new Bill();
		if(dialog.getHouseNumber().equals("")) return null;
		bill.houseNumber = dialog.getHouseNumber();

		if(dialog.getFlatNumber().equals("")) return null;
		bill.flatNumber = dialog.getFlatNumber();
		if(dialog.getNameText().equals("")) return null;
		bill.name = dialog.getNameText();
		if(dialog.getAddress().equals("")) return null;
		bill.address = dialog.getAddress();
		if(dialog.getDate().equals("")) return null;
		bill.date = dialog.getDate();

		if (!Bill.validDate(bill.date)) {
			JOptionPane.showMessageDialog(MainFrame.this, "Date format is invalid",
					"Error", JOptionPane.ERROR_MESSAGE);
			return null;
		}

		if(dialog.getBillSum().equals("")) return null;
		bill.billSum = dialog.getBillSum();
		if(dialog.getPercent().equals("")) return null;
		bill.percent = dialog.getPercent();
		if(dialog.getDeadline().equals("")) return null;
		bill.deadline = dialog.getDeadline();
		return bill;
	}

	boolean appendFile( AppendBillFrame dialog ) {
		try (Index idx = Index.load(index_name);
			 RandomAccessFile raf = new RandomAccessFile( filename, "rw" )) {
			Bill bill = read(dialog);
			if ( bill == null )
				return false;
			idx.test(bill);
			long pos = Buffer.writeObject(raf, bill, false);
			idx.put(bill, pos);
		} catch (IOException | ClassNotFoundException | KeyNotUniqueException e) {
			JOptionPane.showMessageDialog(MainFrame.this, e.getMessage(),
					"Error", JOptionPane.ERROR_MESSAGE);
		}
		return true;
	}

	private static void printRecord( RandomAccessFile raf, long pos )
			throws ClassNotFoundException, IOException {
		boolean[] wasZipped = new boolean[] {false};
		Bill bill = (Bill) Buffer.readObject( raf, pos, wasZipped );
		if (wasZipped[0]) {
			text.append( " compressed\n" );
		}
		text.append( " record at position "+ pos + ": \n" + bill );
	}

	private static void printRecord( RandomAccessFile raf, String key,
									 IndexBase pidx ) throws ClassNotFoundException, IOException {
		long[] poss = pidx.get( key );
		for ( long pos : poss ) {
			text.append( "\n*** Key: \"" +  key + "\" points to" );
			printRecord( raf, pos );
		}
	}

	static void printFile()
			throws IOException, ClassNotFoundException {
		long pos;
		int rec = 0;
		try ( RandomAccessFile raf = new RandomAccessFile( filename, "rw" )) {
			while (( pos = raf.getFilePointer()) < raf.length() ) {
				text.append( "\n#" + (++rec ) + "");
				printRecord( raf, pos );
			}
		}
	}

	private IndexBase indexByArg( String arg, Index idx ) {
		IndexBase pidx = null;
		switch (arg) {
			case "date":
				pidx = idx.dates;
				break;
			case "name":
				pidx = idx.names;
				break;
			case "hn":
				pidx = idx.houseNumbers;
				break;
			case "fn":
				pidx = idx.flatNumbers;
				break;
			default:
				JOptionPane.showMessageDialog(MainFrame.this, "Invalid index specified: " + arg,
						"Error", JOptionPane.ERROR_MESSAGE);
				System.err.println();
				break;
		}
		return pidx;
	}

	boolean printFile( String[] args, boolean reverse )
			throws ClassNotFoundException, IOException {
		try (Index idx = Index.load(index_name);
			 RandomAccessFile raf = new RandomAccessFile( filename, "rw" )) {
			IndexBase pidx = indexByArg( args[1], idx );
			if ( pidx == null ) {
				return true;
			}
			String[] keys =
					pidx.getKeys( reverse ? new KeyCompReverse() : new KeyComp() );
			for ( String key : keys ) {
				printRecord( raf, key, pidx );
			}
		}
		return false;
	}

	boolean findByKey( String[] args )
			throws ClassNotFoundException, IOException {
		try (Index idx = Index.load(index_name);
			 RandomAccessFile raf = new RandomAccessFile( filename, "rw" )) {
			IndexBase pidx = indexByArg( args[1], idx );
			if (!pidx.contains(args[2]))
				return false;
			printRecord( raf, args[2], pidx );
		}
		return true;
	}

	boolean findByKey( String[] args, Comparator<String> comp )
			throws ClassNotFoundException, IOException {
		try (Index idx = Index.load(index_name);
			 RandomAccessFile raf = new RandomAccessFile( filename, "rw" )) {
			IndexBase pidx = indexByArg( args[1], idx );
			if (!pidx.contains(args[2]))
				return true;
			String[] keys = pidx.getKeys( comp );
			boolean k = true;
			for (String key : keys) {
				if (key.equals(args[2]))
					return k;
				printRecord(raf, key, pidx);
				k = false;
			}
		}
		return false;
	}
}
