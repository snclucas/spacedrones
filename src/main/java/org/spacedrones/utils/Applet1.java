package org.spacedrones.utils;


        import java.applet.Applet;
        import java.awt.GridBagLayout;
        import java.awt.GridBagConstraints;
        import java.awt.Insets;
        import java.awt.BorderLayout;
        import java.awt.event.ActionListener;
        import java.awt.event.ActionEvent;
        import java.awt.event.ItemListener;
        import java.awt.event.ItemEvent;
        import java.awt.TextArea;
        import java.awt.List;
        import java.awt.Button;
        import java.awt.Panel;
        import java.text.DecimalFormat;
        import java.util.StringTokenizer;
        import java.awt.Label;
        import java.awt.Font;
        import java.awt.SystemColor;
        import java.awt.Color;


public class Applet1 extends Applet implements ActionListener{

  boolean isStandalone = true;
  int xPixels = 700;
  int yPixels = 400;

  public static TextArea textArea1 = new TextArea(" ",8, 20);
  public static TextArea textArea2 = new TextArea(" ",20, 30);
  public static List list;
  Button button0=null;
  Panel pane=null;
  boolean started=false;
  BorderLayout borderLayout3 = new BorderLayout();
  BorderLayout borderLayout4 = new BorderLayout();
  GridBagLayout gridBagLayout2 = new GridBagLayout();
  BorderLayout borderLayout1 = new BorderLayout();
  Label label1 = new Label();
  Label label2 = new Label();
  Label label3 = new Label();

  //Construct the applet

  public Applet1() {


  }
  public void start () {
    this.setSize(xPixels, yPixels);
    sampleinput.setSamples();
    for(int i=0;i<sampleinput.sampleNames.length;i++)
    {
      list.add(sampleinput.sampleNames[i]);
    }
    textArea1.setText(sampleinput.samples[0]);
  }

  public void actionPerformed(ActionEvent e) {
    boolean needToRun=false;
    String meaning = e.getActionCommand();

    if (needToRun || meaning.equals("Run")) {
      try {
        doSimulation();
      } catch (Exception e1) {
        MyPrint(e1.toString(), 1.0);
      }
    }
  }

  public void doSimulation () throws Exception {
    double WeightLBS, DryMassFraction, FuelCostPerLB, DeltaVkps, ISP;
    double TankPSI, TankMegaPascals, TankGramsPerCC;
    double TankRadiusMeters, TankCylinderMeters;
    String input=textArea1.getText();
    String nt;


    DeltaVkps=0;
    ISP=0;
    WeightLBS=0;
    DryMassFraction=0;
    FuelCostPerLB=0;

    TankPSI=0;
    TankMegaPascals=0;
    TankGramsPerCC=0;
    TankRadiusMeters=0;
    TankCylinderMeters=0;

    double TensileGPa=0;
    double SafetyFactor=2;
    double Density=0;
    double CharacteristicVelocity=0;
    double DeltaV=0;
    double ModulusGPa=0;

    textArea2.setText(" ");


    StringTokenizer parseLine;

    // tokenize aLine using default separators
    //      (white space)
    parseLine = new StringTokenizer(input) ;
    boolean ntUsed = false;
    while (parseLine.hasMoreTokens()) {
      nt = parseLine.nextToken();

      if (nt.equals("/*")) {                // if got comment
        while (parseLine.hasMoreTokens() && !nt.equals("*/")) {  //  discard till close comment
          nt = parseLine.nextToken();
        }
        ntUsed=true;
      }

      if (nt.equals("WeightLBS")) {
        WeightLBS = readDouble(parseLine.nextToken());
        ntUsed=true;
      }
      if (nt.equals("ISP")) {
        ISP = readDouble(parseLine.nextToken());
        ntUsed=true;
      }
      if (nt.equals("DryMassFraction")) {
        DryMassFraction = readDouble(parseLine.nextToken());
        ntUsed=true;
      }
      if (nt.equals("FuelCostPerLB")) {
        FuelCostPerLB = readDouble(parseLine.nextToken());
        ntUsed=true;
      }
      if (nt.equals("DeltaVkps")) {
        DeltaVkps = readDouble(parseLine.nextToken());
        ntUsed=true;
      }
      if (nt.equals("TankPSI")) {
        TankPSI = readDouble(parseLine.nextToken());
        ntUsed=true;
      }
      if (nt.equals("TankMegaPascals")) {
        TankMegaPascals = readDouble(parseLine.nextToken());
        ntUsed=true;
      }
      if (nt.equals("TankGramsPerCC")) {
        TankGramsPerCC = readDouble(parseLine.nextToken());
        ntUsed=true;
      }
      if (nt.equals("TankRadiusMeters")) {
        TankRadiusMeters = readDouble(parseLine.nextToken());
        ntUsed=true;
      }
      if (nt.equals("TankCylinderMeters")) {
        TankCylinderMeters = readDouble(parseLine.nextToken());
        ntUsed=true;
      }
      if (nt.equals("TensileGPa")) {
        TensileGPa = readDouble(parseLine.nextToken());
        ntUsed=true;
      }
      if (nt.equals("Density")) {
        Density = readDouble(parseLine.nextToken());
        ntUsed=true;
      }
      if (nt.equals("SafetyFactor")) {
        SafetyFactor = readDouble(parseLine.nextToken());
        ntUsed=true;
      }
      if (nt.equals("DeltaV")) {
        DeltaV = readDouble(parseLine.nextToken());
        ntUsed=true;
      }
      if (nt.equals("CharacteristicVelocity")) {
        CharacteristicVelocity = readDouble(parseLine.nextToken());
        ntUsed=true;
      }
      if (nt.equals("ModulusGPa")) {
        ModulusGPa = readDouble(parseLine.nextToken());
        ntUsed=true;
      }
      if (!ntUsed) {
        MyPrint("Error - unrecognized keyword: " + nt,  0);
      }
    }

    if (TensileGPa != 0 && Density != 0 && CharacteristicVelocity == 0) {
      CharacteristicVelocity = Math.sqrt(TensileGPa*1000000000.0/
              SafetyFactor/Density);
    }
    if (DeltaV != 0 && DeltaVkps==0) {
      DeltaVkps= DeltaV / 1000.0;
    }
    if (DeltaV == 0 && DeltaVkps!=0) {
      DeltaV= DeltaVkps * 1000.0;
    }
    if (DryMassFraction > 0) {
      outputRocketEquation(WeightLBS, ISP, DryMassFraction,
              FuelCostPerLB, DeltaVkps);
    } else if (TankPSI > 0) {
      outputTank(TankPSI, TankMegaPascals, TankGramsPerCC,
              TankRadiusMeters, TankCylinderMeters);
    } else if (CharacteristicVelocity != 0) {
      outputTetherMassFraction(CharacteristicVelocity, DeltaV);
    } else if (ModulusGPa != 0) {
      outputSpeedOfSound(ModulusGPa, Density);
    } else {
      MyPrint("Did not get good set of input.", 0);
    }
  }

  private void outputSpeedOfSound(double ModulusGPa, double Density) {
    double modulus = ModulusGPa * 1000000000.0;
    double speedOfSound = Math.sqrt(modulus / Density);
    MyPrint("speedOfSound in km/sec = ", speedOfSound/1000.0);
  }
  // From space tethers guide  page 178 (if read correctly):
  //  Mt/Mp (mass of tether over mass of payload) is =
  //      sqrt(pi/2) * X * exp(X*X/pi)
  //  Where X = deltaV / (k * Vc)
  //  Where Vc = Characteristic Velocity
  //  Where k = 1 for spinning
  private void outputTetherMassFraction(double charVel, double deltaV) {
    double k = 1.0;
    double X = deltaV / (k * charVel);
    double MassRatio=0;
    if (X<1) {
      MassRatio = X * X;
    } else {
      MassRatio = Math.sqrt(Math.PI / 2.0) * X * Math.exp(X * X / 2.0);
    }
    MyPrint("tipSpeed is ", deltaV);
    MyPrint("charVel is ", charVel);
    MyPrint("Mass ratio is ratio of tether/payload.");
    MyPrint("MassRatio for huge ballast ", MassRatio);
    MyPrint("MassRatio for payload=ballast ", 2.0*MassRatio);
  }

  /*
  Delta_V = Isp * g * ln(Mo/Mf)
  Delta_V/(Isp * g) = ln(Mo/Mf)
  Mo/Mf = exp( Delta_V/(Isp * g) )
  Mo = Mf * exp(Delta_V/(Isp * g))

  Delta_V  change in velocity, in units compatible with the value you use for g
  Isp  specific impulse, in seconds
  g  acceleration of gravity at the earth's surface
  32.174 ft/sec/sec (9.805 m/sec/sec)
  ln()  natural logarithm function
  Mo  Mass before the burn
  Mf  Mass after the burn
  */

  private void outputRocketEquation(double WeightLBS,
                                    double ISP, double DryMassFraction,
                                    double FuelCostPerLB, double DeltaVkps) throws Exception {

    double g, MoMf,  sfrac, FuelCost;
    double FuelFraction, PayloadFraction, RocketAndPayloadFraction;
    double FuelLBS, PayloadLBS,  Mo, Mf, DeltaVmph, DeltaV;

    g=9.8;           /* g=32.174; */
    RocketAndPayloadFraction=0.0;
    DeltaV = DeltaVkps*1000.0;

    MoMf = java.lang.Math.exp( DeltaV/(ISP * g) );
    RocketAndPayloadFraction = 1.0/MoMf;
    FuelFraction = 1.0-RocketAndPayloadFraction;
/*    DeltaV = isp * g * ln(Mo/Mf); */

    DeltaVkps = DeltaV/1000.0;
    DeltaVmph =DeltaVkps*3600/1.6;
    FuelLBS= WeightLBS *FuelFraction;
    FuelCost = FuelLBS * FuelCostPerLB;
    PayloadFraction = RocketAndPayloadFraction - DryMassFraction;
    PayloadLBS=WeightLBS*PayloadFraction;

    MyPrint("The delta V in meters/sec", DeltaV);
    MyPrint("            in mph", DeltaVmph);
    MyPrint("            in kps", DeltaVkps);

    MyPrint("The fuel fraction is", FuelFraction);
    MyPrint("Total weight (lbs)", WeightLBS);
    MyPrint("Percent structure", 100.0*DryMassFraction);

    MyPrint("LBS Fuel", FuelLBS);
    MyPrint("Total fuel cost", FuelCost);

    MyPrint("Rocket LBS", WeightLBS * DryMassFraction);

    MyPrint("Payload LBS", PayloadLBS);
    MyPrint("Dollars on fuel per lbs payload", FuelCost/PayloadLBS);

  }

  private void outputTank(double TankPSI, double TankMegaPascals,
                          double TankGramsPerCC, double TankRadiusMeters,
                          double TankCylinderMeters) throws Exception {
    double PI = java.lang.Math.PI;
    double PSItoMetric = 6894.757;
    double Pressure = TankPSI * PSItoMetric;
    double TankDiameter = 2.0 * TankRadiusMeters;
    double GramsPerCCtoKgPerMeter2 = 1000000.0/1000.0;      // to Kg/meter^2
    double Density = TankGramsPerCC * GramsPerCCtoKgPerMeter2;
    double Rsquared = TankRadiusMeters * TankRadiusMeters;
    double Rcubed = Rsquared * TankRadiusMeters;
    double Pascals = TankMegaPascals * 1000000.0;


    double SplitCylinderArea = TankCylinderMeters * TankDiameter;
    double SplitCylinderForce = Pressure * SplitCylinderArea;
    double CylinderSplitLine = 2.0 * TankCylinderMeters;
    double CylinderThickness = SplitCylinderForce /
            (CylinderSplitLine * Pascals);
    double CylinderArea = PI * TankDiameter * TankCylinderMeters;
    double CylinderMass = CylinderThickness * CylinderArea * Density;
    double CylinderVolume = PI * Rsquared * TankCylinderMeters;
    if (TankCylinderMeters <= 0) {
      CylinderThickness=0;
      CylinderMass=0;
      CylinderVolume=0;
    }

    double SplitSphereArea = PI * Rsquared;
    double SplitSphereForce = Pressure * SplitSphereArea;
    double SphereSplitLine =  PI * TankDiameter;
    double SphereThickness = SplitSphereForce /
            (SphereSplitLine * Pascals);
    double SphereArea = 4.0 * PI * Rsquared;      // both halves
    double SphereMass = SphereThickness * SphereArea * Density;

    double TotalMass = CylinderMass + SphereMass;

    double SphereVolume = 4.0/3.0 * PI * Rcubed;

    double TankVolume = SphereVolume + CylinderVolume;
    double TankGallons = TankVolume * 264.172048;

    MyPrint("CylinderThickness in cm ", CylinderThickness*100.0);
    MyPrint("SphereThickness in cm ", SphereThickness*100.0);
    MyPrint("CylinderMass in Kg ", CylinderMass);
    MyPrint("SphereMass in Kg ", SphereMass);
    MyPrint("Total mass in Kg ", TotalMass);
    MyPrint("Mass in pounds ", TotalMass * 2.2046);
    MyPrint("TankVolume in meters cubed ", TankVolume);
    MyPrint("TankVolume in US liquid gallons ", TankGallons);
    MyPrint("TankVolume in liters ", TankVolume*1000.0);
    MyPrint("TankVolume in cubic feet ", TankVolume*33.31466);
  }

  private void MyPrint(String s, double n) {
    textArea2.append(s + " " + dTS(n) + "\n");
  }
  private void MyPrint(String s) {
    textArea2.append(s + "\n");
  }


  // dTS = double To String
  public static String dTS(double input) {
    DecimalFormat fmt = new DecimalFormat("#####.##");
    return(fmt.format(input));
  }


  public static double  readDouble(String s) throws Exception {
    try {
      return new Double(s).doubleValue();
    } catch (NumberFormatException e) {
      throw new Exception("Input not a double: " + s);
    }
  }

//Initialize the applet

  public void init() {
    try {
      jbInit();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

//Component initialization

  private void jbInit() throws Exception {

    button0 = new Button("Run");
    button0.setActionCommand("Run");
    button0.addActionListener(this);

    list = new List();
    this.setLayout(borderLayout1);
    list.setMultipleMode(false);

    textArea1.setEditable(true);

    textArea2.setText(" ");
    textArea2.setEditable(false);

    pane = new Panel();
    list.setForeground(SystemColor.textText);
    list.setBackground(new Color(240, 240, 240));
    textArea2.setForeground(SystemColor.textText);
    textArea2.setBackground(new Color(240, 240, 240));
    textArea1.setForeground(SystemColor.textText);
    textArea1.setBackground(new Color(240, 240, 240));
    button0.setFont(new Font("Dialog", 1, 12));
    button0.setBackground(SystemColor.control);
    button0.setForeground(SystemColor.controlText);
    label2.setText("Sample Inputs (click one)");
    label2.setFont(new Font("Dialog", 1, 12));
    label2.setForeground(SystemColor.controlText);
    label3.setText("Output");
    label3.setFont(new Font("Dialog", 1, 12));
    label3.setForeground(SystemColor.controlText);
    label1.setText("Input (you can change this)");
    label1.setFont(new Font("Dialog", 1, 12));
    label1.setForeground(SystemColor.controlText);
    list.addItemListener(new Applet1_list_itemAdapter(this));
    button0.addActionListener(new Applet1_button0_actionAdapter(this));
    pane.setLayout(gridBagLayout2);
    pane.setForeground(SystemColor.windowText);
    pane.setBackground(SystemColor.window);

    pane.add(list, new GridBagConstraints(1, 2, 1, 1, 0.5, 0.5, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(3, 3, 3, 3), 200, 200));
    pane.add(textArea1, new GridBagConstraints(1, 4, 1, 1, 0.5, 0.5, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(3, 3, 3, 3), 200, 150));
    pane.add(button0, new GridBagConstraints(1, 5, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(10, 10, 3, 10), 50, 8));
    pane.add(textArea2, new GridBagConstraints(2, 2, 1, 4, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(3, 3, 3, 3), 200, 200));
    pane.add(label1, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0, GridBagConstraints.SOUTHWEST, GridBagConstraints.NONE, new Insets(3, 5, 1, 5), 0, 0));
    pane.add(label2, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0, GridBagConstraints.SOUTHWEST, GridBagConstraints.NONE, new Insets(3, 5, 1, 5), 0, 0));
    pane.add(label3, new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0, GridBagConstraints.SOUTHWEST, GridBagConstraints.NONE, new Insets(3, 5, 1, 5), 0, 0));
    this.add(pane, BorderLayout.CENTER);

    // this.update(this.getGraphics());
    repaint();
    started=true;
  }
//Get Applet information

  public String getAppletInfo() {
    return "Applet Information";
  }
//Get parameter info

  public String[][] getParameterInfo() {
    return null;
  }

  void list_itemStateChanged(ItemEvent e)
  {
    int i = list.getSelectedIndex();
    if (i>=0 && i<sampleinput.numSamples) {
      textArea1.setText(sampleinput.samples[i]);
    }
  }

  void button0_actionPerformed(ActionEvent e)
  {

  }
}

class Applet1_button0_actionAdapter implements ActionListener
{
  Applet1 adaptee;

  Applet1_button0_actionAdapter(Applet1 adaptee)
  {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e)
  {
    adaptee.button0_actionPerformed(e);
  }
}

class Applet1_list_itemAdapter implements ItemListener
{
  Applet1 adaptee;

  Applet1_list_itemAdapter(Applet1 adaptee)
  {
    this.adaptee = adaptee;
  }

  public void itemStateChanged(ItemEvent e)
  {
    adaptee.list_itemStateChanged(e);
  }
}