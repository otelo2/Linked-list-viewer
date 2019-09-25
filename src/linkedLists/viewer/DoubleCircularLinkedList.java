/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linkedLists.viewer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author 162442 José Antonio Solís Martínez jose.solismz@udlap.mx 162064 María
 * Fernanda Flores Luna maria.floresla@udlap.mx 16XXXX Jorge André Tenorio
 * Guzman jorge.tenoriogn@udlap.mx 16XXXX Mauricio Raúl Tenorio Guzman
 * mauricio.tenoriogn@udlap.mx
 *
 * Date of creation: 11/09/2019
 */
public class DoubleCircularLinkedList {

    //Contains attribute 'head', 'tail', and methods to insert one element, insert from file (ordered), eliminate, search and display elements of the linked list.
    DCNode head;
    DCNode tail;

    //Constructor
    public DoubleCircularLinkedList() {
    }

    //Methods
    //Adds an 'n' integer to a new node, and makes the adjustments so that the pointers aren't lost.
    void add(int n) {
        DCNode node;
        node = new DCNode(n);
        if (head == null) {
            node.next = node;
            node.prev = node;
            head = node;
            tail = node;
        } else {
            DCNode temp;
            temp = head;
            do {
                if (temp.data < n) {
                    temp = temp.next;
                }
            } while ((temp.data < n) && (temp != head));
            node.prev = temp.prev;
            node.next = temp;
            temp.prev = node;
            temp = node.prev;
            temp.next = node;

            if (node.data < head.data) {
                head = node;
            }
            if (node.data > tail.data) {
                tail = node;
            }
        }
    }

    //Deletes a node form the list that has the value that needs to be deleted
    boolean delete(int Terminator) {
        DCNode node;
        node = find(Terminator);
        if (node != null) {
            if (node == head) {
                head = head.next;
            }
            if (node == tail) {
                tail = tail.prev;
            }
            DCNode temp;
            temp = node.next;
            temp.prev = node.prev;
            node = node.prev;
            node.next = temp;
            return true;
        } else {
            return false;
        }
    }

    //Traverses the linked list until the number is found or isn't there, then it returns either the node with the number we are looking for, or null
    DCNode find(int Wally) {
        DCNode node;
        if (head != null) {
            node = head;
            do {
                if (node.data < Wally) {
                    node = node.next;
                }
            } while ((node != head) && (node.data < Wally));

            if (node.data == Wally) {
                return node;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    //Adds integers from a text file, creates a node for every integer
    void insertFromFile() {
        int data;
        try {
            Scanner scanner = new Scanner(new File("integers.txt"));
            while (scanner.hasNextInt()) {
                data = scanner.nextInt();
                add(data);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            JOptionPane.showMessageDialog(null, "The file wasn't found");
        }
    }

    //Turns the contents of the linked list into a string that then gets returned 
    public String printContents() {
        DCNode temp = this.head;
        String content = "";

        if (temp != null) {
            do {
                content = content + " " + Integer.toString(temp.data);
                temp = temp.next;
            } while (temp != this.head);
            System.out.println("Printed linked list");
        }
        
        return content;
    }

    //Fills the linked list with numbers from 1 to the argument 'num''
    void fillLinkedList(int num) {
        for (int i = 0; i < num; i++) {
            add(i + 1);
        }
    }

}
