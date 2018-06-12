# Acovado waiter app
This application is designed to support waiter in managing orders of restaurant running Avocado restaurant system.

## Features
Application is capable of:
 - Showing orders ready to be served
 - Showing orders that have been saved
 - Showing order details
 - Notify about ready orders
 - Closing orders

## Getting started
These instructions help you to run the app in your local machine for testing puropose.

### Prerequisites
To run the app from downloaded code you will need:
 - *Android Studio* with Android SDK 21+ for building project.
 - Android device supporting at least Android SDK 21 or proper device emulator.

### Installation
To create and load `.apk` file open Android Studio and import project into it. Than select `build` -> `Generate Signed APK`. File should be created in `target` directory inside project sources, Install created file on device to start using it.

### Usage and possibilities
Application main interface is presenting two columns with prepared (left) and served (right) orders. Evey time new item is inserted into prepared list, device will make notification to waiter that order is ready to be served. Once waiter is bringing order to client, he shall select order from list, check that every item is served properly and click `ORDER SERVED` button. This will conclude in moving order to served list.

When client is willing to leave restaurant and pay, waiter can open order from served tab, verify price and order details, than close order by clicking `ORDER PAID` button.

Every order on both list is displaying table and it individual id to support waiter job.
