import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MyFrame extends JFrame {

    protected JTextField locationField;

    protected JButton searchButton;

    protected JLabel locationLabel;
    protected JLabel tempLabel;
    protected JLabel tempMinLabel;
    protected JLabel tempMaxLabel;
    protected JLabel conditionLabel;
    protected JLabel feelsLikeLabel;
    protected JLabel humidityLabel;
    protected JLabel windLabel;
    protected JLabel userLabel;
    protected ImageIcon windIcon = new ImageIcon("wind.png");
    protected Image windImage = windIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
    protected ImageIcon windImageIcon = new ImageIcon(windImage);
    protected JPanel userPanel;
    protected JPanel outputPanel;
    protected JPanel iconPanel;

    private JMenuBar menuBar;

    private Color blackGrayColor = new Color(30, 30, 30);
    private Color lightGrayColor = new Color(220, 220, 220);

    public MyFrame() {
        // Set up the frame
        setIconImage(new ImageIcon("weather.png").getImage());
        setTitle("Weather App");
        setSize(500, 400);
        setBackground(lightGrayColor);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        // Set up the location input field
        locationField = new JTextField();
        locationField.setBorder(BorderFactory.createLineBorder(blackGrayColor, 2));
        locationField.setPreferredSize(new java.awt.Dimension(150, 25));
        locationField.setBackground(lightGrayColor);

        // Set up the search button
        searchButton = new JButton("Search");

        // Set up labels
        locationLabel = new JLabel("Location:");
        tempLabel = new JLabel("Temperature:");
        tempMinLabel = new JLabel("Min Temperature:");
        tempMaxLabel = new JLabel("Max Temperature:");
        feelsLikeLabel = new JLabel("Feels Like:");
        humidityLabel = new JLabel("Humidity:");

        conditionLabel = new JLabel("Condition:");
        conditionLabel.setBackground(lightGrayColor);
        conditionLabel.setOpaque(true);

        windLabel = new JLabel("Wind:");
        windLabel.setBackground(lightGrayColor);
        windLabel.setOpaque(true);
        windLabel.setIcon(windImageIcon);
        // set up user input panel
        userPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        userLabel = new JLabel("Enter Location:");
        userPanel.setBackground(lightGrayColor);
        userPanel.add(userLabel);
        userPanel.add(locationField);
        userPanel.add(searchButton);
        userPanel.setMaximumSize(new java.awt.Dimension(Integer.MAX_VALUE, 35));

        // Set up output panel
        outputPanel = new JPanel(new GridLayout(5, 2));
        outputPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(null,
                        "Weather Info", 2, 0, new Font("SansSerif", Font.BOLD, 14), blackGrayColor),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        outputPanel.setBackground(lightGrayColor);
        outputPanel.add(locationLabel);
        outputPanel.add(tempLabel);
        outputPanel.add(tempMinLabel);
        outputPanel.add(tempMaxLabel);
        outputPanel.add(feelsLikeLabel);
        outputPanel.add(humidityLabel);

        // Set up icon panel
        iconPanel = new JPanel(new GridLayout(1, 2));
        iconPanel.setBackground(lightGrayColor);
        iconPanel.add(conditionLabel);
        iconPanel.add(windLabel);
        iconPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Set components to the frame
        add(userPanel);
        add(outputPanel);
        add(iconPanel);

        // Set font for labels and text field
        Font labelFont = new Font("SansSerif", Font.PLAIN, 14);
        locationLabel.setFont(labelFont);
        tempLabel.setFont(labelFont);
        tempMinLabel.setFont(labelFont);
        tempMaxLabel.setFont(labelFont);
        conditionLabel.setFont(labelFont);
        locationField.setFont(labelFont);
        feelsLikeLabel.setFont(labelFont);
        humidityLabel.setFont(labelFont);
        windLabel.setFont(labelFont);

        // Set up the menu bar
        menuBar = new JMenuBar();
        JMenu themeMode = new JMenu("Theme Mode");
        JMenuItem lightMode = new JMenuItem("Light Mode");
        JMenuItem darkMode = new JMenuItem("Dark Mode");
        menuBar.setOpaque(true);
        menuBar.setBorder(BorderFactory.createEmptyBorder());
        // Put actionsListeners for light and dark mode
        lightMode.addActionListener(e -> {
            // Set light mode colors
            menuBar.setBackground(lightGrayColor);
            setBackground(lightGrayColor);
            userPanel.setBackground(lightGrayColor);
            outputPanel.setBackground(lightGrayColor);
            iconPanel.setBackground(lightGrayColor);
            conditionLabel.setBackground(lightGrayColor);
            windLabel.setBackground(lightGrayColor);

            // Set border for output panel with light mode
            outputPanel.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createTitledBorder(null,
                            "Weather Info", 2, 0, new Font("SansSerif", Font.BOLD, 14), blackGrayColor),
                    BorderFactory.createEmptyBorder(10, 10, 10, 10)));
            // Set text colors to black
            userLabel.setForeground(blackGrayColor);
            themeMode.setForeground(blackGrayColor);
            locationLabel.setForeground(blackGrayColor);
            tempLabel.setForeground(blackGrayColor);
            tempMinLabel.setForeground(blackGrayColor);
            tempMaxLabel.setForeground(blackGrayColor);
            conditionLabel.setForeground(blackGrayColor);
            feelsLikeLabel.setForeground(blackGrayColor);
            humidityLabel.setForeground(blackGrayColor);
            windLabel.setForeground(blackGrayColor);

            locationField.setForeground(blackGrayColor);

        });
        darkMode.addActionListener(e -> {
            // Set dark mode colors
            menuBar.setBackground(blackGrayColor);
            getContentPane().setBackground(blackGrayColor);
            userPanel.setBackground(blackGrayColor);
            outputPanel.setBackground(blackGrayColor);
            iconPanel.setBackground(blackGrayColor);
            conditionLabel.setBackground(blackGrayColor);
            windLabel.setBackground(blackGrayColor);

            // Set border for output panel with dark mode
            outputPanel.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createTitledBorder(null,
                            "Weather Info", 2, 0, new Font("SansSerif", Font.BOLD, 14), lightGrayColor),
                    BorderFactory.createEmptyBorder(10, 10, 10, 10)));
            // Set text colors to white
            userLabel.setForeground(lightGrayColor);
            themeMode.setForeground(lightGrayColor);
            locationLabel.setForeground(lightGrayColor);
            tempLabel.setForeground(lightGrayColor);
            tempMinLabel.setForeground(lightGrayColor);
            tempMaxLabel.setForeground(lightGrayColor);
            conditionLabel.setForeground(lightGrayColor);
            feelsLikeLabel.setForeground(lightGrayColor);
            humidityLabel.setForeground(lightGrayColor);
            windLabel.setForeground(lightGrayColor);
        });
        themeMode.add(lightMode);
        themeMode.add(darkMode);
        menuBar.add(themeMode);
        setJMenuBar(menuBar);

        // set visible
        setVisible(true);
    }

}
