import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.regex.*;
import java.util.ArrayList;

public class LoginRegister extends JFrame implements ActionListener {

    public static ArrayList<conta> contas = new ArrayList<conta>();
    public static ArrayList<Livro> livros = new ArrayList<Livro>();
    public static ArrayList<audiobook> audiobook2 = new ArrayList<audiobook>();
    public static ArrayList<utensilios> utensilios = new ArrayList<utensilios>();
    public static ArrayList<Integer> id_user = new ArrayList<>();
    public static ArrayList<Integer> id_userAudio = new ArrayList<>();
    public static ArrayList<Integer> isbn_locado = new ArrayList<>();
    public static ArrayList<Integer> isbn_devolvido = new ArrayList<>();
    public static ArrayList<Integer> audio_locado = new ArrayList<>();
    public static ArrayList<Integer> id_devolvido = new ArrayList<>();
    public static ArrayList<Integer> id_multapendente = new ArrayList<>();
    public static ArrayList<Integer> id_multapaga = new ArrayList<>();
    public static ArrayList<Integer> id_multa = new ArrayList<>();
    public static String ex_user;

    public int sinal = 0;
    public int id = 0;
    public static int index_user;
    public int contalocados = 0;
    public int auxteste = 0;
    public int auxteste3 = 0;
    public int auxteste2 = 0;
    public int multaalarme = 0;
    public int testemultas = 0;

    public JButton login, register;
    public JTextField usuario, senha;

    public LoginRegister() {
        super("Libraric");

        JPanel panel = new JPanel(new GridLayout(4, 2));
        JPanel imagePanel = new JPanel();
        JLabel imageLabel = new JLabel(new ImageIcon("lib2.jpg"));
        imageLabel.setPreferredSize(new Dimension(350, 250));
        imagePanel.add(imageLabel);

        panel.add(new JLabel("E-mail:"));
        usuario = new JTextField();
        panel.add(usuario);
        panel.add(new JLabel("Senha:"));
        senha = new JPasswordField();
        panel.add(senha);

        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        login = new JButton("Login");
        login.addActionListener(this);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(login);

        register = new JButton("Register");
        register.addActionListener(this);
        panel.add(register);

        add(imagePanel, BorderLayout.NORTH);
        add(panel);
        setSize(550, 400);
        setLocationRelativeTo(null);
        setVisible(true);

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == login) {
            String usuario2 = usuario.getText();
            String senha2 = senha.getText();
            conta conta2 = findUser(usuario2, senha2);
            if (conta2 != null) {
                JOptionPane.showMessageDialog(null, "Logado com sucesso!");
                for (int i = 0; i < contas.size(); i++) {
                    if (contas.get(i).getEmail().equals(usuario2) && contas.get(i).getSenha().equals(senha2)) {
                        index_user = i;
                        System.out.println("Login realizado com sucesso! " + index_user);
                        setExtendedState(JFrame.ICONIFIED);
                    }
                }


                TelaInicial home = new TelaInicial(this);

                // Verificar estado da conta e instanciar classe de estado apropriada
                String tipoConta = contas.get(index_user).getPlano();
                EstadoConta estadoConta;
                if (tipoConta.equalsIgnoreCase("administrador")) {
                    estadoConta = new EstadoContaAdministrador(home);
                } else {
                    estadoConta = new EstadoContaComum(home);
                }
    
                // Executar ações correspondentes ao estado atual do sistema
                estadoConta.mostrarTela();


            } else {
                JOptionPane.showMessageDialog(null, "Usuario/senha inválidos!");
            }
        } else if (e.getSource() == register) {
            Reg register = new Reg();
            register.setVisible(true);
        }
        setLocationRelativeTo(null);
    }

    class Reg extends JFrame implements ActionListener {

        private JTextField nome, email, senha;
        private JButton register;
        private JLabel titleLabel;
        private JComboBox<String> plano;

        public Reg() {
            super("Registrar Conta");
            setLayout(new BorderLayout());
            setSize(400, 300);

            JPanel panel = new JPanel(new GridLayout(6, 1));

            JPanel titlePanel = new JPanel();
            titleLabel = new JLabel("Preencha os dados abaixo para criar sua conta");
            titlePanel.add(titleLabel);
            add(titlePanel, BorderLayout.NORTH);

            panel.add(new JLabel("Plano:"));
            DefaultComboBoxModel<String> planoModel = new DefaultComboBoxModel<>();
            planoModel.addElement("comum");
            planoModel.addElement("premium");
            planoModel.addElement("administrador");
            plano = new JComboBox<>(planoModel);
            panel.add(plano);

            panel.add(new JLabel("Email:"));
            email = new JTextField();
            panel.add(email);

            panel.add(new JLabel("Nome:"));
            nome = new JTextField();
            panel.add(nome);

            panel.add(new JLabel("Senha:"));
            senha = new JPasswordField();
            panel.add(senha);

            JPanel panel2 = new JPanel();
            register = new JButton("Registrar");
            register.addActionListener(this);
            panel2.add(register);

            add(panel);
            add(panel2, BorderLayout.SOUTH);
            setLocationRelativeTo(null);
            setVisible(true);
        }

        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == register) {
                String usuario = nome.getText();
                String email2 = email.getText();
                String plano2 = plano.getSelectedItem().toString();
                String senha2 = senha.getText();

                conta contas2 = checkEmail(email2);
                if (isEmailValid(email2)) {

                    if (contas2 != null) {
                        JOptionPane.showMessageDialog(null, "Esse usuário já existe!");
                    } else if (senha2.length() < 8) {
                        JOptionPane.showMessageDialog(null, "A senha deve possuir pelo menos 8 caracteres!");
                    } else if (usuario.length() < 5) {
                        JOptionPane.showMessageDialog(null,
                                "O usuário precisa ter um username maior que 5 caracteres!");
                    } else {
                        if (plano2.equals("premium")) {
                            contas.add(new premium(email2, senha2, usuario, id));
                            contas.get(id).defPlano("premium");
                            id += 1;
                            JOptionPane.showMessageDialog(null, "Conta premium registrada com sucesso!");
                            dispose();
                        }
                        if (plano2.equals("comum")) {
                            contas.add(new comum(email2, senha2, usuario, id));
                            contas.get(id).defPlano("comum");
                            id += 1;
                            JOptionPane.showMessageDialog(null, "Conta comum registrada com sucesso!");
                            dispose();
                        }
                        if (plano2.equals("administrador")) {
                            contas.add(new admin(email2, senha2, usuario, id));
                            contas.get(id).defPlano("administrador");
                            id += 1;
                            JOptionPane.showMessageDialog(null, "Conta administador registrada com sucesso!");
                            dispose();
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Esse email é inválido!");
                }

            }
        }
    }


    public void capa2(ArrayList<conta> teste){
        for (conta user : teste) {
            System.out.println(user.getNome());
        }
    }

    private conta findUser(String usuario, String senha) {
        for (conta contas : contas) {
            if (contas.getEmail().equals(usuario) && contas.getSenha().equals(senha)) {
                return contas;
            }
        }
        return null;
    }

    private conta checkEmail(String usuario) {
        for (conta contas : contas) {
            if (contas.getEmail().equals(usuario)) {
                return contas;
            }
        }
        return null;
    }

    public class IOException extends RuntimeException {
        public IOException(String message) {
            super(message);
        }
    }

    public static boolean isEmailValid(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@gmail+.com+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        String outlook = "^[A-Za-z0-9+_.-]+@outlook+.com+$";
        Pattern patternoutlook = Pattern.compile(outlook);
        Matcher matcheroutlook = patternoutlook.matcher(email);
        String hotmail = "^[A-Za-z0-9+_.-]+@hotmail+.com+$";
        Pattern patternhotmail = Pattern.compile(hotmail);
        Matcher matcherhotmail = patternhotmail.matcher(email);
        String ic = "^[A-Za-z0-9+_.-]+@ic.ufal.br+$";
        Pattern patternic = Pattern.compile(ic);
        Matcher matcheric = patternic.matcher(email);

        return matcher.matches() || matcheroutlook.matches() || matcherhotmail.matches() || matcheric.matches();
    }

    public static void main(String[] args) {
        new LoginRegister();
    }

    class TelaInicial extends JFrame implements ActionListener {
        private JLabel titleLabel;
        
        public TelaInicial(LoginRegister loginRegister) {
            super("Tela inicial");
            setLayout(new BorderLayout());
            setSize(400, 400);
            JPanel titlePanel = new JPanel();
            titleLabel = new JLabel("Escolha o que deseja fazer: ");
            titlePanel.add(titleLabel);
            add(titlePanel, BorderLayout.NORTH);
            setLocationRelativeTo(null);

            Dimension botaoDimensao = new Dimension(250, 30);

            if (sinal == 0) { // criando informações
                livros.add(new Livro("LivroExemplo", "Guilherme", 10, 0, "luta"));
                livros.add(new Livro("LivroExemplo", "Janio", 1234, 150, "romance"));
                audiobook2.add(new audiobook("audioExemplo", "Micael", 120, 0, "aventura", 25));
                audiobook2.add(new audiobook("audioExemplo", "Castelo", 120, 20, "ação", 255));
                utensilios.add(new postit("FaberCastel", "Azul", 2));
                utensilios.add(new marca_texto("Stabilo Boss", "Roxo", "Sem glitter", 10));
                utensilios.add(new apoio_livros("Maxcril", 10, 10));
                id_multapendente.add(0);
                id_multapendente.add(0);
                sinal++;
            }
        }

        public void actionPerformed(ActionEvent e) {
    }
}
}
