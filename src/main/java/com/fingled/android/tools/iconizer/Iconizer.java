package com.fingled.android.tools.iconizer;

import com.sun.javafx.sg.PGShape;
import org.imgscalr.Scalr;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.io.File;
import java.io.IOException;

/**
 * Created by Robert Peralta on 2/12/2015.
 */
public class Iconizer
{

    private static final String TEXT_DRAWABLE = "drawable-";
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

    private void resizeAndSaveIcons() throws IOException
    {
        if (iconType == IconSize.IconType.ACTIONBAR)
            resizeAndSaveActionbarIcons();
        if (iconType == IconSize.IconType.NOTIFICATION)
            resizeAndSaveNotificationIcons();
        if (iconType == IconSize.IconType.CONTEXTUAL)
            resizeAndSaveContextualIcons();
    }

    private void resizeAndSaveContextualIcons() throws IOException
    {
        BufferedImage inputImg = ImageIO.read(in);

        BufferedImage ldpi = Scalr.resize(inputImg, IconSize.SIZE_CONTEXTUAL_LDPI, IconSize.SIZE_CONTEXTUAL_LDPI, Scalr.OP_ANTIALIAS);
        BufferedImage mdpi = Scalr.resize(inputImg, IconSize.SIZE_CONTEXTUAL_MDPI, IconSize.SIZE_CONTEXTUAL_MDPI, Scalr.OP_ANTIALIAS);
        BufferedImage hdpi = Scalr.resize(inputImg, IconSize.SIZE_CONTEXTUAL_HDPI, IconSize.SIZE_CONTEXTUAL_HDPI, Scalr.OP_ANTIALIAS);
        BufferedImage xhdpi = Scalr.resize(inputImg, IconSize.SIZE_CONTEXTUAL_XHDPI, IconSize.SIZE_CONTEXTUAL_XHDPI, Scalr.OP_ANTIALIAS);
        BufferedImage xxhdpi = Scalr.resize(inputImg, IconSize.SIZE_CONTEXTUAL_XXHDPI, IconSize.SIZE_CONTEXTUAL_XXHDPI, Scalr.OP_ANTIALIAS);
        BufferedImage xxxhdpi = Scalr.resize(inputImg, IconSize.SIZE_CONTEXTUAL_XXXHDPI, IconSize.SIZE_CONTEXTUAL_XXXHDPI, Scalr.OP_ANTIALIAS);
        BufferedImage playStoreImg = Scalr.resize(inputImg, IconSize.SIZE_PLAY_STORE, IconSize.SIZE_PLAY_STORE, Scalr.OP_ANTIALIAS);

        writeImagePack(ldpi, mdpi, hdpi, xhdpi, xxhdpi, xxxhdpi);
        writeImage(playStoreImg, null);
    }

    private void resizeAndSaveNotificationIcons() throws IOException
    {
        BufferedImage inputImg = ImageIO.read(in);

        BufferedImage ldpi = Scalr.resize(inputImg, IconSize.SIZE_NOTIFICATION_LDPI, IconSize.SIZE_NOTIFICATION_LDPI, Scalr.OP_ANTIALIAS);
        BufferedImage mdpi = Scalr.resize(inputImg, IconSize.SIZE_NOTIFICATION_MDPI, IconSize.SIZE_NOTIFICATION_MDPI, Scalr.OP_ANTIALIAS);
        BufferedImage hdpi = Scalr.resize(inputImg, IconSize.SIZE_NOTIFICATION_HDPI, IconSize.SIZE_NOTIFICATION_HDPI, Scalr.OP_ANTIALIAS);
        BufferedImage xhdpi = Scalr.resize(inputImg, IconSize.SIZE_NOTIFICATION_XHDPI, IconSize.SIZE_NOTIFICATION_XHDPI, Scalr.OP_ANTIALIAS);
        BufferedImage xxhdpi = Scalr.resize(inputImg, IconSize.SIZE_NOTIFICATION_XXHDPI, IconSize.SIZE_NOTIFICATION_XXHDPI, Scalr.OP_ANTIALIAS);
        BufferedImage xxxhdpi = Scalr.resize(inputImg, IconSize.SIZE_NOTIFICATION_XXXHDPI, IconSize.SIZE_NOTIFICATION_XXXHDPI, Scalr.OP_ANTIALIAS);

        writeImagePack(ldpi, mdpi, hdpi, xhdpi, xxhdpi, xxxhdpi);
    }

    private void resizeAndSaveActionbarIcons() throws IOException
    {
        BufferedImage inputImg = ImageIO.read(in);

        BufferedImage ldpi = Scalr.resize(inputImg, IconSize.SIZE_ACTIONBAR_LDPI, IconSize.SIZE_ACTIONBAR_LDPI, Scalr.OP_ANTIALIAS);
        BufferedImage mdpi = Scalr.resize(inputImg, IconSize.SIZE_ACTIONBAR_MDPI, IconSize.SIZE_ACTIONBAR_MDPI, Scalr.OP_ANTIALIAS);
        BufferedImage hdpi = Scalr.resize(inputImg, IconSize.SIZE_ACTIONBAR_HDPI, IconSize.SIZE_ACTIONBAR_HDPI, Scalr.OP_ANTIALIAS);
        BufferedImage xhdpi = Scalr.resize(inputImg, IconSize.SIZE_ACTIONBAR_XHDPI, IconSize.SIZE_ACTIONBAR_XHDPI, Scalr.OP_ANTIALIAS);
        BufferedImage xxhdpi = Scalr.resize(inputImg, IconSize.SIZE_ACTIONBAR_XXHDPI, IconSize.SIZE_ACTIONBAR_XXHDPI, Scalr.OP_ANTIALIAS);
        BufferedImage xxxhdpi = Scalr.resize(inputImg, IconSize.SIZE_ACTIONBAR_XXXHDPI, IconSize.SIZE_ACTIONBAR_XXXHDPI, Scalr.OP_ANTIALIAS);

        writeImagePack(ldpi, mdpi, hdpi, xhdpi, xxhdpi, xxxhdpi);
    }

    private void validateOuputFile() throws IOException
    {
        if (out == null)
        {
            out = new File(name);
        }
        else if (out.exists() && !out.isDirectory())
        {
            throw new IOException("output is not a directory");
        }

        if (!out.exists() && !(out.mkdir() || out.mkdirs()))
        {
            throw new IOException("output directory could not be created, " +
                                  "make sure you have enough privileges or that the path is not blocked by other app");
        }
    }

    private void writeImagePack(BufferedImage ldpi, BufferedImage mdpi, BufferedImage hdpi, BufferedImage xhdpi, BufferedImage xxhdpi, BufferedImage xxxhdpi) throws IOException
    {
        writeImage(ldpi, TEXT_DRAWABLE + "ldpi");
        writeImage(mdpi, TEXT_DRAWABLE + "mdpi");
        writeImage(hdpi, TEXT_DRAWABLE + "hdpi");
        writeImage(xhdpi, TEXT_DRAWABLE + "xhdpi");
        writeImage(xxhdpi, TEXT_DRAWABLE + "xxhdpi");
        writeImage(xxxhdpi, TEXT_DRAWABLE + "xxxhdpi");
    }

    private void writeImage(BufferedImage image, String directory) throws IOException
    {

        String path = directory != null && directory.trim().length() > 0 ?
                      out.getAbsolutePath() + "/" + directory + "/" + name + ".png" :
                      out.getAbsolutePath() + "/" + name + ".png";
        File file = new File(path);
        file.mkdirs();
        if (!ImageIO.write(image, "png", file))
        {
            throw new IOException("There was an error trying to save the resized images to disk, check that you have " +
                                  "enough disk space available or sufficient user privileges on the output directory.");
        }

    }

    private void validateFileName()
    {
        if (name == null || name.length() == 0)
        {
            name = in.getName();
            name = name.substring(0, name.lastIndexOf('.'));
        }
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
