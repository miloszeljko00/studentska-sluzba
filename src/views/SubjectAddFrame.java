package views;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.ButtonModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import controllers.SubjectController;
import models.Professor;

public class SubjectAddFrame extends JDialog{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4125736938637561082L;

	private static SubjectAddFrame instance;
	
	JButton addProfessorButton;
	JButton removeProfessorButton;
	JTextField professorField;
	Professor professor = null;
	
	private SubjectAddFrame(){
		super();


		this.setModal (true);
		this.setAlwaysOnTop (true);
		this.setModalityType (ModalityType.APPLICATION_MODAL);

		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension d = kit.getScreenSize();
		int width = d.width;
		int height = d.height;

		// Pravljenje prozora
		setSize(width*1/4, height*2/4);
		setLocationRelativeTo(MainFrame.getInstance());
		setTitle(MainFrame.getInstance().getResourceBundle().getString("subjectAdding"));
		Image icon = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB_PRE);
		setIconImage(icon);

		JPanel contentPanel = new JPanel(new GridLayout(8, 2, 5, 5));
		contentPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

		
		
		
		JLabel idLabel = new JLabel(MainFrame.getInstance().getResourceBundle().getString("subjectId") + "*");
		JTextField idField = new JTextField();
		JLabel nameLabel = new JLabel(MainFrame.getInstance().getResourceBundle().getString("subjectName") + "*");
		JTextField nameField = new JTextField();
		JLabel semesterLabel = new JLabel(MainFrame.getInstance().getResourceBundle().getString("semester") + "*");
		JComboBox<String> semesterComboBox = new JComboBox<String>();
		semesterComboBox.addItem(MainFrame.getInstance().getResourceBundle().getString("winter"));
		semesterComboBox.addItem(MainFrame.getInstance().getResourceBundle().getString("summer"));
		JLabel currentYearOfStudyLabel = new JLabel(MainFrame.getInstance().getResourceBundle().getString("yearOfStudy") + "*");
		JComboBox<String> currentYearOfStudyComboBox = new JComboBox<String>();
		currentYearOfStudyComboBox.addItem(MainFrame.getInstance().getResourceBundle().getString("firstYear"));
		currentYearOfStudyComboBox.addItem(MainFrame.getInstance().getResourceBundle().getString("secondYear"));
		currentYearOfStudyComboBox.addItem(MainFrame.getInstance().getResourceBundle().getString("thirdYear"));
		currentYearOfStudyComboBox.addItem(MainFrame.getInstance().getResourceBundle().getString("fourthYear"));		
		JLabel professorLabel = new JLabel(MainFrame.getInstance().getResourceBundle().getString("professor"));

		professorField = new JTextField();
		professorField.setEditable(false);

		
		addProfessorButton = new JButton("+");
		addProfessorButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				SubjectAddChooseProfessor subjectAddChooseProfessor = new SubjectAddChooseProfessor();
				subjectAddChooseProfessor.setVisible(true);
			}
		});
		removeProfessorButton = new JButton("-");
		removeProfessorButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int userInput = JOptionPane.showOptionDialog(SubjectAddFrame.getInstance(), MainFrame.getInstance().getResourceBundle().getString("sureToDeleteCurrentProfessor"),
						MainFrame.getInstance().getResourceBundle().getString("professorDelete"), JOptionPane.YES_NO_OPTION, 0, null, null, e);
				if(userInput == JOptionPane.YES_OPTION) {
					professor = null;
					SubjectAddFrame.getInstance().updateProfessorSelection();
				}
			}
		});
		
		
		
		if(professorField.getText().equals(""))
		{
			addProfessorButton.setEnabled(true);
			removeProfessorButton.setEnabled(false);
		}
		else
		{
			addProfessorButton.setEnabled(false);
			removeProfessorButton.setEnabled(true);
		}
		JLabel espbLabel = new JLabel(MainFrame.getInstance().getResourceBundle().getString("espb") + "*");
		JTextField espbField = new JTextField();
		
		
		JButton confirmButton = new JButton(MainFrame.getInstance().getResourceBundle().getString("confirm"));
		ButtonModel confirmButtonModel = confirmButton.getModel();
		ButtonEnabler buttonEnabler = new ButtonEnabler(confirmButtonModel);	
		buttonEnabler.addDocument(idField.getDocument());
		buttonEnabler.addDocument(nameField.getDocument());
		buttonEnabler.addDocument(espbField.getDocument());
		confirmButton.addActionListener(new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent e) {
				SubjectController subjectController = new SubjectController();
				
				String dataValid = subjectController.addSubject(idField.getText(), nameField.getText(),
						currentYearOfStudyComboBox.getSelectedIndex(),
						semesterComboBox.getSelectedIndex(),espbField.getText(), professor);
				
				if( dataValid != "OK") {
					JOptionPane.showMessageDialog(SubjectAddFrame.getInstance(), dataValid);
				}else {					
					idField.setText("");
					nameField.setText("");
					currentYearOfStudyComboBox.setSelectedIndex(0);
					semesterComboBox.setSelectedIndex(0);
					espbField.setText("");
					professorField.setText("");
					SubjectAddFrame.getInstance().dispose();
				}
			}
			
		});
		
		JButton cancelButton = new JButton(MainFrame.getInstance().getResourceBundle().getString("cancel"));
		cancelButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				idField.setText("");
				nameField.setText("");
				currentYearOfStudyComboBox.setSelectedIndex(0);
				semesterComboBox.setSelectedIndex(0);
				espbField.setText("");
				professorField.setText("");
				SubjectAddFrame.getInstance().dispose();
			}
		});
		
		contentPanel.add(idLabel);
		contentPanel.add(idField);
		contentPanel.add(nameLabel);
		contentPanel.add(nameField);
		contentPanel.add(semesterLabel);
		contentPanel.add(semesterComboBox);
		contentPanel.add(currentYearOfStudyLabel);
		contentPanel.add(currentYearOfStudyComboBox);
		contentPanel.add(espbLabel);
		contentPanel.add(espbField);
		contentPanel.add(professorLabel);
		contentPanel.add(professorField);
		contentPanel.add(addProfessorButton);
		contentPanel.add(removeProfessorButton);
		contentPanel.add(confirmButton);
		contentPanel.add(cancelButton);
	    this.getContentPane().add(contentPanel);
		
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	}
	
	public void updateProfessorSelection() {
		if(professor != null) {
			professorField.setText(professor.getName() + " " + professor.getSurname());
		}else {
			professorField.setText("");
		}
		if(professorField.getText().equals("")) {
			addProfessorButton.setEnabled(true);
			removeProfessorButton.setEnabled(false);
		}else {
			addProfessorButton.setEnabled(false);
			removeProfessorButton.setEnabled(true);
		}
		
	}

	public void setProfessor(Professor p) {
		professor = p;
	}
	
	public Professor getProfessor() {
		return professor;
	}
	
	public static SubjectAddFrame getInstance()
	{
		if(instance==null)
			instance=new SubjectAddFrame();
		instance.setLocationRelativeTo(MainFrame.getInstance());
		return instance;
	}
	public static void recreate() {
		if(instance != null)
			instance.dispose();
		instance = null;
	}
}
