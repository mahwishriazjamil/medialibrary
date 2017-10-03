package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class BrowseSongsPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6780240707419836591L;
	
	// attributes
	private Box main, mbox1, mbox2, mbox3;
	private JLabel jLabSongsTab;
	private JScrollPane scrollPaneTab;
	private JButton butDelete, butSave, butSaveAndQuit;
	private JTable songsTable;
	private ModifiedTableModel tableModel;

	// column labels for the song table
	String[] songTabColumns = { "Name", "Artist", "Album", "Medium" };

	// two dimensional array containing array setting initial data in the song table
	String[][] songTabData = { { " ", " ", " ", " " } };

	public BrowseSongsPanel() {
		super(new FlowLayout());
		initWidgets();
		addWidgets();
		setBackground(new Color(245, 245, 245));
	}

	// method to initialise widgets
	private void initWidgets() {
		// instantiates a vertical box called main where everything visible to the user will be stored
		main = Box.createVerticalBox();

		// instantiate boxes which take in widgets and store them from left to right
		mbox1 = Box.createHorizontalBox();
		mbox2 = Box.createHorizontalBox();
		mbox3 = Box.createHorizontalBox();

		// instantiate jLabel
		jLabSongsTab = new JLabel("Songs in current library:");

		// instantiate buttons
		butDelete = new JButton("Delete song");
		butSave = new JButton("Save");
		butSaveAndQuit = new JButton("Save and Quit");

		// instantiate model
		tableModel = new ModifiedTableModel(songTabData, songTabColumns);


		// instantiate songs table using ModifiedTableModel (under 'tableModel')
		songsTable = new JTable(tableModel);

		songsTable.setPreferredScrollableViewportSize(new Dimension(775, 350));
		// fills up all the table space it is given
		songsTable.setFillsViewportHeight(true);
		// sorts data in columns (ascending/descending)
		songsTable.setAutoCreateRowSorter(true);
		// disable moving columns around
		songsTable.getTableHeader().setReorderingAllowed(false);
		// instantiate scroll pane in songs table
		scrollPaneTab = new JScrollPane(songsTable);
		// horizontal scroll bar only shows in the songs table when needed
		scrollPaneTab.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		// vertical scroll bar only shows in the songs table when needed
		scrollPaneTab.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		
	}

	// method to add widgets
	private void addWidgets() {
		// adding labels/buttons/scroll pane to boxes first instantiated (mboxes)
		mbox1.add(Box.createHorizontalStrut(00));
		mbox1.add(jLabSongsTab);
		mbox1.add(Box.createHorizontalStrut(400));
		mbox1.add(Box.createHorizontalStrut(175));
		mbox1.add(butDelete);
		mbox2.add(scrollPaneTab);
		mbox3.add(Box.createHorizontalStrut(595));
		mbox3.add(butSave);
		mbox3.add(Box.createHorizontalStrut(5));
		mbox3.add(butSaveAndQuit);

		// adds all boxes (mboxes) to main box
		main.add(mbox1);
		main.add(Box.createVerticalStrut(5));
		main.add(mbox2);
		main.add(mbox3);
		
		// adds main
		add(main);

	}

	public void addActionListener(ActionListener a) {
		// adds listener to all buttons
		butDelete.addActionListener(a);
		butSave.addActionListener(a);
		butSaveAndQuit.addActionListener(a);

	}

	// accessors for all private attributes
	public JButton getButDelete() {
		return butDelete;
	}

	public JButton getButSave() {
		return butSave;
	}

	public JButton getButSaveAndQuit() {
		return butSaveAndQuit;
	}

	public JTable getSongsTable() {
		return songsTable;
	}

}
