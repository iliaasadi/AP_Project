package com.Ap_project.model;

public class Block {
    private String blocker;
    private String blocked;

    public Block(String blocker, String blocked) {
        this.blocker = blocker;
        this.blocked = blocked;
    }

    public Block(){
    }

    public String getBlocker() {
        return blocker;
    }

    public void setBlocker(String blocker) {
        this.blocker = blocker;
    }

    public String getBlocked() {
        return blocked;
    }

    public void setBlocked(String blocked) {
        this.blocked = blocked;
    }
}
