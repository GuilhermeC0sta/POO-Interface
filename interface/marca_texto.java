public class marca_texto extends utensilios {

    private String cor;
    private String brilho;
    private int quantidade;

    public marca_texto(String marca, String cor, String brilho, int quantidade) {
        super(marca, quantidade);
        this.cor = cor;
        this.brilho = brilho;
        this.quantidade = quantidade;
    }

    @Override
    public String getCor() {
        return cor;
    }

    @Override
    public String getBrilho() {
        return brilho;
    }

    @Override
    public int getQuantidade() {
        return quantidade;
    }
}
