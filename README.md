# checkm8gui

checkm8gui is an application for macOS and Linux which is a GUI for the [ipwndfu](https://github.com/axi0mx/ipwndfu/) bootrom exploit suite by [@axi0mX](https://github.com/axi0mx/).

## Credit

This program was written by me, however this program would not be possible without the work [axi0mX](https://github.com/axi0mx/) has put into [ipwndfu](https://github.com/axi0mx/ipwndfu/) and the work [@Srikanth-Lingala](https://github.com/srikanth-lingala/) has put into [zip4j](https://github.com/srikanth-lingala/zip4j/).<br/>
[zip4j](https://github.com/srikanth-lingala/zip4j/) is licensed under the [Apache License 2.0](https://www.apache.org/licenses/LICENSE-2.0.txt).<br/>
zip4j Copyright 2010 Srikanth Reddy Lingala

## Features

This program can put your device into pwned DFU mode on a checkm8-compatible device. Once the device is in pwned DFU mode, this program can dump the SecureROM of the device, demote the device (do your research on this before trying), or verbose boot an A11 iPhone X. Features which are planned to be implemented in the near future are: decrypting keybags, changing your boot logo, restoring to signed and unsigned firmwares, and running custom ipwndfu commands.

## Disclaimer

This is not a tool for the inexperienced. This tool could seriously damage your device if misused. If you don't know what you're doing, stay away from this application. It is not feasibly possible to accidentally demote your device with this program. When the demote device option is chosen, multiple confirmation warnings are given before the task is carried out.

## Compilation/Execution

This tool can either be run from the .jar executable of the latest release in the Releases tab, or can be compiled using javac.

## Prerequisites

Java Runtime Environment or Java Development Kit (Java 8 through 14 are known to work)<br/>
A Unix-based operating system (tested on both Ubuntu 19.04.3 and macOS 10.15.6)

## Command-line arguments

-v/-verbose - Prints the command issued to the terminal along with the output of the command when an option is picked. This is not to be confused with verbose booting your iOS device.<br/>
--ignore-os - Launches the application regardless of what operating system it's being run on.<br/>
--ipwndfu-folder folder - A command to manually specify your ipwndfu folder location. This does not overwrite the location recorded in checkm8gui.bin. This folder specification will only persist in the current session.

## Known issues

There are many performance and consistency issues in the source code. None of these will affect your experience with the program in practice, however these are planned to be fixed in a future pre-release update nonetheless.

## Contacting me

I will respond to any PM I receive on Reddit.<br/>
[u/verystrangebeing](https://reddit.com/user/verystrangebeing/)
