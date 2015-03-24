public class BattleMap
{
	private String[][] map = new String[11][11];
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
	public void addShip(int startC, int startR, int endC, int endR, String ship, int shipSize)
	{
		//Horizontal
		if (startR - endR == 0)
		{
			if (Math.abs(startC - endC) == shipSize - 1)
			{
				for (int i = startC; i <= endC; i++)
				{
					if (map[i][startR] == " -")
					{
						map[i][startR] = ship;
					}
					else
					{
						System.out.println("Error: Illegal placement of ship");
						break;
					}
				}
			}
		}
		//Vertical 
		if (startC - endC == 0)
		{
			if (Math.abs(startR - endR) == shipSize - 1)
			{
				for (int i = startR; i <= endR; i++)
				{
					if (map[startC][i] == " -")
					{
						map[startC][i] = ship;
					}
					else
					{
						System.out.println("Error: Illegal placement of ship");
						break;
					}
				}
			}
		}
	}
	public boolean okPlacementColumn(int column, int row, int endRow)
	{
		if (endRow >= 11)
		{
			return false;
		}
		for (int i = row; i <= endRow; i++)
		{
			if (!(map[i][row] == " -"))
			{
				return false;
			}
		}
		return true;
	}
	public boolean okPlacementRow(int column, int row, int endColumn)
	{
		if (endColumn >= 11)
		{
			return false;
		}
		for (int i = column; i <= endColumn; i++)
		{
			if (!(map[column][i] == " -"))
			{
				return false;
			}
		}
		return true;
	}
	public String checkGuess(int column, int row)
	{
		return map[column][row];
	}
	//rename? true iff a ship is destroyed, false otherwise
	public boolean hit(int column, int row)
	{
		if (map[column][row] == " A")
		{
			--aircraftHp;
			map[column][row] = " X";
			if (aircraftHp == 0)
			{
				return true;
			}
		}
		if (map[column][row] == " B")
		{
			--battleshipHp;
			map[column][row] = " X";
			if (battleshipHp == 0)
			{
				return true;
			}
		}
		if (map[column][row] == " D")
		{
			--destroyerHp;
			map[column][row] = " X";
			if (destroyerHp == 0)
			{
				return true;
			}
		}
		if (map[column][row] == " S")
		{
			--subHp;
			map[column][row] = " X";
			if (subHp == 0)
			{
				return true;
			}
		}
		if (map[column][row] == " P")
		{
			--patrolHp;
			map[column][row] = " X";
			if (patrolHp == 0)
			{
				return true;
			}
		}
		return false;
	}
	public void miss(int column, int row)
	{
		map[column][row] = " 0";
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
