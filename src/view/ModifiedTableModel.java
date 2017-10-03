package view;

import javax.swing.table.DefaultTableModel;

public class ModifiedTableModel extends DefaultTableModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4869321333024726705L;

	// extends the default table model

	public ModifiedTableModel (String [][] songTabData, String[] songTabColumns){
		super (songTabData, songTabColumns);
	}

	/** overrides isCellEditable method in DefaultTableModel to ensure 
	users are not able to edit entries from the browse songs panel
	**/
	@Override
	public boolean isCellEditable(int row, int column) {
		return false;
	}
	
	
}
