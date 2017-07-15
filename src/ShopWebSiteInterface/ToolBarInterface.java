/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ShopWebSiteInterface;

import java.awt.event.ActionEvent;
/**
 **
 **
 ** @author Ana Elena Ulate Salas
 **/
public abstract class ToolBarInterface extends javax.swing.JFrame implements ToolBarMethod{
    abstract boolean validationNum();
    abstract boolean validationLetters();
    abstract void clearTextField();
}