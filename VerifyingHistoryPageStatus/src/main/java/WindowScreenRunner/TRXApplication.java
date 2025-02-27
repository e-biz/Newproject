package WindowScreenRunner;


import javax.swing.*;

import StoringLocalValue.ScenarioContext;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class TRXApplication extends JFrame {
    private JTextField emailField;
    private JPasswordField passwordField;
    private JTextField companyNameField;
    private JComboBox<String> accountingAppComboBox;
    private JComboBox<String> environmentComboBox;
    private JComboBox<String> browserComboBox;
    private JButton submitButton;
    private boolean formSubmitted; // Add a flag to indicate form submission


    public TRXApplication() {
    	// Set up the JFrame
        setTitle("eRequisition Script's");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500,600);
        setLocationRelativeTo(null);

        // Initialize components
        emailField = new JTextField(20);
        passwordField = new JPasswordField(20);
        companyNameField = new JTextField(20);
        accountingAppComboBox = new JComboBox<>(new String[]{"Select Accounting Application:", "TXO", "TRX", "Standalone"});
        environmentComboBox = new JComboBox<>(new String[]{"Select Environment:", "QA", "STAGING"});
        browserComboBox = new JComboBox<>(new String[]{"Select Browser:", "chrome", "firefox"});
        submitButton = new JButton("Submit");

     // Add components to the frame with improved layout
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;

        // Add top padding space
        gbc.insets = new Insets(20, 0, 0, 0);

        addFieldWithLabel("Enter Email:", emailField, gbc);
        addFieldWithLabel("Enter Password:", passwordField, gbc);
        addFieldWithLabel("Enter Company Name:", companyNameField, gbc);
        addFieldWithLabel("Select Accounting Application:", accountingAppComboBox, gbc);
        addFieldWithLabel("Select Environment:", environmentComboBox, gbc);
        addFieldWithLabel("Select Browser:", browserComboBox, gbc);

        // Center the Submit button
        gbc.gridy++;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(20, 0, 0, 0); // Add space before the button
        add(submitButton, gbc);

     // Add a note JLabel
        gbc.gridy++;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 0, 0, 0); // Add space before the note

        String noteText = "Note: Please add company which have all the data with respect \n to the History page status";

        String[] lines = noteText.split("\n");

        for (String line : lines) {
            JLabel noteLabel = new JLabel(line);
            add(noteLabel, gbc);
            gbc.gridy++;
            }

        formSubmitted = false;

        // Add ActionListener to the submitButton
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isValidData()) {
                    // Call your method or do the necessary actions with the data
                    String email = emailField.getText();
                    String password = new String(passwordField.getPassword());
                    String companyName = companyNameField.getText();
                    String accountingApp = accountingAppComboBox.getSelectedItem().toString();
                    String environment = environmentComboBox.getSelectedItem().toString();
                    String browser = browserComboBox.getSelectedItem().toString();
                    ScenarioContext.put("AppName", accountingApp);
                    ScenarioContext.put("AEmail", email);
                    ScenarioContext.put("Password",password);
                    ScenarioContext.put("Environment", environment);
                    ScenarioContext.put("CompanyName", companyName);
                    ScenarioContext.put("Browser", browser);
                    // Add your logic here to use the data
                    // For example, call a method to submit the data to the TRX application backend
                    // submitData(email, password, companyName, accountingApp, environment);

                    // Display a success message or perform other actions
                    JOptionPane.showMessageDialog(null, "Data submitted successfully!");
               
                    formSubmitted = true;
                } else {
                    JOptionPane.showMessageDialog(null, "Please fill in all the fields before submitting.");
                }
            }
        });
    }
    	
    private void addFieldWithLabel(String label, JComponent component, GridBagConstraints gbc) {
    	JLabel boldLabel = new JLabel("<html><b>" + label + "</b></html>");
        add(boldLabel, gbc);
        gbc.gridy++;
        gbc.insets = new Insets(5, 0, 0, 0); // Add space between fields
        add(component, gbc);
        gbc.gridy++;
    }

    public boolean isFormSubmitted() {
        return formSubmitted;
    }
    
 // Method to handle form submission
    public void submitForm() {
        submitButton.doClick();
    }
    
 // Method to close the window
    public void closeWindow() {
        setVisible(false);
        dispose();
    }
    
    // Method to validate input fields before submission
    private boolean isValidData() {
        String email = emailField.getText().trim();
        String password = new String(passwordField.getPassword()).trim();
        String companyName = companyNameField.getText().trim();
        String accountingApp = accountingAppComboBox.getSelectedItem().toString();
        String environment = environmentComboBox.getSelectedItem().toString();
        String browser = browserComboBox.getSelectedItem().toString();

        return !email.isEmpty() && !password.isEmpty() && !companyName.isEmpty()
                && !accountingApp.equals("Select Accounting Application:") && !environment.equals("Select Environment:") && !browser.equals("Select Browser:");
    }

    public void display() {
        // Make the JFrame visible
        setVisible(true);
    }
}
