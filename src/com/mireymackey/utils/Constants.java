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
        public static final String SOUL_LEVEL_TILES = "res/leveTiles/soul_level_tilemap.png";

        public static final int LEVELS_AMOUNT = 3;

        public static String getLevelPath (int levelNum){
            return "res/level_data/level" + levelNum + ".png";
        }
    }

    public static class UIConstants{
        public static class Buttons{
            public static final int BUTTON_DEFAULT_WIDTH = 160;
            public static final int BUTTON_DEFAULT_HEIGHT = 96;
            public static final int BUTTON_BOUND_DEFAULT_WIDTH = 160;
            public static final int BUTTON_BOUND_DEFAULT_HEIGHT = 64;
            public static final int BUTTON_WIDTH = (int)(BUTTON_DEFAULT_WIDTH * Game.getScale() / 1.5);
            public static final int BUTTON_HEIGHT = (int)(BUTTON_DEFAULT_HEIGHT * Game.getScale() / 1.5);
            public static final int BUTTON_BOUND_WIDTH = (int)(BUTTON_BOUND_DEFAULT_WIDTH * Game.getScale() / 1.5);
            public static final int BUTTON_BOUND_HEIGHT = (int)(BUTTON_BOUND_DEFAULT_HEIGHT * Game.getScale() / 1.5);
        }
        public static final int BUTTONS = 0;

        public static String getUIPath(int uiElement){
            switch (uiElement){
                case BUTTONS -> {return "res/ui/button_atlas.png";}
                default -> {return null;}
            }
        }
    }
    public static class EntityConstants{
        public static final int PLAYER = 0;
        public static final int PORTAL = 1;
        public static final int FLAME = 2;
        public static final int PLAYER_SOUL = 3;

        public static int getEntityAnimationsAmount(int entityType){
            switch (entityType){
                case PORTAL, FLAME -> {return 1;}
                case PLAYER -> {return 6;}
                default -> {return 1;}
            }
        }

        public static String getEntitySpritePath(int entityType){
            switch (entityType){
                case PORTAL -> {return "res/entities/portal";}
                case FLAME -> {return "res/entities/flame.png";}
                case PLAYER -> {return "res/entities/player";}
                case PLAYER_SOUL -> {return "res/entities/playerSoul";}
                default -> {return "";}
            }
        }

        public static int getEntityGreenCode(int entityType){
            switch (entityType){
                case PORTAL -> {return 255;}
                case FLAME -> {return 254;}
                case PLAYER -> {return 253;}
                default -> {return -1;}
            }
        }

        public static class FlameConstants{
            public static final int FLAME_WIDTH_DEFAULT = 8;
            public static final int FLAME_HEIGHT_DEFAULT = 16;
            public static final int FLAME_WIDTH = (int) (FLAME_WIDTH_DEFAULT * Game.getScale()/1.5);
            public static final int FLAME_HEIGHT = (int) (FLAME_HEIGHT_DEFAULT * Game.getScale()/1.5);

            public static final int FLAME_HITBOX_WIDTH = (int) (6 * Game.getScale());
            public static final int FLAME_HITBOX_HEIGHT= (int) (6 * Game.getScale());

            public static final int FLAME_DRAW_OFFSET_X = (int) (-1 * Game.getScale()/1.5);
            public static final int FLAME_DRAW_OFFSET_Y = (int) (4 * Game.getScale()/1.5);

            public static final int FLAME_INACTIVE = 1;
            public static final int FLAME_ACTIVE = 0;
        }

        public static class PortalConstants{
            public static final int PORTAL_WIDTH_DEFAULT = 16;
            public static final int PORTAL_HEIGHT_DEFAULT = 16;
            public static final int PORTAL_WIDTH = (int) (PORTAL_WIDTH_DEFAULT * Game.getScale());
            public static final int PORTAL_HEIGHT = (int) (PORTAL_HEIGHT_DEFAULT * Game.getScale());

            public static final int PORTAL_HITBOX_WIDTH = (int) (8 * Game.getScale());
            public static final int PORTAL_HITBOX_HEIGHT= (int) (16 * Game.getScale());

            public static final int PORTAL_DRAW_OFFSET_X = (int) (4 * Game.getScale());
            public static final int PORTAL_DRAW_OFFSET_Y = (int) (0 * Game.getScale());

            public static final int PORTAL_INACTIVE = 1;
            public static final int PORTAL_ACTIVE = 0;
        }

        public static class PlayerConstants{
            public static final int PLAYER_HITBOX_WIDTH = (int) (5 * Game.getScale());
            public static final int PLAYER_HITBOX_HEIGHT= (int) (11 * Game.getScale());

            public static final int PLAYER_DRAW_OFFSET_X = (int) (5 * Game.getScale());
            public static final int PLAYER_DRAW_OFFSET_Y = (int) (5 * Game.getScale());
        }

        public static int getEntityHitboxWidth(int entityType){
            switch (entityType){
                case PORTAL -> {return PortalConstants.PORTAL_HITBOX_WIDTH;}
                case FLAME -> {return FlameConstants.FLAME_HITBOX_WIDTH;}
                case PLAYER, PLAYER_SOUL -> {return PlayerConstants.PLAYER_HITBOX_WIDTH;}
                default -> {return 1;}
            }
        }

        public static int getEntityHitboxHeight(int entityType){
            switch (entityType){
                case PORTAL -> {return PortalConstants.PORTAL_HITBOX_HEIGHT;}
                case FLAME -> {return FlameConstants.FLAME_HITBOX_HEIGHT;}
                case PLAYER, PLAYER_SOUL -> {return PlayerConstants.PLAYER_HITBOX_HEIGHT;}
                default -> {return 1;}
            }
        }

        public static int getDefaultEntityState(int entityType){
            switch (entityType){
                case PORTAL -> {return PortalConstants.PORTAL_INACTIVE;}
                case FLAME -> {return FlameConstants.FLAME_ACTIVE;}
                case PLAYER, PLAYER_SOUL -> {return PlayerAction.IDLE;}
                default -> {return 0;}
            }
        }

        public static int getEntityOffsetX(int entityType){
            switch (entityType){
                case PORTAL -> {return PortalConstants.PORTAL_DRAW_OFFSET_X;}
                case FLAME -> {return FlameConstants.FLAME_DRAW_OFFSET_X;}
                case PLAYER, PLAYER_SOUL -> {return PlayerConstants.PLAYER_DRAW_OFFSET_X;}
                default -> {return 0;}
            }
        }
        public static int getEntityOffsetY(int entityType){
            switch (entityType){
                case PORTAL -> {return PortalConstants.PORTAL_DRAW_OFFSET_Y;}
                case FLAME -> {return FlameConstants.FLAME_DRAW_OFFSET_Y;}
                case PLAYER, PLAYER_SOUL -> {return PlayerConstants.PLAYER_DRAW_OFFSET_Y;}
                default -> {return 0;}
            }
        }

        public static int getEntityDefaultWidth(int entityType){
            switch (entityType){
                case PORTAL -> {return PortalConstants.PORTAL_WIDTH_DEFAULT;}
                case FLAME -> {return FlameConstants.FLAME_WIDTH_DEFAULT;}
                case PLAYER, PLAYER_SOUL -> {return 16;}
                default -> {return 16;}
            }
        }
        public static int getEntityDefaultHeight(int entityType){
            switch (entityType){
                case PORTAL -> {return PortalConstants.PORTAL_HEIGHT_DEFAULT;}
                case FLAME -> {return FlameConstants.FLAME_HEIGHT_DEFAULT;}
                case PLAYER, PLAYER_SOUL -> {return 16;}
                default -> {return 16;}
            }
        }
        public static int getEntityWidth(int entityType){
            switch (entityType){
                case PORTAL -> {return PortalConstants.PORTAL_WIDTH;}
                case FLAME -> {return FlameConstants.FLAME_WIDTH;}
                case PLAYER, PLAYER_SOUL -> {return (int)(16 * Game.getScale());}
                default -> {return 16;}
            }
        }
        public static int getEntityHeight(int entityType){
            switch (entityType){
                case PORTAL -> {return PortalConstants.PORTAL_HEIGHT;}
                case FLAME -> {return FlameConstants.FLAME_HEIGHT;}
                case PLAYER, PLAYER_SOUL -> {return (int)(16 * Game.getScale());}
                default -> {return 0;}
            }
        }
    }

    public static class FontConstants{
        public static final String FONT_PATH = "res/fonts/Minecraft.ttf";
        public static final float FONT_SIZE = 32 * Game.getScale();
    }
}
