import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.*;

import tester.*;
import javalib.impworld.*;
import java.awt.Color;

import javalib.worldimages.*;

class ProjectWorld extends World {

  int base; //basse number that the user inputs
  ArrayList<String> digits = new ArrayList<String>(); //representation of this.num in the given base
  int num; //decimal number that the user inputs
  //alphabet for representations greater than 10
  final ArrayList<String> alphabet = new ArrayList<String>(
      Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P",
          "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"));
  final int size = 600; //used for the WorldScene and for calculations

  boolean hasBase = false;
  boolean hasNum = false;
  int circleSize;

  ProjectWorld() {
    this.getNums();
  }

  ProjectWorld(int base) {
    this.base = base;
    this.hasBase = true;
  }

  ProjectWorld(int base, int num) {
    this.base = base;
    this.num = num;
    this.hasBase = true;
    this.hasNum = true;
    this.getRepresentation();
  }

  //updates this.digits to create the representation of
  //this.num based on this.base
  public void getRepresentation() {
    this.digits = new ArrayList<String>();
    while (num > 0) {
      int remainder = this.num % this.base;
      if (remainder > 9) {
        this.digits.add(0, this.alphabet.get(remainder - 10));
      }
      else {
        this.digits.add(0, Integer.toString(remainder));
      }
      num /= base;
    }

    this.circleSize = this.size / (this.base * this.digits.size());

  }

  //generates the number representation WorldScene
  public WorldScene makeScene() {
    WorldScene finalScene = new WorldScene(2 * this.size, this.size);
    if (this.hasNum && this.hasBase) {
      int numDigits = this.digits.size();
      int val;
      for (int i = 0; i < numDigits; i++) {
        String s = this.digits.get(i);
        int power = numDigits - 1 - i;
        try {
          val = Integer.parseInt(s);
        }
        catch (NumberFormatException nfe) {
          val = this.alphabet.indexOf(s) + 10;
        }
        //writing out the power
        int x = (1 + i) * (this.size / numDigits);
        finalScene.placeImageXY(new TextImage(this.base + "^" + power, 15, Color.black), x,
            this.size - 50);
        //writing out the value of the digit
        finalScene.placeImageXY(new TextImage(s, 15, Color.black), x, this.size - 25);
        for (int j = 0; j < val; j++) {
          int y = this.size - 50 - (j + 1) * this.size / this.base;
          finalScene.placeImageXY(new CircleImage(this.circleSize, OutlineMode.SOLID, Color.blue),
              x, y);
        }
      }
    }

    return finalScene;
  }

  //creates the windows that will prompt the user for inputs for this.base and this.num
  void getNums() {
    while (!this.hasBase) {
      try {

        this.base = Integer.parseInt(JOptionPane.showInputDialog(
            "Please enter a integer numerically (i.e: hexadecimal -> 16, no greater than 36 )"));
        if (this.base > 36) {

        }
        else {
          this.hasBase = true;
        }

      }
      //catch if the user does not enter a number
      catch (NumberFormatException nfe) {
        JOptionPane.showMessageDialog(null, "You must enter a number");
      }
    }
    while (!this.hasNum) {
      try {
        this.num = Integer.parseInt(JOptionPane.showInputDialog(
            "Please enter a number in decimal you want to convert to the given base"));
        this.hasNum = true;
      }
      catch (NumberFormatException nfe) {
        JOptionPane.showMessageDialog(null, "You must enter a number");
      }

      this.getRepresentation();

    }
  }
}

class Examples {

  ProjectWorld binary = new ProjectWorld(2);
  ProjectWorld octal = new ProjectWorld(8);
  ProjectWorld hexadecimal = new ProjectWorld(16);

  ProjectWorld drawBinary = new ProjectWorld(2, 10);
  ProjectWorld drawOctal = new ProjectWorld(8, 10);
  ProjectWorld drawHexadecimal = new ProjectWorld(16, 24);

  WorldScene finalSceneBinary;

  void initMakeSceneBinary() {
    this.finalSceneBinary = new WorldScene(2 * 600, 600);
    int numDigits = this.drawBinary.digits.size();
    int val;
    for (int i = 0; i < numDigits; i++) {
      String s = this.drawBinary.digits.get(i);
      int power = numDigits - 1 - i;
      try {
        val = Integer.parseInt(s);
      }
      catch (NumberFormatException nfe) {
        val = this.drawBinary.alphabet.indexOf(s) + 10;
      }
      //writing out the power
      int x = (1 + i) * (600 / numDigits);
      this.finalSceneBinary.placeImageXY(
          new TextImage(this.drawBinary.base + "^" + power, 15, Color.black), x, 600 - 50);
      //writing out the value of the digit
      this.finalSceneBinary.placeImageXY(new TextImage(s, 15, Color.black), x, 600 - 25);
      for (int j = 0; j < val; j++) {
        int y = 600 - 50 - (j + 1) * 600 / this.drawBinary.base;
        this.finalSceneBinary.placeImageXY(
            new CircleImage(600 / (4 * this.drawBinary.base), OutlineMode.SOLID, Color.blue), x, y);
      }
    }
  }

  WorldScene finalSceneOctal;

  void initMakeSceneOctal() {
    this.finalSceneOctal = new WorldScene(2 * 600, 600);
    int numDigits = this.drawOctal.digits.size();
    int val;
    for (int i = 0; i < numDigits; i++) {
      String s = this.drawOctal.digits.get(i);
      int power = numDigits - 1 - i;
      try {
        val = Integer.parseInt(s);
      }
      catch (NumberFormatException nfe) {
        val = this.drawOctal.alphabet.indexOf(s) + 10;
      }
      //writing out the power
      int x = (1 + i) * (600 / numDigits);
      this.finalSceneOctal.placeImageXY(
          new TextImage(this.drawOctal.base + "^" + power, 15, Color.black), x, 600 - 50);
      //writing out the value of the digit
      this.finalSceneOctal.placeImageXY(new TextImage(s, 15, Color.black), x, 600 - 25);
      for (int j = 0; j < val; j++) {
        int y = 600 - 50 - (j + 1) * 600 / this.drawOctal.base;
        this.finalSceneOctal.placeImageXY(
            new CircleImage(600 / (2 * this.drawOctal.base), OutlineMode.SOLID, Color.blue), x, y);
      }
    }
  }

  WorldScene finalSceneHexadecimal;

  void initMakeSceneHexadecimal() {
    this.finalSceneHexadecimal = new WorldScene(2 * 600, 600);
    int numDigits = this.drawHexadecimal.digits.size();
    int val;
    for (int i = 0; i < numDigits; i++) {
      String s = this.drawHexadecimal.digits.get(i);
      int power = numDigits - 1 - i;
      try {
        val = Integer.parseInt(s);
      }
      catch (NumberFormatException nfe) {
        val = this.drawHexadecimal.alphabet.indexOf(s) + 10;
      }
      //writing out the power
      int x = (1 + i) * (600 / numDigits);
      this.finalSceneHexadecimal.placeImageXY(
          new TextImage(this.drawHexadecimal.base + "^" + power, 15, Color.black), x, 600 - 50);
      //writing out the value of the digit
      this.finalSceneHexadecimal.placeImageXY(new TextImage(s, 15, Color.black), x, 600 - 25);
      for (int j = 0; j < val; j++) {
        int y = 600 - 50 - (j + 1) * 600 / this.drawHexadecimal.base;
        this.finalSceneHexadecimal.placeImageXY(
            new CircleImage(600 / (2 * this.drawHexadecimal.base), OutlineMode.SOLID, Color.blue),
            x, y);
      }
    }
  }

  public void testGetRep(Tester t) {
    binary.num = 16;
    binary.getRepresentation();
    t.checkExpect(binary.digits, new ArrayList<String>(Arrays.asList("1", "0", "0", "0", "0")));
    octal.num = 16;
    octal.getRepresentation();
    t.checkExpect(octal.digits, new ArrayList<String>(Arrays.asList("2", "0")));
    this.hexadecimal.num = 16;
    this.hexadecimal.getRepresentation();
    t.checkExpect(this.hexadecimal.digits, new ArrayList<String>(Arrays.asList("1", "0")));
    this.hexadecimal.num = 15;
    this.hexadecimal.getRepresentation();
    t.checkExpect(this.hexadecimal.digits, new ArrayList<String>(Arrays.asList("F")));
    this.hexadecimal.num = 255;
    this.hexadecimal.getRepresentation();
    t.checkExpect(this.hexadecimal.digits, new ArrayList<String>(Arrays.asList("F", "F")));

  }

  public void testMakeScene(Tester t) {
    this.initMakeSceneBinary();
    this.initMakeSceneOctal();
    this.initMakeSceneHexadecimal();
    t.checkExpect(this.drawBinary.makeScene(), this.finalSceneBinary);
    t.checkExpect(this.drawOctal.makeScene(), this.finalSceneOctal);
    t.checkExpect(this.drawHexadecimal.makeScene(), this.finalSceneHexadecimal);
  }

  public void testWorld(Tester t) {
    ProjectWorld testWorld = new ProjectWorld();
    testWorld.bigBang(750, 750);

  }
}