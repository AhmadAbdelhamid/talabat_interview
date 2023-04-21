package org.example;

class Position {
    private int x, y;

    public static Position fromString(String pos) {
        String[] split = pos.split(":");
        int x = Integer.parseInt(split[0]);
        int y = Integer.parseInt(split[1]);
        String direction = split[2];
        return new Position(x, y, direction);
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    private String direction;

    public Position(int x, int y, String direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
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

    public void incrementX() {
        x++;
    }

    public void decrementX() {
        x--;
    }

    public void incrementY() {
        y++;
    }

    public void decrementY() {
        y--;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (!(obj instanceof Position other))
            return false;
        return this.x == other.x && this.y == other.y && this.direction.equals(other.direction);
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }

}
