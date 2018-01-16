import java.util.ArrayList;
/*
  Class deals with keeping track of, creating, and moving entities
 */

public abstract class EntityHandler {
    protected ArrayList<Entity> entities = new ArrayList<>(); //list of all entities to be drawn
    protected Display display;
    protected static int nextE = 45; //ticks until next spawn
    
    public abstract void init(Display display);
    public abstract void updateEntities();
    public abstract void spawnEntity();
    public abstract boolean detectCollision(Player player);
}
