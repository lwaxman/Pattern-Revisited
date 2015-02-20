/*
* Laurie Waxman 
* Pattern Maker Revisited
*
* A tool that allows anyone to create a pattern to be used for
* clothing, wrapping paper, desktop backgrounds, scrapbooking, etc.
* 
*
*/

import processing.pdf.*;

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

color chosenColour = color(random(255), random(255), random(255));
color backgroundColour = color(random(255), random(255), random(255));
color black = color(0, 0, 0);

//used to store previous colours
color[] previousColours = new color[5];
int colourCount = 0;
color lastColour;

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
void setup(){
  size(950, 800);
  background(backgroundColour);
  beginRecord(PDF, "test.pdf"); 
  
  wallpaper = loadImage("wallpaper.jpg");
  toolBar = loadImage("toolBar.png");
  titlePage = loadImage("titles.png");
  tipsPage = loadImage("tips.png");

  smooth();
}

/******************************************* DRAW ******************************************/
void draw(){  
  
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

void drawTitlePage(){
  image(titlePage, 0, 0); //display page image 
  noStroke();
  fill(backgroundColour); 
  rect(560, 575, 230, 33); //fill a rectangle with desired background colour
  select(symmetryX, symmetryY);  //stroke around selected type of symmetry 
  select(layoutX, layoutY);  //stroke around selected layout
}

/***************************************** TIPS PAGE **************************************/

void drawTipsPage(){
  image(tipsPage, 0, 0);
}

/***************************************** PATTERN PAGE **************************************/
//loads the pattern making page when called

void drawPatternMaker(){
    
  //toolbar image
  image(toolBar, 0, 0);  
  
  //draws when mouse is pressed
  if(mousePressed && mouseX>150 && drawingEnabled){
    strokeWeight(brushSize);
    stroke(chosenColour); 
    symmetry();
  }

  //brush size change key commands
  if (keyPressed) {
    if (key == ']') {
      brushSize++;
    }else if (key == '['){
      brushSize--; 
    }
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

void drawPreviousColours(){
  for(int x=0; x<5; x++){
    int xPos = x*21;
    fill(backgroundColour);
    rect(10, 405, 21, 21); 
    
    fill(previousColours[x]);
    rect(xPos+31, 405, 21, 21); 
  }
}




