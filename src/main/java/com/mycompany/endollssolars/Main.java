/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.endollssolars;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 *
 * @author Oscar
 */
public class Main {

    private static ArrayList<Casa> casas = new ArrayList<>();

    public static String addCasa(String nif, String nom, int superficie) {
        for (Casa casa : casas) {
            if (casa.getNif().equals(nif)) {
                return "ERROR: Ja hi ha una casa registrada amb aquest nif";
            } else {
                return "ERROR: No hi ha cap casa registrada amb aquest nif.";
            }
        }
        if (superficie > 10) {
            Casa casa = new Casa(nif, nom, superficie);
            casas.add(casa);
            return "OK: Casa registrada.";
        } else {
            return "ERROR: Superficie incorrecta. Ha de ser més gran de 10.";
        }
    }

    public void info(String nif) {

    }

    public static void list() {
        System.out.println("--- Endolls Solars, S.L. ---");
        System.out.println("Casas enregistrades:" + casas.size());
        for (Casa casa : casas) {
            System.out.println("Client: " + casa.getNif() + " - " + casa.getNom() + "\n" + "Superficie de teulada: " + casa.getSuperficie() + "\n" + "Superfície disponible:" + casa.getSuperficieDisponible() + "\n" + "Interruptor general:"
                    + casa.getInterruptor());

        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader terminal = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            System.out.print("> ");
            String lineaComando = terminal.readLine();
            String[] palabra = lineaComando.split(" ");
            String comando = lineaComando.split(" ")[0];
            switch (comando) {
                case "quit":
                    System.exit(0);
                    break;
                case "addCasa":
                    System.out.println(addCasa(palabra[1], palabra[2], Integer.parseInt(palabra[3])));
                    break;
                case "addPlaca":
                    for (Casa casa : casas) {
                        if (casa.getNif().equals(palabra[1])) {
                            String placas = casa.addPlaca(Integer.parseInt(palabra[2]), Float.parseFloat(palabra[3]), Integer.parseInt(palabra[4]));
                            System.out.println(placas);
                        }
                    }
                    break;
                case "list":
                    list();
                    break;
                default:
                    System.out.println("ERROR: Comanda incorrecta.");
                    break;
            }
        }

    }

}
