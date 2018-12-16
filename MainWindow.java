import java.awt.*;
import java.util.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.*;

public class MainWindow extends JFrame implements ActionListener{
	
	private JTextField messageInput;
	private JTextField keyInput;	
	private JLabel messageVector;
	private JLabel keyMatrix;
	private JLabel encryptedVector;
	private JLabel encryptedVectorWordEncrypt;
	private JLabel encryptedVectorWordDecrypt;
	private JLabel originalVectorWord;
	private JLabel determinant = new JLabel("");
	
	private JLabel keyMatrixLeftBumperEncrypt = new JLabel("");
	private JLabel keyMatrixRightBumperEncrypt  = new JLabel("");
	private JLabel messageVectorLeftBumper  = new JLabel("");
	private JLabel messageVectorRightBumper  = new JLabel("");
	private JLabel encryptedVectorLeftBumperEncrypt  = new JLabel("");
	private JLabel encryptedVectorRightBumperEncrypt  = new JLabel("");
	
	
	private JLabel keyMatrixLeftBumperDecrypt = new JLabel("");
	private JLabel keyMatrixDecrypt = new JLabel("");
	private JLabel keyMatrixRightBumperDecrypt  = new JLabel("");
	
	private JLabel encryptedVectorLeftBumperDecrypt = new JLabel("");
	private JLabel encryptedVectorDecrypt = new JLabel("");
	private JLabel encryptedVectorRightBumperDecrypt  = new JLabel("");
	
	private JLabel originalVectorLeftBumperDecrypt = new JLabel("");
	private JLabel originalVectorDecrypt = new JLabel("");
	private JLabel originalVectorRightBumperDecrypt  = new JLabel("");

	private int vectorSize;
	private JFrame mainFrame;
	private String keyMatrixString = "";
	private String messageVectorString = "";
	private String encryptedVectorString = "";
	
	private String leftBumper = "";
	private String rightBumper = "";


	public static void main(String[] args) {
		new MainWindow("Hill Cipher");
	}
	
	public MainWindow(String title) {
		super(title);
		createWindow();
	}
	
	
//	public String makeRandKey() {
//		
//	}
	
	/*
	 * Creates the frame along with the buttons and text fields for the main window
	 */
	public void createWindow() {
		
		mainFrame = new JFrame("Hill Cipher");
		mainFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		mainFrame.setSize(650, 570);;
		JPanel mainPanel = new JPanel();
		JPanel encryptPanel = new JPanel();
		JPanel decryptPanel = new JPanel();
		
		//Labels for what the user has to enter
		JLabel messageLabel = new JLabel("Enter Message To Encrypt");
		JLabel keyLabel = new JLabel("Enter cipher key");
		messageLabel.setSize(100, 50);
		keyLabel.setSize(100, 50);
		
		keyMatrix = new JLabel("");
		keyMatrix.setSize(300,300);
		
		messageVector = new JLabel("");
		messageVector.setSize(100,100);
		
		encryptedVector = new JLabel("");
		encryptedVector.setSize(100,100);
		
		encryptedVectorWordEncrypt = new JLabel("");
		encryptedVectorWordEncrypt.setSize(100,100);
		
		originalVectorWord = new JLabel("");
		originalVectorWord.setSize(100,100);
		
		
		
		//Text fields for the places and transitions
		messageInput = new JTextField();
		keyInput = new JTextField();
		messageInput.setPreferredSize(new Dimension(200,30));
		keyInput.setPreferredSize(new Dimension(600,30));
		
		//Creating the 3 buttons for the inputs, transitions and submit
		JButton encryptBtn = new JButton("Encrypt");
		encryptBtn.addActionListener(this);
		encryptBtn.setActionCommand("encryptBtn");
		
		JButton decryptBtn = new JButton("Decrypt");
		decryptBtn.addActionListener(this);
		decryptBtn.setActionCommand("decryptBtn");
		
		JButton generateBtn = new JButton("Generate Key");
		generateBtn.addActionListener(this);
		generateBtn.setActionCommand("generateKey");
		
		//Adding all the components to the panel
		mainPanel.add(messageLabel);
		mainPanel.add(messageInput);
		mainPanel.add(keyLabel);
		mainPanel.add(generateBtn);
		mainPanel.add(keyInput);
		mainPanel.add(encryptBtn);
		mainPanel.add(decryptBtn);
		mainPanel.add(determinant);		
		
		encryptPanel.add(keyMatrixLeftBumperEncrypt);
		encryptPanel.add(keyMatrix);
		encryptPanel.add(keyMatrixRightBumperEncrypt);
		encryptPanel.add(new JLabel("  * "));
		encryptPanel.add(messageVectorLeftBumper);
		encryptPanel.add(messageVector);
		encryptPanel.add(messageVectorRightBumper);
		encryptPanel.add(new JLabel("(mod 26)"));
		encryptPanel.add(new JLabel("  =  "));
		encryptPanel.add(encryptedVectorLeftBumperEncrypt);
		encryptPanel.add(encryptedVector);
		encryptPanel.add(encryptedVectorRightBumperEncrypt);
		encryptPanel.add(new JLabel("  =  "));
		encryptPanel.add(encryptedVectorWordEncrypt);
		
		decryptPanel.add(keyMatrixLeftBumperDecrypt);
		decryptPanel.add(keyMatrixDecrypt);
		decryptPanel.add(keyMatrixRightBumperDecrypt);
		decryptPanel.add(new JLabel("  * "));
		decryptPanel.add(encryptedVectorLeftBumperDecrypt);
		decryptPanel.add(encryptedVectorDecrypt);
		decryptPanel.add(encryptedVectorRightBumperDecrypt);
		decryptPanel.add(new JLabel("(mod 26)"));
		decryptPanel.add(new JLabel("  =  "));
		decryptPanel.add(originalVectorLeftBumperDecrypt);
		decryptPanel.add(originalVectorDecrypt);
		decryptPanel.add(originalVectorRightBumperDecrypt);
		decryptPanel.add(new JLabel("  =  "));
		decryptPanel.add(originalVectorWord);
		
		
		
		//Creates a flow layout from left to right for the panel
		FlowLayout topLayout = new FlowLayout();
		mainPanel.setLayout(topLayout);
		mainPanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		
		FlowLayout flow = new FlowLayout();
		encryptPanel.setLayout(flow);
		encryptPanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		
		FlowLayout flow2 = new FlowLayout();
		decryptPanel.setLayout(flow2);
		decryptPanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		
		mainPanel.add(encryptPanel);
		mainPanel.add(decryptPanel);
		//Adds the panel to the frame
		mainFrame.add(mainPanel);
		mainFrame.setVisible(true);
		
	}
	
	public void setKeyMatrixDecrypt(double[][] matrix) {
		
		String keyMatrixStringDecrypt = "<html>";
		for(int i = 0; i < vectorSize; i++) {
			
			for(int j = 0; j < vectorSize; j++) {
				keyMatrixStringDecrypt = keyMatrixStringDecrypt + (int)(matrix[i][j]) + "  ";
			}
			
			if(i == vectorSize - 1) {
				keyMatrixStringDecrypt = keyMatrixStringDecrypt + " ";
			}
			else {
				keyMatrixStringDecrypt = keyMatrixStringDecrypt + "<br>";
			}
		}
		keyMatrixStringDecrypt = keyMatrixStringDecrypt + " </html>";
		keyMatrixDecrypt.setText(keyMatrixStringDecrypt);
	}
	
	public void setKeyMatrix(double[][] matrix) {
		keyMatrixString = "<html>";
		for(int i = 0; i < vectorSize; i++) {
			
			for(int j = 0; j < vectorSize; j++) {
				keyMatrixString = keyMatrixString + (int)(matrix[i][j]) + "  ";
			}
			
			if(i == vectorSize - 1) {
				keyMatrixString = keyMatrixString + " ";
			}
			else {
				keyMatrixString = keyMatrixString + "<br>";
			}
		}
		keyMatrixString = keyMatrixString + " </html>";
		keyMatrix.setText(keyMatrixString);
	} 
	
	public void setMessageVector(double[][] vector) {
		messageVectorString = "<html>";
		for(int i = 0; i < vectorSize; i++) {
			
			for(int j = 0; j < 1; j++) {
				messageVectorString = messageVectorString + (int)(vector[i][j]) + "  ";
			}
			
			if(i == vectorSize - 1) {
				messageVectorString = messageVectorString + " ";
			}
			else {
				messageVectorString = messageVectorString + "<br>";
			}
		}
		messageVectorString = messageVectorString + " </html>";
		messageVector.setText(messageVectorString);
	} 
	
	public void setEncryptedVector(double[][] vector) {
		encryptedVectorString = "<html>";
		for(int i = 0; i < vectorSize; i++) {
			
			for(int j = 0; j < 1; j++) {
				encryptedVectorString = encryptedVectorString + (int)(vector[i][j]) + "  ";
			}
			
			if(i == vectorSize - 1) {
				encryptedVectorString = encryptedVectorString + " ";
			}
			else {
				encryptedVectorString = encryptedVectorString + "<br>";
			}
		}
		encryptedVectorString = encryptedVectorString + " </html>";
		encryptedVector.setText(encryptedVectorString);
	} 
	
	public void setEncryptedVectorDecrypt(double[][] vector) {
		String encryptedVectorStringDecrypt = "<html>";
		for(int i = 0; i < vectorSize; i++) {
			
			for(int j = 0; j < 1; j++) {
				encryptedVectorStringDecrypt = encryptedVectorStringDecrypt + (int)(vector[i][j]) + "  ";
			}
			
			if(i == vectorSize - 1) {
				encryptedVectorStringDecrypt = encryptedVectorStringDecrypt + " ";
			}
			else {
				encryptedVectorStringDecrypt = encryptedVectorStringDecrypt + "<br>";
			}
		}
		encryptedVectorStringDecrypt = encryptedVectorStringDecrypt + " </html>";
		encryptedVectorDecrypt.setText(encryptedVectorStringDecrypt);
	} 
	
	
	public void setOriginal(double[][] vector) {
		String originalString = "<html>";
		for(int i = 0; i < vectorSize; i++) {
			
			for(int j = 0; j < 1; j++) {
				originalString = originalString + (int)(vector[i][j]) + "  ";
			}
			
			if(i == vectorSize - 1) {
				originalString = originalString + " ";
			}
			else {
				originalString = originalString + "<br>";
			}
		}
		originalString = originalString + " </html>";
		originalVectorDecrypt.setText(originalString);
	} 
	
	

	
	public void setEncryptedString(double[][] vector) {
		String newWord = "";
		for(int i = 0; i < vectorSize; i++) {
			
			for(int j = 0; j < 1; j++) {
				char nextLetter = (char)(vector[i][j] + 65);
				newWord = newWord + " " + nextLetter;
			}
		}
		encryptedVectorWordEncrypt.setText(newWord); 
	}
	
	public void setOriginalString(double[][] vector) {
		String newWord = "";
		for(int i = 0; i < vectorSize; i++) {
			
			for(int j = 0; j < 1; j++) {
				char nextLetter = (char)(vector[i][j] + 65);
				newWord = newWord + " " + nextLetter;
			}
		}
		originalVectorWord.setText(newWord); 
	}

	
	public void makeBumpers(boolean encrypt){
		
		leftBumper = "<html>--<br>";
		for(int i = 0; i < vectorSize; i++) {
			leftBumper = leftBumper+ "|<br>";
		}
		
		leftBumper = leftBumper + "--</html>";
		
		rightBumper = "<html>--<br>";
		for(int i = 0; i < vectorSize; i++) {
			rightBumper = rightBumper+ "&nbsp&nbsp&nbsp|<br>";
		}
		
		rightBumper = rightBumper+ "--</html>";	
		
		if(encrypt) {
			keyMatrixLeftBumperEncrypt.setText(leftBumper);
			keyMatrixRightBumperEncrypt.setText(rightBumper);
			messageVectorLeftBumper.setText(leftBumper);
			messageVectorRightBumper.setText(rightBumper);
			encryptedVectorLeftBumperEncrypt.setText(leftBumper);
			encryptedVectorRightBumperEncrypt.setText(rightBumper);
		}
		else {
			keyMatrixLeftBumperDecrypt.setText(leftBumper);
			keyMatrixRightBumperDecrypt.setText(rightBumper);
			originalVectorLeftBumperDecrypt.setText(leftBumper);
			originalVectorRightBumperDecrypt.setText(rightBumper);
			encryptedVectorLeftBumperDecrypt.setText(leftBumper);
			encryptedVectorRightBumperDecrypt.setText(rightBumper);
		}
		
	}

	
	public String generateKey() {
		String generatedKey ="";
		Random r = new Random();
		int low = 0;
		int high = 25;
		vectorSize = messageInput.getText().length();
		
		double[][] tempMat = new double[vectorSize][vectorSize];
		
		HillCipher detTestHC = new HillCipher();
		while(true) {
			for(int i = 0; i < vectorSize; i++) {
				for(int j = 0; j < vectorSize; j++) {
					int result = r.nextInt(high-low) + low;
					char letter = ((char)(result+65));
					generatedKey = generatedKey + letter;
					tempMat[i][j] = result;
				}
			}
			double detOfTest = detTestHC.determinant(tempMat, vectorSize);
			int remainder = (int)detOfTest % 26;
			if (remainder < 0)
			{
				remainder += 26;
			}

			if(remainder == 1) {
				return generatedKey;
			}
			else {
				generatedKey = "";
			}
		}
	}
	
	public void setDeterminant(int det) {
		String detString = "det: " + det;
		
		determinant.setText(detString);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getActionCommand().equals("generateKey")) {
			
			String key = generateKey();
			keyInput.setText(key);

		}
		
		
		HillCipher hc = new HillCipher(keyInput.getText(), messageInput.getText());
		vectorSize = hc.getVectorSize();
		int det = (int)hc.determinant(hc.getKeyMatrix(), vectorSize);
		setDeterminant(det);
		System.out.println(det);
		if(e.getActionCommand().equals("encryptBtn")) { //If the encrypt button is clicked
			
			//HKJNAFXCCQUIKSQWERTYUIOPASDFGHJKLZXCVBNQWERTYUIOPASDFGHJKLZXCVBN
			//ATTACKER
			
			
			makeBumpers(true);
			
			setKeyMatrix(hc.getKeyMatrix());
			setMessageVector(hc.getMessageVector());
			setEncryptedVector(hc.getEncryptedVector());
			setEncryptedString(hc.getEncryptedVector());
			
			
		}
		else if(e.getActionCommand().equals("decryptBtn")) { //If the decrypt button is clicked
			
			
			double[][] decryptKey = hc.getDecryptKey();
			makeBumpers(false);
			

			
			setKeyMatrixDecrypt(hc.getDecryptedKey());
			setEncryptedVectorDecrypt(hc.getEncryptedVector());
			setOriginal(hc.getOriginal());
			setOriginalString(hc.getOriginal());
			
		}

	}
}
