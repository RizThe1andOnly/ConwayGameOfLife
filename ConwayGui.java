package ConwayGOL;

import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.util.List;


/**
 * Object that will create gui for conway game
 */
public class ConwayGui{

    JPanel conwayPanel;
    JPanel controlPanel;
    String control;
    List<List<ConwayButton>> conwayButtonMatrix;
    ConwayMatrix cm;
    JFrame frame;

    public ConwayGui(int cells){

        //create ConwayMatrix object that will be used by the buttons and rest of gui
        this.cm = new ConwayMatrix(cells);
        
        //init obj vars:
        this.conwayButtonMatrix = new ArrayList<>();
        this.conwayPanel = this.setupConwayPanel(cells, this.cm);
        this.controlPanel = this.setupControlPanel();
        this.control = "stop";
         
        //set up frame,panel,layout and add specialized buttons
        this.frame = new JFrame();
        setUpFrame(this.frame);
        this.frame.setVisible(true);
        
    }

    /**
     * function to set up various frame properties such as close property or visible
     * 
     * also sets up the gridbag layout used by the frame and adds the 2 panels
     * conwayPanel and controlPanel
     * 
     * @param frame
     */
    private void setUpFrame(JFrame frame){

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(Color.WHITE);
        frame.setLocationByPlatform(true);
        
        //set up gridbag layout:
        GridBagLayout gbl = new GridBagLayout();
        frame.setLayout(gbl);

        //gridbag constraints for conwayPanel
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 5.0;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        frame.add(this.conwayPanel);
        gbl.setConstraints(this.conwayPanel, gbc);
        

        //gridbag constraints for controlPanel:
        GridBagConstraints gbc2 = new GridBagConstraints();
        gbc2.fill = GridBagConstraints.HORIZONTAL;
        gbc2.weighty = 0.5;
        gbc2.weightx = 1.0;
        gbc2.gridwidth = GridBagConstraints.REMAINDER;
        gbc2.insets = new Insets(0,50,0,50);
        frame.add(this.controlPanel);
        gbl.setConstraints(this.controlPanel, gbc2);

        //set up size for frame
        frame.setSize(gbl.preferredLayoutSize(frame));
        
    }

    /**
     * Creates the conwayPanel and adds the buttons into the panel
     * Process includes initializing each of the buttons and adding them to the
     * panel as well as adding them to the conwayButtonMatrix so that they can be used later
     */
    private JPanel setupConwayPanel(int cells, ConwayMatrix cm){
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setLayout(new GridLayout(cells,cells));
        for(int i=0;i<cells;i++){
            List<ConwayButton> newrow = new ArrayList<>();
            this.conwayButtonMatrix.add(newrow);
            for(int j=0;j<cells;j++){
                ConwayButton newButton = new ConwayButton(cm,i,j);
                panel.add(newButton);
                this.conwayButtonMatrix.get(i).add(newButton);
            }
        }

        return panel;
    }

    /**
     * Creates the controlPanel
     * Includes creating and initializing the Start, Stop, Next, and Clear buttons
     * Sets up the gridlayout and adds the buttons to the panel
     */
    private JPanel setupControlPanel(){
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        GridLayout gl = new GridLayout(1,4);
        gl.setHgap(30);
        panel.setLayout(gl);
        
        //create the control buttons
        JButton nextButton = ConwayControlButton.create(this,"Next");
        JButton startButton = ConwayControlButton.create(this,"Start");
        JButton stopButton = ConwayControlButton.create(this,"Stop");
        JButton clearButton = ConwayControlButton.create(this,"Clear");

        
        //add them to the panel
        panel.add(nextButton);
        panel.add(startButton);
        panel.add(stopButton);
        panel.add(clearButton);
        
        return panel;
    }


    /**
     * - Will be activated by pressing the "Start" button
     * - As long as this.control is set to "start" will have a while loop that
     *   continously calls this.nextFunction()
     *   By doing so will have the gui update with the results of the conway game of life
     * 
     * - Will stop when this.control is "stop" which is done through pressing the stop button
     */
    public void looperFunction(){
        try {
            while(this.control.equals("start")){
                nextFunction();
                Thread.sleep(1000);
            }
        } catch (Exception e) {
            this.frame.dispose();
        }
    }

    /**
     * Activated thru pressing "Next" button or by being called by looperFunction()
     * updates the gui and conwayMatrix based on the rules by
     * continuously calling ConwayMatrix.updateMatrix and the helper function this.updateButtons()
     */
    public void nextFunction(){
        this.cm.updateMatrix();
        this.updateButtons();
    }

    /**
     * updates the buttons based on the current state of the corresponding conwayArray element.
     * does so by calling setBackground function that each button possesses
     */
    private void updateButtons(){
        List<List<ConwayButton>> targetMatrix = this.conwayButtonMatrix;
        for(int i=0;i<targetMatrix.size();i++){
            for(int j=0;j<targetMatrix.get(i).size();j++){
                targetMatrix.get(i).get(j).setBackground();
            }
        }
    }

    

    /**
     * Sets all elements of the conway matrix to zero and calls updateButtons() to reflect this change
     * "clears" the board by zero-ing out all elements
     */
    public void clearFunction(){
        List<List<Integer>> conMatrix = this.cm.matrix;
        for(int i=0;i<conMatrix.size();i++){
            for(int j=0;j<conMatrix.get(i).size();j++){
                conMatrix.get(i).set(j, 0);
            }
        }
        this.updateButtons();
    }


}