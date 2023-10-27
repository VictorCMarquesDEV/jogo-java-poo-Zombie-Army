package mapConfig;

import controle.ControleJogo;
import entidades.Boss;
import entidades.Player;
import entidades.Zumbi;
import jogo.Som;
import jplay.Keyboard;
import jplay.Scene;
import jplay.URL;
import jplay.Window;

public class Cenario {

	protected Window janela;
	protected Scene cena;
	protected Player player;
	protected Keyboard teclado;
	protected Zumbi zumbi[];
	protected Boss boss;
	protected ControleJogo controleJogo;

	public Cenario(Window window, String filename, String filenameM) {
		janela = window;
		cena = new Scene();
		teclado = janela.getKeyboard();
		cena.loadFromFile(URL.scenario(filename));
		player = new Player(640, 350, 250);
		zumbi = new Zumbi[30];
		for (int i = 0; i < zumbi.length; i++) {
			zumbi[i] = new Zumbi(30000, 30000, "zumbi.png", 1500, 2);
		}
		boss = new Boss(30000, 30000, "boss.png", 6000, 100);
		controleJogo = new ControleJogo();
		this.run();

		Som.play(filenameM);
	}

	protected void run() {
		while (true) {

			player.controlar(janela, teclado);
			player.caminho(cena);

			cena.moveScene(player);

			player.x += cena.getXOffset();
			player.y += cena.getYOffset();

			player.draw();

			for (int i = 0; i < zumbi.length; i++) {
				zumbi[i].caminho(cena);
				zumbi[i].perseguir(player.x, player.y);
				zumbi[i].x += cena.getXOffset();
				zumbi[i].y += cena.getYOffset();
				zumbi[i].draw();
				player.atirar(janela, cena, teclado, zumbi[i], player);
				zumbi[i].atacar(player);
				zumbi[i].morrer();
				zumbi[i].aparecer(player);
				controleJogo.contKill(player, zumbi[i]);
			}

			boss.setKillSensitive(player.getKills());
			boss.caminho(cena);
			boss.perseguir(player.x, player.y);
			boss.x += cena.getXOffset();
			boss.y += cena.getYOffset();
			boss.draw();
			boss.atacar(player);
			boss.morrer();
			boss.aparecer(player);
			player.atirar(janela, cena, teclado, boss, player);
			player.morrer();
			controleJogo.displayKills(janela, player);
			controleJogo.displayEnergy(janela, player);
			controleJogo.gameOver(player);
			controleJogo.contKill(player, boss);
			controleJogo.exitGame(janela);
			janela.update();
		}
	}
}
