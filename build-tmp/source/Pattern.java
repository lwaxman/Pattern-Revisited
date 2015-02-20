import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class Pattern extends PApplet {

/*
* Laurie Waxman 
* Pattern Maker
* Creative Computing
* 25.03.13
*
* Pattern Maker is a tool that allows anyone to create a pattern to be used for
* clothing, wrapping paper, desktop backgrounds, scrapbooking, etc.
* 
* There are multiple tabs.
*
*/

/****************************************** VARIABLES ***************************************/
PImage wallpaper;
PImage finalPattern;
PImage toolBar;
PImage titlePage;
PImage zoomPattern;
PImage tipsPage;
PImage preview;

int count;
int finalPatternCount;
int zoom;
int pageCount = 0; 
int brushSize = 5;
int symmetryX = 286;
int symmetryY = 267;
int layoutX = 561;
int layoutY = 266;

//for class Line
//int oldMouseX; 
//int oldMouseY;
//int lines;
//ArrayList lineList; 

int chosenColour = color(random(255), random(255), random(255));
int backgroundColour = color(random(255), random(255), random(255));
int black = color(0, 0, 0);

//used to store previous colours
int[] previousColours = new int[5];
int colourCount = 0;
int lastColour;

boolean brickLayout = true;
boolean gridLayout = false;

boolean symmetryDefault = true;
boolean symmetryCross = false;
boolean symmetryPlus = false;
boolean symmetry45 = false;
boolean symmetry135 = false;
boolean symmetryVertical = false;
boolean symmetryHorizontal = false;
boolean symmetryRotate = false;
boolean noSymmetry = false;

boolean drawingEnabled = true;



/******************************************* SETUP ******************************************/
public void setup(){
  size(950, 800);
  background(backgroundColour);
  wallpaper = loadImage("wallpaper.jpg");
  toolBar = loadImage("toolBar.png");
  titlePage = loadImage("titles.png");
  tipsPage = loadImage("tips.png");

 // zoomPattern = loadImage("pattern"+zoom+".jpg");
 // frameRate(100);  
  smooth();
  
  //lineList = new ArrayList();
}

/******************************************* DRAW ******************************************/
public void draw(){  
  
  //if on title/options page
  if(pageCount == 0){
    drawTitlePage();
  }
   
  if(pageCount == 1){
    drawTipsPage();
  }
  
  //when begin is clicked, pageCount no longer equals zero, and pattern maker is drawn.
  if(pageCount == 2){
    drawPatternMaker();
  }
  
  if(count==0){
    zoom = 0; 
  }else if(count > 1){
    zoom = count-1;
  }

  smooth();
}

/***************************************** OPTIONS PAGE **************************************/

public void drawTitlePage(){
  image(titlePage, 0, 0); //display page image 
  noStroke();
  fill(backgroundColour); 
  rect(560, 575, 230, 33); //fill a rectangle with desired background colour
  select(symmetryX, symmetryY);  //stroke around selected type of symmetry 
  select(layoutX, layoutY);  //stroke around selected layout
}

/***************************************** TIPS PAGE **************************************/

public void drawTipsPage(){
  image(tipsPage, 0, 0);
}

/***************************************** PATTERN PAGE **************************************/
//loads the pattern making page when called
public void drawPatternMaker(){
    
  //toolbar image
  image(toolBar, 0, 0);
  //drawPreview();
  
  
  //draws when mouse is pressed
  if(mousePressed && mouseX>150 && drawingEnabled){
    strokeWeight(brushSize);
    stroke(chosenColour); 
    symmetry();
//    lineList.add(new Line(pmouseX, pmouseY, mouseX, mouseY));
//    drawLines();
  }

  //brush size change key commands
  if (keyPressed) {
    if (key == ']') {
      brushSize++;
    }else if (key == '['){
      brushSize--; 
    }
//    if (key == 'p' || key == 'P') {
//      int lastOne = lineList.size()-1;
//      if (lastOne>=0) {
//        lineList.remove(lastOne);
//        background(backgroundColour);
//        drawLines();
//      }
//    }
  }
  
  //sets brush size limits (1px smallest, 100px largest)
  if(brushSize <=1){
    brushSize = 1;
  }else if(brushSize >= 100){
    brushSize = 100;
  }
  
  //displays brush size and colour
  noStroke();
  fill(chosenColour);
  ellipse(75, 158, brushSize, brushSize);
  
  //display previous colours below colour gradient
  drawPreviousColours();
}

public void drawPreviousColours(){
  for(int x=0; x<5; x++){
    int xPos = x*21;
    fill(backgroundColour);
    rect(10, 405, 21, 21); 
    
    fill(previousColours[x]);
    rect(xPos+31, 405, 21, 21); 
  }
}

/**void drawPreview(){
   preview = get(150, 0, 800, 800);
   preview.resize(50, 50);
  if(gridLayout){
    image(preview, 25, 450);
    image(preview, 75, 450);
    image(preview, 25, 500);
    image(preview, 75, 500);
  }else{
    image(preview, 25, 450);
    image(preview, 75, 450);
    image(preview, 25-(50*.5), 500);
    image(preview, 25+(50*.5), 500);
    image(preview, 25+(50*1.5), 500);
    fill(0);
    noStroke();
    rect(0, 450, 25, 100);
    rect(125, 450, 25, 100);
  }
  
}
**/


/************************************* CLASS LINE ******************************************/
//UNDO? 

////taken from ArrayList reference on processing.org
//void drawLines () {
//  for (int i = lineList.size()-1; i >= 0; i--) { 
//    Line line = (Line) lineList.get(i);
//    line.display();
//  }
//}
//
////idea of making Line a class taken from Polar's Book Prototype II on processing.org
//class Line{
//  
//  int x1, y1, x2, y2;
//
//  Line(int tempX1, int tempY1, int tempX2, int tempY2){
//    x1 = tempX1;
//    x2 = tempX2;
//    y1 = tempY1;
//    y2 = tempY2;
//  }
//  
//  void display(){ //displays lines using type of symmetry
//    if(noSymmetry){
//      noSymmetry();
//    }else if(symmetryRotate){
//      symmetryRotate();
//    }else if(symmetryPlus){
//      symmetryPlus();
//    }else if(symmetryVertical){
//      symmetryVertical();
//    }else if(symmetryHorizontal){
//      symmetryHorizontal();
//    }else if(symmetryCross){
//      symmetryCross();
//    }else if(symmetry45){
//      symmetry45();
//    }else if(symmetry135){
//      symmetry135();
//    }else{
//      symmetryDefault();
//    }     
//  }
//  
//  void symmetryDefault(){
//    line(x1, y1, x2, y2);
//    line(x1, height-y1, x2, height-y2);
//    line(width-x1+150, y1, width-x2+150, y2);
//    line(width-x1+150, height-y1, width-x2+150, height-y2);
//    line(y1+150, x1-150, y2+150, x2-150);
//    line(y1+150, height-x1+150, y2+150, height-x2+150); 
//    line(width-y1, x1-150, width-y2, x2-150);
//    line(width-y1, height-x1+150, width-y2, height-x2+150); 
//  }
//  
//  void noSymmetry(){
//    line(x1, y1, x2, y2);
//  }
//  
//  void symmetryRotate(){ 
//    line(x1, y1, x2, y2);
//    line(width-x1+150, height-y1, width-x2+150, height-y2);
//    line(y1+150, height-x1+150, y2+150, height-x2+150); 
//    line(width-y1, x1-150, width-y2, x2-150);
//  }
//  
//  void symmetryPlus(){ //+
//    line(x1, y1, x2, y2);
//    line(x1, height-y1, x2, height-y2);
//    line(width-x1+150, y1, width-x2+150, y2);
//    line(width-x1+150, height-y1, width-x2+150, height-y2);
//  }
//  
//  void symmetryVertical(){
//    line(x1, y1, x2, y2);
//    line(width-x1+150, y1, width-x2+150, y2);
//  }
//  
//  void symmetryHorizontal(){
//    line(x1, y1, x2, y2);
//    line(x1, height-y1, x2, height-y2);
//  }
//  
//  void symmetryCross(){ //x
//    line(x1, y1, x2, y2);
//    line(width-x1+150, height-y1, width-x2+150, height-y2);
//    line(y1+150, x1-150, y2+150, x2-150);
//    line(width-y1, height-x1+150, width-y2, height-x2+150); 
//  }
//  
//  void symmetry45(){  
//    line(x1, y1, x2, y2);
//    line(width-y1, height-x1+150, width-y2, height-x2+150); 
//  }
//  
//  void symmetry135(){   
//    line(x1, y1, x2, y2);
//    line(y1+150, x1-150, y2+150, x2-150);
//  }
//}


/********************************** MOUSE CLICK ACTIONS ************************************/

public void mouseClicked() {  

  //if on title page
  if (pageCount == 0) {       
    if (mouseX > 165 && mouseX < 268 && mouseY > 267 && mouseY < 370) { //box 1.1 - no symmetry
      symmetryDefault = false;
      symmetryCross = false;
      symmetry45 = false;
      symmetry135 = false;
      symmetryPlus = false;
      symmetryVertical = false;
      symmetryHorizontal = false;
      symmetryRotate = false;
      noSymmetry = true;
      symmetryX = 165;
      symmetryY = 267;
    }
    else if (mouseX > 286 && mouseX < 389 && mouseY > 267 && mouseY < 370) { //box 1.2 - default
      symmetryDefault = true;
      symmetryCross = false;
      symmetry45 = false;
      symmetry135 = false;
      symmetryPlus = false;
      symmetryVertical = false;
      symmetryHorizontal = false;
      symmetryRotate = false;
      noSymmetry = false;
      symmetryX = 286;
      symmetryY = 267;
    }
    else if (mouseX > 406 && mouseX < 509 && mouseY > 267 && mouseY < 370) { //box 1.3 - rotate
      symmetryDefault = false;
      symmetryCross = false;
      symmetry45 = false;
      symmetry135 = false;
      symmetryPlus = false;
      symmetryVertical = false;
      symmetryHorizontal = false;
      symmetryRotate = true;
      noSymmetry = false;
      symmetryX = 406;
      symmetryY = 267;
    }
    else if (mouseX > 165 && mouseX < 268 && mouseY > 385 && mouseY < 488) { //box 2.1 - plus
      symmetryDefault = false;
      symmetryCross = false;
      symmetry45 = false;
      symmetry135 = false;
      symmetryPlus = true;
      symmetryVertical = false;
      symmetryHorizontal = false;
      symmetryRotate = false;
      noSymmetry = false;
      symmetryX = 165;
      symmetryY = 385;
    }
    else if (mouseX > 286 && mouseX < 389 && mouseY > 385 && mouseY < 488) { //box 2.2 - horizontal
      symmetryDefault = false;
      symmetryCross = false;
      symmetry45 = false;
      symmetry135 = false;
      symmetryPlus = false;
      symmetryVertical = false;
      symmetryHorizontal = true;
      symmetryRotate = false;
      noSymmetry = false;
      symmetryX = 286;
      symmetryY = 385;
    }
    else if (mouseX > 406 && mouseX < 509 && mouseY > 385 && mouseY < 488) { //box 2.3 - vertical
      symmetryDefault = false;
      symmetryCross = false;
      symmetry45 = false;
      symmetry135 = false;
      symmetryPlus = false;
      symmetryVertical = true;
      symmetryHorizontal = false;
      symmetryRotate = false;
      noSymmetry = false;
      symmetryX = 406;
      symmetryY = 385;
    }
    else if (mouseX > 165 && mouseX < 268 && mouseY > 503 && mouseY < 606) { //box 3.1 - cross
      symmetryDefault = false;
      symmetryCross = true;
      symmetry45 = false;
      symmetry135 = false;
      symmetryPlus = false;
      symmetryVertical = false;
      symmetryHorizontal = false;
      symmetryRotate = false;
      noSymmetry = false;
      symmetryX = 165;
      symmetryY = 503;
    }
    else if (mouseX > 286 && mouseX < 389 && mouseY > 503 && mouseY < 606) { //box 3.2 - 45
      symmetryDefault = false;
      symmetryCross = false;
      symmetry45 = true;
      symmetry135 = false;
      symmetryPlus = false;
      symmetryVertical = false;
      symmetryHorizontal = false;
      symmetryRotate = false;
      noSymmetry = false;
      symmetryX = 286;
      symmetryY = 503;
    } 
    if (mouseX > 406 && mouseX < 509 && mouseY > 503 && mouseY < 606) { //box 3.3 - 135
      symmetryDefault = false;
      symmetryCross = false;
      symmetry45 = false;
      symmetry135 = true;
      symmetryPlus = false;
      symmetryVertical = false;
      symmetryHorizontal = false;
      symmetryRotate = false;
      noSymmetry = false;
      symmetryX = 406;
      symmetryY = 503;
    }//end symmetry selection

    //layout selection
    if (mouseX > 561 && mouseX < 664 && mouseY> 266 && mouseY < 369) { //box 4.1 - brick layout
      layoutX = 561;
      layoutY = 266;
      brickLayout = true;
      gridLayout = false;
    }
    else if (mouseX > 681 && mouseX < 784 && mouseY> 266 && mouseY < 369) { //box 4.2 - layout
      layoutX = 682;
      layoutY = 266;
      brickLayout = false;
      gridLayout = true;
    }//end layout selection

    //Gets colour mouse clicks from gradient
    if (mouseX > 559 && mouseX < 790 && mouseY > 435 && mouseY < 568) {
      backgroundColour = get(mouseX, mouseY);
    }//end background selection

    if (mouseX > 479 && mouseX < 592  && mouseY> 680 && mouseY < 725) { //TIPS clicked
      pageCount = 1;
    }
    else if (mouseX > 741 && mouseX <853 && mouseY > 680 && mouseY < 725) { //BEGIN clicked
      pageCount = 2;
      background(backgroundColour); //loads background once when called

    }
  }//end title page actions

  //if on tips page
  if (pageCount == 1) {
    if (mouseX > 592 && mouseX <  741 && mouseY> 680 && mouseY < 725) { //OPTIONS clicked
      pageCount = 0;
    }
    else if (mouseX > 741 && mouseX <853 && mouseY > 680 && mouseY < 725) { //BEGIN clicked
      pageCount = 2;
      background(backgroundColour); //loads background once when called
    }
  }//end tips page actions

  //if on pattern maker page
  if (pageCount == 2) { 
    //"zooms" when magnifying glass out is clicked    
    if (mouseX > 10 && mouseX < 70 && mouseY > 10 && mouseY < 75) {
      count++; //counts the number of times zoomed out 

      if (count > 0) { 
        wallpaper = get(150, 0, 800, 800);
        wallpaper.save("pattern"+count+".jpg");
      }//saves pattern to new jpg each zoom

      //loads whichever layout has been previously selected by the user.  
      if (brickLayout) { 
        loadBrickLayout();
      }
      else if (gridLayout) {
        loadGridLayout();
      }
    }//end zoom action

    //save final
    if (mouseX >70 && mouseX < 140 && mouseY > 10 && mouseY < 75) {
      finalPatternCount++;
      finalPattern = get(150, 0, 800, 800);
      finalPattern.save("final"+finalPatternCount+".jpg");
      drawingEnabled = false;
    }

    //changes brush colour when mouse is clicked over gradient area
    if (mouseX > 10 && mouseX < 140 && mouseY> 234 && mouseY < 425) {
      chosenColour = get(mouseX, mouseY);     
      //saves colour to previous colours area
      lastColour = chosenColour;
      previousColours[colourCount] = lastColour;
      if (colourCount >= 4) {
        colourCount = 0;
      }
      else {
        colourCount++;
      }
    }    

    //trash
    if (mouseX > 10 && mouseX < 70 && mouseY > 716 && mouseY <782) {
      background(backgroundColour);
      drawingEnabled = true;
    }

    //start new from main menu
    if (mouseX > 75 && mouseX < 140 && mouseY > 716 && mouseY <782) {
      pageCount = 0;
      drawingEnabled = true;
    }
  }//end pattern maker page actions
}//end mouse click actions



/*************************************** SELECTION ***************************************/
//draws a stroke around selected box when called
public void select(int x, int y) {
  stroke(225, 0, 0);
  strokeWeight(3);
  fill(0, 0);
  rect(x, y, 103, 103);
}


/***************************************** LAYOUTS **************************************/

//arranges repetition of pattern in brick form when called
public void loadBrickLayout() {
  wallpaper.resize(400, 400);
  image(wallpaper, 150, 0);
  image(wallpaper, 550, 0);
  image(wallpaper, -50, 400);
  image(wallpaper, 350, 400);
  image(wallpaper, 750, 400);
}

//arranges repetition of pattern in grid form when called
public void loadGridLayout() {
  wallpaper.resize(400, 400);
  image(wallpaper, 150, 0);
  image(wallpaper, 550, 0);
  image(wallpaper, 150, 400);
  image(wallpaper, 550, 400);
}


/************************************* SYMMETRY **************************************/

//runs the type of symmetry chosen on the title page
public void symmetry(){
  if(noSymmetry){
    noSymmetry();
  }else if(symmetryRotate){
    symmetryRotate();
  }else if(symmetryPlus){
    symmetryPlus();
  }else if(symmetryVertical){
    symmetryVertical();
  }else if(symmetryHorizontal){
    symmetryHorizontal();
  }else if(symmetryCross){
    symmetryCross();
  }else if(symmetry45){
    symmetry45();
  }else if(symmetry135){
    symmetry135();
  }else{
    symmetryDefault();
  }
}

 public void symmetryDefault(){ //8 way
    line(pmouseX, pmouseY, mouseX, mouseY);
    line(pmouseX, height-pmouseY, mouseX, height-mouseY);
    line(width-pmouseX+150, pmouseY, width-mouseX+150, mouseY);
    line(width-pmouseX+150, height-pmouseY, width-mouseX+150, height-mouseY);
    line(pmouseY+150, pmouseX-150, mouseY+150, mouseX-150);
    line(pmouseY+150, height-pmouseX+150, mouseY+150, height-mouseX+150); 
    line(width-pmouseY, pmouseX-150, width-mouseY, mouseX-150);
    line(width-pmouseY, height-pmouseX+150, width-mouseY, height-mouseX+150); 
}

public void noSymmetry(){ 
  line(pmouseX, pmouseY, mouseX, mouseY);
}

public void symmetryRotate(){ 
  line(pmouseX, pmouseY, mouseX, mouseY);
  line(width-pmouseX+150, height-pmouseY, width-mouseX+150, height-mouseY);
  line(pmouseY+150, height-pmouseX+150, mouseY+150, height-mouseX+150); 
  line(width-pmouseY, pmouseX-150, width-mouseY, mouseX-150);
}

public void symmetryPlus(){ // +
   line(pmouseX, pmouseY, mouseX, mouseY);
   line(pmouseX, height-pmouseY, mouseX, height-mouseY);
   line(width-pmouseX+150, pmouseY, width-mouseX+150, mouseY);
   line(width-pmouseX+150, height-pmouseY, width-mouseX+150, height-mouseY);
}

public void symmetryVertical(){ 
  line(pmouseX, pmouseY, mouseX, mouseY);
  line(width-pmouseX+150, pmouseY, width-mouseX+150, mouseY);
}

public void symmetryHorizontal(){
  line(pmouseX, pmouseY, mouseX, mouseY);
  line(pmouseX, height-pmouseY, mouseX, height-mouseY);
}

public void symmetryCross(){ // x
  line(pmouseX, pmouseY, mouseX, mouseY);
  line(width-pmouseX+150, height-pmouseY, width-mouseX+150, height-mouseY);
  line(pmouseY+150, pmouseX-150, mouseY+150, mouseX-150);
  line(width-pmouseY, height-pmouseX+150, width-mouseY, height-mouseX+150); 
}

public void symmetry45(){  // / 
  line(pmouseX, pmouseY, mouseX, mouseY);
  line(width-pmouseY, height-pmouseX+150, width-mouseY, height-mouseX+150); 
}

public void symmetry135(){  // \
  line(pmouseX, pmouseY, mouseX, mouseY);
  line(pmouseY+150, pmouseX-150, mouseY+150, mouseX-150);
}

  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "--full-screen", "--bgcolor=#666666", "--stop-color=#cccccc", "Pattern" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
