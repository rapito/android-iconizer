package com.fingled.android.tools.gui;

import com.fingled.android.tools.iconizer.IconSize;
import com.fingled.android.tools.iconizer.Iconizer;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

/**
 * Created by Robert Peralta on 2/12/2015.
 */
public class IconizerGUI extends JFrame implements ActionListener
{

    private JLabel       imageIcon;
    private JRadioButton contextualRadio;
    private JRadioButton actionbarRadio;
    private JRadioButton notificationRadio;
    private JTextField   inputName;
    private JButton      iconizeButton;
    private JButton      outputButton;
    private JButton      inputButton;
    private File         inputFile;
    private File         outputDir;
    private Iconizer     iconizer;

    public IconizerGUI() throws HeadlessException
    {
        super("Android Iconizer");

        setLayout(new GridLayout(5, 2));

        JLabel inputLabel = new JLabel("Input Image: ");
        JLabel outputLabel = new JLabel("Output Location: ");
        JLabel nameLabel = new JLabel("Icon Name: (optional)");

        inputButton = new JButton("Select Input...");
        outputButton = new JButton("Select target Directory...");
        inputName = new JTextField();

        contextualRadio = new JRadioButton("contextual");
        actionbarRadio = new JRadioButton("actionbar");
        notificationRadio = new JRadioButton("notification");

        ButtonGroup group = new ButtonGroup();
        group.add(contextualRadio);
        group.add(actionbarRadio);
        group.add(notificationRadio);

        imageIcon = new JLabel();

        contextualRadio.setSelected(true);
        contextualRadio.addActionListener(this);
        actionbarRadio.addActionListener(this);
        notificationRadio.addActionListener(this);

        iconizeButton = new JButton("Iconize!");

        inputButton.addActionListener(this);
        outputButton.addActionListener(this);
        iconizeButton.addActionListener(this);

        add(inputLabel);
        add(inputButton);
        add(outputLabel);
        add(outputButton);
        add(nameLabel);
        add(inputName);

        JPanel panel = new JPanel();
        panel.add(contextualRadio);
        panel.add(actionbarRadio);
        panel.add(notificationRadio);
        add(panel);

        add(iconizeButton);
        add(imageIcon);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }


    @Override
    public void actionPerformed(ActionEvent e)
    {
        Object source = e.getSource();
        if (source == inputButton)
        {
            JFileChooser fc = new JFileChooser();
            fc.setCurrentDirectory(new File("."));
            fc.setDialogTitle("Select Input Image");
            fc.setFileFilter(new FileNameExtensionFilter("Image files", ImageIO.getReaderFileSuffixes()));

            int result = fc.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION)
            {
                inputFile = fc.getSelectedFile();
                inputButton.setText(inputFile.getName());

                try
                {
                    imageIcon.setIcon(new ImageIcon(ImageIO.read(inputFile)));
                }
                catch (IOException ex)
                {
                    JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
                }
            }
        }
        else if (source == outputButton)
        {

            JFileChooser fc = new JFileChooser();
            fc.setCurrentDirectory(new File("."));
            fc.setDialogTitle("Select Output Directory");
            fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            fc.setAcceptAllFileFilterUsed(false);

            int result = fc.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION)
            {
                outputDir = fc.getSelectedFile();
                outputButton.setText(outputDir.getName());
            }
        }
        else if (source == iconizeButton)
        {

            if(outputDir == null)
            {
                JOptionPane.showMessageDialog(this, "Error: You must choose output Directory");
                return;
            }
            else if(inputFile == null)
            {
                JOptionPane.showMessageDialog(this, "Error: You must choose input Image");
                return;
            }

            IconSize.IconType type = IconSize.IconType.CONTEXTUAL;
            if (actionbarRadio.isSelected()) type = IconSize.IconType.ACTIONBAR;
            else if (notificationRadio.isSelected()) type = IconSize.IconType.NOTIFICATION;

            iconizer = new Iconizer(inputFile, outputDir, inputName.getText(), type);
            try
            {
                iconizer.iconize();
            }
            catch (Exception ex)
            {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }

            String info = "Resized " + inputFile + " successfully to " + outputDir;
            JOptionPane.showMessageDialog(this, info);
            if (Desktop.isDesktopSupported())
            {
                try
                {
                    Desktop.getDesktop().open(outputDir);
                }
                catch (IOException ex)
                {
                    // We don't really care if we couldn't open the folder
                    ex.printStackTrace();
                }
            }
        }
    }

    public void showSelf()
    {
        pack();
        setVisible(true);
    }
}
