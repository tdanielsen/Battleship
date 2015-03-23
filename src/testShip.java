import java.util.Random;


public class testShip
{

	public static void main(String[] args)
	{
		BattleMap newMap = new BattleMap();
		newMap.resetMap();
		ai newai = new ai(newMap);
		newai.placeShip(5, " A");
		newai.placeShip(4, " B");
		newai.placeShip(3, " D");
		newai.placeShip(3, " S");
		newai.placeShip(2, " P");
//		newMap.addShip(1, 1, 1, 4, " B", 4);
//		newMap.addShip(2, 3, 4, 3, " S", 3);
//		newMap.addShip(5, 5, 5, 6, " P", 2);
		newMap.printMap();
		for (int i = 0; i < 10; i++)
		{
			newai.makeGuess(newMap);
		}	
		newMap.printMap();
		Random rd = new Random();
		int randNum = rd.nextInt(10) + 1;
		System.out.println(randNum);
	}

}
