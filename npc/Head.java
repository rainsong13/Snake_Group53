package npc;

public class Head {

    private String movingDirection;

    public void moveHead(String direction) {
        final String LEFT = "left";
        final String RIGHT = "right";
        final String UP = "up";
        final String DOWN = "down";

        if (direction.equals(LEFT) || direction.equals(RIGHT)) {
            if (movingDirection.equals(UP)) {
                goUp();
            } else if (movingDirection.equals(DOWN)) {
                goDown();
            }
        }

        if (direction.equals(UP) || direction.equals(DOWN)) {
            if (movingDirection.equals(LEFT)) {
                goLeft();
            } else if (movingDirection.equals(RIGHT)) {
                goRight();
            }
        }
    }

    private void goUp() {
    }

    private void goDown() {
    }

    private void goLeft() {
    }

    private void goRight() {
    }
}
