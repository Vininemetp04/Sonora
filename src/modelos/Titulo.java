package modelos;

import saveman.Save;

public class Titulo implements Modelos {
    protected Artista artista;
    protected String nome;
    protected String album;
    protected int anoLancamento;
    protected int duracaoMin;
    protected double rank;

    //
    // Constructors
    //

    public Titulo() { super(); }

    public Titulo(Artista artista, String nome, int anoLancamento, int duracaoMin, double rank) {
        this.artista = artista;
        this.nome = nome;
        this.anoLancamento = anoLancamento;
        this.duracaoMin = duracaoMin;
        this.rank = rank;
    }

    //
    //    Getters & Setters
    //

    public Artista getArtista() {
        return artista;
    }

    public void setArtista(Artista artista) {
        this.artista = artista;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public int getAnoLancamento() {
        return anoLancamento;
    }

    public void setAnoLancamento(int anoLancamento) {
        this.anoLancamento = anoLancamento;
    }

    public int getDuracaoMin() {
        return duracaoMin;
    }

    public void setDuracaoMin(int duracaoMin) {
        this.duracaoMin = duracaoMin;
    }

    public double getRank() {
        return rank;
    }

    public void setRank(double rank) {
        if (this.rank == 0) {
            this.rank = rank;
            return;
        }
        this.rank = (this.rank + rank)/2;
    }


    //
    // #OVERRRIDEs
    //

    @Override
    public String toString() {
        String saida;
        saida = "Nome: " + this.nome + " | Lançamento: " + this.anoLancamento + " | Album: " + this.album;
        saida += "\nDuração: " + this.duracaoMin + "min | Por: " + this.artista.getNome()+ " | Ranking: " + this.rank;
        return saida;
    }

    //
    //  SAVE CTRL
    //
    
    @Override
    public String toSave(){
        String res = "";
        res += this.artista.getId();
        res += ";"+ this.nome + ";";
        res += this.anoLancamento + ";";
        res += this.duracaoMin + ";";
        res += this.rank + ";";
        res += this.album;
        return res;
    }

}
