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
	
	private JButton buttonPlay;
	private JButton buttonPause;

	private String actionPlay;
	private String actionListMusic;
	private String [] MusicList = new String [100];
	private String path = "C:\\Users\\ALLARASSEMJJ20\\eclipse-workspace\\MusicPlayground\\music\\";
	private Clip clip;
	private boolean isPlaying;
	private int index = 0;
	
	public void GUI() {
		File directory = new File(path);
		MusicList = directory.list();
		
		frame = new JFrame(); // create a frame
		frame.setSize(420,420); //set a size
		frame.setTitle("Audio player"); //give a title
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //close by clicking on red cross
		frame.setBackground(Color.blue);; // change color of background
		
		buttonPlay = new JButton("play");
		buttonPlay.setBounds(15,50,100,50);
		buttonPlay.setBackground(Color.green);
		buttonPlay.addActionListener(this);
		
		
		buttonPause = new JButton("pause");
		buttonPause.setBounds(15,150,100,50);
		buttonPause.setBackground(Color.green);
		buttonPause.addActionListener(this);
		
		
		
		
		panel = new JPanel();
		label = new JLabel("Click on play");
		listMusic = new JLabel("Music");
		listMusic.setBounds(250,30,400,100);
		label.setBounds(15,200,100,100);
		panel.setLayout(null);
		panel.add(listMusic);
		panel.add(buttonPlay);
		panel.add(buttonPause);
		panel.add(label);
		panel.add(label);
		frame.add(panel);
		frame.revalidate();
		
		ImageIcon image = new ImageIcon("logo.png"); //create an image icon
		frame.setIconImage(image.getImage()); //change icon of the frame
		frame.setVisible(true); //make the frame visible
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == buttonPlay) {
			
			actionPlay = "playing...";
			label.setText(actionPlay);
			
			actionListMusic = MusicList[index];
			listMusic.setText(actionListMusic);
			index++;
			File file = new File(path + actionListMusic);
			try {
				AudioInputStream as = AudioSystem.getAudioInputStream(file);
				clip = AudioSystem.getClip();
				clip.open(as);
				clip.start();
				isPlaying = true;
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
		
	}
	
	public static void main(String []args) {
		UIplayAround ui = new UIplayAround();
		ui.GUI();
	}
}
