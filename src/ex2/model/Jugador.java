/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ex2.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author joan
 */


@XmlType(propOrder = {"nom", "ranking", "edat"})
public class Jugador {
    private String nom;
    private int ranking;
    private int edat;

    
    public Jugador(){
        
    }
    
    public Jugador(String nom, int ranking, int edat) {
        this.nom = nom;
        this.ranking = ranking;
        this.edat = edat;
    }

    @XmlElement(name="nom")
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    
    @XmlElement(name="ranking")
    public int getRanking() {
        return ranking;
    }

    public void setRanking(int ranking) {
        this.ranking = ranking;
    }
    
    @XmlElement(name="edat")
    public int getEdat() {
        return edat;
    }

    public void setEdat(int edat) {
        this.edat = edat;
    }
    
    
}
