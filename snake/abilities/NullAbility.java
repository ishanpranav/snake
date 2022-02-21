package snake.abilities;

import java.awt.Image;

import snake.Table;

/**
 * Represents no ability.
 *
 * @author Ishan Pranav
 */
public class NullAbility extends Ability
{
    /**
     * Initializes a new instance of the {@link NullAbility} class.
     *
     * @param image The image associated with the ability.
     */
    public NullAbility(Image image)
    {
        super(image);
    }

    /** {@inheritDoc} */
    @Override
    public void execute(Table table)
    {
    }
}
