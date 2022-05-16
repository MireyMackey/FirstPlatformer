package com.mireymackey.utils;

import org.jetbrains.annotations.NotNull;

public class Constants {
    public static class Directions{
        public static final int LEFT = 0;
        public static final int UP = 1;
        public static final int RIGHT = 2;
        public static final int DOWN = 3;
    }

    public static class PlayerCondition{
        public static final int IDLE = 0;
        public static final int RUNNING = 1;
        public static final int JUMPING = 2;
        public static final int FALLING = 3;
        public static final int GROUNDING = 4;
        public static final int DAMAGED = 5;
        public static final int ATTACK_1 = 6;
        public static final int JUMP_ATTACK_1 = 7;
        public static final int JUMP_ATTACK_2 = 8;

        public static int getFrameAmount(int playerCondition){
            switch (playerCondition){
                case RUNNING -> {return 6;}
                case IDLE -> {return 5;}
                case DAMAGED -> {return 4;}
                case JUMPING, ATTACK_1, JUMP_ATTACK_1, JUMP_ATTACK_2 -> {return 3;}
                case GROUNDING -> {return 2;}
                case FALLING -> {return 1;}
                default -> {return 1;}
            }
        }
    }
}
