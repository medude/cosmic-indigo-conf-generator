package gui;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

import main.Main;

public class Window extends JFrame {
	public JPanel panel =  new JPanel();
	public JComponent contentPane;
	
	public Window(String title) {
		super(title);
	    
	    JFrame window = new JFrame(title);
	    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    contentPane = new Main();
	    contentPane.setOpaque(true);
	    window.setContentPane(contentPane);
	    
	    window.pack();
	    window.setVisible(true);
	}
	
	public void makeVisible() {
		setVisible(true);
	}
}
