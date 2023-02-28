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

    //constructor
    public Casa(String nif, String nom, int superficie) {
        this.nif = nif;
        this.nom = nom;
        this.superficie = superficie;
        this.superficieDisponible = superficie;
        this.interruptor = true;
        this.potenciaMaxima = 0;
    }

    public String getNif() {
        return nif;
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
        

        if (superficiePlaca < superficieDisponible) {
            superficieDisponible = superficieDisponible - superficiePlaca;
            Placa placa = new Placa(superficiePlaca, preu, potencia);
            placas.add(placa);
            potenciaMaxima = potenciaMaxima + potencia;
            return "OK: Placa afegida a la casa.";
        } else {
            return "ERROR: No hi ha espai disponible per a instal·lar aquesta placa";
        }

    }

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
            interruptor = true;
            return "OK: Interruptor general activat.";
        } else {
            return "ERROR: La casa ja té l'interruptor encès.";
        }
    }

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


public String onAparell(String descripcio) {
        if (interruptor == true) {
            int potenciaAparells = 0;
            int potenciaMaxima = 0;
            for (Aparell a : aparells) {
                if (a.getInterruptor()) {
                    potenciaAparells = a.getPotencia() + potenciaAparells;
                }
            }
            for (Placa p : placas) {
                potenciaMaxima = p.getPotencia() + potenciaMaxima;
            }
            for (Aparell a : aparells) {
                if (descripcio.equalsIgnoreCase(a.getDescripcio())) {
                    a.setInterruptor(true);
                    potenciaAparells = a.getPotencia() + potenciaAparells;
                    if (potenciaMaxima >= potenciaAparells) {
                        return "OK: Aparell encès.";
                    } else {
                        interruptor = false;
                        // tenqo que poner los aparells a false? en el caso de que si donde vuelvo
                        // a recorrer los aparatos
                        return "ERROR: Han saltat els ploms. La casa ha quedat completament apagada.";
                    }
                }
            }
            return "ERROR: No hi ha cap aparell registrat amb aquesta descripció a la casa indicada.";
        } else {
            return "ERROR: No es pot encendre l'aparell. L'interruptor general està apagat.";
        }
    }
}
