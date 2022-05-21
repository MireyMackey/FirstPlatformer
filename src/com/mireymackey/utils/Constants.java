package com.mireymackey.utils;

import com.mireymackey.main.Game;

public class Constants {
    public static class Directions{
        public static final int LEFT = 0;
        public static final int UP = 1;
        public static final int RIGHT = 2;
        public static final int DOWN = 3;
    }

    public static class PlayerAction {
        public static final int GOING_DOWN = 0;
        public static final int GOING_UP = 1;
        public static final int GROUND_HIT = 2;
        public static final int IDLE = 3;
        public static final int RUNNING = 4;
        public static final int STOP_RUNNING = 5;

        public static int getPlayerFrameAmount(int playerAction){
            switch (playerAction){
                case RUNNING -> {return 10;}
                case GOING_DOWN, GOING_UP -> {return 6;}
                case GROUND_HIT -> {return 4;}
                case STOP_RUNNING -> {return 3;}
                case IDLE -> {return 1;}
                default -> {return 1;}
            }
        }
    }

    public static class LevelResources{
        public static final String LEVEL_TILES = "res/leveTiles/level_tilemap.png";
        public static final String LEVEL_ONE = "res/level_one_data/level_one_data2.png";
    }

    public static class EntityConstants{
        public static final int PLAYER = 0;
        public static final int PORTAL = 1;

        public static int getEntityFrameAmount(int entityType){
            switch (entityType){
                case PORTAL -> {return 9;}
                case PLAYER -> {return 10;}
                default -> {return 1;}
            }
        }

        public static int getEntityAnimationsAmount(int entityType){
            switch (entityType){
                case PORTAL -> {return 1;}
                case PLAYER -> {return 6;}
                default -> {return 1;}
            }
        }

        public static String getEntitySpritePath(int entityType){
            switch (entityType){
                case PORTAL -> {return "res/portal";}
                case PLAYER -> {return "res/player";}
                default -> {return "";}
            }
        }

        public static int getEntitySpriteSize(int entityType){
            switch (entityType){
                case PORTAL -> {return 16;}
                case PLAYER -> {return 16;}
                default -> {return 16;}
            }
        }

        public static class PortalConstants{
            public static final int PORTAL_WIDTH_DEFAULT = 16;
            public static final int PORTAL_HEIGHT_DEFAULT = 16;
            public static final int PORTAL_WIDTH = (int) (PORTAL_WIDTH_DEFAULT * Game.getScale());
            public static final int PORTAL_HEIGHT = (int) (PORTAL_HEIGHT_DEFAULT * Game.getScale());

            public static final int PORTAL_DRAW_OFFSET_X = (int) (4 * Game.getScale());
            public static final int PORTAL_DRAW_OFFSET_Y = (int) (0 * Game.getScale());
        }
    }
}
