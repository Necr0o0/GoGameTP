package Controller;

import Model.Enums.PlayerColor;

import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

	public static int server_port = 58901;

	public static void main( String[] args ) throws Exception {
			try( ServerSocket listener = new ServerSocket(server_port) ) {
				System.out.println("The game server is running...");
				ExecutorService pool = Executors.newFixedThreadPool( 2 );
				while( true ) {
					pool.execute( new User(listener.accept(), PlayerColor.White) );
					pool.execute( new User(listener.accept(), PlayerColor.Black) );
				}
			}
	}

}
