import java.util.Random;


public class ai
{
	private BattleMap map;
	private boolean lastHit = false;
	private int[] lastGuess = new int[2];
	private int[] firstHit = new int[2];
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
		//Vertical ship
		if (randNum == 0)
		{
			column = rd.nextInt(10) + 1;
			row = rd.nextInt(10 - size) + 1;
			if (map.okPlacementColumn(row, column, row + (size -1)))
			{
				map.addShip(row, column, row + (size -1), column, shipName, size);
			}
			else
			{
				this.placeShip(size, shipName);
			}
		}
		//Horizontal Ship
		if (randNum == 1)
		{
			column = rd.nextInt(10 - size) + 1;
			row = rd.nextInt(10) + 1;
			if (map.okPlacementRow(row, column, column + (size -1)))
			{
				map.addShip(row, column, row, column + (size -1), shipName, size);
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
//		if (direction != 0)
//		{
//			direction = continueGuess(map, lastGuess[0], lastGuess[1], direction);
//			
//		}
		if (lastHit == true)
		{
			continueGuess(map, lastGuess[0], lastGuess[1]);
		}
		else if (lastHit == false)
		{
			if (map.checkGuess(row, column) == " 0" || map.checkGuess(row, column) == " X"
					|| map.reasonableGuess(row, column) == false)
			{
				this.makeGuess(map);
			}
			else if (map.checkGuess(row, column) == " -")
			{
				map.miss(row, column);
				lastHit = false;
			}
			else
			{
				if (map.hit(row, column, 0))
				{
					lastHit = false;
				}
				else
				{
					lastHit = true;
					lastGuess[0] = row;
					lastGuess[1] = column;
					firstHit[0] = row;
					firstHit[1] = column;
					direction = 1;
				}
								
			}
		}

//		if (lastHit == true)
//		{
//			if (lastGuess[0] + 1 <= 10)
//			{
//			// make 2 guess methods
//				if (counter == 0)
//				{
//					if (map.checkGuess(lastGuess[0] + 1, lastGuess[1]) == " 0")
//					{
//						this.makeGuess(map);
//					}
//					if (map.checkGuess(lastGuess[0] + 1, lastGuess[1]) == " -")
//					{
//						map.miss(lastGuess[0] + 1, lastGuess[1]);
//					}
//					else
//					{
//						if (map.hit(lastGuess[0] + 1, lastGuess[1]))
//						{
//							lastHit = false;
//						}
//						else
//						{
//							lastHit = true;
//							direction = 1;
//						}
//						lastGuess[0] = lastGuess[0] + 1;
////						lastGuess[1] = lastGuess[1];					
//					}
//					counter++;
//				}
//			}
//			if (lastGuess[1] + 1 <= 10)
//			{
//				if (counter == 1)
//				{
//					if (map.checkGuess(lastGuess[0], lastGuess[1] + 1) == " 0")
//					{
//						this.makeGuess(map);
//					}
//					if (map.checkGuess(lastGuess[0], lastGuess[1] + 1) == " -")
//					{
//						map.miss(lastGuess[0], lastGuess[1] + 1);
//					}
//					else
//					{
//						if (map.hit(lastGuess[0], lastGuess[1] + 1))
//						{
//							lastHit = false;
//						}
//						else
//						{
//							lastHit = true;
//							direction = 2;
//						}
////						lastGuess[0] = lastGuess[0];
//						lastGuess[1] = lastGuess[1] + 1;					
//					}
//				}
//			}
//			if (lastGuess[0] - 1 >= 1)
//			{
//				if (counter == 2)
//				{
//					if (map.checkGuess(lastGuess[0] - 1, lastGuess[1]) == " 0")
//					{
//						this.makeGuess(map);
//					}
//					if (map.checkGuess(lastGuess[0] - 1, lastGuess[1]) == " -")
//					{
//						map.miss(lastGuess[0] - 1, lastGuess[1]);
//					}
//					else
//					{
//						if (map.hit(lastGuess[0] - 1, lastGuess[1]))
//						{
//							lastHit = false;
//						}
//						else
//						{
//							lastHit = true;
//							direction = 3;
//						}
//						lastGuess[0] = lastGuess[0] - 1;
////						lastGuess[1] = lastGuess[1];					
//					}
//				}
//			}
//			if (lastGuess[1] - 1 >= 1)
//			{
//				if (counter == 3)
//				{
//					if (map.checkGuess(lastGuess[0], lastGuess[1] - 1) == " 0")
//					{
//						this.makeGuess(map);
//					}
//					if (map.checkGuess(lastGuess[0], lastGuess[1] - 1) == " -")
//					{
//						map.miss(lastGuess[0], lastGuess[1] - 1);
//					}
//					else
//					{
//						if (map.hit(lastGuess[0], lastGuess[1] - 1))
//						{
//							lastHit = false;
//						}
//						else
//						{
//							lastHit = true;
//							direction = 4;
//						}
////						lastGuess[0] = lastGuess[0];
//						lastGuess[1] = lastGuess[1] - 1;					
//					}
//				}
//			}
//			else
//			{
//				this.makeGuess(map);
//			}
//		}
		
	}
	public int continueGuess(BattleMap map, int row, int column)
	{
		if (direction == 1)
		{
			if (column + 1 >= 11)
			{
				direction++;
				continueGuess(map, row, column);
			}
			else if (map.checkGuess(row, column + 1) == " -")
			{
				map.miss(row, column + 1);
				direction = 3;
				lastGuess[0] = firstHit[0];
				lastGuess[1] = firstHit[1];		
			}
			else if (map.checkGuess(row, column + 1) == " 0" || map.checkGuess(row, column + 1) == " X")
			{
				//go back to first hit
				direction++;
				row = firstHit[0];
				column = firstHit[1];
				continueGuess(map, row, column);
			}
			else
			{
				if (map.hit(row, column + 1, 0))
				{
					lastHit = false;
				}
				else
				{
					lastHit = true;
					lastGuess[1] = column + 1;
					return direction;
				}
			}
		}
		else if (direction == 2)
		{
			if (row + 1 >= 11)
			{
				direction++;
				continueGuess(map, row, column);
			}
			else if (map.checkGuess(row + 1, column) == " -")
			{
				map.miss(row + 1, column);
				direction = 4;
				lastGuess[0] = firstHit[0];
				lastGuess[1] = firstHit[1];
			}
			else if (map.checkGuess(row  + 1, column) == " 0" || map.checkGuess(row + 1, column) == " X")
			{
				//go back to first hit
				direction++;
				row = firstHit[0];
				column = firstHit[1];
				continueGuess(map, row, column);
			}
			else
			{
				if (map.hit(row + 1, column, 0))
				{
					lastHit = false;
				}
				else
				{
					lastHit = true;
					lastGuess[0] = row + 1;
					return direction;
				}
			}
		}
		else if (direction == 3)
		{
			if (column - 1 <= 0)
			{
				direction++;
				continueGuess(map, row, column);
			}
			else if (map.checkGuess(row, column - 1) == " -")
			{
				map.miss(row, column - 1);
				direction = 1;
				lastGuess[0] = firstHit[0];
				lastGuess[1] = firstHit[1];
			}
			else if (map.checkGuess(row, column - 1) == " 0" || map.checkGuess(row, column - 1) == " X")
			{
				//go back to first hit
				direction++;
				row = firstHit[0];
				column = firstHit[1];
				continueGuess(map, row, column);
			}
			else
			{
				if (map.hit(row, column - 1, 0))
				{
					lastHit = false;
				}
				else
				{
					lastHit = true;
					lastGuess[1] = column - 1;
					return direction;
				}
			}
		}
		else if (direction == 4)
		{
			if (row - 1 <= 0)
			{
				direction = 1;
				continueGuess(map, row, column);
			}
			else if (map.checkGuess(row - 1, column) == " -")
			{
				map.miss(row - 1, column);
				direction = 2;
				lastGuess[0] = firstHit[0];
				lastGuess[1] = firstHit[1];
			}
			else if (map.checkGuess(row - 1, column) == " 0" || map.checkGuess(row - 1, column) == " X")
			{
				//go back to first hit
				direction = 1;
				row = firstHit[0];
				column = firstHit[1];
				continueGuess(map, row, column);
			}
			else
			{
				if (map.hit(row - 1, column, 0))
				{
					lastHit = false;
				}
				else
				{
					lastHit = true;
					lastGuess[0] = row - 1;
					return direction;
				}
			}
		}
		return 0;
		
	}
}
