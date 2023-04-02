public class apoio_livros extends utensilios {

    private int slots;

    public apoio_livros(String marca, int slots, int quantidade) {
        super(marca, quantidade);
        this.slots = slots;
    }

    public int getSlots() {
        return slots;
    }

    public void setSlots(int slots) {
        this.slots = slots;
    }

}