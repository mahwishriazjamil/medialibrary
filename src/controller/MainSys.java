package controller;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import model.Library;
import model.Song;
import view.AddSongPanel;
import view.BrowseSongsPanel;
import view.MediaLibraryInterface;
import view.ModifiedTableModel;
import view.SplashScreen;

public class MainSys implements ActionListener {
// second commit test
	// attributes
	private MediaLibraryInterface mainScreen;
	private AddSongPanel addSP;
	private BrowseSongsPanel addBSP;
	private File saveFile;
	private File libFile;
	private Boolean exit;
	private Library lib;
	private int confirm;
	private String entryName;
	private JFileChooser fileChooser;
	private FileFilter filter;
	private String[][] songTabData;
	private FileInputStream fis;
	private ObjectInputStream ois;
	private SplashScreen splashPanel;

	public MainSys() {
		
		initEventAttributes();
		mainScreen = new MediaLibraryInterface("Media Library");
		addSP = mainScreen.getAddSP();
		addBSP = mainScreen.getAddBSP();

		// adds listeners to all components in the song and browse song panel classes
		addSP.addActionListener(this);
		addBSP.addActionListener(this);

		// splash screen created
		splashPanel = new SplashScreen("Media Library");
		splashPanel.addActionListener(this);
		// splash screen set to show before mainscreen
		splashPanel.setVisible(true);

	}

	private void initEventAttributes() {
		fileChooser = new JFileChooser();
		filter = new FileNameExtensionFilter("Media library files (.ser)", "ser");
		fileChooser.addChoosableFileFilter(filter);
		lib = new Library();

		exit = false;

		saveFile = null;
		libFile = null;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		/**
		 * detects which button was clicked for the correct operation to be
		 * carried out
		 **/

		if (e.getSource() == addSP.getButAddSong()) {
			addSongToLibrary();
			refreshSongTabData();
		}

		else if (e.getSource() == addSP.getButSave()) {
			saveSong();
		}

		else if (e.getSource() == addSP.getButSaveAndQuit()) {
			saveLibraryAndQuit();
		}

		 else if (e.getSource() == addBSP.getButDelete()) {
			deleteSongFromLibrary();
		 }

		else if (e.getSource() == addBSP.getButSave()) {
			saveSong();
		}

		else if (e.getSource() == addBSP.getButSaveAndQuit()) {
			saveLibraryAndQuit();
		}

		else if (e.getSource() == splashPanel.getButtonLoadPrevLib()) {
			refreshSongTabData();
			loadPrevLib();
			fileChooser.setFileFilter(filter);
		}

		else if (e.getSource() == splashPanel.getButtonNewLib()) {
			// gets rid of the splash screen
			splashPanel.dispose();
			// shows user main screen
			mainScreen.setVisible(true);
		}

		else if (e.getSource() == splashPanel.getButtonExitSys()) {
			System.exit(0);
		}

	}

	private void deleteSongFromLibrary() {
		// uses the 'getSelectedRow' method to identify current row
		int selectedRow = addBSP.getSongsTable().getSelectedRow();
		// removes the current row (song entry)
		if(selectedRow != -1){
			((ModifiedTableModel) addBSP.getSongsTable().getModel()).removeRow(selectedRow);
		}
		
		}
	

	private void loadPrevLib() {
		/**
		 * creates a file filter allowing only files of type .ser to be opened
		 **/
		fileChooser.setFileFilter(filter);
		confirm = fileChooser.showOpenDialog(mainScreen);
		if (confirm == JFileChooser.APPROVE_OPTION) {
			libFile = fileChooser.getSelectedFile();
			try {
				fis = new FileInputStream(libFile);
				ois = new ObjectInputStream(fis);
				lib = (Library) ois.readObject();
				ois.close();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

			splashPanel.dispose();
			// load data method
			refreshSongTabData();

			mainScreen.setVisible(true);
		}

	}

	private void refreshSongTabData() {
		// removes table rows until there are no more rows left to remove
		while (((ModifiedTableModel) addBSP.getSongsTable().getModel()).getRowCount() > 0) {
			((ModifiedTableModel) addBSP.getSongsTable().getModel()).removeRow(0);
		}

		// load data in previously saved library
		songTabData = lib.vectorFromToString();
		// loops through array and fills in data
		for (int i = 0; i < songTabData.length; i++) {
			/**
			 * adds rows and passes in data using the the double array declared
			 * in vectorFromToString method
			 */
			((ModifiedTableModel) addBSP.getSongsTable().getModel()).addRow(songTabData[i]);
		}

	}

	private void saveLibraryAndQuit() {
		saveSong();
		// system only saves and quits if exit boolean has been set to true by
		// other methods
		if (exit) {
			System.exit(0);
		}
	}

	private void saveSong() {
		// get name of file user wants to save their library in
		entryName = JOptionPane.showInputDialog(mainScreen, "Enter name of file...", "Save",
				JOptionPane.INFORMATION_MESSAGE);

		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		try {
			saveFile = new File(entryName.trim() + ".ser");
			/**
			 * uses saveFile variable to take in an entry name then checks to
			 * see if the file already exists.
			 **/
			if (!saveFile.exists()) {
				fos = new FileOutputStream(entryName + ".ser");
				oos = new ObjectOutputStream(fos);
				oos.writeObject(lib);
				fos.close();
				oos.close();
				exit = true;
			}
			/**
			 * if the file exists, the user is asked whether they wish to
			 * overwrite the previous version with the current.
			 **/
			else {
				int confirm = JOptionPane.showConfirmDialog(mainScreen,
						"A file under this name already exits. \nDo you wish to overwrite it?", "File already exists",
						JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
				if (confirm == JOptionPane.YES_OPTION) {
					fos = new FileOutputStream(entryName + ".ser");
					oos = new ObjectOutputStream(fos);
					oos.writeObject(lib);
					fos.close();
					oos.close();
					exit = true;
				} else {
					/** system remains open if the user clicks 'no' in overwrite
					  command */
					System.out.println("Cancelled");
					exit = false;
				}

			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void addSongToLibrary() {
		boolean textFieldsFilled = false;
		Song song;

		/**
		 * if all are not empty -- trim method makes sure there are not blanks
		 **/
		if (!addSP.getjTFName().getText().trim().contentEquals("")
				&& !addSP.getjTFArtist().getText().trim().contentEquals("")
				&& !addSP.getjTFAlbum().getText().trim().contentEquals("")
				&& !addSP.getjTFMedium().getText().trim().contentEquals("")) {

			textFieldsFilled = true;
		}

		/**
		 * adds song details from song fields if and only if all the text fields have
		 * data in them
		 */
		if (textFieldsFilled == true) {
			song = new Song(addSP.getjTFName().getText().trim(), addSP.getjTFArtist().getText().trim(),
					addSP.getjTFAlbum().getText().trim(), addSP.getjTFMedium().getText().trim());

			// adds the song under 'song' variable to library
			lib.addSong(song);
			// displays which song has most recently been added to the library
			addSP.getjtaSongsLog().append("\n>> " + song.getName() + " has been added to the library!");

		}

		// if any fields are empty - a dialog is presented to the user prompting them to fill all fields in
		else {
			JOptionPane.showMessageDialog(mainScreen, "Please ensure all fields are filled.");
		}

		// set text fields to empty once the song has been added
		addSP.getjTFName().setText("");
		addSP.getjTFArtist().setText("");
		addSP.getjTFAlbum().setText("");
		addSP.getjTFMedium().setText("");
	}

}
