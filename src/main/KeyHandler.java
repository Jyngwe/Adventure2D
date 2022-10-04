package main;

import entity.Action;
import entity.Direction;
import lombok.Getter;

import java.awt.event.*;

@Getter
public class KeyHandler implements KeyListener, MouseListener {

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
            case KeyEvent.VK_E -> action = Action.PICKUP;
        }

        directionChanged = lastDirection != direction;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        lastDirection = direction;
        direction = Direction.NONE;
        action = Action.NONE;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        action = Action.INTERACT;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        action = Action.NONE;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

}
