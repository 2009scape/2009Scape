package core.gui.tab;


import plugin.ge.GEOfferDispatch;
import core.game.node.entity.player.Player;
import core.gui.ConsoleFrame;
import core.gui.ConsoleTab;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A tab used to view the grand exchange.
 *
 * @author Vexia
 */
public class GrandExchangeTab extends ConsoleTab {

    /**
     * The serial UID.
     */
    private static final long serialVersionUID = -1619650762760289798L;

    /**
     * The search field.
     */
    private JTextField searchField;

    /**
     * The jlist.
     */
    private final JList<GrandExchangeOffer> list = new JList<GrandExchangeOffer>();

    /**
     * The model.
     */
    private final DefaultListModel<GrandExchangeOffer> model = new DefaultListModel<GrandExchangeOffer>();

    /**
     * The id search field.
     */
    private JTextField idSearchField;

    /**
     * Constructs a new {@Code GrandExchangeTab} {@Code Object}
     */
    @SuppressWarnings("serial")
    public GrandExchangeTab() {
        super("Grand Exchange");
        list.setCellRenderer(new DefaultListCellRenderer() {

            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                GrandExchangeOffer offer = (GrandExchangeOffer) value;
                if (offer == null) {
                    return label;
                }
                Player player = null;
                for (Player p : ConsoleFrame.getInstance().getUtilityTab().getPlayers()) {
                    if (p == null) {
                        continue;
                    }
                    if (p.getDetails().getUid() == offer.getUid()) {
                        player = p;
                        break;
                    }
                }
                label.setText((player == null ? "" : player.getName() + " - ") + offer.toString());
                return label;
            }

        });
        list.setModel(model);
        JLabel lblSearch = new JLabel("Search player:");
        lblSearch.setBounds(24, 20, 95, 16);
        add(lblSearch);

        searchField = new JTextField();
        searchField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                searchPlayer();
            }
        });
        searchField.setBounds(119, 14, 185, 28);
        add(searchField);
        searchField.setColumns(10);

        JPanel panel = new JPanel();
        panel.setBounds(24, 78, 1015, 518);
        add(panel);
        panel.setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(6, 6, 375, 517);
        panel.add(scrollPane);
        scrollPane.setViewportView(list);

        JButton btnDisplayDatabase = new JButton("Display Database");
        btnDisplayDatabase.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                displayDatabase();
            }
        });
        btnDisplayDatabase.setBounds(316, 15, 173, 29);
        add(btnDisplayDatabase);

        JLabel lblNewLabel = new JLabel("Search id:");
        lblNewLabel.setBounds(24, 48, 61, 16);
        add(lblNewLabel);

        idSearchField = new JTextField();
        idSearchField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                searchId();
            }
        });
        idSearchField.setColumns(10);
        idSearchField.setBounds(119, 48, 185, 28);
        add(idSearchField);

        JButton btnNewButton = new JButton("Load");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ConsoleFrame.getInstance().getUtilityTab().populateList();
            }
        });
        btnNewButton.setBounds(316, 43, 173, 29);
        add(btnNewButton);
    }

    /**
     * Searches the player.
     */
    private void searchPlayer() {
        model.clear();
        Player player = ConsoleFrame.getInstance().getUtilityTab().getPlayer(searchField.getText());
        if (player == null) {
            JOptionPane.showMessageDialog(null, "Error! No player found.");
            return;
        }
        for (GrandExchangeOffer o : player.getGrandExchange().getOffers()) {
            if (o == null) {
                continue;
            }
            addOffer(o);
        }
    }

    /**
     * Searches the id.
     */
    private void searchId() {
        model.clear();
        int itemId = 0;
        try {
            itemId = Integer.parseInt(idSearchField.getText());
        } catch (NumberFormatException e) {

        }
        for (GrandExchangeOffer o : GEOfferDispatch.getOfferMapping().values()) {
            if (o == null) {
                continue;
            }
            if (o.getItemId() == itemId) {
                addOffer(o);
            }
        }
    }

    /**
     * Adds a g.e offer.
     */
    private void addOffer(GrandExchangeOffer offer) {
        model.addElement(offer);
    }

    /**
     * Displays a database.
     */
    private void displayDatabase() {
        if (ConsoleFrame.getInstance().getUtilityTab().getPlayers().size() == 0) {
            JOptionPane.showMessageDialog(null, "Error! No data in DB yet. Press load.");
            return;
        }
        for (GrandExchangeOffer offer : GEOfferDispatch.getOfferMapping().values()) {
            model.addElement(offer);
        }
    }
}
