package br.com.philippesis.makelauncherlinux.util;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
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

    public String[] getAppTypeList() {
        String[] list = new String[]{"Application", "Null"};
        return list;
    }

    public void addJPanelComp(JPanel component, Component addComp) {
        component.add(addComp);
    }

    public boolean makeLauncher(String name, String version, String exec, boolean terminal, String appType, String iconPath,
                                String comment, String categories, String locationLauncher) {
        boolean retorno = false;
        StringBuilder content = new StringBuilder()
                .append("##Criado por: MakeLauncher for Linux beta! 2018")
                .append("\n[Desktop Entry]")
                .append("\nVersion="+version)
                .append("\nName="+name)
                .append("\nComment="+comment)
                .append("\nTerminal="+terminal)
                .append("\nType="+appType)
                .append("\nIcon="+iconPath)
                .append("\nExec="+exec);
        try {
            FileWriter fileWriter = new FileWriter(locationLauncher + name + ".desktop", true);
            fileWriter.write(content.toString());
            fileWriter.close();
            retorno = true;
        } catch (Exception er) {
            er.printStackTrace();
        }
        return retorno;
    }

}
