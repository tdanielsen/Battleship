
public class GuessingMap
{
	private String[][] map = new String[11][11];
	public GuessingMap()
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
					map[i][j] = " ?";
				}
			}
		}
	}
	public boolean guess(BattleMap compMap, int row, int column)
	{
		if (compMap.checkGuess(row, column) == " 0" || compMap.checkGuess(row, column) == " X")
		{
			return false;
		}
		else if (compMap.checkGuess(row, column) == " -")
		{
			map[row][column] = " 0";
			return true;
		}
		else
		{
			map[row][column] = " X";
			return true;
		}
	}
	public void miss(int row, int column)
	{
		map[row][column] = " 0";
	}
	public void hit(int row, int column)
	{
		map[row][column] = " X";
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
