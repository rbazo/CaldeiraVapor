/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author rbazo
 */
public class SensorVapor {

    public double quantidadeVapor; // em litros/s
    private boolean funcionamento = true;

    public SensorVapor() {
        quantidadeVapor = 0;
    }

    public double getQuantidade() {
        return quantidadeVapor;
    }

    public void setFuncionamento(boolean estado) {
        funcionamento = estado;
    }

    public boolean isWorking() { /*throws ExceptionSensor{
         if(funcionamento){
         return funcionamento;
         }
         else{
         throw new ExceptionSensor("Falha no sensor de Vapor!");
         }*/

        return funcionamento;
    }
}
