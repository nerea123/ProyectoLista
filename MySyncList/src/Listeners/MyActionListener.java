/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Listeners;

import Conexion.JSONParser;
import Controladores.CLista;
import Modelos.Item;
import Modelos.Lista;
import Vistas.VAdd;
import Vistas.VEdit;
import Vistas.VLista;
import Vistas.VOpen;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author nerea
 */
public class MyActionListener implements ActionListener{
    
    private JButton button;
    private JList list;
    private Lista lista;
    private VAdd add;
    private VEdit edit;
    private VOpen open;
    private VLista vista;
    private String old_name;
    private int old_cant;
    private int old_check;
    private static int index;
    private static String get_list = "http://nereadaw.esy.es/get_lists.php";
    private static String get_list_item = "http://nereadaw.esy.es/get_list_items.php";
    private static String add_list = "http://nereadaw.esy.es/mysynclists.php";
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_SIN_NOMBRE = "Lista sin nombre";
    private static final String TAG_COD = "cod";
    private static final String TAG_ITEMS = "items";
    private static final String TAG_DESC = "desc";
    private static ArrayList<Lista> openLists= new ArrayList<>();
    
    public MyActionListener(JButton b, JList list, Lista lista){
        this.button = b;
        this.list = list;
        this.lista = lista;
    }
    
    public MyActionListener(JButton b, JList list, Lista lista, VAdd add){
        this.button = b;
        this.list = list;
        this.lista = lista;
        this.add = add;
    }
    
    public MyActionListener(JButton b, JList list, Lista lista, VEdit edit){
        this.button = b;
        this.list = list;
        this.lista = lista;
        this.edit = edit;
    }
    
     public MyActionListener(JButton b, JList list, Lista lista, VLista vista){
        this.button = b;
        this.list = list;
        this.lista = lista;
        this.vista = vista;
    }
     
     public MyActionListener(JButton b, JList list, Lista lista, VLista vista, VOpen open){
        this.button = b;
        this.list = list;
        this.lista = lista;
        this.vista = vista;
        this.open = open;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        switch(button.getName()){
            case "abrir":
                getIListasRequest();
                break;
            case "eliminar":
                int n = JOptionPane.showConfirmDialog(
                        vista,
                        "¿Eliminar "+lista.getItems().get(list.getSelectedIndex()).getDescripcion()+"?",
                        "Eliminar",
                        JOptionPane.YES_NO_OPTION);
                if(n==0){
                    lista.getItems_deleted().add(lista.getItems().get(list.getSelectedIndex()));
                    lista.getItems().remove(list.getSelectedIndex());
                    
                }
                 System.out.println("n"+n);   
                 list.setListData(lista.getItems().toArray());
                break;
            case "anadir":
                addAction();
                break;
            case "delete":
                break;
            case "editar":
                editAction();
                break;
            case "sync":
                System.out.println("pulsado sync");
                break;
            case "guardar":
                saveAction();
                break;
            case "nuevo":
                    lista.setCod(0);
                    vista.getNomLista().setText("Lista sin nombre");
                    lista.getItems().clear();
                    lista.getItems_deleted().clear();
                    list.setListData(lista.getItems().toArray());
                break;
            case "aceptarAdd":
                if(!validateAddAction(add.getNombre()))
                    JOptionPane.showMessageDialog(add,"Debes insertar el nombre del producto","Ops!",JOptionPane.WARNING_MESSAGE);
                else{
                    System.out.println(add.getNombre().getText()+"  "+add.getCantidad().getSelectedItem());
                    lista.addItem(new Item(add.getNombre().getText(), Integer.parseInt((String)add.getCantidad().getSelectedItem()), 0));
                    list.setListData(lista.getItems().toArray());
                    add.dispose();
                }
                break;
            case "aceptarEdit":
                if(!validateEditAction(edit.getNombre()))
                    JOptionPane.showMessageDialog(add,"Debes insertar el nombre del producto","Ops!",JOptionPane.WARNING_MESSAGE);
                else{
                    int new_check;
                    if(edit.getCheck().isSelected())
                        new_check=1;
                    else
                        new_check=0;
                    if(old_cant != Integer.parseInt((String)edit.getCantidad().getSelectedItem())
                            || old_check != new_check
                            || old_name != edit.getNombre().getText()){
                        lista.getItems().get(index).setCambiado(true);
                        lista.getItems().get(index).setCheck_item(new_check);
                        lista.getItems().get(index).setCantidad(Integer.parseInt((String)edit.getCantidad().getSelectedItem()));
                        lista.getItems().get(index).setDescripcion(edit.getNombre().getText());
                        list.setListData(lista.getItems().toArray());
                        lista.setModificada(true);
                    }
                    edit.dispose();
                }
                break;
            case "aceptarOpen":
                int cod = open.getListas().getSelectedIndex();
                getItemsListaRequest(openLists.get(cod).getCod(), openLists.get(cod).getDescripcion());
                break;
        }
        
    }
    
    public void addAction(){
    
        add = new VAdd();
        add.setTitle("Añadir");
        add.getAceptar().setName("aceptarAdd");
        add.getAceptar().addActionListener(new MyActionListener(add.getAceptar(), list, lista, add));
        add.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        add.setLocationRelativeTo(null);
        add.setVisible(true);
    }
    
    public boolean validateAddAction(JTextField name){
        if(name.getText().equals(""))
            return false;
        return true;
    }
    
    public void editAction(){
        index = -1;
        if(list.getSelectedIndex() != -1){
            index = list.getSelectedIndex();
            edit = new VEdit();
            edit.setTitle("Editar");
            old_name = lista.getItems().get(index).getDescripcion();
            edit.getNombre().setText(old_name);
            old_cant = lista.getItems().get(index).getCantidad();
            edit.getCantidad().setSelectedIndex(old_cant -1);
            old_check = lista.getItems().get(index).isCheck_item();
            if(old_check == 1)
                edit.getCheck().setSelected(true);
            edit.getAceptar().setName("aceptarEdit");
            edit.getAceptar().addActionListener(new MyActionListener(edit.getAceptar(), list, lista, edit));
            edit.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            edit.setLocationRelativeTo(null);
            edit.setVisible(true);
        }
    }
    
    public boolean validateEditAction(JTextField name){
        if(name.getText().equals(""))
            return false;
        return true;
    }
    
    public void newAction(){
    
        
    }
    
    public void saveAction(){
        if(lista.getCod() == 0){
            String s = (String)JOptionPane.showInputDialog(
                    vista,
                    "Ponle un nombre a tu lista",
                    "Guardar lista",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    null,
                    "");
            //If a string was returned, say so.
            if ((s != null) && (s.length() > 0)) {
                lista.setDescripcion(s);
            }
        }
        addRequest();
    }
    
    public void addRequest()
    {
        JSONParser jsonParser = new JSONParser();
        // Check for success tag
        int success;
        try {
            // Building Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            
            params.add(new BasicNameValuePair("lista", lista.toJson()));
            if(lista.getItems_deleted().size() > 0)
                params.add(new BasicNameValuePair("delete", lista.deletedToJson()));
            System.out.println("json "+lista.toJson());
            System.out.println("json "+lista.deletedToJson());
//            if(lista.isModificada())
//                params.add(new BasicNameValuePair("update", "si"));
//            System.out.println(params.toString());

            // getting product details by making HTTP request
            JSONObject json = jsonParser.makeHttpRequest(
                            add_list , "POST", params);
            System.out.println(json.toString());
            
            success = json.getInt(TAG_SUCCESS);
            if (success == 1) {
                 lista.setCod(json.getInt(TAG_COD));
                 JOptionPane.showMessageDialog(vista,"Lista "+lista.getDescripcion()+" guardada");
                 vista.getNomLista().setText(lista.getDescripcion());
//                 for(int i=0;i<lista.getItems().size();i++){
//                     lista.getItems().get(i).setCambiado(false);
//                 }
                 lista.setModificada(false);
                 lista.getItems_deleted().clear();
                 JSONArray items = json.getJSONArray(TAG_ITEMS);

                // looping through All Products
                for (int i = 0; i < items.length(); i++) {
                    JSONObject c = items.getJSONObject(i);
                    for(int j=0;j<lista.getItems().size();j++){
                        if(lista.getItems().get(j).getDescripcion().equals(c.getString("desc"))){
                            lista.getItems().get(j).setCambiado(false);
                            lista.getItems().get(j).setCod(c.getInt("cod_item"));
                            System.out.println(lista.getItems().get(j).getDescripcion()+" "+lista.getItems().get(j).getCod());
                        }
                    }
                }
            }
        } catch (JSONException e) {
                e.printStackTrace();
        }
    }
    
    public void getItemsListaRequest(int cod, String desc){
        JSONParser jsonParser = new JSONParser();
        // Check for success tag
        int success;
        try {
            // Building Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();

            params.add(new BasicNameValuePair("cod", String.valueOf(cod)));
            JSONObject json = jsonParser.makeHttpRequest(
                            get_list_item , "GET", params);
            System.out.println("items---"+json.toString());
            
            success = json.getInt(TAG_SUCCESS);
            if (success == 1) {
                 lista.setCod(cod);
                 
                 vista.getNomLista().setText(desc);
                 lista.getItems().clear();
                 JSONArray items = json.getJSONArray(TAG_ITEMS);

                // looping through All Products
                for (int i = 0; i < items.length(); i++) {
                    JSONObject c = items.getJSONObject(i);
                    lista.addItem(new Item(c.getString("desc"), c.getInt("cantidad"), c.getInt("check"), c.getInt("cod")));
                    
                }
                openLists.clear();
                list.setListData(lista.getItems().toArray());
                open.dispose();
            }
        } catch (JSONException e) {
                e.printStackTrace();
        }
    }
    
    public void getIListasRequest(){
        JSONParser jsonParser = new JSONParser();
        // Check for success tag
        int success;
        try {
            // Building Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();
 
            JSONObject json = jsonParser.makeHttpRequest(
                            get_list , "GET", params);
            System.out.println(json.toString());
            
            success = json.getInt(TAG_SUCCESS);
            if (success == 1) {
                 open = new VOpen();
                 JSONArray items = json.getJSONArray("lista");

                // looping through All Products
                for (int i = 0; i < items.length(); i++) {
                    JSONObject c = items.getJSONObject(i);
                    open.getListas().addItem(c.getString("descripcion"));
                    openLists.add(new Lista(c.getString("descripcion"), c.getInt("cod")));
                }
                open.getAceptar().setName("aceptarOpen");
                open.getAceptar().addActionListener(new MyActionListener(open.getAceptar(), list, lista, vista, open));
                open.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                open.setLocationRelativeTo(null);
                open.setVisible(true);
            }
        } catch (JSONException e) {
                e.printStackTrace();
        }
    }
}
