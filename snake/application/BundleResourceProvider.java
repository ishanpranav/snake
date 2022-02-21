package snake.application;

import java.awt.Image;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * A {@link ResourceProvider} used to retrieve resources from a
 * {@link ResourceBundle}.
 *
 * @author Ishan Pranav
 */
public class BundleResourceProvider extends ResourceProvider implements Closeable
{
    private final ResourceBundle _resourceBundle;
    private final HashMap<String, Image> _imagesByKey = new HashMap<String, Image>();
    private final HashMap<String, Clip> _clipsByKey = new HashMap<String, Clip>();

    /**
     * Initializes a new instance of the {@link BundleResourceProvider} class.
     *
     * @param name The project name.
     */
    public BundleResourceProvider(String name)
    {
        this._resourceBundle = ResourceBundle.getBundle(name);
    }

    /** {@inheritDoc} */
    @Override
    public void close()
    {
        for (final Clip clip : this._clipsByKey.values())
        {
            clip.stop();
            clip.close();
        }
    }

    /** {@inheritDoc} */
    @Override
    protected Clip getClip(String key)
    {
        Clip result = this._clipsByKey.get(key);

        if (result == null)
        {
            final AudioFormat format = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, AudioSystem.NOT_SPECIFIED, 16,
                    2, 4, AudioSystem.NOT_SPECIFIED, true);
            final DataLine.Info info = new DataLine.Info(Clip.class, format);

            try (InputStream inputStream = Thread.currentThread().getContextClassLoader()
                    .getResourceAsStream("resources/audio/" + key))
            {
                try (AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(inputStream))
                {
                    result = (Clip)AudioSystem.getLine(info);

                    result.open(audioInputStream);

                    this._clipsByKey.put(key, result);
                }
                catch (final LineUnavailableException exception)
                {
                }
                catch (final UnsupportedAudioFileException exception1)
                {
                }
            }
            catch (final IOException exception)
            {
            }
        }

        result.setFramePosition(0);

        return result;
    }

    /** {@inheritDoc} */
    @Override
    protected Image getImage(String key)
    {
        Image result = this._imagesByKey.get(key);

        if (result == null)
        {
            try (InputStream inputStream = Thread.currentThread().getContextClassLoader()
                    .getResourceAsStream("resources/images/" + key))
            {
                result = ImageIO.read(inputStream);

                this._imagesByKey.put(key, result);
            }
            catch (final IOException exception)
            {
            }
        }

        return result;
    }

    /** {@inheritDoc} */
    @Override
    protected String getString(String key)
    {
        return this._resourceBundle.getString(key);
    }
}
