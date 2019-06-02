package com.mihail.luhov.flappy.bird.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import static com.mihail.luhov.flappy.bird.FlappyBird.HEIGHT;
import static com.mihail.luhov.flappy.bird.FlappyBird.WIDTH;

public class Score {
    private BitmapFont font;
    private int points;
    public Score()
    {
        points = 0;
        font = new BitmapFont(Gdx.files.internal("flappyFont.fnt"));
        font.getData().setScale(0.3f);
    }

    public void increase()
    {
        points += 10;
    }

    public void render(SpriteBatch spriteBatch, OrthographicCamera camera)
    {
        font.draw(spriteBatch, Integer.toString(points),
                camera.position.x + camera.viewportWidth / 3,
                HEIGHT / 2 - 10);
    }

    public void dispose()
    {
        font.dispose();
    }

}
