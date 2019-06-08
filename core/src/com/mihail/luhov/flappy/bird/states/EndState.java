package com.mihail.luhov.flappy.bird.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mihail.luhov.flappy.bird.FlappyBird;

public class EndState extends State {
    private BitmapFont font;
    private Texture background;

    public EndState(GameStateManager gsm)
    {
        super(gsm);
        UIcamera.setToOrtho(false, FlappyBird.WIDTH / 2,
                FlappyBird.HEIGHT / 2);
        font = new BitmapFont(Gdx.files.internal("flappyFont.fnt"));
        font.getData().setScale(0.85f);
        background = new Texture("background.png");
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched())
            gsm.set(new MenuState(gsm));
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
        font.getData().setScale(0.85f);
        font.draw(spriteBatch, "Game over!",
                FlappyBird.WIDTH / 10 - 40, FlappyBird.HEIGHT / 3 + 30);
        font.getData().setScale(0.5f);
        font.draw(spriteBatch, "Highscore: " + Integer.toString(FlappyBird.highscore),
                FlappyBird.WIDTH / 10 - 15, FlappyBird.HEIGHT / 10 - 20);
        spriteBatch.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        font.dispose();
    }
}
