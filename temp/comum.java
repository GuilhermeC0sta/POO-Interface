public class comum extends conta {
    public comum(String email, String senha, String nome, int id) {
        super(email, senha, nome, id);
    }

    public String plano, bios;
    public String valormulta = "10 reais";
    public String tempomulta = "10 dias";
    public int limitelivro = 1, biostam = 50;

    @Override
    public void defPlano(String planoesc) {
        this.plano = planoesc;
    }

    @Override
    public void defBios(String bio) {
        this.bios = bio;
    }

    @Override
    public int getBiotam() {
        return this.biostam;
    }

    @Override
    public String getPlano() {
        return this.plano;
    }

    @Override
    public int getLimlivro() {
        return this.limitelivro;
    }

}
