import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class LoginRegisterSystem extends JFrame implements ActionListener {
    private ArrayList<User> users = new ArrayList<User>();
    private JTextField usernameField, passwordField;
    private JButton loginButton, registerButton;

    public LoginRegisterSystem() {
        super("Login/Register System");

        // Criação dos campos de entrada de usuário e senha
        JPanel panel = new JPanel(new GridLayout(3, 2));
        panel.add(new JLabel("Username:"));
        usernameField = new JTextField();
        panel.add(usernameField);
        panel.add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        panel.add(passwordField);
        loginButton = new JButton("Login");
        loginButton.addActionListener(this);
        panel.add(loginButton);
        registerButton = new JButton("Register");
        registerButton.addActionListener(this);
        panel.add(registerButton);

        // Adiciona os componentes ao frame
        add(panel);
        setSize(300, 150);
        setVisible(true);
    }

    // Evento de clique nos botões
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            String username = usernameField.getText();
            String password = passwordField.getText();
            User user = findUser(username, password);
            if (user != null) {
                JOptionPane.showMessageDialog(null, "Login successful!");
                HomeScreen home = new HomeScreen();
                home.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Invalid username or password!");
            }
        } else if (e.getSource() == registerButton) {
            String username = usernameField.getText();
            String password = passwordField.getText();
            User user = findUser(username, password);
            if (user != null) {
                JOptionPane.showMessageDialog(null, "User already exists!");
            } else {
                users.add(new User(username, password));
                JOptionPane.showMessageDialog(null, "Registration successful!");
            }
        }
    }

    // Encontra um usuário com o username e password fornecidos
    private User findUser(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        new LoginRegisterSystem();
    }

    class HomeScreen extends JFrame {
        public HomeScreen() {
            super("Home Screen");
            setSize(400, 300);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
    }
}
