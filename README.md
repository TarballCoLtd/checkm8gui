# checkm8gui

checkm8gui is an application for macOS and Linux which adapts the [ipwndfu](https://github.com/axi0mx/ipwndfu/) command-line tool by [axi0mX](https://twitter.com/axi0mx/) to a graphical application.

## Credit

This program was written by [me](https://github.com/emeryferrari/), however this program would not be possible without the work [axi0mX](https://twitter.com/axi0mx/) has put into [ipwndfu](https://github.com/axi0mx/ipwndfu/).

## Testing

This program's GUI has been tested in an Ubuntu virtual machine and on a Windows machine, however, it has not been tested on a physical iOS device because I do not own a currently checkm8-compatible device.

## Features

This program can enter your device into pwned DFU mode on checkm8-compatible devices. Once the device is entered into pwned DFU mode, this program can dump the SecureROM, demote your device, and verbose boot your device. Features which are planned to be implemented in the near future are decrypting keybags, changing your boot logo, restoring to signed and unsigned firmwares, and running custom ipwndfu commands.

## Disclaimer

This is not a tool for the inexperienced. This tool could seriously damage your device if misused. If you don't know what you're doing, stay away from this application. It is not feasibly possible to accidentally demote your device with this program. When the demote device option is chosen, multiple confirmation warnings are given before the task is carried out.

## Compilation/Execution

This tool can either be run from the .jar executable of the latest release in the Releases tab, or can be compiled using javac.

## Prerequisites

Besides the Java Runtime Environment or Development Kit, this program requires a separately downloaded copy of [ipwndfu](https://github.com/axi0mx/ipwndfu/). The location of this folder can be wherever you want and is specified by the directory in the checkm8gui.bin file or with the command-line argument --ipwndfu-folder (detailed below). If you can't find the checkm8gui.bin file, run the program and look again. It should appear in the working directory of the program.

## Command-line arguments

-v/-verbose - Prints the command issued to the terminal along with the output of the command when an option is picked. This is not to be confused with verbose booting your iOS device.
--ignore-os - Launches the application regardless of what operating system it's being run on.
--ipwndfu-folder folder - A command to manually specify your ipwndfu folder location. This does not overwrite the location recorded in checkm8gui.bin. This folder specification will only persist in the current session.

## Troubleshooting

If the program crashes without any prior notification or explanation of what happened, run the program from the command-line. Useful information is printed to the console in the event of a crash.

## Known issues

There are many performance and consistency issues in the source code. None of these will affect your experience with the program in practice, however these are planned to be fixed in the next update nonetheless. Saving changes to the program settings also crashes the program. This will be fixed soon. All features listed on disabled buttons are planned to be implemented in the near future.

## Contacting me

I will respond to any PM I receive on Reddit.
[u/verystrangebeing](https://reddit.com/user/verystrangebeing/)