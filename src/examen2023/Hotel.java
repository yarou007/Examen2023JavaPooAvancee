/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package examen2023;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.sql.*;

/**
 *
 * @author 21655
 */
public class Hotel {
    private Set<Client> set_client;
    private List<Chambre> list_chambre;
    private Map<Client,Chambre> map_reservation;
    Connection cx = null;
    public Hotel(){
        set_client= new HashSet<Client>();
        list_chambre=new ArrayList<Chambre>();
        map_reservation = new HashMap<Client,Chambre>();
       }
   
    
    // connexion method behs maghyr ma nokeed naawed feha pas mal de fois 
    public void connexionDB(){
          try {
            Class.forName("com.mysql.cj.jdbc.Driver");
             cx = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel", "root", "manager");
          }catch (ClassNotFoundException ex) {
            System.out.println("Driver not loading");
        } catch (SQLException x) {
            System.out.println(x.getMessage() + " SQL EXCEPTION");
        }
    }
     // creation chambre ( statique mel lowel mbaad nekhdem base de donneés ) 
    public void createChambre(Chambre ch){
       /* insertion hard-coding 
         list_chambre.add(ch);
     */ 
       // database stuff 
       
     try{
            connexionDB();
             String req = " insert into chambre values (?,?,?) ";
             PreparedStatement pstm = cx.prepareStatement(req);
             pstm.setInt(1, ch.getNum_chambre());
             pstm.setInt(2, ch.getNb_lits_disp());
             pstm.setString(3, ch.getEtat());
             if (pstm.executeUpdate()!=0) System.out.println("insertion avec succes  ");
             else System.out.println("Probleme d'insertion ");
        } catch (SQLException x) {
            System.out.println(x.getMessage() + " SQL EXCEPTION");
        }
    }
    
    ///update 
    public void updateChambre(Chambre ch){
          try{
             connexionDB();
             String req = " update chambre set nbLitDis=? , etat=? where numCh=? ";
             PreparedStatement pstm = cx.prepareStatement(req);
         
             pstm.setInt(1, ch.getNb_lits_disp());
             pstm.setString(2, ch.getEtat());
             pstm.setInt(3, ch.getNum_chambre());
             if (pstm.executeUpdate()!=0) System.out.println("modifcation avec succes  ");
             else System.out.println("Probleme modifcation ");
        } catch (SQLException x) {
            System.out.println(x.getMessage() + " SQL EXCEPTION");
        }
    }
    
    
    public void findByNum(int numCh){
        try{
             connexionDB();
             String req = " select * from chambre where numCh=? ";
             PreparedStatement pstm = cx.prepareStatement(req);
             pstm.setInt(1, numCh);
             ResultSet res = pstm.executeQuery();
             while(res.next()){
                 System.out.println(res.getInt(1)+ " "+res.getInt(2)+" "+res.getString(3) );
             }
            
        } catch (SQLException x) {
            System.out.println(x.getMessage() + " SQL EXCEPTION");
        }
    }
    
    public boolean existeClient( Client cl){
        return set_client.contains(cl);
    }
    public boolean existeChambre(int numChambre){
        for (Chambre chambre : list_chambre) {
            if(chambre.getNum_chambre()==numChambre)
            return true;
        }
      return false;
    }
    public boolean verifDispo(Chambre ch, int nblits){
      if   (existeChambre(ch.getNum_chambre())){    // 2          -       1 =1
           if (ch.getEtat().equals("libre" ) &&  (ch.getNb_lits_disp()>=nblits) ){
               return true;
           }
        }
         return false;   
    }
    public boolean Reserver(Client cl,Chambre ch) {
       if(map_reservation.containsKey(cl)){
           return false;
       }
    try{
        if(verifDispo(ch, cl.getNb_lits_res())){
            ch.setEtat("reservee");
             
             map_reservation.put(cl, ch);
             ch.setNb_lits_disp(ch.getNb_lits_disp()-cl.getNb_lits_res());
            return true;
        }
        else{
            String message = "Chambre reserve ou nb lits insuffisant pour client "+cl.getNom();
            throw new ChambreException(message, ch);
        }
    }catch(ChambreException ex){
            System.err.println(ex.getMessage()+" pour chambre num "+ex.getNumChambre());
    }
      return false;
    }
    
    
    public boolean annulerReservation(Client cl){
        if (!existeClient(cl)) return false;
        try{
            if(cl==null){
                throw new NullPointerException("Client est null :p");
            }
            else{
                Chambre ch = map_reservation.get(cl);
                ch.setEtat("libre");
                ch.setNb_lits_disp(ch.getNb_lits_disp()+cl.getNb_lits_res());
                map_reservation.remove(cl);
            }
        }catch(NullPointerException ex){
            System.out.println(ex.getMessage());
        }
        return true;
    }
    
    
    public boolean TransferVers(Client cl, Chambre ch1, Chambre ch2){
//        if (!existeClient(cl)) return false;
//        if(!map_reservation.containsKey(cl)){
//            return false;
//        }
//     
      if(verifDispo(ch2, cl.getNb_lits_res())){
            ch1.setEtat("libre");
            //ch2.setEtat("reservee");
            ch1.setNb_lits_disp(ch1.getNb_lits_disp()+cl.getNb_lits_res());
              map_reservation.remove(cl);
            Reserver(cl, ch2);
          
                  return true;      
        }
       return false;
    }
         
    
    public void Afficher(){
      System.out.println("Client : ");
        set_client.forEach(System.out::println);
        System.out.println("Chambre: ");
        list_chambre.forEach(System.out::println);
         System.out.println("Reservation");
        Set paires = map_reservation.entrySet();
        Iterator<Map.Entry<Client,Chambre>> iter = paires.iterator();
        while(iter.hasNext()){
              Map.Entry paire=iter.next();
              System.out.println(paire.getKey()+"\n"+paire.getValue());
          }
    }
}
