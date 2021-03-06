package views;


import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

import models.DbStudents;
import models.Grade;
import models.Student;

public class AbstractTableModelGrades  extends AbstractTableModel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4656217212866529542L;
	Student s = DbStudents.getInstance().findStudentByIndex((String) StudentTable.getInstance().getValueAt(StudentTable.getInstance().getSelectedRow(), 0));
	
	@Override
	public int getColumnCount() {
		return 5;
	}

	@Override
	public int getRowCount() {
		if(s.getGrades() != null) {
			return s.getGrades().size();
		}else {
			return 0;
		}
	}

	@SuppressWarnings("deprecation")
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
	
		List<Grade> grades = s.getGrades();
		Grade o = grades.get(rowIndex);
		
		if(grades.get(rowIndex)==null)
		{
			JOptionPane.showMessageDialog(null, MainFrame.getInstance().getResourceBundle().getString("subjectNotChosen"));
			return -1;
		} 
		
		switch(columnIndex)
		{
			case 0:
			{
				return o.getSubject().getId();
			}
			case 1:
			{
				return o.getSubject().getName();
			}
			case 2:
			{
				return Integer.toString(o.getSubject().getESPB());
			}
			case 3:
			{
				return o.getGrade();
			}
			case 4:
			{
				int day = o.getEvaluationDate().getDate();
				int month = o.getEvaluationDate().getMonth() + 1;
				int year = o.getEvaluationDate().getYear() + 1900;
				String date = Integer.toString(day) + "." + Integer.toString(month) + "." + Integer.toString(year) + ".";
				return date;
			}
			default:
				return null;
		
		}
	}
	
	@Override
	public String getColumnName(int column) {
		switch(column)
		{
			case 0:
			{
				return MainFrame.getInstance().getResourceBundle().getString("subjectId");
			}
			case 1:
			{
				return MainFrame.getInstance().getResourceBundle().getString("subjectName");
			}
			case 2:
			{
				return MainFrame.getInstance().getResourceBundle().getString("espb");
			}
			case 3:
			{
				return MainFrame.getInstance().getResourceBundle().getString("mark");
			}
			case 4:
			{
				return MainFrame.getInstance().getResourceBundle().getString("date");
			}
			default:
				return null;
		
		}
	}
	public void updateStudent() {
		s = DbStudents.getInstance().findStudentByIndex((String) StudentTable.getInstance().getValueAt(StudentTable.getInstance().getSelectedRow(), 0));
	}
}
