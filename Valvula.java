/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author rbazo
 */
public class Valvula {
    public boolean isOpen;
    
    public Valvula(){
        isOpen = false;
    }
    
    public boolean getState(){
        return isOpen;
    }
    
    public void setState(boolean state){
        isOpen = state;
    }
}
