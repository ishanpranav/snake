package snake;

/**
 * Specifies an orthogonal direction.
 *
 * @author Ishan Pranav
 */
public enum Direction
{
    /** No direction. */
    NONE(0, 0),

    /** The up direction. */
    UP(0, -1),

    /** The down direction. */
    DOWN(0, 1),

    /** The left direction. */
    LEFT(-1, 0),

    /** The right direction. */
    RIGHT(1, 0);

    private final int _horizontalComponent;
    private final int _verticalComponent;

    Direction(int horizontalComponent, int verticalComponent)
    {
        this._horizontalComponent = horizontalComponent;
        this._verticalComponent = verticalComponent;
    }

    /**
     * Gets the dimension of the direction. Only zero-, one-, and two-dimensional
     * directions have been implemented.
     *
     * @return 0 if the direction is zero, 1 if it the direction points in the
     *         horizontal (first) dimension, or 2 if it the direction points in the
     *         vertical (second) dimension.
     */
    public final int getDimension()
    {
        if (this._horizontalComponent != 0)
        {
            return 1;
        }
        if (this._verticalComponent != 0)
        {
            return 2;
        }
        return 0;
    }

    /**
     * Gets the horizontal component of the direction.
     *
     * @return 1 or -1 if the direction is horizontal; otherwise, 0.
     */
    public final int getHorizontalComponent()
    {
        return this._horizontalComponent;
    }

    /**
     * Gets the vertical component of the direction.
     *
     * @return 1 or -1 if the direction is vertical; otherwise, 0.
     */
    public final int getVerticalComponent()
    {
        return this._verticalComponent;
    }
}
