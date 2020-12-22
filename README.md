# McGill-IntroComputerScience
Assignments for Introduction to Computer Science class at McGill University

## Assignment 1: Travel Agency
For this assignment you will write several classes to simulate an online travel agency.

## Assignment 2: A Confusing Train Ride
Due to a significant increase in its student population, especially in its Machine Learning department, the single direct train going to Hogwarts has been replaced by a network of 15 stations distributed over three lines. However, this has revealed to not exactly be an improvement. Just like the staircases within the castle, the stations get bored and, to fight this boredom, rearrange themselves every 2 hours. However, luckily for you, the stations have not yet started switching lines; they always remain on the same line, but the order of the stations within a line gets shuffled. Your task is to implement this shuffling train network and simulate the trip of an unfortunate traveler. You will be given Java templates to complete.

## Assignment 3: A Crowded Cat Cafe Chain
This assignment aims at building on what we have seen in assignment 2, where we navigated through list-like objects, to start working with non-linear data structures. In this assignment, you will implement a derivative of a Binary Search Tree and execute several methods that iterate through trees. The objective is to familiarize yourself with the implementation of this Abstract data type, probably the most discussed one in the course. More specifically, you will be applying concepts seen through the in-class discussion of recursion, trees and how to navigate them, and binary search trees, as well as some principles we will formalize when we discuss heaps.

You have just been named CEO of a chain of Cat Cafes. A cat cafe is a coffee shop in which cats are roaming and interact with customers. At the time of your promotion, you are informed that the chain does not have any repository of the cats, and maintains no information about their seniority. You are extremely upset upon hearing this as you understand those cats are your most important staff members and, like any other employees, deserve salary and working conditions that improve with seniority. You immediately call your best friend, who recently told you they were a computer science expert. You expect them to build you a state-of-the-art database for all your cats. Upon hearing your request, they seem to hesitate a bit and, after a moment of wavering, confess that they are only taking their second computer science course and cannot yet build a professional database. However, they recently learned about binary search trees and how they can be leveraged to efficiently access data. They explain they have not yet learned about heaps and as such their idea does not utilize trees to their full potential, but it should still provide a solution to your problem. They email you a quick draft and you start getting excited about it (after all, cats do love trees), until they specify they cannot help you because they are currently busy with their new job, which they then begin describing. It sounds pretty cool until they try to convince you to invest in their "company" by buying a bunch of knives to re-sell. You try to tell them it sounds like a pyramid scheme but they start yelling something about those only ever occurring in Ancient Egypt, and they hang up on you. You try to call them back but it appears they blocked your number. It looks like you are going to have to build your tree on your own. You have a second look at their email to see what you have to work with, and what is left to do. The database of all cats is represented by a ternary tree CatTree. This tree has a single field root which contains a reference to the root node. The nodes are defined by a private nested class CatNode. Each CatNode is associated with data stored in a CatInfo object (code provided).
