package org.arios.workspace;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.arios.workspace.editor.EditorTab;
import org.arios.workspace.editor.EditorType;

/**
 * The working frame.
 * @author Vexia
 *
 */
public class WorkFrame extends JFrame implements ActionListener {
 
	/**
	 * The serail UID.
	 */
	private static final long serialVersionUID = 1669005276685828247L;
	
	/**
	 * The cache path field.
	 */
	private JTextField cachePath;
	
	/**
	 * The store path field.
	 */
	private JTextField storePath;
	
	/**
	 * The logging console.
	 */
	private JTextArea console = new JTextArea();
	
	/**
	 * The opened editor tabs.
	 */
	private JTabbedPane editorTabs = new JTabbedPane(JTabbedPane.TOP);
	
	/**
	 * The list of opened editors.
	 */
	private List<EditorType> editors = new ArrayList<>();
	
	/**
	 * The button to open the cache editor.
	 */
	private JButton btnCacheEditor = new JButton("Cache Editor");
	
	/**
	 * The button to open the item editor.
	 */
	private JButton btnItemEditor = new JButton("Item Editor");
	
	/**
	 * The button to open the npc editor.
	 */
	private JButton btnNpcEditor = new JButton("NPC Editor");
	
	/**
	 * Constructs a new {@code MainFrame} {@code Object}
	 */
	public WorkFrame() {
		super("Arios Editor - " + WorkSettings.VERSION);
		setLocationRelativeTo(null);
		setSize(WorkSettings.SIZE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 10, 1264, 143);
		panel.setBorder(new TitledBorder(null, "Workspace Tools", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblCachePath = new JLabel("Cache path:");
		lblCachePath.setBounds(10, 23, 95, 22);
		panel.add(lblCachePath);
		
		cachePath = new JTextField(WorkSpace.getWorkSpace().getSettings().getCachePath());
		cachePath.setBounds(93, 20, 361, 28);
		panel.add(cachePath);
		cachePath.setColumns(10);
		
		JLabel lblStorePath = new JLabel("Store path:");
		lblStorePath.setBounds(10, 54, 95, 22);
		panel.add(lblStorePath);
		
		storePath = new JTextField(WorkSpace.getWorkSpace().getSettings().getStorePath());
		storePath.setBounds(93, 51, 361, 28);
		storePath.setColumns(10);
		panel.add(storePath);
		
		JButton btnReplaceServerCache = new JButton("Replace Server Cache");
		btnReplaceServerCache.setBounds(6, 78, 168, 29);
		panel.add(btnReplaceServerCache);
		btnReplaceServerCache.addActionListener(this);
		
		JButton btnNewButton = new JButton("Save");
		btnNewButton.setBounds(170, 78, 164, 29);
		panel.add(btnNewButton);
		btnNewButton.addActionListener(this);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		scrollPane.setBounds(458, 10, 800, 127);
		panel.add(scrollPane);
		console.setLineWrap(true);
		
		console.setBackground(UIManager.getColor("Button.background"));
		console.setEditable(false);
		console.setWrapStyleWord(true);
		scrollPane.setViewportView(console);
		console.setAutoscrolls(true);
		
		
		btnNpcEditor.setBounds(10, 108, 164, 29);
		panel.add(btnNpcEditor);
		btnNpcEditor.addActionListener(this);
		
		btnItemEditor.setBounds(170, 108, 168, 29);
		panel.add(btnItemEditor);
		btnItemEditor.addActionListener(this);
		

		btnCacheEditor.setBounds(332, 108, 117, 29);
		panel.add(btnCacheEditor);
		
		JButton btnShopEditor = new JButton("Shop Editor");
		btnShopEditor.addActionListener(this);
		btnShopEditor.setBounds(332, 78, 117, 29);
		panel.add(btnShopEditor);
		editorTabs.setBounds(0, 149, 1280, 521);
		btnCacheEditor.addActionListener(this);
		
		editorTabs.setBorder(new TitledBorder(null, "Editors", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		editorTabs.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				if (editorTabs.getSelectedIndex() == -1) {
					return;
				}
				EditorTab editor = (EditorTab) editorTabs.getSelectedComponent();
				WorkSpace.getWorkSpace().setEditor(editor);
			}
			
		});
		getContentPane().add(editorTabs);
		setResizable(false);
		setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "NPC Editor":
		case "Item Editor":
		case "Cache Editor":
		case "Shop Editor":
			openEditor(EditorType.forName(e.getActionCommand()));
			break;
		case "Close NPC Editor":
		case "Close Item Editor":
		case "Close Cache Editor":
			closeEditor(EditorType.forName(e.getActionCommand()));
			break;
		case "Replace Server Store":
		case "Replace Server Cache":
			JFileChooser chooser = new JFileChooser();
			chooser.setCurrentDirectory(new java.io.File(System.getProperty("user.home") + "/Dropbox/Arios/Source/data"));
			chooser.setDialogTitle("Choose replace directory");
			chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			chooser.setAcceptAllFileFilterUsed(false);
			if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			String from = e.getActionCommand().equals("Replace Server Store") ? WorkSpace.getWorkSpace().getSettings().getStorePath() : WorkSpace.getWorkSpace().getSettings().getCachePath();
			  WorkSpace.getWorkSpace().replaceCache(from,chooser.getSelectedFile().getAbsolutePath());
			} else {
			  System.out.println("No directory choosen.");
			}
			break;
		case "Save":
			WorkSpace.getWorkSpace().save();
			break;
		}
	}
	
	/**
	 * Opens an editor tab.
 	 * @param type the type.
	 * @return {@code True} if so.
 	 */
	public boolean openEditor(EditorType type) {
		if (editors.contains(type)) {
			System.out.println("The " + type.getTab().getName() + " is already in view.");
			return false;
		}
		JButton button = getButtonByEditor(type);
		button.setText("Close " + type.getTab().getName());
		editors.add(type);
		editorTabs.add(type.getTab());
		return type.getTab().init();
	}
	
	/**
	 * Closes an editor.
 	 * @param type the type.
	 * @return {@code True} if so.
	 */
	public boolean closeEditor(EditorType type) {
		if (!editors.contains(type)) {
			System.out.println("The " + type.getTab().getName() + " isn't in view.");
			return false;
		}
		JButton button = getButtonByEditor(type);
		button.setText(type.getTab().getName());
		editorTabs.remove(type.getTab());
		return editors.remove(type);
	}
	
	/**
	 * Gets the jbutton by the editor.
	 * @param type the type.
	 * @return the jbutton.
	 */
	public JButton getButtonByEditor(EditorType type) {
		JButton button = btnNpcEditor;
		if (type == EditorType.ITEM) {
			button = btnItemEditor;
		}
		return button;
	}
	
	/**
	 * Logs a message on the console.
	 * @param message the message.
	 */
	public void log(String message) {
		console.append(message + "\n");
	}

	/**
	 * Gets the editorTabs.
	 * @return the editorTabs.
	 */
	public JTabbedPane getEditorTabs() {
		return editorTabs;
	}

	/**
	 * Sets the editorTabs.
	 * @param editorTabs the editorTabs to set
	 */
	public void setEditorTabs(JTabbedPane editorTabs) {
		this.editorTabs = editorTabs;
	}

	/**
	 * Gets the editors.
	 * @return the editors.
	 */
	public List<EditorType> getEditors() {
		return editors;
	}

	/**
	 * Sets the editors.
	 * @param editors the editors to set
	 */
	public void setEditors(List<EditorType> editors) {
		this.editors = editors;
	}
}
