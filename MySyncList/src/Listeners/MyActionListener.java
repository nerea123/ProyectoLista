/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 *
 * @author nerea
 */
public class MyActionListener implements ActionListener{
    
    private JButton button;
    
    public MyActionListener(JButton b){
        this.button = b;
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
                break;
            case "delete":
                break;
            case "sync":
                System.out.println("pulsado sync");
                break;
            case "guardar":
                System.out.println("pulsado guardar");
                break;
        }
        
    }
    
}
