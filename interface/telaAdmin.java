import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class telaAdmin extends JFrame implements ActionListener {

    JButton add_itens, remover_itens, livros_devolvidos, cadastros, buttonconfirmar, buttonCP;
    Dimension botaoDimensao = new Dimension(250, 30);
    JPanel panelAdmin = new JPanel();
    JFrame frameMultaPendente = new JFrame();
    Invoker invoker = new Invoker();

    public int auxteste = 0;
    public int id = 0;
    public int index_user = LoginRegister.index_user;
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

    public telaAdmin() {
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

        panelAdmin.setSize(new Dimension(550, 400));
        panelAdmin.setLayout(new BoxLayout(panelAdmin, BoxLayout.Y_AXIS));
        panelAdmin.add(Box.createRigidArea(new Dimension(400, 400)));
        setSize(400, 400);
        add(panelAdmin);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == add_itens) {
            Command add = new AddItemCommand();
            invoker.setCommand(add);
            invoker.executeCommand();
        } else if (e.getSource() == cadastros) {
            Command cadastro = new VerificarCadastroCommand();
            invoker.setCommand(cadastro);
            invoker.executeCommand();
        } else if (e.getSource() == buttonconfirmar) {
            Command confirmar = new ConfirmarCommand();
            invoker.setCommand(confirmar);
            invoker.executeCommand();
        } else if (e.getSource() == remover_itens) {
            Command remover = new RemoverItemCommand();
            invoker.setCommand(remover);
            invoker.executeCommand();
        } else if (e.getSource() == livros_devolvidos) {
            Command devolvidos = new VerificarDevolvidosCommand();
            invoker.setCommand(devolvidos);
            invoker.executeCommand();
        }
    }

}
