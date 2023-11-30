package combate;

import java.util.Random;

import controle.ControleColisao;
import entidades.Player;
import entidades.Zumbi;
import jplay.Sprite;
import jplay.TileInfo;
import jplay.URL;

public class PocaoCura extends Sprite{
	
	private double cura = 50;
	private double heber;
	
	Random aleatorioDx = new Random();
	Random aleatorioDy = new Random();
	
	ControleColisao controle = new ControleColisao();
	TileInfo tile = new TileInfo();
	
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
	
	public void estatico(Player player) {
		if(player.getMovendo()) {
			if(player.x > 510 && player.x < 514 && ControleColisao.colisao(player, player.getTile()) == false) {
				if(player.getDirecao() == 1) {
					this.x += player.getVelocidade();
				} 
				if(player.getDirecao() == 2) {
					this.x -= player.getVelocidade();
				}
			}
			if(player.y > 382 && player.y < 386 && ControleColisao.colisao(player, player.getTile()) == false) {
				if(player.getDirecao() == 4) {
					this.y += player.getVelocidade();
				} 
				if(player.getDirecao() == 5) {
					this.y -= player.getVelocidade();
				}
			}
		}
	}
}
