package snake.application;

import java.awt.Image;

import javax.sound.sampled.Clip;

/**
 * Defines the core behavior of resource providers and provides a base for
 * derived classes.
 *
 * @author Ishan Pranav
 */
public abstract class ResourceProvider
{
    /**
     * Called from constructors in derived classes to initialize the
     * {@link ResourceProvider} class.
     */
    protected ResourceProvider()
    {
    }

    /**
     * Gets an image of an apple.
     *
     * @return The apple image.
     */
    public final Image getAppleImage()
    {
        return this.getImage("Apple.png");
    }

    /**
     * Gets an image of a cake.
     *
     * @return The cake image.
     */
    public final Image getCakeImage()
    {
        return this.getImage("Cake.png");
    }

    /**
     * Gets an image of a piece of cheese.
     *
     * @return The cheese image.
     */
    public final Image getCheeseImage()
    {
        return this.getImage("Cheese.png");
    }

    /**
     * Gets the localized audio clip associated with the given key.
     *
     * @param key The key.
     * @return The audio clip.
     */
    protected abstract Clip getClip(String key);

    /**
     * Gets an image of a cookie.
     *
     * @return The pepper cookie.
     */
    public final Image getCookieImage()
    {
        return this.getImage("Cookie.png");
    }

    /**
     * Gets a string containing the caption presented when the player loses.
     *
     * @return The defeat text.
     */
    public final String getDefeatText()
    {
        return this.getString("5");
    }

    /**
     * Gets the audio clip for the eating sound.
     *
     * @return The eating audio clip.
     */
    public final Clip getEatClip()
    {
        return this.getClip("Crunch.wav");
    }

    /**
     * Gets the Exit caption.
     *
     * @return A string containing the caption for the Exit game option.
     */
    public final String getExitText()
    {
        return this.getString("0");
    }

    /**
     * Gets an image of a cluster of grapes.
     *
     * @return The grape image.
     */
    public final Image getGrapeImage()
    {
        return this.getImage("Grapes.png");
    }

    /**
     * Gets the localized image associated with the given key.
     *
     * @param key The key.
     * @return The image.
     */
    protected abstract Image getImage(String key);

    /**
     * Gets a multiline string that contains the game instructions.
     *
     * @return The game instructions.
     */
    public final String getInstructions()
    {
        return this.getString("1") + this.getExitText();
    }

    /**
     * Gets a string containing the caption for the Instructions game option.
     *
     * @return The Instructions game option caption.
     */
    public final String getInstructionText()
    {
        return this.getString("3");
    }

    /**
     * Gets an image of a lime.
     *
     * @return The lime image.
     */
    public final Image getLimeImage()
    {
        return this.getImage("Lime.png");
    }

    /**
     * Gets a string containing the caption for the Observer game option.
     *
     * @return The Observer game option caption.
     */
    public String getObserverText()
    {
        return this.getString("6");
    }

    /**
     * Gets an image of a pepper.
     *
     * @return The pepper image.
     */
    public final Image getPepperImage()
    {
        return this.getImage("Pepper.png");
    }

    /**
     * Gets an image of a slice of pizza.
     *
     * @return The pizza image.
     */
    public final Image getPizzaImage()
    {
        return this.getImage("Pizza.png");
    }

    /**
     * Gets an image of a bowl of popcorn.
     *
     * @return The popcorn image.
     */
    public final Image getPopcornImage()
    {
        return this.getImage("Popcorn.png");
    }

    /**
     * Gets a string containing the caption for the Singleplayer game option.
     *
     * @return The Singleplayer game option caption.
     */
    public final String getSingleplayerText()
    {
        return this.getString("8");
    }

    /**
     * Gets the localized string value associated with the given key.
     *
     * @param key The key.
     * @return The string value.
     */
    protected abstract String getString(String key);

    /**
     * Gets an image of a cup of tea.
     *
     * @return The tea image.
     */
    public final Image getTeaImage()
    {
        return this.getImage("Tea.png");
    }

    /**
     * Gets the application title.
     *
     * @return The application title.
     */
    public final String getTitle()
    {
        return String.join(" ", this.getTitleSegments());
    }

    /**
     * Gets the audio clip for the title screen.
     *
     * @return The title audio clip.
     */
    public final Clip getTitleClip()
    {
        return this.getClip("FlightOfTheBumblebee.wav");
    }

    /**
     * Gets the segments of the application title string.
     *
     * @return An array that contains the segments of the application title string.
     */
    public final String[] getTitleSegments()
    {
        return new String[]
        {
            this.getString("9"), this.getString("10")
        };
    }
}