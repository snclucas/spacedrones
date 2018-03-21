package org.spacedrones.game;


public class GameLoop extends Game {

  public GameLoop() {
    start();
  }


  public void gameLoop() {

    float delta;
    float accumulator = 0f;
    float interval = 1f / TARGET_UPS;
    float alpha;

    while (running) {

      /* Get delta time and update the accumulator */
      delta = timer.getDelta();
      accumulator += delta;

      /* Handle input */
      input();


      /* Update game and timer UPS if enough time has passed */
      while (accumulator >= interval) {
        update();
        timer.updateUPS();
        accumulator -= interval;
      }

      /* Calculate alpha value for interpolation */
      alpha = accumulator / interval;

      System.out.println(delta + " " + accumulator + " " + alpha + " " + interval);

      timer.updateFPS();

      /* Update timer */
      timer.update();
    }
  }





  public static void main(String[] args) {
    new GameLoop();
  }

}
