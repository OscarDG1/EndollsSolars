/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.endollssolars;

import java.util.ArrayList;

/**
 *
 * @author Oscar
 */
public class Casa {

    //atributos
    private String nif;
    private String nom;
    private int superficie;
    private int superficieDisponible;
    private boolean interruptor;
    private ArrayList<Placa> placas = new ArrayList<>();
    private ArrayList<Aparell> aparells = new ArrayList<>();
    private int potenciaMaxima;
    private float precioTotal;
    private String aparellsEncesos;

    //constructor
    public Casa(String nif, String nom, int superficie) {
        this.nif = nif;
        this.nom = nom;
        this.superficie = superficie;
        this.superficieDisponible = superficie;
        this.interruptor = true;
        this.potenciaMaxima = 0;
        this.precioTotal = 0;
        this.aparellsEncesos = aparellsEncesos;
    }
// getters y setters//
    public ArrayList<Aparell> getAparells() {
        return aparells;

    }

    public float getPrecioTotal() {
        return precioTotal;
    }

    public String getNif() {
        return nif;
    }

    public ArrayList<Placa> getPlacas() {
        return placas;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getSuperficie() {
        return superficie;
    }

    public void setSuperficie(int superficie) {
        this.superficie = superficie;
    }

    public int getSuperficieDisponible() {
        return superficieDisponible;
    }

    public void setSuperficieDisponible(int superficieDisponible) {
        this.superficieDisponible = superficieDisponible;
    }

     public boolean getInterruptor() {
        return interruptor;
    }

    public void setInterruptor(boolean interruptor) {
        this.interruptor = interruptor;

    }

    public int getPotenciaMaxima() {
        return potenciaMaxima;
    }

    public void setPotenciaMaxima(int potenciaMaxima) {
        this.potenciaMaxima = potenciaMaxima;
    }
    
    
    //metodo para apagar la casa y los aparatos//
    public void offcasa() {
        for (Aparell aparell : aparells) {
            aparell.apagar();
        }
        interruptor = false;
    }
//metodo para calcular la potencia actual//
    public int potenciaTotal() {
        int potenciaTotal = 0;
        for (Placa i : placas) {
            potenciaTotal = potenciaTotal + i.getPotencia();
        }
        return potenciaTotal;
    }
//metodo para ver cuando saltan los plomos//
    public Casa saltarplomos(int potencia) {
        totalConsum();
        potenciaTotal();
        if (totalConsum() > potenciaTotal()) {
            offcasa();
            for (Aparell i : aparells) {
                if (i.getInterruptor() == true) {
                    i.offaparell();
                }
            }
        }
        return null;
    }
//metodo que calcula el consumo total//
    public int totalConsum() {
        int consum = 0;
        if (getInterruptor() == false) {
            return 0;
        }
        for (Aparell aparell : aparells) {
            if (aparell.getInterruptor() == true) {
                consum = consum + aparell.getPotencia();
            }
        }
        return consum;
    }


//metodo para añadir una placa//
    public String addPlaca(int superficiePlaca, float preu, int potencia) {

        if (superficiePlaca <= 0) {
            return "ERROR: Superfície incorrecta. Ha de ser més gran de 0.";
        }
        if (preu <= 0) {
            return "ERROR: Preu incorrecte. Ha de ser més gran de 0.";
        }
        if (potencia <= 0) {
            return "ERROR: Potència incorrecte. Ha de ser més gran de 0.";
        }

        if (superficiePlaca <= superficieDisponible) {
            superficieDisponible = superficieDisponible - superficiePlaca;
            Placa placa = new Placa(superficiePlaca, preu, potencia);
            placas.add(placa);
            potenciaMaxima = potenciaMaxima + potencia;
            precioTotal = precioTotal + preu;
            return "OK: Placa afegida a la casa.";
        } else {
            return "ERROR: No hi ha espai disponible per a instal·lar aquesta placa";
        }

    }
//metodo para añadir un aparell//
    public String addAparell(String descripcio, int potencia) {
        if (potencia <= 0) {
            return "ERROR: Potència incorrecte. Ha de ser més gran de 0.";
        }
        Aparell aparell = new Aparell(descripcio, potencia);
        aparells.add(aparell);
        return "OK: Aparell afegit a la casa.";
    }

    public String onCasa() {
        if (interruptor == false) {
            offcasa();
            interruptor = true;
            return "OK: Interruptor general activat.";
        } else {
            return "ERROR: La casa ja té l'interruptor encès.";
        }
    }
//metodo para apagar un aparell//
    public String offAparell(String descripcio) {
        if (interruptor == true) {
            for (Aparell a : aparells) {
                if (descripcio.equalsIgnoreCase(a.getDescripcio())) {
                    if (a.getInterruptor()) {
                        a.setInterruptor(false);
                        return "OK: Aparell apagat.";
                    } else {
                        return "ERROR: L'aparell ja està apagat.";
                    }
                }
            }
            return "ERROR: No hi ha cap aparell registrat amb aquesta descripció a la casa indicada.";
        } else {

            interruptor = false;
            for (Aparell a : aparells) {
                a.setInterruptor(false);
            }
            return "ERROR: Han saltat els ploms. La casa ha quedat completament apagada.";
        }
    }
//metodo para conseguir los aparells encendidos//
    public String getAparellsEncesos() {
        if (!interruptor) {
            return "";
        }

        String aparellsEncesos = "";
        int potenciaAparells = 0;

        for (Aparell a : aparells) {
            if (a.isInterruptor()) {
                aparellsEncesos += "- " + a.getDescripcio() + "\n";
                potenciaAparells += a.getPotencia();
            }
        }

        if (aparellsEncesos.isEmpty()) {
            return aparellsEncesos;
        } else {
            return "\n" + "Aparells encesos:" + "\n" + aparellsEncesos;
        }
    }
//metodo para encender un aparell//
    public String onAparell(String descripcio) {
        if (interruptor) {
            int potenciaAparells = 0;
            int potenciaMaxima = 0;
            String aparellsEncesos = "";
            int encontrados = 0;

            for (Aparell a : aparells) {
                if (a.getInterruptor()) {
                    potenciaAparells += a.getPotencia();
                }
            }

            for (Placa p : placas) {
                potenciaMaxima += p.getPotencia();
            }

            for (Aparell a : aparells) {
                if (descripcio.equalsIgnoreCase(a.getDescripcio())) {
                    a.setInterruptor(true);
                    potenciaAparells += a.getPotencia();
                    aparellsEncesos += "- " + a.getDescripcio() + "\n";
                    encontrados++;
                }
            }

            if (encontrados == 0) {
                return "ERROR: No hi ha cap aparell registrat amb aquesta descripció a la casa indicada.";
            } else if (potenciaMaxima >= potenciaAparells) {
                return "OK: Aparell encès.";
            } else {
                interruptor = false;
                return "ERROR: Han saltat els ploms. La casa ha quedat completament apagada.";
            }
        } else {
            return "ERROR: No es pot encendre l'aparell. L'interruptor general està apagat.";
        }
    }
}
