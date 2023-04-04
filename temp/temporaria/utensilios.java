public abstract class utensilios {

    private String marca;
    private int quantidade;

    public utensilios(String marca, int quantidade) {
        this.marca = marca;
        this.quantidade = quantidade;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getCor() {
        return null;
    }

    public void setCor(String cor) {

    }

    public void setTam(String tamanho) {

    }

    public String getTam() {
        return null;
    }

    public void setBrilho(String brilho) {
    }

    public String getBrilho() {
        return null;
    }

    public int getSlots() {
        return 10000000;
    }

}
