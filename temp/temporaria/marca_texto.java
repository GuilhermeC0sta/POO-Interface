public class marca_texto extends utensilios {

    private String cor;
    private String tamanho;

    public marca_texto(String marca, String cor, String tamanho, int quantidade) {
        super(marca, quantidade);
        this.cor = cor;
        this.tamanho = tamanho;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getCor() {
        return cor;
    }

    public void setTam(String tamanho) {
        this.tamanho = tamanho;
    }

    public String getTam() {
        return tamanho;
    }
}
