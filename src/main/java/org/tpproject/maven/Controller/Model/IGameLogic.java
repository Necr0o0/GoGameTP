package org.tpproject.maven.Controller.Model;

import org.tpproject.maven.Controller.Model.Enums.BoardPositionValue;
import org.tpproject.maven.Controller.Model.Enums.PlayerColor;

public interface IGameLogic
{


    public PlayerColor GetCurrentPlayer();
    public BoardPositionValue[][] GetBoard();

    public void CheckBoard(); // checks game rules vie Getboard();
}
