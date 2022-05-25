package com.mireymackey.entities;

import static com.mireymackey.utils.Constants.EntityConstants.*;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public abstract class Entity {

    protected float x, y;
    protected int width, height;
    protected Rectangle2D.Float hitbox;
    public int entityType;

    public Entity(float x, float y, int width, int height, int entityType){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.entityType = entityType;

        initHitbox(x, y, getEntityHitboxWidth(entityType), getEntityHitboxHeight(entityType));
    }

    protected void drawHitbox(Graphics g){
        //for debugging
        g.setColor(Color.RED);
        g.drawRect((int) hitbox.x,(int) hitbox.y,(int) hitbox.width,(int) hitbox.height);
    }

     protected void initHitbox(float x, float y, float width, float height){
        hitbox = new Rectangle2D.Float(x, y, width, height);
    }

    public Rectangle2D.Float getHitbox() {
        return hitbox;
    }
}
