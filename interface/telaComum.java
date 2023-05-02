import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.regex.*;

public class telaComum extends JFrame implements ActionListener {
    private JButton audios, books, locar, editar, mostrar, devolver, verificar, pagarmultas, vermultas,
            verutensilios, mostrarTudo, buttonPGM, marca_textobutton;
    private JLabel titleLabel;
    public int auxteste = 0;
    public int id = 0;
    public int index_user = 0;
    public int contalocados = 0;
    public int auxteste3 = 0;
    public int auxteste2 = 0;
    public int multaalarme = 0;
    public int testemultas = 0;
    public int isbn;
    public int codigoR;
    public int codigoAudio;
    Dimension botaoDimensao = new Dimension(250, 30);
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

    public class IOException extends RuntimeException {
        public IOException(String message) {
            super(message);
        }
    }

    public telaComum() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
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

        panel.setSize(new Dimension(550, 400));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(Box.createRigidArea(new Dimension(400, 400)));
        setSize(400, 400);
        add(panel);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == mostrar) {
            frameMostrar.setSize(400, 400);
            frameMostrar.add(panel3);
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

                    for (Livro livro : LoginRegister.livros) {
                        if (livro instanceof Livro) {
                            JLabel tituloLabel2 = new JLabel("Título: " + livro.getTitulo());
                            JLabel isbnLabel = new JLabel("ISBN: " + livro.getIsbn());
                            JLabel qntdLabel = new JLabel("Quantidade: " + livro.getQnt_disp());
                            panel4.add(tituloLabel2);
                            panel4.add(isbnLabel);
                            panel4.add(qntdLabel);
                        }
                    }

                    panel4.revalidate();
                    panel4.repaint();
                    frameLocar2.revalidate();
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
                    for (audiobook audio : LoginRegister.audiobook2) {
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
                            frameLocar3.revalidate(); 
                        }
                    }
                    frameLocar3.addWindowListener(new WindowAdapter() {
                        public void windowClosing(WindowEvent e) {
                            frameLocar3.dispose();
                        }
                    });
                }
            });
        } else if (e.getSource() == locar) {
            JComboBox<String> itemField;
            JTextField codigoField = new JTextField(20);
            JPanel panelLocar = new JPanel();
            setLocationRelativeTo(null);
            panelLocar.setLayout(new GridLayout(3, 2));
            panelLocar.add(new JLabel("Digite se quer um livro/audiobook:"));
            DefaultComboBoxModel<String> planoModel = new DefaultComboBoxModel<>();
            planoModel.addElement("livro");
            planoModel.addElement("audiobook");
            itemField = new JComboBox<>(planoModel);
            panelLocar.add(itemField);
            panelLocar.add(new JLabel("Digite o codigo do livro/audiobook:"));
            panelLocar.add(codigoField);
            int resultLocar = JOptionPane.showConfirmDialog(null, panelLocar, "Locar",
            JOptionPane.OK_CANCEL_OPTION);
            contalocados = 0;
            if (resultLocar == JOptionPane.OK_OPTION) {
                for (int k = 0; k < LoginRegister.id_user.size(); k++) {
                    if (LoginRegister.id_user.get(k) == index_user) {
                        contalocados += 1;
                    }
                }

                if (LoginRegister.contas.get(index_user).getPlano().equalsIgnoreCase("comum")) {
                    if (contalocados < 1) {
                        if (itemField.getSelectedItem().toString().equalsIgnoreCase("livro")) {
                            try {
                                isbn = Integer.parseInt(codigoField.getText());
                                for (int k = 0; k < LoginRegister.livros.size(); k++) {
                                    if (isbn == LoginRegister.livros.get(k).getIsbn()) {
                                        if (LoginRegister.livros.get(k).getQnt_disp() == 0) {
                                            JOptionPane.showMessageDialog(null,
                                                    "Livro indisponível, tente novamente em outro momento.");
                                            break;
                                        } else if (LoginRegister.livros.get(k).getQnt_disp() > 0) {
                                            int quantidadeL = LoginRegister.livros.get(k).getQnt_disp() - 1;
                                            LoginRegister.livros.set(k,
                                                    new Livro(LoginRegister.livros.get(k).getTitulo(), LoginRegister.livros.get(k).getAutor(),
                                                            isbn, quantidadeL, LoginRegister.livros.get(k).getGenero()));
                                            JOptionPane.showMessageDialog(null,
                                                    "Parabéns, você conseguiu locar um livro!");
                                                    LoginRegister.id_user.add(LoginRegister.contas.get(index_user).getId());
                                                    LoginRegister.isbn_locado.add(isbn);
                                            break;
                                        }
                                    } else if (k == LoginRegister.livros.size() - 1) {
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
                                for (int k = 0; k < LoginRegister.audiobook2.size(); k++) {
                                    if (codigoAudio == LoginRegister.audiobook2.get(k).getAudio()) {
                                        if (LoginRegister.audiobook2.get(k).getQnt_disp() == 0) {
                                            JOptionPane.showMessageDialog(null,
                                                    "Audiobook indisponível, tente novamente em outro momento.");
                                            break;
                                        } else if (LoginRegister.audiobook2.get(k).getQnt_disp() > 0) {
                                            int quantidadeA = LoginRegister.audiobook2.get(k).getQnt_disp() - 1;
                                            LoginRegister.audiobook2.set(k,
                                                    new audiobook(LoginRegister.audiobook2.get(k).getTitulo(),
                                                    LoginRegister.audiobook2.get(k).getAutor(),
                                                    LoginRegister.audiobook2.get(k).getDuracao(), quantidadeA,
                                                    LoginRegister.audiobook2.get(k).getGenero(), codigoAudio));
                                            JOptionPane.showMessageDialog(null,
                                                    "Parabéns, você conseguiu locar um audiobook!");
                                                    LoginRegister.id_userAudio.add(LoginRegister.contas.get(index_user).getId());
                                                    LoginRegister.audio_locado.add(codigoAudio);
                                            break;
                                        }
                                    } else if (k == LoginRegister.audiobook2.size() - 1) {
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

                if (LoginRegister.contas.get(index_user).getPlano().equalsIgnoreCase("premium")) {
                    if (contalocados < 15) {
                        if (itemField.getSelectedItem().toString().equalsIgnoreCase("livro")) {
                            try {
                                isbn = Integer.parseInt(codigoField.getText());
                                for (int k = 0; k < LoginRegister.livros.size(); k++) {
                                    if (isbn == LoginRegister.livros.get(k).getIsbn()) {
                                        if (LoginRegister.livros.get(k).getQnt_disp() == 0) {
                                            JOptionPane.showMessageDialog(null,
                                                    "Livro indisponível, tente novamente em outro momento.");
                                            break;
                                        } else if (LoginRegister.livros.get(k).getQnt_disp() > 0) {
                                            int quantidadeL = LoginRegister.livros.get(k).getQnt_disp() - 1;
                                            LoginRegister.livros.set(k,
                                                    new Livro(LoginRegister.livros.get(k).getTitulo(), LoginRegister.livros.get(k).getAutor(),
                                                            isbn, quantidadeL, LoginRegister.livros.get(k).getGenero()));
                                            JOptionPane.showMessageDialog(null,
                                                    "Parabéns, você conseguiu locar um livro!");
                                                    LoginRegister.id_user.add(LoginRegister.contas.get(index_user).getId());
                                                    LoginRegister.isbn_locado.add(isbn);
                                            break;
                                        }
                                    } else if (k == LoginRegister.livros.size() - 1) {
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
                                for (int k = 0; k < LoginRegister.audiobook2.size(); k++) {
                                    if (codigoAudio == LoginRegister.audiobook2.get(k).getAudio()) {
                                        if (LoginRegister.audiobook2.get(k).getQnt_disp() == 0) {
                                            JOptionPane.showMessageDialog(null,
                                                    "Audiobook indisponível, tente novamente em outro momento.");
                                            break;
                                        } else if (LoginRegister.audiobook2.get(k).getQnt_disp() > 0) {
                                            LoginRegister.audiobook2.set(k, new audiobook(LoginRegister.audiobook2.get(k).getTitulo(),
                                            LoginRegister.audiobook2.get(k).getAutor(), LoginRegister.audiobook2.get(k).getDuracao(),
                                            LoginRegister.audiobook2.get(k).getQnt_disp() - 1,
                                            LoginRegister.audiobook2.get(k).getGenero(), codigoAudio));
                                            JOptionPane.showMessageDialog(null,
                                                    "Parabéns, você conseguiu locar um audiobook!");
                                                    LoginRegister.id_userAudio.add(index_user);
                                                    LoginRegister.audio_locado.add(codigoAudio);
                                            break;
                                        }
                                    } else if (k == LoginRegister.audiobook2.size() - 1) {
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
                public void actionPerformed(ActionEvent e){
                    if (!janelaAberta) {
                        janelaAberta = true;
                        JPanel panelLocados = new JPanel();
                        setLocationRelativeTo(null);
                        frameLocarA.setSize(400, 400);
                        frameLocarA.add(panelLocados);
                        frameLocarA.setVisible(true);
                        frameLocarA.setLocationRelativeTo(null);

                        if (LoginRegister.id_userAudio.size() == 0 && auxteste3 == 0) {
                            JOptionPane.showMessageDialog(null, "NAO HÁ AUDIOS LOCADOS");
                            auxteste3 = 1;
                        } else {
                            for (int k = 0; k < LoginRegister.id_userAudio.size(); k++) {
                                if (LoginRegister.id_userAudio.get(k) == index_user) {
                                    JLabel tituloLabel6 = new JLabel("Audios: " + LoginRegister.audio_locado.get(k));
                                    panelLocados.add(tituloLabel6);
                                    panelLocados.revalidate();
                                }
                            }
                        }
                        if (LoginRegister.id_user.size() == 0 && auxteste == 0) {
                            JOptionPane.showMessageDialog(null, "NAO HÁ LIVROS LOCADOS");
                            auxteste = 1;
                        } else {
                            for (int k = 0; k < LoginRegister.id_user.size(); k++) {
                                if (LoginRegister.id_user.get(k) == index_user) {
                                    JLabel tituloLabel2 = new JLabel("Livros: " + LoginRegister.isbn_locado.get(k));
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
                    System.out.println(LoginRegister.id_user.size());
                    for (int j = 0; j < LoginRegister.id_user.size(); j++) {
                        if (LoginRegister.id_user.get(j) == index_user) {
                            if (isbn == LoginRegister.isbn_locado.get(j)) {
                                LoginRegister.id_devolvido.add(j, index_user);
                                LoginRegister.isbn_devolvido.add(j, isbn);
                                LoginRegister.id_user.remove(j);
                                LoginRegister.isbn_locado.remove(j);
                                JOptionPane.showMessageDialog(null, "Livro " + isbn + " devolvido!");
                                for (int k = 0; k < LoginRegister.livros.size(); k++) {
                                    if (isbn == LoginRegister.livros.get(k).getIsbn()) {
                                        int devolu = LoginRegister.livros.get(k).getQnt_disp() + 1;
                                        LoginRegister.livros.set(k,
                                                new Livro(LoginRegister.livros.get(k).getTitulo(), LoginRegister.livros.get(k).getAutor(),
                                                        isbn,
                                                        devolu, LoginRegister.livros.get(k).getGenero()));
                                        break;
                                    }
                                }
                            } else
                                JOptionPane.showMessageDialog(null, "Código ISBN não encontrado");
                        }
                        else if (j == LoginRegister.id_user.size() - 1) {
                            JOptionPane.showMessageDialog(null, "Este livro não foi locado por você");
                        }
                    }
                    if (LoginRegister.id_user.size() == 0) {
                        JOptionPane.showMessageDialog(null, "Não há livros locados");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Digite um ISBN válido no campo de código");
                    return;
                }

            }
        } else if (e.getSource() == editar) {
            // Cria caixas de texto para nome, email e bio
            JTextField nomeField = new JTextField(LoginRegister.contas.get(index_user).getNome());
            JTextField emailField = new JTextField(LoginRegister.contas.get(index_user).getEmail());
            JTextField bioField = new JTextField(20);
            JLabel bioMsg = new JLabel("BIO - Comum = 50 caracteres; Premium = 200 caracteres");

            // Cria um painel para as caixas de texto
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
                        && !email.equalsIgnoreCase(LoginRegister.contas.get(index_user).getEmail())) {
                    JOptionPane.showMessageDialog(null,
                            "E-mail inválido ou já cadastrado!");
                } else if (LoginRegister.contas.get(index_user).getPlano().equalsIgnoreCase("comum")) {

                    if (bio.length() > 50) {
                        JOptionPane.showMessageDialog(null, "Você excedeu o número de caracteres");
                    } else if (bio == "00") {
                    } else {
                        LoginRegister.contas.get(index_user).defBios(bio);
                    }
                    LoginRegister.contas.set(index_user, new comum(email, LoginRegister.contas.get(index_user).getSenha(), nome, index_user)); // antes
                                                                                                                   // era
                                                                                                                   // id
                                                                                                                   LoginRegister.contas.get(index_user).defPlano("comum");
                    JOptionPane.showMessageDialog(null, "Nome e email alterados com sucesso!");
                }

                else if (LoginRegister.contas.get(index_user).getPlano().equalsIgnoreCase("premium")) {

                    if (bio.length() > 200) {
                        JOptionPane.showMessageDialog(null, "Você excedeu o número de caracteres");
                    } else if (bio == "00") {
                    } else {
                        LoginRegister.contas.get(index_user).defBios(bio);
                    }
                    String SenhaString = LoginRegister.contas.get(index_user).getSenha();

                    LoginRegister.contas.set(index_user, new premium(email, SenhaString, nome, LoginRegister.index_user)); // antes
                                   LoginRegister.contas.get(index_user).defPlano("premium");
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

                        for (utensilios utensi : LoginRegister.utensilios) {
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

                for (int j = 0; j < LoginRegister.id_multapendente.size(); j++) {
                    if (LoginRegister.id_multapendente.get(j) == index_user) {
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

                for (int j = 0; j < LoginRegister.id_multapendente.size(); j++) {
                    if (LoginRegister.id_multapendente.get(j) == index_user) {
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
                for (int j = 0; j < LoginRegister.id_multapendente.size(); j++) {
                    if (LoginRegister.id_multapendente.get(j) == index_user) {
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
                        for (int j = 0; j < LoginRegister.id_multapendente.size(); j++) {
                            if (LoginRegister.id_multapendente.get(j) == index_user) {
                                LoginRegister.id_multapaga.add(index_user);
                                LoginRegister.id_multapendente.remove(j);
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

    private conta checkEmail(String usuario) {
        for (conta contas : LoginRegister.contas) {
            if (contas.getEmail().equals(usuario)) {
                return contas;
            }
        }
        return null;
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
}