package com.alex.tools.itemsDefsEditor;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.alex.loaders.items.ItemDefinitions;

@SuppressWarnings("serial")
public class ItemDefsEditor extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private ItemDefinitions defs;
	private Application application;
	private JTextField modelIDField;
	private JTextField nameField;
	private JTextField modelZoomField;
	private JTextField groundOptionsField;
	private JTextField inventoryOptionsField;
	private JTextField femaleModelId2Field;
	private JTextField maleModelId1Field;
	private JTextField maleModelId2Field;
	private JTextField maleModelId3Field;
	private JTextField femaleModelId1Field;
	private JTextField femaleModelId3Field;
	private JTextField teamIdField;
	private JTextField notedItemIdField;
	private JTextField switchNotedItemField;
	private JTextField lendedItemIdField;
	private JTextField switchLendedItemField;
	private JTextField changedModelColorsField;
	private JTextField changedTextureColorsField;
	private JCheckBox membersOnlyCheck;
	
	public void save() {
		
		//inv
		defs.setInvModelId(Integer.valueOf(modelIDField.getText()));
		defs.setName(nameField.getText());
		defs.setInvModelZoom(Integer.valueOf(modelZoomField.getText()));
		String[] groundOptions = groundOptionsField.getText().split(";");
		for(int i = 0; i < defs.getGroundOptions().length; i++)
			defs.getGroundOptions()[i] = groundOptions[i].equals("null") ? null : groundOptions[i];
		String[] invOptions = inventoryOptionsField.getText().split(";");
		for(int i = 0; i < defs.getInventoryOptions().length; i++)
			defs.getInventoryOptions()[i] = invOptions[i].equals("null") ? null : invOptions[i];
		
		//wearing
		
		defs.maleEquipModelId1 = Integer.valueOf(maleModelId1Field.getText());
		defs.maleEquipModelId2 = Integer.valueOf(maleModelId2Field.getText());
		defs.maleEquipModelId3 = Integer.valueOf(maleModelId3Field.getText());
		
		defs.femaleEquipModelId1 = Integer.valueOf(femaleModelId1Field.getText());
		defs.femaleEquipModelId2 = Integer.valueOf(femaleModelId2Field.getText());
		defs.femaleEquipModelId3 = Integer.valueOf(femaleModelId3Field.getText());
		defs.teamId = Integer.valueOf(teamIdField.getText());
		
		//others
		defs.notedItemId = Integer.valueOf(notedItemIdField.getText());
		defs.switchNoteItemId = Integer.valueOf(switchNotedItemField.getText());
		defs.lendedItemId = Integer.valueOf(lendedItemIdField.getText());
		defs.switchLendItemId = Integer.valueOf(switchLendedItemField.getText());
		defs.resetModelColors();
		if(!changedModelColorsField.getText().equals("")) {
			String[] splitedModelColorsTexts = changedModelColorsField.getText().split(";");
			for(String t : splitedModelColorsTexts) {
				String[] editedColor = t.split("=");
				defs.changeModelColor(Integer.valueOf(editedColor[0]), Integer.valueOf(editedColor[1]));
			}
		}
		defs.resetTextureColors();
		if(!changedTextureColorsField.getText().equals("")) {
			String[] splitedTextureColorsTexts = changedTextureColorsField.getText().split(";");
			for(String t : splitedTextureColorsTexts) {
				String[] editedColor = t.split("=");
				defs.changeTextureColor(Integer.valueOf(editedColor[0]), Integer.valueOf(editedColor[1]));
			}
		}
		defs.membersOnly = membersOnlyCheck.isSelected();
		defs.write(Application.STORE);
		application.updateItemDefs(defs);
	}
	
	/**
	 * Create the dialog.
	 */
	public ItemDefsEditor(Application application, ItemDefinitions defs) {
		super(application.getFrame(), "Item Definitions Editor", true);
		this.defs = defs;
		this.application = application;
		setBounds(100, 100, 912, 354);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Model ID:");
		lblNewLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		lblNewLabel.setBounds(6, 43, 81, 21);
		contentPanel.add(lblNewLabel);
		{
			modelIDField = new JTextField();
			modelIDField.setBounds(139, 40, 122, 28);
			contentPanel.add(modelIDField);
			modelIDField.setColumns(10);
			modelIDField.setText(""+defs.getInvModelId());
		}
		{
			JLabel label = new JLabel("Name:");
			label.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
			label.setBounds(6, 76, 81, 21);
			contentPanel.add(label);
		}
		{
			nameField = new JTextField();
			nameField.setBounds(139, 73, 122, 28);
			contentPanel.add(nameField);
			nameField.setColumns(10);
			nameField.setText(defs.getName());
		}
		{
			JLabel label = new JLabel("Model Zoom:");
			label.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
			label.setBounds(6, 109, 95, 21);
			contentPanel.add(label);
		}
		{
			modelZoomField = new JTextField();
			modelZoomField.setBounds(139, 106, 122, 28);
			contentPanel.add(modelZoomField);
			modelZoomField.setColumns(10);
			modelZoomField.setText(""+defs.getInvModelZoom());
		}
		{
			JLabel label = new JLabel("Ground Options:");
			label.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
			label.setBounds(6, 142, 108, 21);
			contentPanel.add(label);
		}
		{
			groundOptionsField = new JTextField();
			groundOptionsField.setBounds(139, 139, 122, 28);
			contentPanel.add(groundOptionsField);
			groundOptionsField.setColumns(10);
			String text = "";
			for(String option : defs.getGroundOptions())
				text += (option == null ? "null" : option)+";";
			groundOptionsField.setText(text);
		}
		{
			JLabel label = new JLabel("Inventory Options:");
			label.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
			label.setBounds(6, 175, 139, 21);
			contentPanel.add(label);
		}
		{
			inventoryOptionsField = new JTextField();
			inventoryOptionsField.setBounds(139, 172, 122, 28);
			contentPanel.add(inventoryOptionsField);
			inventoryOptionsField.setColumns(10);
			String text = "";
			for(String option : defs.getInventoryOptions())
				text += (option == null ? "null" : option)+";";
					inventoryOptionsField.setText(text);
		}
		{
			JButton saveButton = new JButton("Save");
			saveButton.setBounds(6, 265, 55, 28);
			contentPanel.add(saveButton);
			saveButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					save();
					dispose();
				}
			});
			getRootPane().setDefaultButton(saveButton);
		}
		{
			JButton cancelButton = new JButton("Cancel");
			cancelButton.setBounds(73, 265, 67, 28);
			contentPanel.add(cancelButton);
			cancelButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			cancelButton.setActionCommand("Cancel");
		}
		
		JLabel label = new JLabel("Interface / Droped");
		label.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
		label.setBounds(6, 6, 205, 21);
		contentPanel.add(label);
		
		JLabel label_1 = new JLabel("Wearing");
		label_1.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
		label_1.setBounds(273, 6, 205, 21);
		contentPanel.add(label_1);
		
		JLabel label_2 = new JLabel("Male Model ID 1:");
		label_2.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		label_2.setBounds(273, 43, 131, 21);
		contentPanel.add(label_2);
		
		JLabel label_3 = new JLabel("Male Model ID 2:");
		label_3.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		label_3.setBounds(273, 76, 131, 21);
		contentPanel.add(label_3);
		
		JLabel label_4 = new JLabel("Male Model ID 3:");
		label_4.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		label_4.setBounds(273, 112, 131, 21);
		contentPanel.add(label_4);
		
		JLabel label_5 = new JLabel("Female Model ID 1:");
		label_5.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		label_5.setBounds(273, 145, 131, 21);
		contentPanel.add(label_5);
		
		JLabel label_6 = new JLabel("Female Model ID 2:");
		label_6.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		label_6.setBounds(273, 175, 131, 21);
		contentPanel.add(label_6);
		
		JLabel label_7 = new JLabel("Female Model ID 3:");
		label_7.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		label_7.setBounds(273, 208, 131, 21);
		contentPanel.add(label_7);
		
		femaleModelId2Field = new JTextField();
		femaleModelId2Field.setBounds(411, 172, 122, 28);
		contentPanel.add(femaleModelId2Field);
		femaleModelId2Field.setColumns(10);
		femaleModelId2Field.setText(""+defs.femaleEquipModelId2);
		
		maleModelId1Field = new JTextField();
		maleModelId1Field.setBounds(411, 40, 122, 28);
		contentPanel.add(maleModelId1Field);
		maleModelId1Field.setColumns(10);
		maleModelId1Field.setText(""+defs.maleEquipModelId1);
		{
			maleModelId2Field = new JTextField();
			maleModelId2Field.setBounds(411, 73, 122, 28);
			contentPanel.add(maleModelId2Field);
			maleModelId2Field.setColumns(10);
			maleModelId2Field.setText(""+defs.maleEquipModelId2);
		}
		{
			maleModelId3Field = new JTextField();
			maleModelId3Field.setBounds(411, 106, 122, 28);
			contentPanel.add(maleModelId3Field);
			maleModelId3Field.setColumns(10);
			maleModelId3Field.setText(""+defs.maleEquipModelId3);
		}
		{
			femaleModelId1Field = new JTextField();
			femaleModelId1Field.setBounds(411, 139, 122, 28);
			contentPanel.add(femaleModelId1Field);
			femaleModelId1Field.setColumns(10);
			femaleModelId1Field.setText(""+defs.femaleEquipModelId1);
		}
		{
			femaleModelId3Field = new JTextField();
			femaleModelId3Field.setBounds(411, 205, 122, 28);
			contentPanel.add(femaleModelId3Field);
			femaleModelId3Field.setColumns(10);
			femaleModelId3Field.setText(""+defs.femaleEquipModelId3);
		}
		{
			JLabel label_8 = new JLabel("Team ID:");
			label_8.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
			label_8.setBounds(273, 241, 131, 21);
			contentPanel.add(label_8);
		}
		{
			teamIdField = new JTextField();
			teamIdField.setBounds(411, 238, 122, 28);
			contentPanel.add(teamIdField);
			teamIdField.setColumns(10);
			teamIdField.setText(""+defs.teamId);
		}
		{
			JLabel label_8 = new JLabel("Others");
			label_8.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
			label_8.setBounds(539, 6, 205, 21);
			contentPanel.add(label_8);
		}
		{
			JLabel label_8 = new JLabel("Noted Item ID:");
			label_8.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
			label_8.setBounds(545, 43, 131, 21);
			contentPanel.add(label_8);
		}
		{
			JLabel label_8 = new JLabel("Switch Noted Item Id:");
			label_8.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
			label_8.setBounds(545, 76, 160, 21);
			contentPanel.add(label_8);
		}
		{
			JLabel label_8 = new JLabel("Lended Item ID:");
			label_8.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
			label_8.setBounds(545, 109, 160, 21);
			contentPanel.add(label_8);
		}
		{
			JLabel label_8 = new JLabel("Switch Lended Item Id:");
			label_8.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
			label_8.setBounds(545, 145, 160, 21);
			contentPanel.add(label_8);
		}
		{
			notedItemIdField = new JTextField();
			notedItemIdField.setBounds(707, 39, 122, 28);
			contentPanel.add(notedItemIdField);
			notedItemIdField.setColumns(10);
			notedItemIdField.setText(""+defs.notedItemId);
		}
		{
			switchNotedItemField = new JTextField();
			switchNotedItemField.setBounds(707, 73, 122, 28);
			contentPanel.add(switchNotedItemField);
			switchNotedItemField.setColumns(10);
			switchNotedItemField.setText(""+defs.switchNoteItemId);
		}
		{
			lendedItemIdField = new JTextField();
			lendedItemIdField.setBounds(707, 106, 122, 28);
			contentPanel.add(lendedItemIdField);
			lendedItemIdField.setColumns(10);
			lendedItemIdField.setText(""+defs.lendedItemId);
		}
		{
			switchLendedItemField = new JTextField();
			switchLendedItemField.setBounds(707, 139, 122, 28);
			contentPanel.add(switchLendedItemField);
			switchLendedItemField.setColumns(10);
			switchLendedItemField.setText(""+defs.switchLendItemId);
		}
		{
			JLabel label_8 = new JLabel("Changed Model Colors:");
			label_8.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
			label_8.setBounds(545, 175, 160, 21);
			contentPanel.add(label_8);
		}
		{
			changedModelColorsField = new JTextField();
			changedModelColorsField.setBounds(707, 172, 122, 28);
			contentPanel.add(changedModelColorsField);
			changedModelColorsField.setColumns(10);
			String text = "";
			if(defs.originalModelColors != null) {
				for(int i = 0; i < defs.originalModelColors.length; i++) {
					text += defs.originalModelColors[i]+"="+defs.modifiedModelColors[i]+";";
				}
			}
			changedModelColorsField.setText(text);
		}
		{
			JLabel label_8 = new JLabel("Changed Texture Colors:");
			label_8.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
			label_8.setBounds(545, 205, 160, 21);
			contentPanel.add(label_8);
		}
		{
			changedTextureColorsField = new JTextField();
			changedTextureColorsField.setBounds(707, 205, 122, 28);
			contentPanel.add(changedTextureColorsField);
			changedTextureColorsField.setColumns(10);
			String text = "";
			if(defs.originalTextureColors != null) {
				for(int i = 0; i < defs.originalTextureColors.length; i++) {
					text += defs.originalTextureColors[i]+"="+defs.modifiedTextureColors[i]+";";
				}
			}
			changedTextureColorsField.setText(text);
		}
		
		membersOnlyCheck = new JCheckBox("Members Only");
		membersOnlyCheck.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		membersOnlyCheck.setBounds(545, 243, 131, 18);
		membersOnlyCheck.setSelected(defs.membersOnly);
		contentPanel.add(membersOnlyCheck);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
		}
		
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setVisible(true);
	}
}
