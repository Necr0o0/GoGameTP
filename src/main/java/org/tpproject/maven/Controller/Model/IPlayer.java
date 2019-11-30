package org.tpproject.maven.Controller.Model;

import org.tpproject.maven.Controller.Model.Enums.PlayerColor;


public interface IPlayer
{
    public PlayerColor GetPlayerColor();
    public int[][] Move();

}
