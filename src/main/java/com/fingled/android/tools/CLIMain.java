package com.fingled.android.tools;

import com.fingled.android.tools.iconizer.IconSize;
import com.fingled.android.tools.iconizer.Iconizer;
import com.fingled.android.tools.iconizer.handler.IconTypeHandler;
import org.kohsuke.args4j.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Robert Peralta on 2/12/2015.
 */
public class CLIMain
{


    private static final int CONSOLE_WIDTH = 80;

    public static void main(String[] args) throws IOException
    {
        new CLIMain().doMain(args);
    }

    @Option(name = "-i", aliases = {"--input"}, usage = "image to iconize", required = true)
    private File in = new File(".");

    @Option(name = "-o", aliases = {"--output"}, usage = "output to this folder")
    private File out = new File(".");

    @Option(name = "-n", aliases = {"--name"}, usage = "filename of resulting icon")
    private String name = "iconized";

    @Option(name = "-t", aliases = {"--type"}, handler = IconTypeHandler.class, usage = "type of icons (actionbar,contextual,notification), default is contextual")
    private IconSize.IconType type = IconSize.IconType.CONTEXTUAL;

    public void doMain(String[] args) throws IOException
    {
        CmdLineParser parser = new CmdLineParser(this);
        Iconizer iconizer;

        parser.setUsageWidth(CONSOLE_WIDTH);
        try
        {
            parser.parseArgument(args);
            if (in == null && out == null && name == null)
                throw new CmdLineException(parser, "At least one argument should be passed");
        }
        catch (CmdLineException e)
        {
            handleCLIException(parser, e);
            return;
        }

        try
        {
            iconizer = new Iconizer(in, out, name, type);
            iconizer.iconize();
        }
        catch (Exception e)
        {
            System.err.println("Couldn't iconize image: " + e.getMessage());
        }

        String output = "Resized " + in + " successfully to " + out;
        System.out.println(output);

    }

    private void handleCLIException(CmdLineParser parser, CmdLineException e)
    {
        System.err.println(e.getMessage());
        System.err.println("java iconizer [options...] arguments...");
        parser.printUsage(System.err);
        System.err.println();

        System.err.println("  Example: java iconizer" + parser.printExample(ExampleMode.ALL));

        return;
    }

}
