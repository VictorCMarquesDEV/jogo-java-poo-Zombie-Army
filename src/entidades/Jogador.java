package entidades;

import jplay.Keyboard;
import jplay.Scene;
import jplay.Window;

public interface Jogador {

	public abstract void morrer();

	public abstract void mover(int lado);
	
	public abstract void controlar(Window janela, Keyboard teclado);
	
	public abstract void atirar(Window janela, Scene cena, Keyboard teclado, Zumbi zumbi, Player player);
	public abstract void atirar(Window janela, Scene cena, Keyboard teclado, Boss zumbi, Player player);

}
