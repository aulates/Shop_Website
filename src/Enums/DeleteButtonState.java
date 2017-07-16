/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Enums;

/**
 **
 ** @author Ana Elena Ulate Salas 
 **/
//Enum property of the button with two properties: false or true
public enum DeleteButtonState {
    Enable(true), Disable(false);
//Variable
    private final boolean enable;
//Getter and setter of the button
    DeleteButtonState(Boolean state) {
        this.enable = state;
    }
    public boolean isEnable() {
        return enable;
    }
}
