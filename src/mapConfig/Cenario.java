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

public class Cenario {

	protected Window janela;
	protected Scene cena;
	protected Player player;
	protected Keyboard teclado;
	protected ArrayList<Zumbi> zumbi = new ArrayList<Zumbi>();
	protected Boss boss;
	protected ControleJogo controleJogo;
	protected PocaoCura pocaoCura;

	public Cenario(Window window, String filename, String filenameM) {
		janela = window;
		cena = new Scene();
		teclado = janela.getKeyboard();
		cena.loadFromFile(URL.scenario(filename));
		player = new Player(640, 350, 250);
		pocaoCura = new PocaoCura("pocao.png",1,650,370);
		for (int i = 0; i < 30; i++) {
			zumbi.add(new Zumbi(30000, 30000, "zumbi.png", 1500, 2));
		}
		boss = new Boss(30000, 30000, "boss.png", 6000, 30);
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

			for (int i = 0; i < 30; i++) {
				zumbi.get(i).caminho(cena);
				zumbi.get(i).perseguir(player.x, player.y);
				zumbi.get(i).x += cena.getXOffset();
				zumbi.get(i).y += cena.getYOffset();
				zumbi.get(i).draw();
				player.atirar(janela, cena, teclado, zumbi.get(i), player);
				zumbi.get(i).atacar(player);
				zumbi.get(i).morrer();
				zumbi.get(i).aparecer(player);
				controleJogo.contKill(player, zumbi.get(i));
				pocaoCura.aparecer(zumbi.get(i), player);
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
}
