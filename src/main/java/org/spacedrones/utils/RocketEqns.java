package org.spacedrones.utils;


public class RocketEqns {

  private static double g0 = 9.81;//32.2 ;
  private static double p0 = 101300; //14.7 ;
  private static double T0 = 298;//518 ;

  public static double[] compute(double molecularWeight, double throatArea,
                           double aratIn, double gammaIn, double exitTemperature,
                           double ambientPressure, double totalPressure, double exitArea) {
    double temperatureRatio,aircor ;
    double mnsub,ptrat,anso,ansn,pso,psn,deriv ;
    int modeOut = 0;

    final double runiv = 8.31432e3;
    final double rgas = runiv / molecularWeight;

    //gm1   = gammaIn - 1.0 ;
    //fac1  = gm1 / gammaIn ;

    int counter = 0;
    double throatMach = 1.0 ;         // assume flow is choked
    aircor = RocketEqns.getMfr(1.0, rgas, gammaIn, g0, p0, T0) ;
    double exitMach = RocketEqns.getMach(2,(aircor/aratIn), rgas, gammaIn, g0, p0, T0) ;

    double pressureSupersonic = RocketEqns.getPisenRatio(exitMach, gammaIn) * totalPressure ;

    double mflow = aircor* throatArea * (totalPressure/p0)/Math.sqrt(exitTemperature/T0) ;

    double exitPressure = 0.0;

    double uex = 0;
    // under expanded nozzle
    if (ambientPressure <= pressureSupersonic) {
      modeOut = 0 ;
      temperatureRatio = RocketEqns.getTisenRatio(exitMach,gammaIn) ;
      uex = exitMach * Math.sqrt (gammaIn * rgas * temperatureRatio * exitTemperature) ;
      exitPressure = pressureSupersonic ;
    }

    double rns = 0.0;
    double npr;
    // over expanded nozzle
    if (ambientPressure > pressureSupersonic) {
      // find exit pressure at which normal shock leaves the nozzle
      double mnsup = exitMach;
      double psub = pressureSupersonic * RocketEqns.getStaticPressureRatio(mnsup,gammaIn) ;

      // slightly overexpanded .. no shock in nozzle
      if (ambientPressure <= psub) {
        modeOut = 1 ;
        exitPressure = pressureSupersonic ;
        temperatureRatio = RocketEqns.getTisenRatio(exitMach,gammaIn) ;
        uex = exitMach * Math.sqrt(gammaIn * rgas * temperatureRatio * exitTemperature) ;
      }

      // highly overexpanded .. normal shock in nozzle
      if (ambientPressure > psub) {
        modeOut = 2 ;
        exitPressure = ambientPressure ;
        anso  = exitArea ;
        mnsup = exitMach ;
        mnsub = RocketEqns.getDownstreamMachNumber(mnsup,gammaIn) ;
        ptrat = RocketEqns.getTotalPressureRatio(mnsup,gammaIn) ;
        pso = RocketEqns.getPisenRatio(mnsub,gammaIn) * ptrat * totalPressure ;
        ansn = anso - 1. ;
        while ((Math.abs(exitPressure - pso) > .001) && (counter < 20)) {
          ++counter;
          mnsup = RocketEqns.getMach(2,(aircor* throatArea /ansn), rgas,gammaIn, g0, p0, T0) ;
          //mnsub = RocketEqns.getDownstreamMachNumber(mnsup,gammaIn) ;
          ptrat = RocketEqns.getTotalPressureRatio(mnsup,gammaIn) ;
          exitMach = RocketEqns.getMach(0,(aircor/aratIn/ptrat), rgas,gammaIn, g0, p0, T0) ;
          psn = RocketEqns.getPisenRatio(exitMach,gammaIn) * ptrat * totalPressure ;
          deriv = (psn-pso)/(ansn-anso) ;
          pso = psn ;
          anso = ansn ;
          ansn = anso + (exitPressure - pso)/deriv ;
        }
        final double ans = anso;
        rns = Math.sqrt(ans / Math.PI) ;
        temperatureRatio = RocketEqns.getTisenRatio(exitMach,gammaIn) ;
        uex = exitMach * Math.sqrt (gammaIn * rgas * temperatureRatio * exitTemperature) ;
      }
    }

    if (ambientPressure > .0001)
      npr = totalPressure / ambientPressure ;
    else
      npr = 1000.;

    double fgros = mflow * uex / g0 + (exitPressure - ambientPressure) * exitArea ;

    return new double[]{mflow, exitMach, uex, rns, fgros, npr, exitPressure, throatMach, modeOut};
  }

  private static double getGamma(double temp, int opt) {
    // Utility to get gamma as a function of temp
    double number,a,b,c,d ;
    a =  -7.6942651e-13;
    b =  1.3764661e-08;
    c =  -7.8185709e-05;
    d =  1.436914;
    if(opt == 0) {
      number = 1.4 ;
    }
    else {
      number = a*temp*temp*temp + b*temp*temp + c*temp +d ;
    }
    return(number) ;
  }

  private static double getMach (int sub, double corair, double gasConstant, double gamma, double g0, double p0, double T0) {
    /* Utility to get the Mach number given the corrected airflow per area */

    //double front = (g0*p0)/Math.sqrt(T0);

    double number,chokair;//,a,b,k;     /* iterate for mach number */
    double deriv,machn,macho,airo,airn;
    int iter ;

    //a = (gamma -1)/2.0 ;
    //b = -(gamma+1.0)/(2.0*(gamma-1.0)) ;
    //k = front * Math.sqrt(gamma/gasConstant) ;

    chokair = getMfr(1.0,gasConstant,gamma, g0, p0, T0) ;
    if (corair > chokair) {
      number = 1.0 ;
      return (number) ;
    }
    else {
      if (sub == 1) macho = 1.0 ;   /* sonic */
      else {
        if (sub == 2) macho = 1.703 ; /* supersonic */
        else macho = .25;                /* subsonic */
        airo = getMfr(macho,gasConstant,gamma, g0, p0, T0) ;    /* initial guess */
        iter = 1 ;
        machn = macho - .2  ;
        while (Math.abs(corair - airo) > .0001 && iter < 50) {
          airn =  getMfr(machn,gasConstant,gamma, g0, p0, T0) ;
          deriv = (airn-airo)/(machn-macho) ;
          airo = airn ;
          macho = machn ;
          machn = macho + (corair - airo)/deriv ;
          ++ iter ;
        }
      }
      number = macho ;
    }
    return number;
  }

  private static double getStaticPressureRatio(double machNumber, double gamma) {
    // NACA 1135 - normal shock relation ps ratio
    double msq = machNumber * machNumber ;
    double gm1 = gamma - 1.0 ;
    double gp1 = gamma + 1.0 ;
    return (2.0*gamma*msq - gm1)/gp1;
  }

  private static double getTotalPressureRatio(double machin, double gam) {
    // NACA 1135 - normal shock relation pt ratio

    double msq = machin * machin ;
    double gm1 = gam - 1.0 ;
    double gp1 = gam + 1.0 ;

    double fac2 = (2.0*gam*msq - gm1)/gp1 ;
    double fac1 = (gp1*msq)/(gm1*msq + 2.0) ;

    return((Math.pow(fac1,(gam/gm1)))
            * (Math.pow((1.0/fac2),(1.0/gm1)))) ;
  }

  private static double getDownstreamMachNumber(double machin, double gam) {
    // NACA 1135 - normal shock relation  mach

    double msq = machin * machin ;
    double gm1 = gam - 1.0 ;
    double gp1 = gam + 1.0 ;

    double fac2 = (2.0*gam*msq - gm1)/gp1 ;
    double fac1 = (gp1*msq)/(gm1*msq + 2.0) ;
    return Math.sqrt(msq / (fac2 * fac1)) ;
  }

  private static double getMfr(double machNumber, double gasConstant, double gamma, double g0, double p0, double T0) {
    /* Utility to get the corrected weightflow per area given the Mach number */
    double front = (g0*p0)/Math.sqrt(T0);

    double fac2 = (gamma+1.0)/(2.0*(gamma-1.0)) ;
    double fac1 = Math.pow((1.0+.5*(gamma-1.0)*machNumber*machNumber),fac2);
    return front * Math.sqrt(gamma/gasConstant) * machNumber / fac1 ;
  }

  public static void main(String[] args) {
    double corair = getMfr(2, 287.778, 1.4, g0, p0, T0);
    double wflow = corair* 0.093 * (p0/p0)/Math.sqrt(T0/T0) ;
    System.out.println(wflow + " " + wflow/g0);

    double v = getMach(2, corair, 287.778, 1.4, g0, p0, T0);
    System.out.println(v);

    double ddd = getStaticPressureRatio(2, 1.4);
    System.out.println(ddd);

    double dddd = getTotalPressureRatio(2, 1.4);
    System.out.println(dddd);

    double ddddd = getDownstreamMachNumber(2, 1.4);
    System.out.println(ddddd);


  }

  private static double getPisenRatio(double machNumber, double gamma)  {
    /* Utility to get the isentropic pressure ratio given the mach number */
    double machNumberSquared = machNumber*machNumber ;
    double gamma_minus_one = gamma - 1.0 ;
    double fac1 = 1.0 + .5*gamma_minus_one*machNumberSquared;
    return Math.pow(1.0/fac1, gamma/gamma_minus_one) ;
  }

  private static double getTisenRatio(double machNumber, double gamma)  {
      /* Utility to get the isentropic temperature ratio given the mach number*/
    double machNumberSquared = machNumber*machNumber ;
    double gamma_minus_one = gamma - 1.0 ;
    return 1.0 /(1.0 + .5*gamma_minus_one*machNumberSquared);
  }

}
