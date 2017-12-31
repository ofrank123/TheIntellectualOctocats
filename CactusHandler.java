import java.util.ArrayList;

/*
  Class deals with keeping track of, creating, and detecting
  collisions for cacti.
 */
public class CactusHandler {
  private ArrayList<Cactus> cacti = new ArrayList<>();
  private Display display;
  private int cactusD, nextCactus; //ticks since last cactus spawn, delta when next cac will be spawned

  //moves and draws cacti, and removes cacti that are out of bounds
  public CactusHandler(Display display) {
    init(display);
  }

  public void init(Display display) {
    this.display = display;
    cactusD = 0;
    nextCactus = (int) ((Math.random()*30) + 15);
    cacti.clear();
  }

  public void updateCacti() {
    int i;
    Cactus thisC;
    for(i = 0; i < cacti.size(); i++) {
      thisC = cacti.get(i);
      thisC.move(-1,0);
      thisC.draw();
      if(thisC.outOfBounds())
        cacti.remove(thisC);
    }
  }

  public boolean detectCollision(Player player) {
    for( Cactus cactus: cacti )
      if(player.colliding(cactus))
        return true;
    return false;
  }

  public void spawnCactus() {
    cactusD++;
    if(cactusD == nextCactus) {
      cacti.add(new Cactus(97, 11, display));
      cactusD = 0;
      nextCactus = (int) ((Math.random()*30) + 15);
    }
  }
}
