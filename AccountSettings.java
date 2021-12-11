import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.ServerSocket;
import java.util.Random;

public class AccountSettings extends JComponent {
    JFrame frameA1 = new JFrame("Account Settings:");
    JFrame frameA2 = new JFrame("Account Modification:");
    JButton modify;
    JButton delete;
    JButton logOut;
    JButton enterA;
    JTextField name;
    JTextField age;
    JTextField password;
    JLabel nameLabel;
    JLabel ageLabel;
    JLabel passwordLabel;
    Server1 s = new Server1(); // here not sure why these 2 codes exist
    NetworkMethods n = new NetworkMethods();
    Account a = new Account();
    InitialMenu i = new InitialMenu();

    ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == modify) {
                modify();
            }
            if (e.getSource() == delete) {
                delete();
            }
            if (e.getSource() == logOut) {
                logOut();
            }
        }
    };

    public void delete() {
        frameA1.setVisible(false);
        frameA2.setVisible(false);//here
        a.deleteAccount(a.getUsername(), a.getPassword()); // check
        JOptionPane.showMessageDialog(null, "Account Deleted", "Attention!",
                JOptionPane.INFORMATION_MESSAGE);

    }

    public void modify(){
        name = new JTextField(20);
        age = new JTextField(20);
        password = new JTextField(20);
        name.setText("");
        age.setText("");
        password.setText("");
        nameLabel = new JLabel("Enter name");
        ageLabel = new JLabel("Enter age");
        passwordLabel = new JLabel("Enter password");
        enterA = new JButton("Enter");
        frameA1.setVisible(false);
        frameA2.setVisible(true);
        JPanel panelButton = new JPanel();
        JPanel panelTextFields = new JPanel(new GridLayout(3,2,-255,10));
        Container content = frameA2.getContentPane();
        panelTextFields.add(nameLabel);
        panelTextFields.add(name);
        panelTextFields.add(ageLabel);
        panelTextFields.add(age);
        panelTextFields.add(passwordLabel);
        panelTextFields.add(password);
        content.add(panelTextFields, BorderLayout.NORTH);
        enterA.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enterA();
            }
        });
        panelButton.add(enterA);
        content.add(panelButton,BorderLayout.CENTER);
    }

    public void enterA(){
        //s.refresh();
        if(name.getText().equals("")||
                age.getText().equals("")||
                password.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Fields can't be empty.", "Error",
                    JOptionPane.ERROR_MESSAGE);
        } else if(!age.getText().matches("-?\\d+(\\.\\d+)?")) {
            JOptionPane.showMessageDialog(null, "Age must be an integer.", "Error",
                    JOptionPane.ERROR_MESSAGE);
        } else if (Integer.parseInt(age.getText())<0){
            JOptionPane.showMessageDialog(null, "Age must be a valid number.", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
        else {
            ServerSocket ss = n.openServerSocket(); //open new ServerSocket
            frameA2.setVisible(false);
            n.sendToServer(name.getText());
            n.sendToServer(age.getText());
            n.sendToServer(password.getText());
            s.modifyAccountInServer(ss);
            n.closeServerSocket(ss); //close ServerSocket

            name.setText("");
            age.setText("");
            password.setText("");
            JOptionPane.showMessageDialog(null, "Account successfully modified!", "Success",
                    JOptionPane.INFORMATION_MESSAGE);
            i.createMainMenu(); // here calling initial menu
        }
    }

    public void logOut() {
        frameA1.setVisible(false);
        frameA2.setVisible(false);//here
        JOptionPane.showMessageDialog(null, "You have been logged out.", "Successfully Logged Out",
                JOptionPane.INFORMATION_MESSAGE);
        i.createMainMenu(); // here seems very wrong

    }
    public void createAccountSettings() {
        JPanel panelButton = new JPanel();  // panel of buttons
        Container content = frameA1.getContentPane();
        modify.addActionListener(actionListener);
        panelButton.add(modify);
        delete.addActionListener(actionListener);
        panelButton.add(delete);
        logOut.addActionListener(actionListener);
        panelButton.add(logOut);
        content.add(panelButton, BorderLayout.CENTER);
        frameA1.setVisible(true);
    }


    public AccountSettings() { //here ???
        modify = new JButton("Modify Account");
        delete = new JButton("Delete Account");
        logOut = new JButton("Log Out");
        frameA1.setResizable(false);
        frameA2.setResizable(false);
        frameA1.setSize(500,500);
        frameA2.setSize(500,500);
        frameA1.setLocationRelativeTo(null);
        frameA2.setLocationRelativeTo(null);
        frameA1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frameA2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);// here copy

    }






}
