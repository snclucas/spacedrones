package org.spacedrones.spacecraft;


import org.spacedrones.components.SpacecraftBusComponent;
import org.spacedrones.components.propulsion.Engine;
import org.spacedrones.components.propulsion.EngineFactory;
import org.spacedrones.components.sensors.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiPredicate;

public class IsComponentOfType implements BiPredicate<SpacecraftBusComponent, Class<? extends SpacecraftBusComponent>> {

  @Override
  public boolean test(final SpacecraftBusComponent lhs, Class<? extends SpacecraftBusComponent> rhs) {
    return lhs.getClass() == rhs ||  rhs.isAssignableFrom(lhs.getClass()) ||
            Arrays.stream(lhs.getClass().getInterfaces()).anyMatch(s -> s == rhs);
  }

  public static void main(String[] args) {
    SpacecraftBusComponent s1 = SensorFactory.getSensor("LinearSensorArray", SensorType.OPTICAL, 1);
    Sensor s2 = SensorFactory.getSensor("LinearSensorArray", SensorType.OPTICAL, 1);
    LinearSensorArray s3 = (LinearSensorArray) SensorFactory.getSensor("LinearSensorArray", SensorType.OPTICAL, 1);

    Sensor s4 = SensorFactory.getSensor("FractalSensorArray", SensorType.OPTICAL, 1);

    SpacecraftBusComponent e1 = EngineFactory.getEngine("SimpleIonEngine", false);

    List<SpacecraftBusComponent> componentList = new ArrayList<>();
    componentList.add(s1);
    componentList.add(s2);
    componentList.add(s3);
    componentList.add(s4);


    componentList.add(e1);

    BiPredicate<SpacecraftBusComponent, Class<? extends SpacecraftBusComponent>> dd = new IsComponentOfType();

    long res1 = componentList.stream().filter(s -> dd.test(s, Sensor.class)).count();
    long res2 = componentList.stream().filter(s -> dd.test(s, FractalSensorArray.class)).count();
    System.out.println(res1);
    System.out.println(res2);

    long res3 = componentList.stream().filter(s -> dd.test(s, Engine.class)).count();
    System.out.println(res3);
  }

}
