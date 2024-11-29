import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JavadocGeneratorApp extends JFrame {

    private JTextArea inputArea;
    private JTextArea outputArea;
    private final JavadocGenerator generator;

    public JavadocGeneratorApp() {
        // Initialize the JavadocGenerator instance
        generator = new JavadocGenerator();

        // Set up the main window
        setTitle("Javadoc Generator");
        setSize(450, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Input panel
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BorderLayout());
        // inputPanel.setBackground(Color.darkGray);
        JLabel inputLabel = new JLabel("Enter Java Method/Constructor:");
        inputArea = new JTextArea(7, 35);
        JScrollPane inputScroll = new JScrollPane(inputArea);

        inputPanel.add(inputLabel, BorderLayout.NORTH);
        inputPanel.add(inputScroll, BorderLayout.CENTER);

        // Output panel
        JPanel outputPanel = new JPanel();
        outputPanel.setLayout(new BorderLayout());
        // outputPanel.setBackground(Color.darkGray);
        JLabel outputLabel = new JLabel("Generated Javadoc:");
        outputArea = new JTextArea(8, 35);
        outputArea.setEditable(false);
        JScrollPane outputScroll = new JScrollPane(outputArea);

        outputPanel.add(outputLabel, BorderLayout.NORTH);
        outputPanel.add(outputScroll, BorderLayout.CENTER);

        // Button panel
        JPanel buttonPanel = new JPanel();
        JButton generateButton = new JButton("Generate Javadoc");
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
