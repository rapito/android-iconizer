package com.fingled.android.tools.util;

import java.io.InputStream;

/**
 * Created by Robert Peralta on 2/12/2015.
 */
public class FileUtil
{

    public static void safeCloseInputStream(InputStream is)
    {
        if (is != null)
            try
            {
                is.close();
            }
            catch (Throwable e)
            {
                e.printStackTrace();
            }
    }
}
