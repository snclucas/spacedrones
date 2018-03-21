package org.spacedrones.game;


import static org.lwjgl.glfw.GLFW.glfwInit;

public abstract class Game {

  public static final int TARGET_FPS = 1;
  public static final int TARGET_UPS = 1;


  /**
   * Shows if the game is running.
   */
  protected boolean running;

  /**
   * Used for timing calculations.
   */
  protected Timer timer;

  /**
   * Default contructor for the game.
   */
  public Game() {
    timer = new Timer();

  }

  /**
   * This should be called to initialize and start the game.
   */
  public void start() {
    init();
    gameLoop();
  }

  public void update() {

  }

  public void input() {

  }

  /**
   * Initializes the game.
   */
  public void init() {
    // Initialize GLFW. Most GLFW functions will not work before doing this.
    if (!glfwInit()) {
      throw new IllegalStateException("Unable to initialize GLFW");
    }

    timer.init();
    running = true;
  }



  /**
   * The game loop. <br>
   * For implementation take a look at <code>VariableDeltaGame</code> and
   * <code>FixedTimestepGame</code>.
   */
  public abstract void gameLoop();


  /**
   * Synchronizes the game at specified frames per second.
   *
   * @param fps Frames per second
   */
  public void sync(int fps) {
    double lastLoopTime = timer.getLastLoopTime();
    double now = timer.getTime();
    float targetTime = 1f / fps;

    while (now - lastLoopTime < targetTime) {
      Thread.yield();

            /* This is optional if you want your game to stop consuming too much
             * CPU but you will loose some accuracy because Thread.sleep(1)
             * could sleep longer than 1 millisecond */
      try {
        Thread.sleep(1);
      } catch (InterruptedException ex) {
        //Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
      }

      now = timer.getTime();
    }
  }


}