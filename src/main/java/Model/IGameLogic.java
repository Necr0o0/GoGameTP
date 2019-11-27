package Model;

import Model.Enums.BoardPositionValue;
import Model.Enums.PlayerColor;

public interface IGameLogic
{


    public PlayerColor GetCurrentPlayer();
    public BoardPositionValue[][] GetBoard();

    public void CheckBoard(); // checks game rules vie Getboard();
}
