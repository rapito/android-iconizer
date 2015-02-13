package com.fingled.android.tools.gui;

import com.fingled.android.tools.GUIMain;
import com.fingled.android.tools.iconizer.IconSize;
import com.fingled.android.tools.iconizer.Iconizer;
import com.fingled.android.tools.util.FileUtil;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

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


        initGUI();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void initGUI()
    {
        initAppIcon();

        JLabel inputLabel = new JLabel("Input Image: ");
        JLabel outputLabel = new JLabel("Output Location: ");
        JLabel nameLabel = new JLabel("Icon Name: (optional)");
        ButtonGroup group = new ButtonGroup();
        JPanel radioButtonPanel = new JPanel();

        inputButton = new JButton("Select Input...");
        outputButton = new JButton("Select target Directory...");
        inputName = new JTextField();

        contextualRadio = new JRadioButton("contextual");
        actionbarRadio = new JRadioButton("actionbar");
        notificationRadio = new JRadioButton("notification");

        imageIcon = new JLabel();
        iconizeButton = new JButton("Iconize!");

        initActionListeners();
        addComponents(group, inputLabel, outputLabel, nameLabel, radioButtonPanel);
    }

    private void initAppIcon()
    {
        InputStream is = null;
        try
        {
            is = getClass().getResourceAsStream("/gui-icon.png");
            setIconImage(ImageIO.read(is));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            FileUtil.safeCloseInputStream(is);
        }
    }

    private void addComponents(ButtonGroup group, JLabel inputLabel, JLabel outputLabel, JLabel nameLabel, JPanel radioButtonPanel)
    {
        group.add(contextualRadio);
        group.add(actionbarRadio);
        group.add(notificationRadio);

        add(inputLabel);
        add(inputButton);
        add(outputLabel);
        add(outputButton);
        add(nameLabel);
        add(inputName);

        radioButtonPanel.add(contextualRadio);
        radioButtonPanel.add(actionbarRadio);
        radioButtonPanel.add(notificationRadio);
        add(radioButtonPanel);

        add(iconizeButton);
        add(imageIcon);
    }

    private void initActionListeners()
    {
        contextualRadio.setSelected(true);
        contextualRadio.addActionListener(this);
        actionbarRadio.addActionListener(this);
        notificationRadio.addActionListener(this);
        inputButton.addActionListener(this);
        outputButton.addActionListener(this);
        iconizeButton.addActionListener(this);
    }

    private void iconizeButtonAction()
    {
        if (invalidUserInput()) return;

        IconSize.IconType type = getIconType();

        iconizer = new Iconizer(inputFile, outputDir, inputName.getText(), type);
        try
        {
            iconizer.iconize();
        }
        catch (Exception ex)
        {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }

        successIconizeAction();
    }

    private void successIconizeAction()
    {
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

    private void outputButtonAction()
    {
        JFileChooser fc = createDirectoryFileChooser();

        int result = fc.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION)
        {
            outputDir = fc.getSelectedFile();
            outputButton.setText(outputDir.getName());
        }
    }

    private void inputButtonAction()
    {

        JFileChooser fc = createInputFileChooser();

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

    private boolean invalidUserInput()
    {
        if (outputDir == null)
        {
            JOptionPane.showMessageDialog(this, "Error: You must choose output Directory");
            return true;
        }
        else if (inputFile == null)
        {
            JOptionPane.showMessageDialog(this, "Error: You must choose input Image");
            return true;
        }
        return false;
    }

    private IconSize.IconType getIconType()
    {
        IconSize.IconType type = IconSize.IconType.CONTEXTUAL;
        if (actionbarRadio.isSelected()) type = IconSize.IconType.ACTIONBAR;
        else if (notificationRadio.isSelected()) type = IconSize.IconType.NOTIFICATION;
        return type;
    }

    private JFileChooser createDirectoryFileChooser()
    {
        JFileChooser fc = new JFileChooser();
        fc.setCurrentDirectory(new File("."));
        fc.setDialogTitle("Select Output Directory");
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fc.setAcceptAllFileFilterUsed(false);
        return fc;
    }

    private JFileChooser createInputFileChooser()
    {
        JFileChooser fc = new JFileChooser();
        fc.setCurrentDirectory(new File("."));
        fc.setDialogTitle("Select Input Image");
        fc.setFileFilter(new FileNameExtensionFilter("Image files", ImageIO.getReaderFileSuffixes()));
        return fc;
    }

    public void showSelf()
    {
        pack();
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        Object source = e.getSource();
        if (source == inputButton)
        {
            inputButtonAction();
        }
        else if (source == outputButton)
        {
            outputButtonAction();
        }
        else if (source == iconizeButton)
        {
            iconizeButtonAction();
        }
    }
}
