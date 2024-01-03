/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package examen2023;

/**
 *
 * @author 21655
 */
public class ChambreException extends Exception{
    
    private Chambre chambre;

    public ChambreException(String message, Chambre chambre) {
        super(message);
        this.chambre = chambre;
    }
    
    public int getNumChambre(){
        return this.chambre.getNum_chambre();
    }
    
    
}
