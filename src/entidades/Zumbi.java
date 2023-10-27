package entidades;

import controle.ControleTiro;

public class Zumbi extends Inimigo {
	
	ControleTiro tiros = new ControleTiro();

	public Zumbi(int x, int y, String filename, double life, double ataque) {
		super(x, y, filename, life, ataque);
		this.x = x;
		this.y = y;
		this.setTotalDuration(2000);
		this.setVelocidade(0);
		this.setAtaque(ataque);
	}

	@Override
	public void morrer() {
		if (this.life <= 0) {
			setVelocidade(0);
			this.x = 10_000_000;
			setDirecao(0);
			setMovendo(false);
		}
	}
	
	@Override
	public void aparecer(Player player) {
		int r = aleatorioS.nextInt(10000);
		int p = aleatorioP.nextInt(-300, 300);
		int dx = aleatorioDx.nextInt(-2, 2);
		int dy = aleatorioDy.nextInt(-2, 2);
		if (r == 30 && this.life > 0) {
			this.x = player.x + 50 + p * dx;
			this.y = player.y + 50 + p * dy;
			this.setMovendo(true);
			this.setVelocidade(0.4);
			this.setDirecao(3);
		}
	}
}
