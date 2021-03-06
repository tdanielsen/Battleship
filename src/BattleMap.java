public class BattleMap
{
	private String[][] map = new String[11][11]; //The first is row, second is column
	private int aircraftHp = 5;
	private int battleshipHp = 4;
	private int destroyerHp = 3;
	private int subHp = 3;
	private int patrolHp = 2;
	public BattleMap()
	{
		resetMap();
	}
	public void resetMap()
	{
		for (int i = 0; i < 11; i++)
		{
			for (int j = 0; j < 11; j++)
			{
				if (i == 0 && j == 0)
				{
					map[i][j] = "  ";
				}
				else if (i == 0)
				{
					if (j == 1)
					{
						map[i][j] = " A";
					}
					if (j == 2)
					{
						map[i][j] = " B";
					}
					if (j == 3)
					{
						map[i][j] = " C";
					}
					if (j == 4)
					{
						map[i][j] = " D";
					}
					if (j == 5)
					{
						map[i][j] = " E";
					}
					if (j == 6)
					{
						map[i][j] = " F";
					}
					if (j == 7)
					{
						map[i][j] = " G";
					}
					if (j == 8)
					{
						map[i][j] = " H";
					}
					if (j == 9)
					{
						map[i][j] = " I";
					}
					if (j == 10)
					{
						map[i][j] = " J";
					}
					if (j == 11)
					{
						map[i][j] = " K";
					}
				}
				else if (j == 0)
				{
					if (i == 10)
					{
						map[i][j] = "" + i;
					}
					else
					{
						map[i][j] = " " + i;
					}
				}
				else
				{
					map[i][j] = " -";
				}
			}
		}
	}
	public void addShip(int startR, int startC, int endR, int endC, String ship, int shipSize)
	{
		//Vertical
		if (startC - endC == 0)
		{
			if (Math.abs(startR - endR) == shipSize - 1)
			{
				if (endR > startR)
				{
					for (int i = startR; i <= endR; i++)
					{
						if (map[i][startC] == " -")
						{
							map[i][startC] = ship;
						}
						else
						{
							System.out.println("Error: Illegal placement of ship @ " + startR + ", " + startC);
							break;
						}
					}
				}
				else
				{
					for (int i = endR; i <= startR; i++)
					{
						if (map[i][startC] == " -")
						{
							map[i][startC] = ship;
						}
						else
						{
							System.out.println("Error: Illegal placement of ship @ " + startR + ", " + startC);
							break;
						}
					}
				}
			}
		}
		//Horizontal 
		if (startR - endR == 0)
		{
			if (Math.abs(startC - endC) == shipSize - 1)
			{
				if (endC > startC)
				{
					for (int i = startC; i <= endC; i++)
					{
						if (map[startR][i] == " -")
						{
							map[startR][i] = ship;
						}
						else
						{
							System.out.println("Error: Illegal placement of ship @ " + startR + ", " + startC);
							break;
						}
					}
				}
				else
				{
					for (int i = endC; i <= startC; i++)
					{
						if (map[startR][i] == " -")
						{
							map[startR][i] = ship;
						}
						else
						{
							System.out.println("Error: Illegal placement of ship @ " + startR + ", " + startC);
							break;
						}
					}
				}
			}
		}
	}
	public boolean okPlacementColumn(int row, int column, int endRow)
	{
		if (endRow >= 11 || endRow <= 0)
		{
			return false;
		}
		if (endRow > row)
		{
			for (int i = row; i <= endRow; i++)
			{
				if (!(map[i][column] == " -"))
				{
	//				System.out.println("Cannot place ship at " + i + ", " + column);
					return false;
				}
			}
		}
		else
		{
			for (int i = endRow; i <= row; i++)
			{
				if (!(map[i][column] == " -"))
				{
	//				System.out.println("Cannot place ship at " + i + ", " + column);
					return false;
				}
			}
		}
//		System.out.println("Okay to place @ " + row + ", " + column);
		return true;
	}
	public boolean okPlacementRow(int row, int column, int endColumn)
	{
		if (endColumn >= 11 || endColumn <= 0)
		{
			return false;
		}
		if (endColumn > column)
		{
			for (int i = column; i <= endColumn; i++)
			{
				if (!(map[row][i] == " -"))
				{
	//				System.out.println("Cannot place ship at " + i + ", " + column);
					return false;
				}
			}
		}
		else
		{
			for (int i = endColumn; i <= column; i++)
			{
				if (!(map[row][i] == " -"))
				{
	//				System.out.println("Cannot place ship at " + i + ", " + column);
					return false;
				}
			}
		}
//		System.out.println("Okay to place @ " + row + ", " + column);
		return true;
	}
	public String checkGuess(int row, int column)
	{
		return map[row][column];
	}
	//rename? true iff a ship is destroyed, false otherwise
	public boolean hit(int row, int column, int user)
	{
		if (map[row][column] == " A")
		{
			--aircraftHp;
			map[row][column] = " X";
			if (aircraftHp == 0)
			{
				if (user == 1)
				{
					System.out.println("You have sunk their Aircraft carrier!");
					remaining();
				}
				return true;
			}
		}
		if (map[row][column] == " B")
		{
			--battleshipHp;
			map[row][column] = " X";
			if (battleshipHp == 0)
			{
				if (user == 1)
				{
					System.out.println("You have sunk their Battleship!");
					remaining();
				}
				return true;
			}
		}
		if (map[row][column] == " D")
		{
			--destroyerHp;
			map[row][column] = " X";
			if (destroyerHp == 0)
			{
				if (user == 1)
				{
					System.out.println("You have sunk their Destroyer!");
					remaining();
				}
				return true;
			}
		}
		if (map[row][column] == " S")
		{
			--subHp;
			map[row][column] = " X";
			if (subHp == 0)
			{
				if (user == 1)
				{
					System.out.println("You have sunk their Sub!");
					remaining();
				}
				return true;
			}
		}
		if (map[row][column] == " P")
		{
			--patrolHp;
			map[row][column] = " X";
			if (patrolHp == 0)
			{
				if (user == 1)
				{
					System.out.println("You have sunk their Patrol Boat!");
					remaining();
				}
				return true;
			}
		}
		return false;
	}
	public void remaining()
	{
		if (aircraftHp != 0)
		{
			System.out.println("Their Aircraft carrier still remains!");
		}
		if (battleshipHp != 0)
		{
			System.out.println("Their Battleship still remains!");
		}
		if (destroyerHp != 0)
		{
			System.out.println("Their Destroyer still remains!");
		}
		if (subHp != 0)
		{
			System.out.println("Their Sub still remains!");
		}
		if (patrolHp != 0)
		{
			System.out.println("Their Patrol Boat still remains!");
		}
	}
	public void miss(int row, int column)
	{
		map[row][column] = " 0";
	}
	public boolean hasWon()
	{
		return (aircraftHp == 0 && battleshipHp == 0 && destroyerHp == 0 && subHp == 0 && patrolHp == 0);
	}
	public boolean reasonableGuess(int row, int column)
	{
		if (column + 1 >= 11 || checkGuess(row, column + 1) != " 0")
		{
			if (row + 1 >= 11 || checkGuess(row + 1, column) != " 0")
			{
				if (column - 1 <= 0 || checkGuess(row, column - 1) != " 0")
				{
					if (row - 1 <= 0 || checkGuess(row - 1, column) != " 0")
					{
						return true;
					}
				}
			}
		}
		return false;
	}
	public void printMap()
	{
		for (int i = 0; i < 11; i++)
		{
			for (int j = 0; j < 11; j++)
			{
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
	}
}
