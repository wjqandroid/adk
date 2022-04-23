package com.visionvera.psychologist.c.eventbus;

public class CommitToMyEvaluationBus {

    public int type;
    public String photo;
    public String name;

    public CommitToMyEvaluationBus(int type) {
        this.type = type;
    }

    public CommitToMyEvaluationBus(int type,String photo,String name) {
        this.type = type;
        this.photo = photo;
        this.name = name;
    }
}
