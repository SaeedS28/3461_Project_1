import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import javax.swing.*;

public class LogicOfGUI extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String separator=System.getProperty("line.separator");
	private String[] words={"Dystopia","Saskatchewan","Conscience",
			"Writers", "Deductible", "Pharaoh", "Handkerchief","Quizzed","Australia",
			"Windows"};
	private int i=0;
	private int correct, incorrect=0;
	private PrintWriter file;
	private JTextField text=new JTextField(30);
	private JLabel label= new JLabel();
	private JPanel p3=new JPanel();
	private JLabel testCase=new JLabel();
	private JLabel timer= new JLabel("Case 1");
	private JLabel label2=new JLabel();
	private JPanel p4 = new JPanel();
	private JButton button = new JButton("Press to Begin");
	private JPanel p5 =new JPanel();
	private int counter;
	private int buttonPress=0;
	private String userName;
	private Timer time;
	private int bksp=0;
	/**
	 * initializes the GUI for the first case study
	 * @throws FileNotFoundException if the file being written to does not exist
	 */
	public LogicOfGUI(String userName){
		
		super("Typing Tester GUI \n"); //the caption of the title bar
		this.userName=userName;
		this.setResizable(false);
		setSize(775,600);  //default window size
		setDefaultCloseOperation(EXIT_ON_CLOSE);  //quits the program whenever the x is pressed
		this.setLocationRelativeTo(null);
		
		this.getContentPane().setLayout(null);
		JPanel p1 =new JPanel();
		JPanel p2 = new JPanel(); 
		JPanel p0=new JPanel();
		
		//Setting the location and size of the different panels
		p0.setLocation(10,10);
		p0.setSize(150, 50);
		p1.setLocation(215, 50);
		p1.setSize(350, 250);
		
		p2.setLocation(375,185);
		p2.setSize(250,50);
		
		p3.setLocation(150,185);
		p3.setSize(200,185);
		button.setFont(new Font("Arial", Font.BOLD, 20));
		p4.setLocation(8,400);
		p4.setSize(770, 40);
		p5.setLocation(8, 500);
		p5.setSize(780,50);
		p5.add(button);

		//Setting the properties of different GUI elements to be placed on the panels
		timer.setFont(new Font("Arial", Font.BOLD, 40));
		timer.setForeground(Color.BLACK);
		label.setFont(new Font("Arial", Font.BOLD, 25));
		label2.setFont(new Font("Arial", Font.BOLD, 25));
		label.setForeground(Color.RED);
		testCase.setFont(new Font("Arial", Font.BOLD, 15));
		testCase.setText("Case 1: 1 char/sec");
		text.setFont(new Font("Arial", Font.BOLD, 25));
		text.setSize(30, 50);
		text.setVisible(false);
		
		p0.add(testCase);
		p2.add(label);
		p1.add(timer);
		p3.add(label2);
		p4.add(text);		
		
		this.add(p0);
		this.add(p2);
		this.add(p1);
		this.add(p3);
		this.add(p4);
		this.add(p5);
		
		//Initializes a file
		try {
			file= new PrintWriter("case1"+"_"+userName+".txt");
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		
		event e=new event();
		
		button.addActionListener(e);
		text.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if((e.getKeyCode()==KeyEvent.VK_BACK_SPACE) && (button.isEnabled()==false)) {
					bksp++;
				}
			}
		});
		
	}
	
	
	//this class contains the logic of the first case gui
	private class event implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			timer.setForeground(Color.BLACK);
			button.setText("Next");
			text.setVisible(true);
			button.setEnabled(false);
			label2.setText("Enter the Word: ");
			if (buttonPress<10){
				counter=words[i].length();
				timer.setText("Timer: " +counter);
				label.setText(words[i]);
				text.setText("");
				TimeClass tc=new TimeClass();
				time=new Timer(1000,tc);
				time.start();
			}
			else{
				file.append(separator+correct+ " correct\t\t\t"+incorrect + " incorrect");
				file.close();
				LogicOfGUICase2 case2=new LogicOfGUICase2(userName);
				case2.setVisible(true);
				dispose();
			}
			buttonPress++;
		}
	}
	
	//This class contains the logic of the timer
	private class TimeClass implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent n) {
			counter--;
			if(counter>=Integer.MAX_VALUE*2) {	//Colour changes for the last three seconds to let the user know that time is almost up
				timer.setText("Time: "+counter);
				if(counter<=3){
					timer.setForeground(Color.RED);
				}
			}
			else {
				//Stops the timer, hides the gui elements, and writes the relevant data to a file
				time.stop();
				label2.setText("");
				timer.setText("Done! Press next.");
				text.setVisible(false);
				button.setEnabled(true);
				file.append(words[i]);
				file.append("\t\t");
				file.append(text.getText());
				file.append("\t\t");
				file.append(String.valueOf(bksp));
				
				file.append("\t\t");
				
				bksp=0;
				if(text.getText().toLowerCase().equals(label.getText().toLowerCase())){
					file.append("Correct");
					correct++;
				}
				else{
					file.append("Incorrect");
					incorrect++;
				}
				text.setText("");
				label.setText("");
				file.append(separator);
				i++;
			}
				
		}
		
	}
}