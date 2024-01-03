/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package examen2023;

import java.util.Objects;

/**
 *
 * @author 21655
 */
public class Client {
    
   private String nom;
   private String prenom;
   private String adresse;
   private int nb_lits_res;

    public Client(String nom, String prenom, String adresse, int nb_lits_res) {
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.nb_lits_res = nb_lits_res;
    }

    public Client() {
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public int getNb_lits_res() {
        return nb_lits_res;
    }

    public void setNb_lits_res(int nb_lits_res) {
        this.nb_lits_res = nb_lits_res;
    }
//
//
//  public boolean equals(Client autre){
//    if     (Objects.equals(this.nom, autre.nom) && 
//            Objects.equals(this.prenom, autre.nom) && 
//            Objects.equals(this.adresse, autre.nom)){
//                                                          return true; // shyraja3 true w yokhrej :p 
//      }
//       return false; // else 
//  }
//    
//    Question 1 )
   @Override
    public boolean equals (Object obj){
        if (this==obj){
            return true;
        }
        if(getClass()!=obj.getClass()) return false;
        if(obj==null) return false;
        Client cl = (Client) obj;
        return (Objects.equals(this.nom, cl.nom) && Objects.equals(this.prenom, cl.prenom) && Objects.equals(this.adresse, cl.adresse));
    }

    @Override
    public String toString() {
        return "Client{" + "nom=" + nom + ", prenom=" + prenom + ", adresse=" + adresse + ", nb_lits_res=" + nb_lits_res + '}';
    }
    
    
}
