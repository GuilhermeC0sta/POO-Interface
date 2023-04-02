public class postit extends utensilios {

    private String cor;

    public postit(String marca, String cor, int quantidade) {
        super(marca, quantidade);
        this.cor = cor;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }
}
