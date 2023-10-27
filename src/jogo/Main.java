package jogo;

import jplay.GameImage;
import jplay.Keyboard;
import jplay.URL;
import jplay.Window;
import mapConfig.Cenario;

public class Main {

	public static void main(String[] args) {
		Window janela = new Window(1024, 768);
		GameImage plano = new GameImage(URL.sprite("Menu.png"));
		Keyboard teclado = janela.getKeyboard();
		
		while(true) {
			plano.draw();
			janela.update();
			
			if(teclado.keyDown(Keyboard.ENTER_KEY)) {
				new Cenario(janela, "Cenario1.scn", "musicaFundo.wav");
			} 
		}
	}
}
