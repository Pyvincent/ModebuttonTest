package com.wang.vincent.modebuttontest.databean;

/**
 * Created by vincent on 16-9-24.
 */

public class Vend {
    private int id;
    private String vendName;

    public Vend() {
    }

    public Vend(int id, String vendName) {
        this.id = id;
        this.vendName = vendName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVendName() {
        return vendName;
    }

    public void setVendName(String vendName) {
        this.vendName = vendName;
    }
}
