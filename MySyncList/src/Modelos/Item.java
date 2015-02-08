/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelos;

/**
 *
 * @author nerea
 */
public class Item {
    private String descripcion;
    private int cod;
    private int cantidad;
    private int check_item;
    private boolean cambiado;

    public Item(){
        cambiado = true;
    }
    
    public Item(String desc, int cant, int check){
        this.cantidad = cant;
        this.check_item = check;
        this.descripcion = desc;
        cambiado = true;
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
     * @return the cantidad
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * @param cantidad the cantidad to set
     */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * @return the check_item
     */
    public int isCheck_item() {
        return check_item;
    }

    /**
     * @param check_item the check_item to set
     */
    public void setCheck_item(int check_item) {
        this.check_item = check_item;
    }

    /**
     * @return the cambiado
     */
    public boolean isCambiado() {
        return cambiado;
    }

    /**
     * @param cambiado the cambiado to set
     */
    public void setCambiado(boolean cambiado) {
        this.cambiado = cambiado;
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
}
