/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Render;

import Modelos.Item;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

/**
 *
 * @author nerea
 */
public class MyListCellRenderer implements ListCellRenderer {
    
    protected DefaultListCellRenderer defaultRenderer = new DefaultListCellRenderer();
    private ImageIcon iconOk = createImageIcon("../images/ok.png","");
    private ImageIcon iconNo = createImageIcon("../images/no.png","");
    //private String text = null;
    private Font font = new Font("Helvetica", Font.PLAIN, 20);
    private Color foreground = Color.BLACK;

    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        
        JLabel renderer = (JLabel) defaultRenderer.getListCellRendererComponent(list, value, index,
        isSelected, cellHasFocus);
        
        if (value instanceof Item) {
          
          Item item = (Item) value;
          
          if (!isSelected) {
            renderer.setForeground(foreground);
          }
          
          if (item.isCheck_item()) {
            renderer.setIcon(iconOk);
          }
          else
              renderer.setIcon(iconNo);
          
          renderer.setText(item.getDescripcion());
          renderer.setFont(font);
        }
        
        return renderer;
    }
    
    /** Returns an ImageIcon, or null if the path was invalid. */
    protected ImageIcon createImageIcon(String path,
                                               String description) {
        java.net.URL imgURL = getClass().getResource(path);
        if (imgURL != null) {
            if(description.equals(""))
              return new ImageIcon(imgURL);
            else
              return new ImageIcon(imgURL, description);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
    
}
