package com.mihail.luhov.flappy.bird.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mihail.luhov.flappy.bird.FlappyBird;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class MenuState extends State {
    private Texture background;
    private Texture playButton;
    private BitmapFont font;
    private float playButtonX;
    private float playButtonY;
    public MenuState(GameStateManager gsm)
    {
        super(gsm);
        UIcamera.setToOrtho(false, FlappyBird.WIDTH / 2,
                FlappyBird.HEIGHT / 2);
        background = new Texture("background.png");
        playButton = new Texture("playButton.png");
        playButtonX = UIcamera.position.x - UIcamera.viewportWidth / 2;
        playButtonY = UIcamera.position.y;
        font = new BitmapFont(Gdx.files.internal("flappyFont.fnt"));
        font.getData().setScale(0.5f);
    }
    @Override
    public void handleInput() {
        if (Gdx.input.justTouched())
        {
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
        spriteBatch.setProjectionMatrix(UIcamera.combined);
        spriteBatch.begin();
        spriteBatch.draw(background, 0, 0);
        spriteBatch.draw(playButton, UIcamera.position.x - playButton.getWidth() / 2,
                UIcamera.position.y);
        font.draw(spriteBatch, "Highscore: " + Integer.toString(FlappyBird.highscore),
                FlappyBird.WIDTH / 10 - 15,
                FlappyBird.HEIGHT / 10 + 20);
        spriteBatch.end();
    }

    @Override
    public void dispose()
    {
        background.dispose();
        playButton.dispose();
        font.dispose();
    }

}
