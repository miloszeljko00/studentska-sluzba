package views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class EditEntityListener implements ActionListener{
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		MainFrame mainFrame = MainFrame.getInstance();
		
		JPanel panel = (JPanel) mainFrame.getContentPane();
		
		MainTabbedPane mainTabbedPane = (MainTabbedPane) panel.getComponent(2);
		
		switch(mainTabbedPane.getSelectedIndex()) {
		case 0:
			StudentEditFrame studentEditFrame;
			if(StudentTable.getInstance().getSelectedRow() != -1) {
				studentEditFrame = StudentEditFrame.getInstance();
				StudentInfoPanel.getInstance().updateStudentSelection(StudentTable.getInstance().getSelectedRow());
				StudentGradesPanel.getInstance().refresh();
				studentEditFrame.setVisible(true);
			}else {
				JOptionPane.showMessageDialog(mainTabbedPane, MainFrame.getInstance().getResourceBundle().getString("studentNotChosen"), MainFrame.getInstance().getResourceBundle().getString("warning"), JOptionPane.WARNING_MESSAGE);
			}
			break;
		case 1:
			ProfessorEditFrame professorEditFrame;
			if(ProfessorTable.getInstance().getSelectedRow() != -1) {
				professorEditFrame=ProfessorEditFrame.getInstance();
				ProfessorInfoPanel.getInstance().updateProfessorSelection(ProfessorTable.getInstance().getSelectedRow());
				professorEditFrame.getProfessorSubjectPanel().refresh();
				professorEditFrame.setVisible(true);
			}
			else {
				JOptionPane.showMessageDialog(mainTabbedPane, MainFrame.getInstance().getResourceBundle().getString("professorNotChosen"), MainFrame.getInstance().getResourceBundle().getString("warning"), JOptionPane.WARNING_MESSAGE);
			}
			break;
		case 2:
			SubjectEditFrame subjectEditFrame;
			if(SubjectTable.getInstance().getSelectedRow() != -1) {
				subjectEditFrame = SubjectEditFrame.getInstance();
				subjectEditFrame.updateSubjectSelection();
				subjectEditFrame.setVisible(true);
			}else {
				JOptionPane.showMessageDialog(mainTabbedPane, MainFrame.getInstance().getResourceBundle().getString("subjectNotChosen"), MainFrame.getInstance().getResourceBundle().getString("warning"), JOptionPane.WARNING_MESSAGE);
			}
			break;
		default:
			System.out.println(MainFrame.getInstance().getResourceBundle().getString("error"));
			break;
		}	
	}
}
