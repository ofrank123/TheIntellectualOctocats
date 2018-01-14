import java.util.ArrayList;

/*
  Class deals with keeping track of, creating, and detecting
  collisions for cacti. nextE keeps track of when the next
  cactus should be spawned
*/
public class CactusHandler extends EntityHandler {
    //Inherits entities ArrayList, display, and nextE from EntityHandler

    public CactusHandler(Display display) {
	init(display);
    }

    //Initialize the cactus handler
    public void init(Display display) {
	this.display = display;
	entities.clear(); //clears all cacti currently in list
    }

    public void updateEntities() {
	int i;
	Cactus thisC;
	for(i = 0; i < entities.size(); i++) {
	    thisC = (Cactus) entities.get(i);
	    thisC.move(-1,0);
	    thisC.draw();
	    if(thisC.outOfBounds())
		entities.remove(thisC);
	}
    }

    public boolean detectCollision(Player player) {
	for( Entity cactus: entities )
	    if(player.colliding((Cactus) cactus)){
		entities.remove(cactus);
		return true;
	    }
	return false;
    }

    public void spawnEntity() {
	nextE--;
	if(nextE == 0) {
	    entities.add(new Cactus(97, 11, display));
	    nextE = (int) ((Math.random()*30) + 15);
	}
    }
}
