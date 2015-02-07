/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nerea
 */
public class Conexion {
    
     private static Conexion INSTANCE = new Conexion();
    private String bd;
    private String servidor;
    private String puerto;
    private String usuario;
    private String contraseña;
    private Conexion() {

 }

    public static Conexion getInstance() {
        return INSTANCE;
    }
    public Connection conectar(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            String BaseDeDatos = "jdbc:mysql://"+servidor+":"+puerto+"/"+bd+"";
            String login = usuario;
            String passwd = contraseña;
            Connection Conexion = (Connection) DriverManager.getConnection(BaseDeDatos,login,passwd);
            return Conexion;
        }
         catch (ClassNotFoundException e) {
            System.out.println("Clase no encontrada");
         }        
        catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
         }
        return null;
    }

}
