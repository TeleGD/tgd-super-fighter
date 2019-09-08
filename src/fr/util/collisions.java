package fr.util;

import fr.decor.Plateforms;
import fr.character.Player;

public class collisions {

	private static int margev = 0;

	public static boolean isCollisionplayer1plateform(Player player1, Plateforms plat, int delta) {

		if (player1.getspeedY() < 0) {
			return false;
		}
		if (player1.getY() + player1.getHeight() < plat.getY() - margev) {
			return false;
		}
		if (player1.getY() + player1.getHeight() > plat.getY() + margev) {
			return false;
		}
		if (player1.getX() + player1.getWidth() / 2 < plat.getX()) {
			return false;
		}
		if (player1.getX() + player1.getWidth() / 2 > plat.getX() + plat.getWidth()) {
			return false;
		}
		return true;
	}

	public static boolean isCollisionPlayerFromAbove(Player playerhaut,Player playerbas){
		if(playerhaut.speedY<=0){return false;}
		if((playerhaut.getY()+playerhaut.getHeight()<playerbas.getY())&&(playerhaut.getnewY()+playerhaut.getHeight()<playerbas.getnewY())){return false;}
		if((playerhaut.getY()>playerbas.getY()+playerbas.getHeight())&&(playerhaut.getnewY()>playerbas.getnewY()+playerbas.getHeight())){return false;}
		if((playerhaut.getX()+playerhaut.getWidth()<playerbas.getX())&&(playerhaut.getnewX()+playerhaut.getWidth()<playerbas.getnewX())){return false;}
		if((playerhaut.getX()>playerbas.getX()+playerbas.getWidth())&&(playerhaut.getnewX()>playerbas.getnewX()+playerbas.getWidth())){return false;}
		return true;
	}

}
