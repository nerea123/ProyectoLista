/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelos;

import java.util.ArrayList;

/**
 *
 * @author nerea
 */
public class Lista {
    
    private int cod;
    private String descripcion;
    private ArrayList <Item> items;
    
    public Lista(){
        items = new ArrayList<>();
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
        aux+=descripcion+"|";
        for(int i =0;i<items.size();i++){
            aux +=items.get(i).getDescripcion()+"|";
            aux +=items.get(i).getCantidad()+"|";
            aux +=items.get(i).isCheck_item()+";";
        }
        return aux;
    }
    
    
}
