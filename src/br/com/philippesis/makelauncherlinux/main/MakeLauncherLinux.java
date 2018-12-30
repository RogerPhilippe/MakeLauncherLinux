package br.com.philippesis.makelauncherlinux.main;

import br.com.philippesis.makelauncherlinux.util.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class MakeLauncherLinux extends JFrame {

    // Panels
    private JPanel pnlTitleBar, pnlMainMenu, pnlContent, pnlFooter;

    // Icons
    private JLabel appIcon, minimizeIcon, closeIcon;

    // Variables
    private String path;

    private Point mouseDownCompCoords = null;

    private Utils utils;
    
    public MakeLauncherLinux() {

        utils = new Utils();
        path = utils.getAppAssetsPath();
        initComps();
    }

    private void initComps() {

        // Main Frame
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setUndecorated(true);
        setLayout(null);
        // Title Bar
        pnlTitleBar = new JPanel();
        pnlTitleBar.setBounds(0, 0, 600, 44);
        pnlTitleBar.setLayout(null);
        // Icon APP title bar
        appIcon = new JLabel();
        appIcon.setIcon(new ImageIcon(path+ "/app_icons/title-bar/icons8-rocket-24.png"));
        appIcon.setText("  Make Launcher for Linux beta!");
        appIcon.setVerticalTextPosition(SwingConstants.CENTER);
        appIcon.setBounds(10, 10, 260, 24);
        // Icon minimize title bar
        minimizeIcon = new JLabel();
        minimizeIcon.setIcon(new ImageIcon(path+ "/app_icons/title-bar/icons8-minimize-window-24.png"));
        minimizeIcon.setBounds(537, 10, 24, 24);
        // Icon close title bar
        closeIcon = new JLabel();
        closeIcon.setIcon(new ImageIcon(path+ "/app_icons/title-bar/icons8-close-window-24.png"));
        closeIcon.setBounds(566, 10, 24, 24);
        // Add comps on title bar
        pnlTitleBar.add(appIcon);
        pnlTitleBar.add(minimizeIcon);
        pnlTitleBar.add(closeIcon);
        // Add comps on application
        add(pnlTitleBar);

        // Eventos
        // Mouse drag
        pnlTitleBar.addMouseListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent e) {
                mouseDownCompCoords = null;
            }

            public void mousePressed(MouseEvent e) {
                mouseDownCompCoords = e.getPoint();
                // System.out.println("Cords: " + e.getPoint());
                // System.out.println("Location: " + e.getLocationOnScreen());
            }
        });
        pnlTitleBar.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                Point currCoords = e.getLocationOnScreen();
                setLocation(currCoords.x - mouseDownCompCoords.x, currCoords.y - mouseDownCompCoords.y);
            }
        });
        //Fim mouse drag
        // Mouse click release event icon close
        closeIcon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                Object[] options = { "SIM", "NÃO" };
                if(utils.yesNoConfirm(options, "Deseja Realmente Sair?", "Sair da aplicação",
                        -1, 2, MakeLauncherLinux.this)) dispose();
            }
        });
        // Mouse entered event icon close
        closeIcon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                closeIcon.setToolTipText("Fechar");
            }
        });
        // Mouse click release event icon minimize
        minimizeIcon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                // Minimizar aplicação
                setState(Frame.ICONIFIED);
            }
        });
        // Mouse entered event icon minimize
        minimizeIcon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                minimizeIcon.setToolTipText("Minimizar");
            }
        });

    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            MakeLauncherLinux makeLauncherLinux = new MakeLauncherLinux();
            makeLauncherLinux.setVisible(true);
        });
    }
    
}
