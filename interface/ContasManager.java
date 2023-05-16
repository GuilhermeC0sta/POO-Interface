import javax.swing.*;
import java.util.regex.*;

public class ContasManager extends JFrame {

    void criarConta(String usuario, String email, String senha, String plano) {
        switch (plano) {
            case "premium":
                LoginRegister.contas.add(new premium(email, senha, usuario, LoginRegister.id));
                break;
            case "comum":
                LoginRegister.contas.add(new comum(email, senha, usuario, LoginRegister.id));
                break;
            case "administrador":
                LoginRegister.contas.add(new admin(email, senha, usuario, LoginRegister.id));
                break;
        }

        LoginRegister.contas.get(LoginRegister.id).defPlano(plano);
        LoginRegister.id += 1;
        JOptionPane.showMessageDialog(null, "Conta " + plano + " registrada com sucesso!");
        dispose();
    }

    public int getIndexConta(String nomeUsuario, String senha) {
        for (int i = 0; i < LoginRegister.contas.size(); i++) {
            if (LoginRegister.contas.get(i).getEmail().equals(nomeUsuario) && LoginRegister.contas.get(i).getSenha().equals(senha)) {
                return i;
            }
        }
        return -1;
    }

    public boolean isEmailValid(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@(gmail|outlook|hotmail|ic\\.ufal\\.br)+\\.com$";
        Pattern pattern = Pattern.compile(regex);
        java.util.regex.Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}