package modelos;

import dinamic.Fila;

public class User implements Modelos{
    private String nome;
    private String login;
    private String senha;
    private final Fila<Titulo> filaReproducao = new Fila<>();

    public void setSenha(String senha){
        this.senha = encrypt(senha);
        System.out.println(this.senha);
    }

    public void addTlFila(Titulo tl){
        this.filaReproducao.enfileirar(tl);
        System.out.println("O titulo: " + tl.getNome() + " foi a dicionado à sua lista de reprodição.");
    }

    public void seeFilaRep(){
        this.filaReproducao.mostrarFila();
    }

    public Titulo getTlAtual(){
        return this.filaReproducao.getPrimeiro();
    }

    public void proximoTitulo(){
        this.filaReproducao.desenfileirar();
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getLog(){
        return this.login;
    }

    public String getNome() {
        return nome;
    }

    public boolean login(String senha){
        return this.senha.equals(encrypt(senha));
    }

    private String encrypt(String sen){
        String saida = "";
        char[] senC = sen.toCharArray();
        int a = 0;
        for (int i = senC.length-1; i >= 0 ; i--){
            a += senC[i];
        }
        saida += (char) a/senC.length;
        return saida;
    }

    @Override
    public String toSave() {
        return (this.nome+";"+this.login+";"+this.senha);
    }

    public void setSENHAsv(String s){
        this.senha = s;
    }
}
