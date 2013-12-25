// Main Algorithms run in this class

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;

public class GenerateMeAPass extends JFrame {

	private JPanel contentPane;
	JCheckBox chckbxLetters;
	JCheckBox chckbxNumbers;
	JCheckBox chckbxSpecialCharacters;
	JSpinner spinner;
	JTextArea txtrAsdasda;
	static Generator g;
	String globalPass = "";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		g = new Generator();
		EventQueue.invokeLater(new Runnable() {
			public void run() {

				try {
					GenerateMeAPass frame = new GenerateMeAPass();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GenerateMeAPass() {
		setTitle("Generate Me A Pass");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 414, 349);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		chckbxLetters = new JCheckBox("Letters");
		chckbxLetters.setBackground(Color.ORANGE);
		chckbxLetters.setFont(new Font("Calibri", Font.BOLD, 14));
		chckbxLetters.setBounds(24, 36, 97, 23);
		contentPane.add(chckbxLetters);

		chckbxNumbers = new JCheckBox("Numbers");
		chckbxNumbers.setBackground(Color.ORANGE);
		chckbxNumbers.setFont(new Font("Calibri", Font.BOLD, 14));
		chckbxNumbers.setBounds(24, 62, 97, 23);
		contentPane.add(chckbxNumbers);

		chckbxSpecialCharacters = new JCheckBox("Special Characters");
		chckbxSpecialCharacters.setBackground(Color.ORANGE);
		chckbxSpecialCharacters.setFont(new Font("Calibri", Font.BOLD, 14));
		chckbxSpecialCharacters.setBounds(24, 88, 147, 23);
		contentPane.add(chckbxSpecialCharacters);

		spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(4, 2, 50, 1));
		spinner.setFont(new Font("Calibri", Font.BOLD, 14));
		spinner.setBounds(321, 37, 67, 20);
		contentPane.add(spinner);

		JLabel lblPasswordLength = new JLabel("Password Length:");
		lblPasswordLength.setFont(new Font("Calibri", Font.PLAIN, 12));
		lblPasswordLength.setForeground(Color.WHITE);
		lblPasswordLength.setBackground(Color.WHITE);
		lblPasswordLength.setBounds(214, 37, 97, 23);
		contentPane.add(lblPasswordLength);

		JButton btnGenerate = new JButton("Generate");
		btnGenerate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int mode = 0;
				String password = "NaN";
				if (!chckbxLetters.isSelected() && !chckbxNumbers.isSelected()
						&& !chckbxSpecialCharacters.isSelected()) {
					JOptionPane
							.showMessageDialog(null,
									"You should select at least one password characteristic !");
				} else {
					// 0 -> numbers only, 1->
					// letters only,
					// 2->special chars only
					// 3-> number+letter,
					// 4-> number+special
					// chars,
					// 5->letter+special
					// chars
					// 6-> all valid, others
					// not accepted !
					if ((chckbxNumbers.isSelected())
							&& (!chckbxLetters.isSelected() && !chckbxSpecialCharacters
									.isSelected())) {
						mode = 0;
					} else if ((chckbxLetters.isSelected())
							&& (!chckbxNumbers.isSelected() && !chckbxSpecialCharacters
									.isSelected())) {
						mode = 1;
					} else if ((!chckbxLetters.isSelected() && !chckbxNumbers
							.isSelected())
							&& chckbxSpecialCharacters.isSelected()) {
						mode = 2;
					} else if ((chckbxLetters.isSelected() && chckbxNumbers
							.isSelected())
							&& !chckbxSpecialCharacters.isSelected()) {
						mode = 3;
					} else if (!chckbxLetters.isSelected()
							&& (chckbxNumbers.isSelected() && chckbxSpecialCharacters
									.isSelected())) {
						mode = 4;
					} else if ((chckbxLetters.isSelected() && chckbxSpecialCharacters
							.isSelected()) && !chckbxNumbers.isSelected()) {
						mode = 5;
					} else {
						mode = 6;
					}
					password = g.generateAPassword(
							(Integer) spinner.getValue(), mode);
				}
				globalPass = password;
				txtrAsdasda.setText(password);
			}
		});
		btnGenerate.setBounds(214, 86, 174, 25);
		contentPane.add(btnGenerate);

		txtrAsdasda = new JTextArea();
		txtrAsdasda.setEditable(false);
		txtrAsdasda.setBounds(24, 154, 364, 64);
		contentPane.add(txtrAsdasda);

		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String fileName = JOptionPane
						.showInputDialog("Enter the name of the file");
				fileName += ".txt";

				File file = new File(fileName);

				FileWriter fw;
				try {
					fw = new FileWriter(file.getAbsoluteFile());
					BufferedWriter bw = new BufferedWriter(fw);
					bw.write(globalPass);
					bw.close();
					JOptionPane
							.showMessageDialog(null, "Password file saved !");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "An error occured !");
					e1.printStackTrace();
				}

			}
		});
		btnSave.setBounds(22, 244, 99, 25);
		contentPane.add(btnSave);

		JButton btnSendAsEmail = new JButton("Send via E-mail");
		btnSendAsEmail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String passInfo = JOptionPane
						.showInputDialog("Enter the explanation for this password :");
				String mailaddressTo = JOptionPane
						.showInputDialog("Enter your email address : ");

				final String username = "mypwd.remainder@gmail.com";
				final String password = "o6L690pw";

				Properties props = new Properties();
				props.put("mail.smtp.auth", "true");
				props.put("mail.smtp.starttls.enable", "true");
				props.put("mail.smtp.host", "smtp.gmail.com");
				props.put("mail.smtp.port", "587");

				Session session = Session.getInstance(props,
						new javax.mail.Authenticator() {
							protected PasswordAuthentication getPasswordAuthentication() {
								return new PasswordAuthentication(username,
										password);
							}
						});

				String msg = "This password is created with - Generate Me A Pass -"
						+ "\n\n";
				msg += globalPass;

				try {

					Message message = new MimeMessage(session);
					message.setFrom(new InternetAddress(
							"mypwd.remainder@gmail.com"));
					message.setRecipients(Message.RecipientType.TO,
							InternetAddress.parse(mailaddressTo));
					message.setSubject(passInfo);
					message.setText(msg);

					Transport.send(message);

					//System.out.println("Done");

				} catch (MessagingException e1) {
					throw new RuntimeException(e1);
				}

				JOptionPane.showMessageDialog(null,
						"Your password has been sent successfully !");
			}
		});
		btnSendAsEmail.setBounds(133, 244, 144, 25);
		contentPane.add(btnSendAsEmail);

		JButton btnCopyToClipboard = new JButton("Copy");
		btnCopyToClipboard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// globalpass
				StringSelection stringSelection = new StringSelection(
						globalPass);
				Clipboard clpbrd = Toolkit.getDefaultToolkit()
						.getSystemClipboard();
				clpbrd.setContents(stringSelection, null);
				JOptionPane.showMessageDialog(null,
						"Password copied to the Clipboard !");
			}
		});
		btnCopyToClipboard.setBounds(289, 244, 99, 25);
		contentPane.add(btnCopyToClipboard);
		
		JLabel lblVBurak = new JLabel("v 0.0.1 Burak Yildirim");
		lblVBurak.setForeground(Color.WHITE);
		lblVBurak.setBounds(262, 296, 126, 14);
		contentPane.add(lblVBurak);
	}
}
