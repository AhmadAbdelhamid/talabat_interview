# Task:

You are given the initial starting point of a rover “0:0:N”.
0,0 are X and Y coordinates on a grid of (10, 10).
‘N’ is the direction rover is facing, alias for North.
Directions it can face are N, S, E, W

Sending ‘L’ and ‘R’ commands to rover makes it rotate left and right

‘M‘ command moves the rover one step in the direction it’s currently facing. E.g. If its position is “2:3:E”, sending ‘M’ to the rover moves it to “3:3:E”.

The rover receives a string of commands e.g. "RMMLML" and returns the end point after the moves. For example, if its starting position is “0:0:N”, sending "RMMLML" to the rover will move it to "2:1:W".

The rover wraps around if it reaches the end of the grid (imagine the grid as a globe). E.g. if the grid is a square from (0,0) to (10,10), and if the rover is at a position “10:2:E”, moving it once more will make it wrap around the grid and go to “0:2:E”.

The grid may have obstacles. If a given sequence of commands encounters an obstacle, the rover moves up to the last possible point and reports the obstacle e.g. "O:2:2:N" (O stands for Obstacle).

For us, it doesn't matter that you build the whole solution during the session, but the way that you go about it and your workflow.

We value test-driving (using TDD) the solution and growing it in very small, incremental steps while explaining your intentions and design decisions to the interviewer.
