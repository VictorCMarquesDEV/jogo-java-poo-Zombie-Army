package entidades;

import jplay.URL;
import jplay.Window;
import combate.PocaoCura;
import controle.ControleColisao;
import controle.ControleTiro;
import jplay.Keyboard;
import jplay.Scene;

public class Player extends Entidade implements Jogador {

	protected int kills = 0;

	ControleTiro tiros = new ControleTiro();

	public Player(int x, int y, double ataque) {
		super(URL.sprite("jogador.png"), 20, 2000);
		this.x = x;
		this.y = y;
		this.setTotalDuration(2000);
		this.ataque = ataque;
	}

	public void atirar(Window janela, Scene cena, Keyboard teclado, Zumbi zumbi, Player player) {
		if (teclado.keyDown(Keyboard.A_KEY)) {
			tiros.adicionaTiro(x, y, direcao, cena);
		}
		tiros.run(zumbi, player, cena);
	}

	public void atirar(Window janela, Scene cena, Keyboard teclado, Boss zumbi, Player player) {
		if (teclado.keyDown(Keyboard.A_KEY)) {
			tiros.adicionaTiro(x, y, direcao, cena);
		}
		tiros.run(zumbi, player, cena);
	}
	
	private boolean positionX() {
		if(this.x > 510 && this.x < 514 && ControleColisao.colisao(this, tile) == false) {
			return true;
		}else {
			return false;
		}
	}
	
	private boolean positionY() {
		if(this.y > 382 && this.y < 386 && ControleColisao.colisao(this, tile) == false) {
			return true;
		}else {
			return false;
		}
	}

	public void controlar(Window janela, Keyboard teclado, PocaoCura pocao) {

		if (teclado.keyDown(Keyboard.LEFT_KEY)) {
			if (this.x > 0) {
				this.x -= this.getVelocidade();
			}
			lado = 1;
			mover(lado);
			if(positionX()) {
				pocao.x += this.getVelocidade();
			}
		}
		
		if (teclado.keyDown(Keyboard.RIGHT_KEY)) {
			if (this.x < janela.getWidth() - 45) {
				this.x += this.getVelocidade();
			}
			lado = 2;
			mover(lado);
			if(positionX()) {
				pocao.x -= this.getVelocidade();
			}
		}
		
		if (teclado.keyDown(Keyboard.UP_KEY)) {
			if (this.y > 0) {
				this.y -= this.getVelocidade();
			}
			lado = 4;
			mover(lado);
			if(positionY()) {
				pocao.y += this.getVelocidade();
			}
		}
		
		if (teclado.keyDown(Keyboard.DOWN_KEY)) {
			if (this.y < janela.getHeight() - 48) {
				this.y += this.getVelocidade();
			}
			lado = 5;
			mover(lado);
			if(positionY()) {
				pocao.y -= this.getVelocidade();
			}
		}
		
		if (movendo) {
			update();
			this.setMovendo(false);
		}

	}

	@Override
	public void mover(int lado) {
		if (direcao != 1 && lado == 1) {
			this.setSequence(4, 8);
			this.setDirecao(1);
		}
		if (direcao != 2 && lado == 2) {
			this.setSequence(8, 12);
			this.setDirecao(2);
		}
		if (direcao != 4 && lado == 4) {
			this.setSequence(12, 16);
			this.setDirecao(4);
		}
		if (direcao != 5 && lado == 5) {
			this.setSequence(0, 4);
			this.setDirecao(5);
		}
		this.setMovendo(true);
	}

	@Override
	public void morrer() {
		if (getLife() <= 0) {
			this.setVelocidade(0);
			this.setMovendo(false);
		}
	}

	public int getKills() {
		return kills;
	}

	public void setKills(int kills) {
		this.kills = kills;
	}

	public ControleTiro getTiros() {
		return tiros;
	}

	public void setTiros(ControleTiro tiros) {
		this.tiros = tiros;
	}
}
