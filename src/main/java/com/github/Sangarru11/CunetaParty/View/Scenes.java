package com.github.Sangarru11.CunetaParty.View;

public enum Scenes {

    MAIN("View/Main.fxml");
    private String url;
    Scenes(String url){
        this.url=url;
    }
    public String getURL(){
        return url;
    }
}