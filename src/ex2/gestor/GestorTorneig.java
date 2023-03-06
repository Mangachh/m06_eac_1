/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ex2.gestor;

import ex2.model.Torneig;
import ex2.model.Jugador;

import java.io.File;
import java.util.Comparator;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 * Aquesta classe gestiona un torneig les dades del qual estan emmagatzemades en
 * un fitxer XML, i escriu l'actualització també en un fitxer XML
 *
 * @author joan
 */
public class GestorTorneig {    

    /**
     * Llegeix el fitxer XML, hi afegeix un jugador, i escriu el resultat en un
     * altre fitxer
     *
     * @param infoJugador Les dades del nou jugador en format "Nom;Ranking;Edat"
     * @param origen El nom del fitxer XML origen
     * @param desti El nom del fitxer XML destí
     * @throws ex2.gestor.GestorTorneigException
     */
    public void afegirJugador(String infoJugador, String origen, String desti) throws GestorTorneigException {
        String[] data = infoJugador.split(";");
               
        try {
            final String nom = data[0];   
            final int ranking = Integer.parseInt(data[1]);
            final int edat = Integer.parseInt(data[2]);
            Torneig torneig = this.loadTorneig(origen);
            torneig.getJugadors().add(new Jugador(nom, ranking, edat));
            torneig.setCategoria(this.returnCategoryByRanking(ranking));
            
            // save
            this.saveTorneig(torneig, desti);
        } catch (NumberFormatException | IndexOutOfBoundsException| JAXBException e) {
            throw new GestorTorneigException(e.getMessage(), e.getCause());
        }
    }
    
    /**
     * Retorna la categoria del torneing en base al ranking que 
     * es passa.
     * A = ranking > 0 <= 30
     * B = ranking > 30 <= 100
     * C = la resta
     * @param ranking
     * @return A ó B ó C
     */
    private String returnCategoryByRanking(final int ranking){
        final int rankMaxA = 30;
        final int rankMaxB = 100;
        
        if(ranking > rankMaxB){
            return "C";
        }else if(ranking > rankMaxA){
            return "B";
        }else{
            return "A";
        }
    }

    /**
     * Obté la categoria del torneig del fitxer XML
     *
     * @param fitxer El nom del fitxer
     * @return La categoria del torneig
     * @throws ex2.gestor.GestorTorneigException
     */
    public String getCategoria(String fitxer) throws GestorTorneigException {        
        Torneig t;
        // TODO: no me acaba de gustar, pero bueno
        try {
            t = this.loadTorneig(fitxer);
            return t.getCategoria();
        } catch (JAXBException | NullPointerException e) {
            throw new GestorTorneigException(e.getMessage(), e.getCause());
        }
    }

    /**
     * Obté del fitxer XML les dades del jugador amb millor ranking
     * El millor ranking és aquell de menor número.
     * 
     * @param fitxer El nom del fitxer
     * @return Les dades del jugador en format "Nom;Ranking;Edat"
     * @throws ex2.gestor.GestorTorneigException
     */
    public String getMillorJugador(String fitxer) throws GestorTorneigException { 
        final String format = "%s;%s;%s";
        
        try {
            List<Jugador> jugadors = this.loadTorneig(fitxer).getJugadors();
            Comparator<Jugador> comparator = Comparator.comparing(Jugador::getRanking);
            Jugador best = jugadors.stream().min(comparator).get();
            return String.format(format, best.getNom(), best.getRanking(), best.getEdat());
        } catch (JAXBException | NullPointerException ex) {
            throw new GestorTorneigException(ex.getMessage(), ex.getCause());
        }
    }
    
    /**
     * Carrega un {@link Torneig} a partir de la ruta
     * @param path -> ruta del fitxer
     * @return -> torneig
     * @throws JAXBException 
     */
    private Torneig loadTorneig(final String path) throws JAXBException{
        File file = new File(path);
        JAXBContext jaxb = JAXBContext.newInstance(Torneig.class);
        Unmarshaller unmarshaller = jaxb.createUnmarshaller();
        return (Torneig)unmarshaller.unmarshal(file);
    }
       
    /**
     * Guarda un {@link Torneig} a la ruta de destí
     * @param torneig -> torneing a guardar
     * @param destination -> destí
     * @throws JAXBException 
     */
    private void saveTorneig(final Torneig torneig, final String destination) throws JAXBException{
        File file = new File(destination);
        JAXBContext jaxb = JAXBContext.newInstance(Torneig.class);
        Marshaller marshaller = jaxb.createMarshaller();
        marshaller.marshal(torneig, file);
    }



}
