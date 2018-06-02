# Acovado clinet app
This application is a single part of a complex system for any restaurant. As an example it is adjusted to requirements of sushi restaurant owner.

## Getting started
These instructions help you to run the app in your local machine for testing puropose.

### Prerequisites
To run the app from downloaded code you will need:
* [Node.js](https://nodejs.org/) in currently recomended version
* [Expo XDE](https://github.com/expo/xde/releases) (if you prefer you can use Expo CLI also, but this instructions are directed to XDE users)
* Phone with Android (v >= 4.1) or Emulator (I recommend [Genymotion](https://www.genymotion.com/fun-zone/), device: Google Nexus 5 - 5.1.0 - API 22 - 1080 x 1920)

You can also run the app without installing all developement tools on your local machine. To do that you need only [Expo Client App](https://play.google.com/store/apps/details?id=host.exp.exponent&hl=pl) on your Android phone(v >= 4.1).

### Installation
If you decided to use only Expo Client App on you Android Phone the only thing you have to do to run the app is open the Expo Client app, click "Expolre" on the bottom navigation bar and type "avocado" or "ybaa" where avocado is the name of application and ybaa is the profile of publisher. Then just click the app and you got it!

If you prefer to download the code and run it by yourself follow the instructions belowe. 
At the beginning you have to clone or download the repo from github. Then open your terminal in directory with package.json file and type:
```sh
$ npm install
```
Next you have to run Expo XDE, click "Project" -> "Open project" and choose the path to cloned code. Then (if it is not set) switch to tunnel connection by clicking setting icon -> host -> tunnel and turn off "developement mode" in the same place.
If you want to run the app on your device click "share" on Expo XDE and open Expo Client App on your phone. Scann the QR code and that's all.
If you prefer to run the app on android emulator run your virtual device and when it is ready click "Device" -> "Open on Android" on Expo XDE. Then wait and that's all.

### Usage and possibilities
