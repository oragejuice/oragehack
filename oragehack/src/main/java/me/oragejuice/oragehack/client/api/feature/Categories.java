package me.oragejuice.oragehack.client.api.feature;

public enum Categories {

    COMBAT("Combat"),
    MOVEMENT("Movement"),
    RENDER("Render"),
    CLIENT("Client");

    String name;
    Categories(String name){
        this.name = name;
    }

    @Override
    public String toString(){
        return name;
    }
}
