package controle;

import java.awt.Color;
import java.awt.Font;

import entidades.Boss;
import entidades.Player;
import entidades.Zumbi;
import jogo.Som;
import jplay.GameImage;
import jplay.Keyboard;
import jplay.URL;
import jplay.Window;

public class ControleJogo {

	private Font f = new Font("arial", Font.BOLD, 30);
	private Color c = new Color(255, 0, 0);
	private boolean death = false;
	GameImage plano = new GameImage(URL.sprite("gameOver.png"));

	public void gameOver(Player player) {
		if (player.getLife() <= 0) {
			plano.draw();
			c = Color.BLACK;
			Som.stop();
			death = true;
		}
	}

	public void contKill(Player player, Boss boss) {
		if (boss.getLife() <= 0 && boss.getMorreu() == 0) {
			player.setKills(player.getKills() + 1);
			boss.setMorreu(1);
		}
	}
	
	public void contKill(Player player, Zumbi zumbi) {
		if (zumbi.getLife() <= 0 && zumbi.getMorreu() == 0) {
			player.setKills(player.getKills() + 1);
			zumbi.setMorreu(1);
		}
	}
	
	public void displayEnergy(Window janela, Player player) {
		janela.drawText("Vida: " + player.getLife(), 50, 50, c, f);
	}

	public void displayKills(Window janela, Player player) {
		janela.drawText("Mortos: " + player.getKills(), 300, 50, c, f);
	}
	
	public void exitGame(Window janela) {
		Keyboard teclado = janela.getKeyboard();
		if(death == true && teclado.keyDown(Keyboard.M_KEY)){
			System.exit(0);
		}
	}
}
