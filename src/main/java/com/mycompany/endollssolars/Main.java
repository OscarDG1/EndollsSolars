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

    static ArrayList<Casa> casas = new ArrayList<>();

    public static String addCasa(String nif, String nom, int superficie) {
        for (Casa casa : casas) {
            if (casa.getNif().equals(nif)) {
                return "ERROR: Ja hi ha una casa registrada amb aquest nif";
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

    public static String info(String nif) throws Exception {
        for (Casa casa : casas) {
            if (casa.getNif().equals(nif)) {
                System.out.println("Client: " + casa.getNif() + " - " + casa.getNom()
                        + "\n" + "Plaques solars instal·lades: " + casa.getPlacas().size()
                        + "\n" + "Potència total: " + casa.getPotenciaMaxima() + "W"
                        + "\n" + "Inversió total: " + casa.getPrecioTotal() + "€"
                        + "\n" + "Aparells registrats:" + casa.getAparells().size()
                        + "\n" + "Consum actual:" + casa.totalConsum() + "W"
                        + casa.getAparellsEncesos());
                return "";
            }
        }

        throw new Exception("ERROR: No hi ha cap casa registrada amb aquest nif.");
    }

    public static void list() {
        if (casas.isEmpty()) {
            System.out.println("No hi ha cases registrades.");
        } else {
            System.out.println("--- Endolls Solars, S.L. ---");
            System.out.println("Casas enregistrades: " + casas.size());
            int i = 1;
            for (Casa casa : casas) {
                System.out.println("Casa " + i);
                System.out.println("Client: " + casa.getNif() + " - " + casa.getNom());
                System.out.println("Superficie de teulada: " + casa.getSuperficie());
                System.out.println("Superfície disponible: " + casa.getSuperficieDisponible());
                if (casa.getInterruptor() == true) {
                    System.out.println("Interruptor general: encès");
                } else {
                    System.out.println("Interruptor general: apagat");
                }
                if (casa.getPlacas().isEmpty()) {
                    System.out.println("No té plaques solars instal·lades.");
                } else {
                    System.out.println("Plaques solars instal·lades: " + casa.getPlacas().size());
                }
                if (casa.getAparells().isEmpty()) {
                    System.out.println("No té cap aparell elèctric registrat.");
                } else {
                    System.out.println("Aparells registrats: " + casa.getAparells().size());
                }
                System.out.println();
                i++;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader terminal = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            System.out.print("> ");
            String lineaComando = terminal.readLine();
            String[] palabra = lineaComando.split(" ");
            String comando = lineaComando.split(" ")[0].toLowerCase();

            switch (comando) {
                case "addcasa":
                    if (palabra.length != 4) {
                        System.out.println("ERROR: Número de paràmetres incorrecte." + "\n" + "Ús: addCasa [nif] [nom] [superficie]");
                    } else {
                        System.out.println(addCasa(palabra[1], palabra[2], Integer.parseInt(palabra[3])));
                    }
                    break;
                case "addplaca":
                    if (palabra.length != 5) {
                        System.out.println("ERROR: Número de paràmetres incorrecte." + "\n" + "Ús: addPlaca [nif] [superficie] [preu] [potència]");
                    } else {
                        boolean encontrado = false;
                        for (Casa casa : casas) {
                            if (casa.getNif().equals(palabra[1])) {
                                encontrado = true;
                                String placas = casa.addPlaca(Integer.parseInt(palabra[2]), Float.parseFloat(palabra[3]), Integer.parseInt(palabra[4]));
                                System.out.println(placas);
                                encontrado = true;
                            }
                        }
                        if (!encontrado) {
                            System.out.println("ERROR: No hi ha cap casa registrada amb aquest nif.");
                        }
                    }
                    break;

                case "addaparell":
                    if (palabra.length != 4) {
                        System.out.println("ERROR: Número de paràmetres incorrecte." + "\n" + "Ús: addAparell [nif] [descripció] [potència]");
                    } else {
                        boolean casaEncontrada = false;
                        for (Casa casa : casas) {
                            if (casa.getNif().equals(palabra[1])) {
                                casaEncontrada = true;
                                boolean encontrado = false;
                                for (Aparell a : casa.getAparells()) {
                                    if (a.getDescripcio().equalsIgnoreCase(palabra[2])) {
                                        System.out.println("ERROR: Ja existeix un aparell amb aquesta descripció a la casa indicada.");
                                        encontrado = true;
                                        break;
                                    }
                                }
                                if (!encontrado) {
                                    int potencia = Integer.parseInt(palabra[3]);
                                    if (potencia <= 0) {
                                        System.out.println("ERROR: Potència incorrecte. Ha de ser més gran de 0.");
                                    } else {
                                        String aparells = casa.addAparell(palabra[2], potencia);
                                        System.out.println("OK: Aparell afegit a la casa.");
                                        casa.saltarplomos(potencia);
                                    }
                                }
                                break;
                            }
                        }
                        if (!casaEncontrada) {
                            System.out.println("ERROR: No hi ha cap casa registrada amb aquest nif.");
                        }
                    }
                    break;

                case "oncasa":
                    if (palabra.length != 2) {
                        System.out.println("ERROR: Número de paràmetres incorrecte." + "\n"
                                + "Ús: onCasa [nif]");
                    } else {
                        boolean encontrado = false;
                        for (Casa casa : casas) {
                            if (casa.getNif().equals(palabra[1])) {
                                String encendida = casa.onCasa();
                                System.out.println(encendida);
                                encontrado = true;
                            }
                        }
                        if (!encontrado) {
                            System.out.println("ERROR: No hi ha cap casa registrada amb aquest nif.");
                        }
                    }
                    break;

                case "onaparell":
                    if (palabra.length != 3) {
                        System.out.println("ERROR: Número de paràmetres incorrecte" + "\n"
                                + "Ús: onAparell [nif] [descripció aparell]");

                    } else {
                        boolean encontrado = false;
                        for (Casa casa : casas) {
                            if (casa.getNif().equals(palabra[1])) {
                                String aparellEncendido = casa.onAparell(palabra[2]);
                                System.out.println(aparellEncendido);
                                casa.saltarplomos(0);
                                encontrado = true;
                                break;
                            }
                        }

                        if (!encontrado) {
                            System.out.println("ERROR: No hi ha cap casa registrada amb aquest nif.");
                        }
                    }
                    break;

                case "offaparell":
                    if (palabra.length != 3) {
                        System.out.println("ERROR: Número de paràmetres incorrecte" + "\n"
                                + "Ús: offAparell [nif] [descripció aparell]");
                    } else {
                        boolean encontrado = false;
                        for (Casa casa : casas) {
                            if (casa.getNif().equals(palabra[1])) {

                                String aparellApagado = casa.offAparell(palabra[2]);
                                System.out.println(aparellApagado);
                                encontrado = true;
                            }
                        }

                        if (!encontrado) {
                            System.out.println("ERROR: No hi ha cap casa registrada amb aquest nif.");
                        }

                    }
                    break;

                case "list":
                    if (palabra.length != 1) {
                        System.out.println("ERROR: Número de paràmetres incorrecte" + "\n"
                                + "Ús: list");
                    } else {
                        list();
                    }
                    break;
                case "info":
                    if (palabra.length != 2) {
                        System.out.println("ERROR: Número de paràmetres incorrecte" + "\n"
                                + "Ús: info [nif]");
                    } else {
                        try {
                            String infoo = info(palabra[1]);
                            System.out.println(infoo);
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    break;
                case "quit":
                    if (palabra.length == 1) {
                        System.exit(0);
                    } else {
                        System.out.println("ERROR: Número de parámetros incorrecto. Ús: quit");
                    }
                    break;

                default:
                    System.out.println("ERROR: Comanda incorrecta.");
                    break;
            }
        }

    }

}
