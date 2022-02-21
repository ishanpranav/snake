package snake;

import java.util.Random;

import snake.abilities.Ability;
import snake.abilities.MotionAbility;
import snake.abilities.NullAbility;
import snake.application.ResourceProvider;

/**
 * Represents an extended random number generator. This class implements the
 * Singleton Design Pattern.
 *
 * @author Ishan Pranav
 */
public final class Randomizer
{
    private static Randomizer instance;

    private final Random _random = new Random();

    private Randomizer()
    {
    }

    /**
     * Generates a random ability.
     *
     * @param resources The resource provider.
     * @return The ability.
     */
    public Ability createAbility(ResourceProvider resources)
    {
        final int next = this._random.nextInt(10);

        switch (next)
        {
            case 0:
                return new NullAbility(resources.getPizzaImage());

            case 1:
                return new NullAbility(resources.getPopcornImage());

            case 2:
                return new MotionAbility(resources.getCakeImage(), true);

            case 3:
                return new MotionAbility(resources.getCookieImage(), true);

            case 4:
                return new MotionAbility(resources.getPepperImage(), true);

            case 5:
                return new MotionAbility(resources.getTeaImage(), true);

            case 6:
                return new MotionAbility(resources.getAppleImage(), false);

            case 7:
                return new MotionAbility(resources.getGrapeImage(), false);

            case 8:
                return new MotionAbility(resources.getLimeImage(), false);

            default:
                return new NullAbility(resources.getCheeseImage());
        }
    }

    /**
     * Generates a random direction.
     *
     * @return The direction.
     */
    public Direction createDirection()
    {
        final Direction[] values = Direction.values();

        return values[this._random.nextInt(values.length - 1) + 1];
    }

    /**
     * Generates a random number according to a uniform distribution but excludes
     * the most extreme values.
     *
     * @param count  The width of the range.
     * @param offset The margin which determines which values are considered
     *               extreme.
     * @return The random number.
     */
    public int createOffset(int count, int offset)
    {
        return this._random.nextInt(count - offset * 2) + offset;
    }

    /**
     * Gets the default {@link Randomizer} instance.
     *
     * @return The default instance.
     */
    public static Randomizer getDefault()
    {
        if (instance == null)
        {
            instance = new Randomizer();
        }

        return instance;
    }
}