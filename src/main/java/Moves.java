import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name ="moves")
public class Moves implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "MoveID", unique = true)
    private int Moveid;

    @Column(name = "GameID")
    private int gameID;

    @Column(name = "PlayerColor")
    private String playerColor;

    @Column(name = "PlayerMove")
    private String playerMove;

    public int getGameID() {
        return gameID;
    }

    public int getMoveid() {
        return Moveid;
    }

    public String getPlayerColor() {
        return playerColor;
    }

    public String getPlayerMove() {
        return playerMove;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }

    public void setMoveid(int moveid) {
        Moveid = moveid;
    }

    public void setPlayerColor(String playerColor) {
        this.playerColor = playerColor;
    }

    public void setPlayerMove(String playerMove) {
        this.playerMove = playerMove;
    }
}
