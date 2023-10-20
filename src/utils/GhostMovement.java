package utils;

import Models.Cords;
import Models.Direction;

import java.util.*;

public class GhostMovement {

    public static Direction getNextMove(Cords pacman, ArrayList<Cords> walls, Cords ghost) {

        // Get the direction of the ghost to Pacman.
        Direction ghostDirection = getDirection(ghost, pacman);

        // Check if the ghost is next to a wall.
        if (isWall(walls, ghost)) {

            // If the ghost is next to a wall, opposite direction.
            Direction oppositeDirection = getOppositeDirection(ghostDirection);

            // Get the direction of the wall the ghost is next to.
            Direction wallDirection = getDirection(ghost, walls.get(getWallIndex(ghost, walls)));

            // If the ghost can move in the opposite direction of the wall, then do so.
            assert oppositeDirection != null;
            if (canMove(ghost, oppositeDirection, walls)) {
                return oppositeDirection;
            }

            // Otherwise, the ghost can only move in the direction of the wall.
            return wallDirection;
        }

        // Otherwise, the ghost can move in any direction.
        return ghostDirection;
    }

    private static Direction getDirection(Cords a, Cords b) {

        // Calculate the angle between the two vectors.
        double angle = Math.atan2(b.y - a.y, b.x - a.x);

        // Convert the angle to a direction.
        return Direction.values()[(int) (angle / (2 * Math.PI) * 4)];
    }

    private static boolean isWall(ArrayList<Cords> walls, Cords point) {

        // Iterate over all the walls.
        for (Cords wall : walls) {

            // If the point is inside the wall, return true.
            if (point.x >= wall.x && point.x <= wall.x + 1 && point.y >= wall.y && point.y <= wall.y + 1) {
                return true;
            }
        }

        // Otherwise, return false.
        return false;
    }

    private static Direction getOppositeDirection(Direction direction) {

        switch (direction) {
            case UP:
                return Direction.DOWN;
            case DOWN:
                return Direction.UP;
            case LEFT:
                return Direction.RIGHT;
            case RIGHT:
                return Direction.LEFT;
            default:
                return null;
        }
    }

    private static int getWallIndex(Cords point, ArrayList<Cords> walls) {

        // Iterate over all the walls.
        for (int i = 0; i < walls.size(); i++) {

            // If the point is inside the wall, return the index.
            if (point.x >= walls.get(i).x && point.x <= walls.get(i).x + 1 && point.y >= walls.get(i).y && point.y <= walls.get(i).y + 1) {
                return i;
            }
        }

        // Otherwise, return -1.
        return -1;
    }

    private static boolean canMove(Cords ghost, Direction direction, ArrayList<Cords> walls) {

        // Get the new position of the ghost.
        Cords newPosition = ghost.add(direction.asCords());

        // Check if the new position is inside a wall.
        return !isWall(walls, newPosition);
    }

}
