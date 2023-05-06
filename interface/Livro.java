public class Livro extends ItemsBiblioteca {
    private LivroEstado state;
    private String autor;
    private int isbn;
    private String genero;
    private String titulo;
    private int qntd_disp;

    public Livro(String titulo, String autor, int isbn, int qntd_disp, String genero) {
        super(titulo, qntd_disp);
        this.qntd_disp = qntd_disp;
        this.titulo = titulo;
        this.isbn = isbn;
        this.autor = autor;
        this.genero = genero;
        state = new AvailableState();
    }

    @Override
    public String getAutor() {
        return autor;
    }

    @Override
    public int getIsbn() {
        return isbn;
    }

    @Override
    public void setGenero(String genero) {
        this.genero = genero;
    }

    @Override
    public String getGenero() {
        return genero;
    }

    @Override
    public String getTitulo() {
        return titulo;
    }

    @Override
    public void locar() {

        System.out.println("\nVocê conseguiu locar um livro! Devolva dentro de 10 dias.\n");
        System.out.println(" Este livro possui as seguintes especificações: ");
        System.out.println(" Título: " + getTitulo());
        System.out.println(" Autor: " + autor);
        System.out.println(" Isbn: " + isbn);
        System.out.println(" Genero: " + genero);
        System.out.println(" Quantidade disponível: " + getQnt_disp() + "\n");
    }

    private void updateState() {
        if (qntd_disp == 0) {
            state = new OutOfStockState();
        } else {
            state = new AvailableState();
        }
    }

    public void rent() {
        // Verifica se o livro está disponível para aluguel.
        if (state.isAvailable()) {
            qntd_disp--;
            updateState();
        } else {
            throw new IllegalStateException("Livro não disponível para aluguel.");
        }
    }

    public boolean isAvailable() {
        return state.isAvailable();
    }
}
