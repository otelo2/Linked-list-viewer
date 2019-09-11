/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linkedLists.viewer;

import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import javax.swing.plaf.basic.BasicScrollBarUI;

/**
 *
 * @author 162442 José Antonio Solís Martínez jose.solismz@udlap.mx
 * Date of creation: 12/08/2019
 */
public class GUI extends JFrame implements ActionListener{
    
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

    Array myArr;
    
    //Constructor
    GUI() {
        Container contentPane;

        //Sets properties of the window
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setResizable(false);
        setTitle("Array Viewer");
        setLocation(FRAME_X_ORIGIN, FRAME_Y_ORIGIN);

        contentPane = getContentPane();
        contentPane.setLayout(null);
        contentPane.setBackground(new Color(48, 48, 48));
        
        //Sets the font, useful for changing its size
        Font Helvetica = new Font("helvetica", Font.PLAIN, 20);
        
        //Creates the main menu
        menuTools = new JMenu("Tools");
        menuTools.setForeground(new Color(255,255,255));
        
        //Creates the menu that has additional functions that werent
        // part of the assignment specifications. Mostly for testing new stuff out.
        menuExtras = new JMenu("Extras");
        menuExtras.setForeground(new Color(255,255,255));

        //Creates the different menu items and adds them to the main menu
        menuItem = new JMenuItem("Insert one element");
        menuItem.addActionListener(this);
        menuTools.add(menuItem);
        
        menuItem = new JMenuItem("Insert all from file");
        menuItem.addActionListener(this);
        menuTools.add(menuItem);

        menuItem = new JMenuItem("Delete one element");
        menuItem.addActionListener(this);
        menuTools.add(menuItem);
        
        menuItem = new JMenuItem("Show Contents");
        menuItem.addActionListener(this);
        menuTools.add(menuItem);
        
        menuItem = new JMenuItem("Merge Sort");
        menuItem.addActionListener(this);
        menuTools.add(menuItem);
        
        //Adds the separator
        menuTools.add(new JSeparator());

        menuItem = new JMenuItem("Exit");
        menuItem.addActionListener(this);
        menuTools.add(menuItem);

        //These items go to the extras menu
        menuItem = new JMenuItem("Delete Every");
        menuItem.addActionListener(this);
        menuExtras.add(menuItem);
        
        menuItem = new JMenuItem("Delete Everything");
        menuItem.addActionListener(this);
        menuExtras.add(menuItem);

        menuItem = new JMenuItem("Bubble Sort");
        menuItem.addActionListener(this);
        menuExtras.add(menuItem);

        menuItem = new JMenuItem("Search");
        menuItem.addActionListener(this);
        menuExtras.add(menuItem);
        
        menuItem = new JMenuItem("Search Every");
        menuItem.addActionListener(this);
        menuExtras.add(menuItem);
        
        //Sets and colors the menu bar
        menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        menuBar.add(menuTools);
        menuBar.add(menuExtras);
        menuBar.setBackground(new Color(38,38,38));

        //This is where the numbers of the array are shown
        arrayArea = new JTextArea();
        arrayArea.setBounds(50, 70, 220, 65);
        arrayArea.setEditable(false);
        arrayArea.setBorder(null);
        arrayArea.setForeground(Color.white);
        arrayArea.setBackground(new Color(33,33,33));
        arrayArea.setFont(Helvetica);
        contentPane.add(arrayArea);

        //Scrollbar functionality, horizontal only
        scrollPane = new JScrollPane(arrayArea);
        scrollPane.setBounds(0, 0, 385, 339);
        scrollPane.setBorder(null);
        scrollPane.getHorizontalScrollBar().setBackground(new Color(48,48,48));
        //Makes the scrollbar thumb color wine 
        scrollPane.getHorizontalScrollBar().setUI(new BasicScrollBarUI()
        {
            @Override
            protected void configureScrollBarColors()
            {
                this.thumbColor = new Color (145, 47, 64);
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
        int size=0;
        try{
            size = Integer.parseInt(input);
        }catch(Exception e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "You need to type a positive integer");
            System.exit(0);
        }
        
        //Creates the array and tells it what its size is
        myArr=new Array();
        myArr.size=size;
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
                if(myArr.counter == myArr.size){
                    JOptionPane.showMessageDialog(null, "The array is full, try deleting something first");
                }
                else{
                input = JOptionPane.showInputDialog("Type number you want to insert:");
                //Converts whatever the user typed into an int and calls the insert function of the array.
                try{
                    numb = Integer.parseInt(input);
                    myArr.insert(numb);
                }catch(Exception e){
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
                try{
                    numb = Integer.parseInt(input);
                    myArr.delete(numb);
                }catch(Exception e){
                    JOptionPane.showMessageDialog(null, "You need to type an integer");
                }
                arrayArea.setText(myArr.printContents());
                break;
            case "Delete Every":
                input = JOptionPane.showInputDialog("Type number you want to delete:");
                try{
                    numb = Integer.parseInt(input);
                    myArr.deleteEvery(numb);
                }catch(Exception e){
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
                try{
                    numb = Integer.parseInt(input);
                    myArr.search(numb);
                }catch(Exception e){
                    JOptionPane.showMessageDialog(null, "You need to type an integer");
                }
                break;
            case "Search Every":
                input = JOptionPane.showInputDialog("Type number you want to find:");
                try{
                    numb = Integer.parseInt(input);
                    myArr.searchEvery(numb);
                }catch(Exception e){
                    JOptionPane.showMessageDialog(null, "You need to type an integer");
                }
                break;
            case "Show Contents":
                arrayArea.setText(myArr.printContents());
                break;
            case "Merge Sort":
                myArr.mvmnt=0;
                JOptionPane.showMessageDialog(null, "The number of movements was: "+myArr.mergeSort(0, myArr.counter-1));
                arrayArea.setText(myArr.printContents());
                break;
            case "Exit":
                System.exit(0);
                break;
        }
    }
    
}
