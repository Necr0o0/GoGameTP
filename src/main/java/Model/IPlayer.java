package Model;

import Model.Enums.PlayerColor;


public interface IPlayer
{
    public PlayerColor GetPlayerColor();
    public int[][] Move();

}
