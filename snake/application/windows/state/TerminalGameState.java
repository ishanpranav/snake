package snake.application.windows.state;

import java.awt.Graphics2D;

import pong.Drawable;
import pong.application.windows.CenteredStringDrawingContext;
import snake.application.ResourceProvider;

/**
 * Represents the terminal game state. This class implements the State Design
 * Pattern as defined by the {@link GameContext} and {@link Drawable}
 * interfaces.
 *
 * @author Ishan Pranav
 */
public class TerminalGameState implements Drawable
{
    private final GameContext _context;

    /**
     * Initializes a new instance of the {@link TerminalGameState} class.
     *
     * @param context The game context.
     */
    public TerminalGameState(GameContext context)
    {
        this._context = context;
    }

    /** {@inheritDoc} */
    @Override
    public final void draw(Graphics2D graphics2D)
    {
        final ResourceProvider resources = this._context.getResources();

        final CenteredStringDrawingContext stringDrawingContext = new CenteredStringDrawingContext(graphics2D,
                this._context.getWidth(), 75);

        stringDrawingContext.draw(resources.getDefeatText());
        stringDrawingContext.draw(resources.getExitText());
    }

    /** {@inheritDoc} */
    @Override
    public final void update()
    {
    }
}
