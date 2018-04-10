package org.spacedrones.interpretor;


import groovy.lang.Binding;
import groovy.lang.GroovyShell;
import org.codehaus.groovy.control.CompilerConfiguration;
import org.codehaus.groovy.control.customizers.ImportCustomizer;

import java.util.Date;

public class Driver {

  public Driver() {

    applyConfigurationScript();
  }


  private void applyConfigurationScript() {

    Binding binding = new Binding();
    //binding.setVariable("configuration", configuration);

    CompilerConfiguration configuratorConfig = new CompilerConfiguration();
    ImportCustomizer customizer = new ImportCustomizer();
    customizer.addStaticStars("org.codehaus.groovy.control.customizers.builder.CompilerCustomizationBuilder");

    customizer.addStaticStars("org.spacedrones.game.Driver");

    configuratorConfig.addCompilationCustomizers(customizer);



    GroovyShell shell = new GroovyShell(binding, configuratorConfig);
    try {
      Integer result = 0;
      Date start = new Date();



      binding.setVariable("Driver", new org.spacedrones.game.Driver());
      binding.setVariable("input", 2);
      String script = "Driver.online()";
      Object gg = shell.evaluate(script);

      System.out.println(gg);


      Date end = new Date();
      System.out.println("Groovy:time is : " + (end.getTime() - start.getTime()) + ",result is " + result);

    } catch (Exception e) {
      System.out.println("Could not execute Groovy compiler configuration script: ");
      System.out.println(e.getMessage());
    }
  }



  public static void main(String[] args) {
    new Driver();
  }

}
