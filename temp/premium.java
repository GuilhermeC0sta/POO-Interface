public class premium extends conta {

    public premium(String email, String senha, String nome, int id) {
        super(email, senha, nome, id);
    }

    public String plano, bios;
    public String valormulta = "2 reais";
    public String tempomulta = "30 dias";
    public int limitelivro = 15, biostam = 200;

    @Override
    public void defPlano(String planoesc) {
        this.plano = planoesc;
    }

    @Override
    public String getPlano() {
        return this.plano;
    }

    @Override
    public int getLimlivro() {
        return this.limitelivro;
    }

    @Override
    public void defBios(String bio) {
        this.bios = bio;
    }

    @Override
    public int getBiotam() {
        return this.biostam;
    }
}
