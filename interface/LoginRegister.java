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

    public int sinal = 0;
    public int id = 0;
    public int index_user;
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
                home.setVisible(true);
                LoginRegister dados = new LoginRegister();

                // Verificar estado da conta e instanciar classe de estado apropriada
                String tipoConta = contas.get(index_user).getPlano();
                EstadoConta estadoConta;
                if (tipoConta.equalsIgnoreCase("administrador")) {
                    estadoConta = new EstadoContaAdministrador(home, contas, dados);
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

    public ArrayList<conta> getContas(){
        return contas;
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

        private JButton audios, books, locar, editar, mostrar, devolver, verificar, pagarmultas, vermultas,
                verutensilios, mostrarTudo, marca_textobutton;
        private JButton add_itens, remover_itens, livros_devolvidos, cadastros, buttonPGM, buttonconfirmar, buttonCP;
        private JLabel titleLabel;
        public int isbn;
        public int codigoAudio;
        public int codigoR;

        JFrame frameLocar2 = new JFrame();
        JFrame frameLocarA = new JFrame();
        JFrame frameLocar3 = new JFrame();
        JFrame frameEditar = new JFrame();

        JPanel panel3 = new JPanel();
        JPanel panelEditar = new JPanel();
        JPanel panel6 = new JPanel();
        JPanel panelUtensilios = new JPanel();
        JPanel panel5 = new JPanel();
        JPanel panelLocadosA = new JPanel();

        JFrame frameMostrar = new JFrame();
        JFrame frameVerificar = new JFrame();
        JFrame frameLocar = new JFrame();
        JFrame frameDevolver = new JFrame();
        JFrame frameUtensilio = new JFrame();
        JFrame frameMultaPendente = new JFrame();

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

            if (contas.get(index_user).getPlano().equalsIgnoreCase("comum")
                    || contas.get(index_user).getPlano().equalsIgnoreCase("premium")) {
                JPanel panel = new JPanel();
                panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
                audios = new JButton("Mostrar audiobooks");
                audios.setPreferredSize(botaoDimensao);
                audios.setMaximumSize(botaoDimensao);
                audios.setMinimumSize(botaoDimensao);
                audios.addActionListener(this);
                audios.setAlignmentX(Component.CENTER_ALIGNMENT);

                mostrarTudo = new JButton("Mostrar todos os itens locados");
                mostrarTudo.setPreferredSize(botaoDimensao);
                mostrarTudo.setMaximumSize(botaoDimensao);
                mostrarTudo.setMinimumSize(botaoDimensao);
                mostrarTudo.addActionListener(this);
                mostrarTudo.setAlignmentX(Component.CENTER_ALIGNMENT);

                buttonPGM = new JButton("Pagar multa");
                buttonPGM.setPreferredSize(botaoDimensao);
                buttonPGM.setMaximumSize(botaoDimensao);
                buttonPGM.setMinimumSize(botaoDimensao);
                buttonPGM.addActionListener(this);
                buttonPGM.setAlignmentX(Component.CENTER_ALIGNMENT);

                marca_textobutton = new JButton("Mostrar os utensilios em geral");
                marca_textobutton.setPreferredSize(botaoDimensao);
                marca_textobutton.setMaximumSize(botaoDimensao);
                marca_textobutton.setMinimumSize(botaoDimensao);
                marca_textobutton.addActionListener(this);
                marca_textobutton.setAlignmentX(Component.CENTER_ALIGNMENT);

                books = new JButton("Mostrar livros");
                books.setPreferredSize(botaoDimensao);
                books.setMaximumSize(botaoDimensao);
                books.setMinimumSize(botaoDimensao);
                books.addActionListener(this);
                books.setAlignmentX(Component.CENTER_ALIGNMENT);

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

            if (e.getSource() == mostrar) {
                // JPanel panel3 = new JPanel();
                // JFrame frameMostrar = new JFrame();
                frameMostrar.setSize(400, 400);
                frameMostrar.add(panel3);
                // titleLabel = new JLabel("Escolha uma das duas opções abaixo: ");
                // panel3.add(titleLabel);
                // add(frameMostrar);
                panel3.add(books);
                panel3.add(Box.createRigidArea(new Dimension(0, 50)));
                panel3.add(audios);

                books.setAlignmentX(Component.CENTER_ALIGNMENT);
                audios.setAlignmentX(Component.CENTER_ALIGNMENT);
                frameMostrar.setVisible(true);
                frameMostrar.setLocationRelativeTo(null);

                books.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        JPanel panel4 = new JPanel(new GridLayout(0, 3));
                        JFrame frameLocar2 = new JFrame();
                        frameLocar2.setSize(400, 400);
                        frameLocar2.add(panel4);
                        frameLocar2.setVisible(true);
                        frameLocar2.setLocationRelativeTo(null);
                        panel4.removeAll(); // remove todos os componentes do painel

                        for (Livro livro : livros) {
                            if (livro instanceof Livro) {
                                JLabel tituloLabel2 = new JLabel("Título: " + livro.getTitulo());
                                JLabel isbnLabel = new JLabel("ISBN: " + livro.getIsbn());
                                JLabel qntdLabel = new JLabel("Quantidade: " + livro.getQnt_disp());
                                // adicionar componentes ao painel central
                                panel4.add(tituloLabel2);
                                panel4.add(isbnLabel);
                                panel4.add(qntdLabel);
                            }
                        }

                        panel4.revalidate();
                        panel4.repaint();
                        frameLocar2.revalidate(); // Atualiza o layout da janela

                        // adiciona o WindowListener para a janela
                        frameLocar2.addWindowListener(new WindowAdapter() {
                            public void windowClosing(WindowEvent e) {
                                frameLocar2.dispose(); // apaga a janela
                            }
                        });
                    }
                });

                audios.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        JPanel panel4 = new JPanel(new GridLayout(0, 3));
                        JFrame frameLocar3 = new JFrame();
                        frameLocar3.setSize(400, 400);
                        frameLocar3.add(panel4);
                        frameLocar3.setVisible(true);
                        frameLocar3.setLocationRelativeTo(null);
                        for (audiobook audio : audiobook2) {
                            if (audio instanceof audiobook) {
                                JLabel tituloLabel3 = new JLabel("Título: " + audio.getTitulo());
                                JLabel codigoLabel = new JLabel("Codigo do áudio: " + audio.getAudio());
                                JLabel qntdLabel3 = new JLabel("Quantidade: " + audio.getQnt_disp());
                                // adicionar componentes ao painel central
                                panel4.add(tituloLabel3);
                                panel4.add(codigoLabel);
                                panel4.add(qntdLabel3);

                                panel4.revalidate();
                                panel4.repaint();
                                frameLocar3.revalidate(); // Atualiza o layout da janela
                            }
                        }

                        // adiciona o WindowListener para a janela
                        frameLocar3.addWindowListener(new WindowAdapter() {
                            public void windowClosing(WindowEvent e) {
                                frameLocar3.dispose(); // apaga a janela
                            }
                        });
                    }
                });
            } else if (e.getSource() == locar) {
                // Cria caixas de texto para nome, email e bio
                JComboBox<String> itemField;
                JTextField codigoField = new JTextField(20);

                // Cria um painel para as caixas de texto
                JPanel panelLocar = new JPanel();
                setLocationRelativeTo(null);
                panelLocar.setLayout(new GridLayout(3, 2)); // GridLayout com 3 linhas e 2 colunas
                panelLocar.add(new JLabel("Digite se quer um livro/audiobook:"));
                DefaultComboBoxModel<String> planoModel = new DefaultComboBoxModel<>();
                planoModel.addElement("livro");
                planoModel.addElement("audiobook");
                itemField = new JComboBox<>(planoModel);
                panelLocar.add(itemField);
                panelLocar.add(new JLabel("Digite o codigo do livro/audiobook:"));
                panelLocar.add(codigoField);

                // Exibe um JOptionPane com as caixas de texto e espera o usuário clicar em OK
                int resultLocar = JOptionPane.showConfirmDialog(null, panelLocar, "Locar",
                        JOptionPane.OK_CANCEL_OPTION);
                contalocados = 0;
                if (resultLocar == JOptionPane.OK_OPTION) {
                    // Obtém o texto digitado nas caixas de texto
                    // String item = itemField.getText();
                    // String codigo = codigoField.getText();

                    for (int k = 0; k < id_user.size(); k++) {
                        if (id_user.get(k) == index_user) {
                            contalocados += 1;
                        }
                    }

                    if (contas.get(index_user).getPlano().equalsIgnoreCase("comum")) {
                        if (contalocados < 1) {
                            if (itemField.getSelectedItem().toString().equalsIgnoreCase("livro")) {
                                try {
                                    isbn = Integer.parseInt(codigoField.getText());
                                    for (int k = 0; k < livros.size(); k++) {
                                        if (isbn == livros.get(k).getIsbn()) {
                                            if (livros.get(k).getQnt_disp() == 0) {
                                                JOptionPane.showMessageDialog(null,
                                                        "Livro indisponível, tente novamente em outro momento.");
                                                break;
                                            } else if (livros.get(k).getQnt_disp() > 0) {
                                                int quantidadeL = livros.get(k).getQnt_disp() - 1;
                                                livros.set(k,
                                                        new Livro(livros.get(k).getTitulo(), livros.get(k).getAutor(),
                                                                isbn, quantidadeL, livros.get(k).getGenero()));
                                                JOptionPane.showMessageDialog(null,
                                                        "Parabéns, você conseguiu locar um livro!");
                                                id_user.add(contas.get(index_user).getId());
                                                isbn_locado.add(isbn);
                                                break;
                                            }
                                        } else if (k == livros.size() - 1) {
                                            JOptionPane.showMessageDialog(null, "Código ISBN não encontrado.");
                                            break;
                                        }
                                    }
                                } catch (NumberFormatException ex) {
                                    JOptionPane.showMessageDialog(null, "Código ISBN inválido.");
                                }
                            } else if (itemField.getSelectedItem().toString().equalsIgnoreCase("audiobook")) {
                                try {
                                    codigoAudio = Integer.parseInt(codigoField.getText());
                                    for (int k = 0; k < audiobook2.size(); k++) {
                                        if (codigoAudio == audiobook2.get(k).getAudio()) {
                                            if (audiobook2.get(k).getQnt_disp() == 0) {
                                                JOptionPane.showMessageDialog(null,
                                                        "Audiobook indisponível, tente novamente em outro momento.");
                                                break;
                                            } else if (audiobook2.get(k).getQnt_disp() > 0) {
                                                int quantidadeA = audiobook2.get(k).getQnt_disp() - 1;
                                                audiobook2.set(k,
                                                        new audiobook(audiobook2.get(k).getTitulo(),
                                                                audiobook2.get(k).getAutor(),
                                                                audiobook2.get(k).getDuracao(), quantidadeA,
                                                                audiobook2.get(k).getGenero(), codigoAudio));
                                                JOptionPane.showMessageDialog(null,
                                                        "Parabéns, você conseguiu locar um audiobook!");
                                                id_userAudio.add(contas.get(index_user).getId());
                                                audio_locado.add(codigoAudio);
                                                break;
                                            }
                                        } else if (k == audiobook2.size() - 1) {
                                            JOptionPane.showMessageDialog(null, "Código ISBN não encontrado.");
                                            break;
                                        }
                                    }
                                } catch (NumberFormatException ex) {
                                    JOptionPane.showMessageDialog(null, "Código do audiobook inválido.");
                                }
                            }
                            contalocados += 1;
                        } else {
                            JOptionPane.showMessageDialog(null,
                                    "Você não pode locar mais de um item pois a sua conta é comum");
                        }
                    }

                    if (contas.get(index_user).getPlano().equalsIgnoreCase("premium")) {
                        if (contalocados < 15) {
                            if (itemField.getSelectedItem().toString().equalsIgnoreCase("livro")) {
                                try {
                                    isbn = Integer.parseInt(codigoField.getText());
                                    for (int k = 0; k < livros.size(); k++) {
                                        if (isbn == livros.get(k).getIsbn()) {
                                            if (livros.get(k).getQnt_disp() == 0) {
                                                JOptionPane.showMessageDialog(null,
                                                        "Livro indisponível, tente novamente em outro momento.");
                                                break;
                                            } else if (livros.get(k).getQnt_disp() > 0) {
                                                int quantidadeL = livros.get(k).getQnt_disp() - 1;
                                                livros.set(k,
                                                        new Livro(livros.get(k).getTitulo(), livros.get(k).getAutor(),
                                                                isbn, quantidadeL, livros.get(k).getGenero()));
                                                JOptionPane.showMessageDialog(null,
                                                        "Parabéns, você conseguiu locar um livro!");
                                                id_user.add(contas.get(index_user).getId());
                                                isbn_locado.add(isbn);
                                                break;
                                            }
                                        } else if (k == livros.size() - 1) {
                                            JOptionPane.showMessageDialog(null, "Código ISBN não encontrado.");
                                            break;
                                        }
                                    }
                                } catch (NumberFormatException ex) {
                                    JOptionPane.showMessageDialog(null, "Código ISBN inválido.");
                                }
                            } else if (itemField.getSelectedItem().toString().equalsIgnoreCase("audiobook")) {
                                try {
                                    codigoAudio = Integer.parseInt(codigoField.getText());
                                    for (int k = 0; k < audiobook2.size(); k++) {
                                        if (codigoAudio == audiobook2.get(k).getAudio()) {
                                            if (audiobook2.get(k).getQnt_disp() == 0) {
                                                JOptionPane.showMessageDialog(null,
                                                        "Audiobook indisponível, tente novamente em outro momento.");
                                                break;
                                            } else if (audiobook2.get(k).getQnt_disp() > 0) {
                                                audiobook2.set(k, new audiobook(audiobook2.get(k).getTitulo(),
                                                        audiobook2.get(k).getAutor(), audiobook2.get(k).getDuracao(),
                                                        audiobook2.get(k).getQnt_disp() - 1,
                                                        audiobook2.get(k).getGenero(), codigoAudio));
                                                JOptionPane.showMessageDialog(null,
                                                        "Parabéns, você conseguiu locar um audiobook!");
                                                id_userAudio.add(index_user);
                                                audio_locado.add(codigoAudio);
                                                break;
                                            }
                                        } else if (k == audiobook2.size() - 1) {
                                            JOptionPane.showMessageDialog(null, "Código de audiobook não encontrado.");
                                            break;
                                        }
                                    }
                                } catch (NumberFormatException ex) {
                                    JOptionPane.showMessageDialog(null, "Código de audiobook inválido.");
                                }
                            }
                            contalocados += 1;
                        } else {
                            JOptionPane.showMessageDialog(null, "Você só pode locar até no máximo 15 itens");
                        }
                    }

                }
            } else if (e.getSource() == verificar) {
                auxteste3 = 0;
                auxteste = 0;
                frameVerificar.setSize(400, 400);
                frameVerificar.add(panel5);

                panel5.add(mostrarTudo);
                panel5.add(Box.createRigidArea(new Dimension(0, 50)));

                mostrarTudo.setAlignmentX(Component.CENTER_ALIGNMENT);
                frameVerificar.setVisible(true);
                frameVerificar.setLocationRelativeTo(null);

                mostrarTudo.addActionListener(new ActionListener() {
                    boolean janelaAberta = false;

                    public void actionPerformed(ActionEvent e) {
                        if (!janelaAberta) {
                            janelaAberta = true;
                            JPanel panelLocados = new JPanel();
                            setLocationRelativeTo(null);
                            frameLocarA.setSize(400, 400);
                            frameLocarA.add(panelLocados);
                            frameLocarA.setVisible(true);
                            frameLocarA.setLocationRelativeTo(null);

                            if (id_userAudio.size() == 0 && auxteste3 == 0) {
                                JOptionPane.showMessageDialog(null, "NAO HÁ AUDIOS LOCADOS");
                                auxteste3 = 1;
                            } else {
                                for (int k = 0; k < id_userAudio.size(); k++) {
                                    if (id_userAudio.get(k) == index_user) {
                                        JLabel tituloLabel6 = new JLabel("Audios: " + audio_locado.get(k));
                                        panelLocados.add(tituloLabel6);
                                        panelLocados.revalidate();
                                    }
                                }
                            }
                            if (id_user.size() == 0 && auxteste == 0) {
                                JOptionPane.showMessageDialog(null, "NAO HÁ LIVROS LOCADOS");
                                auxteste = 1;
                            } else {
                                for (int k = 0; k < id_user.size(); k++) {
                                    if (id_user.get(k) == index_user) {
                                        JLabel tituloLabel2 = new JLabel("Livros: " + isbn_locado.get(k));
                                        panelLocados.add(tituloLabel2);
                                        panelLocados.revalidate();
                                    }
                                }
                            }

                            frameLocarA.addWindowListener(new WindowAdapter() {
                                public void windowClosing(WindowEvent e) {
                                    janelaAberta = false;
                                    frameLocarA.remove(panelLocados);
                                    frameLocarA.dispose();
                                    janelaAberta = false; // reinicializa a variável
                                }
                            });
                        }
                    }
                });
            } else if (e.getSource() == devolver) {
                // Cria caixas de texto para nome, email e bio
                JTextField codigoField2 = new JTextField();

                // Cria um painel para as caixas de texto
                JPanel panelDevolver = new JPanel();
                panelDevolver.setLayout(new GridLayout(3, 2)); // GridLayout com 3 linhas e 2 colunas
                panelDevolver.add(new JLabel("Digite o codigo do livro:"));
                panelDevolver.add(codigoField2);

                // Exibe um JOptionPane com as caixas de texto e espera o usuário clicar em OK
                int resultDevolver = JOptionPane.showConfirmDialog(null, panelDevolver, "Devolver",
                        JOptionPane.OK_CANCEL_OPTION);
                if (resultDevolver == JOptionPane.OK_OPTION) {
                    try {
                        isbn = Integer.parseInt(codigoField2.getText());
                        System.out.println(id_user.size());
                        for (int j = 0; j < id_user.size(); j++) {
                            if (id_user.get(j) == index_user) {
                                if (isbn == isbn_locado.get(j)) {
                                    id_devolvido.add(j, index_user);
                                    isbn_devolvido.add(j, isbn);
                                    id_user.remove(j);
                                    isbn_locado.remove(j);
                                    JOptionPane.showMessageDialog(null, "Livro " + isbn + " devolvido!");
                                    for (int k = 0; k < livros.size(); k++) {
                                        if (isbn == livros.get(k).getIsbn()) {
                                            int devolu = livros.get(k).getQnt_disp() + 1;
                                            livros.set(k,
                                                    new Livro(livros.get(k).getTitulo(), livros.get(k).getAutor(),
                                                            isbn,
                                                            devolu, livros.get(k).getGenero()));
                                            break;
                                        }
                                    }
                                } else
                                    JOptionPane.showMessageDialog(null, "Código ISBN não encontrado");
                            }
                            else if (j == id_user.size() - 1) {
                                JOptionPane.showMessageDialog(null, "Este livro não foi locado por você");
                            }
                        }
                        if (id_user.size() == 0) {
                            JOptionPane.showMessageDialog(null, "Não há livros locados");
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Digite um ISBN válido no campo de código");
                        return;
                    }

                }
            } else if (e.getSource() == editar) {
                // Cria caixas de texto para nome, email e bio
                JTextField nomeField = new JTextField(contas.get(index_user).getNome());
                JTextField emailField = new JTextField(contas.get(index_user).getEmail());
                JTextField bioField = new JTextField(20);
                JLabel bioMsg = new JLabel("BIO - Comum = 50 caracteres; Premium = 200 caracteres");

                // Cria um painel para as caixas de texto
                JPanel panelEditar = new JPanel();
                panelEditar.setLayout(new GridLayout(4, 2)); // GridLayout com 3 linhas e 2 colunas
                panelEditar.add(new JLabel("Nome:"));
                panelEditar.add(nomeField);
                panelEditar.add(new JLabel("E-mail:"));
                panelEditar.add(emailField);
                panelEditar.add(new JLabel("Bio:"));
                panelEditar.add(bioField);
                panelEditar.add(bioMsg);

                // Exibe um JOptionPane com as caixas de texto e espera o usuário clicar em OK
                int result = JOptionPane.showConfirmDialog(null, panelEditar, "Editar perfil",
                        JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION) {
                    // Obtém o texto digitado nas caixas de texto
                    String nome = nomeField.getText();
                    String email = emailField.getText();
                    String bio = bioField.getText();

                    if (nome.length() < 5) {
                        JOptionPane.showMessageDialog(null,
                                "O usuário precisa ter um username maior que 5 caracteres!");
                    }

                    else if ((checkEmail(email) != null || isEmailValid(email) == false)
                            && !email.equalsIgnoreCase(contas.get(index_user).getEmail())) {
                        JOptionPane.showMessageDialog(null,
                                "E-mail inválido ou já cadastrado!");
                    } else if (contas.get(index_user).getPlano().equalsIgnoreCase("comum")) {

                        if (bio.length() > 50) {
                            JOptionPane.showMessageDialog(null, "Você excedeu o número de caracteres");
                        } else if (bio == "00") {
                        } else {
                            contas.get(index_user).defBios(bio);
                        }
                        contas.set(index_user, new comum(email, contas.get(index_user).getSenha(), nome, index_user)); // antes
                                                                                                                       // era
                                                                                                                       // id
                        contas.get(index_user).defPlano("comum");
                        JOptionPane.showMessageDialog(null, "Nome e email alterados com sucesso!");
                    }

                    else if (contas.get(index_user).getPlano().equalsIgnoreCase("premium")) {

                        if (bio.length() > 200) {
                            JOptionPane.showMessageDialog(null, "Você excedeu o número de caracteres");
                        } else if (bio == "00") {
                        } else {
                            contas.get(index_user).defBios(bio);
                        }
                        contas.set(index_user, new premium(email, contas.get(index_user).getSenha(), nome, index_user)); // antes
                                                                                                                         // era
                                                                                                                         // id
                        contas.get(index_user).defPlano("premium");
                        JOptionPane.showMessageDialog(null, "Nome e email alterados com sucesso!");
                    }
                }
            } else if (e.getSource() == verutensilios) {
                auxteste3 = 0;
                auxteste2 = 0;
                auxteste = 0;
                frameUtensilio.setSize(400, 400);
                frameUtensilio.add(panel6);
                panel6.add(marca_textobutton);

                marca_textobutton.setAlignmentX(Component.CENTER_ALIGNMENT);
                frameUtensilio.setVisible(true);
                frameUtensilio.setLocationRelativeTo(null);

                marca_textobutton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (auxteste == 0) {
                            auxteste = 1;
                            JPanel panelUten = new JPanel(new GridLayout(0, 1));
                            JFrame frameUten = new JFrame();
                            frameUten.setSize(400, 400);
                            frameUten.add(panelUten);
                            frameUten.setVisible(true);
                            panelUten.removeAll(); // remove todos os componentes do painel

                            for (utensilios utensi : utensilios) {
                                JLabel corlabel = new JLabel("" + utensi.getInformacao());
                                // adicionar componentes ao painel central
                                panelUten.add(corlabel);
                            }

                            panelUten.revalidate();
                            panelUten.repaint();
                            frameUten.revalidate(); // Atualiza o layout da janela

                            // adiciona o WindowListener para a janela
                            frameUten.addWindowListener(new WindowAdapter() {
                                public void windowClosing(WindowEvent e) {
                                    frameUten.dispose(); // apaga a janela
                                }
                            });
                        }
                        auxteste3 = 0;
                        auxteste2 = 0;
                        auxteste = 0;
                    }
                });

            } else if (e.getSource() == vermultas) {
                auxteste3 = 0;
                if (auxteste3 == 0) {
                    auxteste3 = 1;
                    JPanel panelMP = new JPanel();
                    JFrame frameMP = new JFrame();
                    frameMP.setSize(400, 400);
                    frameMP.setLocationRelativeTo(null);
                    frameMP.add(panelMP);
                    panelMP.removeAll();
                    multaalarme = 0; // redefine a variável multaalarme para 0

                    for (int j = 0; j < id_multapendente.size(); j++) {
                        if (id_multapendente.get(j) == index_user) {
                            multaalarme = 1;
                            JLabel multalabel = new JLabel("Você possui uma multa não paga");
                            panelMP.add(multalabel);
                        }
                    }
                    if (multaalarme == 0) {
                        JOptionPane.showMessageDialog(null, "você não possui multas pendentes");
                    }
                    panelMP.repaint();
                    frameMP.revalidate();

                    frameMP.addWindowListener(new WindowAdapter() {
                        public void windowClosing(WindowEvent e) {
                            frameMP.dispose();
                        }
                    });

                    frameMP.setVisible(true);
                } else {
                    frameMultaPendente.setSize(400, 400);
                    frameMultaPendente.add(panel6);
                    frameMultaPendente.setVisible(true);
                }
            } else if (e.getSource() == pagarmultas) {
                testemultas = 0;
                auxteste3 = 0;
                if (auxteste3 == 0) {
                    auxteste3 = 1;
                    JPanel panelMP = new JPanel(new GridLayout(0, 1));
                    JFrame frameMP = new JFrame();
                    frameMP.setSize(400, 400);
                    frameMP.setLocationRelativeTo(null);
                    frameMP.add(panelMP);
                    panelMP.removeAll();
                    buttonPGM.setSize(10, 10);
                    multaalarme = 0; // redefine a variável multaalarme para 0

                    for (int j = 0; j < id_multapendente.size(); j++) {
                        if (id_multapendente.get(j) == index_user) {
                            multaalarme = 1;
                            JLabel multalabel = new JLabel("Você possui uma multa não paga");
                            panelMP.add(multalabel);
                        }
                    }
                    if (multaalarme == 0) {
                        JOptionPane.showMessageDialog(null, "você não possui multas pendentes");
                    }
                    panelMP.repaint();
                    frameMP.revalidate();
                    frameMP.addWindowListener(new WindowAdapter() {
                        public void windowClosing(WindowEvent e) {
                            frameMP.dispose();
                        }
                    });
                    for (int j = 0; j < id_multapendente.size(); j++) {
                        if (id_multapendente.get(j) == index_user) {
                            testemultas += 1;
                        }
                    }
                    if (testemultas != 0) {
                        panelMP.add(buttonPGM);
                    } else {
                        JLabel multalabel2 = new JLabel("Você pagou todas suas multas ou não possui multas");
                        panelMP.add(multalabel2);
                    }
                    buttonPGM.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            for (int j = 0; j < id_multapendente.size(); j++) {
                                if (id_multapendente.get(j) == index_user) {
                                    id_multapaga.add(index_user);
                                    id_multapendente.remove(j);
                                    JOptionPane.showMessageDialog(null, "você pagou uma de suas multas");
                                }
                            }
                            frameMP.dispose();
                        }
                    });
                    frameMP.setVisible(true);

                } else {
                    frameMultaPendente.setSize(400, 400);
                    frameMultaPendente.add(panel6);
                    frameMultaPendente.setVisible(true);
                    frameMultaPendente.setLocationRelativeTo(null);
                }

            } 
            
        }
    }

    public class contas {
    }

}
