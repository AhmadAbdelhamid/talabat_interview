package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class RoverTest {

    private static final Obstacles EMPTY_OBSTACLES = new Obstacles();

    @Test
    void position_is_equal() {
        Position position = new Position(2, 2, "N");
        Position position2 = new Position(2, 2, "N");
        assertEquals(position, position2);
    }

    static Stream<Arguments> allRoverMoves() {

        return Stream.of(
                Arguments.of("0:0:N", "", "0:0:N", EMPTY_OBSTACLES),
                Arguments.of("0:0:N", "R", "0:0:E", EMPTY_OBSTACLES),
                Arguments.of("0:0:E", "R", "0:0:S", EMPTY_OBSTACLES),
                Arguments.of("0:0:S", "R", "0:0:W", EMPTY_OBSTACLES),
                Arguments.of("0:0:W", "R", "0:0:N", EMPTY_OBSTACLES),
                Arguments.of("0:0:N", "L", "0:0:W", EMPTY_OBSTACLES),
                Arguments.of("0:0:W", "L", "0:0:S", EMPTY_OBSTACLES),
                Arguments.of("0:0:S", "L", "0:0:E", EMPTY_OBSTACLES),
                Arguments.of("0:0:E", "L", "0:0:N", EMPTY_OBSTACLES),
                Arguments.of("10:2:E", "M", "0:2:E", EMPTY_OBSTACLES),
                Arguments.of("2:3:E", "M", "3:3:E", EMPTY_OBSTACLES),
                Arguments.of("2:10:N", "M", "2:0:N", EMPTY_OBSTACLES),
                Arguments.of("0:0:N", "RMMLML", "2:1:W", EMPTY_OBSTACLES),
                Arguments.of("0:0:N", "RMMLMMM", "O:2:2:N", withObstacleAt(2, 2, "N")),
                Arguments.of("0:0:N", "RMMLMMMMMM", "O:2:5:N", withObstacleAt(2, 5, "N"))
        );
    }


    @ParameterizedTest
    @MethodSource("allRoverMoves")
    void checkAllRoverMoves(String position, String command, String expected, Obstacles obstacles) {
        Rover rover = new Rover(position, obstacles);
        //act
        String roverPos = rover.getDirectionFor(command);
        //assert
        assertEquals(expected, roverPos);
    }


    //Helper---------------------------------------------------------------------------------------------

    private static Obstacles withObstacleAt(int x, int y, String direction) {
        Obstacles obstacles = new Obstacles();
        obstacles.addObstacle(x, y, direction);
        return obstacles;
    }


}