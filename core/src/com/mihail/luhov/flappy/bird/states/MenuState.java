package com.mihail.luhov.flappy.bird.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mihail.luhov.flappy.bird.FlappyBird;

public class MenuState extends State {
    private Texture background;
    private Texture playButton;
    private float playButtonX;
    private float playButtonY;
    public MenuState(GameStateManager gsm)
    {
        super(gsm);
        camera.setToOrtho(false, FlappyBird.WIDTH / 2,
                FlappyBird.HEIGHT / 2);
        background = new Texture("background.png");
        playButton = new Texture("playButton.png");
        playButtonX = camera.position.x - camera.viewportWidth / 2;
        playButtonY = camera.position.y;
    }
    @Override
    public void handleInput() {
        if (Gdx.input.justTouched())
        {
           // if (Gdx.input.getY() <= playButton.getHeight() + playButtonY &&
           //         Gdx.input.getX() <= playButton.getWidth() + playButtonX &&
           // Gdx.input.getY() >= playButtonY && Gdx.input.getX() >= playButtonX)
            {
                gsm.set(new PlayState(gsm));
            }

        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        spriteBatch.setProjectionMatrix(camera.combined);
        spriteBatch.begin();
        spriteBatch.draw(background, 0, 0);
        spriteBatch.draw(playButton, camera.position.x - playButton.getWidth() / 2,
                camera.position.y);
        spriteBatch.end();
    }

    @Override
    public void dispose()
    {
        background.dispose();
        playButton.dispose();
    }

}
