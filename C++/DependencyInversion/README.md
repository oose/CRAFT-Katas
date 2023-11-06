# Dependency Inversion

The example simulates a railway level crossing with barrier that shall be lowered when a train is arriving to prevent accidents. It is working based on a state machine.

## Overview

We have three components:
* driver: This is supposed to be the interface to the actual actuator of the barrier. Currently there is only a stub.
* domain: Domain logic, in particular the state machine that operates the barrier.
* ui: User Interface for displaying status messages

## The problem

Currently the domain logic depends on the UI component which makes changing the UI or adding an additional UI difficult. 

## Task

Refactor the code by inverting the abovementioned dependency: The UI shall depend upon the domain logic as we expect it to change more frequently.

## Bonus

Provide a possibility to add multiple UIs to the software. All UIs that currently registered shall receive the messages.