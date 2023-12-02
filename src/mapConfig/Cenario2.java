package mapConfig;

import java.util.ArrayList;

import combate.PocaoCura;
import controle.ControleJogo;
import entidades.Boss;
import entidades.Player;
import entidades.Zumbi;
import jogo.Som;
import jplay.Keyboard;
import jplay.Scene;
import jplay.URL;
import jplay.Window;

public class Cenario2 {

	protected Window janela;
	protected Scene cena;
	protected Player player;
	protected Keyboard teclado;
	protected ArrayList<Zumbi> zumbi = new ArrayList<Zumbi>();
	protected Boss boss;
	protected ControleJogo controleJogo;
	protected PocaoCura pocaoCura;
	private final int QTTZUMBI = 30;

	public Cenario2(java.awt.Window janela2, String filename, String filenameM) {
		janela = (Window) janela2;
		cena = new Scene();
		teclado = janela.getKeyboard();
		cena.loadFromFile(URL.scenario(filename));
		player = new Player(640, 350, 250);
		pocaoCura = new PocaoCura("pocao.png", 1, 650, 370);
		for (int i = 0; i < QTTZUMBI; i++) {
			zumbi.add(new Zumbi(30000, 30000, "zumbi.png", 1500, 0));
		}
		boss = new Boss(30000, 30000, "boss.png", 6000, 0);
		controleJogo = new ControleJogo();
		this.run();

		Som.play(filenameM);
	}

	protected void run() {
		while (true) {

			player.controlar(janela, teclado);
			pocaoCura.estatico(player);

			cena.moveScene(player);

			player.x += cena.getXOffset();
			player.y += cena.getYOffset();

			player.draw();
			pocaoCura.draw();

			for (Zumbi i : zumbi) {
				i.caminho(cena);
				i.perseguir(player.x, player.y);
				i.x += cena.getXOffset();
				i.y += cena.getYOffset();
				i.draw();
				player.atirar(janela, cena, teclado, i, player);
				i.atacar(player);
				i.morrer();
				i.aparecer(player);
				controleJogo.contKill(player, i);
				pocaoCura.aparecer(i, player);
			}

			boss.setKillSensitive(player.getKills());
			boss.caminho(cena);
			boss.perseguir(player.x, player.y);
			boss.atirar(janela, cena, teclado, boss, player);
			boss.x += cena.getXOffset();
			boss.y += cena.getYOffset();
			boss.draw();
			boss.atacar(player);
			boss.morrer();
			controleCenario();
			boss.aparecer(player);
			player.atirar(janela, cena, teclado, boss, player);
			player.morrer();
			pocaoCura.curar(player);
			controleJogo.displayKills(janela, player);
			controleJogo.displayEnergy(janela, player);
			controleJogo.gameOver(player);
			controleJogo.contKill(player, boss);
			controleJogo.exitGame(janela);
			player.imovel();
			player.caminho(cena);
			janela.update();
		}
	}
	
	private void controleCenario() {
		if(boss.getLife() <= 0) {
			new Cenario3(janela, "Cenario3.scn", "musicafundo.wav");
		}
	}
}