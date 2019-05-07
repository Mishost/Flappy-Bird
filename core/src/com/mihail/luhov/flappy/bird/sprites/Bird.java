package com.mihail.luhov.flappy.bird.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class Bird {
    private static final int GRAVITY = -15;
    //only here because only the bird is affected by gravity
    private static final int MOVEMENT = 100;
    private Vector3 position;
    private Vector3 velocity;

    private Animation birdAnimation;
    private Rectangle bounds;
    private Texture bird;
    private Sound ding;

    public Bird(int x, int y)
    {
        position = new Vector3(x, y, 0);
        velocity = new Vector3(0, 0, 0);
        bird = new Texture("birdanimation.png");
        birdAnimation = new Animation(new TextureRegion(bird), 3, 0.5f);
        bounds = new Rectangle(x, y, bird.getWidth()  / 3, bird.getHeight());
        ding = Gdx.audio.newSound(Gdx.files.internal("ding.mp3"));
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void playSound()
    {
        ding.play(0.5f);
    }

    public void update(float dt)
    {
        birdAnimation.update(dt);
        if (position.y > 0) //we should not add gravity if we have fallen
            velocity.add(0, GRAVITY, 0);
        velocity.scl(dt);
        position.add(MOVEMENT * dt, velocity.y, 0);

        if (position.y < 0)
            position.y = 0;
        velocity.scl(1 / dt);
        bounds.setPosition(position.x, position.y);
    }

    public Vector3 getPosition()
    {
        return position;
    }

    public TextureRegion getTexture()
    {
        return birdAnimation.getFrame();
    }

    public void jump()
    {
        velocity.y = 250;
    }

    public void dispose()
    {
        bird.dispose();
        ding.dispose();
    }

}
