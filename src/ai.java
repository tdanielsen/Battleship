import java.util.Random;


public class ai
{
	private BattleMap map;
	private boolean lastHit = false;
	private int[] lastGuess = new int[2];
	private int[] firstHit = new int[2];
	private int counter = 0;
	private int direction = 0; // East = 1, South = 2, West = 3, North 4
	
	public ai(BattleMap oceanMap)
	{
		map = oceanMap;
		
	}
	
	public void placeShip(int size, String shipName)
	{
		Random rd = new Random();
		int row = 0;
		int column = 0;
		int randNum = rd.nextInt(2);
		if (randNum == 0)
		{
			column = rd.nextInt(10) + 1;
			row = rd.nextInt(10 - size) + 1;
			if (map.okPlacementColumn(column, row, row + (size -1)))
			{
				map.addShip(column, row, column, row + (size -1), shipName, size);
			}
			else
			{
				this.placeShip(size, shipName);
			}
		}
		if (randNum == 1)
		{
			column = rd.nextInt(10 - size) + 1;
			row = rd.nextInt(10) + 1;
			if (map.okPlacementRow(column, row, column + (size -1)))
			{
				map.addShip(column, row, column + (size -1), row, shipName, size);
			}
			else
			{
				this.placeShip(size, shipName);
			}
		}
	}
	public void makeGuess(BattleMap map)
	{
		Random rd = new Random();
		int row = rd.nextInt(10) + 1;
		int column = rd.nextInt(10) + 1;
		if (direction != 0)
		{
			direction = continueGuess(map, lastGuess[0], lastGuess[1], direction);
			
		}
		if (lastHit == false)
		{
			if (map.checkGuess(column, row) == " 0")
			{
				this.makeGuess(map);
			}
			if (map.checkGuess(column, row) == " -")
			{
				map.miss(column, row);
				lastHit = false;
			}
			else
			{
				if (map.hit(column, row))
				{
					lastHit = false;
				}
				else
				{
					lastHit = true;
				}
				lastGuess[0] = column;
				lastGuess[1] = row;
				firstHit[0] = column;
				firstHit[1] = row;				
			}
		}
		if (lastHit == true)
		{
			if (lastGuess[0] + 1 <= 10)
			{
			// make 2 guess methods
				if (counter == 0)
				{
					if (map.checkGuess(lastGuess[0] + 1, lastGuess[1]) == " 0")
					{
						this.makeGuess(map);
					}
					if (map.checkGuess(lastGuess[0] + 1, lastGuess[1]) == " -")
					{
						map.miss(lastGuess[0] + 1, lastGuess[1]);
					}
					else
					{
						if (map.hit(lastGuess[0] + 1, lastGuess[1]))
						{
							lastHit = false;
						}
						else
						{
							lastHit = true;
							direction = 1;
						}
						lastGuess[0] = lastGuess[0] + 1;
//						lastGuess[1] = lastGuess[1];					
					}
					counter++;
				}
			}
			if (lastGuess[1] + 1 <= 10)
			{
				if (counter == 1)
				{
					if (map.checkGuess(lastGuess[0], lastGuess[1] + 1) == " 0")
					{
						this.makeGuess(map);
					}
					if (map.checkGuess(lastGuess[0], lastGuess[1] + 1) == " -")
					{
						map.miss(lastGuess[0], lastGuess[1] + 1);
					}
					else
					{
						if (map.hit(lastGuess[0], lastGuess[1] + 1))
						{
							lastHit = false;
						}
						else
						{
							lastHit = true;
							direction = 2;
						}
//						lastGuess[0] = lastGuess[0];
						lastGuess[1] = lastGuess[1] + 1;					
					}
				}
			}
			if (lastGuess[0] - 1 >= 1)
			{
				if (counter == 2)
				{
					if (map.checkGuess(lastGuess[0] - 1, lastGuess[1]) == " 0")
					{
						this.makeGuess(map);
					}
					if (map.checkGuess(lastGuess[0] - 1, lastGuess[1]) == " -")
					{
						map.miss(lastGuess[0] - 1, lastGuess[1]);
					}
					else
					{
						if (map.hit(lastGuess[0] - 1, lastGuess[1]))
						{
							lastHit = false;
						}
						else
						{
							lastHit = true;
							direction = 3;
						}
						lastGuess[0] = lastGuess[0] - 1;
//						lastGuess[1] = lastGuess[1];					
					}
				}
			}
			if (lastGuess[1] - 1 >= 1)
			{
				if (counter == 3)
				{
					if (map.checkGuess(lastGuess[0], lastGuess[1] - 1) == " 0")
					{
						this.makeGuess(map);
					}
					if (map.checkGuess(lastGuess[0], lastGuess[1] - 1) == " -")
					{
						map.miss(lastGuess[0], lastGuess[1] - 1);
					}
					else
					{
						if (map.hit(lastGuess[0], lastGuess[1] - 1))
						{
							lastHit = false;
						}
						else
						{
							lastHit = true;
							direction = 4;
						}
//						lastGuess[0] = lastGuess[0];
						lastGuess[1] = lastGuess[1] - 1;					
					}
				}
			}
			else
			{
				this.makeGuess(map);
			}
		}
		
	}
	public int continueGuess(BattleMap map, int column, int row, int direction)
	{
		if (direction == 1)
		{
			if (map.checkGuess(column + 1, row) == " -")
			{
				map.miss(column + 1, row);
				lastHit = false;
			}
			if (map.checkGuess(column + 1, row) == " 0" || map.checkGuess(column + 1, row) == " X")
			{
				//go back to first hit
				direction = 3;
				column = firstHit[0];
				row = firstHit[1];
				continueGuess(map, column, row, direction);
			}
			else
			{
				if (map.hit(column + 1, row))
				{
					lastHit = false;
				}
				else
				{
					lastHit = true;
					return direction;
				}
			}
		}
		if (direction == 2)
		{
			if (map.checkGuess(column, row + 1) == " -")
			{
				map.miss(column, row + 1);
				lastHit = false;
			}
			if (map.checkGuess(column, row + 1) == " 0" || map.checkGuess(column, row + 1) == " X")
			{
				//go back to first hit
				direction = 4;
				column = firstHit[0];
				row = firstHit[1];
				continueGuess(map, column, row, direction);
			}
			else
			{
				if (map.hit(column, row + 1))
				{
					lastHit = false;
				}
				else
				{
					lastHit = true;
					return direction;
				}
			}
		}
		if (direction == 3)
		{
			if (map.checkGuess(column - 1, row) == " -")
			{
				map.miss(column - 1, row);
				lastHit = false;
			}
			if (map.checkGuess(column - 1, row) == " 0" || map.checkGuess(column - 1, row) == " X")
			{
				//go back to first hit
				direction = 1;
				column = firstHit[0];
				row = firstHit[1];
				continueGuess(map, column, row, direction);
			}
			else
			{
				if (map.hit(column - 1, row))
				{
					lastHit = false;
				}
				else
				{
					lastHit = true;
					return direction;
				}
			}
		}
		if (direction == 4)
		{
			if (map.checkGuess(column, row - 1) == " -")
			{
				map.miss(column, row - 1);
				lastHit = false;
			}
			if (map.checkGuess(column, row - 1) == " 0" || map.checkGuess(column, row - 1) == " X")
			{
				//go back to first hit
				direction = 2;
				column = firstHit[0];
				row = firstHit[1];
				continueGuess(map, column, row, direction);
			}
			else
			{
				if (map.hit(column, row - 1))
				{
					lastHit = false;
				}
				else
				{
					lastHit = true;
					return direction;
				}
			}
		}
		return 0;
		
	}
}
