package snake.abilities;

import java.awt.Image;

import snake.Table;

/**
 * Represents the ability to toggle the autonomous motion of fruit.
 *
 * @author Ishan Pranav
 */
public class MotionAbility extends Ability
{
    private final boolean _mobile;

    /**
     * Initializes a new instance of the {@link MotionAbility} class.
     *
     * @param image  The image associated with the ability.
     * @param mobile {@code true} if fruits may move autonomously; otherwise,
     *               {@code false}.
     */
    public MotionAbility(Image image, boolean mobile)
    {
        super(image);

        this._mobile = mobile;
    }

    /** {@inheritDoc} */
    @Override
    public void execute(Table table)
    {
        table.setMobile(this._mobile);
    }
}