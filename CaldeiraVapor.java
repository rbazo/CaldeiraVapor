/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author rbazo
 */
public class CaldeiraVapor {
    
    public Valvula valvula;
    public static double capacidadeMaximaAgua; // capacidade máxima da caldeira em litros
    public static double limiteMinimoAgua; // abaixo desse valor a caldeira fica em perigo após 5s, em litros
    public static double limiteMaximoAgua; // acima desse valor a caldeira fica em perigo após 5s, em litros
    public static double minimoNormalAgua; // valor mínimo que deve ser mantido, em litros
    public static double maximoNormalAgua; // valor maximo que deve ser mantido, em litros
    public static double maximoVapor; // quantidade máxima de vapor, em litros/segundo
    public static double fatorAumentoVapor; //a definir
    public static double fatorReducaoVapor; //a definir
    
    public CaldeiraVapor(){
        valvula = new Valvula(); //em um primeiro momento a válvula está aberta, segundo a especificação
        capacidadeMaximaAgua=200; // capacidade máxima da caldeira em litros
		limiteMinimoAgua=capacidadeMaximaAgua*0.1; // abaixo desse valor a caldeira fica em perigo após 5s, em litros
		limiteMaximoAgua=capacidadeMaximaAgua*0.9; // acima desse valor a caldeira fica em perigo após 5s, em litros
		minimoNormalAgua=capacidadeMaximaAgua*0.25; // valor mínimo que deve ser mantido, em litros
		maximoNormalAgua=capacidadeMaximaAgua*0.75; // valor maximo que deve ser mantido, em litros
		maximoVapor=0; // quantidade máxima de vapor, em litros/segundo
		fatorAumentoVapor=0; //a definir
		fatorReducaoVapor=0; //a definir
    }
    
    
}
