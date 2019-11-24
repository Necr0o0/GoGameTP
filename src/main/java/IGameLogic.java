import Enums.*;

public interface IGameLogic
{


    public PlayerColor GetCurrentPlayer();
    public BoardPositionValue[][] GetBoard();

    public void CheckBoard(); // checks game rules vie Getboard();
}
