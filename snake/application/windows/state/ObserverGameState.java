package snake.application.windows.state;

import java.awt.Graphics2D;

import pong.Drawable;
import snake.Table;

/**
 * Represents the Observer game state. This class implements the State Design
 * Pattern as defined by the {@link GameContext} and {@link Drawable}
 * interfaces.
 *
 * @author Ishan Pranav
 */
public class ObserverGameState implements Drawable
{
    private final Table _table;

    /**
     * Initializes a new instance of the {@link ObserverGameState} class.
     *
     * @param context The game context.
     */
    public ObserverGameState(GameContext context)
    {
        this._table = context.createTable();
        this._table.getSnake().follow();
    }

    /** {@inheritDoc} */
    @Override
    public final void draw(Graphics2D graphics2D)
    {
        this._table.draw(graphics2D);
    }

    /** {@inheritDoc} */
    @Override
    public final void update()
    {
        this._table.update();
    }
}