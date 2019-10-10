package javax.emeryferrari.checkm8;
import net.lingala.zip4j.exception.*;
import net.lingala.zip4j.*;
import java.io.*;
public class Checkm8Launcher {
	private static final String osRaw = System.getProperty(C8Const.OS_NAME);
	private static final String os = System.getProperty(C8Const.OS_NAME).toLowerCase();
	public static String ipwndfuFolder = "ipwndfu/";
	private Checkm8Launcher() {}
	public static void main(String[] args) {
		try {
			new Checkm8Launcher().extract();
		} catch(IOException ex) {
			ex.printStackTrace();
			System.out.println(C8Const.ZIP_ERROR);
			System.exit(1);
		}
		
		C8Const.EXPLOIT_DEVICE_COMMAND = "./" + Checkm8Launcher.ipwndfuFolder + "ipwndfu -p";
		C8Const.DUMP_SECUREROM_COMMAND = "./" + Checkm8Launcher.ipwndfuFolder + "ipwndfu --dump-rom";
		C8Const.DEMOTE_DEVICE_COMMAND = "./" + Checkm8Launcher.ipwndfuFolder + "ipwndfu --demote";
		C8Const.VERBOSE_BOOT_COMMAND = "./" + Checkm8Launcher.ipwndfuFolder + "ipwndfu -p --boot";
		if (os.contains(C8Const.OS[0])) {
			Checkm8Launcher.valid(args);
		} else if (os.contains(C8Const.OS[1])) {
			Checkm8Launcher.valid(args);
		} else if (os.contains(C8Const.OS[2])) {
			Checkm8Launcher.valid(args);
		} else if (os.contains(C8Const.OS[3])) {
			Checkm8Launcher.valid(args);
		} else {
			boolean ignore = false;
			for (int y = 0; y < args.length; y++) {
				if (args[y].equals(C8Const.IGNORE_OS_FLAG)) {
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
		System.out.println(C8Const.VALID);
		Checkm8Launcher.launch(args);
	}
	private static void ignore(String[] args) {
		Checkm8Launcher.printOS();
		System.out.println(C8Const.IGNORE);
		Checkm8Launcher.launch(args);
	}
	private static void invalid() {
		Checkm8Launcher.printOS();
		System.out.println(C8Const.INVALID);
		System.exit(1);
	}
	private static void launch(String[] args) {
		Display.createDisplay(args);
	}
	private void extract() throws IOException {
		File output = new File("ipwndfu.zip");
		InputStream is = this.getClass().getClassLoader().getResourceAsStream("ipwndfu.zip");
		FileOutputStream fos = new FileOutputStream(output);
		byte[] buffer = new byte[4096];
		int read;
		while ((read = is.read(buffer)) > 0) {
			fos.write(buffer, 0, read);
			fos.flush();
		}
		fos.close();
		try {
			String source = System.getProperty("user.dir") + "/ipwndfu.zip";
			String destination = System.getProperty("user.dir") + "/ipwndfu";
			ZipFile file = new ZipFile(source);
			file.extractAll(destination);
		} catch (ZipException ex) {
			ex.printStackTrace();
			System.out.println(C8Const.ZIP_ERROR);
		}
		File ipwndfu = new File(System.getProperty("user.dir") + "/ipwndfu.zip");
		ipwndfu.delete();
	}
}