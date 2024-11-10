import java.util.ArrayList;
import java.util.List;

public abstract class Entity {
    protected List<Cell> cells;

    public Entity() {
        cells = new ArrayList<>();
    }

    public void addCell(Cell cell) {
        cells.add(cell);
        cell.setEntity(this);
    }

    public abstract boolean isDestroyed();
}
