package snake;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

import pong.Drawable;
import pong.application.windows.CenteredStringDrawingContext;
import snake.application.ResourceProvider;

/**
 * Represents a table used for the arcade game.
 *
 * @author Ishan Pranav
 */
public class Table implements Drawable
{
    private static final int SPEED = 7;

    private final ResourceProvider _resources;
    private final int _rows;
    private final int _columns;
    private final int _width;
    private final Tile[] _tiles;
    private final Snake _snake;
    private final ArrayList<Fruit> _fruits = new ArrayList<Fruit>();

    private int _snakeTicks;
    private int _fruitTicks;
    private boolean _mobile;

    /**
     * Initializes a new instance of the {@link Table} class.
     *
     * @param resources The resource provider.
     * @param tile      The tile prototype.
     * @param rows      The number of rows of tiles.
     * @param columns   The number of columns of tiles.
     * @param width     The width.
     * @param height    The height.
     */
    public Table(ResourceProvider resources, Tile tile, int rows, int columns, int width, int height)
    {
        this._resources = resources;
        this._rows = rows;
        this._columns = columns;
        this._width = width;
        this._tiles = new Tile[rows * columns];

        final int count = rows * columns;

        int row = -1;
        int column = 0;

        for (int i = 0; i < count; i++)
        {
            if (i % columns == 0)
            {
                row++;
                column = 0;
            }

            this._tiles[i] = tile.clone(row, column);

            column++;
        }

        final Tile head = this.getTile();
        final Direction direction = Randomizer.getDefault().createDirection();

        this._snake = new Snake(head, this.getTile(head.getRow() + direction.getVerticalComponent(),
                head.getColumn() + direction.getHorizontalComponent()));
        this._snake.setTable(this);

        this.addFruit();
    }

    private final void addFruit()
    {
        final Fruit fruit = new Fruit(this.getTile(), Randomizer.getDefault().createAbility(this._resources));

        fruit.setTable(this);

        this._fruits.add(fruit);
    }

    /**
     * Determines whether the table's bounds contains the specified row and column.
     *
     * @param row    The row.
     * @param column The column.
     * @return {@code true} if the specified row and column are within the table's
     *         bounds; otherwise, {@code false}.
     */
    public final boolean contains(int row, int column)
    {
        return row >= 0 && row < this._rows && column > 0 && column < this._columns;
    }

    /** {@inheritDoc} */
    @Override
    public final void draw(Graphics2D graphics2D)
    {
        this._snake.draw(graphics2D);

        graphics2D.setColor(Color.ORANGE);

        for (final Fruit fruit : this._fruits)
        {
            fruit.draw(graphics2D);
        }

        final CenteredStringDrawingContext stringDrawingContext = new CenteredStringDrawingContext(graphics2D,
                this._width, 0, 250);

        graphics2D.setColor(Color.WHITE);

        stringDrawingContext.setSize(50);
        stringDrawingContext.draw(this._snake.getSize());
    }

    /**
     * Consumes the fruit at the specified tile.
     *
     * @param tile The tile.
     */
    public final void eat(Tile tile)
    {
        for (int i = 0; i < this._fruits.size(); i++)
        {
            final Fruit fruit = this._fruits.get(i);

            if (fruit.getTile() == tile)
            {
                this._resources.getEatClip().start();

                fruit.eat();

                this._fruits.remove(i);

                break;
            }
        }

        if (this._fruits.size() == 0)
        {
            for (int i = 0; i < this._snake.getSize(); i++)
            {
                this.addFruit();
            }
        }
    }

    /**
     * Gets the fruits.
     *
     * @return The set of fruits.
     */
    public final Iterable<Fruit> getFruits()
    {
        return this._fruits;
    }

    /**
     * Gets the snake.
     *
     * @return The snake.
     */
    public final Snake getSnake()
    {
        return this._snake;
    }

    private final Tile getTile()
    {
        Tile tile;
        final Randomizer randomizer = Randomizer.getDefault();

        do
        {
            tile = this.getTile(randomizer.createOffset(this._rows, 2), randomizer.createOffset(this._columns, 2));
        }
        while (this.isFruit(tile) || this._snake != null && this._snake.contains(tile));

        return tile;
    }

    /**
     * Gets the tile at the specified coordinates.
     *
     * @param row    The row.
     * @param column The column.
     * @return The tile.
     */
    public final Tile getTile(int row, int column)
    {
        return this._tiles[row * this._columns + column];
    }

    /**
     * Determines whether there is a fruit on the specified tile.
     *
     * @param tile The tile.
     * @return {@code true} if there is a fruit on the tile; otherwise,
     *         {@code false}.
     */
    public final boolean isFruit(Tile tile)
    {
        for (final Fruit fruit : this._fruits)
        {
            if (fruit.getTile() == tile)
            {
                return true;
            }
        }

        return false;
    }

    /**
     * Gets a value indicating whether the fruits are agitated.
     *
     * @return {@code true} if the fruits are agitated; otherwise, {@code false}.
     */
    public final boolean isMobile()
    {
        return this._mobile;
    }

    /**
     * Sets a value indicating whether the fruits are agitated.
     *
     * @param value {@code true} if the fruits are agitated; otherwise,
     *              {@code false}.
     */
    public final void setMobile(boolean value)
    {
        this._mobile = value;

        if (value)
        {
            this._snake.setColor(Color.MAGENTA);
        }
        else
        {
            this._snake.setColor(Color.ORANGE);
        }
    }

    /** {@inheritDoc} */
    @Override
    public final void update()
    {
        if (this._snakeTicks >= SPEED)
        {
            this._snake.update();

            this._snakeTicks = 0;
        }

        if (this._fruitTicks >= SPEED * 3)
        {
            for (final Fruit fruit : this._fruits)
            {
                fruit.update();
            }

            this._fruitTicks = 0;
        }

        this._snakeTicks++;
        this._fruitTicks++;
    }
}
