package br.com.philippesis.makelauncherlinux.main;

import br.com.philippesis.makelauncherlinux.model.DesktopFile;
import br.com.philippesis.makelauncherlinux.util.OpenDesktopFile;
import br.com.philippesis.makelauncherlinux.util.Utils;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.IOException;
import java.util.Objects;

public class MakeLauncherLinux extends JFrame {

    // Variables
    private String path, iconPath, locationLauncher;

    private Point mouseDownCompCoords = null;

    private JFileChooser fc;

    private Utils utils;
    
    private MakeLauncherLinux() {
        utils = new Utils();
        path = utils.getAppAssetsPath();
        initComps();
    }

    private void initComps() {

        // Paths images
        String PATH_ICONS_TITLE_BAR = "/app_icons/title-bar/";
        String PATH_ICONS_MAIN_MENU = "/app_icons/main-menu/";
        String PATH_ICONS_CONTENT = "/app_icons/content/";
        String PATH_ICONS_APP = "/app_icons/app/";
        //String PATH_ICONS_CONTEXT_MENU = "/app_icons/context-menu/";

        // Main Frame
        setSize(430, 370);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setUndecorated(true);
        setLayout(null);
        // App Icon
        setIconImage(Toolkit.getDefaultToolkit().getImage(path + PATH_ICONS_APP + "icons8-rocket-48.png"));
        // Title Bar
        JPanel pnlTitleBar = new JPanel();
        pnlTitleBar.setBounds(0, 0, 430, 44);
        pnlTitleBar.setLayout(null);
            // Icon APP title bar
        JLabel appIcon = new JLabel();
        // Icons path
        appIcon.setIcon(new ImageIcon(path + PATH_ICONS_TITLE_BAR + "icons8-rocket-24.png"));
        appIcon.setText("  Make Launcher for Linux beta!");
        appIcon.setVerticalTextPosition(SwingConstants.CENTER);
        appIcon.setBounds(10, 10, 260, 24);
            // Icon minimize title bar
        JLabel minimizeIcon = new JLabel();
        minimizeIcon.setIcon(new ImageIcon(path + PATH_ICONS_TITLE_BAR + "icons8-minimize-window-24.png"));
        minimizeIcon.setBounds(367, 10, 24, 24);
            // Icon close title bar
        JLabel closeIcon = new JLabel();
        closeIcon.setIcon(new ImageIcon(path + PATH_ICONS_TITLE_BAR + "icons8-close-window-24.png"));
        closeIcon.setBounds(396, 10, 24, 24);
            // Add comps on title bar
        pnlTitleBar.add(appIcon);
        pnlTitleBar.add(minimizeIcon);
        pnlTitleBar.add(closeIcon);
        // Main Menu
        JPanel pnlMainMenu = new JPanel();
        pnlMainMenu.setBounds(0, 50, 430, 42);
        pnlMainMenu.setLayout(null);
            // Button New
        JLabel newIcon = new JLabel();
        newIcon.setIcon(new ImageIcon(path + PATH_ICONS_MAIN_MENU + "icons8-plus-32.png"));
        newIcon.setBounds(10, 5, 32, 32);
            // Button Open
        JLabel openIcon = new JLabel();
        openIcon.setIcon(new ImageIcon(path + PATH_ICONS_MAIN_MENU + "icons8-opened-folder-32.png"));
        openIcon.setBounds(52,5, 32, 32 );
            // Launch Icon
        JLabel launchIcon = new JLabel();
        launchIcon.setIcon(new ImageIcon(path + PATH_ICONS_MAIN_MENU + "icons8-launch-32.png"));
        launchIcon.setBounds(94, 5, 32, 32);
            // About icon
        JLabel aboutIcon = new JLabel();
        aboutIcon.setIcon(new ImageIcon(path + PATH_ICONS_MAIN_MENU + "icons8-about-32.png"));
        aboutIcon.setBounds(136, 5, 32, 32);
            // Exit icon
        JLabel exitIcon = new JLabel();
        exitIcon.setIcon(new ImageIcon(path + PATH_ICONS_MAIN_MENU + "icons8-exit-sign-32.png"));
        exitIcon.setBounds(178, 5, 32, 32);
            // Add comps on main menu
        pnlMainMenu.add(newIcon);
        pnlMainMenu.add(openIcon);
        pnlMainMenu.add(launchIcon);
        pnlMainMenu.add(aboutIcon);
        pnlMainMenu.add(exitIcon);
        // Content Panel
        JPanel pnlContent = new JPanel();
        pnlContent.setBounds(0, 92, 430, 260);
        pnlContent.setLayout(null);
            // Label Name
        JLabel lbName = new JLabel("Nome");
        lbName.setBounds(10, 10, 44, 18);
            // Edit Name
        JTextField tfName = new JTextField();
        tfName.setBounds(10, 30, 260, 22);
            // Label App Type
        JLabel lbAppType = new JLabel("Tipo da Aplicação");
        lbAppType.setBounds(10, 54, 130, 22);
            // Combo Type
        String[] items = utils.getAppTypeList();
        JComboBox cbAppType = new JComboBox();
        for (String item:items) { cbAppType.addItem(item); }
        cbAppType.setBounds(10, 78, 260, 22);
            // Label App path/command
        JLabel lbAppPathCommand = new JLabel("Aplicação/Comando");
        lbAppPathCommand.setBounds(10, 106, 140, 22);
            // Edit App path/command
        JTextField tfAppCommand = new JTextField();
        tfAppCommand.setBounds(10, 130, 368, 22);
            // Button Search
        JLabel openContentIcon = new JLabel();
        openContentIcon.setIcon(new ImageIcon(path + PATH_ICONS_CONTENT + "icons8-more-16.png"));
        openContentIcon.setBounds(382, 133, 16, 16);
            // Button Paste
        JLabel pasteIcon = new JLabel();
        pasteIcon.setIcon(new ImageIcon(path + PATH_ICONS_CONTENT + "icons8-paste-16.png"));
        pasteIcon.setBounds(404, 133, 16, 16);
            // Edit exec on terminal
        JCheckBox checkBoxExecTerminal = new JCheckBox();
        checkBoxExecTerminal.setText("Exec via Terminal");
        checkBoxExecTerminal.setBounds(190, 173, 160, 22);
            // Label version
        JLabel lbVersion = new JLabel("Versão");
        lbVersion.setBounds(10, 153, 60, 22);
            // Edit version
        JTextField tfVersion = new JTextField();
        tfVersion.setBounds(10, 175, 160, 22);
            // Label comment
        JLabel lbComment = new JLabel("Comentário");
        lbComment.setBounds(10, 200, 90, 22);
            // Edit comment
        JTextField tfComment = new JTextField();
        tfComment.setBounds(10, 223, 410, 22);
            // Photo
        JLabel photoIcon = new JLabel();
        photoIcon.setText("Icone");
        photoIcon.setVerticalTextPosition(SwingConstants.CENTER);
        photoIcon.setHorizontalTextPosition(SwingConstants.CENTER);
        photoIcon.setBorder(BorderFactory.createLineBorder(Color.black));
        photoIcon.setBounds(300, 16, 96, 96);
            // Add comps Content Panel
        pnlContent.add(lbName);
        pnlContent.add(tfName);
        pnlContent.add(lbAppType);
        pnlContent.add(cbAppType);
        pnlContent.add(lbAppPathCommand);
        pnlContent.add(tfAppCommand);
        pnlContent.add(openContentIcon);
        pnlContent.add(pasteIcon);
        pnlContent.add(checkBoxExecTerminal);
        pnlContent.add(lbVersion);
        pnlContent.add(tfVersion);
        pnlContent.add(lbComment);
        pnlContent.add(tfComment);
        pnlContent.add(photoIcon);
        // Footer panel
        JPanel pnlFooter = new JPanel();
        pnlFooter.setBounds(0, 352, 430, 18);
        pnlFooter.setLayout(null);
        // Add comps on application
        add(pnlTitleBar);
        add(pnlMainMenu);
        add(pnlContent);
        add(pnlFooter);

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
        newIcon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                Object[] options = new Object[] {"SIM", "NÃO"};
                if(utils.yesNoConfirm(options, "Deseja criar novo lançador?", "Criar novo lançador",
                        -1, 2, MakeLauncherLinux.this, "/app_icons/app/icons8-siren-32.png")) {
                    // Limpar campos
                    utils.clearTextField(tfName);
                    utils.clearTextField(tfAppCommand);
                    utils.clearTextField(tfVersion);
                    utils.clearTextField(tfComment);
                    photoIcon.setIcon(null);
                    photoIcon.setText("Icone");
                    iconPath = null;
                    locationLauncher = null;
                    cbAppType.setSelectedIndex(0);
                    checkBoxExecTerminal.setSelected(false);
                }
            }
        });
        // Mouse entered event icon new
        newIcon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                newIcon.setToolTipText("Novo Lançador");
            }
        });
        // Mouse click release event icon open
        openIcon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                fc = new JFileChooser();
                fc.addChoosableFileFilter(new FileNameExtensionFilter("Desktop", "desktop"));
                if(fc.showOpenDialog(MakeLauncherLinux.this) == JFileChooser.APPROVE_OPTION) {
                    OpenDesktopFile openDesktopFile = new OpenDesktopFile();
                    try {
                        DesktopFile desktopFile = openDesktopFile.lerProp(fc.getSelectedFile().getAbsolutePath());
                        // Setar campos
                        tfName.setText(desktopFile.getmName());
                        tfAppCommand.setText(desktopFile.getmExec());
                        tfComment.setText(desktopFile.getmComment());
                        tfVersion.setText(desktopFile.getmVersion());
                        cbAppType.setSelectedItem(desktopFile.getmAppType());
                        checkBoxExecTerminal.setSelected(desktopFile.ismTerminal());
                        photoIcon.setIcon(new ImageIcon(utils.getImage(desktopFile.getmIconPath(), photoIcon.getWidth(),
                                photoIcon.getHeight())));
                        photoIcon.setText("");
                    } catch (Exception er) { er.printStackTrace(); }
                }
            }
        });
        // Mouse entered event icon open
        openIcon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                openIcon.setToolTipText("Abrir Lançador Existente");
            }
        });
        // Mouse click release event icon launch
        launchIcon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                // Verifica se campos indispenssáveis estão preenchidos
                if (tfName.getText().isEmpty() && tfAppCommand.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(MakeLauncherLinux.this, "Pelo menos os campos Nome " +
                            "e Aplicação/Comando devem estar preenchidos.");
                } else {
                    // Criar lançador?
                    Object[] options = { "SIM", "NÃO" };
                    if(utils.yesNoConfirm(options, "Deseja criar o lançador com os dados informados?", "Criar Lançador",
                            -1, 2, MakeLauncherLinux.this, "/app_icons/app/icons8-siren-32.png")) {
                        // Caminho para salvar lançador
                        locationLauncher = null;
                        fc = new JFileChooser();
                        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                        if(fc.showOpenDialog(MakeLauncherLinux.this) == JFileChooser.APPROVE_OPTION) {
                            locationLauncher = fc.getSelectedFile().getAbsolutePath();
                        }
                        // Criar lançador.
                        DesktopFile desktopFile = makeDesktopObject(tfName.getText().trim(), tfVersion.getText().trim(),
                                tfComment.getText(), checkBoxExecTerminal.isSelected(),
                                Objects.requireNonNull(cbAppType.getSelectedItem()).toString(), iconPath, tfAppCommand.getText());
                        if (utils.makeLauncher(desktopFile, locationLauncher + "/")) {
                            JOptionPane.showMessageDialog(MakeLauncherLinux.this,
                                    "Lançador criado com sucesso em:\n" + locationLauncher+"/" + tfName.getText()+".desktop");
                        }
                    }
                }
            }
        });
        // Mouse entered event icon launch
        launchIcon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                launchIcon.setToolTipText("Lançar - Gerar Lançador");
            }
        });
        // Mouse click release event icon about
        aboutIcon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                JOptionPane.showMessageDialog(MakeLauncherLinux.this, "MakeLauncher for Linux beta!\n" +
                                "Developer: Roger Philippe - PhilippeSis\nhttp://philippesis.com.br\nhttps://icons8.com/",
                        "About It - Ver.: 0.0.1", JOptionPane.PLAIN_MESSAGE, new ImageIcon(
                                path+"/app_icons/app/icons8-rocket-36.png"));
            }
        });
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
        // Mouse click release event path app / command
        openContentIcon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                fc = new JFileChooser();
                if(fc.showOpenDialog(MakeLauncherLinux.this) == JFileChooser.APPROVE_OPTION) {
                    tfAppCommand.setText(fc.getSelectedFile().getAbsolutePath());
                }
            }
        });
        // Mouse entered event path app / command
        openContentIcon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                openContentIcon.setToolTipText("Selecionar Programa");
            }
        });
        // Mouse click release event paste icon
        pasteIcon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                tfAppCommand.setText(paste(pasteIcon));
                tfAppCommand.requestFocus();
            }
        });
        // Mouse entered event paste icon
        pasteIcon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                pasteIcon.setToolTipText("Colar");
            }
        });
        // Mouse click release event photo icon
        photoIcon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                fc = new JFileChooser();
                fc.addChoosableFileFilter(new FileNameExtensionFilter("Imagem", "png"));
                if(fc.showOpenDialog(MakeLauncherLinux.this) == JFileChooser.APPROVE_OPTION) {
                    iconPath = fc.getSelectedFile().getAbsolutePath();
                    Image dimg = utils.getImage(iconPath, photoIcon.getWidth(), photoIcon.getHeight());
                    photoIcon.setIcon(new ImageIcon(dimg));
                    photoIcon.setText("");
                }
            }
        });
        // Mouse entered event photo icon
        photoIcon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                photoIcon.setToolTipText("Icone do Lançador");
            }
        });

    }

    private void exitApp() {
        Object[] options = { "SIM", "NÃO" };
        if(utils.yesNoConfirm(options, "Deseja Realmente Sair?", "Sair da aplicação",
                JOptionPane.DEFAULT_OPTION, JOptionPane.CANCEL_OPTION, MakeLauncherLinux.this,
                "/app_icons/app/icons8-siren-32.png")) dispose();
    }

    // Metodo colar da area de transferencia do sistema operacional.
    private static String paste(JLabel btn) {
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        Transferable content = clipboard.getContents(btn); //como parametro do getContents, tem que ser o objeto q vai usar a area de transferencia
        String value="";
        if (content != null) {
            try {
                value = content.getTransferData(DataFlavor.stringFlavor).toString();
            } catch (UnsupportedFlavorException | IOException e) {
                e.printStackTrace();
            }

        }
        return value;
    }

    private DesktopFile makeDesktopObject(String appName, String version, String comment, boolean terminal, String appType,
                                      String iconPath, String exec) {
        return new DesktopFile.Builder()
                .setName(appName)
                .setVersion(version)
                .setComment(comment)
                .setTerminal(terminal)
                .setAppType(appType)
                .setIconPath(iconPath)
                .setExec(exec)
                .build();
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            MakeLauncherLinux makeLauncherLinux = new MakeLauncherLinux();
            makeLauncherLinux.setVisible(true);
        });
    }
    
}
