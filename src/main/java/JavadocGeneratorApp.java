import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JavadocGeneratorApp extends JFrame {

    private JTextArea inputArea;
    private JTextArea outputArea;
    private final JavadocGenerator generator;
    private final Color BACKGROUND_COLOR = new Color(34, 34, 34);

    public JavadocGeneratorApp() {
        // Initialize the JavadocGenerator instance
        generator = new JavadocGenerator();

        // Set up the main window
        setTitle("Javadoc Generator");
        setSize(450, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(BACKGROUND_COLOR);

        // Input panel
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BorderLayout());
        inputPanel.setBackground(BACKGROUND_COLOR);
        inputPanel.setForeground(Color.WHITE);

        JLabel inputLabel = new JLabel("Enter Java Method/Constructor:");
        inputArea = new JTextArea(7, 35);
        inputArea.setBackground(BACKGROUND_COLOR);
        inputLabel.setForeground(Color.WHITE);
        inputArea.setForeground(Color.WHITE);

        JScrollPane inputScroll = new JScrollPane(inputArea);
        inputScroll.setBackground(BACKGROUND_COLOR);

        inputPanel.add(inputLabel, BorderLayout.NORTH);
        inputPanel.add(inputScroll, BorderLayout.CENTER);

        // Output panel
        JPanel outputPanel = new JPanel();
        outputPanel.setLayout(new BorderLayout());
        outputPanel.setBackground(BACKGROUND_COLOR);

        JLabel outputLabel = new JLabel("Generated Javadoc:");
        outputArea = new JTextArea(8, 35);
        outputArea.setEditable(false);
        outputArea.setBackground(BACKGROUND_COLOR);
        outputArea.setForeground(Color.WHITE);
        outputLabel.setForeground(Color.WHITE);

        JScrollPane outputScroll = new JScrollPane(outputArea);
        outputScroll.setBackground(BACKGROUND_COLOR);

        outputPanel.add(outputLabel, BorderLayout.NORTH);
        outputPanel.add(outputScroll, BorderLayout.CENTER);

        // Button panel
        JPanel buttonPanel = new JPanel();
        JButton generateButton = new JButton("Generate Javadoc");
        generateButton.setBackground(BACKGROUND_COLOR);
        buttonPanel.setBackground(BACKGROUND_COLOR);
        // generateButton.setBackground(Color.darkGray);
        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generateJavadoc();
            }
        });

        buttonPanel.add(generateButton);

        // Add components to the main window
        add(inputPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        add(outputPanel, BorderLayout.SOUTH);
    }

    private void generateJavadoc() {
        String input = inputArea.getText().trim();
        if (input.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a Java method or constructor.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String javadoc = generator.generateJavadocContent(input);
        outputArea.setText(javadoc);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JavadocGeneratorApp app = new JavadocGeneratorApp();
            app.setVisible(true);
        });
    }
}
