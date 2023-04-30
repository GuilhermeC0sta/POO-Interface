import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.awt.event.*;
import java.util.ArrayList;

public class telaAdmin extends JFrame implements ActionListener {

    JButton add_itens, remover_itens, livros_devolvidos, cadastros, buttonconfirmar, buttonCP;
    Dimension botaoDimensao = new Dimension(250, 30);
    JPanel panelAdmin = new JPanel();
    JFrame frameMultaPendente = new JFrame();

    public int auxteste = 0;
    public int id = 0;
    public int index_user;
    public int contalocados = 0;
    public int auxteste3 = 0;
    public int auxteste2 = 0;
    public int multaalarme = 0;
    public int testemultas = 0;
    public int isbn;
    public int codigoR;

    JPanel panel6 = new JPanel();
    
    public class IOException extends RuntimeException {
        public IOException(String message) {
            super(message);
        }
    }

    public telaAdmin(){
        panelAdmin.add(Box.createRigidArea(new Dimension(0, 10)));
        add_itens = new JButton("Adicionar livro/audiobook/utensilio");
        add_itens.setPreferredSize(botaoDimensao);
        add_itens.setMaximumSize(botaoDimensao);
        add_itens.setMinimumSize(botaoDimensao);
        add_itens.addActionListener(this);
        panelAdmin.add(add_itens);
        add_itens.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelAdmin.add(Box.createRigidArea(new Dimension(0, 10)));

        remover_itens = new JButton("Remover livro/audiobook");
        remover_itens.setPreferredSize(botaoDimensao);
        remover_itens.setMaximumSize(botaoDimensao);
        remover_itens.setMinimumSize(botaoDimensao);
        remover_itens.addActionListener(this);
        panelAdmin.add(remover_itens);
        remover_itens.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelAdmin.add(Box.createRigidArea(new Dimension(0, 10)));

        livros_devolvidos = new JButton("Verificar livros devolvidos");
        livros_devolvidos.setPreferredSize(botaoDimensao);
        livros_devolvidos.setMaximumSize(botaoDimensao);
        livros_devolvidos.setMinimumSize(botaoDimensao);
        livros_devolvidos.addActionListener(this);
        panelAdmin.add(livros_devolvidos);
        livros_devolvidos.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelAdmin.add(Box.createRigidArea(new Dimension(0, 10)));

        buttonCP = new JButton("Pagar multa");
        buttonCP.setPreferredSize(botaoDimensao);
        buttonCP.setMaximumSize(botaoDimensao);
        buttonCP.setMinimumSize(botaoDimensao);
        buttonCP.addActionListener(this);
        buttonCP.setAlignmentX(Component.CENTER_ALIGNMENT);

        cadastros = new JButton("Verificar contas cadastradas");
        cadastros.setPreferredSize(botaoDimensao);
        cadastros.setMaximumSize(botaoDimensao);
        cadastros.setMinimumSize(botaoDimensao);
        cadastros.addActionListener(this);
        panelAdmin.add(cadastros);
        cadastros.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelAdmin.add(Box.createRigidArea(new Dimension(0, 10)));

        buttonconfirmar = new JButton("Confirmar pagamento de multas");
        buttonconfirmar.setPreferredSize(botaoDimensao);
        buttonconfirmar.setMaximumSize(botaoDimensao);
        buttonconfirmar.setMinimumSize(botaoDimensao);
        buttonconfirmar.addActionListener(this);
        panelAdmin.add(buttonconfirmar);
        buttonconfirmar.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelAdmin.add(Box.createRigidArea(new Dimension(0, 10)));

        panelAdmin.setSize(new Dimension(550,400));
        panelAdmin.setLayout(new BoxLayout(panelAdmin, BoxLayout.Y_AXIS));
        panelAdmin.add(Box.createRigidArea(new Dimension(400, 400)));
        setSize(400,400);
        add(panelAdmin);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == add_itens) {
            String[] opcoes = { "Livro", "Audiobook", "Utensilio" };
            int opcaoSelecionada = JOptionPane.showOptionDialog(null, "Selecione o tipo de item a adicionar:",
                    "Adicionar Itens", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, opcoes,
                    opcoes[0]);

            if (opcaoSelecionada == 0) { // Clicou em "Livro"
                JTextField tituloAdd = new JTextField();
                JTextField autorAdd = new JTextField();
                JTextField isbnAdd = new JTextField();
                JTextField generoAdd = new JTextField();
                JTextField quantidadeAdd = new JTextField();
                JPanel panelItens = new JPanel(new GridLayout(0, 1));
                setLocationRelativeTo(null);

                panelItens.add(new JLabel("Titulo:"));
                panelItens.add(tituloAdd);
                panelItens.add(new JLabel("Autor:"));
                panelItens.add(autorAdd);
                panelItens.add(new JLabel("ISBN:"));
                panelItens.add(isbnAdd);
                panelItens.add(new JLabel("Gênero:"));
                panelItens.add(generoAdd);
                panelItens.add(new JLabel("Quantidade:"));
                panelItens.add(quantidadeAdd);
                int result = JOptionPane.showConfirmDialog(null, panelItens, "Adicionar Livro",
                        JOptionPane.OK_CANCEL_OPTION);
                try {
                    int isbnAddItem = Integer.parseInt(isbnAdd.getText());
                    int quantidadeAddItem = Integer.parseInt(quantidadeAdd.getText());
                    for (int i = 0; i < LoginRegister.livros.size(); i++) {
                        if (LoginRegister.livros.get(i).getIsbn() == isbnAddItem) {
                            JOptionPane.showMessageDialog(null, "ISBN já cadastrado!");
                            return;
                        }
                    }

                    if (!autorAdd.getText().matches("[a-zA-Z]+") || !generoAdd.getText().matches("[a-zA-Z]+")) {
                        throw new IOException("O campo autor/gênero deve conter somente letras.");
                    }

                    if (result == JOptionPane.OK_OPTION) {
                        LoginRegister.livros.add(
                                new Livro(tituloAdd.getText(), autorAdd.getText(), isbnAddItem, quantidadeAddItem,
                                        generoAdd.getText()));
                        JOptionPane.showMessageDialog(null, "Livro adicionado!");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "ISBN e Quantidade devem ser números inteiros!");
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }

            } else if (opcaoSelecionada == 1) { // Clicou em "Audiobook"
                JTextField tituloAdd = new JTextField();
                JTextField autorAdd = new JTextField();
                JTextField duracaoAdd = new JTextField();
                JTextField generoAdd = new JTextField();
                JTextField quantidadeAdd = new JTextField();
                JTextField idAudioAdd = new JTextField();

                JPanel panelItens = new JPanel(new GridLayout(0, 1));
                int duracaoAddItem = 0;
                int quantidadeAddItem = 0;
                int idAudioAddItem = 0;
                setLocationRelativeTo(null);

                panelItens.add(new JLabel("Titulo:"));
                panelItens.add(tituloAdd);
                panelItens.add(new JLabel("Autor:"));
                panelItens.add(autorAdd);
                panelItens.add(new JLabel("Duração:"));
                panelItens.add(duracaoAdd);
                panelItens.add(new JLabel("Gênero:"));
                panelItens.add(generoAdd);
                panelItens.add(new JLabel("Quantidade:"));
                panelItens.add(quantidadeAdd);
                panelItens.add(new JLabel("ID do Áudio:"));
                panelItens.add(idAudioAdd);

                int result = JOptionPane.showConfirmDialog(null, panelItens, "Adicionar Audiobook",
                        JOptionPane.OK_CANCEL_OPTION);

                try {
                    duracaoAddItem = Integer.parseInt(duracaoAdd.getText());
                    quantidadeAddItem = Integer.parseInt(quantidadeAdd.getText());
                    idAudioAddItem = Integer.parseInt(idAudioAdd.getText());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "ISBN, Quantidade e Duração devem ser números inteiros!");
                    return;
                }

                if (result == JOptionPane.OK_OPTION) {
                    for (int i = 0; i < LoginRegister.audiobook2.size(); i++) {
                        if (LoginRegister.audiobook2.get(i).getAudio() == idAudioAddItem) {
                            JOptionPane.showMessageDialog(null, "ID do áudio já cadastrado!");
                            return;
                        }
                    }
                    LoginRegister.audiobook2.add(new audiobook(tituloAdd.getText(), autorAdd.getText(), duracaoAddItem,
                            quantidadeAddItem,
                            generoAdd.getText(), idAudioAddItem));
                    JOptionPane.showMessageDialog(null, "Audio adicionado!");
                }
            } else if (opcaoSelecionada == 2) { // Clicou em "Utensilio"
                String[] options = { "Postit", "Marca texto", "Apoio de livros" };
                int utensilioOption = JOptionPane.showOptionDialog(null, "Escolha o utensílio a adicionar:",
                        "Adicionar Utensílio", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options,
                        options[0]);

                if (utensilioOption == 0) { // Adicionar Postit
                    JTextField corField = new JTextField();
                    JTextField marcaField = new JTextField();
                    JTextField quantidadeField = new JTextField();
                    JPanel panelP = new JPanel(new GridLayout(0, 1));
                    try {
                        panelP.add(new JLabel("Cor:"));
                        panelP.add(corField);
                        panelP.add(new JLabel("Marca:"));
                        panelP.add(marcaField);
                        panelP.add(new JLabel("Quantidade:"));
                        panelP.add(quantidadeField);
                        int result = JOptionPane.showConfirmDialog(null, panelP, "Adicionar Postit",
                                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

                        if (!corField.getText().matches("[a-zA-Z]+")) {
                            throw new IOException("O campo cor deve conter somente letras.");
                        }

                        if (result == JOptionPane.OK_OPTION) {
                            // Adicionar postit ao inventário com os dados preenchidos
                            String corP = corField.getText();
                            String marcaP = marcaField.getText();
                            int quantidadeP = Integer.parseInt(quantidadeField.getText());
                            LoginRegister.utensilios.add(new postit(marcaP, corP, quantidadeP));
                            JOptionPane.showMessageDialog(null, "Postit adicionado!");
                        }

                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Quantidade deve ser um número inteiro!");
                        return;
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage());
                    }

                } else if (utensilioOption == 1) { // Adicionar Marca Texto
                    JTextField marcaField = new JTextField();
                    JTextField corField = new JTextField();
                    JTextField brilhoField = new JTextField();
                    JTextField quantidadeField = new JTextField();
                    JPanel panelM = new JPanel(new GridLayout(0, 1));
                    try {

                        panelM.add(new JLabel("Marca:"));
                        panelM.add(marcaField);
                        panelM.add(new JLabel("Cor:"));
                        panelM.add(corField);
                        panelM.add(new JLabel("Brilho:"));
                        panelM.add(brilhoField);
                        panelM.add(new JLabel("Quantidade:"));
                        panelM.add(quantidadeField);
                        int result = JOptionPane.showConfirmDialog(null, panelM, "Adicionar Marca Texto",
                                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

                        if (!corField.getText().matches("[a-zA-Z]+")
                                || !brilhoField.getText().matches("[a-zA-Z]+")) {
                            throw new IOException("O campo cor/brilho deve conter somente letras.");
                        }

                        if (result == JOptionPane.OK_OPTION) {
                            // Adicionar marca texto ao inventário com os dados preenchidos
                            String marcaM = marcaField.getText();
                            String corM = corField.getText();
                            String brilhoM = brilhoField.getText();
                            int quantidadeM = Integer.parseInt(quantidadeField.getText());
                            LoginRegister.utensilios.add(new marca_texto(marcaM, corM, brilhoM, quantidadeM));
                            JOptionPane.showMessageDialog(null, "Marca texto adicionado!");
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Quantidade deve ser um número inteiro!");
                        return;
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage());
                    }

                } else if (utensilioOption == 2) { // Adicionar Apoio de Livros
                    JTextField marcaField = new JTextField();
                    JTextField slotsField = new JTextField();
                    JTextField quantidadeField = new JTextField();
                    JPanel panel = new JPanel(new GridLayout(0, 1));
                    try {

                        panel.add(new JLabel("Marca:"));
                        panel.add(marcaField);
                        panel.add(new JLabel("Slots:"));
                        panel.add(slotsField);
                        panel.add(new JLabel("Quantidade:"));
                        panel.add(quantidadeField);
                        int result = JOptionPane.showConfirmDialog(null, panel, "Adicionar Apoio de Livros",
                                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

                        if (result == JOptionPane.OK_OPTION) {
                            // Adicionar apoio de livros ao inventário com os dados preenchidos
                            String marcaA = marcaField.getText();
                            int slotsA = Integer.parseInt(slotsField.getText());
                            int quantidadeA = Integer.parseInt(quantidadeField.getText());
                            LoginRegister.utensilios.add(new apoio_livros(marcaA, slotsA, quantidadeA));
                            JOptionPane.showMessageDialog(null, "Apoio para Livros adicionado!");
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Slots e Quantidade devem ser números inteiros!");
                        return;
                    }
                }
            }
        } else if (e.getSource() == cadastros) {
            auxteste2 = 0;

            if (auxteste2 == 0) {
                auxteste2 = 1;
                JPanel panelCadastros = new JPanel(new GridLayout(0, 4));
                JFrame frameCadastros = new JFrame();
                frameCadastros.setSize(400, 400);
                frameCadastros.add(panelCadastros);
                frameCadastros.setVisible(true);
                frameCadastros.setLocationRelativeTo(null);
                panelCadastros.removeAll(); // remove todos os componentes do painel

                for (conta user : LoginRegister.contas) {
                    JLabel NomeLabel = new JLabel("Nome: " + user.getNome());
                    JLabel EmailLabel = new JLabel("Email: " + user.getEmail());
                    JLabel planoLabel = new JLabel("Plano: \n" + user.getPlano());
                    panelCadastros.add(NomeLabel);
                    panelCadastros.add(EmailLabel);
                    panelCadastros.add(planoLabel);
                    panelCadastros.add(Box.createRigidArea(new Dimension(0, 10)));
                }

                panelCadastros.revalidate();
                panelCadastros.repaint();
                frameCadastros.revalidate(); // Atualiza o layout da janela

                // adiciona o WindowListener para a janela
                frameCadastros.addWindowListener(new WindowAdapter() {
                    public void windowClosing(WindowEvent e) {
                        frameCadastros.dispose(); // apaga a janela
                    }
                });
            }
        } else if (e.getSource() == buttonconfirmar) {
            auxteste2 = 0;
            if (auxteste2 == 0) {
                JPanel panelCP = new JPanel(new GridLayout(0, 1));
                JFrame frameCP = new JFrame();
                frameCP.setSize(400, 400);
                frameCP.add(panelCP);
                frameCP.setLocationRelativeTo(null);
                panelCP.removeAll();
                buttonCP.setSize(10, 10);
                multaalarme = 0; // redefine a variável multaalarme para 0

                for (int j = 0; j < LoginRegister.id_multapaga.size(); j++) {
                    multaalarme = 1;
                    JLabel multalabel = new JLabel("O Usuario: " + LoginRegister.id_multapaga.get(j) + " pagou uma multa");
                    panelCP.add(multalabel);
                }
                if (multaalarme == 0) {
                    JOptionPane.showMessageDialog(null, "Não há multas para serem pagas");
                }
                panelCP.repaint();
                frameCP.revalidate();
                frameCP.addWindowListener(new WindowAdapter() {
                    public void windowClosing(WindowEvent e) {
                        frameCP.dispose();
                    }
                });
                if (LoginRegister.id_multapaga.size() != 0) {
                    panelCP.add(buttonCP);
                } else {
                    JLabel multalabel2 = new JLabel("Não há multas a serem confirmadas");
                    panelCP.add(multalabel2);
                }
                buttonCP.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        for (int j = 0; j < LoginRegister.id_multapaga.size(); j++) {
                            LoginRegister.id_multapaga.remove(j);
                            JOptionPane.showMessageDialog(null, "voce confirmou o pagamento de uma das multas");
                        }
                        frameCP.dispose();
                    }
                });
                frameCP.setVisible(true);

            } else {
                frameMultaPendente.setSize(400, 400);
                frameMultaPendente.add(panel6);
                frameMultaPendente.setVisible(true);
            }
        } else if (e.getSource() == remover_itens) {
            JComboBox<String> itemFieldR = new JComboBox<>(new String[] { "Livro", "Audiobook" });
            JTextField codigoFieldR = new JTextField();

            JPanel panelRemover = new JPanel();
            setLocationRelativeTo(null);
            panelRemover.setLayout(new GridLayout(3, 2)); // GridLayout com 3 linhas e 2 colunas
            panelRemover.add(new JLabel("Selecione o item para remover:"));
            panelRemover.add(itemFieldR);
            panelRemover.add(new JLabel("Digite o código do item:"));
            panelRemover.add(codigoFieldR);

            int resultRemover = JOptionPane.showConfirmDialog(null, panelRemover, "Remover",
                    JOptionPane.OK_CANCEL_OPTION);

            if (resultRemover == JOptionPane.OK_OPTION) {
                try {
                    if (itemFieldR.getSelectedItem().equals("Livro")) {
                        isbn = Integer.parseInt(codigoFieldR.getText());

                        for (int k = 0; k < LoginRegister.livros.size(); k++) {
                            if (isbn == LoginRegister.livros.get(k).getIsbn()) {
                                LoginRegister.livros.remove(k);
                                JOptionPane.showMessageDialog(null, "Livro " + isbn + " removido!");
                            } else if (k == LoginRegister.livros.size() - 1) {
                                JOptionPane.showMessageDialog(null, "Livro não encontrado!");
                            }
                        }

                    } else if (itemFieldR.getSelectedItem().equals("Audiobook")) {
                        codigoR = Integer.parseInt(codigoFieldR.getText());

                        for (int k = 0; k < LoginRegister.audiobook2.size(); k++) {
                            if (codigoR == LoginRegister.audiobook2.get(k).getAudio()) {
                                LoginRegister.audiobook2.remove(k);
                                JOptionPane.showMessageDialog(null, "Audiobook " + codigoR + " removido!");
                            } else if (k == LoginRegister.audiobook2.size() - 1) {
                                JOptionPane.showMessageDialog(null, "Audiobook não encontrado!");
                            }
                        }
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Código inválido. Digite um número válido.");
                }
            }

        } else if (e.getSource() == livros_devolvidos) {
            List<Integer> ids_a_remover = new ArrayList<Integer>();
            for (int j = 0; j < LoginRegister.id_devolvido.size(); j++) {
                String mensagem = "O usuário de id " + LoginRegister.id_devolvido.get(j) + " devolveu o livro "
                        + LoginRegister.isbn_devolvido.get(j)
                        + ".\nSe este livro foi devolvido, clique em OK.\nCaso contrário, clique em CANCELAR.";
                int resultado = JOptionPane.showConfirmDialog(null, mensagem, "Livro devolvido",
                        JOptionPane.OK_CANCEL_OPTION);
                if (resultado == JOptionPane.OK_OPTION) {
                    LoginRegister.id_devolvido.remove(j);
                    LoginRegister.isbn_devolvido.remove(j);
                    JOptionPane.showMessageDialog(null, "Confirmado que o livro foi devolvido!");
                } else if (resultado == JOptionPane.CANCEL_OPTION) {
                    if (!LoginRegister.id_multa.contains(LoginRegister.id_devolvido.get(j))) {
                        String mensagemMulta = "Aplicar multa? Digite S para aplicar multa caso tenha passado o prazo, ou N caso contrário.";
                        String resposta = JOptionPane.showInputDialog(null, mensagemMulta, "Multa",
                                JOptionPane.QUESTION_MESSAGE);
                        boolean multado = false;
                        if (resposta != null && resposta.equalsIgnoreCase("S")) {
                            JOptionPane.showMessageDialog(null, "Multa aplicada!");
                            LoginRegister.id_multa.add(LoginRegister.id_devolvido.get(j));
                            multado = true;
                        } else if (resposta != null && resposta.equalsIgnoreCase("N")) {
                            JOptionPane.showMessageDialog(null, "Multa não aplicada!");
                            multado = true;
                        }
                        if (multado) {
                            for (int k = 0; k < ids_a_remover.size(); k++) {
                                int id = ids_a_remover.get(k);
                                if (id == LoginRegister.id_devolvido.get(j)) {
                                    ids_a_remover.remove(k);
                                    break;
                                }
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Usuário já foi multado!");
                    }
                }
            }
        }
    }

    
}
