package entidades;

import java.util.Random;

import jplay.URL;

public abstract class Inimigo extends Entidade implements IA{
	
	Random aleatorioS = new Random();
	Random aleatorioP = new Random();
	Random aleatorioDx = new Random();
	Random aleatorioDy = new Random();
	public int morreu = 0;
	
	public Inimigo(int x, int y, String filename, double life, double ataque) {
		super(URL.sprite(filename), 16, life);
		this.x = x;
		this.y = y;
		this.setTotalDuration(2000);
		this.setVelocidade(0);
		this.setAtaque(ataque);
	}
	
	public void perseguir(double x, double y) {
		if (this.x > x && this.y <= y + 50 && this.y >= y - 50) {
			moveTo(x, y, velocidade);
			lado = 1;
			mover(lado);
		}

		else if (this.x < x && this.y <= y + 50 && this.y >= y - 50) {
			moveTo(x, y, velocidade);
			lado = 2;
			mover(lado);
		}

		else if (this.y < y) {
			moveTo(x, y, velocidade);
			lado = 3;
			mover(lado);
		}

		else if (this.y > y) {
			moveTo(x, y, velocidade);
			lado = 4;
			mover(lado);
		}

		if (movendo) {
			update();
			setMovendo(false);
		}
	}
	
	public void atacar(Player jogador) {
		if (this.collided(jogador)) {
			jogador.setLife(jogador.getLife() - this.getAtaque());
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

	public abstract void aparecer(Player player);
	
	public double getMorreu() {
		return this.morreu;
	}

	public void setMorreu(int morreu) {
		this.morreu = morreu;
	}
}
