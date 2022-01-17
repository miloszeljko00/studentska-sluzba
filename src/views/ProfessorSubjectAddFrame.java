package views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import models.DbProfessors;
import models.DbSubjects;
import models.Subject;

public class ProfessorSubjectAddFrame extends JDialog implements ActionListener{
	
	private static final long serialVersionUID = -7981006772785670384L;
	private JButton okButton;
	private JButton cancelButton;
	private String selectedSubject;
	@SuppressWarnings("rawtypes")
	private JList subjects;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ProfessorSubjectAddFrame() {
		super();
		
		setTitle("Dodaj predmet");
		setSize(400, 400);
		setLocationRelativeTo(this.getParent());
		setModal(true);
		setResizable(false);
		setAlwaysOnTop (true);
		
		JPanel commandsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		okButton = new JButton("Potvrdi");
		okButton.addActionListener(this);
		cancelButton = new JButton("Odustani");
		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		commandsPanel.add(okButton);
		commandsPanel.add(cancelButton);
		
		DefaultListModel listModel;
		listModel = new DefaultListModel();
		for(Subject s : DbSubjects.getInstance().getSubjects()) {
			if(!DbProfessors.getInstance().getRow(ProfessorTable.getInstance().getSelectedRow()).getSubjects().contains(s)) {
				listModel.addElement(s.getId() + " - " + s.getName());
		}
		}
		subjects = new JList(listModel);
	    subjects.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	    JScrollPane scrollPane = new JScrollPane(subjects);
		
		add(scrollPane, BorderLayout.NORTH);
		add(commandsPanel, BorderLayout.SOUTH);
		
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		selectedSubject = (String) subjects.getSelectedValue();
		if (selectedSubject == null) {
			JOptionPane.showMessageDialog(this, "Predmet nije selektovan.", "Upozorenje!",
					JOptionPane.ERROR_MESSAGE);
		}
		else {
			String[] parts = selectedSubject.split(" - ");
			String id = parts[0];
			for(Subject s : DbSubjects.getInstance().getSubjects()) {
				if(id.equals(s.getId())) {
					if(s.getProfessor() != null) {
						int userInput = JOptionPane.showConfirmDialog(this,
								"Izabrani predmet ve� ima dodeljenog profesora, izvr�iti zamenu?",
								"Zamena profesora", JOptionPane.YES_NO_OPTION);
						if (userInput == JOptionPane.YES_OPTION) {
							if(s.getProfessor().getSubjects().contains(s)) {
								s.getProfessor().getSubjects().remove(s);
							}
							DbProfessors.getInstance().getRow(ProfessorTable.getInstance().getSelectedRow()).getSubjects().add(s);
							s.setProfessor(DbProfessors.getInstance().getRow(ProfessorTable.getInstance().getSelectedRow()));
						}else {
							return;
						}
					}else {
						DbProfessors.getInstance().getRow(ProfessorTable.getInstance().getSelectedRow()).getSubjects().add(s);
						s.setProfessor(DbProfessors.getInstance().getRow(ProfessorTable.getInstance().getSelectedRow()));
					}
				}
			}
			JOptionPane.showMessageDialog(this, "Predmet uspe�no dodat!");
			ProfessorEditFrame.getInstance().getProfessorSubjectPanel().refresh();
			setVisible(false);
		}
	}
}