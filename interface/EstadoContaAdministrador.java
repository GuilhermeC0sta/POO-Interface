//import LoginRegister.TelaInicial;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.regex.*;
import java.util.ArrayList;


public class EstadoContaAdministrador extends EstadoConta{
    
    public ArrayList<conta> contas;
    LoginRegister dados;

    public EstadoContaAdministrador(LoginRegister.TelaInicial telaInicial, ArrayList<conta> contas, LoginRegister dados){
        super(telaInicial);
        this.dados = dados;
        this.contas = contas;
    }

    @Override
    public void mostrarTela() {
        //LoginRegister teste = new LoginRegister();
        //teste.getContas();

        telaAdmin novatela = new telaAdmin(contas, dados);
        novatela.setVisible(true);
    }

}

