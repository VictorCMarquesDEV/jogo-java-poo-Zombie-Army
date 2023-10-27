package entidades;

import java.awt.Point;
import java.util.Vector;

import controle.ControleColisao;
import jplay.GameObject;
import jplay.Scene;
import jplay.Sprite;
import jplay.TileInfo;

public abstract class Entidade extends Sprite {

	protected double velocidade = 1;
	protected int direcao = 3;
	protected boolean movendo = false;
	protected double ataque;
	protected double life;
	protected int lado;

	TileInfo tile = new TileInfo();
	ControleColisao controle = new ControleColisao();

	protected Entidade(String fileName, int numFrames, double life) {
		super(fileName, numFrames);
		this.setLife(life);
	}

	public void caminho(Scene cena) {
		Point min = new Point((int) this.x, (int) this.y);
		Point max = new Point((int) this.x + this.width, (int) this.y + this.height);

		Vector<?> tiles = cena.getTilesFromPosition(min, max);

		for (int i = 0; i < tiles.size(); i++) {
			tile = (TileInfo) tiles.elementAt(i);
		}

		if (ControleColisao.colisao(this, tile) == true) {
			if (colisaoVertical(this, tile)) {
				if (tile.y + tile.height - 2 < this.y) {
					this.y = tile.y + tile.height;
				} else if (tile.y > this.y + this.height - 2) {
					this.y = tile.y - this.height;
				}
			}
			if (colisaoHorizontal(this, tile)) {
				if (tile.x > this.x + this.width - 2) {
					this.x = tile.x - this.width;
				} else {
					this.x = tile.x + tile.width;
				}
			}
		}
	}

	public boolean colisaoVertical(GameObject obj, GameObject obj2) {
		if (obj2.x + obj2.width <= obj.x) {
			return false;
		}
		if (obj.x + obj.width <= obj2.x) {
			return false;
		}
		return true;
	}

	public boolean colisaoHorizontal(GameObject obj, GameObject obj2) {
		if (obj2.y + obj2.height <= obj.y) {
			return false;
		}
		if (obj.y + obj.height <= obj2.y) {
			return false;
		}
		return true;
	}

	public abstract void mover(int lado);

	public abstract void morrer();

	public double getVelocidade() {
		return velocidade;
	}

	public void setVelocidade(double velocidade) {
		this.velocidade = velocidade;
	}

	public int getDirecao() {
		return direcao;
	}

	public void setDirecao(int direcao) {
		this.direcao = direcao;
	}

	public boolean getMovendo() {
		return movendo;
	}

	public void setMovendo(boolean movendo) {
		this.movendo = movendo;
	}

	public TileInfo getTile() {
		return tile;
	}

	public void setTile(TileInfo tile) {
		this.tile = tile;
	}

	public ControleColisao getControle() {
		return controle;
	}

	public void setControle(ControleColisao controle) {
		this.controle = controle;
	}
	
	public double getLife() {
		return life;
	}

	public void setLife(double life) {
		this.life = life;
	}

	public double getAtaque() {
		return ataque;
	}

	public void setAtaque(double ataque) {
		this.ataque = ataque;
	}

}
