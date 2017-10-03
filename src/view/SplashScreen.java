package view;

import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class SplashScreen extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2981468791393221646L;

	// attributes
	private JPanel splashPanel;
	private JButton butNewLib;
	private JButton butLoadPrevLib;
	private JButton butExitSys;

	// constructor
	public SplashScreen(String message) {
		super(message);

		// calls method to initialise widgets
		initWidgets();
		// calls method to add widgets
		addWidgets();
		// closes application when 'x' is clicked
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setSize(500, 100);
		setResizable(false);
	}

	// method to initialise widgets
	private void initWidgets() {
		/**
		 * creates a panel which adheres to the FlowLayout and arranges the
		 * buttons specified in a linear format
		 */
		splashPanel = new JPanel(new GridBagLayout());

		// instantiates buttons to be added to splash panel
		butNewLib = new JButton("New library");
		butLoadPrevLib = new JButton("Load previously saved library");
		butExitSys = new JButton("Exit media library");
	}

	private void addWidgets() {
		// adds buttons to splash panel
		splashPanel.add(butLoadPrevLib);
		splashPanel.add(butNewLib);
		splashPanel.add(butExitSys);

		splashPanel.setBackground(new Color(245, 245, 245));
		setContentPane(splashPanel);
	}

	public void addActionListener(ActionListener splashListener) {
		// adds listener to all buttons
		butNewLib.addActionListener(splashListener);
		butLoadPrevLib.addActionListener(splashListener);
		butExitSys.addActionListener(splashListener);
	}

	// accessors for all private attributes
	public JButton getButtonLoadPrevLib() {
		return butLoadPrevLib;
	}

	public JButton getButtonNewLib() {
		return butNewLib;
	}

	public JButton getButtonExitSys() {
		return butExitSys;
	}

}
