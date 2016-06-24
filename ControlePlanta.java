/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;

/**
 *
 * @author rbazo
 */
public class ControlePlanta {

    //existem 5 estados
    //0 = inicialização
    //1 = normal
    //2 = degradado
    //3 = salvamento
    //4 = parada
    public int estado;
    public Planta planta;
    public boolean inicializando;

    public ControlePlanta(Planta planta) {
        this.planta = planta;
        estado=0;
        inicializando=false;
    }

    /*public void Opera() {
        estado=0;return;
        //fazer um switch case entre os modos de operação
        //colocar um random pra causar umas falhas nos dispositivos setando o funcionamento deles para false?
        
    }*/

    public void inicializacao() {
        //estado = 0;
        //se o sensor de vapor não for 0 chama a parada ou falha no sensor dagua
        if(!inicializando){
			if (planta.sensorDeVapor.getQuantidade() != 0 || !planta.sensorDeAgua.isWorking()) {
				estado=4;return;            
			}

			//se a quantidade água for maior que o máximo normal
			//abre a válvula
			if (planta.sensorDeAgua.getQuantidadeAgua() > CaldeiraVapor.maximoNormalAgua) {
				planta.caldeiraVapor.valvula.isOpen=true;
			}

			//se a quantidade de água estiver abaixo do mínimo normal
			//abre uma bomba
			if (planta.sensorDeAgua.getQuantidadeAgua() < CaldeiraVapor.minimoNormalAgua) {
				planta.controleBomba0.Start();
				planta.controleBomba1.Start();
				planta.controleBomba2.Start();
				//System.out.println("b: "+planta.controleBomba0.getState());
				planta.controleBomba3.Start();
				inicializando=true;
				estado=0;return;
			}

			//se todos sensores estiverem funcionando
			if (planta.sensorDeAgua.isWorking() && planta.sensorDeVapor.isWorking()
                && !checkFailurePumps()) {
            //se a agua estiver no nível normal
            if (planta.sensorDeAgua.getQuantidadeAgua() > CaldeiraVapor.minimoNormalAgua
                    && planta.sensorDeAgua.getQuantidadeAgua() < CaldeiraVapor.maximoNormalAgua) {
                estado=1;return;
                
            }
		    //senão vá para degradado
			
		}
		else {
				estado=2;return;
			}
		}else{
			estado=1;return;
		}
    }

    //checar as bombas também me parece gambiarra
    public void normal() {
        //estado = 1;
        if (!planta.sensorDeAgua.isWorking()) {
            estado=3;return;
        }

        if (planta.sensorDeAgua.getQuantidadeAgua() < CaldeiraVapor.minimoNormalAgua) {
            //checar se está próximo limite minimo, se sim ir para estado=4;return;
            //como implementar um 'proximo?'
            //5 provisório para considerar próximo
            if (planta.sensorDeAgua.getQuantidadeAgua() < CaldeiraVapor.limiteMinimoAgua) {
                estado=4;return;
            }

            planta.controleBomba0.Start();
            planta.controleBomba1.Start();
            planta.controleBomba2.Start();
            planta.controleBomba3.Start();
        }
        if (planta.sensorDeAgua.getQuantidadeAgua() > CaldeiraVapor.maximoNormalAgua) {
            //checar se está próximo limite maximo, se sim ir para estado=4;return;
            //como implementar um 'proximo?'
            //5 provisório para considerar próximo
            

            planta.controleBomba0.Off();
            planta.controleBomba1.Off();
            planta.controleBomba2.Off();
            planta.controleBomba3.Off();
            planta.caldeiraVapor.valvula.isOpen=true;
            
            if (planta.sensorDeAgua.getQuantidadeAgua() > CaldeiraVapor.limiteMaximoAgua) {
                estado=4;return;
            }
        }
        
        if((planta.sensorDeAgua.getQuantidadeAgua() < CaldeiraVapor.maximoNormalAgua) && 
        (planta.sensorDeAgua.getQuantidadeAgua() > CaldeiraVapor.minimoNormalAgua)){
			planta.caldeiraVapor.valvula.isOpen=false;
		}

        //se falha em algum outro dispositivo
        if (!planta.sensorDeVapor.isWorking()) {
            estado=2;return;
        }

    }

    public void degradado() {
        //estado = 2;
        if (planta.sensorDeAgua.getQuantidadeAgua() < CaldeiraVapor.minimoNormalAgua) {
            //checar se está próximo limite minimo, se sim ir para estado=4;return;
            //como implementar um 'proximo?'
            //5 provisório para considerar próximo
            

         //   checkOpenPumps().Start();
			planta.controleBomba0.Start();
            planta.controleBomba1.Start();
            planta.controleBomba2.Start();
            planta.controleBomba3.Start();
            
            if (planta.sensorDeAgua.getQuantidadeAgua() < CaldeiraVapor.limiteMinimoAgua) {
                estado=4;return;
            }
        }
        if (planta.sensorDeAgua.getQuantidadeAgua() > CaldeiraVapor.maximoNormalAgua) {
            //checar se está próximo limite maximo, se sim ir para estado=4;return;
            //como implementar um 'proximo?'
            //5 provisório para considerar próximo
            

            planta.controleBomba0.Off();
            planta.controleBomba1.Off();
            planta.controleBomba2.Off();
            planta.controleBomba3.Off();
            
            if (planta.sensorDeAgua.getQuantidadeAgua() > CaldeiraVapor.limiteMaximoAgua) {
                estado=4;return;
                //System.out.println("aqui");
            }
        }
        if (planta.sensorDeVapor.isWorking() && planta.sensorDeAgua.isWorking()) {
            estado=1;return;
        }

    }

    public void salvamento() {
        //estado = 3;
        //se o número de vazão de todas as bombas = valor do sensor de vapor
        //senão ir para parada
        //**considerar que todas bombas tem a mesma vazão
        if (planta.sensorDeVapor.getQuantidade() == planta.controleBomba0.getVazao() * numberOpenPumps()) {

            //se todos os sensores voltarem a funcionar volta pro normal
            if (planta.sensorDeVapor.isWorking() && checkFailurePumps() && planta.sensorDeAgua.isWorking()) {
                estado=1;return;
            }
            
            //se só o de agua voltar a funcionar volta pro degradado
            if(planta.sensorDeAgua.isWorking()){
                estado=2;return;
            }
            
        } else {
            estado=4;return;
        }

    }

    public void parada() {
        //estado = 4;
        
        System.out.println("Morte do caldeirão.");
        System.exit(1);
        
    }

    //true se há falha em alguma bomba
    //false se não há falha
    public boolean checkFailurePumps() {
        ArrayList<ControleBomba> listaControleBombas = new ArrayList();
        listaControleBombas.add(planta.controleBomba0);
        listaControleBombas.add(planta.controleBomba1);
        listaControleBombas.add(planta.controleBomba2);
        listaControleBombas.add(planta.controleBomba3);

        for (ControleBomba controleBomba : listaControleBombas) {
            if (!controleBomba.isWorking()) {
                return true;
            }
        }
        return false;
    }

    public int numberOpenPumps() {
        ArrayList<ControleBomba> listaControleBombas = new ArrayList();
        listaControleBombas.add(planta.controleBomba0);
        listaControleBombas.add(planta.controleBomba1);
        listaControleBombas.add(planta.controleBomba2);
        listaControleBombas.add(planta.controleBomba3);
        int i = 0;

        for (ControleBomba controleBomba : listaControleBombas) {
            if (controleBomba.isOpen) {
                i++;
            }
        }
        return i;
    }

    public ControleBomba checkOpenPumps() {
        ArrayList<ControleBomba> listaControleBombas = new ArrayList();
        listaControleBombas.add(planta.controleBomba0);
        listaControleBombas.add(planta.controleBomba1);
        listaControleBombas.add(planta.controleBomba2);
        listaControleBombas.add(planta.controleBomba3);

        for (ControleBomba controleBomba : listaControleBombas) {
            if (!controleBomba.isOpen) {
                return controleBomba;
            }
        }
        return null;
    }
}
