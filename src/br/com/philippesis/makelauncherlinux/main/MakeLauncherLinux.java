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

    // Icons title bar
    private JLabel appIcon, minimizeIcon, closeIcon;
    // Icons Main menu
    private JLabel newIcon, openIcon, launchIcon, aboutIcon, exitIcon;

    // Variables
    private String path;

    // Icons path
    private String PATH_ICONS_TITLE_BAR = "/app_icons/title-bar/";
    private String PATH_ICONS_MAIN_MENU = "/app_icons/main-menu/";
    private String PATH_ICONS_CONTEXT_MENU = "/app_icons/context-menu/";
    private String PATH_ICONS_CONTENT = "/app_icons/content/";

    private Point mouseDownCompCoords = null;

    private Utils utils;
    
    public MakeLauncherLinux() {

        utils = new Utils();
        path = utils.getAppAssetsPath();
        initComps();
    }

    private void initComps() {

        // Main Frame
        setSize(430, 370);
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
        appIcon.setIcon(new ImageIcon(path + PATH_ICONS_TITLE_BAR + "icons8-rocket-24.png"));
        appIcon.setText("  Make Launcher for Linux beta!");
        appIcon.setVerticalTextPosition(SwingConstants.CENTER);
        appIcon.setBounds(10, 10, 260, 24);
            // Icon minimize title bar
        minimizeIcon = new JLabel();
        minimizeIcon.setIcon(new ImageIcon(path + PATH_ICONS_TITLE_BAR + "icons8-minimize-window-24.png"));
        minimizeIcon.setBounds(367, 10, 24, 24);
            // Icon close title bar
        closeIcon = new JLabel();
        closeIcon.setIcon(new ImageIcon(path + PATH_ICONS_TITLE_BAR + "icons8-close-window-24.png"));
        closeIcon.setBounds(396, 10, 24, 24);
            // Add comps on title bar
        pnlTitleBar.add(appIcon);
        pnlTitleBar.add(minimizeIcon);
        pnlTitleBar.add(closeIcon);
        // Main Menu
        pnlMainMenu = new JPanel();
        pnlMainMenu.setBounds(0, 50, 600, 58);
        pnlMainMenu.setLayout(null);
            // Button New
        newIcon = new JLabel();
        newIcon.setIcon(new ImageIcon(path + PATH_ICONS_MAIN_MENU + "icons8-plus-32.png"));
        newIcon.setBounds(10, 5, 32, 32);
            // Button Open
        openIcon = new JLabel();
        openIcon.setIcon(new ImageIcon(path + PATH_ICONS_MAIN_MENU + "icons8-opened-folder-32.png"));
        openIcon.setBounds(52,5, 32, 32 );
            // Launch Icon
        launchIcon = new JLabel();
        launchIcon.setIcon(new ImageIcon(path + PATH_ICONS_MAIN_MENU + "icons8-launch-32.png"));
        launchIcon.setBounds(94, 5, 32, 32);
            // About icon
        aboutIcon = new JLabel();
        aboutIcon.setIcon(new ImageIcon(path + PATH_ICONS_MAIN_MENU + "icons8-about-32.png"));
        aboutIcon.setBounds(136, 5, 32, 32);
            // Exit icon
        exitIcon = new JLabel();
        exitIcon.setIcon(new ImageIcon(path + PATH_ICONS_MAIN_MENU + "icons8-exit-sign-32.png"));
        exitIcon.setBounds(178, 5, 32, 32);
            // Add comps on main menu
        pnlMainMenu.add(newIcon);
        pnlMainMenu.add(openIcon);
        pnlMainMenu.add(launchIcon);
        pnlMainMenu.add(aboutIcon);
        pnlMainMenu.add(exitIcon);
        // Add comps on application
        add(pnlTitleBar);
        add(pnlMainMenu);

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
                exitApp();
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
        // Mouse click release event icon new
        // Mouse entered event icon new
        newIcon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                newIcon.setToolTipText("Novo Lançador");
            }
        });
        // Mouse click release event icon open
        // Mouse entered event icon open
        openIcon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                openIcon.setToolTipText("Abrir Lançador Existente");
            }
        });
        // Mouse click release event icon launch
        // Mouse entered event icon launch
        launchIcon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                launchIcon.setToolTipText("Lançar - Gerar Lançador");
            }
        });
        // Mouse click release event icon about
        // Mouse entered event icon about
        aboutIcon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                aboutIcon.setToolTipText("Sobre a Aplicação");
            }
        });
        // Mouse click release event icon exit
        exitIcon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                exitApp();
            }
        });
        // Mouse entered event icon exit
        exitIcon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                exitIcon.setToolTipText("Sair");
            }
        });

    }

    public void exitApp() {
        Object[] options = { "SIM", "NÃO" };
        if(utils.yesNoConfirm(options, "Deseja Realmente Sair?", "Sair da aplicação",
                -1, 2, MakeLauncherLinux.this)) dispose();
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            MakeLauncherLinux makeLauncherLinux = new MakeLauncherLinux();
            makeLauncherLinux.setVisible(true);
        });
    }
    
}
