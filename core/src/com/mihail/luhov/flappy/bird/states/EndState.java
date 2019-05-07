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
        camera.setToOrtho(false, FlappyBird.WIDTH / 2,
                FlappyBird.HEIGHT / 2);
        font = new BitmapFont(Gdx.files.internal("flappyFont.fnt"));
        font.getData().setScale(0.92f);
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
        spriteBatch.setProjectionMatrix(camera.combined);
        spriteBatch.begin();
        spriteBatch.draw(background, 0, 0);
        font.draw(spriteBatch, "Game over!",
                camera.position.x - camera.viewportWidth / 2, 270);
        spriteBatch.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        font.dispose();
    }
}
