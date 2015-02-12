import com.fingled.android.tools.CLIMain;
import org.junit.Before;
import org.junit.Test;

public class CLIMainTest
{

    CLIMain  cli;
    String[] args;

    @Before
    public void setUp() throws Exception
    {
        cli = new CLIMain();
    }

    @Test
    public void testDoMain() throws Exception
    {
        args= "-i com.fingled.android.tools.CLIMain.class -o a.txt -name \"myicon\"".split(" ");

        cli.doMain(args);
    }
}