# PirateDice (Mille Sabord)

## Purpose

This project is a very simple exercice that I build like I do a puzzle. I do it simply to relax and keep on touch with Java. Here I implement this cool game I've played with friends. 

## The Game
![picture](src/Assets/PirateDice.jpg)

## Actual Interface (Temp)

### On the Island 
![picture](src/Assets/Looking/2019-01-17_Island.png)

### On the Death Island
![picture](src/Assets/Looking/2019-01-17_Death.png)

## Phase(s)

| # | Life cycle |                  Description                       | State |
|---|------------|----------------------------------------------------|-------|
| 1 | ALPHA      | Building the minimum viable product of the game    |   X   |
| 2 | BETA       | Enhance gaming experience                          |       |
| 3 | RELEASE    | Deploying a Mobile version of the game             |       |
| 4 | RELEASE    | Implementing a multiplayer game experience         |       |

## What have been done

- [x] Generating Models
- [x] Coding a singleton repository
- [x] Preparing a Preferences files 
- [x] Preparing a Strong Assets 
- [ ] Preparing a Music theme
- [ ] Preparing a Sound effects
- [ ] Preparing nice visuals aspects 

### Business logic

#### GamePlay
- [x] Implementing rolling dices
- [x] Implementing the switch card
- [x] Implementing the Death
- [x] Implementing the lost tempPts when player obtain three Deaths 
- [x] Implementing the beat the death
- [ ] Implementing minus pts for all other players when beat the death
- [ ] Implementing the ending game
- [ ] Implementing the setup menu

#### Cards 
-End Turn effect-
- [x] Implementing the BL for : PirateCard
- [x] Implementing the BL for : GoldenPiece  
- [x] Implementing the BL for : DiamondCard 
- [x] Implementing the BL for : PirateBoatCardEasy
- [x] Implementing the BL for : PirateBoatCardMedium
- [x] Implementing the BL for : PirateBoatCardHard
- [x] Implementing the BL for : MonkeyPirateCard

-During turn effect- 
- [x] Implementing the BL for : SimpleSkullCard  
- [x] Implementing the BL for : DoubleSkullCard 
- [x] Implementing the BL for : WitchCard  
- [ ] Implementing the BL for : ChestCard 


### Issue(s)
- [x] Line 133 :: service : EVERYTIME WE PLAY PTS INCREMENT for PirateBoatCards 
- [ ] Problem when player pts is shorter than the pirateBoatCards
- [ ] Factorizing MonkeyPirateCard

## Rules

![picture](src/Assets/Rules/Rules_1.png)
![picture](src/Assets/Rules/Rules_2.png)
![picture](src/Assets/Rules/Rules_3.png)
![picture](src/Assets/Rules/Rules_4.png)

## Copyright
***
- Author : Haim Shafir
[![Haim Shafir](https://youtu.be/uT5c_MfSZzY)

- Design : Jose Pedro
- Programmor : Maxime Laniel 

- Image(s) : <a href="https://www.freepik.com/free-vector/island-background-design_1020626.htm">Designed by Brgfx</a> 
***