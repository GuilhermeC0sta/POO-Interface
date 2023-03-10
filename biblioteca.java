import java.util.*;

public class biblioteca { // ADICIONAR ou REMOVER livros do acervo;
    public ArrayList<conta> contas = new ArrayList<>();
    private ArrayList<livro> livros = new ArrayList<>();

    private ArrayList<Integer> id_user = new ArrayList<>();
    private ArrayList<Integer> isbn_locado = new ArrayList<>();

    private ArrayList<Integer> id_devolvido = new ArrayList<>();
    private ArrayList<Integer> isbn_devolvido = new ArrayList<>();
    private ArrayList<Integer> id_multa = new ArrayList<>();
    private ArrayList<Integer> id_multapaga = new ArrayList<>();

    public String nome, email, senha, titulo, autor, controle, dev, nome_b, email_b, senha_b, log = "n", plan, BIO;

    private boolean logado;

    int k = 0, index_user = 0, op = 0, id = 1, isbn, qnt_disp, isbnlocar, isbndevolver, j, i;

    Scanner sc = new Scanner(System.in);

    public biblioteca() {
        this.contas = new ArrayList<>();
        this.livros = new ArrayList<>();
        this.id_user = new ArrayList<>();
        this.isbn_locado = new ArrayList<>();
        this.id_devolvido = new ArrayList<>();
        this.isbn_devolvido = new ArrayList<>();
        this.id_multa = new ArrayList<>();
        this.id_multapaga = new ArrayList<>();
        this.sc = new Scanner(System.in);

        nome_b = "biblioteca";
        email_b = "biblioteca123@gmail.com";
        senha_b = "admin";
        contas.add(new admin(email_b, senha_b, nome_b, 0));
        contas.get(0).defPlano("Administrador");
    }

    public String login() {
        System.out.println("Digite o email:");
        email = sc.nextLine();
        System.out.println("Digite a senha:");
        senha = sc.nextLine();
        for (i = 0; i < contas.size(); i++) {
            if (contas.get(i).getEmail().equals(email) && contas.get(i).getSenha().equals(senha)) {
                index_user = i;
                System.out.println("Login realizado com sucesso!");
                return "logado";
            }
            else{
                if(i == contas.size()-1){
                    return "n logado";
                }
            }
        }

        System.out.println("Essa conta n??o existe ou voc?? errou o email/senha");
        return "erro";
    }

    public void cadastrar_usuario() {

        System.out.println("Digite seu plano:");
        plan = sc.nextLine();

        if(plan.equalsIgnoreCase("comum")){
            System.out.println("Digite seu nome:");
            nome = sc.nextLine();

            System.out.println("Digite o email:");
            email = sc.nextLine();

            System.out.println("Digite a senha:");
            senha = sc.nextLine();

            contas.get(index_user).defPlano(plan);
        
            System.out.println("Usu??rio " + nome + " cadastrado!");

            contas.add(new comum(email, senha, nome, id));
            id += 1;
        }
        else if(plan.equalsIgnoreCase("premium")){
            System.out.println("Digite seu nome:");
            nome = sc.nextLine();

            System.out.println("Digite o email:");
            email = sc.nextLine();

            System.out.println("Digite a senha:");
            senha = sc.nextLine();

            contas.get(index_user).defPlano(plan);
            
            System.out.println("Usu??rio " + nome + " cadastrado!");

            contas.add(new premium(email, senha, nome, id));
            id += 1;
        }
        else{
            System.out.println("Plano inv??lido! Digite novamente.");
        }
    }

    public void editar_perfil() {
        if(contas.get(index_user).getPlano().equalsIgnoreCase("comum")){
            System.out.println("Digite o novo nome:");
            sc.nextLine();
            nome = sc.nextLine();
            System.out.println("Digite o novo email:");
            email = sc.nextLine();
            System.out.println("Digite uma BIO para voc?? com at?? 50 caracteres (caso n??o desejar digite 00):");
            BIO = sc.nextLine();
            if(BIO.length() > 50){
                System.out.println("Voc?? excedeu o n??mero de caracteres");
            }
            else if(BIO == "00"){
            }
            else{
                contas.get(index_user).defBios(BIO);
            }
            contas.set(index_user, new conta(email, contas.get(index_user).getSenha(), nome, id));
            System.out.println("Nome e email alterados com sucesso!");
        }
        else if(contas.get(index_user).getPlano().equalsIgnoreCase("premium")){
            System.out.println("Digite o novo nome:");
            sc.nextLine();
            nome = sc.nextLine();
            System.out.println("Digite o novo email:");
            email = sc.nextLine();
            System.out.println("Digite uma BIO para voc?? com at?? 200 caracteres (caso n??o desejar digite 00):");
            BIO = sc.nextLine();
            if(BIO.length() > 200){
                System.out.println("Voc?? excedeu o n??mero de caracteres");
            }
            else if(BIO == "00"){
            }
            else{
                contas.get(index_user).defBios(BIO);
            }
            contas.set(index_user, new conta(email, contas.get(index_user).getSenha(), nome, id));
            System.out.println("Nome e email alterados com sucesso!");
        }
        else if(contas.get(index_user).getPlano().equalsIgnoreCase("administrador")){
            System.out.println("Digite o novo nome:");
            sc.nextLine();
            nome = sc.nextLine();
            System.out.println("Digite o novo email:");
            email = sc.nextLine();
            contas.set(index_user, new conta(email, contas.get(index_user).getSenha(), nome, id));
            System.out.println("Nome e email alterados com sucesso!");
        }
        else{
            System.out.println("Plano Inv??lido, digite novamente!");
        }
    }

    public void logout() {
        if (logado) {
            logado = false;
            System.out.println("Logout realizado com sucesso!");
        } else {
            System.out.println("Nenhum usu??rio logado!");
        }
    }

    public void mostrar_livros() {
        String tester = contas.get(0).getPlano();
        System.out.println(tester);
        int tester2 = contas.get(0).getLimlivro();
        System.out.println(tester2);
        for (int i = 0; i < livros.size(); i++) {
            System.out.println(
                    "Titulo: " + livros.get(i).titulo + " - " + "Autor: " + livros.get(i).autor
                            + " - "
                            + " C??digo ISBN: " + livros.get(i).isbn + " - "
                            + " Quantidade dispon??vel: "
                            + livros.get(i).qnt_disp);
        }
    }

    public void listar_usuarios() {
        if (index_user == 0) {
            for (int i = 0; i < contas.size(); i++) {
                System.out.println("Nome: " + contas.get(i).getNome() + " - " + "Email: " + contas.get(i).getEmail());
            }
        } else {
            System.out.println("Somente adminstradores podem usar esta fun????o");
        }
    }

    public void add_livro() {
        if (index_user == 0) {
            System.out.println("Digite o titulo:");
            sc.nextLine();
            titulo = sc.nextLine();

            System.out.println("Digite o autor:");

            autor = sc.nextLine();

            System.out.println("Digite o codigo ISBN:");
            isbn = sc.nextInt();

            System.out.println("Digite a quantidade dispon??veis:");
            qnt_disp = sc.nextInt();

            livros.add(new livro(titulo, autor, isbn, qnt_disp));
        } else {
            System.out.println("Somente adminstradores podem usar esta fun????o");
        }
    }

    public void locar_livro() {
        System.out.println("Digite o c??digo ISBN do livro que deseja locar: ");
        isbnlocar = sc.nextInt();
        for (k = 0; k < livros.size(); k++) {
            if (isbnlocar == livros.get(k).isbn) {
                if (livros.get(k).qnt_disp == 0) {
                    System.out.println("Livro indispon??vel, tente novamente em outro momento.");
                    break;
                } else {
                    livros.set(k, new livro(livros.get(k).titulo, livros.get(k).autor, isbnlocar,
                            livros.get(k).qnt_disp - 1));
                    System.out.println("\nVoc?? conseguiu locar um livro! Devolva dentro de 10 dias.\n");
                    id_user.add(index_user);
                    isbn_locado.add(isbnlocar);
                    break;
                }
            }
        }
    }

    public void remover_livro() {
        if (index_user == 0) {
            System.out.println("Digite o c??digo ISBN do livro que deseja remover do acervo: ");
            isbnlocar = sc.nextInt();
            for (k = 0; k < livros.size(); k++) {
                if (isbnlocar == livros.get(k).isbn) {
                    livros.remove(k);
                }
            }
        } else {
            System.out.println("Somente adminstradores podem usar esta fun????o");
        }
    }

    public void devolver_livro() {
        if (id_user.size() == 0) {
            System.out.println("*N??O H?? LIVRO LOCADOS*");
            // break;
        } else {
            System.out.println("Livros locados: ");
        }
        for (k = 0; k < id_user.size(); k++) {
            if (id_user.get(k) == index_user) {
                System.out.println(isbn_locado.get(k));
            }
        }
        System.out.println("Escolha o livro que deseja devolver de acordo com o ISBN: ");
        isbndevolver = sc.nextInt();
        for (j = 0; j < id_user.size(); j++) {
            if (id_user.get(j) == index_user) {
                if (isbn_locado.get(j) == isbndevolver) {
                    id_devolvido.add(j, index_user);
                    isbn_devolvido.add(j, isbndevolver);
                    id_user.remove(j);
                    isbn_locado.remove(j);
                    System.out.println("Livro " + isbndevolver + " devolvido! A Libraric agradece!\n");
                    for (k = 0; k < livros.size(); k++) {
                        if (isbndevolver == livros.get(k).isbn) {
                            livros.set(k,
                                    new livro(livros.get(k).titulo, livros.get(k).autor, isbndevolver,
                                            livros.get(k).qnt_disp + 1));
                            break;
                        }
                    }
                    break;
                }
            }
            if (j == id_user.size() - 1) {
                System.out.println("Este livro n??o foi locado por voc??.");
            }
        }
    }

    public void livros_locados() {
        if (id_user.size() == 0) {
            System.out.println("*N??O H?? LIVRO LOCADOS*");
            // break;
        } else {
            System.out.println("Livros locados: ");
        }
        for (k = 0; k < id_user.size(); k++) {
            if (id_user.get(k) == index_user) {
                System.out.println(isbn_locado.get(k));
            }
        }
    }

    public void livros_devolvidos() {
        if (index_user == 0) {
            for (j = 0; j < id_devolvido.size(); j++) {
                System.out.println("O usuario de id " + id_devolvido.get(j) + " devolveu o livro "
                        + isbn_devolvido.get(j));
                System.out.println("Se este livro foi devolvido digite S, caso contrario, digite N");
                sc.nextLine();
                dev = sc.nextLine();
                if (dev.equalsIgnoreCase("S")) {
                    id_devolvido.remove(j);
                    isbn_devolvido.remove(j);
                } else if (dev.equalsIgnoreCase("N")) {
                    System.out.println(
                            "Aplicar multa? digite S para aplicar multa caso tenha passado o prazo, digite N, caso nao tenha passado o prazo");
                    String multinha = sc.nextLine();
                    if (multinha.equalsIgnoreCase("S")) {
                        System.out.println("MULTA APLICADA!");
                        id_multa.add(id_devolvido.get(j));
                    } else if (multinha.equalsIgnoreCase("N")) {
                        System.out.println("MULTA N??O APLICADA!");
                    }
                }
            }
        } else {
            System.out.println("Somente adminstradores podem usar esta fun????o");
        }
    }

    public void verificar_multas() {
        for (j = 0; j < id_multa.size(); j++) {
            if (id_multa.get(j) == index_user) {
                System.out.println(
                        "Voc?? possui uma multa n??o paga! Realize o pagamento dentro do prazo ou ent??o ser?? aplicado juros ?? multa!");
                System.out.println(
                        "Deseja pag??-la agora? se SIM digite S, caso contr??rio, digite outra N");
                sc.nextLine();
                String resp = sc.nextLine();
                if (resp.equalsIgnoreCase("S")) {
                    System.out.println("Dirija-se para o caixa para realizar o pagamento!");
                    id_multapaga.add(index_user);
                } else if (resp.equalsIgnoreCase("N")) {
                    System.out.println(
                            "Realize o pagamento dentro do prazo ou ent??o ser?? aplicado juros ?? multa!");
                    break;
                }
            }
        }
    }

    public void multas_pagas() {
        if (index_user == 0) {
            for (j = 0; j < id_multapaga.size(); j++) {
                System.out.println(
                        "O usu??rio de id " + id_multapaga.get(j) + " pagou a multa, confirma?");
                sc.nextLine();
                String resposta = sc.nextLine();
                if (resposta.equalsIgnoreCase("S")) {
                    System.out.println("Confirmado que o usu??rio de id " + id_multapaga.get(j)
                            + " pagou a multa!");
                    id_multapaga.remove(j);
                    id_devolvido.remove(j);
                    id_multa.remove(j);
                }
            }
        } else {
            System.out.println("Somente adminstradores podem usar esta fun????o");
        }
    }

}
