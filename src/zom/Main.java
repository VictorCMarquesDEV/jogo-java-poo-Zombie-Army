package zom;

import controle.ControleCenario;
import jplay.GameImage;
import jplay.Keyboard;
import jplay.URL;
import jplay.Window;
import mapConfig.SystemFacade;

public class Main {

	public static void main(String[] args) {
		Window janela = new Window(1024, 768);
		GameImage plano = new GameImage(URL.sprite("Menu.png")); 
		Keyboard teclado = janela.getKeyboard();
		ControleCenario cenario = new ControleCenario();
		
		while(true) {
			try {
				plano.draw();
				janela.update();
				
				if(teclado.keyDown(Keyboard.ENTER_KEY)) {
					cenario.controle(janela);
				} 
			}catch(Exception e) {
				System.out.println("FATAL ERROR!!");
			}
		}
	}
}
