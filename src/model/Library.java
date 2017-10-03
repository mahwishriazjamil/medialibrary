package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Library implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7061113723906700760L;

	// attributes
	private List<Song> records;

	// constructor
	public Library() {
		records = new ArrayList<Song>();
	}

	// method allows for songs to be added to records arrayList
	public void addSong(Song newSong) {
		records.add(newSong);
	}

	// toString method to display song entries
	@Override
	public String toString() {

		String total = "\n";

		/** uses an Iterator pattern to loop through the 'records' arrayList and
		 	display all song entries
		*/
		Iterator<Song> i = records.iterator();
		while (i.hasNext()) {
			Song song = (Song) i.next();
			total = total + song.toString();

		}
		return total;

	}
	
	public String[][] vectorFromToString(){

		String[][] result = new String[records.size()][5];
		
		/** for each loop loops through the 'records'
			list and retrieve the songs and their details 
			and assign it to the 'result' two-dim array
			*/
		for (int i = 0; i < records.size(); i++){
			result[i][0] = records.get(i).getName();
			result[i][1] = records.get(i).getArtist();
			result[i][2] = records.get(i).getAlbum();
			result[i][3] = records.get(i).getMedium();
		}
		// prints 'result' array
		return result;
	}

}