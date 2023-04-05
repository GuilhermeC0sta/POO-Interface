public class apoio_livros extends utensilios {

    private int slots;
    private String Marca;
    private int quantidade;

    public apoio_livros(String marca, int slots, int quantidade) {
        super(marca, quantidade);
        this.slots = slots;
        this.Marca = marca;
        this.quantidade = quantidade;
    }
    
    @Override
    public int getSlots() {
        return slots;
    }

    @Override
    public String getMarca() {
        return this.Marca;
    }

    @Override
    public int getQuantidade() {
        return quantidade;
    }

}
