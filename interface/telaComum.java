import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class telaComum extends JFrame implements ActionListener { // fix
    private JButton locar, editar, mostrar, devolver, verificar, pagarmultas, vermultas,
            verutensilios, mostrarTudo, marca_textobutton, audios, books;
    Invoker invoker = new Invoker();
    int auxteste3 = 0;
    int index_user = LoginRegister.index_user;
    JFrame frameMultaPendente = new JFrame();
    JPanel panel6 = new JPanel();
    JButton buttonPGM = new JButton("Pagar Multa");
    Dimension botaoDimensao = new Dimension(250, 30);
    int multaalarme = 0;

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
            Command Mostrar = new Mostrar();
            invoker.setCommand(Mostrar);
            invoker.executeCommand();
        } else if (e.getSource() == locar) {
            Command LocarCommand = new LocarCommand();
            invoker.setCommand(LocarCommand);
            invoker.executeCommand();
        } else if (e.getSource() == verificar) {
            Command VerificarCommand = new VerificarCommand();
            invoker.setCommand(VerificarCommand);
            invoker.executeCommand();
        } else if (e.getSource() == devolver) {
            Command Devolver = new DevolverCommand();
            invoker.setCommand(Devolver);
            invoker.executeCommand();
        } else if (e.getSource() == editar) {
            Command Editar = new EditarCommand();
            invoker.setCommand(Editar);
            invoker.executeCommand();
        } else if (e.getSource() == verutensilios) {
            Command VerUtensilios = new VerUtensiliosCommand();
            invoker.setCommand(VerUtensilios);
            invoker.executeCommand();
        } else if (e.getSource() == vermultas) {
            Command VerMultas = new VerMultasCommand();
            invoker.setCommand(VerMultas);
            invoker.executeCommand();
        } else if (e.getSource() == pagarmultas) {
            Command PagarMultas = new PagarMultasCommand();
            invoker.setCommand(PagarMultas);
            invoker.executeCommand();
        }

    }

}
