/*
 * File: Breakout.java
 * -------------------
 * Name:
 * Section Leader:
 * 
 * This file will eventually implement the game of Breakout.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;
import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class Breakout extends GraphicsProgram {

/** Width and height of application window in pixels */
	public static final int APPLICATION_WIDTH = 400;
	public static final int APPLICATION_HEIGHT = 600;

/** Dimensions of game board (usually the same) */
	private static final int WIDTH = APPLICATION_WIDTH;
	private static final int HEIGHT = APPLICATION_HEIGHT;

/** Dimensions of the paddle */
	private static final int PADDLE_WIDTH = 60;
	private static final int PADDLE_HEIGHT = 10;

/** Offset of the paddle up from the bottom */
	private static final int PADDLE_Y_OFFSET = 30;

/** Number of bricks per row */
	private static final int NBRICKS_PER_ROW = 10;

/** Number of rows of bricks */
	private static final int NBRICK_ROWS = 10;

/** Separation between bricks */
	private static final int BRICK_SEP = 4;

/** Width of a brick */
	private static final int BRICK_WIDTH =
	  (WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;

/** Height of a brick */
	private static final int BRICK_HEIGHT = 8;
/** Bricks left on screen */
	private int BRICKS_LEFT = 100;

/** Radius of the ball in pixels */
	private static final int BALL_RADIUS = 10;
	private static final int BALL_DIAMETER = BALL_RADIUS * 2;
/** Offset of the top brick row from the top */
	private static final int BRICK_Y_OFFSET = 70;
/** Mouse x */
	public double mouseX;
/** Number of turns */
	private static final int NTURNS = 3;
	///This is the paddle.
	GRect paddle;
/** This is the ball for the game */
	GOval Ball;
/** random Generator*/
	private RandomGenerator rgen;
/** Ball velocities */
	private double vx;
	private double vy;

/** Runs the Breakout program. */
	public void run() {
		addMouseListeners();
		makeBricks();
		rgen = RandomGenerator.getInstance();
		paddle = new GRect(WIDTH / 2, HEIGHT - PADDLE_Y_OFFSET, PADDLE_WIDTH, PADDLE_HEIGHT);
		paddle.setFilled(true);
		Ball = new GOval(WIDTH / 2 - BALL_RADIUS, HEIGHT / 2 - BALL_RADIUS, BALL_DIAMETER, BALL_DIAMETER);
		Ball.setFilled(true);
		add(paddle);
		add(Ball);
		update();
		waitForClick();
		vy = 3.0;
		vx = rgen.nextDouble(1.0, 3.0);
		if (rgen.nextBoolean(0.5)) {
			vx = -vx;
		}
		updateBall();
	}
	
	private void makeBricks() {
		for(int a = 0; a < NBRICK_ROWS; a++) {
			for(int i = 0; i < NBRICKS_PER_ROW; i++) {
				GRect brick = new GRect(BRICK_SEP - 2 + (i * (BRICK_WIDTH + BRICK_SEP)), BRICK_SEP + BRICK_Y_OFFSET + (a * (BRICK_HEIGHT + BRICK_SEP)), BRICK_WIDTH, BRICK_HEIGHT);
				brick.setFilled(true);

				if(a < 2) {
					brick.setFillColor(Color.RED);
					brick.setColor(Color.RED);
				}
				else if(a < 4) {
					brick.setFillColor(Color.ORANGE);
					brick.setColor(Color.ORANGE);
				}
				else if(a < 6) {
					brick.setFillColor(Color.YELLOW);
					brick.setColor(Color.YELLOW);
				}
				else if(a < 8) {
					brick.setFillColor(Color.GREEN);
					brick.setColor(Color.GREEN);
				}
				else if(a < 10) {
					brick.setFillColor(Color.CYAN);
					brick.setColor(Color.CYAN);
				}
				add(brick);
			}
			}
	}
	
	public void mouseMoved(MouseEvent e) {
		mouseX = e.getX();
		update();
	}
	
	public void update() {
		paddle.setLocation(mouseX - (PADDLE_WIDTH / 2), HEIGHT - PADDLE_Y_OFFSET);
		if((paddle.getX() + paddle.getWidth()) > WIDTH) {
			paddle.setLocation(WIDTH - paddle.getWidth(), HEIGHT - PADDLE_Y_OFFSET);
		}
		else if(paddle.getX() <= 0) {
			paddle.setLocation(0, HEIGHT - PADDLE_Y_OFFSET);
		}
	}
	private void reflectOffSides() {
		vx = -vx;
	}
	
	private void updateBall() {
		Ball.move(vx, vy);
		while(BRICKS_LEFT > 0) {
			if(Ball.getX() <= 0) {
				reflectOffSides();
			}
			if(Ball.getX() + BALL_DIAMETER >= WIDTH) {
				reflectOffSides();
			}
			Ball.move(vx, vy);
			pause(3);
		}
	}

}
