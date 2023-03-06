/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ex2.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author joan
 */

@XmlRootElement(name="torneig")
@XmlType(propOrder= {"nom", "lloc", "categoria", "jugadors"})
public class Torneig {
    private String nom;
    private String lloc;
    
    /* La categoria és A, B o C segons el ranking del jugador pitjor classificat
    A: 1-30
    B: 31-100
    C: 101 o més
    */
    private String categoria;
    
    private final List<Jugador> jugadors = new ArrayList<>();
    
    @XmlElement(name="nom")
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    
    @XmlElement(name="lloc")
    public String getLloc() {
        return lloc;
    }

    public void setLloc(String lloc) {
        this.lloc = lloc;
    }
    
    @XmlElement(name="categoria")
    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    
    @XmlElementWrapper(name="jugadors")
    @XmlElement(name="jugador")
    public List<Jugador> getJugadors() {
        return jugadors;
    }

    
}
