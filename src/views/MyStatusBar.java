package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


//https://www.sourcecodester.com/tutorials/java/5668/easy-way-implement-dynamic-clock-java-using-threads.html

public class MyStatusBar extends JPanel implements Runnable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4762649533520077076L;
	JLabel windowNameLabel;
	
	public MyStatusBar() {
		super();
		windowNameLabel = new JLabel(MainFrame.getInstance().getResourceBundle().getString("studentServicesStudents"));
		windowNameLabel.setBorder(new EmptyBorder(4, 4, 4, 4));
		windowNameLabel.setFont(new Font("Serif", Font.BOLD, 16));
		
		
		
		
		this.setLayout(new BorderLayout());
		this.add(windowNameLabel, BorderLayout.WEST);
		this.add(dateLabel, BorderLayout.EAST);
		
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension d = kit.getScreenSize();
		d.height=40;
		setMaximumSize(d);
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		
		this.start();
		
	}
	
	
	
	Thread timer = null;
	String dateToDisplay;
	
	int hr;
	Date d;
	JLabel dateLabel = new JLabel();
	int hour;
	int minute;
	int second;
	int day;
	int month;
	int year;

	
	public void start() { 
		 if(timer == null) 
	    { 
	      timer = new Thread(this); 
	      timer.start(); 
	    } 
	} 
	
	
	
	
	public void stop() 
	{ 
	  timer = null; 
	} 
	
	@SuppressWarnings("deprecation")
	public String getFormatedDate(Date d) {
    
    String formatedDate=" "; 
		hour = d.getHours(); 
		minute = d.getMinutes(); 
		day = d.getDate();
		month = d.getMonth() + 1; 	//returns 0 to 11 for each month with january being 0
		year = d.getYear() + 1900;	//	returns current year - 1900
		
		String minuteString = String.valueOf(minute);
		if(minute < 10) {
			minuteString = "0" + String.valueOf(minute);
		}
		
		String hourString = String.valueOf(hour);
		if(hour < 10) {
			hourString = "0" + String.valueOf(hour);
		}
		
		
		formatedDate=formatedDate.concat(hourString); 
		formatedDate=formatedDate.concat(":"); 
		formatedDate=formatedDate.concat(minuteString);  
		formatedDate=formatedDate.concat("  "); 
		formatedDate=formatedDate.concat(String.valueOf(day));  
		formatedDate=formatedDate.concat("."); 
		formatedDate=formatedDate.concat(String.valueOf(month));  
		formatedDate=formatedDate.concat("."); 
		formatedDate=formatedDate.concat(String.valueOf(year)); 
		formatedDate=formatedDate.concat(".      "); 
		return formatedDate;  
	} 
	
	public void run() { 
			// Sleep in the timer thread... 
			while (timer != null) { 
			    try {Thread.sleep(10);} 
			catch (InterruptedException e){} 
			d = new  Date(); 
			dateToDisplay=getFormatedDate(d); 
			dateLabel.setText(dateToDisplay); 
		} 
		timer = null;  
	} 
	
	public void reloadUI(){
		MainTabbedPane mainTabbedPane = (MainTabbedPane) MainFrame.getInstance().getContentPane().getComponent(2);
		switch(mainTabbedPane.getSelectedIndex()) {
		case 0:
			windowNameLabel.setText(MainFrame.getInstance().getResourceBundle().getString("studentServicesStudents"));
			break;
		case 1:
			windowNameLabel.setText(MainFrame.getInstance().getResourceBundle().getString("studentServicesProfessors"));
			break;
		case 2:
			windowNameLabel.setText(MainFrame.getInstance().getResourceBundle().getString("studentServicesSubjects"));
			break;
		default:
			break;
		}
		
	}
}
	