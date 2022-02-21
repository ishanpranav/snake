package snake.application.windows;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import javax.sound.sampled.Clip;

import pong.Drawable;
import pong.application.windows.GameDriverSlim;
import snake.Table;
import snake.Tile;
import snake.application.ResourceProvider;
import snake.application.windows.state.GameContext;
import snake.application.windows.state.InitialGameState;

/**
 * Represents an application used to play an arcade game.
 *
 * @author Ishan Pranav
 */
public class Game extends GameDriverSlim implements GameContext, Drawable
{
    private static final long serialVersionUID = -1614426423878335492L;

    private static final int HEIGHT = 600;
    private static final int OBJECT_SIZE = 20;
    private static final int WIDTH = 800;

    /**
     * Specifies the resource provider. This field is serialized.
     */
    private final ResourceProvider _resources;

    /**
     * Specifies the game state. This field is serialized.
     */
    private Drawable _state;

    /**
     * Initializes a new instance of the {@link Game} class.
     *
     * @param resources The resource provider.
     */
    public Game(ResourceProvider resources)
    {
        this._resources = resources;

        resources.getTitleClip().loop(Clip.LOOP_CONTINUOUSLY);

        this.setTitle(this._resources.getTitle());
        this.setSize(WIDTH, HEIGHT);
        this.setState(new InitialGameState(this));
    }

    /** {@inheritDoc} */
    @Override
    public final Table createTable()
    {
        return new Table(this._resources, Tile.createPrototype(OBJECT_SIZE), HEIGHT / OBJECT_SIZE, WIDTH / OBJECT_SIZE,
                WIDTH, HEIGHT);
    }

    /** {@inheritDoc} */
    @Override
    public void draw(Graphics2D graphics2D)
    {
        graphics2D.clearRect(0, 0, this.getWidth(), this.getHeight());
        graphics2D.setColor(Color.white);

        this._state.draw(graphics2D);
    }

    /** {@inheritDoc} */
    @Override
    public final ResourceProvider getResources()
    {
        return this._resources;
    }

    /** {@inheritDoc} */
    @Override
    public final void setState(Drawable value)
    {
        this._state = value;
    }

    /** {@inheritDoc} */
    @Override
    public void update()
    {
        if (this.isPressed(KeyEvent.VK_ESCAPE))
        {
            this.setState(new InitialGameState(this));
        }

        this._state.update();
    }
}