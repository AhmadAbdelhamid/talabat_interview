package org.example;

public class Rover {

    private Direction direction;
    private Obstacles obstacles;

    public Rover(String position) {
        this.direction = Direction.of(position);
        this.obstacles = new Obstacles();
    }

    public Rover(String position, Obstacles obstacles) {
        this(position);
        this.obstacles = obstacles;
    }

    public String getDirectionFor(String command) {

        for (char cmd : command.toCharArray()) {
            if (hasObstacles()) {
                return direction.getStopPosition();
            }
            applyCommand(cmd);
        }
        return direction.getStopPosition();
    }

    private void applyCommand(char cmd) {
        switch (cmd) {
            case 'R' -> turnRight();
            case 'L' -> turnLeft();
            case 'M' -> direction.move();
        }
    }

    private void turnRight() {
        updateDirection(direction.turnRight());
    }

    private void turnLeft() {
        updateDirection(direction.turnLeft());
    }

    private void updateDirection(Direction newState) {
        this.direction = newState;
    }

    private boolean hasObstacles() {
        boolean contains = obstacles.contains(direction.position);
        direction.isBlocked(contains);
        return contains;
    }
}



