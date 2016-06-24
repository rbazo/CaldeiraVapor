/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author rbazo
 */
public class Planta {
    
    public CaldeiraVapor caldeiraVapor;
    public SensorAgua sensorDeAgua;
    public SensorVapor sensorDeVapor;
    public Bomba bomba0;
    public Bomba bomba1;
    public Bomba bomba2;
    public Bomba bomba3;
    public ControleBomba controleBomba0;
    public ControleBomba controleBomba1;
    public ControleBomba controleBomba2;
    public ControleBomba controleBomba3;
    
    
    public Planta(){
        caldeiraVapor = new CaldeiraVapor();
        sensorDeAgua= new SensorAgua();
        sensorDeVapor = new SensorVapor();
        bomba0 = new Bomba();
        bomba1 = new Bomba();
        bomba2 = new Bomba();
        bomba3 = new Bomba();
        controleBomba0 = new ControleBomba(bomba0);
        controleBomba1 = new ControleBomba(bomba1);
        controleBomba2 = new ControleBomba(bomba2);
        controleBomba3 = new ControleBomba(bomba3);
    }
    
}
