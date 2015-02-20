
/************************************* SYMMETRY **************************************/

//runs the type of symmetry chosen on the title page
void symmetry(){
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

 void symmetryDefault(){ //8 way
    line(pmouseX, pmouseY, mouseX, mouseY);
    line(pmouseX, height-pmouseY, mouseX, height-mouseY);
    line(width-pmouseX+150, pmouseY, width-mouseX+150, mouseY);
    line(width-pmouseX+150, height-pmouseY, width-mouseX+150, height-mouseY);
    line(pmouseY+150, pmouseX-150, mouseY+150, mouseX-150);
    line(pmouseY+150, height-pmouseX+150, mouseY+150, height-mouseX+150); 
    line(width-pmouseY, pmouseX-150, width-mouseY, mouseX-150);
    line(width-pmouseY, height-pmouseX+150, width-mouseY, height-mouseX+150); 
}

void noSymmetry(){ 
  line(pmouseX, pmouseY, mouseX, mouseY);
}

void symmetryRotate(){ 
  line(pmouseX, pmouseY, mouseX, mouseY);
  line(width-pmouseX+150, height-pmouseY, width-mouseX+150, height-mouseY);
  line(pmouseY+150, height-pmouseX+150, mouseY+150, height-mouseX+150); 
  line(width-pmouseY, pmouseX-150, width-mouseY, mouseX-150);
}

void symmetryPlus(){ // +
   line(pmouseX, pmouseY, mouseX, mouseY);
   line(pmouseX, height-pmouseY, mouseX, height-mouseY);
   line(width-pmouseX+150, pmouseY, width-mouseX+150, mouseY);
   line(width-pmouseX+150, height-pmouseY, width-mouseX+150, height-mouseY);
}

void symmetryVertical(){ 
  line(pmouseX, pmouseY, mouseX, mouseY);
  line(width-pmouseX+150, pmouseY, width-mouseX+150, mouseY);
}

void symmetryHorizontal(){
  line(pmouseX, pmouseY, mouseX, mouseY);
  line(pmouseX, height-pmouseY, mouseX, height-mouseY);
}

void symmetryCross(){ // x
  line(pmouseX, pmouseY, mouseX, mouseY);
  line(width-pmouseX+150, height-pmouseY, width-mouseX+150, height-mouseY);
  line(pmouseY+150, pmouseX-150, mouseY+150, mouseX-150);
  line(width-pmouseY, height-pmouseX+150, width-mouseY, height-mouseX+150); 
}

void symmetry45(){  // / 
  line(pmouseX, pmouseY, mouseX, mouseY);
  line(width-pmouseY, height-pmouseX+150, width-mouseY, height-mouseX+150); 
}

void symmetry135(){  // \
  line(pmouseX, pmouseY, mouseX, mouseY);
  line(pmouseY+150, pmouseX-150, mouseY+150, mouseX-150);
}

