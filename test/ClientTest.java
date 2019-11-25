import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClientTest {
	@Test
	public void SendStringToServer() throws Exception {
		Client client = new Client( "127.0.0.1" );

		String foo = "test string";
		client.SendMessage( foo );
		String bar = client.ReceiveMessage();
		System.out.println( bar );

		// Note: Making the Player transmit anything to Client before using EchoReceivedString() method WILL break this test
		assertTrue( foo.equals( bar ) );
	}
}