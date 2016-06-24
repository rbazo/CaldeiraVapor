/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author rbazo
 */
public class Bomba {
    
    protected static double capacidade; //ou throughput, em litros/segundo
    protected boolean isOpen;
    
    public Bomba(){
        isOpen = false;
        capacidade = 4.5;
    }
       
}
