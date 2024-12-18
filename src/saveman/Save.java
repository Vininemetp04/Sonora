package saveman;

import modelos.*;
import dinamic.*;

import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Save {
    public static final String artSv = "svArt.csv";
    public static final String tituloSv = "svTitulo.csv";
    public static final String userSv = "svUser.csv";


    private static void createSaveFile(String file) {
        try{
            File saveFile = new File(file);
            saveFile.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void save(Lista<Modelos> lista, String file) {
        createSaveFile(file);
        try{
            FileWriter sv = new FileWriter(file);
            for (int i = 0 ; i<lista.size(); i++){
                sv.write(lista.get(i).toSave()+"\n");
            }
            sv.close();
        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    public static Lista<Modelos> readSaveArt(){
        Lista<Modelos> artsSv = new Lista<>();
        try {
            File artFilesrc = new File(artSv);
            Scanner artFile = new Scanner(artFilesrc);

            //Atista
            while (artFile.hasNextLine()) {
                String data = artFile.nextLine();
                Artista fromSv = new Artista(data);
                artsSv.add(fromSv);
            }
            artFile.close();
        }catch (FileNotFoundException e) {
            System.out.println("ERRO AO CARREGAR ARTISTAS");
            e.printStackTrace();
        }
        return artsSv;
    }

    public static Lista<Modelos> readSaveUser(){
        Lista<Modelos> usrSv = new Lista<>();
        try {
            File artFilesrc = new File(userSv);
            Scanner userFile = new Scanner(artFilesrc);

            //Atista
            while (userFile.hasNextLine()) {
                String data = userFile.nextLine();
                User fromSv = new User();
                String[] datas = data.split(";");
                fromSv.setNome(datas[0]);
                fromSv.setLogin(datas[1]);
                fromSv.setSENHAsv(datas[2]);
                usrSv.add(fromSv);
            }
            userFile.close();
        }catch (FileNotFoundException e) {
            System.out.println("ERRO AO CARREGAR USUAIOS");
            e.printStackTrace();
        }
        return usrSv;
    }

    public static Lista<Modelos> readSaveTl(Lista<Modelos> artsSv) {
        Lista<Modelos> tlsSv = new Lista<>();

        try{
            File tlFilesrc = new File(tituloSv);
            Scanner tlFile = new Scanner(tlFilesrc);
            //Titulos
            while (tlFile.hasNextLine()) {
                String data = tlFile.nextLine();
                String[] datas = data.split(";");
                Titulo fromSv = new Titulo();
                fromSv.setArtista((Artista) artsSv.get(Integer.parseInt(datas[0])));
                fromSv.setNome(datas[1]);
                fromSv.setAnoLancamento(Integer.parseInt(datas[2]));
                fromSv.setDuracaoMin(Integer.parseInt(datas[3]));
                fromSv.setRank(Double.parseDouble(datas[4]));
                fromSv.setAlbum(datas[5]);
                tlsSv.add(fromSv);
            }

            tlFile.close();
        } catch (FileNotFoundException e) {
            System.out.println("ERRO AO CARREGAR TITULOS");
            e.printStackTrace();
        }
        return tlsSv;
    }

}
