/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author rbazo
 */
public class ControleBomba extends Bomba {

    private Bomba bomba;
    private boolean funcionamento = true;

    public ControleBomba(Bomba bomba) {
        this.bomba = bomba;
    }

    //verifica se a água está fluindo da bomba para a caldeira
    public boolean isFlowing() {
        return bomba.isOpen;
    }
    
    public double getVazao(){
        return capacidade;
    }
    
    public void Start() {
        isOpen = true;
        //precisa de 5s pra começar a colocar água
    }

    public void Off() {
        isOpen = false;
        //imediato
    }

    public boolean getState() {
        return isOpen;
    }

    public void setFuncionamento(boolean estado) {
        funcionamento = estado;
    }

    public boolean isWorking() { /* throws ExceptionSensor{
         if(funcionamento){
         return funcionamento;
         }
         else{
         throw new ExceptionSensor("Falha no controlador de alguma bomba!");
         }
         */

        return funcionamento;
    }
}
