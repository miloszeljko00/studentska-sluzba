package views;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.WindowConstants;



public class StudentEditFrame extends JFrame{

		
		/**
		 * 
		 */
	private static final long serialVersionUID = -919912894941283074L;
	private static StudentEditFrame instance;
	
	private StudentEditFrame() {
		super();

		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension d = kit.getScreenSize();
		int width = d.width;
		int height = d.height;

		// Pravljenje prozora
		setSize(width*1/4, height*2/4);
		setLocationRelativeTo(null);
		setTitle("Izmena studenta");
		Image icon = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB_PRE);
		setIconImage(icon);
		
		JTabbedPane tabbedPane = new JTabbedPane();
		
		StudentInfoPanel studentInfoPanel = StudentInfoPanel.getInstance();
		StudentGradesPanel studentGradesPanel = StudentGradesPanel.getInstance();
		StudentSubjectsPanel studentSubjectsPanel = StudentSubjectsPanel.getInstance();
		

		tabbedPane.add(studentInfoPanel, "Informacije");
		tabbedPane.add(studentGradesPanel, "Polo�eni");
		tabbedPane.add(studentSubjectsPanel, "Nepolo�eni");
		
		this.add(tabbedPane);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	}
	
	public static StudentEditFrame getInstance()
	{
		if(instance == null)
			instance = new StudentEditFrame();
		return instance;
	}

}