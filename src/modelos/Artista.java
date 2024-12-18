package modelos;

import saveman.Save;

public class Artista implements Modelos{
    private static int c = 0;
    private final int id;
    private final String nome;

    public Artista(String nome) {
        this.nome = nome;
        this.id = c++;
    }

    public int getId(){
        return this.id;
    }

    public String getNome() {
        return this.nome;
    }

    //
    //  Controladores SAVE
    //

    @Override
    public String toSave() {
        return this.nome;
    }
}
