package models;
//package views;

import models.Views;

public class ForbiddenIsland {

    public static void main(String[] args) {
        int nb = 6;

        Plateau plateau = new Plateau(nb);
        //Validation validation = new Validation(plateau);
        Views views = new Views(plateau);
        views.display();

    }
}