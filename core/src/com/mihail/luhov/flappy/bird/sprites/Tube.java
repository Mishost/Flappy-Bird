package com.mihail.luhov.flappy.bird.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class Tube {
    public static final int TUBE_WIDTH = 52; //how wide is the tube image
    private static final int FLUCTUATION = 170; // + LOWEST_OPENING = maximum place a bottom tube can be
    private static final int TUBE_GAP = 100; //the space between each 2 top and bottom tubes
    private static final int LOWEST_OPENING = 50; //the lowest place a bottom tube can be

    private Texture topTube, bottomTube;
    private Vector2 posTopTube, posBottomTube;
    private Random rand;

    private Rectangle boundsTop, boundsBottom;
    public Tube(float x)
    {
        topTube = new Texture("toptube.png");
        bottomTube = new Texture("bottomtube.png");
        rand = new Random();

        //posTopTube = new Vector2(x, rand.nextInt(FLUCTUATION) + TUBE_GAP + LOWEST_OPENING);
        //posBottomTube = new Vector2(x, posTopTube.y - TUBE_GAP - bottomTube.getHeight());

        posBottomTube = new Vector2(x, LOWEST_OPENING +
                rand.nextInt(FLUCTUATION) - bottomTube.getHeight());
        //subtracting the height of the bottom tube, because we want
        //the top of the tube to be at that spot, not the bottom left point of the image
        posTopTube = new Vector2(x, posBottomTube.y + TUBE_GAP + topTube.getHeight());
        //adding the height because here the bottom left is what we want to draw

        boundsTop = new Rectangle(posTopTube.x, posTopTube.y, topTube.getWidth(), topTube.getHeight());
        boundsBottom = new Rectangle(posBottomTube.x, posBottomTube.y,
                bottomTube.getWidth(), bottomTube.getHeight());
    }

    public void reposition(float x)
    {
        posBottomTube.set(x, LOWEST_OPENING +
                rand.nextInt(FLUCTUATION) - bottomTube.getHeight());
        posTopTube.set(x, posBottomTube.y + TUBE_GAP + topTube.getHeight());

        boundsTop.setPosition(posTopTube);
        boundsBottom.setPosition(posBottomTube);
    }

    public boolean collide(Rectangle player)
    {
        return player.overlaps(boundsTop) || player.overlaps(boundsBottom);
    }

    public Texture getTopTube() {
        return topTube;
    }

    public Texture getBottomTube() {
        return bottomTube;
    }

    public Vector2 getPosTopTube() {
        return posTopTube;
    }

    public Vector2 getPosBottomTube() {
        return posBottomTube;
    }
}
