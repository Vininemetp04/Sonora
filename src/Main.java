import modelos.*;
import dinamic.*;
import saveman.Save;

import java.util.Scanner;

public class  Main {
    static Scanner sc = new Scanner(System.in);
    static Lista<Modelos> listaArtistas = new Lista<>();
    static Lista<Modelos> listaTitulos = new Lista<>();
    static Lista<Modelos> listaUsr = new Lista<>();
    static User user = null;

    public static void main(String[] args) {
        clearScreen();
        logo();
        listaArtistas = Save.readSaveArt();
        listaTitulos = Save.readSaveTl(listaArtistas);
        listaUsr = Save.readSaveUser();
        try {
            Thread.sleep(800);
        } catch (InterruptedException e) { throw new RuntimeException(e); }

        loginPage();
    }

    public static void loginPage(){
        int a = -1;
        while (a != 0){
            clearScreen();
            System.out.print("""
                    =-=-=-=-=-=-=-=| Login | =-=-=-=-=-=-=-=
                      [ 1 ] Login
                      [ 2 ] Cadastrar
                      [ 0 ] Fechar Programa \n
                    O que deseja fazer?\s""");

            a = sc.nextInt();
            sc.nextLine();

            switch (a){
                case 1:
                    loginUsr();
                    break;
                case 2:
                    cadUsr();
                    break;
                case 0:
                    exitApp();
                default:
                    System.out.println("ENTRADA INVALIDA");
            }
        }
    }

    public static void appMain(){

        int escolha = -1;
        while (escolha != 0){
            mainMenu();
            escolha = sc.nextInt();
            sc.nextLine();
            swApp(escolha);
        }
    }

    public static void logo(){
        System.out.println("""
                ░░      ░░░░      ░░░   ░░░  ░░░      ░░░       ░░░░      ░░
                ▒  ▒▒▒▒▒▒▒▒  ▒▒▒▒  ▒▒    ▒▒  ▒▒  ▒▒▒▒  ▒▒  ▒▒▒▒  ▒▒  ▒▒▒▒  ▒
                ▓▓      ▓▓▓  ▓▓▓▓  ▓▓  ▓  ▓  ▓▓  ▓▓▓▓  ▓▓       ▓▓▓  ▓▓▓▓  ▓
                ███████  ██  ████  ██  ██    ██  ████  ██  ███  ███        █
                ██      ████      ███  ███   ███      ███  ████  ██  ████  █
                """);
    }

    public static void mainMenu(){
        clearScreen();
        System.out.print("""
                =-=-=-=-=-=-=-=| Menu | =-=-=-=-=-=-=-=
                  [ 1 ] Adicionar música
                  [ 2 ] Adicionar artista
                  [ 3 ] Mostrar musicas cadastradas
                  [ 4 ] Mostrar artistas cadastrados
                  [ 5 ] Mostrar lista de reprodução
                  [ 6 ] Proxima musica da lista de reprodução
                  [ 7 ] Adicionar musica a lista de reprodução
                  [ 8 ] Avaliar musica
                  [ 0 ] Fechar Programa \n
                O que deseja fazer?\s""");
    }

    public static void swApp(int ope){
        clearScreen();
        switch (ope){
            case 1:
                addTl();
                break;
            case 2:
                addArt();
                break;
            case 3:
                listTl();
                break;
            case 4:
                listArt();
                break;
            case 5:
                Titulo temp = user.getTlAtual();

                if (temp == null){
                    System.out.println("Não há musicas na sua lista de reprodução!!!");
                    break;
                }
                user.seeFilaRep();
                break;
            case 6:
                user.proximoTitulo();
                break;
            case 7:
                addTlLISTA();
                break;
            case 8:
                avaliaTl();
                break;
            case 0:
                exitApp();
            default:
                System.out.println("Entrada invalida!!!");
        }
        System.out.println("Pressione \"Enter\" para voltar ao menu inicial");
        sc.nextLine();
    }

    private static void avaliaTl() {
        Titulo toup = user.getTlAtual();

        if (toup == null){
            System.out.println("Não há musicas na sua lista de reprodução!!!");
            return;
        }

        System.out.print("Sua avaliação (0 - 5): ");
        toup.setRank(sc.nextInt());
        sc.nextLine();
    }

    private static void addTlLISTA() {
        clearScreen();
        if (listaTitulos.isEmpty()){
            System.out.println("Nenhum titulo foi carregado");
            return;
        }
        listaTitulos.showList();
        System.out.print("Qual o numero da musica que deseja adicionar à lista: ");
        int a = sc.nextInt();
        sc.nextLine();
        user.addTlFila((Titulo) listaTitulos.get(a));
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void exitApp() {
        System.out.println("Salvando Dados");
        Save.save(listaArtistas, Save.artSv);
        Save.save(listaTitulos, Save.tituloSv);
        Save.save(listaUsr, Save.userSv);
        sc.close();
        System.out.println("Fechando o Sonora");
        System.exit(0);
    }

    public static void addTl(){
        if (listaArtistas.isEmpty()){
            System.out.println("Nenhum artista foi carregado");
            return;
        }
        Titulo novoTl = new Titulo();

        System.out.print("Nome da Música: ");
        novoTl.setNome(sc.nextLine());

        System.out.print("Album da Música: ");
        novoTl.setNome(sc.nextLine());

        System.out.print("Ano de lançamento: ");
        novoTl.setAnoLancamento(sc.nextInt());
        sc.nextLine();

        System.out.print("Duração (minutos): ");
        novoTl.setDuracaoMin(sc.nextInt());
        sc.nextLine();

        System.out.print("Id do artista: ");
        novoTl.setArtista((Artista) listaArtistas.get(sc.nextInt()));
        sc.nextLine();
        listaTitulos.add(novoTl);
    }

    public static void addArt(){
        System.out.print("Nome do artista: ");
        String nome = sc.nextLine();
        Artista novoArt = new Artista(nome);
        System.out.println("O id de "+nome+" é: " + novoArt.getId());
        listaArtistas.add(novoArt);
    }

    public static void listTl(){
        for (int i = 0 ; i<listaTitulos.size(); i++){
            System.out.println("=-=-=-=-=-=-=-=-=-=");
            System.out.println(listaTitulos.get(i));
        }
    }

    public static void listArt(){
        for (int i = 0 ; i<listaArtistas.size(); i++){
            Artista temp = (Artista) listaArtistas.get(i);
            System.out.println(temp.getId() + "º: " + temp.getNome());
        }
    }

    private static void loginUsr() {
        clearScreen();
        System.out.print("Email: ");
        String email = sc.nextLine();

        User temp = new User();

        for (int i = 0; i < listaUsr.size(); i++){
            User perc = (User) listaUsr.get(i);
            if (perc.getLog().equals(email)){
                temp = perc;
            }
        }

        if (temp.getNome() != null){
            System.out.println("Olá " + temp.getNome());
        } else {
            System.out.println("Usuario não cadastrado!!!");
            return;
        }

        System.out.print("Senha: ");
        String senha = sc.nextLine();

        if (temp.login(senha)){
            user = temp;
            appMain();
        } else {
            System.out.println("Senha incorreta");
        }
    }

    public static void cadUsr(){
        clearScreen();
        User newUsr = new User();

        System.out.print("Seu nome: ");
        newUsr.setNome(sc.nextLine());

        System.out.print("Seu e-mail: ");
        newUsr.setLogin(sc.nextLine());

        String senha = " ";
        String senhaConf = ".";
        while (!senha.equals(senhaConf)){
            System.out.print("Senha: ");
            senha = sc.nextLine();
            System.out.print("Confirmar a senha: ");
            senhaConf = sc.nextLine();
        }
        newUsr.setSenha(senha);

        listaUsr.add(newUsr);
    }
}