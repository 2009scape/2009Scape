package valkyrion;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.jvnet.substance.skin.SubstanceRavenGraphiteLookAndFeel;

@SuppressWarnings("serial")
public class PackerGUI extends JFrame {
	
	public JPanel contentPane;
	public JTextField input = new JTextField();
	public JTextField cacheDir = new JTextField();
	public JTextField musicId = new JTextField();
	public JLabel lblInput = new JLabel("MIDI location:");
	public JLabel lblCacheDir = new JLabel("Cache location:");
	public JLabel lblMusicId = new JLabel("Music Id (has to be an int, e.g 0 is login music):");
	public JFileChooser filePicker = new JFileChooser();
	public JFileChooser filePicker2 = new JFileChooser();
	public JButton btnFilePick, btnFilePick2, btnPack;
	
	public PackerGUI() {
		setTitle("Music Packer/Replacer");
		setSize(450, 400);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnFilePick = new JButton("Browse");
		btnFilePick.setBounds(330, 50, 90, 25);
		btnFilePick2 = new JButton("Browse");
		btnFilePick2.setBounds(330, 120, 90, 25);
		btnPack = new JButton("Pack to Cache");
		btnPack.setBounds(140, 240, 120, 60);
		input.setBounds(25, 50, 300, 30);
		cacheDir.setBounds(25, 120, 300, 30);
		musicId.setBounds(25, 190, 300, 30);
		lblCacheDir.setBounds(25, 90, 300, 30);
		lblInput.setBounds(25, 15, 300, 30);
		lblMusicId.setBounds(25, 160, 300, 30);
		contentPane.add(lblCacheDir);
		contentPane.add(cacheDir);
		contentPane.add(lblMusicId);
		contentPane.add(lblInput);
		contentPane.add(btnFilePick);
		contentPane.add(btnFilePick2);
		contentPane.add(musicId);
		contentPane.add(btnPack);
		contentPane.add(input);
		filePicker.setFileFilter(new FileNameExtensionFilter("MIDI Files", "mid"));
		
		btnFilePick.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//filePicker.addChoosableFileFilter();
				filePicker.showOpenDialog(null);
				input.setText(filePicker.getSelectedFile().getAbsolutePath());
			}
		});
		btnFilePick2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				filePicker2.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				filePicker2.showOpenDialog(null);
				cacheDir.setText(filePicker2.getSelectedFile().getAbsolutePath());
			}
		});
		
		btnPack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					CachePacker.convertMidi(input.getText().toString(), System.getProperty("user.home") + "/tempout");
					CachePacker.replaceMidi(cacheDir.getText().toString() + "/", Integer.parseInt(musicId.getText().toString()), 0, System.getProperty("user.home") + "/tempout");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	}
	
	public static void main(String[] args) {
		JFrame.setDefaultLookAndFeelDecorated(true);
		JDialog.setDefaultLookAndFeelDecorated(true);

		EventQueue.invokeLater(new Runnable() {

			public void run() {
					try {
						UIManager.setLookAndFeel(new SubstanceRavenGraphiteLookAndFeel());
					} catch (UnsupportedLookAndFeelException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					new PackerGUI().setVisible(true);
			}

		});
	}

}
