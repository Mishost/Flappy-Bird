package com.mihail.luhov.flappy.bird.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.Array;
import com.mihail.luhov.flappy.bird.FlappyBird;
import com.mihail.luhov.flappy.bird.sprites.Bird;
import com.mihail.luhov.flappy.bird.sprites.Tube;

public class PlayState extends State {
    private static final int TUBE_SPACING = 125;
    private static final int TUBE_COUNT = 4;

    private Bird bird;
    private Texture background;
    private Array<Tube> tubes;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        bird = new Bird(50, 300);
        background = new Texture("background.png");

        tubes = new Array<Tube>();
        for (int i = 0; i < TUBE_COUNT; ++i)
            tubes.add(new Tube(i * (TUBE_SPACING + Tube.TUBE_WIDTH)));

        camera.setToOrtho(false, FlappyBird.WIDTH / 2,
                FlappyBird.HEIGHT / 2);
    }

    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched())
            bird.jump();
    }

    @Override
    public void update(float dt) {
        handleInput();
        bird.update(dt);

        camera.position.x = bird.getPosition().x + 80;
        for (Tube tube : tubes)
        {
            if (tube.getPosTopTube().x + tube.getTopTube().getWidth() <
                    camera.position.x - (camera.viewportWidth / 2))
                tube.reposition(tube.getPosTopTube().x +
                        (Tube.TUBE_WIDTH + TUBE_SPACING) * TUBE_COUNT);
        }
        camera.update();
    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        spriteBatch.setProjectionMatrix(camera.combined);
        spriteBatch.begin();
        spriteBatch.draw(background, camera.position.x - (camera.viewportWidth / 2), 0);
        spriteBatch.draw(bird.getTexture(), bird.getPosition().x, bird.getPosition().y);

        for (Tube tube : tubes)
        {
            spriteBatch.draw(tube.getTopTube(), tube.getPosTopTube().x, tube.getPosTopTube().y);
            spriteBatch.draw(tube.getBottomTube(), tube.getPosBottomTube().x,
                    tube.getPosBottomTube().y);
        }

        spriteBatch.end();
    }

    @Override
    public void dispose() {

    }
}
