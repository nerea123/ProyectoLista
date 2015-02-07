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
    private int cantidad;
    private boolean check_item;

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
    public boolean isCheck_item() {
        return check_item;
    }

    /**
     * @param check_item the check_item to set
     */
    public void setCheck_item(boolean check_item) {
        this.check_item = check_item;
    }
}
