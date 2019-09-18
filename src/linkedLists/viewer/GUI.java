/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linkedLists.viewer;

import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.plaf.basic.BasicScrollBarUI;

/**
 *
 * @author 162442 José Antonio Solís Martínez jose.solismz@udlap.mx Date of
 * creation: 12/08/2019
 */
public class GUI extends JFrame implements ActionListener {

    //Set size and position of main window
    int FRAME_WIDTH = 400;
    int FRAME_HEIGHT = 400;
    int FRAME_X_ORIGIN = 300;
    int FRAME_Y_ORIGIN = 180;

    //Declaration of atributes
    JMenu menuTools, menuExtras;
    JMenuItem menuItem;
    JMenuBar menuBar;

    JTextArea arrayArea;
    JScrollPane scrollPane;

    Color color1;
    Color color2;
    Color color3;
    Color color4;
    Color color5;

    Array myArr;

    //Constructor
    GUI() {
        Container contentPane;
        addColors();

        //Sets properties of the window
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setResizable(false);
        setTitle("Array Viewer");
        setLocation(FRAME_X_ORIGIN, FRAME_Y_ORIGIN);

        contentPane = getContentPane();
        contentPane.setLayout(null);
        contentPane.setBackground(Color.black);

        //Sets the font, useful for changing its size
        Font Helvetica = new Font("helvetica", Font.PLAIN, 20);

        //Creates the main menu
        menuTools = new JMenu("Tools");
        menuTools.setForeground(color4);
        menuTools.setBackground(color2);
        menuTools.setBorder(null);
        menuTools.setBorderPainted(false);

        //Creates the menu that has additional functions that werent
        // part of the assignment specifications. Mostly for testing new stuff out.
        menuExtras = new JMenu("Extras");
        menuExtras.setForeground(color4);
        menuExtras.setBackground(color2);

        //Creates the different menu items and adds them to the main menu
        menuItem = new JMenuItem("Insert one element");
        menuItem.setBackground(color2);
        menuItem.setForeground(color4);
        menuItem.setBorder(null);
        menuItem.setBorderPainted(false);
        menuItem.addActionListener(this);
        menuTools.add(menuItem);

        menuItem = new JMenuItem("Insert all from file");
        menuItem.setBackground(color2);
        menuItem.setForeground(color4);
        menuItem.setBorder(null);
        menuItem.setBorderPainted(false);
        menuItem.addActionListener(this);
        menuTools.add(menuItem);

        menuItem = new JMenuItem("Delete one element");
        menuItem.setBackground(color2);
        menuItem.setForeground(color4);
        menuItem.setBorder(null);
        menuItem.setBorderPainted(false);
        menuItem.addActionListener(this);
        menuTools.add(menuItem);

        menuItem = new JMenuItem("Show Contents");
        menuItem.setBackground(color2);
        menuItem.setForeground(color4);
        menuItem.setBorder(null);
        menuItem.setBorderPainted(false);
        menuItem.addActionListener(this);
        menuTools.add(menuItem);

        menuItem = new JMenuItem("Merge Sort");
        menuItem.setBackground(color2);
        menuItem.setForeground(color4);
        menuItem.setBorder(null);
        menuItem.setBorderPainted(false);
        menuItem.addActionListener(this);
        menuTools.add(menuItem);

        //Adds the separator
        menuTools.add(new JSeparator());

        menuItem = new JMenuItem("Exit");
        menuItem.setBackground(color2);
        menuItem.setForeground(color4);
        menuItem.setBorder(null);
        menuItem.setBorderPainted(false);
        menuItem.addActionListener(this);
        menuTools.add(menuItem);

        //These items go to the extras menu
        menuItem = new JMenuItem("Delete Every");
        menuItem.setBackground(color2);
        menuItem.setForeground(color4);
        menuItem.setBorder(null);
        menuItem.setBorderPainted(false);
        menuItem.addActionListener(this);
        menuExtras.add(menuItem);

        menuItem = new JMenuItem("Delete Everything");
        menuItem.setBackground(color2);
        menuItem.setForeground(color4);
        menuItem.setBorder(null);
        menuItem.setBorderPainted(false);
        menuItem.addActionListener(this);
        menuExtras.add(menuItem);

        menuItem = new JMenuItem("Bubble Sort");
        menuItem.setBackground(color2);
        menuItem.setForeground(color4);
        menuItem.setBorder(null);
        menuItem.setBorderPainted(false);
        menuItem.addActionListener(this);
        menuExtras.add(menuItem);

        menuItem = new JMenuItem("Search");
        menuItem.setBackground(color2);
        menuItem.setForeground(color4);
        menuItem.setBorder(null);
        menuItem.setBorderPainted(false);
        menuItem.addActionListener(this);
        menuExtras.add(menuItem);

        menuItem = new JMenuItem("Search Every");
        menuItem.setBackground(color2);
        menuItem.setForeground(color4);
        menuItem.setBorder(null);
        menuItem.setBorderPainted(false);
        menuItem.addActionListener(this);
        menuExtras.add(menuItem);

        //Sets and colors the menu bar
        menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        menuBar.add(menuTools);
        menuBar.add(menuExtras);
        menuBar.setBackground(color2);
        menuBar.setForeground(color5);

        //This is where the numbers of the array are shown
        arrayArea = new JTextArea();
        arrayArea.setBounds(50, 70, 220, 65);
        arrayArea.setEditable(false);
        arrayArea.setBorder(null);
        arrayArea.setForeground(color5);
        arrayArea.setBackground(color1);
        arrayArea.setFont(Helvetica);
        contentPane.add(arrayArea);

        //Scrollbar functionality, horizontal only
        scrollPane = new JScrollPane(arrayArea);
        scrollPane.setBounds(0, 0, 385, 339);
        scrollPane.setBorder(null);
        scrollPane.getHorizontalScrollBar().setBackground(color2);
        //Changes the scrollbar thumb color 
        scrollPane.getHorizontalScrollBar().setUI(new BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = color4;
            }
        });
        contentPane.add(scrollPane);

        //So it stops the program once you close the windows
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //
        //CREATION OF THE ARRAY
        //
        //Asks the user for the size of the array to create
        String input = JOptionPane.showInputDialog("Type the size of the array:");
        int size = 0;
        try {
            size = Integer.parseInt(input);
        } catch (Exception e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "You need to type a positive integer");
            System.exit(0);
        }

        //Creates the array and tells it what its size is
        myArr = new Array();
        myArr.size = size;
        myArr.data = new int[size];

        //Creates window that asks the user if they want the array to be randomized or in sequence
        int response = JOptionPane.showConfirmDialog(null, "Do you want the array to be random?", "Randomize Array", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (response == JOptionPane.NO_OPTION) {
            //Creates a normal array. eg: if selected 5 as the size, the array will have 1, 2, 3, 4, 5 as its contents
            System.out.println("Normal Array");
            myArr.generateArray(size);
        } else if (response == JOptionPane.YES_OPTION) {
            //Creates a random array. eg: will be filled with any number from 0 to the size the user selected
            System.out.println("Random Array");
            myArr.generateRandomArray(size);
        } else if (response == JOptionPane.CLOSED_OPTION) {
            //Window was closed, exits the program
            System.out.println("JOptionPane closed");
            System.exit(0);
        }

        //Prints the contents of the array to the arrayArea
        arrayArea.setText(myArr.printContents());
    }

    //This section checks what the user clicked and calls the requested function from the array object
    public void actionPerformed(ActionEvent event) {
        //Gets the name of the pressed button so it can be evaluated on the switch.
        String buttonName = event.getActionCommand();
        String input;
        int numb;
        switch (buttonName) {
            case "Insert one element":
                if (myArr.counter == myArr.size) {
                    JOptionPane.showMessageDialog(null, "The array is full, try deleting something first");
                } else {
                    input = JOptionPane.showInputDialog("Type number you want to insert:");
                    //Converts whatever the user typed into an int and calls the insert function of the array.
                    try {
                        numb = Integer.parseInt(input);
                        myArr.insert(numb);
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "You need to type an integer");
                    }
                    arrayArea.setText(myArr.printContents());
                }
                break;
            case "Insert all from file":
                myArr.appendFromFile();
                arrayArea.setText(myArr.printContents());
                break;
            case "Delete one element":
                input = JOptionPane.showInputDialog("Type number you want to delete:");
                try {
                    numb = Integer.parseInt(input);
                    myArr.delete(numb);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "You need to type an integer");
                }
                arrayArea.setText(myArr.printContents());
                break;
            case "Delete Every":
                input = JOptionPane.showInputDialog("Type number you want to delete:");
                try {
                    numb = Integer.parseInt(input);
                    myArr.deleteEvery(numb);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "You need to type an integer");
                }
                arrayArea.setText(myArr.printContents());
                break;
            case "Delete Everything":
                myArr.deleteEverything();
                arrayArea.setText(myArr.printContents());
                break;
            case "Bubble Sort":
                //Calls the ugly bubble sort function
                myArr.bubbleSort();
                arrayArea.setText(myArr.printContents());
                break;
            case "Search":
                input = JOptionPane.showInputDialog("Type number you want to find:");
                try {
                    numb = Integer.parseInt(input);
                    myArr.search(numb);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "You need to type an integer");
                }
                break;
            case "Search Every":
                input = JOptionPane.showInputDialog("Type number you want to find:");
                try {
                    numb = Integer.parseInt(input);
                    myArr.searchEvery(numb);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "You need to type an integer");
                }
                break;
            case "Show Contents":
                arrayArea.setText(myArr.printContents());
                break;
            case "Merge Sort":
                myArr.mvmnt = 0;
                JOptionPane.showMessageDialog(null, "The number of movements was: " + myArr.mergeSort(0, myArr.counter - 1));
                arrayArea.setText(myArr.printContents());
                break;
            case "Exit":
                System.exit(0);
                break;
        }
    }

    public void addColors() {
        int[] arrColors = new int[15];
        int r1;
        int g1;
        int b1;
        int r2;
        int g2;
        int b2;
        int r3;
        int g3;
        int b3;
        int r4;
        int g4;
        int b4;
        int r5;
        int g5;
        int b5;

        //Creates window that asks the user if they want the array to be randomized or in sequence
        int response = JOptionPane.showConfirmDialog(null, "Do you want to use the default color pallete for the program?", "Color pallete selection", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        switch (response) {
                case JOptionPane.NO_OPTION:
            case JOptionPane.CLOSED_OPTION:
                //Creates a random array. eg: will be filled with any number from 0 to the size the user selected
                System.out.println("User selected color scheme");
                try {
                    JFileChooser fileChooser = new JFileChooser();
                    fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
                    int result = fileChooser.showOpenDialog(this);
                    if (result == JFileChooser.APPROVE_OPTION) {
                        File selectedFile = fileChooser.getSelectedFile();
                        System.out.println("Selected file: " + selectedFile.getAbsolutePath());
                        Scanner scanner = new Scanner(selectedFile);
                        int i = 0;
                        while (scanner.hasNextInt() && (i < 15)) {
                            arrColors[i++] = scanner.nextInt();
                        }
                    } else {
                        System.exit(0);
                    }
                } catch (FileNotFoundException e) {
                    System.out.println("colors.txt not found");
                }

                r1 = arrColors[0];
                g1 = arrColors[1];
                b1 = arrColors[2];
                r2 = arrColors[3];
                g2 = arrColors[4];
                b2 = arrColors[5];
                r3 = arrColors[6];
                g3 = arrColors[7];
                b3 = arrColors[8];
                r4 = arrColors[9];
                g4 = arrColors[10];
                b4 = arrColors[11];
                r5 = arrColors[12];
                g5 = arrColors[13];
                b5 = arrColors[14];

                color1 = new Color(r1, g1, b1);
                color2 = new Color(r2, g2, b2);
                color3 = new Color(r3, g3, b3);
                color4 = new Color(r4, g4, b4);
                color5 = new Color(r5, g5, b5);
                break;
            case JOptionPane.YES_OPTION:
                //Creates a normal array. eg: if selected 5 as the size, the array will have 1, 2, 3, 4, 5 as its contents
                System.out.println("Default color scheme");
                try {
                    Scanner scanner = new Scanner(new File("colors.txt"));
                    int i = 0;
                    while (scanner.hasNextInt() && (i < 15)) {
                        arrColors[i++] = scanner.nextInt();
                    }
                } catch (FileNotFoundException e) {
                    System.out.println("colors.txt not found");
                }

                r1 = arrColors[0];
                g1 = arrColors[1];
                b1 = arrColors[2];
                r2 = arrColors[3];
                g2 = arrColors[4];
                b2 = arrColors[5];
                r3 = arrColors[6];
                g3 = arrColors[7];
                b3 = arrColors[8];
                r4 = arrColors[9];
                g4 = arrColors[10];
                b4 = arrColors[11];
                r5 = arrColors[12];
                g5 = arrColors[13];
                b5 = arrColors[14];

                color1 = new Color(r1, g1, b1);
                color2 = new Color(r2, g2, b2);
                color3 = new Color(r3, g3, b3);
                color4 = new Color(r4, g4, b4);
                color5 = new Color(r5, g5, b5);
                break;

        }

    }
}
