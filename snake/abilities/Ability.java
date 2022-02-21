package snake.abilities;

import java.awt.Image;

import snake.Table;

/**
 * Defines the core behavior of special abilities and provides a base for
 * derived classes. This class defines the Command Design Pattern.
 *
 * @author Ishan Pranav
 */
public abstract class Ability
{
    private final Image _image;

    /**
     * Called from constructors in derived classes to initialize the {@link Ability}
     * class.
     *
     * @param image The image associated with the ability.
     */
    protected Ability(Image image)
    {
        this._image = image;
    }

    /**
     * Performs the operation associated with the ability.
     *
     * @param table The table.
     */
    public abstract void execute(Table table);

    /**
     * Gets the image associated with the ability.
     *
     * @return The image.
     */
    public final Image getImage()
    {
        return this._image;
    }
}