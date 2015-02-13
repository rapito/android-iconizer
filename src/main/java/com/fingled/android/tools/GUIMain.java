package com.fingled.android.tools;

import com.fingled.android.tools.iconizer.gui.IconizerGUI;

import javax.swing.*;

/**
 * Created by Robert Peralta on 2/12/2015.
 */
public class GUIMain
{

    public static void main(String[] args)
    {
        javax.swing.SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
                try
                {
                    IconizerGUI gui = new IconizerGUI();
                    gui.showSelf();
                    gui.setResizable(false);

                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });
    }
}
