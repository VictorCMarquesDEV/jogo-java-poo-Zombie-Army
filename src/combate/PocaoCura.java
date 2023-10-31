package combate;

import java.util.Random;

import controle.ControleColisao;
import entidades.Player;
import entidades.Zumbi;
import jplay.Sprite;
import jplay.URL;

public class PocaoCura extends Sprite{
	
	private double cura = 50;
	
	Random aleatorioDx = new Random();
	Random aleatorioDy = new Random();
	
	ControleColisao controle = new ControleColisao();
	
	public PocaoCura(String filename, int numFrames, double x, double y) {
		super(URL.sprite(filename), numFrames);
		this.x = x;
		this.y = y;
	}

	public void curar(Player player) {
		if(ControleColisao.colisao(player, this)) {
			this.x = 10_000_000;
			this.y = 10_000_000;
			player.setLife(player.getLife() + cura);
		}
	}
	
	public void aparecer(Zumbi zumbi, Player player) {
		int dx = aleatorioDx.nextInt(-3, 3);
		int dy = aleatorioDy.nextInt(-3, 3);
		if(zumbi.getCurou() == 0 && zumbi.getMorreu() == 1) {
			this.x = player.getX() + 50*dx;
			this.y = player.getY() + 30*dy;
			zumbi.setCurou(1);
		}
	}
	
	
}
