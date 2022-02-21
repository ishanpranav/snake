package snake;

import java.awt.Rectangle;

/**
 * Represents a tile. This class implements the Prototype Design Pattern.
 *
 * @author Ishan Pranav
 */
public class Tile implements Cloneable
{
    private int _row;
    private int _column;
    private Rectangle _rectangle;

    private Tile(int size)
    {
        this._rectangle = new Rectangle(0, 0, size, size);
    }

    /**
     * Adds a direction to the tile.
     *
     * @param direction The direction.
     * @return The sum.
     */
    public final Tile add(Direction direction)
    {
        return this.clone(this._row + direction.getVerticalComponent(),
                this._column + direction.getHorizontalComponent());
    }

    /** {@inheritDoc} */
    @Override
    public final Tile clone()
    {
        try
        {
            return (Tile)super.clone();
        }
        catch (final CloneNotSupportedException exception)
        {
            return null;
        }
    }

    /**
     * Creates and returns a copy of this object.
     *
     * @param row    The row of the clone.
     * @param column The column of the clone.
     * @return The clone.
     */
    public final Tile clone(int row, int column)
    {
        final Tile result = this.clone();

        result._row = row;
        result._column = column;

        final int size = result._rectangle.width;

        result._rectangle = new Rectangle(column * size, row * size, size, size);

        return result;
    }

    /**
     * Gets the column of the tile.
     *
     * @return The column.
     */
    public final int getColumn()
    {
        return this._column;
    }

    /**
     * Gets a rectangle representing the bounds of the tile.
     *
     * @return A rectangle.
     */
    public final Rectangle getRectangle()
    {
        return this._rectangle;
    }

    /**
     * Gets the row of the tile.
     *
     * @return The row.
     */
    public final int getRow()
    {
        return this._row;
    }

    /**
     * Gets the size of the tile.
     *
     * @return The size.
     */
    public final int getSize()
    {
        return this._rectangle.width;
    }

    /**
     * Creates a new prototypical instance of the {@link Tile} class.
     *
     * @param size The size of the tile.
     *
     * @return A new tile instance.
     */
    public static Tile createPrototype(int size)
    {
        return new Tile(size);
    }
}
