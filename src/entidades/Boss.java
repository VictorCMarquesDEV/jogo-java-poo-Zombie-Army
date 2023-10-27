package entidades;


public class Boss extends Inimigo {
	
	private int killSensitive;
	private int reLife;

	public Boss(int x, int y, String filename, double life, double ataque) {
		super(x, y, filename, life, ataque);
		this.x = x;
		this.y = y;
		this.setTotalDuration(2000);
		this.setVelocidade(0);
		this.setAtaque(ataque);
		this.reLife = (int) life;
	}

	@Override
	public void morrer() {
		if (this.getLife() <= 0) {
			this.setVelocidade(0);
			this.x = 10_000_000;
			this.setDirecao(0);
			this.setMovendo(false);
			this.setLife(reLife);
			this.morreu = 0;
		}
	}

	@Override
	public void atacar(Player jogador) {
		if (this.collided(jogador)) {
			jogador.setLife(jogador.getLife() - this.getAtaque());
		}
	}

	@Override
	public void aparecer(Player player) {
		int p = aleatorioP.nextInt(-300, 300);
		int dx = aleatorioDx.nextInt(-2, 2);
		int dy = aleatorioDy.nextInt(-2, 2);
		if (this.morreu != 1 && killSensitive % 5 == 0 && killSensitive != 0) {
			this.x = player.x + 50 + p * dx;
			this.y = player.y + 50 + p * dy;
			this.setMovendo(true);
			this.setVelocidade(0.6);
			this.setDirecao(3);
			this.morreu = 1;
		}
	}
	
	public void setKillSensitive(int killSensitive){
		this.killSensitive = killSensitive;
	}
}
