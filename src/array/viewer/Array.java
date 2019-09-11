/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package array.viewer;

import java.io.File;
import java.io.FileNotFoundException;
import javax.swing.*;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author 162442 José Antonio Solís Martínez jose.solismz@udlap.mx
 * Date of creation: 12/08/2019
 */
public class Array {

    //Atributes
    int size;
    int counter;
    int data[];
    int mvmnt=0;

    //Random number used for generation of random array
    Random rand = new Random();

    //Constructor. Empty as of 06/09/2019
    Array() {
    }

    //Methods
    
    //Generates the sequential array
    void generateArray(int size) {
        //Generates an array with contents from 1 to size
        for (int i = 0; i < size; i++) {
            data[i] = i + 1;
            counter = i + 1;
        }
    }

    //Generates the random array
    void generateRandomArray(int size) {
        //Generates a random array with numbers smaller than size
        for (int i = 0; i < size; i++) {
            data[i] = rand.nextInt(size);
            counter = i + 1;
        }
    }

    //Inserts a number in the last position
    void insert(int num) {
        //Alta secuencial
        if (counter == size) {
            System.out.println("Array is full");
        } else {
            data[counter] = num;
            counter++;
        }
    }

    //Delets every instance of the specified number
    void deleteEvery(int num) {
        //Deletes every number
        int i, j;
        boolean found = false;
        System.out.println("Going to delete number " + num);
        if (counter > 0) {
            for (i = 0; i < counter; i++) {
                if (num == data[i]) {
                    System.out.println("Found number " + num + " in index " + i + " and deleted it");
                    found = true;
                    for (j = i + 1; j < counter; j++) {
                        data[j - 1] = data[j];
                    }
                    counter--;
                    i--;
                }
            }
            if (found == false) {
                JOptionPane.showMessageDialog(null, "Number not found");
                System.out.println("Number not found");
            }
        }
    }

    //Deletes just the first instance of the specified number
    void delete(int num) {
        //Deletes just the first number
        int i, j;
        boolean found = false;
        boolean firstTime = true;
        System.out.println("Going to delete number " + num);
        if (counter > 0) {
            for (i = 0; i < counter; i++) {
                if (num == data[i]&& firstTime) {
                    firstTime=false;
                    System.out.println("Found number " + num + " in index " + i + " and deleted it");
                    found = true;
                    for (j = i + 1; j < counter; j++) {
                        data[j - 1] = data[j];
                    }
                    counter--;
                    i--;
                }
            }
            if (found == false) {
                JOptionPane.showMessageDialog(null, "Number not found");
                System.out.println("Number not found");
            }
        }
    }

    //Ugly bubble sort function
    void bubbleSort() {
        //Bubble sort
        int n = data.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (data[j] > data[j + 1]) {
                    int temp = data[j];
                    data[j] = data[j + 1];
                    data[j + 1] = temp;
                }
            }
        }
        System.out.println("Sorted array");
    }

    //Finds every position of the specified number
    public int searchEvery(int num) {
        //Finds every position of that number not just the first/last one
        int position = -1;
        boolean notFound = true;
        for (int i = 0; i < counter; i++) {
            if (data[i] == num && position != i) {
                position = i;
                System.out.println("Searched number " + num + " is in index " + position);
                JOptionPane.showMessageDialog(null, "Searched number " + num + " is in index " + position);
                notFound = false;
            }
        }
        if (notFound) {
            System.out.println("Couldn't find number " + num);
            JOptionPane.showMessageDialog(null, "Couldn't find number " + num);
        }
        return position;
    }

    //Finds the first position of the specified number
    public int search(int num) {
        //Finds just the  first position of the number
        int position = -1;
        boolean notFound = true;
        boolean alreadyFound = false;
        for (int i = 0; i < counter; i++) {
            if (data[i] == num && position != i && alreadyFound == false) {
                position = i;
                System.out.println("Searched number " + num + " is in index " + position);
                JOptionPane.showMessageDialog(null, "Searched number " + num + " is in index " + position);
                notFound = false;
                alreadyFound = true;
            }
        }
        if (notFound) {
            System.out.println("Couldn't find number " + num);
            JOptionPane.showMessageDialog(null, "Couldn't find number " + num);
        }
        return position;
    }

    //Turns the contents of the array into a string that then gets returned 
    public String printContents() {
        String content = "";
        for (int i = 0; i < counter; i++) {
            content = content + " " + Integer.toString(data[i]);
        }
        System.out.println("Printed array");
        return content;
    }

    //Adds number from a file to the array, as long as it has empty spaces
    public void appendFromFile() {
        //Checks if array is full
        if (counter == size) {
            //Tell user array is full
            JOptionPane.showMessageDialog(null, "The array is full, try deleting something first");
        } else {
            try {
                //Append to array from the file
                Scanner scanner = new Scanner(new File("integers.txt"));
                while (scanner.hasNextInt() && (counter != size)) {
                    data[counter++] = scanner.nextInt();
                }
            } catch (FileNotFoundException e) {
                System.out.println("File not found");
                JOptionPane.showMessageDialog(null, "The file wasn't found");
            }
        }
    }
    
    //Doesn't actually delete everything, just changes the value of the counter to 0
    public void deleteEverything(){
        counter = 0;
    }
    
    //Merge sort counting number of comparisons. Takes the leftmost and rightmost index of the array as arguments.
    public int mergeSort(int left, int right){
        int i,j,k,middle;
        int[] temp = new int[size];
        if(right>left){
            middle=(right+left)/2;
            mergeSort(left,middle);
            mergeSort(middle+1,right);
            for(i=middle+1;i>left;i--){
                //First movement
                mvmnt++;
                temp[i-1] = data[i-1];
            }
            for(j=middle;j<right;j++){
                //Second movement
                mvmnt++;
                temp[right+middle-j] = data[j+1];
            }
            for(k=left;k<=right;k++){
                //Third movement
                mvmnt++;
                if(temp[i]<temp[j]){
                    data[k] = temp[i++];
                }
                else
                    data[k] = temp[j--];
            }
        }
        //Prints the ammount of movements
        System.out.println(mvmnt);
        return mvmnt;
    }
}
