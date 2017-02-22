import java.util.*;

import javax.swing.*;

import java.awt.event.*;
import java.awt.*;
import java.io.*;

public class FlashCardBuilder {
	
	private JFrame frame;
	private JPanel playerPanel;
	private JTextArea questionArea;
	private JTextArea answerArea;
	private ArrayList<FlashCard> cardList;
	
	public static void main(String args[]) {
		FlashCardBuilder fcb = new FlashCardBuilder();
		fcb.run();
	}
	
	public void run() {
		frame = new JFrame("Flash Card Builder");
		JPanel mainPanel = new JPanel();
		
		questionArea = new JTextArea(6,20);
		questionArea.setLineWrap(true);
		questionArea.setWrapStyleWord(true); // Set to wrap at word boundaries
		Font bigFont = new Font("sanserif", Font.BOLD, 24);
		questionArea.setFont(bigFont);
		
		JScrollPane qScroller = new JScrollPane(questionArea); // Make question area scrollable
		qScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		qScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		answerArea = new JTextArea(6, 20);
		answerArea.setLineWrap(true);
		answerArea.setWrapStyleWord(true);
		answerArea.setFont(bigFont);
		
		JScrollPane aScroller = new JScrollPane(answerArea); // Make question area scrollable
		aScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		aScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		JButton nextButton = new JButton("Next Card");
		cardList = new ArrayList<FlashCard>();
		
		JLabel qLabel = new JLabel("Question: ");
		JLabel aLabel = new JLabel("Answer: ");
		
		mainPanel.add(qLabel);
		mainPanel.add(qScroller);
		mainPanel.add(aLabel);
		mainPanel.add(aScroller);
		mainPanel.add(nextButton);
		
		/* Functionality */
		
		nextButton.addActionListener(new NextCardListener());
		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		JMenuItem newMenuItem = new JMenuItem("New");
		JMenuItem saveMenuItem = new JMenuItem("Save");
		
		newMenuItem.addActionListener(new NewMenuListener());
		saveMenuItem.addActionListener(new SaveMenuListener());
		
		fileMenu.add(newMenuItem);
		fileMenu.add(saveMenuItem);
		menuBar.add(fileMenu);
		
		JMenu playerMenu = new JMenu("Player");
		menuBar.add(playerMenu);
		JMenuItem playerMenuItem = new JMenuItem("Play");
		playerMenu.add(playerMenuItem);
		playerMenuItem.addActionListener(new PlayMenuListener());
				
		playerPanel = new JPanel();
		JLabel playerLabel = new JLabel("This is where the player could have been. Demonstration purposes only. Need to change File menu items");
		playerPanel.add(playerLabel);
		
		frame.setJMenuBar(menuBar);
		frame.setContentPane(mainPanel);
		frame.setSize(500, 600);
		frame.setVisible(true);
	}
	
	private void clearCard() {
		questionArea.setText("");
		answerArea.setText("");
		questionArea.requestFocus();
	}
	
	public class PlayMenuListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			frame.setContentPane(playerPanel);
			frame.setSize(500, 600);
			frame.setVisible(true);
		}
	}
	
	public class NextCardListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			FlashCard card = new FlashCard(questionArea.getText(), answerArea.getText());
			cardList.add(card);
			clearCard();
		}
	}
	
	public class SaveMenuListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			FlashCard card = new FlashCard(questionArea.getText(), answerArea.getText());
			cardList.add(card);
			
			JFileChooser fileSave = new JFileChooser();
			fileSave.showSaveDialog(frame);
			saveFile(fileSave.getSelectedFile());
		}
		
		private void saveFile(File file) {
			try {
				BufferedWriter writer = new BufferedWriter(new FileWriter(file));
				
				for (FlashCard card:cardList) {
					writer.write(card.getQuestion() + "/");
					writer.write(card.getAnswer() + "\n");
				}
				writer.close();
				
			} catch (IOException e) {
				System.out.println("Could not write the card list out.");
				e.printStackTrace();
			}
		}
	}
	
	public class NewMenuListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			cardList.clear();
			clearCard();
		}
	}
}
