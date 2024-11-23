package player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ComputerPlayer implements Player {
    private final int gridSize;
    private final List<int[]> availableMoves;
    private final Random random;

    public int[] lastHit = null;
    private int directionIndex = 0;
    private final int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public ComputerPlayer(int gridSize) {
        this.gridSize = gridSize;
        this.random = new Random();
        this.availableMoves = new ArrayList<>();
        for (int x = 0; x < gridSize; x++) {
            for (int y = 0; y < gridSize; y++) {
                availableMoves.add(new int[]{x, y});
            }
        }
    }

    @Override
    public int[] getCoordinates() {
        if (lastHit != null) {
            for (int i = directionIndex; i < directions.length; i++) {
                int nextX = lastHit[0] + directions[i][0];
                int nextY = lastHit[1] + directions[i][1];

                if (isValidMove(nextX, nextY)) {
                    directionIndex = i;
                    return new int[]{nextX, nextY};
                }
            }
            lastHit = null;
            directionIndex = 0;
        }

        return getRandomMove();
    }


    private boolean isValidMove(int x, int y) {
        return x >= 0 && x < gridSize && y >= 0 && y < gridSize && availableMoves.contains(new int[]{x, y});
    }

    private int[] getRandomMove() {
        if (availableMoves.isEmpty()) {
            throw new IllegalStateException("No more moves available for the computer.");
        }

        int index = random.nextInt(availableMoves.size());
        return availableMoves.remove(index);
    }

    public void onHit(int x, int y) {
        lastHit = new int[]{x, y};
        directionIndex = 0;
    }
    public void onMiss() {
        if (lastHit != null) {
            directionIndex++;
            if (directionIndex >= directions.length) {
                lastHit = null;
                directionIndex = 0;
            }
        }
    }

}
