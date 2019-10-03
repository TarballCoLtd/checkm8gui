package javax.emeryferrari.checkm8;
import java.io.*;
public class Checkm8Launcher {
	private static final String osRaw = System.getProperty("os.name");
	private static final String os = System.getProperty("os.name").toLowerCase();
	public static String ipwndfuFolder = "";
	private Checkm8Launcher() {}
	public static void main(String[] args) {
		boolean set = true;
		for (int x = 0; x < args.length; x++) {
			if (args[x].equals("--ipwndfu-folder")) {
				if (x+1 < args.length) {
					Checkm8Launcher.ipwndfuFolder = args[x+1] + "/";
					set = false;
				}
			}
		}
		if (set) {
			try {
				if (!new File(C8Const.SETTINGS_FILE).exists()) {
					FileOutputStream fos = new FileOutputStream(C8Const.SETTINGS_FILE);
					fos.write("ipwndfu-directory=ipwndfu".getBytes());
					fos.flush();
					fos.close();
				}
				FileInputStream fis = new FileInputStream(C8Const.SETTINGS_FILE);
				BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
				Checkm8Launcher.ipwndfuFolder = reader.readLine().split("=")[1];
				reader.close();
			} catch (IOException ex) {
				ex.printStackTrace();
				System.exit(1);
			} catch (ArrayIndexOutOfBoundsException ex) {
				ex.printStackTrace();
				System.exit(1);
			}
		}
		C8Const.EXPLOIT_DEVICE_COMMAND = "./" + Checkm8Launcher.ipwndfuFolder + "ipwndfu -p";
		C8Const.DUMP_SECUREROM_COMMAND = "./" + Checkm8Launcher.ipwndfuFolder + "ipwndfu --dump-rom";
		C8Const.DEMOTE_DEVICE_COMMAND = "./" + Checkm8Launcher.ipwndfuFolder + "ipwndfu --demote";
		C8Const.VERBOSE_BOOT_COMMAND = "./" + Checkm8Launcher.ipwndfuFolder + "ipwndfu -p --boot";
		if (os.contains("mac")) {
			Checkm8Launcher.valid(args);
		} else if (os.contains("nix")) {
			Checkm8Launcher.valid(args);
		} else if (os.contains("nux")) {
			Checkm8Launcher.valid(args);
		} else if (os.contains("aix")) {
			Checkm8Launcher.valid(args);
		} else {
			boolean ignore = false;
			for (int y = 0; y < args.length; y++) {
				if (args[y].equals("--ignore-os")) {
					ignore = true;
				}
			}
			if (ignore) {
				Checkm8Launcher.ignore(args);
			} else {
				Checkm8Launcher.invalid();
			}
		}
	}
	public static void printOS() {
		System.out.println("OS: " + osRaw);
	}
	private static void valid(String[] args) {
		Checkm8Launcher.printOS();
		System.out.println("Valid operating system detected.\nLaunching...\n");
		Checkm8Launcher.launch(args);
	}
	private static void ignore(String[] args) {
		Checkm8Launcher.printOS();
		System.out.println("Invalid operating system detected.\nIgnoring and launching...\n");
		Checkm8Launcher.launch(args);
	}
	private static void invalid() {
		Checkm8Launcher.printOS();
		System.out.println("Invalid operating system detected.\nipwndfu is a Unix executable and must run on a Unix-based operating system such as macOS or a variant of Linux.\nIf your operating system is based on Unix and this error message persists, run this program again with the --ignore-os argument.\nQuitting...\n");
		System.exit(1);
	}
	private static void launch(String[] args) {
		Display.createDisplay(args);
	}
}