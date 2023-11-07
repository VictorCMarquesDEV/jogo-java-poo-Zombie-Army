package controle;

import java.util.LinkedList;

import combate.Projetil;
import entidades.Boss;
import entidades.Player;
//import entidades.Entidade;
import entidades.Zumbi;
import jplay.Scene;
import jplay.Sound;
import jplay.URL;

public class ControleTiro {

	LinkedList<Projetil> tiros = new LinkedList<>();
	public Projetil tiro;

	public void adicionaTiro(double x, double y, int caminho, Scene cena) {
		try {
			tiro = new Projetil(x, y, caminho);
			tiros.addFirst(tiro);
			cena.addOverlay(tiro);
		}catch(Exception e) {
			System.out.println("OUT OF MEMORY");
		}
		somDisparo();
	}

	public void run(Zumbi inimigo, Player player) {
		for (int i = 0; i < tiros.size(); i++) {
			Projetil tiro = tiros.removeFirst();
			tiro.mover();
			tiro.estatico(player);
			tiros.addLast(tiro);
			
			if(tiro.collided(inimigo)) {
				tiro.x=10_000_000;
				tiro.y=10_000_000;
				inimigo.setLife(inimigo.getLife()-player.getAtaque());
			}
		}
	}
	
	public void run(Boss inimigo, Player player) {
		for (int i = 0; i < tiros.size(); i++) {
			Projetil tiro = tiros.removeFirst();
			tiro.mover();
			tiro.estatico(player);
			tiros.addLast(tiro);
			
			if(tiro.collided(inimigo)) {
				tiro.x=10_000_000;
				tiro.y=10_000_000;
				inimigo.setLife(inimigo.getLife()-player.getAtaque());
			}
		}
	}
	
	public void run(Player inimigo, Boss player) {
		for (int i = 0; i < tiros.size(); i++) {
			Projetil tiro = tiros.removeFirst();
			tiro.mover();
			tiro.estatico(inimigo);
			tiros.addLast(tiro);
			
			if(tiro.collided(inimigo)) {
				tiro.x=10_000_000;
				tiro.y=10_000_000;
				inimigo.setLife(inimigo.getLife()-player.getAtaque());
			}
		}
	}

	private void somDisparo() {
		new Sound(URL.audio("tiro.wav")).play();
	}
}
