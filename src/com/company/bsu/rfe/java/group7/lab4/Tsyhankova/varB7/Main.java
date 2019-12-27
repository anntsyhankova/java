package com.company.bsu.rfe.java.group7.lab4.Tsyhankova.varB7;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

public class Main extends JFrame {

    private boolean fileLoaded = false;
    private GraphicsDisplay display = new GraphicsDisplay();
    private JCheckBoxMenuItem showAxisMenuItem;
    private JCheckBoxMenuItem showMarkersMenuItem;
   // private JCheckBoxMenuItem showGridMenuItem;
    private JMenuItem resetGraphicsMenuItem;
    private JMenuItem saveToTextMenuItem;
    private JFileChooser fileChooser = null;

    public Main() {
        super("Вывод графика функции");
        this.setSize(600, 600);
        Toolkit kit = Toolkit.getDefaultToolkit();
        this.setLocation((kit.getScreenSize().width - 600) / 2, (kit.getScreenSize().height - 600) / 2);
        JMenuBar menuBar = new JMenuBar();
        this.setJMenuBar(menuBar);
        JMenu fileMenu = new JMenu("Файл");
        menuBar.add(fileMenu);

        Action openGraphicsAction = new AbstractAction("Open file") {
            public void actionPerformed(ActionEvent arg0) {
                if (Main.this.fileChooser == null) {
                    Main.this.fileChooser = new JFileChooser();
                    Main.this.fileChooser.setCurrentDirectory(new File("."));
                }

                if (Main.this.fileChooser.showOpenDialog(Main.this) == 0) {
                }

                Main.this.openGraphics(Main.this.fileChooser.getSelectedFile());
            }
        };
        fileMenu.add(openGraphicsAction);

        Action saveToTextAction = new AbstractAction("Save to .txt") {
            public void actionPerformed(ActionEvent arg0) {
                if (Main.this.fileChooser == null) {
                    Main.this.fileChooser = new JFileChooser();
                    Main.this.fileChooser.setCurrentDirectory(new File("."));
                }

                if (Main.this.fileChooser.showSaveDialog(Main.this) == 0) {
                    Main.this.display.saveToTextFile(Main.this.fileChooser.getSelectedFile());
                }

            }
        };
        this.saveToTextMenuItem = fileMenu.add(saveToTextAction);
        JMenu graphicsMenu = new JMenu("График");
        menuBar.add(graphicsMenu);

        Action showAxisAction = new AbstractAction("Показать оси координат") {
            public void actionPerformed(ActionEvent e) {
                Main.this.display.setShowAxis(Main.this.showAxisMenuItem.isSelected());
            }
        };
        this.showAxisMenuItem = new JCheckBoxMenuItem(showAxisAction);
        graphicsMenu.add(this.showAxisMenuItem);
        this.showAxisMenuItem.setSelected(true);
        Action showMarkersAction = new AbstractAction("Показать маркеры точек") {
            public void actionPerformed(ActionEvent e) {
                Main.this.display.setShowMarkers(Main.this.showMarkersMenuItem.isSelected());
            }
        };
        this.showMarkersMenuItem = new JCheckBoxMenuItem(showMarkersAction);
        graphicsMenu.add(this.showMarkersMenuItem);
        this.showMarkersMenuItem.setSelected(true);
        graphicsMenu.addSeparator();
        Action resetGraphicsAction = new AbstractAction("Отменить все изменения") {
            public void actionPerformed(ActionEvent event) {
                Main.this.display.reset();
            }
        };

    /*    this.showGridMenuItem = new JCheckBoxMenuItem(showGridAction);
        graphicsMenu.add(this.showGridMenuItem);
        this.showGridMenuItem.setSelected(true);
        graphicsMenu.addSeparator();
        Action showGridAction  = new AbstractAction("Show grid") {
            public void actionPerformed(ActionEvent event) {
                Main.this.display.reset();
            }
        };
        */
        this.resetGraphicsMenuItem = new JMenuItem(resetGraphicsAction);
        graphicsMenu.add(this.resetGraphicsMenuItem);
        this.resetGraphicsMenuItem.setEnabled(false);
        graphicsMenu.addMenuListener(new Main.GraphicsMenuListener());
        this.getContentPane().add(this.display, "Center");
    }

    protected void openGraphics(File selectedFile) {
        try {
            DataInputStream in = new DataInputStream(new FileInputStream(selectedFile));
            ArrayList graphicsData = new ArrayList(50);

            while(in.available() > 0) {
                Double x = in.readDouble();
                Double y = in.readDouble();
                graphicsData.add(new Double[]{x, y});
            }

            if (graphicsData.size() > 0) {
                this.fileLoaded = true;
                this.resetGraphicsMenuItem.setEnabled(true);
                this.display.showGraphics(graphicsData);
            }
        } catch (FileNotFoundException var6) {
        } catch (IOException var7) {
        }

    }

    public static void main(String[] args) {
        Main frame = new Main();
        frame.setDefaultCloseOperation(3);
        frame.setVisible(true);
    }

    private class GraphicsMenuListener implements MenuListener {
        private GraphicsMenuListener() {
        }

        public void menuCanceled(MenuEvent arg0) {
        }

        public void menuDeselected(MenuEvent arg0) {
        }

        public void menuSelected(MenuEvent arg0) {
            Main.this.showAxisMenuItem.setEnabled(Main.this.fileLoaded);
            Main.this.showMarkersMenuItem.setEnabled(Main.this.fileLoaded);
            Main.this.saveToTextMenuItem.setEnabled(Main.this.fileLoaded);
           // Main.this.showGridMenuItem.setEnabled(Main.this.fileLoaded);
        }
    }
}

