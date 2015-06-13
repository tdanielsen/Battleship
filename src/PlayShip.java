import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class PlayShip
{
	public static void main(String[] args)
	{
		Scanner scanner = new Scanner(System.in); // for console input

		boolean done = false;
		System.out.println("Welcome to Navel Battle!");
		while (!done)
		{
			System.out.print("Please enter a command (play, help, quit): ");
			String input = scanner.nextLine();
			if (input.length() > 0)
			{
				switch (input)
				{

				case "play":
					play(scanner);
					break;
				
				case "help":
					System.out.println("How to Play:");
					System.out.println("Place your ships on the map and then sail for the high seas!");
					System.out.println("Remember to put coordinates in row, column order and no space in between");
					System.out.println("example: 5,g");
					break;
					
				case "quit":
					done = true;
					break;

				default: // ignore invalid commands
					System.out.println("Invalid command.");
					break;

				}
			}
		}
	}
	public static void play (Scanner scanner)
	{
		BattleMap compMap = new BattleMap();
		ai newai = new ai(compMap);
		newai.placeShip(5, " A");
		newai.placeShip(4, " B");
		newai.placeShip(3, " D");
		newai.placeShip(3, " S");
		newai.placeShip(2, " P");
		BattleMap userMap = new BattleMap();
		GuessingMap topMap = new GuessingMap();
		String input = "";
		int row = 0;
		int column = 0;
		String direction = "";
		for (int i = 0; i < 5; i++)
		{
			userMap.printMap();
			if (i == 0)
			{
				System.out.println("Place input the starting coordinates for the Aircraft carrier and "
						+ "the direction you'd like it to go (N, E, W, S): ");
				input = scanner.nextLine();
				try
				{
					StringTokenizer firstSpace = new StringTokenizer(input, ",");
					row = Integer.parseInt(firstSpace.nextToken());
					column = letterConverter(firstSpace.nextToken());
					direction = firstSpace.nextToken();
					if (column == 0)
					{
						System.out.println("Input error");
						i--;
					}
					else if (!playerPlacement(row, column, " A", 5, direction, userMap))
					{
						System.out.println("Invaild ship placement");
						i--;
					}
				}
				catch (NumberFormatException e)
				{
					System.out.println("Input error! Make sure that you put in you guess in row,column format!");
					i--;
				}
				catch (NoSuchElementException e)
				{
					System.out.println("Input error! Make sure you have a row, column, AND direction");
					i--;
				}
			}
			else if (i == 1)
			{
				System.out.println("Place input the starting coordinates for the Battleship and "
						+ "the direction you'd like it to go (N, E, W, S): ");
				input = scanner.nextLine();
				try
				{
					StringTokenizer firstSpace = new StringTokenizer(input, ",");
					row = Integer.parseInt(firstSpace.nextToken());
					column = letterConverter(firstSpace.nextToken());
					direction = firstSpace.nextToken();
					if (column == 0)
					{
						System.out.println("Input error");
						i--;
					}
					else if (!playerPlacement(row, column, " B", 4, direction, userMap))
					{
						System.out.println("Invaild ship placement");
						i--;
					}
				}
				catch (NumberFormatException e)
				{
					System.out.println("Input error! Make sure that you put in you guess in row,column format!");
					i--;
				}
				catch (NoSuchElementException e)
				{
					System.out.println("Input error! Make sure you have a row, column, AND direction");
					i--;
				}
			}
			else if (i == 2)
			{
				System.out.println("Place input the starting coordinates for the Destoryer and "
						+ "the direction you'd like it to go (N, E, W, S): ");
				input = scanner.nextLine();
				try
				{
					StringTokenizer firstSpace = new StringTokenizer(input, ",");
					row = Integer.parseInt(firstSpace.nextToken());
					column = letterConverter(firstSpace.nextToken());
					direction = firstSpace.nextToken();
					if (column == 0)
					{
						System.out.println("Input error");
						i--;
					}
					else if (!playerPlacement(row, column, " D", 3, direction, userMap))
					{
						System.out.println("Invaild ship placement");
						i--;
					}
				}
				catch (NumberFormatException e)
				{
					System.out.println("Input error! Make sure that you put in you guess in row,column format!");
					i--;
				}
				catch (NoSuchElementException e)
				{
					System.out.println("Input error! Make sure you have a row, column, AND direction");
					i--;
				}
			}
			else if (i == 3)
			{
				System.out.println("Place input the starting coordinates for the Sub and "
						+ "the direction you'd like it to go (N, E, W, S): ");
				input = scanner.nextLine();
				try
				{
					StringTokenizer firstSpace = new StringTokenizer(input, ",");
					row = Integer.parseInt(firstSpace.nextToken());
					column = letterConverter(firstSpace.nextToken());
					direction = firstSpace.nextToken();
					if (column == 0)
					{
						System.out.println("Input error");
						i--;
					}
					else if (!playerPlacement(row, column, " S", 3, direction, userMap))
					{
						System.out.println("Invaild ship placement");
						i--;
					}
				}
				catch (NumberFormatException e)
				{
					System.out.println("Input error! Make sure that you put in you guess in row,column format!");
					i--;
				}
				catch (NoSuchElementException e)
				{
					System.out.println("Input error! Make sure you have a row, column, AND direction");
					i--;
				}
			}
			else if (i == 4)
			{
				System.out.println("Place input the starting coordinates for the Patrol Boat and "
						+ "the direction you'd like it to go (N, E, W, S): ");
				input = scanner.nextLine();
				try
				{
					StringTokenizer firstSpace = new StringTokenizer(input, ",");
					row = Integer.parseInt(firstSpace.nextToken());
					column = letterConverter(firstSpace.nextToken());
					direction = firstSpace.nextToken();
					if (column == 0)
					{
						System.out.println("Input error");
						i--;
					}
					else if (!playerPlacement(row, column, " P", 2, direction, userMap))
					{
						System.out.println("Invaild ship placement");
						i--;
					}
				}
				catch (NumberFormatException e)
				{
					System.out.println("Input error! Make sure that you put in you guess in row,column format!");
					i--;
				}
				catch (NoSuchElementException e)
				{
					System.out.println("Input error! Make sure you have a row, column, AND direction");
					i--;
				}
			}
		}
		System.out.println("The game has 'set sail'! Type 'give up' if you wish to end early.");
		while (compMap.hasWon() == false && userMap.hasWon() == false)
		{
			System.out.println("It is your turn! Remember it is row,column order!");
			topMap.printMap();
			userMap.printMap();
			input = scanner.nextLine();
			if (!input.equalsIgnoreCase("give up"))
			{
				
				try
				{
					StringTokenizer firstSpace = new StringTokenizer(input, ",");
					row = Integer.parseInt(firstSpace.nextToken());
					column = letterConverter(firstSpace.nextToken());
					if (row >= 11 || row <= 0 || column >= 11 || row <= 0)
					{
						System.out.println("Cannot guess there. Please guess between 1-10 and A-J");
					}
					else if (compMap.checkGuess(row, column) == " 0" || compMap.checkGuess(row, column) == " X")
					{
						System.out.println("You have already guessed there, please guess again!");
					}
					else if (compMap.checkGuess(row, column) == " -")
					{
						topMap.miss(row, column);
						compMap.miss(row, column);
						newai.makeGuess(userMap);
					}
					else
					{
						if(compMap.hit(row, column))
						{
							System.out.println("You have sunk their navy boat!");
						}
						topMap.hit(row, column);
						newai.makeGuess(userMap);
					}
				}
				catch (NumberFormatException e)
				{
					System.out.println("Input error! Make sure that you put in you guess in row,column format!");
				}
				catch (NoSuchElementException e)
				{
					System.out.println("Input error! Make sure you have a row, column, AND direction");
				}			
			}
			else
			{
				System.out.println("The Computer's map:");
				compMap.printMap();
				break;
			}
		}
	}
	public static boolean playerPlacement(int row, int column, String ship, int shipSize,
			String direction, BattleMap map)
	{
		if (direction.equalsIgnoreCase("N"))
		{
			if (map.okPlacementColumn(row, column, row - (shipSize - 1)))
			{
				map.addShip(row, column, row - (shipSize - 1), column, ship, shipSize);
				return true;
			}
		}
		if (direction.equalsIgnoreCase("E"))
		{
			if (map.okPlacementRow(row, column, column + (shipSize - 1)))
			{
				map.addShip(row, column, row, column + (shipSize - 1), ship, shipSize);
				return true;
			}
		}
		if (direction.equalsIgnoreCase("W"))
		{
			if (map.okPlacementRow(row, column, column - (shipSize - 1)))
			{
				map.addShip(row, column, row, column - (shipSize - 1), ship, shipSize);
				return true;
			}
		}
		if (direction.equalsIgnoreCase("S"))
		{
			if (map.okPlacementColumn(row, column, row + (shipSize - 1)))
			map.addShip(row, column, row + (shipSize - 1), column, ship, shipSize);
			return true;
		}
		return false;
	}
	public static int letterConverter(String letter)
	{
		if (letter.equalsIgnoreCase("A"))
		{
			return 1;
		}
		if (letter.equalsIgnoreCase("B"))
		{
			return 2;
		}
		if (letter.equalsIgnoreCase("C"))
		{
			return 3;
		}
		if (letter.equalsIgnoreCase("D"))
		{
			return 4;
		}
		if (letter.equalsIgnoreCase("E"))
		{
			return 5;
		}
		if (letter.equalsIgnoreCase("F"))
		{
			return 6;
		}
		if (letter.equalsIgnoreCase("G"))
		{
			return 7;
		}
		if (letter.equalsIgnoreCase("H"))
		{
			return 8;
		}
		if (letter.equalsIgnoreCase("I"))
		{
			return 9;
		}
		if (letter.equalsIgnoreCase("J"))
		{
			return 10;
		}
		return 0;
	}
}
