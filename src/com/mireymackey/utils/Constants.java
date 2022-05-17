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
        public static final int GOING_DOWN = 0;
        public static final int GOING_UP = 1;
        public static final int GROUND_HIT = 2;
        public static final int IDLE = 3;
        public static final int RUNNING = 4;
        public static final int STOP_RUNNING = 5;

        public static int getFrameAmount(int playerCondition){
            switch (playerCondition){
                case RUNNING -> {return 10;}
                case GOING_DOWN, GOING_UP -> {return 6;}
                case GROUND_HIT -> {return 4;}
                case STOP_RUNNING -> {return 3;}
                case IDLE -> {return 1;}
                default -> {return 1;}
            }
        }
    }
}
