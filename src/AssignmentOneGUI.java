import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


public class AssignmentOneGUI extends JFrame {  //adds functionality for the gui and window

	
	private static final long serialVersionUID = 1L;
	//Prompt shown at the start
	private static final String introText="<html>Certain words will appear on the screen.<br />"
			+ "Your job is to type these words into the textbox before the timer runs out. <br />"
			+ "There are 3 scenarios to consider <br /><br />"
			+ "<u><font color=\"red\">YOUR ANSWERS WILL BE RECORDED ONCE THE TIMER RUNS OUT.</font><u />" 
			+ "<br />"
			+ "<br />The timer is on the top of the screen. <br />"
			+ "Press the button below to begin </html>";
	
	
	public AssignmentOneGUI() {
		
		super("Typing Tester GUI \n"); //the caption of the title bar
		this.setResizable(false);
		setSize(950,350);  //default window size
		setDefaultCloseOperation(EXIT_ON_CLOSE);  //quits the program whenever the x is pressed
		this.setLocationRelativeTo(null);
		
		JButton begin = new JButton("Begin ");
		JLabel l = new JLabel(introText, SwingConstants.LEFT);
		//l.setForeground(Color.BLUE);
		l.setFont(new Font("Arial", Font.BOLD, 26));
		
		JPanel p2 = new JPanel(); //panel allows us to add gui elements
		p2.setLayout(new BorderLayout());
		p2.add(begin, BorderLayout.SOUTH);
		p2.add(l,BorderLayout.NORTH);
		this.add(p2);
		begin.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e)
			{
				//Button press brings the main application into view
				if(e.getSource().equals(begin)){
					try {
						String fn = JOptionPane.showInputDialog("Please enter your name");
						if(fn==null||fn.isEmpty()) {
							JOptionPane.showMessageDialog(null, "You did not follow the instructions. "
									+ "This app will now shut down");
							dispose();
							System.exit(1);
						}
						LogicOfGUI logic=new LogicOfGUI(fn);
						logic.setVisible(true);
					} catch (Exception e1) {
						e1.printStackTrace();
					} 
				}
				dispose();  //Deallocates the current view from memory
			}
			
		});

}
}
