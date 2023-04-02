import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import java.util.ArrayList;

public class LoginRegister extends JFrame implements ActionListener {
    public ArrayList<conta> contas = new ArrayList<conta>();
    public ArrayList<Livro> livros = new ArrayList<Livro>();

    private JButton login, register;
    private JTextField usuario, senha;

    public LoginRegister() {
        super("Libraric");

        JPanel panel = new JPanel(new GridLayout(3, 2));
        panel.add(new JLabel("Usuario:"));
        usuario = new JTextField();
        panel.add(usuario);
        panel.add(new JLabel("Senha:"));
        senha = new JPasswordField();
        panel.add(senha);
        login = new JButton("Login");
        login.addActionListener(this);
        panel.add(login);
        register = new JButton("Register");
        register.addActionListener(this);
        panel.add(register);

        add(panel);
        setSize(300, 150);
        setVisible(true);

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == login) {
            String usuario2 = usuario.getText();
            String senha2 = senha.getText();
            conta conta2 = findUser(usuario2, senha2);
            if (conta2 != null) {
                JOptionPane.showMessageDialog(null, "Logado com sucesso!");
                TelaInicial home = new TelaInicial();
                home.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Usuario/senha inválidos!");
            }
        } else if (e.getSource() == register) {
            Reg register = new Reg();
            register.setVisible(true);
        }
    }

    class Reg extends JFrame implements ActionListener {

        private JTextField nome, email, senha, plano;
        private JButton register;
        private JLabel titleLabel;
        int id = 1;

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
            plano = new JTextField();
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
            add(panel, BorderLayout.CENTER);
            add(panel2, BorderLayout.SOUTH);
            setVisible(true);

        }

        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == register) {
                String usuario = nome.getText();
                String email2 = email.getText();
                String plano2 = plano.getText();
                String senha2 = senha.getText();

                conta contas2 = findUser(usuario, senha2);

                if (contas2 != null) {
                    JOptionPane.showMessageDialog(null, "Esse usuário já existe!");
                } else {
                    if (plano2.equals("premium")) {
                        contas.add(new premium(email2, senha2, usuario, id));
                        id += 1;
                        JOptionPane.showMessageDialog(null, "Conta premium registrada com sucesso!");
                    }
                    if (plano2.equals("comum")) {
                        contas.add(new comum(email2, senha2, usuario, id));
                        id += 1;
                        JOptionPane.showMessageDialog(null, "Conta comum registrada com sucesso!");
                    }
                    if (plano2.equals("administrador")) {
                        contas.add(new admin(email2, senha2, usuario, id));
                        id += 1;
                        JOptionPane.showMessageDialog(null, "Conta administador registrada com sucesso!");
                    }
                }
            }
        }
    }

    private conta findUser(String usuario, String senha) {
        for (conta contas : contas) {
            if (contas.getNome().equals(usuario) && contas.getSenha().equals(senha)) {
                return contas;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        new LoginRegister();
    }

    class TelaInicial extends JFrame implements ActionListener {

        private JButton audios, books, locar, editar, mostrar, devolver, verificar, pagarmultas, vermultas,
                verutensilios;
        private JLabel titleLabel;

        public TelaInicial() {
            super("Tela inicial");
            setLayout(new BorderLayout());
            setSize(400, 400);
            JPanel titlePanel = new JPanel();
            titleLabel = new JLabel("Escolha o que deseja fazer: ");
            titlePanel.add(titleLabel);
            add(titlePanel, BorderLayout.NORTH);

            Dimension botaoDimensao = new Dimension(250, 30);
            livros.add(new Livro("teste", "Micael", 123, 10, "luta"));

            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            audios = new JButton("Locar audiobook");
            audios.setPreferredSize(botaoDimensao);
            audios.setMaximumSize(botaoDimensao);
            audios.setMinimumSize(botaoDimensao);
            audios.addActionListener(this);
            audios.setAlignmentX(Component.CENTER_ALIGNMENT);

            books = new JButton("Locar livro");
            books.setPreferredSize(botaoDimensao);
            books.setMaximumSize(botaoDimensao);
            books.setMinimumSize(botaoDimensao);
            books.addActionListener(this);
            books.setAlignmentX(Component.CENTER_ALIGNMENT);
            books.setAlignmentY(Component.TOP_ALIGNMENT);

            locar = new JButton("Locar livro/audiobook");
            locar.setPreferredSize(botaoDimensao);
            locar.setMaximumSize(botaoDimensao);
            locar.setMinimumSize(botaoDimensao);
            locar.addActionListener(this);
            locar.setAlignmentX(Component.CENTER_ALIGNMENT);
            panel.add(locar);
            panel.add(Box.createRigidArea(new Dimension(0, 10)));

            editar = new JButton("Editar perfil");
            editar.setPreferredSize(botaoDimensao);
            editar.setMaximumSize(botaoDimensao);
            editar.setMinimumSize(botaoDimensao);
            editar.addActionListener(this);
            panel.add(editar);
            editar.setAlignmentX(Component.CENTER_ALIGNMENT);
            panel.add(Box.createRigidArea(new Dimension(0, 10)));

            mostrar = new JButton("Mostrar livros/audiobooks disponíveis");
            mostrar.setPreferredSize(botaoDimensao);
            mostrar.setMaximumSize(botaoDimensao);
            mostrar.setMinimumSize(botaoDimensao);
            mostrar.addActionListener(this);
            panel.add(mostrar);
            mostrar.setAlignmentX(Component.CENTER_ALIGNMENT);
            panel.add(Box.createRigidArea(new Dimension(0, 10)));

            devolver = new JButton("Devolver livros/audiobooks locados");
            devolver.setPreferredSize(botaoDimensao);
            devolver.setMaximumSize(botaoDimensao);
            devolver.setMinimumSize(botaoDimensao);
            devolver.addActionListener(this);
            panel.add(devolver);
            devolver.setAlignmentX(Component.CENTER_ALIGNMENT);
            panel.add(Box.createRigidArea(new Dimension(0, 10)));

            verificar = new JButton("Verificar livros/audiobooks locados");
            verificar.setPreferredSize(botaoDimensao);
            verificar.setMaximumSize(botaoDimensao);
            verificar.setMinimumSize(botaoDimensao);
            verificar.addActionListener(this);
            panel.add(verificar);
            verificar.setAlignmentX(Component.CENTER_ALIGNMENT);
            panel.add(Box.createRigidArea(new Dimension(0, 10)));

            vermultas = new JButton("Ver multas pendentes");
            vermultas.setPreferredSize(botaoDimensao);
            vermultas.setMaximumSize(botaoDimensao);
            vermultas.setMinimumSize(botaoDimensao);
            vermultas.addActionListener(this);
            panel.add(vermultas);
            vermultas.setAlignmentX(Component.CENTER_ALIGNMENT);
            panel.add(Box.createRigidArea(new Dimension(0, 10)));

            pagarmultas = new JButton("Pagar multas");
            pagarmultas.setPreferredSize(botaoDimensao);
            pagarmultas.setMaximumSize(botaoDimensao);
            pagarmultas.setMinimumSize(botaoDimensao);
            pagarmultas.addActionListener(this);
            panel.add(pagarmultas);
            pagarmultas.setAlignmentX(Component.CENTER_ALIGNMENT);
            panel.add(Box.createRigidArea(new Dimension(0, 10)));

            verutensilios = new JButton("Ver utensilios disponíveis");
            verutensilios.setPreferredSize(botaoDimensao);
            verutensilios.setMaximumSize(botaoDimensao);
            verutensilios.setMinimumSize(botaoDimensao);
            verutensilios.addActionListener(this);
            panel.add(verutensilios);
            verutensilios.setAlignmentX(Component.CENTER_ALIGNMENT);
            panel.add(Box.createRigidArea(new Dimension(0, 10)));

            add(panel);
            setVisible(true);

        }

        public void actionPerformed(ActionEvent e) {

            if (e.getSource() == locar) {
                JPanel panel3 = new JPanel();
                JFrame frameLocar = new JFrame();
                frameLocar.setSize(400, 400);
                frameLocar.add(panel3);

                // add(frameLocar);
                panel3.add(books);
                panel3.add(Box.createRigidArea(new Dimension(0, 50)));
                panel3.add(audios);

                books.setAlignmentX(Component.CENTER_ALIGNMENT);
                audios.setAlignmentX(Component.CENTER_ALIGNMENT);
                frameLocar.setVisible(true);

                books.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        JPanel panel4 = new JPanel();
                        JFrame frameLocar2 = new JFrame();
                        frameLocar2.setSize(400, 400);
                        frameLocar2.add(panel4);
                        frameLocar2.setVisible(true);
                        // aqui eu tenho que mostrar todos os livros
                        for (Livro livro : livros) {
                            if (livro instanceof Livro) {
                                JLabel tituloLabel = new JLabel("Título: " + livro.getTitulo());
                                JLabel isbnLabel = new JLabel("ISBN: " + livro.getIsbn());
                                JLabel qntdLabel = new JLabel("Quantidade: " + livro.getQnt_disp());
                                // adicionar componentes ao painel central
                                panel4.add(tituloLabel);
                                panel4.add(isbnLabel);
                                panel4.add(qntdLabel);

                                //getContentPane().add(books, BorderLayout.CENTER);
                                revalidate(); // atualizar layout do JFrame
                            }
                        }
                    }
                });

                audios.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        // aqui eu tenho que mostrar todos os audios
                    }
                });
            }
        }
        // antes eu tinha criado uma classe livrosFrame

    }
}
