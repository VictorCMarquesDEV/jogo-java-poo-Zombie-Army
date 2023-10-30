package controle;

import jplay.GameObject;
import jplay.TileInfo;

public class ControleColisao {

	public static boolean colisao(GameObject obj, TileInfo tile) {

		if (tile.id >= 12 && obj.collided(tile)) {
			return true;
		} else {
			return false;
		}
	}
}
