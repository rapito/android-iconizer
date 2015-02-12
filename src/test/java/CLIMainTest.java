import com.fingled.android.tools.CLIMain;
import com.fingled.android.tools.iconizer.Iconizer;
import junit.framework.Assert;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class CLIMainTest
{

    private static final String TEST_PATH     = "C:\\test\\android-iconizer\\";
    private static final String TEST_FILENAME = "iconizer.png";
    private static final String TEST_PATH_IN  = TEST_PATH + "input\\" + TEST_FILENAME;
    private static final String TEST_PATH_OUT = TEST_PATH + "output\\";

    CLIMain  cli;
    String[] args;

    @BeforeClass
    public static void setUpResources() throws IOException
    {

        InputStream is = CLIMainTest.class.getResourceAsStream(TEST_FILENAME);

        BufferedImage image = ImageIO.read(is);
        File file = new File(TEST_PATH_IN);
        file.mkdirs();

        ImageIO.write(image, "png", file);

        is.close();
    }

    @AfterClass
    public static void destroyResources() throws IOException
    {
        Path directory = Paths.get(TEST_PATH);
        Files.walkFileTree(directory, new SimpleFileVisitor<Path>()
        {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException
            {
                Files.delete(file);
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException
            {
                Files.delete(dir);
                return FileVisitResult.CONTINUE;
            }
        });
    }

    @Before
    public void setUp() throws Exception
    {
        cli = new CLIMain();
    }

    @Test
    public void testWithAllArgs() throws Exception
    {
        args = ("-i com.fingled.android.tools.CLIMain.class -i " + TEST_PATH_IN + " -o " + TEST_PATH_OUT + " -n myicon")
                .split(" ");

        cli.doMain(args);

        Assert.assertTrue(validPaths());
    }

    private boolean validPaths()
    {
        String[] paths = {
                "drawable-ldpi",
                "drawable-mdpi",
                "drawable-hdpi",
                "drawable-xhdpi",
                "drawable-xxhdpi",
                "drawable-xxxhdpi"};

        for (String p : paths)
        {
            File f = new File(TEST_PATH_OUT + p);
            if (!f.exists()) return false;
        }

        return true;
    }
}