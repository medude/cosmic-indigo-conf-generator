package main;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashSet;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import gui.Window;

public class Main extends JPanel implements ActionListener {
	JFileChooser resChooser;
	JButton resChooserButton;
	JButton createFileButton;
	JTextField confFileName;
	JTextField windowName;
	
	String filename;
	
	File resLocation;
	
	public Main() {
		super(new BorderLayout());
		
		resChooser = new JFileChooser();
		resChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		
		resChooserButton = new JButton("Select /res folder");
		resChooserButton.addActionListener(this);
		
		createFileButton = new JButton("Press to generate .conf file");
		createFileButton.addActionListener(this);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(resChooserButton);
		buttonPanel.add(createFileButton);
		
		confFileName = new JTextField(20);
		windowName = new JTextField(20);
		
		JPanel bodyPanel1 = new JPanel();
		bodyPanel1.add(new JLabel("Filename: "),
			    BorderLayout.WEST);
		bodyPanel1.add(confFileName);
		bodyPanel1.add(new JLabel(".conf"),
			    BorderLayout.WEST);
		
		JPanel bodyPanel2 = new JPanel();
		bodyPanel2.add(new JLabel("   Window name: "),
			    BorderLayout.WEST);
		bodyPanel2.add(windowName);
		
		add(buttonPanel, BorderLayout.PAGE_START);
		add(bodyPanel1, BorderLayout.CENTER);
		add(bodyPanel2, BorderLayout.SOUTH);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == resChooserButton) {
			filename = confFileName.getText();
			
			int returnVal = resChooser.showOpenDialog(Main.this);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				resLocation = resChooser.getSelectedFile();
			}
		} else if(e.getSource() == createFileButton) {
				File images = new File(resLocation.toString() + "/images");
				File[] listOfImages = images.listFiles();
				ArrayList<String> imageNames = new ArrayList<String>();
				
				for(int i = 0; i < listOfImages.length; i++) {
			    	if(listOfImages[i].isFile()) {
			    		if(listOfImages[i].getName().contains("."))
			    			imageNames.add(listOfImages[i].getName().substring(0, listOfImages[i].getName().lastIndexOf('.')));
			    		else
			    			imageNames.add(listOfImages[i].getName());
			    	}
			    }
				imageNames = removeDuplicates(imageNames);
			    
			    File models = new File(resLocation.toString() + "/models");
			    File[] listOfModels = models.listFiles();
			    ArrayList<String> modelsNames = new ArrayList<String>();
			    
			    for(int i = 0; i < listOfModels.length; i++) {
			    	if(listOfModels[i].isFile()) {
			    		if(listOfModels[i].getName().contains("."))
			    			modelsNames.add(listOfModels[i].getName().substring(0, listOfModels[i].getName().lastIndexOf('.')));
			    		else
			    			modelsNames.add(listOfModels[i].getName());
			    	}
			    }
			    modelsNames = removeDuplicates(modelsNames);
			    
			    File shaders = new File(resLocation.toString() + "/shaders");
			    File[] listOfShaders = shaders.listFiles();
			    ArrayList<String> shadersNames = new ArrayList<String>();
			    
			    for(int i = 0; i < listOfModels.length; i++) {
			    	if(listOfShaders[i].isFile()) {
			    		if(listOfShaders[i].getName().contains("."))
			    			shadersNames.add(listOfShaders[i].getName().substring(0, listOfShaders[i].getName().lastIndexOf('.')));
			    		else
			    			shadersNames.add(listOfShaders[i].getName());
			    	}
			    }
			    shadersNames = removeDuplicates(shadersNames);
			    
			    PrintWriter confFile = null;
			    try {
					confFile = new PrintWriter(resLocation.toString() + "/config/" + filename + ".conf", "UTF-8");
				} catch(FileNotFoundException e1) {
					e1.printStackTrace();
				} catch(UnsupportedEncodingException e1) {
					e1.printStackTrace();
				}
				
				confFile.println("v.1.0.0");
			    
			    confFile.println("texture.total");
			    confFile.println(imageNames.size());
			    
			    for(int i = 0; i < imageNames.size(); i++) {
			    	confFile.println("texture." + i);
			    	confFile.println(imageNames.get(i));
			    }
			    
			    
			    confFile.println("model.total");
			    confFile.println(modelsNames.size());
			    
			    for(int i = 0; i < modelsNames.size(); i++) {
			    	confFile.println("model." + i);
			    	confFile.println(modelsNames.get(i));
			    }
			    
			    
			    confFile.println("shader.total");
			    confFile.println(shadersNames.size());
			    
			    for(int i = 0; i < shadersNames.size(); i++) {
			    	confFile.println("shader." + i);
			    	confFile.println(shadersNames.get(i));
			    }
			    
			    confFile.println("window.title");
			    confFile.print(windowName.getText());
			    
			    confFile.close();
		}
	}

	
	public ArrayList<String> removeDuplicates(ArrayList<String> list) {

		// Store unique items in result.
		ArrayList<String> result = new ArrayList<>();

		// Record encountered Strings in HashSet.
		HashSet<String> set = new HashSet<>();

		// Loop over argument list.
		for (String item : list) {

		    // If String is not in set, add it to the list and the set.
		    if (!set.contains(item)) {
			result.add(item);
			set.add(item);
		    }
		}
		return result;
	    }
	
	public static void main(String[] args) {
		Window window = new Window("Cosmic Indigo .conf Generator");
	}
}