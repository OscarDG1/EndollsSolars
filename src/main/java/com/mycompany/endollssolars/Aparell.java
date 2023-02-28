/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.endollssolars;

/**
 *
 * @author Oscar
 */
public class Aparell {

    private String descripcio;
    private int potencia;
    private boolean interruptor;

    public Aparell(String descripcio, int potencia) {
        this.descripcio = descripcio;
        this.potencia = potencia;
        this.interruptor = false;
    }

    public String getDescripcio() {
        return descripcio;
    }

    public void setDescripcio(String descripcio) {
        this.descripcio = descripcio;
    }

    public boolean getInterruptor() {
        return interruptor;
    }

    public void setInterruptor(boolean interruptor) {
        this.interruptor = interruptor;
    }

    public int getPotencia() {
        return potencia;
    }

    public void setPotencia(int potencia) {
        this.potencia = potencia;
    }

}
