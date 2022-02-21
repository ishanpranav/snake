package snake;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;

import pong.Drawable;
import snake.abilities.Ability;

/**
 * Represents an edible item.
 *
 * @author Ishan Pranav
 */
public class Fruit implements Drawable
{
    private final Direction[] _moves = new Direction[10];
    private final Ability _ability;

    private Tile _tile;
    private int _move;
    private Table _table;

    /**
     * Initializes a new instance of the {@link Fruit} class.
     *
     * @param tile    The tile.
     * @param ability The ability.
     */
    public Fruit(Tile tile, Ability ability)
    {
        this._tile = tile;
        this._ability = ability;

        final Randomizer randomizer = Randomizer.getDefault();

        Direction previous = Direction.NONE;

        for (int i = 0; i < this._moves.length; i += 2)
        {
            Direction direction;

            do
            {
                direction = randomizer.createDirection();
            }
            while (direction == previous);

            previous = direction;
            this._moves[i] = direction;
            this._moves[i + 1] = direction;
        }
    }

    /** {@inheritDoc} */
    @Override
    public final void draw(Graphics2D graphics2D)
    {
        final Image image = this._ability.getImage();
        final Rectangle rectangle = this._tile.getRectangle();

        graphics2D.drawImage(image, rectangle.x, rectangle.y, rectangle.width, rectangle.height, null);
    }

    /** Consumes the fruit. */
    public final void eat()
    {
        this._ability.execute(this._table);
    }

    /**
     * Gets the tile.
     *
     * @return The tile.
     */
    public final Tile getTile()
    {
        return this._tile;
    }

    final void setTable(Table value)
    {
        this._table = value;
    }

    /** {@inheritDoc} */
    @Override
    public final void update()
    {
        if (this._table.isMobile())
        {
            final Tile sum = this._tile.add(this._moves[this._move]);
            final int row = sum.getRow();
            final int column = sum.getColumn();

            if (this._table.contains(row, column))
            {
                this._tile = this._table.getTile(row, column);
            }

            this._move = (this._move + 1) % this._moves.length;
        }
    }
}
