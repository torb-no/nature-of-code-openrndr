# Vectors - Exercise 1.8

     void update() {
     
        PVector mouse = new PVector(mouseX,mouseY);
        PVector dir = PVector.sub(mouse,location);
     
        dir.normalize();
     
        dir.mult(0.5);
     
        acceleration = dir;
     
        velocity.add(acceleration);
        velocity.limit(topspeed);
        location.add(velocity);
     
    }

> Try implementing the above example with a variable magnitude of acceleration, stronger when it is either closer or farther away.

[Link](http://natureofcode.com/book/chapter-1-vectors/#chapter01_exercise8)