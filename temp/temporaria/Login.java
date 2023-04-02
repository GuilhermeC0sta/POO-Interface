import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Login extends JFrame {

    // Componentes da interface
    private JLabel titulo;
    private JLabel nomeLabel;
    private JTextField nomeField;
    private JLabel emailLabel;
    private JTextField emailField;
    private JLabel senhaLabel;
    private JPasswordField senhaField;
    private JButton criarContaButton;
    private JButton registrarButton;

    public Login() {
        // Configurações da janela
        setTitle("Login");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Criação dos componentes
        titulo = new JLabel("Criação de Nova Conta");
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        nomeLabel = new JLabel("Nome:");
        nomeField = new JTextField();
        emailLabel = new JLabel("E-mail:");
        emailField = new JTextField();
        senhaLabel = new JLabel("Senha:");
        senhaField = new JPasswordField();
        criarContaButton = new JButton("Criar Conta");
        registrarButton = new JButton("Registrar");

        // Criação do painel principal
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // Adição dos componentes ao painel principal
        mainPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(titulo);
        mainPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(nomeLabel);
        mainPanel.add(nomeField);
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(emailLabel);
        mainPanel.add(emailField);
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(senhaLabel);
        mainPanel.add(senhaField);
        mainPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(criarContaButton);
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(registrarButton);

        // Adição do painel principal à janela
        add(mainPanel);

        // Configuração do botão "Criar Conta"
        criarContaButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Criação de uma nova conta com as informações do formulário
                String nome = nomeField.getText();
                String email = emailField.getText();
                String senha = new String(senhaField.getPassword());
                conta novaConta = new comum(nome, email, senha, 0);

                // Fechar janela de criação de conta
                dispose();
            }
        });

        // Configuração do botão "Registrar"
        registrarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Criação e exibição da janela de registro
                TelaDeRegistro telaRegistro = new TelaDeRegistro();
                telaRegistro.setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        // Criação e exibição da janela
        Login gui = new Login();
        gui.setVisible(true);
    }
}