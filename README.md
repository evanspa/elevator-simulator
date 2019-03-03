# elevator-simulator

A program that simulates a building elevator.

# Motivation

A toy to play around with Clojure.  An elevator simulation seems like a good
idea because the rules are not terribly complicated, but there are enough of
them to make it a non-trivial system.

My goal is to use this project as a means for exploring and using Clojure.

# Requirements and rules for the Simulation

* The simulator will consist of a single building with at least 1 elevator.
* The number of elevators can be specified when the simulation is started, and
  cannot be changed after the simulation has started.
* The number of floors for the building can be specified when the simulation is
  started.  The minimum number of floors a building can have is 2.
* People will spontaneously appear on random floors (including the ground
  floor), and will immediately be in the vicinity of the elevators (the
  simulator will not be concerned about people walking down hallways and
  whatnot).
* A person will consist of a list of destination floors, along with a duration
  that they will spend on each floor before wanting to go to their next floor.
* A person will have a weight (in kg).
* Once an elevator delivers a person to their destination floor, that person
  will either disappear, or will have a new destination.
* When a person has been delivered to their final destination floor, they will
  disappear from the simulation.
* An elevator will have a max load capacity, measured in kilograms.
* If an elevator is overloaded, it will sound an alarm, and people will need to
  exit the elevator in a LIFO order until the elevator is not overloaded.
* An elevator will test if it is overloaded when it has closed its doors and is
  about to move to its next destination.
* An elevator will close its doors when 3 seconds has elapsed without a new
  passenger entering it.

# Non-functional Requirements

* There should be a strict separation between the user interface / graphics of
the simulation and the logic of the simulation.  This will allow the development
of various front-ends for the simulator, while reusing its logic.  For example,
a rich client front-end could be developed in Java FX (or Swing, etc), or a web
front-end could be developed using ClojureScript, and both should be able reuse
the same exact logic layer.

# Random Design Thoughts

We will use Clojure's core.async to model our simulation.  We'll have the
following processes:

* Floors.  A floor will be a process that produces people at random intervals.  People will simply randomly appear on a floor.
* Elevators.  An elevator will be a process that consumes people from floors and
delivers people to their immediate destination floor (a person may have multiple
destination floors, but the elevator only cares about delivering a person to
their immediate (next) destination floor.

Is a person a process?  I don't think so.  A person does not produce or consume
anything.  It simply has state that changes over time.  So, an atom wrapping a map?
