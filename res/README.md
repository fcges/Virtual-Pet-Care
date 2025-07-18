# Virtual Pet Care

## Overview
This project is a virtual pet care application that allows users to take care of a virtual pet. Users can feed, play with, clean it, and put it to sleep. The pet has various moods and needs that change over time. User can get coins according to the pet's mood and needs, and use them to interact with the pet or buy items. The application is built using Java and Swing for the GUI.

## Features
- interactive GUI using Java Swing
- pet with various moods and needs
- coins system for interactions and items
- pet care activities: feed, play, clean, sleep
- pet status display
- pet mood and needs display
- 6 items to buy
- equipments will affect the pet's mood and needs
- 7 pet moods

---

## How to run the project
### Requirements
- Java Development Kit (JDK) 23 or higher
### Run the jar file
- Open a terminal and navigate to the directory where the jar file is located.
- Run the following command:
```bash
java -jar VirtualPetCare.jar
```
### Interactive GUI
- The GUI will open, and you can interact with the pet using the buttons provided.
- You can click on the buttons to feed, play with, clean, or put the pet to sleep when in the main scene.
- You can click on the button with a sandglass icon to advance the game for one step.
- You can also click on the buttons to open the shop or inventory scenes.
#### Shop scene
- Click on the button on the top-right corner to open the shop scene.
- In the shop scene, you can buy items using the coins you have earned.
- Click on the up or down arrows on the left to navigate between the items.
- Click on the green button with a check mark to buy the item.
- Click on the red button with a cross to go back to the main scene.
#### Inventory scene
- You can open the inventory scene by clicking on the button below the shop button.
- In the inventory scene, you can see the items you have and equip them.
- Click on the up or down arrows on the left to navigate between the items.
- Click on the green button with a check mark to equip the item.
- Click on the red button with a cross to go back to the main scene.

---

## Changes
- Change some exception messages to be more user-friendly.
- Delete the name of the pet in the GUI version.

---

## Assumptions
- The pet will not die.
- The pet will refuse to do any other activity if it is hungry or sleepy, except feed or sleep.
- The pet will not be able to play if it is dirty.
- The pet will earn more coins if it is happy.
- The pet will be angry if all the health values are very low.
- The pet will not earn coins if it is sleepy, hungry, uncomfortable(dirty) or bored.
- The pet will lose more coins if it is angry.
- Equipments will affect the pet's mood and needs.
- Equipments will broke after its durability is 0.
- If the pet is angry and user can not afford any interaction, game will be over.

## Limitations
- User can only buy one item at a time.
- User can only have one item for each type.
- Shop and inventory scene will not display the fully detail information of the item.
- If add more item types in the future, the shop and inventory information may be out of the bounds. A scroll bar may be needed.

## Citations
- JLayeredPane (Java Platform SE 8 ). (2024, December 4). Oracle.com. https://docs.oracle.com/javase/8/docs/api/javax/swing/JLayeredPane.html
- Rohan. (2012, July 5). Making Image button look pressed/clicked in Swing. Stack Overflow. https://stackoverflow.com/questions/11351728/making-image-button-look-pressed-clicked-in-swing
- mportiz08. (2024). Newline in JLabel. Stack Overflow. https://stackoverflow.com/questions/1090098/newline-in-jlabel
‌