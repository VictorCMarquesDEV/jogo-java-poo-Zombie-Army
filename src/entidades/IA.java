package entidades;

public interface IA {
	public abstract void perseguir(double x, double y);
	
	public abstract void atacar(Player jogador);

	public abstract void aparecer(Player player);
}
