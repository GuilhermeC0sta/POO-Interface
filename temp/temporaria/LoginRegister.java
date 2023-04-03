import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import java.util.ArrayList;

public class LoginRegister extends JFrame implements ActionListener {
    public ArrayList<conta> contas = new ArrayList<conta>();
    public ArrayList<Livro> livros = new ArrayList<Livro>();
    public ArrayList<audiobook> audiobook2 = new ArrayList<audiobook>();
    private ArrayList<Integer> id_user = new ArrayList<>();
    private ArrayList<Integer> id_userAudio = new ArrayList<>();
    private ArrayList<Integer> isbn_locado = new ArrayList<>();
    private ArrayList<Integer> isbn_devolvido = new ArrayList<>();
    private ArrayList<Integer> audio_locado = new ArrayList<>();
    private ArrayList<Integer> id_devolvido = new ArrayList<>();

    public int sinal = 0;
    public int id = 0;
    public int index_user;
    public int contalocados = 0;
    
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
                    if (plano2.equals("a")) {
                        contas.add(new comum(email2, senha2, usuario, id));
                        contas.get(id).defPlano("a");
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

        private JButton audios, books, locar, devolucao ,locarL, editar, mostrar, devolver, verificar, pagarmultas, vermultas, verutensilios, mostrarL, mostrarTudo;
        private JLabel titleLabel;
        private JTextField codigo, item, itemD, codigoD;
        public int isbn;
        public int codigoAudio;


        JFrame frameLocar2 = new JFrame();
        JFrame frameLocarA = new JFrame();
        JFrame frameLocar3 = new JFrame();
        JFrame frameEditar = new JFrame();

        JPanel panel3 = new JPanel();
        JPanel panelEditar = new JPanel();
        JPanel panel5 = new JPanel();
        JPanel panelLocadosA = new JPanel();
        //JPanel panelLocados = new JPanel();

        //JPanel panelLocadosL = new JPanel();
        JFrame frameMostrar = new JFrame();
        JFrame frameVerificar = new JFrame();
        JFrame frameLocar = new JFrame();
        JFrame frameDevolver = new JFrame();
        //JPanel panel4 = new JPanel();
        //JFrame frameLocar2 = new JFrame();



        public TelaInicial() {
            super("Tela inicial");
            setLayout(new BorderLayout());
            setSize(400, 400);
            JPanel titlePanel = new JPanel();
            titleLabel = new JLabel("Escolha o que deseja fazer: ");
            titlePanel.add(titleLabel);
            add(titlePanel, BorderLayout.NORTH);


            Dimension botaoDimensao = new Dimension(250, 30);

            if (contas.get(index_user).getPlano().equalsIgnoreCase("a") || contas.get(index_user).getPlano().equalsIgnoreCase("premium")) { // ta vindo como nulo
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


                books = new JButton("Mostrar livros");
                books.setPreferredSize(botaoDimensao);
                books.setMaximumSize(botaoDimensao);
                books.setMinimumSize(botaoDimensao);
                books.addActionListener(this);
                books.setAlignmentX(Component.CENTER_ALIGNMENT);
                // panel.add(books);
                // panel.add(Box.createRigidArea(new Dimension(0, 10)));

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
            
            if(sinal == 0){ //gambiarra
                livros.add(new Livro("teste", "Micael", 123, 0, "luta"));
                livros.add(new Livro("teste2", "Micael2", 1234, 150, "romance"));
                audiobook2.add(new audiobook("teste3audio", "Micael3", 120, 0, "aventura", 25));
                audiobook2.add(new audiobook("teste3audiosss", "Micael4", 120, 20, "ação", 255));
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
                        JPanel panel4 = new JPanel();
                        //JFrame frameLocar2 = new JFrame();
                        frameLocar2.setSize(400, 400);
                        frameLocar2.add(panel4);
                        frameLocar2.setVisible(true);
                        for (Livro livro : livros) {
                            if (livro instanceof Livro) {
                                JLabel tituloLabel2 = new JLabel("Título: " + livro.getTitulo());
                                JLabel isbnLabel = new JLabel("ISBN: " + livro.getIsbn());
                                JLabel qntdLabel = new JLabel("Quantidade: \n" + livro.getQnt_disp());
                                // adicionar componentes ao painel central
                                panel4.add(tituloLabel2);
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
                        JPanel panel4 = new JPanel();
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

                                //getContentPane().add(books, BorderLayout.CENTER);
                                revalidate(); // atualizar layout do JFrame
                            }
                        }
                    }
                });
            }

            else if(e.getSource() == locar) {
                JPanel panel2 = new JPanel(new GridLayout(3, 2));
                panel2.add(new JLabel("Digite se quer um livro/audiobook"));
                item = new JTextField();
                item.setPreferredSize(new Dimension(150, 30));
                panel2.add(item);
                panel2.add(new JLabel("Digite o codigo do livro/audiobook"));
                codigo = new JTextField();
                codigo.setPreferredSize(new Dimension(150, 30));
                panel2.add(codigo);  
            
                frameLocar.add(panel2);
                frameLocar.setSize(500, 300);
                frameLocar.setVisible(true);
                panel2.add(Box.createRigidArea(new Dimension(0, 10)));

                locarL = new JButton("Locar livro/audiobook");
                locarL.addActionListener(this);
                panel2.add(locarL);

                contalocados = 0;
                locarL.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        for(int k = 0; k < id_user.size(); k++){
                            if(id_user.get(k) == index_user){
                                contalocados+=1;
                            }
                        }

                        if(contas.get(index_user).getPlano().equalsIgnoreCase("a")){
                            if(contalocados < 1){
                                if(item.getText().equalsIgnoreCase("livro")){
                                    isbn = Integer.parseInt(codigo.getText());
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
                                else if(item.getText().equalsIgnoreCase("audiobook")){
        
                                    codigoAudio = Integer.parseInt(codigo.getText());
                                    
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
                            }
                        }

                        if(contas.get(index_user).getPlano().equalsIgnoreCase("premium")){
                            if(contalocados < 15){
                                if(item.getText().equalsIgnoreCase("livro")){
                                    isbn = Integer.parseInt(codigo.getText());
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
        
                                else if(item.getText().equalsIgnoreCase("audiobook")){
                                    codigoAudio = Integer.parseInt(codigo.getText());
                                    
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
                            }
                        }
                    }
                }
                );
            }

            else if (e.getSource() == verificar) {
                frameVerificar.setSize(400, 400);
                frameVerificar.add(panel5);

                panel5.add(mostrarTudo);
                panel5.add(Box.createRigidArea(new Dimension(0, 50)));

                mostrarTudo.setAlignmentX(Component.CENTER_ALIGNMENT);
                frameVerificar.setVisible(true);

                mostrarTudo.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        JPanel panelLocados = new JPanel();
                        //JFrame frameLocar2 = new JFrame();
                        frameLocarA.setSize(400, 400);
                        frameLocarA.add(panelLocados);
                        frameLocarA.setVisible(true);

                        if (id_userAudio.size() == 0) {
                            JOptionPane.showMessageDialog(null, "NAO HÁ AUDIOS LOCADOS");
                        } else {
                            for (int k = 0; k < id_userAudio.size(); k++) {
                                if (id_userAudio.get(k) == index_user) {
                                    JLabel tituloLabel6 = new JLabel("Audios: " + audio_locado.get(k));
                                    panelLocados.add(tituloLabel6);
                                    revalidate();
                                }
                            }
                        }
                        if (id_user.size() == 0) {
                            JOptionPane.showMessageDialog(null, "NAO HÁ LIVROS LOCADOS");
                        } else {  
                            for (int k = 0; k < id_user.size(); k++) {
                                if (id_user.get(k) == index_user) {
                                    JLabel tituloLabel2 = new JLabel("Livros: " + isbn_locado.get(k));
                                    panelLocados.add(tituloLabel2);
                                    revalidate();
                                }
                            }
                        }
                    }
                }
                );


            }

            else if(e.getSource() == devolver){
                JPanel panelDevolver = new JPanel(new GridLayout(3, 2));
                panelDevolver.add(new JLabel("Digite se quer devolver um livro"));
                itemD = new JTextField();
                itemD.setPreferredSize(new Dimension(150, 30));
                panelDevolver.add(itemD);
                panelDevolver.add(new JLabel("Digite o codigo do livro"));
                codigoD = new JTextField();
                codigoD.setPreferredSize(new Dimension(150, 30));
                panelDevolver.add(codigoD);  

                frameDevolver.add(panelDevolver);
                frameDevolver.setSize(500, 300);
                frameDevolver.setVisible(true);
                panelDevolver.add(Box.createRigidArea(new Dimension(0, 10)));

                devolucao = new JButton("Devolver livro");
                devolucao.addActionListener(this);
                panelDevolver.add(devolucao);

                devolucao.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if(itemD.getText().equalsIgnoreCase("livro")){
                            isbn = Integer.parseInt(codigoD.getText());
                            for (int j = 0; j < id_user.size(); j++) {
                                if (id_user.get(j) == index_user) {
                                    if (isbn_locado.get(j) == isbn) {
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
                                        break;
                                    }
                                }
                                if (j == id_user.size() - 1) {
                                    JOptionPane.showMessageDialog(null, "Este livro não foi locado por você");
                                }
                            }
                        }
                    }
                });
            }

            else if(e.getSource() == editar){

                frameEditar.setSize(400, 400);
                frameEditar.add(panelEditar);

                panelEditar.add(mostrarTudo);
                panelEditar.add(Box.createRigidArea(new Dimension(0, 50)));

                mostrarTudo.setAlignmentX(Component.CENTER_ALIGNMENT);
                frameVerificar.setVisible(true);

                mostrarTudo.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        JPanel panelLocados = new JPanel();
                        //JFrame frameLocar2 = new JFrame();
                        frameLocarA.setSize(400, 400);
                        frameLocarA.add(panelLocados);
                        frameLocarA.setVisible(true);

                        if (id_userAudio.size() == 0) {
                            JOptionPane.showMessageDialog(null, "NAO HÁ AUDIOS LOCADOS");
                        } else {
                            for (int k = 0; k < id_userAudio.size(); k++) {
                                if (id_userAudio.get(k) == index_user) {
                                    JLabel tituloLabel6 = new JLabel("Audios: " + audio_locado.get(k));
                                    panelLocados.add(tituloLabel6);
                                    revalidate();
                                }
                            }
                        }
                        if (id_user.size() == 0) {
                            JOptionPane.showMessageDialog(null, "NAO HÁ LIVROS LOCADOS");
                        } else {  
                            for (int k = 0; k < id_user.size(); k++) {
                                if (id_user.get(k) == index_user) {
                                    JLabel tituloLabel2 = new JLabel("Livros: " + isbn_locado.get(k));
                                    panelLocados.add(tituloLabel2);
                                    revalidate();
                                }
                            }
                        }
                    }
                }
                );

            }
















        }
    }
}
