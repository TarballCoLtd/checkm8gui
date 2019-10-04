package javax.emeryferrari.checkm8;
public class C8Const {
	private C8Const() {}
	public static final String APPLICATION_NAME = "checkm8gui";
	public static final String[] APPLICATION_AUTHOR = {"u/verystrangebeing", "axi0mX"};
	public static final String APPLICATION_VERSION = "0.1";
	
	public static final String TITLE = "<html><body><font size=\"20\">checkm8gui";
	public static final String SUBTITLE = "<html><body>by Emery Ferrari, using axi0mX's ipwndfu</font><font size=\"20\"><br/><br/>";
	
	public static final String EXPLOIT_DEVICE = "Exploit iOS Device";
	public static final String DUMP_SECUREROM = "Dump SecureROM";
	public static final String DECRYPT_KEYBAG = "Decrypt Keybag";
	public static final String DEMOTE_DEVICE = "Demote iOS Device";
	public static final String VERBOSE_BOOT = "Verbose Boot iOS Device";
	public static final String BOOT_LOGO = "Custom iOS Device Boot Logo";
	public static final String RESTORE_FIRMWARE_SIGN = "Restore Signed Firmware to iOS Device";
	public static final String RESTORE_FIRMWARE_UNSIGN = "Restore Unsigned Firmware to iOS Device";
	public static final String CUSTOM_IPWNDFU_FLAGS = "Run Custom ipwndfu Argument";
	public static final String SETTINGS = "Settings";
	public static final String ABOUT = "About";
	public static final String OK = "OK";
	public static final String NEWLINE = "<html><body><br/>";
	public static final String EXPLOITING_LABEL = "Running exploit...";
	public static final String IPWNDFU_FOLDER_LABEL = "ipwndfu Folder: ";
	public static final String SAVE_RETURN = "Save Changes and Return";
	public static final String BACK = "Back";
	
	public static String EXPLOIT_DEVICE_COMMAND = "";
	public static String DUMP_SECUREROM_COMMAND = "";
	public static String DEMOTE_DEVICE_COMMAND = "";
	public static String VERBOSE_BOOT_COMMAND = "";
	
	public static final String CONSOLE_ERROR = "Exception occurred while attempting to run a command in the terminal.";
	
	public static final String SETTINGS_FILE = "checkm8gui.bin";
	
	public static final String OS_NAME = "os.name";
	
	public static final String TERMINAL_HEADER = "terminal > ";
	
	public static final String[] BUTTONS_1 = {"Yes, I know what I'm doing", "Cancel"};
	public static final String[] BUTTONS_2 = {"Yes", "No"};
	
	public static final String WARNING_1 = "WARNING: Unless you know what demoting your device means and what JTAG is, do not complete this operation. It is not reversible.";
	public static final String WARNING_2 = "Are you sure? You cannot undo this action.";
	public static final String WARNING_TITLE = "checkm8gui Warning";
	
	public static final String IPWNDFU_FOLDER_FLAG = "--ipwndfu-folder";
	public static final String IGNORE_OS_FLAG = "--ignore-os";
	public static final String VERBOSE_FLAG_1 = "-v";
	public static final String VERBOSE_FLAG_2 = "-verbose";
	
	public static final String DEFAULT_1 = "ipwndfu-directory=ipwndfu/";
	
	public static final String[] OS = {"aix", "mac", "nix", "nux"};
	
	public static final String VALID = "Valid operating system detected.\\nLaunching...\\n";
	public static final String IGNORE = "Invalid operating system detected.\\nIgnoring and launching...\\n";
	public static final String INVALID = "Invalid operating system detected.\\nipwndfu is a Unix executable and must run on a Unix-based operating system such as macOS or a variant of Linux.\\nIf your operating system is based on Unix and this error message persists, run this program again with the --ignore-os argument.\\nQuitting...\\n";
}