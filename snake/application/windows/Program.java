package snake.application.windows;

import snake.application.BundleResourceProvider;

/**
 * Represents the application.
 *
 * @author Ishan Pranav
 */
public final class Program
{
    private Program()
    {
    }

    /**
     * Provides the main entry point for the application.
     *
     * @param args The application arguments.
     */
    public static void main(String[] args)
    {
        try (BundleResourceProvider bundleResourceProvider = new BundleResourceProvider("snake.application.Resources"))
        {
            new Game(bundleResourceProvider).start();

            while (true)
            {
            }
        }
    }
}
