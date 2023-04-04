import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

import java.util.ArrayList;

public class LoginRegister extends JFrame implements ActionListener {
    public ArrayList<conta> contas = new ArrayList<conta>();
    public ArrayList<Livro> livros = new ArrayList<Livro>();
    public ArrayList<audiobook> audiobook2 = new ArrayList<audiobook>();
    public ArrayList<utensilios> utensilios = new ArrayList<utensilios>();
    private ArrayList<Integer> id_user = new ArrayList<>();
    private ArrayList<Integer> id_userAudio = new ArrayList<>();
    private ArrayList<Integer> isbn_locado = new ArrayList<>();
    private ArrayList<Integer> isbn_devolvido = new ArrayList<>();
    private ArrayList<Integer> audio_locado = new ArrayList<>();
    private ArrayList<Integer> id_devolvido = new ArrayList<>();
    private ArrayList<Integer> id_multapendente = new ArrayList<>();
    private ArrayList<Integer> id_multapaga = new ArrayList<>();
    private ArrayList<Integer> id_multa = new ArrayList<>();

    public int sinal = 0;
    public int id = 0;
    public int index_user;
    public int contalocados = 0;
    public int auxteste = 0;
    public int auxteste3 = 0;
    public int auxteste2 = 0;
    public int multaalarme = 0;
    public int testemultas = 0;
    
    private JButton login, register;
    private JTextField usuario, senha;

    public LoginRegister() {
        super("Libraric");

        JPanel panel = new JPanel(new GridLayout(4, 2));
        JPanel imagePanel = new JPanel();
        JLabel imageLabel = new JLabel(new ImageIcon("lib2.jpg"));
        imageLabel.setPreferredSize(new Dimension(350, 250));
        imagePanel.add(imageLabel);

        panel.add(new JLabel("Usuario:"));
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
                    }
                }
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
                        contas.get(id).defPlano("premium");
                        id += 1;
                        JOptionPane.showMessageDialog(null, "Conta premium registrada com sucesso!");
                    }
                    if (plano2.equals("comum")) {
                        contas.add(new comum(email2, senha2, usuario, id));
                        contas.get(id).defPlano("comum");
                        id += 1;
                        JOptionPane.showMessageDialog(null, "Conta comum registrada com sucesso!");
                    }
                    if (plano2.equals("administrador")) {
                        contas.add(new admin(email2, senha2, usuario, id));
                        contas.get(id).defPlano("administrador");
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

        private JButton audios, books, locar,  editar, mostrar, devolver, verificar, pagarmultas, vermultas, verutensilios, mostrarTudo, postitbutton, marca_textobutton, apoio_livrosbutton;
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

        public TelaInicial() {
            super("Tela inicial");
            setLayout(new BorderLayout());
            setSize(400, 400);
            JPanel titlePanel = new JPanel();
            titleLabel = new JLabel("Escolha o que deseja fazer: ");
            titlePanel.add(titleLabel);
            add(titlePanel, BorderLayout.NORTH);


            Dimension botaoDimensao = new Dimension(250, 30);

            if (contas.get(index_user).getPlano().equalsIgnoreCase("comum") || contas.get(index_user).getPlano().equalsIgnoreCase("premium")){
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
                
                postitbutton = new JButton("Mostrar todos os postits");
                postitbutton.setPreferredSize(botaoDimensao);
                postitbutton.setMaximumSize(botaoDimensao);
                postitbutton.setMinimumSize(botaoDimensao);
                postitbutton.addActionListener(this);
                postitbutton.setAlignmentX(Component.CENTER_ALIGNMENT);

                marca_textobutton = new JButton("Mostrar todos os marca-textos");
                marca_textobutton.setPreferredSize(botaoDimensao);
                marca_textobutton.setMaximumSize(botaoDimensao);
                marca_textobutton.setMinimumSize(botaoDimensao);
                marca_textobutton.addActionListener(this);
                marca_textobutton.setAlignmentX(Component.CENTER_ALIGNMENT);

                apoio_livrosbutton = new JButton("Mostrar todos os apoios para livros");
                apoio_livrosbutton.setPreferredSize(botaoDimensao);
                apoio_livrosbutton.setMaximumSize(botaoDimensao);
                apoio_livrosbutton.setMinimumSize(botaoDimensao);
                apoio_livrosbutton.addActionListener(this);
                apoio_livrosbutton.setAlignmentX(Component.CENTER_ALIGNMENT);

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
            
            else if(contas.get(index_user).getPlano().equalsIgnoreCase("administrador")){

                JPanel panelAdmin = new JPanel();
                panelAdmin.setLayout(new BoxLayout(panelAdmin, BoxLayout.Y_AXIS));
                //add_itens, remover_itens, livros_devolvidos, multas, cadastros; 
                panelAdmin.add(Box.createRigidArea(new Dimension(0, 50)));

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

                add(panelAdmin);
                setVisible(true);
                
            }

            if(sinal == 0){ //gambiarra
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
                //JPanel panel3 = new JPanel();
                //JFrame frameMostrar = new JFrame();
                frameMostrar.setSize(400, 400);
                frameMostrar.add(panel3);
                //titleLabel = new JLabel("Escolha uma das duas opções abaixo: ");
                //panel3.add(titleLabel);
                // add(frameMostrar);
                panel3.add(books);
                panel3.add(Box.createRigidArea(new Dimension(0, 50)));
                panel3.add(audios);
            
                books.setAlignmentX(Component.CENTER_ALIGNMENT);
                audios.setAlignmentX(Component.CENTER_ALIGNMENT);
                frameMostrar.setVisible(true);
            
                books.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        JPanel panel4 = new JPanel(new GridLayout(0, 3));
                        JFrame frameLocar2 = new JFrame();
                        frameLocar2.setSize(400, 400);
                        frameLocar2.add(panel4);
                        frameLocar2.setVisible(true);
                        panel4.removeAll(); // remove todos os componentes do painel
            
                        for (Livro livro : livros) {
                            if (livro instanceof Livro) {
                                JLabel tituloLabel2 = new JLabel("Título: " + livro.getTitulo());
                                JLabel isbnLabel = new JLabel("ISBN: " + livro.getIsbn());
                                JLabel qntdLabel = new JLabel("Quantidade: \n" + livro.getQnt_disp());
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
                        for (audiobook audio : audiobook2) {
                            if (audio instanceof audiobook) {
                                JLabel tituloLabel3 = new JLabel("Título: " + audio.getTitulo());
                                JLabel codigoLabel = new JLabel("Codigo do áudio: " + audio.getAudio());
                                JLabel qntdLabel3 = new JLabel("Quantidade: \n" + audio.getQnt_disp());
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
            }


            else if (e.getSource() == locar) {
                // Cria caixas de texto para nome, email e bio
                JTextField itemField = new JTextField(20);
                JTextField codigoField = new JTextField(20);
                
                // Cria um painel para as caixas de texto
                JPanel panelLocar = new JPanel();
                panelLocar.setLayout(new GridLayout(3, 2)); // GridLayout com 3 linhas e 2 colunas
                panelLocar.add(new JLabel("Digite se quer um livro/audiobook:"));
                panelLocar.add(itemField);
                panelLocar.add(new JLabel("Digite o codigo do livro/audiobook:"));
                panelLocar.add(codigoField);
            
                // Exibe um JOptionPane com as caixas de texto e espera o usuário clicar em OK
                int resultLocar = JOptionPane.showConfirmDialog(null, panelLocar, "Locar", JOptionPane.OK_CANCEL_OPTION);
                contalocados = 0;
                if (resultLocar == JOptionPane.OK_OPTION){
                    // Obtém o texto digitado nas caixas de texto
                    //String item = itemField.getText();
                    //String codigo = codigoField.getText();
                    
                    for(int k = 0; k < id_user.size(); k++){
                        if(id_user.get(k) == index_user){
                            contalocados+=1;
                        }
                    }
            
                    if(contas.get(index_user).getPlano().equalsIgnoreCase("comum")){
                        if(contalocados < 1){
                            if(itemField.getText().equalsIgnoreCase("livro")){
                                isbn = Integer.parseInt(codigoField.getText());
                                for (int k = 0; k < livros.size(); k++) {
                                    if (isbn == livros.get(k).getIsbn()) {
                                        if (livros.get(k).getQnt_disp() == 0) {
                                            JOptionPane.showMessageDialog(null, "Livro indisponível, tente novamente em outro momento.");
                                            break;
                                        } else {
                                            int quantidadeL = livros.get(k).getQnt_disp() - 1;
                                            livros.set(k, new Livro(livros.get(k).getTitulo(), livros.get(k).getAutor(), isbn,
                                                    quantidadeL, livros.get(k).getGenero()));
                                            JOptionPane.showMessageDialog(null, "Parabéns, você conseguiu locar um livro!");
                                            id_user.add(contas.get(index_user).getId()); //esse id antes era id
                                            isbn_locado.add(isbn);
                                            //frameLocar.dispose();
                                            break;
                                        }
                                    }
                                }
                            }
                            else if(itemField.getText().equalsIgnoreCase("audiobook")){
            
                                codigoAudio = Integer.parseInt(codigoField.getText());
                                
                                for (int k = 0; k < audiobook2.size(); k++) {
                                    if (codigoAudio == audiobook2.get(k).getAudio()) {
                                        if (audiobook2.get(k).getQnt_disp() == 0) {
                                            JOptionPane.showMessageDialog(null, "Audiobook indisponível, tente novamente em outro momento.");
                                            break;
                                        } else {
                                            int quantidadeA = audiobook2.get(k).getQnt_disp() - 1;
                                            audiobook2.set(k,
                                            new audiobook(audiobook2.get(k).getTitulo(), audiobook2.get(k).getAutor(), audiobook2.get(k).getDuracao(), quantidadeA, audiobook2.get(k).getGenero(), codigoAudio));
                                            JOptionPane.showMessageDialog(null, "Parabéns, você conseguiu locar um audiobook!");
                                            id_userAudio.add(contas.get(index_user).getId()); //antes tava k, index_user
                                            audio_locado.add(codigoAudio);
                                            //frameLocar.dispose();
                                            break;
                                        }
                                    }
                                }
                            }
                            contalocados+=1;
                        }
                        else{
                            JOptionPane.showMessageDialog(null, "Você não pode locar mais de um item pois a sua conta é comum");
                        }
                    }
            
                    if(contas.get(index_user).getPlano().equalsIgnoreCase("premium")){
                        if(contalocados < 15){
                            if(itemField.getText().equalsIgnoreCase("livro")){
                                isbn = Integer.parseInt(codigoField.getText());
                                for (int k = 0; k < livros.size(); k++) {
                                    if (isbn == livros.get(k).getIsbn()) {
                                        if (livros.get(k).getQnt_disp() == 0) {
                                            JOptionPane.showMessageDialog(null, "Livro indisponível, tente novamente em outro momento.");
                                            break;
                                        } else {
                                            int quantidadeL = livros.get(k).getQnt_disp() - 1;
                                            livros.set(k, new Livro(livros.get(k).getTitulo(), livros.get(k).getAutor(), isbn,
                                                    quantidadeL, livros.get(k).getGenero()));
                                            JOptionPane.showMessageDialog(null, "Parabéns, você conseguiu locar um livro!");
                                            id_user.add(index_user); //esse id antes era id
                                            isbn_locado.add(isbn);
                                            break;
                                        }
                                    }
                                }
                            }
            
                            else if(itemField.getText().equalsIgnoreCase("audiobook")){
                                codigoAudio = Integer.parseInt(codigoField.getText());
                                
                                for (int k = 0; k < audiobook2.size(); k++) {
                                    if (codigoAudio == audiobook2.get(k).getAudio()) {
                                        if (audiobook2.get(k).getQnt_disp() == 0) {
                                            JOptionPane.showMessageDialog(null, "Audiobook indisponível, tente novamente em outro momento.");
                                            break;
                                        } else {
                                            audiobook2.set(k,
                                            new audiobook(audiobook2.get(k).getTitulo(), audiobook2.get(k).getAutor(), audiobook2.get(k).getDuracao(), audiobook2.get(k).getQnt_disp() - 1, audiobook2.get(k).getGenero(), codigoAudio));
                                            JOptionPane.showMessageDialog(null, "Parabéns, você conseguiu locar um audiobook!");
                                            id_userAudio.add(index_user);
                                            audio_locado.add(codigoAudio);
                                            //frameLocar.dispose();
                                            break;
                                        }
                                    }
                                }
                            }
                            contalocados+=1;
                        }
                        else{
                            JOptionPane.showMessageDialog(null, "Você só pode locar até no máximo 15 itens");
                        }
                    }
                }
            }

            else if (e.getSource() == verificar) {
                auxteste3 = 0;
                auxteste = 0; 
                frameVerificar.setSize(400, 400);
                frameVerificar.add(panel5);
                        
                panel5.add(mostrarTudo);
                panel5.add(Box.createRigidArea(new Dimension(0, 50)));
                        
                mostrarTudo.setAlignmentX(Component.CENTER_ALIGNMENT);
                frameVerificar.setVisible(true);
                        
                mostrarTudo.addActionListener(new ActionListener() {
                    boolean janelaAberta = false;
                    public void actionPerformed(ActionEvent e) {
                        if (!janelaAberta) {
                            janelaAberta = true;
                            JPanel panelLocados = new JPanel();
                            frameLocarA.setSize(400, 400);
                            frameLocarA.add(panelLocados);
                            frameLocarA.setVisible(true);
                            
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
            } 

            else if (e.getSource() == devolver) {
                // Cria caixas de texto para nome, email e bio
                JTextField itemField2 = new JTextField();
                JTextField codigoField2 = new JTextField();
                
                // Cria um painel para as caixas de texto
                JPanel panelDevolver = new JPanel();
                panelDevolver.setLayout(new GridLayout(3, 2)); // GridLayout com 3 linhas e 2 colunas
                panelDevolver.add(new JLabel("Digite livro para devolver um livro"));
                panelDevolver.add(itemField2);
                panelDevolver.add(new JLabel("Digite o codigo do livro:"));
                panelDevolver.add(codigoField2);
            
                // Exibe um JOptionPane com as caixas de texto e espera o usuário clicar em OK
                int resultDevolver = JOptionPane.showConfirmDialog(null, panelDevolver, "Devolver", JOptionPane.OK_CANCEL_OPTION);
                if (resultDevolver == JOptionPane.OK_OPTION){
                    if(itemField2.getText().equalsIgnoreCase("livro")){
                        isbn = Integer.parseInt(codigoField2.getText());
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
                                            //System.out.println(livros.get(k).getIsbn());
                                            int devolu = livros.get(k).getQnt_disp() + 1;
                                            //System.out.println("DPS DE ADICIONAR " + devolu);
                                            livros.set(k,
                                                    new Livro(livros.get(k).getTitulo(), livros.get(k).getAutor(), isbn, devolu, livros.get(k).getGenero()));
                                            break;
                                        }
                                    }
                                }
                            }
                            else if (j == id_user.size() - 1) {
                                JOptionPane.showMessageDialog(null, "Este livro não foi locado por você");
                            }
                        }
            
                    }
                }
            }

            else if (e.getSource() == editar) {
                // Cria caixas de texto para nome, email e bio
                JTextField nomeField = new JTextField(20);
                JTextField emailField = new JTextField(20);
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
                int result = JOptionPane.showConfirmDialog(null, panelEditar, "Editar perfil", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION){
                    // Obtém o texto digitado nas caixas de texto
                    String nome = nomeField.getText();
                    String email = emailField.getText();
                    String bio = bioField.getText();
                    
                    if (contas.get(index_user).getPlano().equalsIgnoreCase("comum")) {

                        if (bio.length() > 50) {
                            JOptionPane.showMessageDialog(null, "Você excedeu o número de caracteres");
                        } else if (bio == "00") {
                        } else {
                            contas.get(index_user).defBios(bio);
                        }
                        contas.set(index_user, new comum(email, contas.get(index_user).getSenha(), nome, index_user)); //antes era id
                        contas.get(index_user).defPlano("a");
                        JOptionPane.showMessageDialog(null, "Nome e email alterados com sucesso!");
                    }

                    else if (contas.get(index_user).getPlano().equalsIgnoreCase("premium")) {

                        if (bio.length() > 200) {
                            JOptionPane.showMessageDialog(null, "Você excedeu o número de caracteres");
                        } else if (bio == "00") {
                        } else {
                            contas.get(index_user).defBios(bio);
                        }
                        contas.set(index_user, new premium(email, contas.get(index_user).getSenha(), nome, index_user)); //antes era id
                        contas.get(index_user).defPlano("premium");
                        JOptionPane.showMessageDialog(null, "Nome e email alterados com sucesso!");
                    }
                }
            }

            else if (e.getSource() == verutensilios) {
                auxteste3 = 0;
                auxteste2 = 0;
                auxteste = 0; 
                frameUtensilio.setSize(400, 400);
                frameUtensilio.add(panel6);
                panel6.add(marca_textobutton);
                panel6.add(Box.createRigidArea(new Dimension(0, 50)));
                panel6.add(postitbutton);
                panel6.add(Box.createRigidArea(new Dimension(0, 50)));
                panel6.add(apoio_livrosbutton);
            
                marca_textobutton.setAlignmentX(Component.CENTER_ALIGNMENT);
                postitbutton.setAlignmentX(Component.CENTER_ALIGNMENT);
                apoio_livrosbutton.setAlignmentX(Component.CENTER_ALIGNMENT);
                frameUtensilio.setVisible(true);
            
                postitbutton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if(auxteste3 == 0){
                            auxteste3 = 1;
                            JPanel panelUten = new JPanel(new GridLayout(0, 4));
                            JFrame frameUten = new JFrame();
                            frameUten.setSize(400, 400);
                            frameUten.add(panelUten);
                            frameUten.setVisible(true);
                            panelUten.removeAll(); // remove todos os componentes do painel
                            
                            for (utensilios utensi : utensilios) {
                                if (utensi instanceof postit) {
                                    JLabel corlabel = new JLabel("Cor: " + utensi.getCor());
                                    JLabel marcalabel = new JLabel("ISBN: " + utensi.getMarca());
                                    JLabel qntdLabel = new JLabel("Quantidade: \n" + utensi.getQuantidade());
                                    // adicionar componentes ao painel central
                                    panelUten.add(corlabel);
                                    panelUten.add(marcalabel);
                                    panelUten.add(qntdLabel);
                                }
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
            
                marca_textobutton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if(auxteste == 0){
                            auxteste = 1;
                            JPanel panelUten = new JPanel(new GridLayout(4, 2));
                            JFrame frameUten = new JFrame();
                            frameUten.setSize(400, 400);
                            frameUten.add(panelUten);
                            frameUten.setVisible(true);
                            panelUten.removeAll(); // remove todos os componentes do painel
                            
                            for (utensilios utensi : utensilios) {
                                if (utensi instanceof marca_texto) {
                                        JLabel corlabel = new JLabel("Cor: " + utensi.getCor());
                                        JLabel marcalabel = new JLabel("ISBN: " + utensi.getMarca());
                                        JLabel qntdLabel = new JLabel("Quantidade: \n" + utensi.getQuantidade());
                                        JLabel combrilhoLabel = new JLabel("Brilho: \n" + utensi.getBrilho());
                                        // adicionar componentes ao painel central
                                        panelUten.add(corlabel);
                                        panelUten.add(marcalabel);
                                        panelUten.add(qntdLabel);
                                        panelUten.add(combrilhoLabel);
                                    }
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

                apoio_livrosbutton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if(auxteste2 == 0){
                            auxteste2 = 1;
                            JPanel panelUten = new JPanel(new GridLayout(4, 2));
                            JFrame frameUten = new JFrame();
                            frameUten.setSize(400, 400);
                            frameUten.add(panelUten);
                            frameUten.setVisible(true);
                            panelUten.removeAll(); // remove todos os componentes do painel
                            
                            for (utensilios utensi : utensilios) {
                                if (utensi instanceof apoio_livros) {
                                    JLabel slotslabel = new JLabel("Cor: " + utensi.getSlots());
                                    JLabel marcalabel = new JLabel("Marca: " + utensi.getMarca());
                                    JLabel qntdLabel = new JLabel("Quantidade: \n" + utensi.getQuantidade());
                                    // adicionar componentes ao painel central
                                    panelUten.add(slotslabel);
                                    panelUten.add(marcalabel);
                                    panelUten.add(qntdLabel);
                                }
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
            }

            else if (e.getSource() == vermultas) {
                auxteste3 = 0;
                if (auxteste3 == 0) {
                    auxteste3 = 1;
                    JPanel panelMP = new JPanel();
                    JFrame frameMP = new JFrame();
                    frameMP.setSize(400, 400);
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
            }
            
            else if(e.getSource() == pagarmultas){
                testemultas = 0;
                auxteste3 = 0;
                if (auxteste3 == 0) {
                    auxteste3 = 1;
                    JPanel panelMP = new JPanel(new GridLayout(0,1));
                    JFrame frameMP = new JFrame();
                    frameMP.setSize(400, 400);
                    frameMP.add(panelMP);
                    panelMP.removeAll();
                    buttonPGM.setSize(10,10);
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
                            testemultas +=1;
                        }
                    }
                    if(testemultas != 0){
                        panelMP.add(buttonPGM);
                    }
                    else{
                        JLabel multalabel2 = new JLabel("Você pagou todas suas multas ou não possui multas");
                        panelMP.add(multalabel2);
                    }
                    buttonPGM.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent e){
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
                }
                
            }

            else if (e.getSource() == add_itens){
                String[] opcoes = {"Livro", "Audiobook", "Utensilio"};
                int opcaoSelecionada = JOptionPane.showOptionDialog(null, "Selecione o tipo de item a adicionar:", "Adicionar Itens", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, opcoes, opcoes[0]);
                
                if (opcaoSelecionada == 0){ // Clicou em "Livro"
                    JTextField tituloAdd = new JTextField();
                    JTextField autorAdd = new JTextField();
                    JTextField isbnAdd = new JTextField();
                    JTextField generoAdd = new JTextField();
                    JTextField quantidadeAdd = new JTextField();
                    JPanel panelItens = new JPanel(new GridLayout(0, 1));

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

                    int result = JOptionPane.showConfirmDialog(null, panelItens, "Adicionar Livro", JOptionPane.OK_CANCEL_OPTION);
                    int isbnAddItem = Integer.parseInt(isbnAdd.getText());
                    int quantidadeAddItem = Integer.parseInt(quantidadeAdd.getText());
                    
                    if (result == JOptionPane.OK_OPTION){
                        livros.add(new Livro(tituloAdd.getText(), autorAdd.getText(), isbnAddItem, quantidadeAddItem, generoAdd.getText()));
                        JOptionPane.showMessageDialog(null, "Livro adicionado!");
                    }
                }
                else if (opcaoSelecionada == 1){ // Clicou em "Audiobook"
                    JTextField tituloAdd = new JTextField();
                    JTextField autorAdd = new JTextField();
                    JTextField duracaoAdd = new JTextField();
                    JTextField generoAdd = new JTextField();
                    JTextField quantidadeAdd = new JTextField();
                    JTextField idAudioAdd = new JTextField();

                    JPanel panelItens = new JPanel(new GridLayout(0, 1));

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

                    int result = JOptionPane.showConfirmDialog(null, panelItens, "Adicionar Audiobook", JOptionPane.OK_CANCEL_OPTION);
                    
                    int duracaoAddItem = Integer.parseInt(duracaoAdd.getText());
                    int quantidadeAddItem = Integer.parseInt(quantidadeAdd.getText());
                    int idAudioAddItem = Integer.parseInt(idAudioAdd.getText());
                    

                    if (result == JOptionPane.OK_OPTION){
                        audiobook2.add(new audiobook(tituloAdd.getText(), autorAdd.getText(), duracaoAddItem, quantidadeAddItem, generoAdd.getText(), idAudioAddItem));
                        JOptionPane.showMessageDialog(null, "Audio adicionado!");
                    }
                }

                else if (opcaoSelecionada == 2){ // Clicou em "Utensilio"
                    
                }
            }

            else if(e.getSource() == cadastros){
                auxteste2 = 0;
                if(auxteste2 == 0){
                    auxteste2 = 1;
                    JPanel panelCadastros = new JPanel(new GridLayout(0, 4));
                    JFrame frameCadastros = new JFrame();
                    frameCadastros.setSize(400, 400);
                    frameCadastros.add(panelCadastros);
                    frameCadastros.setVisible(true);
                    panelCadastros.removeAll(); // remove todos os componentes do painel
                    
                    for (conta user : contas) {
                        if (user instanceof conta) {
                            JLabel NomeLabel = new JLabel("Nome: " + user.getNome());
                            JLabel EmailLabel = new JLabel("Email: " + user.getEmail());
                            JLabel planoLabel = new JLabel("Plano: \n" + user.getPlano());
                            panelCadastros.add(NomeLabel);
                            panelCadastros.add(EmailLabel);
                            panelCadastros.add(planoLabel);
                            panelCadastros.add(Box.createRigidArea(new Dimension(0, 10)));
                        }
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
            }

            else if(e.getSource() == buttonconfirmar){
                auxteste2 = 0;
                if(auxteste2 == 0){
                    JPanel panelCP = new JPanel(new GridLayout(0,1));
                    JFrame frameCP = new JFrame();
                    frameCP.setSize(400, 400);
                    frameCP.add(panelCP);
                    panelCP.removeAll();
                    buttonCP.setSize(10,10);
                    multaalarme = 0; // redefine a variável multaalarme para 0
                
                    for (int j = 0; j < id_multapaga.size(); j++) {
                        multaalarme = 1;
                        JLabel multalabel = new JLabel("O Usuario: " + id_multapaga.get(j) + " pagou uma multa");
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
                    if(id_multapaga.size() != 0){
                        panelCP.add(buttonCP);
                    }
                    else{
                        JLabel multalabel2 = new JLabel("Não há multas a serem confirmadas");
                        panelCP.add(multalabel2);
                    }
                    buttonCP.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent e){
                            for (int j = 0; j < id_multapaga.size(); j++) {
                                id_multapaga.remove(j);
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
            }

            else if (e.getSource() == remover_itens){

                JTextField itemFieldR = new JTextField();
                JTextField codigoFieldR = new JTextField();

                JPanel panelRemover = new JPanel();
                panelRemover.setLayout(new GridLayout(3, 2)); // GridLayout com 3 linhas e 2 colunas
                panelRemover.add(new JLabel("Digite livro/audiobook para remover um dos itens"));
                panelRemover.add(itemFieldR);
                panelRemover.add(new JLabel("Digite o codigo do livro/audiobook:"));
                panelRemover.add(codigoFieldR);

                int resultRemover = JOptionPane.showConfirmDialog(null, panelRemover, "Remover", JOptionPane.OK_CANCEL_OPTION);
                
                if (resultRemover == JOptionPane.OK_OPTION){
                    if(itemFieldR.getText().equalsIgnoreCase("livro")){
                        isbn = Integer.parseInt(codigoFieldR.getText());

                        for (int k = 0; k < livros.size(); k++) {
                            if (isbn == livros.get(k).getIsbn()) {
                                livros.remove(k);
                                JOptionPane.showMessageDialog(null, "Livro " + isbn + " removido!");
                            }
                        }
                        
                    }

                    else if(itemFieldR.getText().equalsIgnoreCase("audiobook")){
                        codigoR = Integer.parseInt(codigoFieldR.getText());

                        for (int k = 0; k < audiobook2.size(); k++) {
                            if (codigoR == audiobook2.get(k).getAudio()) {
                                audiobook2.remove(k);
                                JOptionPane.showMessageDialog(null, "Audiobook " + codigoR + " removido!");
                            }
                        }
                    }
                }
            }

            else if (e.getSource() == livros_devolvidos) {
                List<Integer> ids_a_remover = new ArrayList<Integer>();
                for (int j = 0; j < id_devolvido.size(); j++) {
                    String mensagem = "O usuário de id " + id_devolvido.get(j) + " devolveu o livro " + isbn_devolvido.get(j) + ".\nSe este livro foi devolvido, clique em OK.\nCaso contrário, clique em CANCELAR.";
                    int resultado = JOptionPane.showConfirmDialog(null, mensagem, "Livro devolvido", JOptionPane.OK_CANCEL_OPTION);
                    if (resultado == JOptionPane.OK_OPTION) {
                        id_devolvido.remove(j);
                        isbn_devolvido.remove(j);
                        JOptionPane.showMessageDialog(null, "Confirmado que o livro foi devolvido!");
                    } else if (resultado == JOptionPane.CANCEL_OPTION) {
                        if (!id_multa.contains(id_devolvido.get(j))) {
                            String mensagemMulta = "Aplicar multa? Digite S para aplicar multa caso tenha passado o prazo, ou N caso contrário.";
                            String resposta = JOptionPane.showInputDialog(null, mensagemMulta, "Multa", JOptionPane.QUESTION_MESSAGE);
                            boolean multado = false;
                            if (resposta != null && resposta.equalsIgnoreCase("S")) {
                                JOptionPane.showMessageDialog(null, "Multa aplicada!");
                                id_multa.add(id_devolvido.get(j));
                                multado = true;
                            } else if (resposta != null && resposta.equalsIgnoreCase("N")) {
                                JOptionPane.showMessageDialog(null, "Multa não aplicada!");
                                multado = true;
                            }
                            if (multado) {
                                for (int k = 0; k < ids_a_remover.size(); k++) {
                                    int id = ids_a_remover.get(k);
                                    if (id == id_devolvido.get(j)) {
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
}
