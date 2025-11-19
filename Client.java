import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.text.*;
import java.net.*;
import java.io.*;

public class Client extends JFrame {
    private JPanel chatPanel;
    private JTextField messageField;
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private static final String SERVER_IP = "localhost";
    private static final int SERVER_PORT = 5000;
    private JScrollPane scrollPane;
    private Box vertical;
    private static final Color DARK_BG = new Color(18, 18, 18);
    private static final Color DARKER_BG = new Color(13, 13, 13);
    private static final Color ACCENT = new Color(0, 150, 136);
    private static final Color TEXT_COLOR = new Color(255, 255, 255);
    private static final Color GRAY_TEXT = new Color(180, 180, 180);
    private static final Color INPUT_BG = new Color(30, 30, 30);
    private static final Color MESSAGE_BG = new Color(0, 120, 215);
    private static final Color SEND_BUTTON_COLOR = new Color(0, 120, 215);
    private JFrame mainFrame;

    public Client() {
        mainFrame = this;
        setTitle("Chatting Application");
        setSize(400, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(DARK_BG);

        // Create header panel
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(DARKER_BG);
        headerPanel.setPreferredSize(new Dimension(400, 80));
        headerPanel.setLayout(new BorderLayout());
        headerPanel.setBorder(new EmptyBorder(10, 15, 10, 15));

        // Back button panel
        JPanel backButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        backButtonPanel.setBackground(DARKER_BG);
        
        // Create back arrow button
        JButton backButton = new JButton() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                // Draw arrow
                g2.setColor(TEXT_COLOR);
                int[] xPoints = {20, 10, 10, 20, 20};
                int[] yPoints = {10, 15, 15, 20, 20};
                g2.fillPolygon(xPoints, yPoints, 5);
                
                g2.dispose();
            }
            
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(30, 30);
            }
        };
        backButton.setBackground(DARKER_BG);
        backButton.setBorderPainted(false);
        backButton.setFocusPainted(false);
        backButton.setContentAreaFilled(false);
        backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        backButton.addActionListener(e -> {
            mainFrame.dispose();
        });
        
        backButtonPanel.add(backButton);
        headerPanel.add(backButtonPanel, BorderLayout.WEST);

        // Profile photo panel
        JPanel profilePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        profilePanel.setBackground(DARKER_BG);
        
        // Create a circular profile photo with icon
        JPanel photoPanel = new JPanel() {}
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                // Draw circular background
                g2.setColor(ACCENT);
                g2.fillOval(0, 0, 50, 50);
                
                // Draw icon (simple user icon)
                g2.setColor(Color.WHITE);
                g2.fillOval(15, 10, 20, 20); // Head
                g2.fillArc(10, 30, 30, 25, 0, 180); // Body
                
                g2.dispose();
            }
            
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(50, 50);
            }
        };
        photoPanel.setBackground(DARKER_BG);
        profilePanel.add(photoPanel);

        // Name and status panel
        JPanel namePanel = new JPanel();
        namePanel.setBackground(DARKER_BG);
        namePanel.setLayout(new BoxLayout(namePanel, BoxLayout.Y_AXIS));
        namePanel.setBorder(new EmptyBorder(5, 15, 0, 0));

        JLabel nameLabel = new JLabel("Virat Kohli");
        nameLabel.setForeground(TEXT_COLOR);
        nameLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        nameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel statusLabel = new JLabel("Online");
        statusLabel.setForeground(GRAY_TEXT);
        statusLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        statusLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        namePanel.add(nameLabel);
        namePanel.add(Box.createVerticalStrut(2));
        namePanel.add(statusLabel);

        JPanel leftHeaderPanel = new JPanel(new BorderLayout());
        leftHeaderPanel.setBackground(DARKER_BG);
        leftHeaderPanel.add(profilePanel, BorderLayout.WEST);
        leftHeaderPanel.add(namePanel, BorderLayout.CENTER);

        headerPanel.add(leftHeaderPanel, BorderLayout.WEST);

        // Create chat panel
        chatPanel = new JPanel();
        chatPanel.setLayout(new BoxLayout(chatPanel, BoxLayout.Y_AXIS));
        chatPanel.setBackground(DARK_BG);
        vertical = Box.createVerticalBox();
        chatPanel.add(vertical);

        // Create scroll pane
        scrollPane = new JScrollPane(chatPanel);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.getViewport().setBackground(DARK_BG);

        // Create bottom panel
        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(DARK_BG);
        bottomPanel.setLayout(new BorderLayout(10, 0));
        bottomPanel.setBorder(new EmptyBorder(15, 15, 15, 15));

        messageField = new JTextField();
        messageField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        messageField.setForeground(TEXT_COLOR);
        messageField.setBackground(INPUT_BG);
        messageField.setCaretColor(TEXT_COLOR);
        messageField.setBorder(BorderFactory.createCompoundBorder(
            new RoundedBorder(INPUT_BG, 20),
            BorderFactory.createEmptyBorder(10, 15, 10, 15)
        ));

        JButton sendButton = new JButton("Send");
        sendButton.setBackground(SEND_BUTTON_COLOR);
        sendButton.setForeground(TEXT_COLOR);
        sendButton.setFocusPainted(false);
        sendButton.setBorderPainted(false);
        sendButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        sendButton.setPreferredSize(new Dimension(80, 40));

        bottomPanel.add(messageField, BorderLayout.CENTER);
        bottomPanel.add(sendButton, BorderLayout.EAST);

        // Add components to frame
        add(headerPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        // Add action listeners
        sendButton.addActionListener(e -> sendMessage());
        messageField.addActionListener(e -> sendMessage());

        // Connect to server in a separate thread
        new Thread(this::connectToServer).start();
    }

    private void addMessage(String message, boolean isClient) {
        JPanel messagePanel = new JPanel();
        messagePanel.setLayout(new BorderLayout());
        messagePanel.setBackground(DARK_BG);
        messagePanel.setBorder(new EmptyBorder(5, 15, 5, 15));

        JPanel bubblePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(isClient ? MESSAGE_BG : INPUT_BG);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                g2.dispose();
            }
        };
        bubblePanel.setLayout(new BoxLayout(bubblePanel, BoxLayout.Y_AXIS));
        bubblePanel.setBackground(new Color(0, 0, 0, 0));
        bubblePanel.setBorder(BorderFactory.createEmptyBorder(8, 12, 8, 12));
        bubblePanel.setMaximumSize(new Dimension(250, Integer.MAX_VALUE));

        // Create a text area for wrapping text
        JTextArea messageArea = new JTextArea(message);
        messageArea.setEditable(false);
        messageArea.setLineWrap(true);
        messageArea.setWrapStyleWord(true);
        messageArea.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        messageArea.setForeground(TEXT_COLOR);
        messageArea.setBackground(new Color(0, 0, 0, 0));
        messageArea.setBorder(null);
        messageArea.setOpaque(false);
        messageArea.setFocusable(false);
        messageArea.setCaretColor(new Color(0, 0, 0, 0));
        messageArea.setSelectionColor(new Color(0, 0, 0, 0));
        messageArea.setSelectedTextColor(TEXT_COLOR);

        JLabel timeLabel = new JLabel(new SimpleDateFormat("HH:mm").format(new Date()));
        timeLabel.setFont(new Font("Segoe UI", Font.PLAIN, 10));
        timeLabel.setForeground(GRAY_TEXT);
        timeLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);

        bubblePanel.add(messageArea);
        bubblePanel.add(Box.createVerticalStrut(3));
        bubblePanel.add(timeLabel);

        JPanel wrapperPanel = new JPanel(new BorderLayout());
        wrapperPanel.setBackground(DARK_BG);
        wrapperPanel.add(bubblePanel, isClient ? BorderLayout.EAST : BorderLayout.WEST);

        messagePanel.add(wrapperPanel, BorderLayout.CENTER);

        vertical.add(messagePanel);
        chatPanel.revalidate();
        chatPanel.repaint();
        
        // Auto scroll to bottom
        SwingUtilities.invokeLater(() -> {
            JScrollBar vertical = scrollPane.getVerticalScrollBar();
            vertical.setValue(vertical.getMaximum());
        });
    }

    private void connectToServer() {
        try {
            socket = new Socket(SERVER_IP, SERVER_PORT);
            addMessage("Connected to Rohit!", true);
            
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            
            String message;
            while ((message = in.readLine()) != null) {
                addMessage(message, false);
            }
        } catch (IOException e) {
            addMessage("Error: " + e.getMessage(), true);
        }
    }

    private void sendMessage() {
        String message = messageField.getText().trim();
        if (!message.isEmpty() && out != null) {
            out.println(message);
            addMessage(message, true);
            messageField.setText("");
        }
    }

    // Custom rounded border class
    class RoundedBorder implements Border {
        private Color color;
        private int radius;

        RoundedBorder(Color color, int radius) {
            this.color = color;
            this.radius = radius;
        }

        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(color);
            g2.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
            g2.dispose();
        }

        public Insets getBorderInsets(Component c) {
            return new Insets(radius/2, radius/2, radius/2, radius/2);
        }

        public boolean isBorderOpaque() {
            return false;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            new Client().setVisible(true);
        });
    }
}

