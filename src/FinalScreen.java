import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


public class FinalScreen extends JFrame {  //adds functionality for the gui and window

	
	private static final long serialVersionUID = 1L;
	
	//FInal screen prompt
	private static final String introText="<html>Thank you for participating.<br />"
			+ "Wherever you ran this app from, there should be 3 files:<br /><br /> <font color=red>"
			+ "case1_[YOURNAME].txt, case2_[YOURNAME].txt, and case3_[YOURNAME].txt </font><br/><br />"
			+ "Please send these files to me and finish the survey. <br />"
			+ "<br />"
			+ "Press the button below to end<html>";
	
	
	public FinalScreen() {
		
		super("Typing Tester GUI \n"); //the caption of the title bar
		this.setResizable(false);
		setSize(950,350);  //default window size
		setDefaultCloseOperation(EXIT_ON_CLOSE);  //quits the program whenever the x is pressed
		this.setLocationRelativeTo(null);
		
		JButton end = new JButton("End");
		JLabel l = new JLabel(introText, SwingConstants.LEFT);
		l.setFont(new Font("Arial", Font.BOLD, 26));
		
		JPanel p2 = new JPanel(); //panel allows us to add gui elements
		p2.setLayout(new BorderLayout());
		p2.add(end, BorderLayout.SOUTH);
		p2.add(l,BorderLayout.NORTH);
		this.add(p2);
		end.addActionListener(new ActionListener(){

			@Override
			
			//Button press disposes the gui
			public void actionPerformed(ActionEvent e)
			{
				if(e.getSource().equals(end)){
					dispose();
				}
			}
			});
		}
	}

