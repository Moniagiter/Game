public class Ship extends Entity {

    @Override
    public boolean isDestroyed() {
        for (Cell cell : cells) {
            if (!cell.isHit()) {
                return false;
            }
        }
        return true;
    }
}
