package Controller.Spring.Routes;

import Controller.Spring.APIServer;
import Controller.Spring.Link.Requests.PutStoneRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ActionController {
	@PostMapping("/put-stone")
	public ServerResponse PutStone(@RequestBody PutStoneRequest req ) {
		if( APIServer.game.ValidateMove( req.xPos, req.yPos ) ) {

		}

		return new ServerResponse();
	}
}
