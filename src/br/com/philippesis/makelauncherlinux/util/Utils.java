package br.com.philippesis.makelauncherlinux.util;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

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

    public boolean yesNoConfirm(Object[] options, String msg, String title, int optionType, int messageType, Component parent) {
        path = getAppAssetsPath();
        int valueJOptionPane = JOptionPane.showOptionDialog(parent, msg, title, optionType, messageType,
                new ImageIcon(path+"/app_icons/app/icons8-siren-32.png"), options, options[0]);
        if(valueJOptionPane == 0) return true;
        else return false;
    }


}
