

/********************************** MOUSE CLICK ACTIONS ************************************/

void mouseClicked() {  

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
        endRecord();
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
void select(int x, int y) {
  stroke(225, 0, 0);
  strokeWeight(3);
  fill(0, 0);
  rect(x, y, 103, 103);
}


/***************************************** LAYOUTS **************************************/

//arranges repetition of pattern in brick form when called
void loadBrickLayout() {
  wallpaper.resize(400, 400);
  image(wallpaper, 150, 0);
  image(wallpaper, 550, 0);
  image(wallpaper, -50, 400);
  image(wallpaper, 350, 400);
  image(wallpaper, 750, 400);
}

//arranges repetition of pattern in grid form when called
void loadGridLayout() {
  wallpaper.resize(400, 400);
  image(wallpaper, 150, 0);
  image(wallpaper, 550, 0);
  image(wallpaper, 150, 400);
  image(wallpaper, 550, 400);
}

