/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package statistiky;

/**
 *
 * @author Jožko
 */
public class PriemernaDlzkaRadu {
    
    private double casPoslednejZmenyDlzkyRadu;
    private double celkovySucet;
    
    private double celkovySucetDoCasu;
      
    public PriemernaDlzkaRadu() {
        this.casPoslednejZmenyDlzkyRadu = 0;
        this.celkovySucet = 0;
        
        this.celkovySucetDoCasu = 0;
    }
    
    public void pripocitajVazenuPocetnost(double aktualnySimulacnyCas, int pocetLudi) {
        double vypocitane = (aktualnySimulacnyCas - this.casPoslednejZmenyDlzkyRadu) * pocetLudi;
        this.celkovySucet = this.celkovySucet + vypocitane;
        this.casPoslednejZmenyDlzkyRadu = aktualnySimulacnyCas;
        if (aktualnySimulacnyCas < 28800) {
            this.celkovySucetDoCasu = this.celkovySucet;
        }
    }
    
    public double dajPriemernuDlzkuRadu(double aktualnySimulacnyCas) {
        return this.celkovySucet / aktualnySimulacnyCas;
    }
      
    public double dajPriemernuDlzkuRaduDoCasu(double aktualnySimulacnyCas) {
        return this.celkovySucetDoCasu / aktualnySimulacnyCas;
    }
}
