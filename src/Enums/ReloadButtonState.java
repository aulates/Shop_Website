/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Enums;

/**
 *
 * @author Ana Elena Ulate Salas 
 */
public enum ReloadButtonState {
    Enable(true), Disable(false);

    private final boolean enable;

    ReloadButtonState(Boolean state) {
        this.enable = state;
    }

    public boolean isEnable() {
        return enable;
    }
}
