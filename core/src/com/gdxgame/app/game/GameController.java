package com.gdxgame.app.game;

import com.badlogic.gdx.math.MathUtils;
import com.gdxgame.app.screen.ScreenManager;

public class GameController {
    private BackgroundGdx background;
    private BulletController bulletController;
    private RockController rockController;
    private Hero hero;

    public RockController getAsteroidController() {
        return rockController;
    }

    public BulletController getBulletController() {
        return bulletController;
    }

    public BackgroundGdx getBackground() {
        return background;
    }

    public Hero getHero() {
        return hero;
    }

    public GameController() {
        this.background = new BackgroundGdx(this);
        this.bulletController = new BulletController();
        this.rockController = new RockController(this);
        this.hero = new Hero(this);

        for (int i = 0; i < 3; i++) {
            rockController.setup(MathUtils.random(0, ScreenManager.SCREEN_WIDTH),
                    MathUtils.random(0, ScreenManager.SCREEN_HEIGHT),
                    MathUtils.random(-150, 150), MathUtils.random(-150, 150), 1.0f);
        }
    }

    public void update(float dt) {
        background.update(dt);
        bulletController.update(dt);
        rockController.update(dt);
        hero.update(dt);
        checkCollisions();
    }

    public void checkCollisions() {
        for (int i = 0; i < bulletController.getActiveList().size(); i++) {
            Bullet b = bulletController.getActiveList().get(i);
            for (int j = 0; j < rockController.getActiveList().size(); j++) {
                Rock a = rockController.getActiveList().get(j);
                if (a.getHitArea().contains(b.getPosition())) {
                    b.deactivate();
                    a.deactivate();
                    hero.addScore(a.getHpMax() * 100 );
                    break;
                }
            }
        }
    }
}
