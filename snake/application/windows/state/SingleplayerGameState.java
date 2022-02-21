package snake.application.windows.state;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import pong.Drawable;
import snake.Direction;
import snake.Table;

/**
 * Represents the Singleplayer game state. This class implements the State
 * Design Pattern as defined by the {@link GameContext} and {@link Drawable}
 * interfaces.
 *
 * @author Ishan Pranav
 */
public class SingleplayerGameState implements Drawable
{
    private final Table _table;
    private final GameContext _context;

    /**
     * Initializes a new instance of the {@link SingleplayerGameState} class.
     *
     * @param context The game context.
     */
    public SingleplayerGameState(GameContext context)
    {
        this._table = context.createTable();
        this._context = context;
    }

    /** {@inheritDoc} */
    @Override
    public void draw(Graphics2D graphics2D)
    {
        this._table.draw(graphics2D);
    }

    /** {@inheritDoc} */
    @Override
    public void update()
    {
        this._table.update();

        if (this._table.getSnake().isTerminated())
        {
            this._context.setState(new TerminalGameState(this._context));
        }

        if (this._context.isPressed(KeyEvent.VK_W) || this._context.isPressed(KeyEvent.VK_UP))
        {
            this._table.getSnake().setDirection(Direction.UP);
        }
        else if (this._context.isPressed(KeyEvent.VK_S) || this._context.isPressed(KeyEvent.VK_DOWN))
        {
            this._table.getSnake().setDirection(Direction.DOWN);
        }
        else if (this._context.isPressed(KeyEvent.VK_A) || this._context.isPressed(KeyEvent.VK_LEFT))
        {
            this._table.getSnake().setDirection(Direction.LEFT);
        }
        else if (this._context.isPressed(KeyEvent.VK_D) || this._context.isPressed(KeyEvent.VK_RIGHT))
        {
            this._table.getSnake().setDirection(Direction.RIGHT);
        }
    }
}
