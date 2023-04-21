package org.example;

public class Position {

    public static final int GRID_WIDTH = 10;
    public static final int GRID_HEIGHT = 10;
    private int x, y;
    private boolean isObstacle = false;
    private String direction;
    public Position() {
        this.x = 0;
        this.y = 0;
        this.direction = "N";
    }

    public int getX() {
        return x;
    }


    public int getY() {
        return y;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getPositionAsText() {
        if (isObstacle) {
            return "%s:%d:%d:%s".formatted("O", getX(), getY(), getDirection());
        }
        return "%d:%d:%s".formatted(getX(), getY(), getDirection());
    }

    public void addStep() {
        switch (direction) {
            case "N" -> {
                if (y == GRID_HEIGHT) y = 0;
                else y++;
            }
            case "S" -> {
                if (y == 0) y = GRID_HEIGHT;
                else y--;
            }
            case "E" -> {
                if (x == GRID_WIDTH) x = 0;
                else x++;
            }
            case "W" -> {
                if (x == 0) x = GRID_WIDTH;
                else x--;
            }
        }
    }

    public void setObstacle(boolean obstacle) {
        isObstacle = obstacle;
    }

    public void setPosition(String position) {
        positionParser(position);
    }

    private void positionParser(String pos) {
        String[] split = pos.split(":");
        this.x = Integer.parseInt(split[0]);
        this.y = Integer.parseInt(split[1]);
        this.direction = split[2];
    }
}
