package pl.pja.edu.s27619.board;

import pl.pja.edu.s27619.content.Direction;
import pl.pja.edu.s27619.content.MovebleObject;

public interface GameBoardInterface {
    public boolean move(MovebleObject movebleObject, Direction direction);

}
