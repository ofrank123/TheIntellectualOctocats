import java.util.ArrayList;

/*
  Class deals with keeping track of, creating, and detecting
  collisions for cacti.
 */
public class CactusHandler {
  private ArrayList<Cactus> cacti = new ArrayList<>();
  private ArrayList<Cactus> allCacti = new ArrayList<>();

  //moves and draws cacti, and removes cacti that are out of bounds
  public void updateCacti() {
    int i;
    Cactus thisC;
    for(i = 0; i < cacti.size(); i++) {
      thisC = cacti.get(i);
      thisC.move(0,-1);
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

  //adds cacti to the lists of cacti
  private void addC(Cactus cactus){
    cacti.add(cactus);
    allCacti.add(cactus);
  }

}
