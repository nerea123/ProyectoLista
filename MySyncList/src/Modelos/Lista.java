/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelos;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author nerea
 */
public class Lista {
    
    private int cod;
    private String descripcion;
    private ArrayList <Item> items;
    private ArrayList <Item> items_deleted;
    private boolean modificada;
    
    public Lista(){
        items = new ArrayList<>();
        items_deleted = new ArrayList<>();
        modificada = false;
    }

    /**
     * @return the cod
     */
    public int getCod() {
        return cod;
    }

    /**
     * @param cod the cod to set
     */
    public void setCod(int cod) {
        this.cod = cod;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the items
     */
    public ArrayList <Item> getItems() {
        return items;
    }

    /**
     * @param items the items to set
     */
    public void setItems(ArrayList <Item> items) {
        this.items = items;
    }
    
    public String toString() {
        
        String aux="";
        aux+=cod+"|";
        aux+=descripcion+"|";
        for(int i =0;i<items.size();i++){
            if(items.get(i).isCambiado()){
                aux +=items.get(i).getDescripcion()+"|";
                aux +=items.get(i).getCantidad()+"|";
                aux +=items.get(i).isCheck_item()+"<";
                if(modificada)
                    aux +=items.get(i).getCod()+"<";               
            }
        }
        return aux;
    }
    
    public void addItem(Item t){
        items.add(t);
    }

    /**
     * @return the modificada
     */
    public boolean isModificada() {
        return modificada;
    }

    /**
     * @param modificada the modificada to set
     */
    public void setModificada(boolean modificada) {
        this.modificada = modificada;
    }
    
    public String toJson(){
        JSONObject j = new JSONObject() ;
        try {
            j.append("desc", descripcion);
            j.append("cod", cod);
            j.append("items", items);
        } catch (JSONException ex) {
            Logger.getLogger(Lista.class.getName()).log(Level.SEVERE, null, ex);
        }
        return j.toString();
    }
    
    public String deletedToJson(){
        JSONObject j = new JSONObject() ;
        try {
            j.append("desc", descripcion);
            j.append("cod", cod);
            j.append("items", items_deleted);
        } catch (JSONException ex) {
            Logger.getLogger(Lista.class.getName()).log(Level.SEVERE, null, ex);
        }
        return j.toString();
    }

    /**
     * @return the items_deleted
     */
    public ArrayList <Item> getItems_deleted() {
        return items_deleted;
    }

    /**
     * @param items_deleted the items_deleted to set
     */
    public void setItems_deleted(ArrayList <Item> items_deleted) {
        this.items_deleted = items_deleted;
    }
    
    
}
