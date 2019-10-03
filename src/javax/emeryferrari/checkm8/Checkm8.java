package javax.emeryferrari.checkm8;
import java.io.*;
public class Checkm8 {
	private Checkm8() {}
	public static final InputStream exploitDevice() throws IOException {
		return Checkm8.runCommand(C8Const.EXPLOIT_DEVICE_COMMAND);
	}
	public static final InputStream dumpSecureRom() throws IOException {
		// not sure where the files are saved
		// if you dump SecureROM, the files will have to be found by the user
		return Checkm8.runCommand(C8Const.DUMP_SECUREROM_COMMAND);
	}
	public static final InputStream decryptKeybag(String keybag) throws IOException {
		// NOT IMPLEMENTED
		return null;
	}
	public static final InputStream demoteDevice() throws IOException {
		return Checkm8.runCommand(C8Const.DEMOTE_DEVICE_COMMAND);
	}
	public static final InputStream verboseBoot() throws IOException {
		return Checkm8.runCommand(C8Const.VERBOSE_BOOT_COMMAND);
	}
	public static final InputStream bootLogo() throws IOException {
		// NOT IMPLEMENTED
		return null;
	}
	public static final InputStream restoreFirmwareSign() throws IOException {
		// NOT IMPLEMENTED
		// when this feature is implemented, this method will not be no-args
		return null;
	}
	public static final InputStream restoreFirmwareUnsign() throws IOException {
		// NOT IMPLEMENTED
		// when this feature is implemented, this method will not be no-args
		return null;
	}
	private static final InputStream runCommand(String command) throws IOException {
		Process process = Runtime.getRuntime().exec(command);
		return process.getInputStream();
	}
}