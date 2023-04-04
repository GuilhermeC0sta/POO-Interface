public class postit extends utensilios {

    private String cor, Marca;
    private int quantidade;

    public postit(String marca, String cor, int quantidade) {
        super(marca, quantidade);
        this.cor = cor;
        this.Marca = marca;
        this.quantidade = quantidade;
    }

    @Override
    public String getCor() {
        return cor;
    }

    @Override
    public String getMarca() {
        return Marca;
    }

    @Override
    public int getQuantidade() {
        return quantidade;
    }
    
}
