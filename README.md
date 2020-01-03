Rizwan Chowdhury 
December 2019 -January 2020




Implementation of Conway's Game of Life visualization through Java


Game Description at: https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life


Uses Java Lists and java.swing/awt components to accomplish tasks.


The program does the Conway Game of Life simulation through the use of 2d arrays and buttons. There is a board of buttons that change color based on 
either button click or the state of the corresponding element in the 2d array. Red = alive and Gray = dead. The program operates on a 2d list in the 
background and changes are reflected with the button color in the gui. Each button has access to the 2d list and contains an index variables to access the corresponding element
in the array. Each button also contains methods to change color when there is change in the array. The gui has methods that call for update on the
array (as per the simulation) and those methods call the button's method to update color. Simulations can be in two forms, either by 1 step using the 
"Next" button or a non-stop simulation through "Start" button (simulation can be stopped using the "Stop" button).




The code is run through the ConwayGameOfLife.java. By running this file the entire program is started.


Can also be run by using the jar executable included in this repository "ConwayGameOfLife.jar"




Broad Overview:




ConwayGameOfLife.java is driver that runs code




ConwayMatrix is object that stores 2d List object that stores the states for the Game of Life. 
  
  -Contains the methods that update the matrix with the rules of the Conway Game of Life based on current states
  



ConwayGui is object that holds the gui and visual elements as well as control elements:
  
  -Sets up frame and panels
  
  
  -Adds the ConwayButtons
  
  
  -Adds the ConwayControlButtons
  
  
  -Contains methods to control the gui:
  
  
  - functions such as looperFunction() to start the simulation and continue until stopped.
    
 


ConwayButton object are buttons that set and show state of cells in the simulation:

  - Upon press will set state
  
  
  - Is passed the ConwayMatrix object and index so each button has access to its corresponding cell in array
  
  
  - Contains method to change state when array's state is changed
  
  


ConwayControlButton:
 
 
 -Stop,Start,Next,Clear buttons to control the conway game of life. Ex: start the game of life simulation with "Start" button or see one step simulation through the "Next" button.
