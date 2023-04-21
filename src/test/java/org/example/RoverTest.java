package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RoverTest {

    private Rover rover;

    @BeforeEach
    void setUp() {
        rover = new Rover();
    }

    @Test
    void rover_initial_position_should_return_0_0_N() {
        //act
        String position = rover.getPositionAsText();
        //assert
        assertEquals("0:0:N", position);
    }

    @Test
    void rover_parse_position_from_string() {
        //arrange
        Position pos = new Position("2:3:E");
        rover.setPosition(pos);
        //act
        String position = rover.getPositionAsText();
        //assert
        assertEquals("2:3:E", position);
    }

    @Test
    void rover_at_00N_moves_one_step_should_return_01N() {
        //act
        rover.move("M");
        String position = rover.getPositionAsText();
        //assert
        assertEquals("0:1:N", position);
    }

    @Test
    void rover_at_23E_moves_one_step_should_return_33E() {
        //arrange
        Position pos = new Position("2:3:E");
        rover.setPosition(pos);
        //act
        rover.move("M");
        String position = rover.getPositionAsText();
        //assert
        assertEquals("3:3:E", position);
    }

    @Test
    void rover_at_22W_moves_one_step_should_return_12W() {
        //arrange
        Position pos = new Position("2:2:W");
        rover.setPosition(pos);
        //act
        rover.move("M");
        String position = rover.getPositionAsText();
        //assert
        assertEquals("1:2:W", position);
    }

    @Test
    void rover_at_22S_moves_one_step_should_return_21S() {
        //arrange
        Position pos = new Position("2:2:S");
        rover.setPosition(pos);
        //act
        rover.move("M");
        String position = rover.getPositionAsText();
        //assert
        assertEquals("2:1:S", position);
    }

    @Test
    void rover_at_102E_moves_one_step_should_return_02E() {
        //arrange
        Position pos = new Position("10:2:E");
        rover.setPosition(pos);
        //act
        rover.move("M");
        String position = rover.getPositionAsText();
        //assert
        assertEquals("0:2:E", position);
    }

    @Test
    void rover_at_010N_moves_one_step_should_return_00N() {
        //arrange
        Position pos = new Position("0:10:N");
        rover.setPosition(pos);
        //act
        rover.move("M");
        String position = rover.getPositionAsText();
        //assert
        assertEquals("0:0:N", position);
    }

    @Test
    void rover_at_102E_turnLeft_twice_then_move_one_step_should_return_92W() {
        //arrange
        Position pos = new Position("10:2:E");
        rover.setPosition(pos);
        //act
        rover.move("L");
        rover.move("L");
        rover.move("M");
        String position = rover.getPositionAsText();
        //assert
        assertEquals("9:2:W", position);
    }

    @Test
    void rover_at_00N_RMMLML_should_return_21W() {
        //arrange
        Position pos = new Position("0:0:N");
        rover.setPosition(pos);
        //act
        rover.move("RMMLML");
        String position = rover.getPositionAsText();
        //assert
        assertEquals("2:1:W", position);
    }

    @Test
    void rover_at_22N_should_return_12W_when_turnLeft_then_move() {
        //arrange
        Position pos = new Position("2:2:N");
        rover.setPosition(pos);
        //act
        rover.move("L");
        rover.move("M");
        String position = rover.getPositionAsText();
        //assert
        assertEquals("1:2:W", position);
    }

    @Nested
    class TurnRightTest {

        //Testing Turn-Right
        //.01- n -(l)-> e.
        //.02- e -(l)-> s.
        //.03- s -(l)-> w.
        //.04- w -(l)-> n.

        @Test
        void rover_with_N_direction_should_return_E_when_turn_Right() {
            //act
            rover.move("R");
            String direction = rover.getPositionAsText();
            //assert
            assertEquals("0:0:E", direction);
        }

        @Test
        void rover_with_E_direction_should_return_S_when_turn_Right() {
            //arrange
            rover.setPosition(new Position("0:0:E"));
            //act
            rover.move("R");
            String direction = rover.getPositionAsText();
            //assert
            assertEquals("0:0:S", direction);
        }

        @Test
        void rover_with_S_direction_should_return_W_when_turn_Right() {
            //arrange
            rover.setPosition(new Position("0:0:S"));
            //act
            rover.move("R");
            String direction = rover.getPositionAsText();
            //assert
            assertEquals("0:0:W", direction);
        }

        @Test
        void rover_with_W_direction_should_return_N_when_turn_Right() {
            //arrange
            rover.setPosition(new Position("0:0:W"));
            //act
            rover.move("R");
            String direction = rover.getPositionAsText();
            //assert
            assertEquals("0:0:N", direction);
        }
    }

    @Nested
    class TurnLeftTest {
        //Testing Turn-Left
        //.01- n -(l)-> w.
        //.02- w -(l)-> s.
        //.03- s -(l)-> e.
        //.04- e -(l)-> n.

        @Test
        void rover_with_N_direction_should_return_W_when_turn_left() {
            //act
            rover.move("L");
            String direction = rover.getPositionAsText();
            //assert
            assertEquals("0:0:W", direction);
        }

        @Test
        void rover_with_W_direction_should_return_S_when_turn_left() {
            //arrange
            rover.setPosition(new Position("0:0:W"));
            //act
            rover.move("L");
            String direction = rover.getPositionAsText();
            //assert
            assertEquals("0:0:S", direction);
        }

        @Test
        void rover_with_S_direction_should_return_E_when_turn_left() {
            //arrange
            rover.setPosition(new Position("0:0:S"));
            //act
            rover.move("L");
            String direction = rover.getPositionAsText();
            //assert
            assertEquals("0:0:E", direction);
        }

        @Test
        void rover_with_E_direction_should_return_N_when_turn_left() {
            //arrange
            rover.setPosition(new Position("0:0:E"));
            //act
            rover.move("L");
            String direction = rover.getPositionAsText();
            //assert
            assertEquals("0:0:N", direction);
        }
    }

    @Nested
    class MoveCommandTest {
        @Test
        void rover_Y_position_incremented_when_moves_one_step_in_N_direction() {
            //act
            rover.move("M");
            String position = rover.getPositionAsText();
            //assert
            assertEquals("0:1:N", position);
        }

        @Test
        void rover_X_position_incremented_when_moves_one_step_in_E_direction() {
            //act
            rover.setPosition(new Position("0:0:E"));
            rover.move("M");
            String position = rover.getPositionAsText();
            //assert
            assertEquals("1:0:E", position);
        }

        @Test
        void rover_Y_position_decremented_when_moves_one_step_in_S_direction() {
            //act
            rover.setPosition(new Position("0:0:S"));
            rover.move("M");
            String position = rover.getPositionAsText();
            //assert
            assertEquals("0:10:S", position);
        }

        @Test
        void rover_X_position_decremented_when_moves_one_step_in_W_direction() {
            //act
            rover.setPosition(new Position("0:0:W"));
            rover.move("M");
            String position = rover.getPositionAsText();
            //assert
            assertEquals("10:0:W", position);
        }
    }

    @Nested
    class CommandsTest {
        @Test
        void rover_turn_right_when_send_R_command() {
            //arrange
            rover.move("R");
            //act
            String position = rover.getPositionAsText();
            //assert
            assertEquals("0:0:E", position);
        }

        @Test
        void rover_turn_left_when_send_L_command() {
            //arrange
            rover.move("L");
            //act
            String position = rover.getPositionAsText();
            //assert
            assertEquals("0:0:W", position);
        }
    }

    @Nested
    class ObstaclesTest {

        @Test
        void rover_at_00N_with_cmd_RMMLMMML_should_report_Obstacle_at_O22N() {
            //arrange
            Position position = new Position("0:0:N");
            int[][] obstacles = new int[][]{{2, 5}};
            rover.setObstacles(obstacles);
            rover.setPosition(position);
            rover.move("RMMLMMMMMM");
            //act
            String roverPos = rover.getPositionAsText();
            //assert
            assertEquals("O:2:5:N", roverPos);
        }
    }
}