/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controladores;

import Listeners.MyActionListener;
import Modelos.Item;
import Modelos.Lista;
import Render.MyListCellRenderer;
import Vistas.VLista;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.ListModel;

/**
 *
 * @author nerea
 */
public class CLista {
    
    private Lista lista;
    private VLista vLista;
    
    private CLista() {
        vLista = new VLista();
        lista = new Lista();
        setButtonsNames();
        setActionButtons();
        Item t = new Item();
        
        t.setDescripcion("preba");
        t.setCheck_item(true);
        
         Item t2 = new Item();
        
        t2.setDescripcion("preba");
        t2.setCheck_item(false);
        
        lista.getItems().add(t);
        lista.getItems().add(t2);
        vLista.getLista().setListData(lista.getItems().toArray());
        vLista.getLista().setCellRenderer(new MyListCellRenderer());
        vLista.setVisible(true);
    }
    
    public static CLista getInstance() {
        return CSingletonListaHolder.INSTANCE;
    }

    /**
     * @return the lista
     */
    public Lista getLista() {
        return lista;
    }

    /**
     * @return the vLista
     */
    public VLista getvLista() {
        return vLista;
    }

    /**
     * @param vLista the vLista to set
     */
    public void setvLista(VLista vLista) {
        this.vLista = vLista;
    }
    
    private static class CSingletonListaHolder {

        private static final CLista INSTANCE = new CLista();
    }
    
    private void setActionButtons(){
        vLista.getAbrir().addActionListener(new MyActionListener(vLista.getAbrir()));
        vLista.getAnadir().addActionListener(new MyActionListener(vLista.getAnadir()));
        vLista.getEliminar().addActionListener(new MyActionListener(vLista.getEliminar()));
        vLista.getSync().addActionListener(new MyActionListener(vLista.getSync()));
        vLista.getGuardar().addActionListener(new MyActionListener(vLista.getGuardar()));
    }
    
    private void setButtonsNames(){
        vLista.getAbrir().setName("abrir");
        vLista.getAnadir().setName("anadir");
        vLista.getEliminar().setName("eliminar");
        vLista.getSync().setName("sync");
        vLista.getGuardar().setName("guardar");
    }
}
