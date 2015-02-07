/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Listeners;

import Conexion.JSONParser;
import Controladores.CLista;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author nerea
 */
public class MyActionListener implements ActionListener{
    
    private JButton button;
    private JList list;
    private static String get_list = "http://nereadaw.esy.es/get_lists.php";
    private static String add_list = "http://nereadaw.esy.es/add_list.php";
    private static final String TAG_SUCCESS = "success";
    
    public MyActionListener(JButton b, JList list){
        this.button = b;
        this.list = list;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch(button.getName()){
            case "abrir":
                System.out.println("pulsado abrir");
                break;
            case "eliminar":
                System.out.println("pulsado eliminar");
                break;
            case "anadir":
                System.out.println("pulsado anadir");
                addAction();
                break;
            case "delete":
                break;
            case "sync":
                System.out.println("pulsado sync");
                break;
            case "guardar":
                System.out.println("pulsado guardar");
                break;
            case "nuevo":
                break;
        }
        
    }
    
    public void addAction(){
    
        JFrame frame= new JFrame("Añadir item a lista");
        frame.setSize(400, 300);
        frame.setVisible(true);
       
    }
    
    public void newAction(){
    
        
    }
    
    public void addRequest()
    {
        JSONParser jsonParser = new JSONParser();
        // Check for success tag
        int success;
        try {
            // Building Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("lista", "lista3|cocacola|1|0<ternera|1|0<|agua|2|0<"));
            System.out.println(params.toString());

            // getting product details by making HTTP request
            JSONObject json = jsonParser.makeHttpRequest(
                            add_list , "POST", params);
            System.out.println(json.toString());
            
            success = json.getInt(TAG_SUCCESS);
            if (success == 1) {
                   //to-do
                System.out.println("bien!");
            }
        } catch (JSONException e) {
                e.printStackTrace();
        }
    }    
}
