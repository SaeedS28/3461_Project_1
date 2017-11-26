import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Random;
import javax.swing.*;

public class LogicOfGUI2 extends JFrame{
	
	private String separator=System.getProperty("line.separator");
	private String[] words={"Blue","Saskatchewan","Conscience",
			"Rhythm", "Deductible", "Pharaoh", "Handkerchief","Zeus","Australia",
			"Caesar"};
	int i=0;
	PrintWriter file;
	JTextField text=new JTextField("Enter the word that you see above here",30);
	JLabel label= new JLabel();
	JPanel p3=new JPanel();
	JLabel timer= new JLabel();
	JLabel label2=new JLabel();
	JPanel p4 = new JPanel();
	JButton button = new JButton("Press to Begin");
	JPanel p5 =new JPanel();
	int count;
	int buttonPress=0;
	
	Timer time;
	public LogicOfGUI2() throws FileNotFoundException, UnsupportedEncodingException{
		
		super("Typing Tester GUI \n"); //the caption of the title bar
		this.setResizable(false);
		setSize(775,600);  //default window size
		setDefaultCloseOperation(EXIT_ON_CLOSE);  //quits the program whenever the x is pressed
		this.setLocationRelativeTo(null);
		
		this.getContentPane().setLayout(null);
		JPanel p1 =new JPanel();
		JPanel p2 = new JPanel(); //panel allows us to add gui elements
		

		p1.setLocation(325, 50);
		p1.setSize(150, 100);
		
		p2.setLocation(375,185);
		p2.setSize(200,50);
		
		p3.setLocation(150,185);
		p3.setSize(200,185);
		
		p4.setLocation(10,400);
		p4.setSize(770, 40);
		p5.setLocation(8, 500);
		p5.setSize(780,30);
		p5.add(button);

		timer.setFont(new Font("Arial", Font.BOLD, 30));
		timer.setForeground(Color.RED);
		label.setFont(new Font("Arial", Font.BOLD, 25));
		label2.setFont(new Font("Arial", Font.BOLD, 25));
		label.setForeground(Color.RED);
		text.setSize(700, 20);
		
		p2.add(label);
		p1.add(timer);
		p3.add(label2);
		p4.add(text);
		
		this.add(p2);
		this.add(p1);
		this.add(p3);
		this.add(p4);
		this.add(p5);
		
		file= new PrintWriter("output2.txt");
		
		button.addActionListener(new event());
	}
	
	public class event implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			button.setText("Next");
			button.setEnabled(false);
			label2.setText("Enter the Word: ");
			if (buttonPress<10){

				count=(int) Math.ceil(words[i].length()/2);
				timer.setText("Timer: " +count);
				label.setText(words[i]);
				text.setText("");
				TimeClass tc=new TimeClass(count);
				time=new Timer(1000,tc);
				time.start();
			}
			else{
				file.close();
			}
			buttonPress++;
		}
	}
	
	public class TimeClass implements ActionListener{

		int counter;
		
		public TimeClass(int counter) {
			this.counter=counter;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			counter--;
			if(counter>=1) {
				timer.setText("Time: "+counter);
			}
			else {
				time.stop();
				
				timer.setText("Done! Press next.");
				
				button.setEnabled(true);
				file.append(words[i]);
				file.append("\t");
				file.append(text.getText());
				file.append("\t");
				if(text.getText().equals(label.getText())){
					file.append("Correct");
				}
				else{
					file.append("Incorrect");
				}
				text.setText("");
				label.setText("");
				file.append(separator);
				i++;
			}
				
		}
		
	}
}

