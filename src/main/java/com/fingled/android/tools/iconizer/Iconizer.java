package com.fingled.android.tools.iconizer;

import java.io.File;
import java.io.IOException;

/**
 * Created by Robert Peralta on 2/12/2015.
 */
public class Iconizer
{

    private final IconSize.IconType iconType;
    private       String            name;
    private       File              out;
    private final File              in;

    public Iconizer(File in, File out, String name, IconSize.IconType iconType)
    {
        this.in = in;
        this.out = out;
        this.name = name;
        this.iconType = iconType;
    }

    public void iconize() throws IOException
    {
        validateInputFile();
        validateFileName();
        validateOuputFile();

        resizeAndSaveIcons();
    }

    private void resizeAndSaveIcons()
    {
        if (iconType == IconSize.IconType.ACTIONBAR)
            resizeAndSaveActionbarIcons();
        if (iconType == IconSize.IconType.NOTIFICATION)
            resizeAndSaveNotificationIcons();
        if (iconType == IconSize.IconType.CONTEXTUAL)
            resizeAndSaveContextualIcons();
    }

    private void resizeAndSaveContextualIcons()
    {

    }

    private void resizeAndSaveNotificationIcons()
    {

    }

    private void resizeAndSaveActionbarIcons()
    {

    }

    private void validateOuputFile() throws IOException
    {
        if (out == null)
        {
            out = new File(name);
        }
        else if (!out.isDirectory())
        {
            throw new IOException("output is not a directory");
        }

        if (!out.exists() && !(out.mkdir() || out.mkdirs()))
        {
            throw new IOException("output directory could not be created, " +
                                  "make sure you have enough privileges or that the path is not blocked by other app");
        }
    }

    private void validateFileName()
    {
        if (name == null || name.length() == 0)
            name = in.getName();
    }

    private void validateInputFile()
    {
        if (in == null)
            throw new NullPointerException("input filename shouldn't be null");
    }


    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public File getOut()
    {
        return out;
    }

    public File getIn()
    {
        return in;
    }
}
