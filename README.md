# Testing-Game
Testing for a game. May complete it, may not. Depends on how much time I have during the school year. 

HOW TO COMPILE:
```console
javac *.java
```

HOW TO RUN:
```console
java Game.java
```

HOW TO PLAY:
 - Movement controls are W, A, S, D for Up, Left, Down, Right respectively
 - Shooting contols are Space to shoot
    - Only 5 bullets can be active at a time


TODO:
 - [WIP] Add player health and display it on the screen
    - Currently health is displayed on the terminal
    - Maybe have a small box in the top left/right corner that displays the health
 - [WIP] Add collision between player and enemy
 - If possible, try to make bullet (and soon player) collision more efficient
    - There are checks for the bullet collision every single frame of the game, which is obviously bad
    - Try to look into event handlers 
 - Try to look into switch statements instead of using if/else statements
    - I heard they're more efficient and better to look at lol


 - [DONE] If possible, change the enemies into circles instead of triangles
    - I just don't like the way the triangles look. The triangles are built around some pre-existing code that I found anyways, and that's plagiarism
    - The circles would be implemented as follows
        - The circle object would be displayed on the screen as a circle
        - Bullet collision would be a large square surrounding the circle (square is centered around the origin and has a length of 2 * radius)
        - Player collision would be a smaller square inside of the circle (square's corners touch the edge of the circle)
