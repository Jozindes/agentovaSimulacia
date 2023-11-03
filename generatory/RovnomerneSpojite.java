/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package generatory;

/**
 *
 * @author Jožko
 */
public class RovnomerneSpojite extends Generator {   
    private final double a;
    private final double b;
    
    public RovnomerneSpojite(double a, double b) {
        this.a = a;
        this.b = b;
    }
    
    public double dajVygenerovanuHodnotu() {
        return this.a + (this.generator.nextDouble() * (this.b - this.a));
    }
}
