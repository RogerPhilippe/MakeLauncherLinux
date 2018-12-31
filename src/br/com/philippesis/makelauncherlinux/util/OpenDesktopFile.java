package br.com.philippesis.makelauncherlinux.util;

import br.com.philippesis.makelauncherlinux.model.DesktopFile;

import javax.swing.*;
import java.io.FileInputStream;
import java.util.Properties;

public class OpenDesktopFile {

    public DesktopFile lerProp(String locationFile) {
        Properties prop = getProp(locationFile);
        String version = String.valueOf(prop.getProperty("Version"));
        String name = String.valueOf(prop.getProperty("Name"));
        String comment = String.valueOf(prop.getProperty("Comment"));
        boolean terminal = Boolean.parseBoolean(prop.getProperty("Terminal"));
        String appType = String.valueOf(prop.getProperty("Type"));
        String iconPath = String.valueOf(prop.getProperty("Icon"));
        String exec = String.valueOf(prop.getProperty("Exec"));
        return new DesktopFile.Builder()
                .setVersion(version)
                .setName(name)
                .setComment(comment)
                .setTerminal(terminal)
                .setAppType(appType)
                .setIconPath(iconPath)
                .setExec(exec)
                .build();
    }
    private static Properties getProp(String locationFile) {
        Properties props = new Properties();
        try {
            FileInputStream file = new FileInputStream(locationFile);
            props.load(file);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "E04. " + e);
        }
        return props;
    }

}
