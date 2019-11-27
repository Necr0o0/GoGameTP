package Model;

import Model.Enums.PlayerColor;

import java.util.Vector;


public interface IPlayer
{
    public PlayerColor GetPlayerColor();
    public int[][] Move();

}
