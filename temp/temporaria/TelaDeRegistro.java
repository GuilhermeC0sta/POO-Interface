import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TelaDeRegistro extends JFrame {

    private JLabel titleLabel, nameLabel, emailLabel, passwordLabel;
    private JTextField nameField, emailField;
    private JPasswordField passwordField;
    private JButton registerButton, backButton;

    public TelaDeRegistro() {
        super("Registrar Conta");
        setLayout(new BorderLayout());

        // Panel para o título
        JPanel titlePanel = new JPanel();
        titleLabel = new JLabel("Preencha os dados abaixo para criar sua conta");
        titlePanel.add(titleLabel);
        add(titlePanel, BorderLayout.NORTH);

        // Panel para o formulário
        JPanel formPanel = new JPanel(new GridLayout(4, 2));
        nameLabel = new JLabel("Nome:");
        nameField = new JTextField(20);
        emailLabel = new JLabel("Email:");
        emailField = new JTextField(20);
        passwordLabel = new JLabel("Senha:");
        passwordField = new JPasswordField(20);
        formPanel.add(nameLabel);
        formPanel.add(nameField);
        formPanel.add(emailLabel);
        formPanel.add(emailField);
        formPanel.add(passwordLabel);
        formPanel.add(passwordField);
        add(formPanel, BorderLayout.CENTER);

        // Panel para os botões
        JPanel buttonPanel = new JPanel();
        registerButton = new JButton("Registrar");
        backButton = new JButton("Voltar");
        buttonPanel.add(registerButton);
        buttonPanel.add(backButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Adiciona a ação para o botão de registrar
        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Coloque aqui a lógica para registrar a conta
                JOptionPane.showMessageDialog(null, "Conta criada com sucesso!");
                dispose(); // Fecha a janela de registro após a criação da conta
            }
        });

        // Adiciona a ação para o botão de voltar
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // Fecha a janela de registro e volta para a tela anterior
            }
        });

        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        new TelaDeRegistro();
    }
}