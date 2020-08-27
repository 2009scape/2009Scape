package core.gui.tab;


import core.ServerConstants;
import core.gui.ConsoleTab;
import core.gui.component.PlayerViewer;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.*;

/**
 * The player tab.
 *
 * @author Vexia
 */
public class PlayerTab extends ConsoleTab {

    /**
     * The serial UID.
     */
    private static final long serialVersionUID = 8865322670299347942L;

    /**
     * The list of player names to search through.
     */
    private final List<String> playerNames = new ArrayList<>();

    /**
     * The player name text field.
     */
    private JTextField textField;

    /**
     * The default model.
     */
    private final DefaultListModel<String> model = new DefaultListModel<String>();

    /**
     * The tabbed panes.
     */
    private final JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);

    /**
     * The mapping of open player viewers.
     */
    private final Map<String, PlayerViewer> viewers = new HashMap<>();

    /**
     * Constructs a new {@Code PlayerTab} {@Code Object}
     */
    public PlayerTab() {
        super("Players");

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setBounds(6, 43, 260, 539);
        add(scrollPane);

        final JList<String> list = new JList<String>();
        list.addListSelectionListener(e -> {
            if (list.getSelectedValue() != null) {
                openViewer(list.getSelectedValue());
            }
        });
        list.setModel(model);
        scrollPane.setViewportView(list);

        JLabel lblSearchForPlayer = new JLabel("Search for player:");
        lblSearchForPlayer.setBounds(6, 15, 143, 16);
        add(lblSearchForPlayer);

        textField = new JTextField();
        textField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                model.clear();
                if (textField.getText() == null || textField.getText().length() < 1) {
                	populatePlayerSearch();
                    return;
                }
                for (String name : playerNames) {
                    if (name.toLowerCase().contains(textField.getText())) {
                        model.addElement(name);
                    }
                }
            }
        });
        textField.setBounds(117, 9, 149, 28);
        add(textField);
        textField.setColumns(10);

        tabbedPane.setBounds(278, 43, 755, 539);
        add(tabbedPane);

        JButton btnRepopulate = new JButton("Repopulate");
        btnRepopulate.addActionListener(e -> populatePlayerSearch());
        btnRepopulate.setBounds(278, 10, 117, 29);
        add(btnRepopulate);
        populatePlayerSearch();
    }

    /**
     * Opens a viewer for a player.
     *
     * @param name the name.
     * @return {@code True} if opened.
     */
    public boolean openViewer(String name) {
        if (viewers.containsKey(name)) {
            return false;
        }
        PlayerViewer viewer = new PlayerViewer(name);
        viewers.put(name, viewer);
        tabbedPane.add(viewer.getName(), viewer);
        return true;
    }

    /**
     * Closes a viewer.
     *
     * @param viewer the viewer.
     * @return {@code True} if closed.
     */
    public boolean closeViewer(PlayerViewer viewer) {
        if (!viewers.containsValue(viewer)) {
            JOptionPane.showMessageDialog(null, "Error! Viewer wasn't open.");
            return false;
        }
        viewers.remove(viewer.getName());
        tabbedPane.remove(viewer);
        return true;
    }

    /**
     * Populates the searchable names.
     */
    public void populatePlayerSearch() {
        playerNames.clear();
        model.clear();
        File f = new File(ServerConstants.PLAYER_SAVE_PATH);
        if (f.listFiles() == null) {
            System.out.println("Player directory was null!");
            return;
        }
        for (File file : Objects.requireNonNull(f.listFiles())) {
        	String fileName = file.getName();
        	if (!fileName.contains(".save")) continue;
        	String playerName = fileName.replace(".save", "").trim();
            playerNames.add(playerName);
            model.addElement(playerName);
        }
    }

    /**
     * Adds a searchable name to the list.
     *
     * @param name the name.
     */
    public void addSearchableName(String name) {
        playerNames.add(name);
    }

    /**
     * Gets the playerNames.
     *
     * @return the playerNames
     */
    public List<String> getPlayerNames() {
        return playerNames;
    }

    /**
     * Gets the viewers.
     *
     * @return the viewers
     */
    public Map<String, PlayerViewer> getViewers() {
        return viewers;
    }
}
