package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RoverTest {


    private Rover rover;

    @BeforeEach
    void setUp() {
        rover = new Rover();
    }

    private static final int[][] EMPTY_OBSTACLES = new int[][]{};

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
                Arguments.of("0:0:N", "RMMLMMM", "O:2:2:N", withObstacleAt(2, 2)),
                Arguments.of("0:0:N", "RMMLMMMMMM", "O:2:5:N", withObstacleAt(2, 5))
        );
    }


    @ParameterizedTest
    @MethodSource("allRoverMoves")
    void checkAllRoverMoves(String startPosition, String command, String expected, int[][] obstacles) {
        rover.updatePosition(startPosition);
        rover.setObstacles(obstacles);
        rover.command(command);
        //act
        String roverPos = rover.getPositionAsText();
        //assert
        assertEquals(expected, roverPos);
    }

    private static int[][] withObstacleAt(int x, int y) {
        return new int[][]{{x, y}};
    }
}