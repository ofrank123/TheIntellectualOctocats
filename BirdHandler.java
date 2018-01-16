import java.util.ArrayList;

public class BirdHandler extends EntityHandler{
  public BirdHandler(Display disply){
    init(display);
  }
  public void init(Display display){
    this.display = display;
    entities.clear();
  }
  public void updateEntities(){
    int i;
    Bird thisB;
    for(i = 0; i < entities.size(); i++){
	    thisB = (Bird) entities.get(i);
	    thisB.move(-1, 0);
	    thisB.draw();
	    if(thisB.outOfBounds())
        entities.remove(thisB);
    }
  }
  public boolean detectCollision(Player player){
    for (Entity bird: entities)
	    if(player.colliding((Bird) bird)){
        entities.remove(bird);
        return true;
	    }
    return false;
  }
  public void spawnEntity(){
    nextE--;
    if(nextE == 0){
	    if ((Math.random()* 2) > 1)
        entities.add(new Bird(97, 8, display));
	    else
        entities.add(new Bird(97, 11, display));
	    
	    nextE = (int) ((Math.random()*45)+ 15);
    }
  }
}
