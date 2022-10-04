package main;

import entity.Action;
import entity.Direction;
import lombok.Getter;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

@Getter
public class KeyHandler implements KeyListener {

    private Direction direction;
    private Action action;
    private boolean directionChanged;
    protected Direction lastDirection;
    public KeyHandler() {
        this.direction = Direction.NONE;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //int code = e.getKeyCode();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        switch (code) {
            case KeyEvent.VK_W -> direction = Direction.UP;
            case KeyEvent.VK_S -> direction = Direction.DOWN;
            case KeyEvent.VK_A -> direction = Direction.LEFT;
            case KeyEvent.VK_D -> direction = Direction.RIGHT;
            case KeyEvent.VK_E -> action = Action.INTERACT;
        }

        directionChanged = lastDirection != direction;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        lastDirection = direction;
        direction = Direction.NONE;
        action = Action.NONE;
    }

}
