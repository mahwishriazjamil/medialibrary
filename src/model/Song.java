package model;

import java.io.Serializable;

public class Song implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7356281630216904431L;
	
	// attributes
	private String name; 
	private String artist;
	private String album;
	private String medium;
	
	// constructor
	public Song(String name, String artist, String album, String medium) {
		super();
		this.name = name;
		this.artist = artist;
		this.album = album;
		this.medium = medium;
	}

	// toString method to output song details
	@Override
	public String toString() {
		return "\nSong name: " + name 
				+ " \nSong artist: " + artist 
				+ " \nSong album: " + album 
				+ " \nSong medium: " + medium + "\n";
	}

	// accessors for all private fields
	public String getName() {
		return name;
	}

	public String getArtist() {
		return artist;
	}

	public String getAlbum() {
		return album;
	}

	public String getMedium() {
		return medium;
	}


	
}
