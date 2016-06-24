/* Periodic thread in Java */

import javax.realtime.PriorityScheduler;
import javax.realtime.PriorityParameters;
import javax.realtime.PeriodicParameters;
import javax.realtime.RelativeTime;
import javax.realtime.RealtimeThread;

import java.util.Random;

public class RTHelloWorld {
	public static ControlePlanta cp;
  public RTHelloWorld(){
	Planta planta = new Planta();
	cp=new ControlePlanta(planta);
  }
  public static void main(String[] args)
  {
    RTHelloWorld rt=new RTHelloWorld();
    /* priority for new thread: mininum+10 */
    int priority = PriorityScheduler.instance().getMinPriority()+20;
    PriorityParameters priorityParameters = new PriorityParameters(priority);
    int priority2 = PriorityScheduler.instance().getMinPriority()+15;
    PriorityParameters priorityParameters2 = new PriorityParameters(priority2);

    /* period: 200ms */
    RelativeTime period = new RelativeTime(1000 /* ms */, 0 /* ns */);
    RelativeTime period2 = new RelativeTime(5000 /* ms */, 0 /* ns */);

    /* release parameters for periodic thread: */
    PeriodicParameters periodicParameters = new PeriodicParameters(null,period, null,null,null,null);
    PeriodicParameters periodicParameters2 = new PeriodicParameters(null,period2, null,null,null,null);

    /* create periodic thread: */
    RealtimeThread realtimeThread = new RealtimeThread(priorityParameters,periodicParameters)
    {
      public void run()
      {
		 //System.out.println("Thread 1"); 
        while(true)
        {
          cp.planta.sensorDeVapor.quantidadeVapor=5*(cp.planta.sensorDeAgua.quantidadeAgua/cp.planta.caldeiraVapor.capacidadeMaximaAgua);
          cp.planta.sensorDeAgua.quantidadeAgua-=cp.planta.sensorDeVapor.quantidadeVapor;
          //System.out.println("a: "+cp.planta.controleBomba0.getState());
		  if(cp.planta.controleBomba0.getState()){
			cp.planta.sensorDeAgua.quantidadeAgua+=cp.planta.bomba0.capacidade;
		  }
		  if(cp.planta.controleBomba1.getState()){
			cp.planta.sensorDeAgua.quantidadeAgua+=cp.planta.bomba1.capacidade;
		  }
		  if(cp.planta.controleBomba2.getState()){
			cp.planta.sensorDeAgua.quantidadeAgua+=cp.planta.bomba2.capacidade;
		  }
		  if(cp.planta.controleBomba3.getState()){
			cp.planta.sensorDeAgua.quantidadeAgua+=cp.planta.bomba3.capacidade;
		  }
		  if(cp.planta.caldeiraVapor.valvula.isOpen){
			cp.planta.sensorDeAgua.quantidadeAgua-= 5;
		  }
			  System.out.println("Agua: "+cp.planta.sensorDeAgua.getQuantidadeAgua()+" val:" +cp.planta.caldeiraVapor.valvula.isOpen
			  +" bombas: "+cp.planta.controleBomba3.getState()+" estado: "+cp.estado);
			  //System.out.println("Thread 1"); 
			  waitForNextPeriod();
			}
		  }
    };
    
    RealtimeThread realtimeThread2 = new RealtimeThread(priorityParameters2,periodicParameters2)
    {
      public void run()
      {
		  //System.out.println("thread2");
        while(true)
        {
		  breakSensor(cp);
          switch(cp.estado){
			case 0:  
				cp.inicializacao();
				break;
			case 1:
				cp.normal();
				break;
			case 2:
				cp.degradado();
				break;
			case 3:
				cp.salvamento();
				break;
			case 4:
				cp.parada();
				break;
		  }
		  System.out.println("Thread 2"); 
		  waitForNextPeriod();
	  }
	  /*if(cp.planta.sensorDeAgua.quantidadeAgua<cp.planta.caldeiraVapor.minimoNormalAgua){
		cp.planta.controleBomba0.Off();
	  }
	  else{
	 	cp.planta.controleBomba0.Start();
	  }
	  if(cp.planta.sensorDeAgua.quantidadeAgua>cp.planta.caldeiraVapor.maximoNormalAgua){
		cp.planta.caldeiraVapor.valvula.setState(true);
	  }
          else{
      		cp.planta.caldeiraVapor.valvula.setState(false);
	  }
        }*/
      }
    };

    /* start periodic thread: */
    realtimeThread2.start();
    realtimeThread.start();
    
    
  }
  public static void breakSensor(ControlePlanta cp){
	  Random rn = new Random();
	  if(rn.nextInt(10) == 9){
		  int aux = rn.nextInt(2);
		  switch(aux){
		  	case 0:
		  		System.out.println("Falha no sensor d'água!");
		  		cp.planta.sensorDeAgua.setFuncionamento(false);
		  		break;
		  	case 1:
		  		System.out.println("Falha no sensor de vapor!");
		  		cp.planta.sensorDeVapor.setFuncionamento(false);
		  		break;
		  		
		  }
	  }
  }
}
