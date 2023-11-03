/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package statistiky;

/**
 *
 * @author Jožko
 */
public class Priemer {
    
    private double celkovySucet;
    private double pocetHodnot;
    
    private double celkovySucetDoCasu;
    private double pocetHodnotDoCasu;
    
    public Priemer() {
        this.celkovySucet = 0;
        this.pocetHodnot = 0;
        
        this.celkovySucetDoCasu = 0;
        this.pocetHodnotDoCasu = 0;
    }
    
    // pre statistiky s chladenim
    public void pripocitajHodnotu(double kolko) {
        this.celkovySucet = this.celkovySucet + kolko;
        this.pocetHodnot = this.pocetHodnot + 1;
    }
    
    public double dajPriemer() {
        return this.celkovySucet / this.pocetHodnot;
    }
    
    public double dajPocetHodnot() {
        return this.pocetHodnot;
    }
    
    public double dajSmerodajnuOdchylku() {
        double sumaNaDruhu = Math.pow(dajPriemer(), 2);        
        double prvaZatvorka = sumaNaDruhu / (this.pocetHodnot - 1);
        double druhaZatvorka = dajPriemer() / (this.pocetHodnot - 1);
        double zatvorkaNaDruhu = Math.pow(druhaZatvorka, 2);
        return Math.sqrt(prvaZatvorka - zatvorkaNaDruhu);
    }
    
    public double dajLavostrannyInterval90() {
        return dajPriemer() - (dajSmerodajnuOdchylku() * 1.645 / Math.sqrt(this.pocetHodnot));
    }
    
    public double dajPravostrannyInterval90() {
        return dajPriemer() + (dajSmerodajnuOdchylku() * 1.645 / Math.sqrt(this.pocetHodnot));
    }
    //
    
    // pre statistiky bez chladenia
    public void pripocitajHodnotuDoCasu(double kolko) {
        this.celkovySucetDoCasu = this.celkovySucetDoCasu + kolko;
        this.pocetHodnotDoCasu = this.pocetHodnotDoCasu + 1;
    }
    
    public double dajPriemernyCasDoCasu() {
        return this.celkovySucetDoCasu / this.pocetHodnotDoCasu;
    }
    
    public double dajPocetHodnotDoCasu() {
        return this.pocetHodnotDoCasu;
    }
    
    public double dajSmerodajnuOdchylkuDoCasu() {
        double sumaNaDruhu = Math.pow(dajPriemernyCasDoCasu(), 2);        
        double prvaZatvorka = sumaNaDruhu / (this.pocetHodnotDoCasu - 1);
        double druhaZatvorka = dajPriemernyCasDoCasu()/ (this.pocetHodnotDoCasu - 1);
        double zatvorkaNaDruhu = Math.pow(druhaZatvorka, 2);
        return Math.sqrt(prvaZatvorka - zatvorkaNaDruhu);
    }
    
    public double dajLavostrannyInterval90DoCasu() {
        return dajPriemernyCasDoCasu() - (dajSmerodajnuOdchylkuDoCasu() * 1.645 / Math.sqrt(this.pocetHodnotDoCasu));
    }
    
    public double dajPravostrannyInterval90DoCasu() {
        return dajPriemernyCasDoCasu() + (dajSmerodajnuOdchylkuDoCasu() * 1.645 / Math.sqrt(this.pocetHodnotDoCasu));
    }
    //
}
