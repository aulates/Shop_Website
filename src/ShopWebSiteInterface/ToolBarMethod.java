/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ShopWebSiteInterface;

import java.awt.event.ActionEvent;
/**
 **
 ** @author Ana Elena Ulate Salas
 **/
//interface with Tool Bar Methods
public interface ToolBarMethod {
    public boolean jbNewActionPerfomed(ActionEvent evt);
    public boolean jbEditActionPerfomed(ActionEvent evt);
    public boolean jbSaveActionPerfomed(ActionEvent evt);
    public boolean jbCancelActionPerfomed(ActionEvent evt);
    public boolean jbDeleteActionPerfomed(ActionEvent evt);
    public boolean jbFindActionPerfomed(ActionEvent evt);
    public boolean jbFilterActionPerfomed(ActionEvent evt);
    public boolean jbReloadActionPerfomed(ActionEvent evt);
    public boolean jbExitActionPerfomed(ActionEvent evt);
    public void addBuy();
    public void cancelBuy();
    public void reloadBuy();
}
