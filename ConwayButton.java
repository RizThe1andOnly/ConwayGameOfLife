package ConwayGOL;

import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The set of button that will be used to initialize the conway game and
 * will display the effects of the game of life.
 * 
 * Consist of public method to set the background and actionlistener to
 * take in initial and intermediate user inputs
 */
public class ConwayButton extends JButton implements ActionListener{

    List<Integer> index = new ArrayList<>();
    ConwayMatrix overallMatrix;
    int state;

    /**
     * Constructor to initialize the button. Will create the index list
     * , add the actionlistener to the button and will set the inital background
     * 
     * @param conwayArray : the ConwayMatrix object that will be passed from ConwayGui
     *                      It will contain the states of all the buttons
     *                      Each button has its own index and will change 
     *                      state of the element at index button has access
     *                      to.
     * 
     * @param i: the row index that will be passed in
     * @param j: the column index that will be passed in
     */
    public ConwayButton(ConwayMatrix conwayArray,int i,int j){
        
        //set up button properties:
        Dimension d = new Dimension(50,50);
        setPreferredSize(d);

        // set up parameters to object variables
        this.index.add(i);
        this.index.add(j);
        this.overallMatrix = conwayArray;
        this.state = this.overallMatrix.matrix.get(this.index.get(0)).get(this.index.get(1));
        this.addActionListener(this);
        this.setBackground();

    }

    /**
     * Sets the background color based on the current state.
     * 
     * Also sets the current state to the value of the overallMatrix that 
     * is at the index that the button has access to.
     * 
     * If state = 0 then color is GRAY
     * If state = 1 then color is RED
     */
    public void setBackground(){
        this.state = this.overallMatrix.matrix.get(this.index.get(0)).get(this.index.get(1));
        if(this.state==0) setBackground(Color.GRAY);
        if(this.state==1) setBackground(Color.RED);
    }


    /**
     * listens for button push and then based on current state will change
     * the overallMatrix's value at the index the button has access to.
     * 
     * if currentState is 0 then newstate is 1
     * if currentState is 1 then newstate is 0
     */
    @Override
    public void actionPerformed(ActionEvent e){
        int newState = (this.state==0) ? 1:0;
        this.overallMatrix.matrix.get(this.index.get(0)).set(this.index.get(1), newState);
        this.setBackground();
    }

}
