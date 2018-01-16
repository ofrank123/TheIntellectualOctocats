/*
  Entity class, superclass for all objects that needs to change
  position in game (everything besides floor).  An Entity's
  location is the top leftmost pixel of its matrix.
  E.g:
  012345
  0
  1 ====
  2 |  |
  3 |  |
  4 ====
  5
  This entity would be stored in a 4x4 matrix and it's
  location would be (1,1).
*/
public abstract class Entity {
    protected char[][] entMatrix;
    protected int[] location = new int[2];
    protected Display display;

    public void draw() {
	for(int i = 0; i < entMatrix.length; i++)
	    for(int j = 0; j < entMatrix[i].length; j++) {
		display.setLoc( location[1] + i, location[0] + j,
				entMatrix[i][j]);
	    }
    }

    public boolean outOfBounds() {
	return location[0] <= 1 ||
	    (location[0] + entMatrix.length + 1 ) > 100;
    }

    public void move(int x, int y){
	location[0] += x;
	location[1] += y;
	draw();
    }
    public int getX(){
	return location[0];
    }
    public int getY(){
	return location[1];
    }

}
