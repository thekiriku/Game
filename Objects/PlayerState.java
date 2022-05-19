package com.example.game.Objects;

public class PlayerState {
    public enum State{
        NOT_MOVING,
        START_MOVING,
        IS_MOVING
    }
    private Player player;
    private State state;

    public PlayerState(Player player){
        this.player = player;
        this.state = State.NOT_MOVING;
    }

    public State getState(){
        return state;
    }

    public void update(){
        switch (state){
            case NOT_MOVING:
                if(player.velocityX != 0 || player.velocityY != 0)
                    state = State.START_MOVING;
                break;
            case START_MOVING:
                if(player.velocityX != 0 || player.velocityY != 0)
                    state = State.IS_MOVING;
                break;
            case IS_MOVING:
                if(player.velocityX == 0 && player.velocityY == 0 )
                    state = State.NOT_MOVING;
                break;
            default:
                break;
        }
    }
}
