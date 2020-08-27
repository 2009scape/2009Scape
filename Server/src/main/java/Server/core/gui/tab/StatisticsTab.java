package core.gui.tab;


import core.Server;
import core.ServerConstants;
import core.game.node.item.GroundItemManager;
import core.game.system.SystemManager;
import core.game.system.SystemState;
import core.game.world.GameWorld;
import core.game.world.map.RegionManager;
import core.game.world.repository.Repository;
import core.gui.ConsoleTab;
import core.plugin.PluginManager;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.net.URL;

/**
 * Handles server info tab.
 *
 * @author Emperor
 */
public class StatisticsTab extends ConsoleTab {

    /**
     * The serial UID.
     */
    private static final long serialVersionUID = 6164020580271944550L;

    /**
     * The queue size.
     */
    public static final int QUEUE_SIZE = 1 << 16;

    /**
     * The performance data queue.
     */
    private static short[] performanceQueue = new short[QUEUE_SIZE];

    /**
     * The memory data queue.
     */
    private static short[] memoryQueue = new short[QUEUE_SIZE];

    /**
     * The current index of the performance queue.
     */
    private static int queueIndex = 0;

    /**
     * The current statistics zoom.
     */
    private static int statisticsZoom = 5;

    /**
     * The singleton.
     */
    public static final StatisticsTab INSTANCE = new StatisticsTab();

    /**
     * The statistics mouse coordinates.
     */
    private Point statisticMousePoint = null;

    /**
     * The zoom in button.
     */
    private JButton zoomIn;

    /**
     * The zoom out button.
     */
    private JButton zoomOut;

    /**
     * The reset button.
     */
    private JButton resetButton;

    /**
     * The save log button.
     */
    private JButton saveLogButton;

    /**
     * The shutdown button.
     */
    private JButton shutdown;

    /**
     * The file chooser instance.
     */
    private JFileChooser fileChooser;

    /**
     * If the tool tip is currently opened.
     */
    private boolean toolTipOpened;

    /**
     * The world statistics text pane.
     */
    private StatsTextPane worldStatistics;

    /**
     * The thread statistics text pane.
     */
    private StatsTextPane threadStatistics;

    /**
     * The working time of the main thread.
     */
    private static long workingTime;

    /**
     * The maximum amount of players being active at the same time.
     */
    private static int maximumPlayers;

    /**
     * The text area.
     */
    private final JTextArea console = new JTextArea();

    /**
     * Constructs a new {@Code StatisticsTab} {@Code Object}
     */
    public StatisticsTab() {
        super("Statistics");
        setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setBackground(UIManager.getColor("Button.background"));
        scrollPane.setBounds(6, 403, 1042, 189);
        scrollPane.setBorder(new TitledBorder(null, "Console", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        add(scrollPane);

        console.setLineWrap(true);
        console.setBackground(UIManager.getColor("CheckBox.background"));
        console.setEditable(false);
        scrollPane.setViewportView(console);

        JLabel label = new JLabel();
        label.setBounds(450, 345, 163, 16);
        add(label);
    }

    @Override
    public String getToolTipText(MouseEvent e) {
        if (e.getX() > getWidth() / 2 && e.getX() < getWidth()
                && e.getY() > 2 && e.getY() < getHeight() / 2) {
            int max = (getWidth() / 2) / statisticsZoom;
            int index = queueIndex - (max - (e.getX() - (getWidth() / 2)) / statisticsZoom);
            toolTipOpened = true;
            if (index < 0) {
                return "Tick: null, time: null.";
            }
            return new StringBuilder("Tick: ").append(index).append(", time: ").append(performanceQueue[index] + 600).append(".").toString();
        }
        toolTipOpened = false;
        return null;
    }

    @Override
    public Point getToolTipLocation(MouseEvent e) {
        if (e.getX() > getWidth() / 2 && e.getX() < getWidth()
                && e.getY() > 2 && e.getY() < getHeight() / 2) {
            return new Point(e.getX() + 15, e.getY() + 10);
        }
        return null;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        drawPerformanceStatistics(this, g);
    }

    /**
     * Positions the components.
     */
    public void positionComponents() {
        int init = 90;
        int diff = 5;
        zoomIn.setLocation(getWidth() / 2 - (zoomIn.getWidth() / 2) - diff, 5 + init);
        zoomOut.setLocation(getWidth() / 2 - (zoomOut.getWidth() / 2) - diff, 30 + init);
        resetButton.setLocation(getWidth() / 2 - (resetButton.getWidth() / 2) - diff, 55 + init);
        saveLogButton.setLocation(getWidth() / 2 - (saveLogButton.getWidth() / 2) - diff, 80 + init);
        shutdown.setLocation(getWidth() / 2 - (shutdown.getWidth() / 2), 310);
        worldStatistics.setLocation(55, 310);
        threadStatistics.setLocation(shutdown.getX() + shutdown.getWidth() + 35, 310);
    }

    /**
     * Initializes the server info tab.
     *
     * @return This instance.
     */
    public StatisticsTab init() {
        initMaximumPlayers();
        fileChooser = new JFileChooser("./");
        zoomIn = new JButton("+");
        zoomIn.setLayout(null);
        zoomIn.setVisible(true);
        zoomIn.setSize(50, 20);
        zoomIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                zoomOut.setEnabled(true);
                if (++statisticsZoom > 20) {
                    statisticsZoom = 20;
                    zoomIn.setEnabled(false);
                    return;
                }
                drawPerformanceStatistics(StatisticsTab.this, getGraphics());
            }
        });
        zoomOut = new JButton("-");
        zoomOut.setLayout(null);
        zoomOut.setVisible(true);
        zoomOut.setSize(50, 20);
        zoomOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                zoomIn.setEnabled(true);
                if (--statisticsZoom < 2) {
                    statisticsZoom = 2;
                    zoomOut.setEnabled(false);
                    return;
                }
                drawPerformanceStatistics(StatisticsTab.this, getGraphics());
            }
        });
        resetButton = new JButton("x");
        resetButton.setLayout(null);
        resetButton.setVisible(true);
        resetButton.setSize(50, 20);
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                queueIndex = 0;
            }
        });
        saveLogButton = new JButton("", createImageIcon("Save16.gif"));
        saveLogButton.setLayout(null);
        saveLogButton.setVisible(true);
        saveLogButton.setSize(50, 20);
        saveLogButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int returnVal = fileChooser.showSaveDialog(StatisticsTab.this);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    if (!file.getName().contains(".")) {
                        file = new File(file.getPath() + ".txt");
                    }
                    logQueues(file);
                }
            }
        });
        add(resetButton);
        add(zoomIn);
        add(zoomOut);
        add(saveLogButton);
        shutdown = new JButton("Shutdown");
        shutdown.setLayout(null);
        shutdown.setVisible(true);
        shutdown.setSize(100, 20);
        shutdown.addActionListener(e -> SystemManager.flag(SystemState.TERMINATED));
        add(shutdown);
        worldStatistics = new StatsTextPane().init();
        worldStatistics.setSize(390, 90);
        add(worldStatistics);
        updateWorldText();
        threadStatistics = new StatsTextPane().init();
        threadStatistics.setSize(390, 90);
        add(threadStatistics);
        updateThreadText();
        setLayout(null);
        setVisible(true);
        ToolTipManager.sharedInstance().setInitialDelay(0);
        positionComponents();
        return this;
    }

    /**
     * Initializes the maximum players count.
     */
    private static void initMaximumPlayers() {
        setMaximumPlayers(0);
    }

    /**
     * Sets the maximum amount of players.
     *
     * @param maximum The maximum.
     */
    private static void setMaximumPlayers(int maximum) {
        maximumPlayers = maximum;
    }

    /**
     * Logs the queues.
     *
     * @param file The file to log to.
     */
    protected static void logQueues(File file) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))) {
            bw.append("/////////////////////////////////////////////////////////////////////////");
            bw.newLine();
            bw.append("/////////////////////////////////////////////////////////////////////////");
            bw.newLine();
            //	bw.append("// " + CalenderDate.getFormattedDate() + " performance log results:");
            bw.newLine();
            bw.append("/////////////////////////////////////////////////////////////////////////");
            bw.newLine();
            for (int i = 0; i < queueIndex; i++) {
                bw.append(new StringBuilder("performance_report:memory_usage-[tick=").append(i).append(", mem=").append(memoryQueue[i]).append("mb]."));
                bw.newLine();
            }
            for (int i = 0; i < queueIndex; i++) {
                int value = 600 + performanceQueue[i];
                bw.append(new StringBuilder("performance_report:clock_speed-[tick=").append(i).append(", time=").append(value).append(", status=").append(value < 601 ? "NORMAL]." : "DELAYED]."));
                bw.newLine();
            }
            bw.append("/////////////////////////////////////////////////////////////////////////");
            bw.newLine();
            bw.flush();
            bw.close();
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    /**
     * Creates an image icon.
     *
     * @param path The path of the image file.
     * @return The image icon.
     */
    private static ImageIcon createImageIcon(String path) {
        URL imgURL = StatisticsTab.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        }
        return null;
    }

    /**
     * Reports the performance value.
     *
     * @param value The value.
     */
    public static void reportPerformance(int value) {
        if (value >= Short.MAX_VALUE) {
            value = Short.MAX_VALUE - 1;
        }
        secureQueues();
        performanceQueue[queueIndex] = (short) value;
        memoryQueue[queueIndex++] = (short) ((Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / 1_000_000);
        INSTANCE.repaint();
        workingTime += (600 + value);
        if (INSTANCE.threadStatistics != null) {
            INSTANCE.updateThreadText();
            if ((GameWorld.getTicks() % 10) == 0) {
                INSTANCE.updateWorldText();
            }
        }
    }

    /**
     * Updates the thread statistics text.
     */
    public void updateThreadText() {
        StringBuilder sb = new StringBuilder();
        long runtime = System.currentTimeMillis() - Server.startTime;
        double percentage = workingTime / (runtime * 0.01);
        long hours = runtime / 3600000;
        runtime -= hours * 3600000;
        long minutes = runtime / 60000;
        runtime -= minutes * 60000;
        long seconds = runtime / 1000;
        sb.append("Runtime: ").append(hours).append("h ").append(minutes).append("m ").append(seconds).append("s").append(" - ").append(GameWorld.getTicks()).append(" ticks").append("\n");
        sb.append("Working time: ").append(workingTime).append("ms").append(" - ").append(String.format("%.2f", percentage)).append("%").append("\n");
        sb.append("Average cycle: ").append(String.format("%.1f", (double) workingTime / GameWorld.getTicks())).append("ms").append("\n");
        threadStatistics.setText(sb.toString());
    }

    /**
     * Updates the world statistics text.
     */
    public void updateWorldText() {
        int players = Repository.getPlayers().size();
        if (players > getMaximumPlayers()) {
            setMaximumPlayers(players);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Players: ").append(players).append(", max: ").append(getMaximumPlayers()).append("\n");
        sb.append("NPCs: ").append(Repository.getNpcs().size()).append("\n");
        sb.append("Regions: ").append(RegionManager.getRegionCache().size()).append("\n");
        sb.append("Ground items: ").append(GroundItemManager.getItems().size()).append("\n");
        worldStatistics.setText(sb.toString());
    }

    /**
     * Draws the performance statistics.
     *
     * @param c The component.
     * @param g The graphics.
     */
    private static void drawPerformanceStatistics(Component c, Graphics g) {
        int x = c.getWidth() / 2 + 48;
        int y = c.getHeight() / 2;

        Point zero = new Point(x, y / 2);
        g.setColor(Color.LIGHT_GRAY);

        //Fill the background rectangle.
        g.fillRect(x, 5, c.getWidth() - 4 - x, y - 5);
        g.setColor(Color.GRAY);

        int count = 0;
        for (int i = 0; i < c.getWidth() - 4 - x; i += statisticsZoom) {
            g.drawLine((int) (zero.getX() + i), 5,
                    (int) (zero.getX() + i), y - 1);
            count++;
        }

        int space = (y / 2 - 10) / 4;
        g.setFont(new Font(null, Font.PLAIN, 9));
        for (int i = 0; i < 5; i++) {
            g.setColor(i == 0 ? Color.BLACK : Color.GRAY);
            g.drawLine(x, (int) zero.getY() - (space * i), c.getWidth() - 5, (int) zero.getY() - (space * i));
            g.drawLine(x, (int) zero.getY() + (space * i), c.getWidth() - 5, (int) zero.getY() + (space * i));
            g.setColor(Color.BLACK);
            g.drawString("" + (i * 150), x - 18, (int) zero.getY() - (space * i) + 2);
            if (i != 0) {
                g.drawString("-" + (i * 150), x - 20, (int) zero.getY() + (space * i) + 2);
            }
        }
        g.setColor(Color.GREEN);
        for (int i = 1; i < count; i++) {
            int index = queueIndex - i;
            if (index < 1) {
                break;
            }
            int fromPoint = performanceQueue[index];
            int fromX = (count - i) * statisticsZoom;
            int fromY = (int) (-fromPoint / (600D / (space * 4)));
            int toPoint = index == 0 ? 0 : performanceQueue[index - 1];
            int toX = (count - i - 1) * statisticsZoom;
            int toY = (int) (-toPoint / (600D / (space * 4)));
            if (fromY < 0) {
                g.setColor(Color.RED);
            }
            g.drawLine((int) (zero.getX() + fromX), (int) (zero.getY() + fromY),
                    (int) (zero.getX() + toX), (int) (zero.getY() + toY));
            if (fromY >= 0) {
                g.setColor(Color.GREEN);
            }
        }
        g.setColor(Color.BLACK);
        //Draw vertical line.
        g.drawLine(x, y + 4, x, 1);
        Point p = ((StatisticsTab) c).statisticMousePoint;
        if (p != null) {
            g.setColor(new Color(251, 230, 130));
            g.fillRoundRect((int) (p.getX() + 10), (int) (p.getY() - 10), 100, 50, 10, 10);
        }
        if (((StatisticsTab) c).toolTipOpened) {
            ((StatisticsTab) c).setToolTipText("test");
        }
        drawMemoryStatistics(c, g);
    }

    /**
     * Draws the performance statistics.
     *
     * @param c The component.
     * @param g The graphics.
     */
    private static void drawMemoryStatistics(Component c, Graphics g) {
        int x = 5;
        int y = c.getHeight() / 2;
        int endX = (c.getWidth() / 2) - 48;

        Point zero = new Point(x + 18, y);
        g.setColor(Color.LIGHT_GRAY);

        //Fill the background rectangle.
        g.fillRect((int) zero.getX(), 5, endX - x, y - 5);
        g.setColor(Color.GRAY);

        int count = 0;
        for (int i = 0; i < endX - x; i += statisticsZoom) {
            g.drawLine((int) (zero.getX() + i), 5,
                    (int) (zero.getX() + i), y - 1);
            count++;
        }

        int space = (y) / 10;
        g.setFont(new Font(null, Font.PLAIN, 9));
        for (int i = 0; i < 10; i++) {
            g.setColor(Color.GRAY);
            g.drawLine((int) zero.getX() - 2, (int) zero.getY() - (space * i), endX + 17, (int) zero.getY() - (space * i));
            g.setColor(Color.BLACK);
            g.drawString("" + (i * 100), 2, (int) zero.getY() - (space * i) + 2);
        }
        g.setColor(Color.BLUE);
        for (int i = 1; i < count; i++) {
            int index = queueIndex - i;
            if (index < 1) {
                break;
            }
            int fromPoint = memoryQueue[index];
            int fromX = (count - i) * statisticsZoom;
            int fromY = (int) (fromPoint / (900D / (space * 10)));
            int toPoint = index == 0 ? 0 : memoryQueue[index - 1];
            int toX = (count - i - 1) * statisticsZoom;
            int toY = (int) (toPoint / (900D / (space * 10)));
            if (fromY < 0) {
                g.setColor(Color.RED);
            }
            g.drawLine((int) (zero.getX() + fromX), (int) (zero.getY() - fromY),
                    (int) (zero.getX() + toX), (int) (zero.getY() - toY));
            if (fromY < 0) {
                g.setColor(Color.GREEN);
            }
        }
        g.setColor(Color.BLACK);
        //Draw horizontal line.
        g.drawLine((int) (zero.getX() - 4), (int) zero.getY(), endX + 21, (int) zero.getY());
        //Draw vertical line.
        g.drawLine((int) zero.getX(), (int) zero.getY() + 4, (int) zero.getX(), 1);
        Point p = ((StatisticsTab) c).statisticMousePoint;
        if (p != null) {
            g.setColor(new Color(251, 230, 130));
            g.fillRoundRect((int) (p.getX() + 10), (int) (p.getY() - 10), 100, 50, 10, 10);
        }
        if (((StatisticsTab) c).toolTipOpened) {
            ((StatisticsTab) c).setToolTipText("test");
        }
    }

    /**
     * Secures the queues.
     */
    private static void secureQueues() {
        if (queueIndex >= QUEUE_SIZE) {
            int lagSpikes = 0;
            int memoryUsageSpikes = 0;
            @SuppressWarnings("unused")
            int totalMemory = 0;
            @SuppressWarnings("unused")
            int totalCycleTime = 0;
            //Start at tick 500, the JVM has to "warm up" first.
            for (int i = 500; i < QUEUE_SIZE; i++) {
                if (performanceQueue[i] > 0) { //Anything above 0 (-600 + 600) = lag
                    lagSpikes++;
                }
                if (memoryQueue[i] > 700) { //Over 700Mb
                    memoryUsageSpikes++;
                }
                totalMemory += memoryQueue[i];
                totalCycleTime += performanceQueue[i];
            }
            if (lagSpikes > 350 || memoryUsageSpikes > 350) {
                logQueues(new File(ServerConstants.LOGS_PATH + "/system/Performance-log.txt"));
            }
            //System.out.println("Average cycle time: " + (600 + (totalCycleTime / (QUEUE_SIZE - 500))) + "ms.");
            //System.out.println("Average memory usage: " + (totalMemory / (QUEUE_SIZE - 500)) + "Mb.");
            queueIndex = 0;
        }
    }

    /**
     * Logs a message to the console.
     *
     * @param message the message.
     */
    public void log(String message) {
        console.append(message + "\n");
        console.setCaretPosition(console.getDocument().getLength());
    }

    /**
     * Sets the statistics zoom.
     *
     * @param pixels The amount of pixels.
     */
    public static void setStatisticsZoom(int pixels) {
        statisticsZoom = pixels;
    }

    /**
     * Gets the workingTime.
     *
     * @return The workingTime.
     */
    public static long getWorkingTime() {
        return workingTime;
    }

    /**
     * Sets the workingTime.
     *
     * @param workingTime The workingTime to set.
     */
    public static void setWorkingTime(long workingTime) {
        StatisticsTab.workingTime = workingTime;
    }

    /**
     * Gets the maximumPlayers.
     *
     * @return The maximumPlayers.
     */
    public static int getMaximumPlayers() {
        return maximumPlayers;
    }

    /**
     * Handles the statistics tab text pane.
     *
     * @author Emperor
     */
    public final class StatsTextPane extends JTextPane {

        /**
         * The serial UID.
         */
        private static final long serialVersionUID = 664276151176087663L;

        /**
         * Constructs a new {@code StatsTextPane} {@code Object}.
         */
        public StatsTextPane() {
            super();
        }

        /**
         * Initializes the stats text pane.
         *
         * @return The stats text pane.
         */
        public StatsTextPane init() {
            setEditable(false);
            setLayout(null);
            Font font = new Font("Monospaced", Font.PLAIN, 12);
            setFont(font);
            super.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
            super.setVisible(true);
            return this;
        }

    }
}
