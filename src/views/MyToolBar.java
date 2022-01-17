package views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToolBar;

import controllers.StudentController;
import methods.ResizeIcon;

public class MyToolBar extends JToolBar {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5375224116363442663L;
	
	JTextField searchBar;
	
	public MyToolBar()
	{
		super();
		
		int iconSize = 18;
		Icon addIcon = ResizeIcon.resizeIcon(new ImageIcon("images/addButton.png"),iconSize,iconSize);
		Icon editIcon = ResizeIcon.resizeIcon(new ImageIcon("images/editButton.png"),iconSize,iconSize);
		Icon deleteIcon = ResizeIcon.resizeIcon(new ImageIcon("images/deleteButton.png"),iconSize,iconSize);
		Icon searchIcon = ResizeIcon.resizeIcon(new ImageIcon("images/searchButton.png"),iconSize,iconSize);
		
		JButton addButton = new JButton(addIcon);
		JButton editButton = new JButton(editIcon);
		JButton deleteButton = new JButton(deleteIcon);
		JButton searchButton = new JButton(searchIcon);

		addButton.setToolTipText("Add entity");
		editButton.setToolTipText("Edit entity");
		deleteButton.setToolTipText("Delete entity");
		searchButton.setToolTipText("Search entity");
		
		searchBar = new JTextField(30);
		Dimension d = new Dimension(40, 30);
		searchBar.setMaximumSize(d);
		
		addButton.addActionListener(new AddEntityListener());
		editButton.addActionListener(new EditEntityListener());
		deleteButton.addActionListener(new DeleteEntityListener());
		searchBar.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent e) {
			
			}
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if (e.getKeyCode()==KeyEvent.VK_ENTER){
					MainFrame mainFrame = MainFrame.getInstance();
					
					JPanel panel = (JPanel) mainFrame.getContentPane();
					
					MainTabbedPane mainTabbedPane = (MainTabbedPane) panel.getComponent(2);
					
					switch(mainTabbedPane.getSelectedIndex()) {
						case 0:
						{
							String s = searchBar.getText();
							StudentController studentController = new StudentController();
							studentController.searchStudent(s);
							searchButton.setSelected(false);
							break;
						}
						case 1:
						{
						}
						case 2:
						{	
			
						}
					}
			    }
			}
		});
		
		searchButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MainFrame mainFrame = MainFrame.getInstance();
				
				JPanel panel = (JPanel) mainFrame.getContentPane();
				
				MainTabbedPane mainTabbedPane = (MainTabbedPane) panel.getComponent(2);
				
				switch(mainTabbedPane.getSelectedIndex()) {
					case 0:
					{
						String s = searchBar.getText();
						StudentController studentController = new StudentController();
						studentController.searchStudent(s);
						searchButton.setSelected(false);
						break;
					}
					case 1:
					{
					}
					case 2:
					{	
		
					}
				}
			}
		});
		
		this.add(addButton);
		this.add(editButton);
		this.add(deleteButton);
		this.add(Box.createHorizontalGlue());
		this.add(searchBar);
		this.add(searchButton);
		this.setFloatable(false);
		this.setBorder(BorderFactory.createLineBorder(Color.black));	
	}
	



}
