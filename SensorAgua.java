/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author rbazo
 */
public class SensorAgua {

    public double quantidadeAgua; //em litros
    private boolean funcionamento = true;

    public SensorAgua() {
        quantidadeAgua = 0;
    }

    public double getQuantidadeAgua(){
        return quantidadeAgua;
    }

    public void setFuncionamento(boolean estado) {
        funcionamento = estado;
    }

    public boolean isWorking() { /*throws ExceptionSensor{
         if(funcionamento){
         return funcionamento;
         }
         else{
         throw new ExceptionSensor("Falha no sensor d'água!");
         }
         */

        return funcionamento;
    }

}
