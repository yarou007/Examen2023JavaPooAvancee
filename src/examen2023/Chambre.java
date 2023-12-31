/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package examen2023;

/**
 *
 * @author 21655
 */
public class Chambre {
    private int num_chambre;
    private int nb_lits_disp;
    private String etat;

    public Chambre(int num_chambre, int nb_lits_disp, String etat) {
        this.num_chambre = num_chambre;
        this.nb_lits_disp = nb_lits_disp;
        this.etat = etat;
    }

    public int getNum_chambre() {
        return num_chambre;
    }

    public void setNum_chambre(int num_chambre) {
        this.num_chambre = num_chambre;
    }

    public int getNb_lits_disp() {
        return nb_lits_disp;
    }

    public void setNb_lits_disp(int nb_lits_disp) {
        this.nb_lits_disp = nb_lits_disp;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    @Override
    public String toString() {
        return "Chambre{" + "num_chambre=" + num_chambre + ", nb_lits_disp=" + nb_lits_disp + ", etat=" + etat + '}';
    }
    
}
