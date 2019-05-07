package com.mihail.luhov.flappy.bird.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.mihail.luhov.flappy.bird.FlappyBird;
import com.mihail.luhov.flappy.bird.sprites.Bird;
import com.mihail.luhov.flappy.bird.sprites.Tube;

public class PlayState extends State {
    private static final int TUBE_SPACING = 125;
    private static final int TUBE_COUNT = 4;
    private static final int GROUND_OFFSET = -80;

    private Bird bird;
    private Texture background;
    private Array<Tube> tubes;
    private Texture ground;
    private Vector2 groundPos1, groundPos2;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        bird = new Bird(50, 300);
        background = new Texture("background.png");
        ground = new Texture("ground.png");
        groundPos1 = new Vector2(camera.position.x  - camera.viewportWidth / 2, GROUND_OFFSET);
        groundPos2 = new Vector2(groundPos1.x + ground.getWidth(), GROUND_OFFSET);
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
        updateGround();
        bird.update(dt);

        camera.position.x = bird.getPosition().x + 80;

        if (bird.getPosition().y <= ground.getHeight() + GROUND_OFFSET)
            gsm.set(new EndState(gsm));
        for (Tube tube : tubes)
        {
            if (tube.getPosTopTube().x + tube.getTopTube().getWidth() <
                    camera.position.x - (camera.viewportWidth / 2))
            {
                tube.reposition(tube.getPosTopTube().x +
                        (Tube.TUBE_WIDTH + TUBE_SPACING) * TUBE_COUNT);
            }

            if (tube.collide(bird.getBounds()))
            {
                gsm.set(new EndState(gsm));
                break;
            }
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
        spriteBatch.draw(ground, groundPos1.x, groundPos1.y);
        spriteBatch.draw(ground, groundPos2.x, groundPos2.y);
        spriteBatch.end();
    }

    @Override
    public void dispose() {
        bird.dispose();
        background.dispose();

        for (Tube tube : tubes)
            tube.dispose();
        ground.dispose();
    }

    public void updateGround()
    {
        if (camera.position.x - camera.viewportWidth / 2 > groundPos1.x + ground.getWidth())
            groundPos1.add(ground.getWidth() * 2, 0);
        if (camera.position.x - camera.viewportWidth / 2 > groundPos2.x + ground.getWidth())
            groundPos2.add(ground.getWidth() * 2, 0);
    }
}
