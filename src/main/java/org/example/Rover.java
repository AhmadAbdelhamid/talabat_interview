package org.example;

public class Rover {
    private Position position;

    private int[][] obstacles;

    public void setObstacles(int[][] obstacles) {
        this.obstacles = obstacles;
    }

    public Rover() {
        position = new Position();
    }

    public Rover(Position position) {
        this.position = position;
    }

    public String getPositionAsText() {
        return position.getPositionAsText();
    }

    private void turnRight() {
        String direction = position.getDirection();
        String newDirection = switch (direction) {
            case "N" -> "E";
            case "E" -> "S";
            case "S" -> "W";
            case "W" -> "N";
            default -> "";
        };
        position.setDirection(newDirection);
    }

    private void turnLeft() {
        String direction = position.getDirection();
        String newDirection = switch (direction) {
            case "N" -> "W";
            case "W" -> "S";
            case "S" -> "E";
            case "E" -> "N";
            default -> "";
        };
        position.setDirection(newDirection);
    }

    public void move(String command) {
        char[] commands = command.toCharArray();
        for (char cmd : commands) {
            if (cmd == 'R') {
                turnRight();
            } else if (cmd == 'L') {
                turnLeft();
            } else {

                for (int i = 0; i < obstacles.length; i++) {
                    if (position.getX() == obstacles[i][0]
                            && position.getY() == obstacles[i][1]) {
                        position.setObstacle(true);
                        System.out.println("O:" + position.getX() + ":" + position.getY() + ":" + position.getDirection());
                        return;
                    }
                }
                position.addStep();
            }
        }

    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
