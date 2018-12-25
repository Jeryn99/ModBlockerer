package me.fril.modblocker;

public enum MBActions {
    DISCONNECT(1),
    BAN(2);

    private int id;

    MBActions(int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
