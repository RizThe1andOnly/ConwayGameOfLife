package ConwayGOL;
import java.awt.Color;
import java.awt.event.*;
import javax.swing.*;

/**
     * intermediatary object that will return a different object based on @param type
     * the different types are:
     *  - Next
     *  - Stop
     *  - Clear
     *  - Start
     * each with different functionalities and with their own classes defined below
     * 
     * Primary objective of these buttons will be to activate or deactivate the functions within the gui
     * that control the flow of events
     * 
     * @param main: the main gui which will have the functions that control the flow of events
     * @param type
     */
public class ConwayControlButton {

    public static JButton create(ConwayGui main,String type){
        if(type.equals("Stop")) return new StopButton(main);
        if(type.equals("Next")) return new NextButton(main);
        if(type.equals("Clear")) return new ClearButton(main);
        if(type.equals("Start")) return new StartButton(main);
        return null;
    }


}

/**
 * Class to contain some common properties for the control buttons
 */
class ControlButtonClass extends JButton{

    public ControlButtonClass(){
        setForeground(Color.WHITE);
        setBackground(Color.green);
    }

}


/**
 * Stop button will be responsible for stopping the loop that takes place when Start button starts loop
 * 
 * will do so by setting the control variable in the main gui to "stop" which will then be read by looperFunction in gui to stop
 * the loop
 */
class StopButton extends ControlButtonClass implements ActionListener{

    ConwayGui main;

    public StopButton(ConwayGui main){
        this.main = main;
        this.addActionListener(this);
        setText("Stop");
    }

    @Override
    public void actionPerformed(ActionEvent e){
        this.main.control = "stop";
    }
}

/**
 * StartButton starts loop in looperFunction(in main gui object) by setting the control variable to "start" 
 * Uses a seperate thread to accomplish task. Since loop is used button click will not finish until loop finishes
 * or a thread is used. Thread takes process to outside sequence so button click can finish and gui processess can
 * continue.
 */
class StartButton extends ControlButtonClass implements ActionListener{
    ConwayGui main;
    public StartButton(ConwayGui main){
        this.main = main;
        this.addActionListener(this);
        setText("Start");
    }

    @Override
    public void actionPerformed(ActionEvent e){
        this.main.control = "start";
        LoopRunner lr = new LoopRunner(this.main);
        new Thread(lr).start();
    }
}

/**
 * NextButton will set control to "next" and call the nextFunction in the main gui
 */
class NextButton extends ControlButtonClass implements ActionListener{

    ConwayGui main;
    
    public NextButton(ConwayGui main){
        this.main = main;
        this.addActionListener(this);
        setText("Next");
    }

    @Override
    public void actionPerformed(ActionEvent e){
        this.main.control = "next";
        this.main.nextFunction();
    }
}

/**
 * ClearButton will set control to "clear" and call the clearFunction in main gui
 */
class ClearButton extends ControlButtonClass implements ActionListener{
    
    ConwayGui main;

    public ClearButton(ConwayGui main){
        this.main = main;
        this.addActionListener(this);
        setText("Clear");
    }

    @Override
    public void actionPerformed(ActionEvent e){
        this.main.control = "clear";
        this.main.clearFunction();
    }
}

/**
 *  Runnable for the thread used by StartButton.
 *  Receives @param ConwayGui so that loopFunction in main gui can be accessed and run.
 */
class LoopRunner implements Runnable{

    ConwayGui main;

    public LoopRunner(ConwayGui main){
        this.main = main;
    }

    @Override
    public void run(){
        this.main.looperFunction();
    }
}