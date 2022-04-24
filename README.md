# Forbidden-Island

<br>

## **Description**

<br>

This project is a collaboration between Clément Gilli and [Louis-Alexis Peneloux](https://github.com/moleculeATP). Our goal is to develop the board game "The Forbidden Island" by Matt Leacock.


<br>

<img alt="Forbidden_Island.png" src="./resources/images/Forbidden_Island.png"/>

<br>

## <br> **Development**

<br>
We have implemented the whole game, including the extensions. So we managed to implement everything. However, we had a hard time implementing the key exchange and the "navigator" role which were complex, as well as the startup screen with the sliders, but we finally succeeded. Apart from that, the rest was pretty simple but quite long.
<br> <br>
Clément was in charge of the board model, the display of the board and the players, the different roles and the special actions.
Louis-Alexis was in charge of the implementation of the card deck, the key exchange and the display of the players' inventories.
We did the biggest parts together, like the player model and controllers.

<br>

We have made an [uml](uml.pdf) diagram divided into 3 parts: model, view and controller.

## <br> **Rules**

<br>
The goal of the game is to collect the 4 different artifacts and then meet on the heliport.
You can lose in 4 different ways:

- the heliport has sunk
- a type of artifact has sunk
- a player has drowned
- the water level has reached the maximum level

<br>
To help you, there are 6 different roles with unique powers:
<br>
<br>

- <img alt="control2.png" src="./resources/images/plongeur2.png"/> the diver can move in the water
- <img alt="control2.png" src="./resources/images/navigateur2.png"/> the navigator can move another player one or two cases
- <img alt="control2.png" src="./resources/images/explorateur2.png"/> the explorer can move and dry diagonally
- <img alt="control2.png" src="./resources/images/messager2.png"/> the messenger can give cards to any player
- <img alt="control2.png" src="./resources/images/pilote2.png"/> the pilot can fly to any case
- <img alt="control2.png" src="./resources/images/ingenieur2.png"/> the engineer can dry a case at no cost

<br> In addition, you have 2 special cards:
<br>

- <img alt="control2.png" src="./resources/images/sandbag.png"/> the sandbag allows to dry any case
- <img alt="control2.png" src="./resources/images/helicopter2.png"/> the helicopter allows you to fly with the other players on the case to any case


## <br> **Controls** 
<br>

<p align="center">
<img alt="control2.png" src="./resources/images/control2.png"/>
</p>

## <br> **Interface** 

<br>
<p align="center">
<img alt="control2.png" src="./resources/images/interface.png"/>
</p>