package controle;

import java.awt.Window;

import mapConfig.SystemFacade;

public class ControleCenario {
	private SystemFacade cenarioAtual;
	private int estadoCenario = 1;
	
	public void controle(Window janela) {
		if(estadoCenario == 1) {
			setCenarioAtual(new SystemFacade(janela, "Cenario2.scn", "musicaFundo.wav"));
		}else if(estadoCenario == 2) {
			setCenarioAtual(new SystemFacade(janela, "Cenario2.scn", "musicaFundo.wav"));
		}else{
			setCenarioAtual(new SystemFacade(janela, "Cenario3.scn", "musicaFundo.wav"));
		}
	}
	
	public SystemFacade getCenarioAtual() {
		return cenarioAtual;
	}

	public void setCenarioAtual(SystemFacade cenarioAtual) {
		this.cenarioAtual = cenarioAtual;
	}

	public void bossMorreu() {
		if (estadoCenario == 1) {
            estadoCenario = 2;
        }else if(estadoCenario == 2) {
        	estadoCenario = 3;
        }
	}
}
