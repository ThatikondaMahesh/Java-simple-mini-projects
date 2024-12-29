// program to TicTacToe class {manager class}
import java.util.*;
public class TicTacToe
{
	private Player player1,player2;
	private Board board;
	private int numPlayers=0;
	public static void main(String ar[])
	{
		TicTacToe t=new TicTacToe();
		t.startGame();
	}
	public void startGame()
	{
		Scanner sc=new Scanner(System.in);
		// Take Players input
		  player1=takePlayerInput(++numPlayers);
		  player2=takePlayerInput(++numPlayers);
		  while(player1.getSymbol()==player2.getSymbol())
		  {
			  System.out.print("symbol already taken Please enter the Symbol");
			  player2.setSymbol(sc.next().charAt(0));
		  }
		  
	    // create the board 
		 board=new Board(player1.getSymbol(),player2.getSymbol());
		  
		int status=Board.INCOMPLETE;
        // play the Game 
		boolean playerTurn=true;
		while(status==Board.INCOMPLETE || status==Board.INVALIDMOVE)
		{
			if(playerTurn)
			{
				System.out.println("Player 1-"+player1.getName()+" 's turn");
				System.out.print("Enter x:");
				int x=sc.nextInt();
				System.out.println("Enter y: ");
				int y=sc.nextInt();
				status =board.move(player1.getSymbol(),x,y);
				if(status==Board.INVALIDMOVE)
				{
					System.out.print("Invalid move !! Please try again !!");
				}
				else
				{
					playerTurn=false;
					board.print();
					
				}
			}
			else
			{
				System.out.println("Player 2-"+player2.getName()+" 's turn");
				System.out.print("Enter x:");
				int x=sc.nextInt();
				System.out.println("Enter y:");
				int y=sc.nextInt();
				status =board.move(player1.getSymbol(),x,y);
				if(status==Board.INVALIDMOVE)
				{
					System.out.print("Invalid move !! Please try again !!");
				}
				else
				{
					
					playerTurn=true;
					board.print();
				}
				
			}
			
			
		}
		if(status==Board.PLAYER1WINS)
		{
			System.out.println("player-1 "+player1.getName()+" wins !!");
		}
		else if(status==Board.PLAYER2WINS)
		{
			System.out.println("player-2 "+player2.getName()+" wins !!");
		}
		else
		{
			System.out.println("DRAW!");

		}
		
	}
	private Player takePlayerInput(int num)
	{
		Scanner sc=new Scanner(System.in);

		System.out.println("Enter the player "+num+"name:");
		String name =sc.nextLine();
		System.out.println("Enter the"+name+"'s symbol");
		char symbol=sc.next().charAt(0);
		Player p=new Player(name,symbol);
		return p;
      		
	}
}