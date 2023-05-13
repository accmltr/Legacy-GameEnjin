# Game Enjin

Game Enjin is a 2D game engine made for prototyping game mechanics using the Scala programming language.

It is not focused on competing with industry leading game engines in terms of creating AAA gaming experiences, but is rather concerned with being a powerful and efficient tool for prototyping.

The goal is for it to be powerful tool for prototyping, and be a good educational accesory.

## Target Features for MVP

- `Game` class to setup project and run/pause/quit game.
- Game objects for hosting provided and custom components.
- Easy creation of game object factory methods.
- Editor for placing game objects via reflection on methods annotated with `@GameObjectFactoryMethod`.
- Rendering components for primitive shapes, polygons and sprites.
- Collision detection for primitive shapes and polygons.
- `Sound` singleton for mono audio playback and `SpacialSound` component for positional audio.
- Basic UI elements and canvas layer.
- User input events via `UserInput` singleton.
- Export games as executables for Windows.

## Motivation

In my experience, trying to quickly test and prototype ideas for games with complicated mechanics and logic tends to be quite a slow and painful process in most engines. I have been using Unity3D and Godot for my personal projects for many years, and have generally found it difficult to create and prototype games which often involve complicated combat mechanics and stat systems. When I discovered Scala however, I found much more clarity when creating intricate systems with interesting data types and powerful stateless classes for processing that data. There is much less boilerplate and there are many powerful language features, like [implicit conversions](https://docs.scala-lang.org/scala3/book/ca-implicit-conversions.html), [pattern matching](https://docs.scala-lang.org/scala3/book/control-structures.html#match-expressions) and its [domain modelling tools](https://docs.scala-lang.org/scala3/book/domain-modeling-tools.html), which gives you free reign to structure your project the way you want to without running into roadblocks.

Another important thing about development in general is for the tools you use to not get in your way. I believe a game engine geared towards prototyping with minimal features will help developers to stay better focused on creating robust, barebones prototypes without going down various rabbit holes of playing with more advanced features they do not need. Since there will not be many features outside of the listed ones, Game Enjin should be an easy, yet powerful, tool to pick up and add to your arsenal as a game developer.

## Education Potential

A tool like Game Enjin would be a great way to properly introduce beginners to the most important design patterns and engineering principles needed to create robust and scalable games.

It is also a fun and effective way to learn about some of the core features of [Scala](https://docs.scala-lang.org/scala3/book/scala-features.html).

## Current State

This version of the project can be seen as a very rough draft with many likely changes due for the overall design. However, I already made a tower defense game with it as a test, and it worked quite well.

Here are some screenshots:

![2023-05-12 (5)](https://github.com/accmltr/GameEnjin/assets/19354678/9c5ebfb0-4884-4b13-b258-105b228dfb18)
![2023-05-12 (4)](https://github.com/accmltr/GameEnjin/assets/19354678/5edbac47-ed76-4c68-b1c0-43e8d9c5bca5)
