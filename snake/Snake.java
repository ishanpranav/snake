package snake;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.LinkedList;

import pong.Drawable;

/**
 * Represents a snake.
 *
 * @author Ishan Pranav
 */
public class Snake implements Drawable
{
    private final LinkedList<Tile> _bodyParts = new LinkedList<Tile>();

    private boolean _terminated;
    private Tile _head;
    private Table _table;
    private Direction _direction = Direction.NONE;
    private Color _color = Color.GREEN;
    private boolean _follow = false;

    /**
     * Initializes a new instance of the {@link Snake} class.
     *
     * @param head The head.
     * @param tail The tail.
     */
    public Snake(Tile head, Tile tail)
    {
        this._head = head;

        this._bodyParts.add(tail);
    }

    /**
     * Determines whether the snake contains a specific tile.
     *
     * @param value The tile.
     * @return {@code true} if the snake contains the tile; otherwise,
     *         {@code false}.
     */
    public final boolean contains(Tile value)
    {
        return this._head == value || this._bodyParts.contains(value);
    }

    /** {@inheritDoc} */
    @Override
    public final void draw(Graphics2D graphics2D)
    {
        graphics2D.setColor(this._color);

        final Rectangle rectangle = this._head.getRectangle();

        graphics2D.draw(rectangle);
        graphics2D.fill(rectangle);

        for (final Tile bodyPart : this._bodyParts)
        {
            graphics2D.draw(bodyPart.getRectangle());
        }
    }

    /** Follows the fruit.  */
    public final void follow()
    {
        this._follow = true;

        this.setDirection(Randomizer.getDefault().createDirection());
    }

    /**
     * Gets the size of the snake.
     *
     * @return The number of body parts.
     */
    public final int getSize()
    {
        return this._bodyParts.size();
    }

    /**
     * Gets a value indicating whether the snake is terminated.
     *
     * @return {@code true} if the snake is terminated; otherwise, {@code false}.
     */
    public final boolean isTerminated()
    {
        return this._terminated;
    }

    /**
     * Sets the color of the snake.
     *
     * @param value The color.
     */
    public final void setColor(Color value)
    {
        this._color = value;
    }

    /**
     * Sets the direction of the snake.
     *
     * @param value The direction.
     */
    public final void setDirection(Direction value)
    {
        if (this._direction.getDimension() != value.getDimension())
        {
            this._direction = value;
        }
    }

    final void setTable(Table value)
    {
        this._table = value;
    }

    /** {@inheritDoc} */
    @Override
    public final void update()
    {

        if (this._follow)
        {
            Tile minTile = null;
            int minSquareDistance = Integer.MAX_VALUE;

            for (final Fruit fruit : this._table.getFruits())
            {
                final Tile tile = fruit.getTile();

                final int rowDifference = tile.getRow() - this._head.getRow();
                final int columnDifference = tile.getColumn() - this._head.getColumn();
                final int squareDistance = rowDifference * rowDifference + columnDifference * columnDifference;

                if (squareDistance < minSquareDistance)
                {
                    minTile = tile;
                    minSquareDistance = squareDistance;
                }
            }

            final int rowDifference = minTile.getRow() - this._head.getRow();
            final int columnDifference = minTile.getColumn() - this._head.getColumn();

            Direction result = this._direction;

            switch (this._direction.getDimension())
            {
                case 1:
                    if (Math.abs(rowDifference) > Math.abs(columnDifference))
                    {
                        if (rowDifference > 0)
                        {
                            result = Direction.DOWN;
                        }
                        else
                        {
                            result = Direction.UP;
                        }
                    }
                    break;

                case 2:
                    if (Math.abs(columnDifference) > Math.abs(rowDifference))
                    {
                        if (columnDifference > 0)
                        {
                            result = Direction.RIGHT;
                        }
                        else
                        {
                            result = Direction.LEFT;
                        }
                    }
                    break;
            }

            final Tile sum = this._head.add(result);
            final int column = sum.getColumn();
            final int row = sum.getRow();
            boolean legal = false;

            if (this._table.contains(row, column))
            {
                legal = true;

                for (final Tile bodyPart : this._bodyParts)
                {
                    if (bodyPart.getRow() == row && bodyPart.getColumn() == column)
                    {
                        legal = false;

                        break;
                    }
                }
            }

            if (legal)
            {
                this.setDirection(result);
            }
            else if (this._direction == result)
            {
                final Direction original = this._direction;
                final Randomizer randomizer = Randomizer.getDefault();

                do
                {
                    this.setDirection(randomizer.createDirection());
                }
                while (this._direction == original);
            }
        }

        if (this._direction != Direction.NONE)
        {
            if (this._bodyParts.contains(this._head))
            {
                this._terminated = true;
            }

            this._bodyParts.add(this._head);

            if (this._table.isFruit(this._head))
            {
                this._table.eat(this._head);
            }
            else
            {
                this._bodyParts.remove();
            }

            final Tile sum = this._head.add(this._direction);
            final int row = sum.getRow();
            final int column = sum.getColumn();

            if (this._table.contains(row, column))
            {
                this._head = this._table.getTile(row, column);
            }
            else
            {
                this._terminated = true;
            }
        }
    }
}
