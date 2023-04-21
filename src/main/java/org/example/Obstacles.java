package org.example;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Obstacles {
    public List<Position> getObstacles() {
        return obstaclesPositions;
    }

    public boolean contains(Position position) {
        return obstaclesPositions.contains(position);
    }

    public void addObstacle(int x, int y, String direction) {
        obstaclesPositions.add(new Position(x, y, direction));
    }

    private final List<Position> obstaclesPositions = new ArrayList<>();
}

