package Controller.Spring;

import Model.DefaultGameLogic;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class APIServer {
	public static DefaultGameLogic game;
	public static void main(String[] args) {
		game = new DefaultGameLogic(19);
		SpringApplication.run(APIServer.class, args);
	}

}
