package com.alex.tools.itemsDefsEditor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class GeneratedUkeys extends JDialog {


	public GeneratedUkeys(JFrame frame, byte[] ukeys) {
		super(frame, "Ukeys", true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		
		final JEditorPane editorPane = new JEditorPane();
		editorPane.setText(Arrays.toString(ukeys));
		editorPane.setBounds(6, 6, 420, 213);
		getContentPane().add(editorPane);
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnClose.setBounds(101, 221, 90, 28);
		getContentPane().add(btnClose);
		
		JButton btnCopy = new JButton("Copy");
		btnCopy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ActionEvent nev = new ActionEvent(editorPane, ActionEvent.ACTION_PERFORMED, "copy"); 
				editorPane.selectAll(); 
				editorPane.getActionMap().get(nev.getActionCommand()).actionPerformed(nev); 
			}
		});
		btnCopy.setBounds(6, 221, 90, 28);
		getContentPane().add(btnCopy);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setVisible(true);
	}
}
