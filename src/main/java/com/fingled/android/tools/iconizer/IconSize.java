package com.fingled.android.tools.iconizer;

/**
 * Icon Sizes based on:
 * <ul>
 * <li>
 * <a href="http://www.wiseman-designs.com/wp-content/uploads/2011/09/Android-Icon-Guidelines-Poster.pdf">wiseman-designs.com</a>
 * </li>
 * <li>
 * <a href="http://iconhandbook.co.uk/reference/chart/android/">iconhandbook.co.uk/</a>
 * </li>
 * </ul>
 * Created by Robert Peralta on 2/12/2015.
 */
public class IconSize
{

    /**
     * Google Play store Icon Size
     */
    public final static int SIZE_PLAY_STORE           = 512;
    /**
     * Android icons require five separate sizes for
     * different screen pixel densities. Icons for lower
     * resolution are created automatically from the
     * baseline.
     * <p/>
     * Used to surface actions and/or provide status for
     * specific items. For example, in the Gmail app, each
     * message has a star icon that marks the message as
     * important.
     */
    public final static int SIZE_CONTEXTUAL_LDPI      = 36;
    public final static int SIZE_CONTEXTUAL_MDPI      = 48;
    public final static int SIZE_CONTEXTUAL_HDPI      = 72;
    public final static int SIZE_CONTEXTUAL_XHDPI     = 96;
    public final static int SIZE_CONTEXTUAL_XXHDPI    = 144;
    public final static int SIZE_CONTEXTUAL_XXXHDPI   = 192;
    /**
     * These icons are used in the action bar menu. The
     * first number is the size of the icon area, and
     * the second is file size.
     */
    public final static int SIZE_ACTIONBAR_LDPI       = 18;
    public final static int SIZE_ACTIONBAR_MDPI       = 32;
    public final static int SIZE_ACTIONBAR_HDPI       = 48;
    public final static int SIZE_ACTIONBAR_XHDPI      = 64;
    public final static int SIZE_ACTIONBAR_XXHDPI     = 96;
    public final static int SIZE_ACTIONBAR_XXXHDPI    = 128;
    /**
     * These are used to represent application notifications
     * in the status bar. They should be flat (no gradients),
     * white and face-on perspective
     */
    public final static int SIZE_NOTIFICATION_LDPI    = 18;
    public final static int SIZE_NOTIFICATION_MDPI    = 24;
    public final static int SIZE_NOTIFICATION_HDPI    = 36;
    public final static int SIZE_NOTIFICATION_XHDPI   = 48;
    public final static int SIZE_NOTIFICATION_XXHDPI  = 72;
    public final static int SIZE_NOTIFICATION_XXXHDPI = 96;

    /**
     * Icon Types available
     * to resize the icons.
     */
    public enum IconType
    {
        CONTEXTUAL,
        NOTIFICATION,
        ACTIONBAR
    }
}
