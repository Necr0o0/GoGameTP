package Controller;

import Model.Enums.PlayerColor;
import Model.DefaultGameLogic;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server extends Thread {

	public static int server_port = 58901;
	
	@Override
	public void run()
	{
		try( ServerSocket listener = new ServerSocket(server_port) )
		{
			System.out.println("The game server is running...");
			ExecutorService pool = Executors.newFixedThreadPool( 2 );
			while( true )
			{
				DefaultGameLogic game = new DefaultGameLogic( 19 );
				pool.execute( new User(listener.accept(), PlayerColor.Black, game ) );
				pool.execute( new User(listener.accept(), PlayerColor.White, game ) );
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
