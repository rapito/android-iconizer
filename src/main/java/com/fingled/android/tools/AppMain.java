package com.fingled.android.tools;

import java.io.IOException;

/**
 * Application Main entry point to decide between cli or gui.
 * <p/>
 * Created by Robert Peralta on 2/12/2015.
 */
public class AppMain
{

    public static void main(String[] args) throws IOException
    {
        if (args == null || args.length <= 0)
        {
            GUIMain.main(args);
        }
        else
        {
            CLIMain.main(args);
        }

    }
}
