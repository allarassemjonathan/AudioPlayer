import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class UIplayAround implements ActionListener{
	private JPanel panel;
	private JFrame frame;
	
	private JLabel label;
	private JLabel listMusic;
	private JLabel Title;
	
	private JButton buttonPlay;
	private JButton buttonPause;
	private JButton buttonSkipRight;
	private JButton buttonSkipLeft;

	private String actionPlay;
	private String actionListMusic;
	private String [] MusicList = new String [100];
	/*
	 * the path needs to be changed depending on where you put your music
	 */
	private String path = "C:\\Users\\ALLARASSEMJJ20\\eclipse-workspace\\MusicPlayground\\music\\";
	private Clip clip;
	private boolean isPlaying;
	private int index = 0;
	private int xline = 170;
	private int yline = 150;
	private int ymusicline = 30;
	private int width = 80;
	private int length = 30;
	
	
	public void GUI() {
		File directory = new File(path);
		MusicList = directory.list();
		
		frame = new JFrame(); // create a frame
		frame.setSize(420,420); //set a size
		frame.setTitle("Audio player"); //give a title
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //close by clicking on red cross
		frame.setBackground(Color.blue);; // change color of background
		frame.setResizable(false);
		
		buttonPlay = new JButton("play");
		buttonPlay.setBounds(xline - 25, yline,width,length);
		buttonPlay.setBackground(Color.green);
		buttonPlay.addActionListener(this);
		
		
		buttonPause = new JButton("pause");
		buttonPause.setBounds(xline - 25, yline + 50,width,length);
		buttonPause.setBackground(Color.green);
		buttonPause.addActionListener(this);
		
		
		buttonSkipRight = new JButton(" --> ");
		buttonSkipRight.setBounds(xline  + 75, ymusicline + 37, width, length);
		buttonSkipRight.setBackground(Color.green);
		buttonSkipRight.addActionListener(this);
		

		buttonSkipLeft = new JButton(" <-- ");
		buttonSkipLeft.setBounds(xline  - 125, ymusicline + 37, width, length);
		buttonSkipLeft.setBackground(Color.green);
		buttonSkipLeft.addActionListener(this);
		
		
		
		panel = new JPanel();
		label = new JLabel("Click on play");
		listMusic = new JLabel("Music");
		Title = new JLabel("JAVA AUDIO PLAYER");
		Title.setBounds(xline - 47, ymusicline - 50, 400,100);
		listMusic.setBounds(xline ,ymusicline,400,100);
		label.setBounds(15,300,100,100);
		panel.setLayout(null);
		panel.add(listMusic);
		panel.add(buttonPlay);
		panel.add(buttonPause);
		panel.add(buttonSkipRight);
		panel.add(buttonSkipLeft);
		panel.add(Title);
		panel.add(label);
		panel.add(label);
		panel.setBackground(new Color(0xadd8e6));
		frame.add(panel);
		frame.revalidate();
		ImageIcon image = new ImageIcon("skipLeft.png"); //create an image icon
		frame.setIconImage(image.getImage()); //change icon of the frame
		frame.setVisible(true); //make the frame visible
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == buttonPlay) {
			
			actionPlay = "playing...";
			label.setText(actionPlay);
			
			
			try {
				if(!isPlaying) {
				actionListMusic = MusicList[index];
				String [] dirty = actionListMusic.split(".wav");
				System.out.println(dirty[0]);
				listMusic.setText(dirty[0]);
				index++;
				File file = new File(path + actionListMusic);
				AudioInputStream as = AudioSystem.getAudioInputStream(file);
				clip = AudioSystem.getClip();
				clip.open(as);
				isPlaying = true;
				}
				clip.start();
			} catch (UnsupportedAudioFileException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (LineUnavailableException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		
		if(e.getSource() == buttonPause) {
			actionPlay = "pause";
			label.setText(actionPlay);
			if(isPlaying) {
				clip.stop();
			}
		}
		
		if(e.getSource() == buttonSkipRight) {
			actionPlay = "Skipping...";
			try {
				if(isPlaying) {
					clip.stop();
				}
				index++;
				actionListMusic = MusicList[index];
				String [] dirty = actionListMusic.split(".wav");
				listMusic.setText(dirty[0]);
				File file = new File(path + actionListMusic);
				AudioInputStream as = AudioSystem.getAudioInputStream(file);
				clip = AudioSystem.getClip();
				clip.open(as);
				clip.start();
				isPlaying = true;
			}
			catch (UnsupportedAudioFileException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (LineUnavailableException e1) {
				e1.printStackTrace();
			}
				
		}
		if(e.getSource() == buttonSkipLeft) {
			try {
				if(isPlaying) {
					clip.stop();
				}
				index--;
				actionListMusic = MusicList[index];
				String [] dirty = actionListMusic.split(".wav");
				listMusic.setText(dirty[0]);
				File file = new File(path + actionListMusic);
				AudioInputStream as = AudioSystem.getAudioInputStream(file);
				clip = AudioSystem.getClip();
				clip.open(as);
				clip.start();
				isPlaying = true;
			}
			catch (UnsupportedAudioFileException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (LineUnavailableException e1) {
				e1.printStackTrace();
			}
		}
		
	}
	
	public static void main(String []args) {
		UIplayAround ui = new UIplayAround();
		ui.GUI();
	}
}
