package javax.emeryferrari.checkm8;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
public class Display {
	private static String[] args = {};
	private static final Display CLASS_OBJECT = new Display();
	private static final JFrame FRAME = new JFrame(C8Const.APPLICATION_NAME + " v" + C8Const.APPLICATION_VERSION);
	private static final JPanel PANEL = new JPanel();
	private static final JLabel TITLE = new JLabel(C8Const.TITLE);
	private static final JLabel SUBTITLE = new JLabel(C8Const.SUBTITLE);
	private static final JButton EXPLOIT_DEVICE = new JButton(C8Const.EXPLOIT_DEVICE);
	private static final JButton DUMP_SECUREROM = new JButton(C8Const.DUMP_SECUREROM);
	private static final JButton DECRYPT_KEYBAG = new JButton(C8Const.DECRYPT_KEYBAG);
	private static final JButton DEMOTE_DEVICE = new JButton(C8Const.DEMOTE_DEVICE);
	private static final JButton VERBOSE_BOOT = new JButton(C8Const.VERBOSE_BOOT);
	private static final JButton BOOT_LOGO = new JButton(C8Const.BOOT_LOGO);
	private static final JButton RESTORE_FIRMWARE_SIGN = new JButton(C8Const.RESTORE_FIRMWARE_SIGN);
	private static final JButton RESTORE_FIRMWARE_UNSIGN = new JButton(C8Const.RESTORE_FIRMWARE_UNSIGN);
	private static final JButton CUSTOM_IPWNDFU_FLAGS = new JButton(C8Const.CUSTOM_IPWNDFU_FLAGS);
	private static final JButton SETTINGS = new JButton(C8Const.SETTINGS);
	private static final JButton ABOUT = new JButton(C8Const.ABOUT);
	private static final JButton OK = new JButton(C8Const.OK);
	private static final JLabel NEWLINE = new JLabel("<html><body><br/>");
	private static final JLabel EXPLOITING_LABEL = new JLabel("Running exploit...");
	private static JPanel exploitOutput = new JPanel();
	private static final JLabel IPWNDFU_FOLDER_LABEL = new JLabel("ipwndfu Folder: ");
	private static final JTextField IPWNDFU_FOLDER = new JTextField(50);
	private static final JButton SAVE_RETURN = new JButton("Save Changes and Return");
	private static final JButton BACK = new JButton("Back");
	private Display() {}
	public static void createDisplay(String[] args) {
		Display.args = args;
		
		Display.exploitOutput.setLayout(new BoxLayout(Display.exploitOutput, BoxLayout.Y_AXIS));
		
		Display.FRAME.setSize(800, 600);
		Display.FRAME.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Display.DECRYPT_KEYBAG.setEnabled(false);
		Display.BOOT_LOGO.setEnabled(false);
		Display.RESTORE_FIRMWARE_SIGN.setEnabled(false);
		Display.RESTORE_FIRMWARE_UNSIGN.setEnabled(false);
		Display.CUSTOM_IPWNDFU_FLAGS.setEnabled(false);
		
		Display.EXPLOIT_DEVICE.addActionListener(CLASS_OBJECT.new ExploitDevice());
		Display.DUMP_SECUREROM.addActionListener(CLASS_OBJECT.new DumpSecureRom());
		Display.DECRYPT_KEYBAG.addActionListener(CLASS_OBJECT.new DecryptKeybag());
		Display.DEMOTE_DEVICE.addActionListener(CLASS_OBJECT.new DemoteDevice());
		Display.VERBOSE_BOOT.addActionListener(CLASS_OBJECT.new VerboseBoot());
		Display.BOOT_LOGO.addActionListener(CLASS_OBJECT.new BootLogo());
		Display.RESTORE_FIRMWARE_SIGN.addActionListener(CLASS_OBJECT.new RestoreFirmwareSign());
		Display.RESTORE_FIRMWARE_UNSIGN.addActionListener(CLASS_OBJECT.new RestoreFirmwareUnsign());
		Display.CUSTOM_IPWNDFU_FLAGS.addActionListener(CLASS_OBJECT.new CustomIpwndfuFlags());
		Display.SETTINGS.addActionListener(CLASS_OBJECT.new Settings());
		Display.ABOUT.addActionListener(CLASS_OBJECT.new About());
		Display.OK.addActionListener(CLASS_OBJECT.new OK());
		Display.IPWNDFU_FOLDER.addActionListener(CLASS_OBJECT.new IpwndfuFolder());
		Display.SAVE_RETURN.addActionListener(CLASS_OBJECT.new SaveReturn());
		Display.BACK.addActionListener(CLASS_OBJECT.new Back());
		
		Display.PANEL.setLayout(new BoxLayout(Display.PANEL, BoxLayout.Y_AXIS));
		Display.PANEL.add(Display.TITLE);
		Display.PANEL.add(Display.SUBTITLE);
		Display.PANEL.add(Display.EXPLOIT_DEVICE);
		Display.PANEL.add(Display.DUMP_SECUREROM);
		Display.PANEL.add(Display.DECRYPT_KEYBAG);
		Display.PANEL.add(Display.DEMOTE_DEVICE);
		Display.PANEL.add(Display.VERBOSE_BOOT);
		Display.PANEL.add(Display.BOOT_LOGO);
		Display.PANEL.add(Display.RESTORE_FIRMWARE_SIGN);
		Display.PANEL.add(Display.RESTORE_FIRMWARE_UNSIGN);
		Display.PANEL.add(Display.CUSTOM_IPWNDFU_FLAGS);
		Display.PANEL.add(Display.SETTINGS);
		Display.PANEL.add(Display.ABOUT);
		
		Display.IPWNDFU_FOLDER.setMaximumSize(Display.IPWNDFU_FOLDER.getPreferredSize());
		
		Display.renderDisplay();
	}
	public static void renderDisplay() {
		Display.FRAME.setVisible(true);
		Display.FRAME.getContentPane().add(BorderLayout.WEST, Display.PANEL);
		Display.refresh();
	}
	public static final void refresh() {
		Display.FRAME.revalidate();
		Display.FRAME.repaint();
	}
	public class ExploitDevice implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
			Display.exploitOutput = new JPanel();
			Display.exploitOutput.setLayout(new BoxLayout(Display.exploitOutput, BoxLayout.Y_AXIS));
			Display.FRAME.getContentPane().remove(Display.PANEL);
			Display.FRAME.getContentPane().add(BorderLayout.WEST, Display.exploitOutput);
			Display.exploitOutput.add(Display.EXPLOITING_LABEL);
			Display.refresh();
			boolean verbose = false;
			for (int i = 0; i < Display.args.length; i++) {
				if (Display.args[i].equals("-v")) {
					verbose = true;
				} else if (Display.args[i].equals("-verbose")) {
					verbose = true;
				}
			}
			if (verbose) {
				Display.exploitOutput.add(new JLabel("OS: " + System.getProperty("os.name")));
				Display.exploitOutput.add(new JLabel("terminal > " + C8Const.EXPLOIT_DEVICE_COMMAND));
				Display.refresh();
			}
			Display.exploitOutput.add(Display.NEWLINE);
			Display.refresh();
			Thread thread = new Thread() {
				@Override
				public void run() {
					try {
						InputStream is = Checkm8.exploitDevice();
						BufferedReader reader = new BufferedReader(new InputStreamReader(is));
						String string = null;
						while ((string = reader.readLine()) != null) {
							Display.exploitOutput.add(new JLabel(string));
							Display.refresh();
						}
						Display.exploitOutput.add(Display.NEWLINE);
						Display.exploitOutput.add(Display.OK);
						Display.refresh();
					} catch (IOException ex) {
						System.out.println(C8Const.CONSOLE_ERROR + "\n\n");
						ex.printStackTrace();
						System.exit(1);
					}
				}
			};
			thread.start();
		}
	}
	public class DumpSecureRom implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
			Display.exploitOutput = new JPanel();
			Display.exploitOutput.setLayout(new BoxLayout(Display.exploitOutput, BoxLayout.Y_AXIS));
			Display.FRAME.getContentPane().remove(Display.PANEL);
			Display.FRAME.getContentPane().add(BorderLayout.WEST, Display.exploitOutput);
			Display.exploitOutput.add(Display.EXPLOITING_LABEL);
			Display.refresh();
			boolean verbose = false;
			for (int i = 0; i < Display.args.length; i++) {
				if (Display.args[i].equals("")) {
					verbose = true;
				} else if (Display.args[i].equals("")) {
					verbose = true;
				}
			}
			if (verbose) {
				Display.exploitOutput.add(new JLabel("OS: " + System.getProperty("os.name")));
				Display.exploitOutput.add(new JLabel("terminal > " + C8Const.DUMP_SECUREROM_COMMAND));
				Display.refresh();
			}
			Display.exploitOutput.add(Display.NEWLINE);
			Display.refresh();
			Thread thread = new Thread() {
				@Override
				public void run() {
					try {
						InputStream is = Checkm8.dumpSecureRom();
						BufferedReader reader = new BufferedReader(new InputStreamReader(is));
						String string = null;
						while ((string = reader.readLine()) != null) {
							Display.exploitOutput.add(new JLabel(string));
							Display.refresh();
						}
						Display.exploitOutput.add(Display.NEWLINE);
						Display.exploitOutput.add(Display.OK);
						Display.refresh();
					} catch (IOException ex) {
						System.out.println(C8Const.CONSOLE_ERROR + "\n\n");
						ex.printStackTrace();
						System.exit(1);
					}
				}
			};
			thread.start();
		}
	}
	public class DecryptKeybag implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
			// NOT IMPLEMENTED
		}
	}
	public class DemoteDevice implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
			String[] buttons1 = {"Yes, I know what I'm doing", "Cancel"};
			String[] buttons2 = {"Yes", "No"};
			int result1 = JOptionPane.showOptionDialog(null, "WARNING: Unless you know what demoting your device means and what JTAG is, do not complete this operation. It is not reversible.", "checkm8gui Warning", JOptionPane.WARNING_MESSAGE, 0, null, buttons1, buttons1[1]);
			if (result1 == 0 ) {
				int result2 = JOptionPane.showOptionDialog(null, "Are you sure? You cannot undo this action.", "checkm8gui Warning", JOptionPane.WARNING_MESSAGE, 0, null, buttons2, buttons2[1]);
				if (result2 == 0) {
					Display.exploitOutput = new JPanel();
					Display.exploitOutput.setLayout(new BoxLayout(Display.exploitOutput, BoxLayout.Y_AXIS));
					Display.FRAME.getContentPane().remove(Display.PANEL);
					Display.FRAME.getContentPane().add(BorderLayout.WEST, Display.exploitOutput);
					Display.exploitOutput.add(Display.EXPLOITING_LABEL);
					Display.refresh();
					boolean verbose = false;
					for (int i = 0; i < Display.args.length; i++) {
						if (Display.args[i].equals("")) {
							verbose = true;
						} else if (Display.args[i].equals("")) {
							verbose = true;
						}
					}
					if (verbose) {
						Display.exploitOutput.add(new JLabel("OS: " + System.getProperty("os.name")));
						Display.exploitOutput.add(new JLabel("terminal > " + C8Const.DEMOTE_DEVICE_COMMAND));
						Display.refresh();
					}
					Display.exploitOutput.add(Display.NEWLINE);
					Display.refresh();
					Thread thread = new Thread() {
						@Override
						public void run() {
							try {
								InputStream is = Checkm8.demoteDevice();
								BufferedReader reader = new BufferedReader(new InputStreamReader(is));
								String string = null;
								while ((string = reader.readLine()) != null) {
									Display.exploitOutput.add(new JLabel(string));
									Display.refresh();
								}
								Display.exploitOutput.add(Display.NEWLINE);
								Display.exploitOutput.add(Display.OK);
								Display.refresh();
							} catch (IOException ex) {
								System.out.println(C8Const.CONSOLE_ERROR + "\n\n");
								ex.printStackTrace();
								System.exit(1);
							}
						}
					};
					thread.start();
				}
			}
		}
	}
	public class VerboseBoot implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
			Display.exploitOutput = new JPanel();
			Display.exploitOutput.setLayout(new BoxLayout(Display.exploitOutput, BoxLayout.Y_AXIS));
			Display.FRAME.getContentPane().remove(Display.PANEL);
			Display.FRAME.getContentPane().add(BorderLayout.WEST, Display.exploitOutput);
			Display.exploitOutput.add(Display.EXPLOITING_LABEL);
			Display.refresh();
			boolean verbose = false;
			for (int i = 0; i < Display.args.length; i++) {
				if (Display.args[i].equals("")) {
					verbose = true;
				} else if (Display.args[i].equals("")) {
					verbose = true;
				}
			}
			if (verbose) {
				Display.exploitOutput.add(new JLabel("OS: " + System.getProperty("os.name")));
				Display.exploitOutput.add(new JLabel("terminal > " + C8Const.VERBOSE_BOOT_COMMAND));
				Display.refresh();
			}
			Display.exploitOutput.add(Display.NEWLINE);
			Display.refresh();
			Thread thread = new Thread() {
				@Override
				public void run() {
					try {
						InputStream is = Checkm8.verboseBoot();
						BufferedReader reader = new BufferedReader(new InputStreamReader(is));
						String string = null;
						while ((string = reader.readLine()) != null) {
							Display.exploitOutput.add(new JLabel(string));
							Display.refresh();
						}
						Display.exploitOutput.add(Display.NEWLINE);
						Display.exploitOutput.add(Display.OK);
						Display.refresh();
					} catch (IOException ex) {
						System.out.println(C8Const.CONSOLE_ERROR + "\n\n");
						ex.printStackTrace();
						System.exit(1);
					}
				}
			};
			thread.start();
		}
	}
	public class BootLogo implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
			// NOT IMPLEMENTED
		}
	}
	public class RestoreFirmwareSign implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
			// NOT IMPLEMENTED
		}
	}
	public class RestoreFirmwareUnsign implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
			// NOT IMPLEMENTED
		}
	}
	public class CustomIpwndfuFlags implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
			// will be implemented shortly after the first release
		}
	}
	public class Settings implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
			Display.IPWNDFU_FOLDER.setText(Checkm8Launcher.ipwndfuFolder);
			Display.exploitOutput = new JPanel();
			Display.exploitOutput.setLayout(new BoxLayout(Display.exploitOutput, BoxLayout.Y_AXIS));
			JPanel inner1 = new JPanel();
			inner1.setLayout(new BoxLayout(inner1, BoxLayout.X_AXIS));
			inner1.add(Display.IPWNDFU_FOLDER_LABEL);
			inner1.add(Display.IPWNDFU_FOLDER);
			Display.exploitOutput.add(inner1);
			Display.exploitOutput.add(Display.NEWLINE);
			Display.exploitOutput.add(Display.SAVE_RETURN);
			Display.FRAME.getContentPane().remove(Display.PANEL);
			Display.FRAME.getContentPane().add(BorderLayout.WEST, Display.exploitOutput);
			Display.refresh();
		}
	}
	public class About implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
			Display.exploitOutput = new JPanel();
			Display.exploitOutput.setLayout(new BoxLayout(Display.exploitOutput, BoxLayout.Y_AXIS));
			Display.exploitOutput.add(new JLabel("checkm8gui"));
			Display.exploitOutput.add(new JLabel("programmed by @emeryferrari"));
			Display.exploitOutput.add(new JLabel("More information available in the readme at github.com/emeryferrari/checkm8gui"));
			Display.exploitOutput.add(Display.NEWLINE);
			Display.exploitOutput.add(Display.BACK);
			Display.FRAME.getContentPane().remove(Display.PANEL);
			Display.FRAME.getContentPane().add(BorderLayout.WEST, Display.exploitOutput);
			Display.refresh();
		}
	}
	public class OK implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
			Display.FRAME.getContentPane().remove(Display.exploitOutput);
			Display.renderDisplay();
		}
	}
	public class IpwndfuFolder implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
			Checkm8Launcher.ipwndfuFolder = Display.IPWNDFU_FOLDER.getText();
		}
	}
	public class SaveReturn implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
			Checkm8Launcher.ipwndfuFolder = Display.IPWNDFU_FOLDER.getText();
			if (new File(C8Const.SETTINGS_FILE).delete()) {
				try {
					FileOutputStream fos = new FileOutputStream(C8Const.SETTINGS_FILE);
					String write = "ipwndfu-directory=" + Checkm8Launcher.ipwndfuFolder;
					fos.write(write.getBytes());
					fos.flush();
					fos.close();
				} catch (IOException ex) {
					ex.printStackTrace();
					System.exit(1);
				}
				Display.FRAME.getContentPane().remove(Display.exploitOutput);
				Display.renderDisplay();
			} else {
				System.out.println("Unexpected error occurred while trying to delete checkm8gui.bin.");
				System.exit(1);
			}
		}
	}
	public class Back implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
			Display.FRAME.getContentPane().remove(Display.exploitOutput);
			Display.renderDisplay();
		}
	}
}