public class Cell {
    private Coordinate coordinate;
    private Entity entity;
    private boolean isHit;

    public Cell(int x, int y) {
        this.coordinate = new Coordinate(x, y);
        this.isHit = false;
    }

    // Методи
    public boolean hasEntity() { return entity != null; }
    public void setEntity(Entity entity) { this.entity = entity; }
    public boolean isHit() { return isHit; }
    public void hit() { isHit = true; }
    public Entity getEntity() { return entity; }
    public Coordinate getCoordinate() { return coordinate; }
}

