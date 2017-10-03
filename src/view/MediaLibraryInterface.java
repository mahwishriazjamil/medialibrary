package view;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

public class MediaLibraryInterface extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7089307856474220368L;

	// attributes
	private AddSongPanel addSP;
	private BrowseSongsPanel addBSP;
	private JTabbedPane jTabPane;
	private String spaceFill;
	
	// constructor
	public MediaLibraryInterface(String title) {
		super(title);
		
		jTabPane = new JTabbedPane();
		addSP = new AddSongPanel();
		addBSP = new BrowseSongsPanel();
		spaceFill = "       ";
		
		// add a tab with the label 'add song' 
		jTabPane.addTab(spaceFill + spaceFill + spaceFill + spaceFill + "Add Song" + spaceFill + spaceFill + spaceFill + spaceFill, addSP);
		jTabPane.addTab(spaceFill + spaceFill + spaceFill + spaceFill + "Browse Songs" + spaceFill + spaceFill + spaceFill + spaceFill, addBSP);
		add(jTabPane);
		// close the application when the 'x' is clicked
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		/** makes everything within the tab fit automatically - no need
		to set size
		**/
		pack();

		// ensures the tab size is fixed and not resizable by user
		setResizable(false);
	}

	// accessors for all attributes
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public AddSongPanel getAddSP() {
		return addSP;
	}

	public BrowseSongsPanel getAddBSP() {
		return addBSP;
	}

	public JTabbedPane getjTabPane() {
		return jTabPane;
	}

	public String getSpaceFill() {
		return spaceFill;
	}

}
