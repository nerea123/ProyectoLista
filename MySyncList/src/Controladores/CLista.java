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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

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
        lista.setDescripcion(vLista.getNomLista().getText());
        lista.setCod(0);
        setButtonsNames();
        setActionButtons();
        Item t = new Item();
        
        t.setDescripcion("preba");
        t.setCantidad(1);
        t.setCheck_item(1);
        
         Item t2 = new Item();
        
        t2.setDescripcion("preba2");
        t2.setCantidad(4);
        t2.setCheck_item(0);
        
        lista.getItems().add(t);
        lista.getItems().add(t2);
        vLista.getLista().setListData(lista.getItems().toArray());
        vLista.getLista().setCellRenderer(new MyListCellRenderer());
        System.out.println(lista.toJson());
        vLista.setLocationRelativeTo(null);
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
        vLista.getAbrir().addActionListener(new MyActionListener(vLista.getAbrir(), vLista.getLista(), lista));
        vLista.getAnadir().addActionListener(new MyActionListener(vLista.getAnadir(), vLista.getLista(), lista));
        vLista.getEliminar().addActionListener(new MyActionListener(vLista.getEliminar(), vLista.getLista(), lista, vLista));
         vLista.getEditar().addActionListener(new MyActionListener(vLista.getEditar(), vLista.getLista(), lista));
        vLista.getSync().addActionListener(new MyActionListener(vLista.getSync(), vLista.getLista(), lista));
        vLista.getGuardar().addActionListener(new MyActionListener(vLista.getGuardar(), vLista.getLista(), lista, vLista));
        
        vLista.getLista().addListSelectionListener(new ChangeSelection());
    }
    
    private void setButtonsNames(){
        vLista.getAbrir().setName("abrir");
        vLista.getAnadir().setName("anadir");
        vLista.getEliminar().setName("eliminar");
         vLista.getEditar().setName("editar");
        vLista.getSync().setName("sync");
        vLista.getGuardar().setName("guardar");
    }
    
    class ChangeSelection implements ListSelectionListener{

        @Override
        public void valueChanged(ListSelectionEvent e) {
      System.out.println(vLista.getLista().getSelectedIndex());
            if(vLista.getLista().getSelectedIndex() != -1)
            //obtenemos el item en la posicion seleccionada de la lista y asignamos su cantidad al texto
            vLista.getCantidad().setText(String.valueOf(lista.getItems().get(vLista.getLista().getSelectedIndex()).getCantidad()));
        }
        
    }
}
