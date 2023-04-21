package org.example;

abstract class Direction {
    protected static final int MAX_GRID_WIDTH = 10;
    protected static final int MAX_GRID_HEIGHT = 10;
    private boolean isBlocked;

    protected Position position;

    public Direction(Position position) {
        this.position = position;
    }

    public void isBlocked(boolean blocked) {
        isBlocked = blocked;
    }

    public Position getPosition() {
        return position;
    }

    public static Direction of(String pos) {
        Position position = Position.fromString(pos);

        return switch (position.getDirection()) {
            default -> new NorthDirection(position);
            case "E" -> new EastDirection(position);
            case "S" -> new SouthDirection(position);
            case "W" -> new WestDirection(position);
        };
    }

    abstract Direction turnRight();

    abstract Direction turnLeft();

    abstract void move();

    public String getPositionAsText() {
        StringBuilder sb = new StringBuilder();
        if (isBlocked)
            sb.append("O:");

        sb.append(position.getX());
        sb.append(":");
        sb.append(position.getY());
        sb.append(":");
        sb.append(position.getDirection());

        return sb.toString();
    }

    String getStopPosition() {
        return getPositionAsText();
    }
}

class NorthDirection extends Direction {

    public NorthDirection(Position position) {
        super(position);
    }

    @Override
    public Direction turnRight() {
        position.setDirection("E");
        return new EastDirection(position);
    }

    @Override
    public Direction turnLeft() {
        position.setDirection("W");
        return new WestDirection(position);
    }

    @Override
    void move() {
        if (position.getY() == MAX_GRID_HEIGHT) position.setY(0);
        else position.incrementY();
    }
}

class SouthDirection extends Direction {


    public SouthDirection(Position position) {
        super(position);
    }

    @Override
    public Direction turnRight() {
        position.setDirection("W");
        return new WestDirection(position);
    }

    @Override
    public Direction turnLeft() {
        position.setDirection("E");
        return new EastDirection(position);
    }

    @Override
    void move() {
        if (position.getY() == 0) position.setY(MAX_GRID_HEIGHT);
        else position.decrementY();
    }
}

class EastDirection extends Direction {

    public EastDirection(Position position) {
        super(position);
    }

    @Override
    public Direction turnRight() {
        position.setDirection("S");
        return new SouthDirection(position);
    }

    @Override
    public Direction turnLeft() {
        position.setDirection("N");
        return new NorthDirection(position);
    }

    @Override
    void move() {
        if (position.getX() == MAX_GRID_WIDTH) position.setX(0);
        else position.incrementX();
    }
}

class WestDirection extends Direction {

    public WestDirection(Position position) {
        super(position);
    }

    @Override
    public Direction turnRight() {
        position.setDirection("N");
        return new NorthDirection(position);
    }

    @Override
    public Direction turnLeft() {
        position.setDirection("S");
        return new SouthDirection(position);
    }

    @Override
    void move() {
        if (position.getX() == 0) position.setX(MAX_GRID_WIDTH);
        else position.decrementX();
    }
}