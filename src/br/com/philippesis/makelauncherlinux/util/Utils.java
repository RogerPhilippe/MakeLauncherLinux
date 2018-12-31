package br.com.philippesis.makelauncherlinux.util;

import br.com.philippesis.makelauncherlinux.model.DesktopFile;
import org.jetbrains.annotations.NotNull;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;

import static java.awt.Image.SCALE_SMOOTH;

public class Utils {

    private String path;

    public Utils() {

    }

    public String getAppAssetsPath() {

        //Atualizar caminho aplicação
        try {
            path = new File("assets/").getCanonicalPath();
            //System.out.println("Caminho da aplicação: "+path);
            return path;
        } catch (IOException e1) {
            e1.printStackTrace();
            return null;
        }
    }

    public boolean yesNoConfirm(Object[] options, String msg, String title, int optionType, int messageType,
                                Component parent, String icon) {
        path = getAppAssetsPath();
        int valueJOptionPane = JOptionPane.showOptionDialog(parent, msg, title, optionType, messageType,
                new ImageIcon(path+icon), options, options[0]);
        return valueJOptionPane == 0;
    }

    public String[] getAppTypeList() {
        return new String[]{"Application", "Null"};
    }

    public boolean makeLauncher(DesktopFile desktop, String locationLauncher) {
        boolean retorno = false;
        StringBuilder content = new StringBuilder()
                .append("##Criado por: MakeLauncher for Linux beta! 2018")
                .append("\n[DesktopFile Entry]")
                .append("\nVersion=").append(desktop.getmVersion())
                .append("\nName=").append(desktop.getmName())
                .append("\nComment=").append(desktop.getmComment())
                .append("\nTerminal=").append(desktop.ismTerminal())
                .append("\nType=").append(desktop.getmAppType())
                .append("\nIcon=").append(desktop.getmIconPath())
                .append("\nExec=").append(desktop.getmExec());
        try {
            FileWriter fileWriter = new FileWriter(locationLauncher + desktop.getmName() + ".desktop", true);
            fileWriter.write(content.toString());
            fileWriter.close();
            retorno = true;
        } catch (Exception er) {
            er.printStackTrace();
        }
        return retorno;
    }

    public Image getImage(String path, int width, int height) {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(path));
        } catch (Exception er) { er.printStackTrace(); }
        return Objects.requireNonNull(img).getScaledInstance(width, height, SCALE_SMOOTH);
    }

    public void clearTextField(@NotNull JTextField component) {
        component.setText("");
    }

}
