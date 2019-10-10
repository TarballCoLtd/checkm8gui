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
	private static final JButton ABOUT = new JButton(C8Const.ABOUT);
	private static final JButton OK = new JButton(C8Const.OK);
	private static final JLabel NEWLINE = new JLabel(C8Const.NEWLINE);
	private static final JLabel EXPLOITING_LABEL = new JLabel(C8Const.EXPLOITING_LABEL);
	private static JPanel exploitOutput = new JPanel();
	private static final JButton BACK = new JButton(C8Const.BACK);
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
		Display.ABOUT.addActionListener(CLASS_OBJECT.new About());
		Display.OK.addActionListener(CLASS_OBJECT.new OK());
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
		Display.PANEL.add(Display.ABOUT);
		
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
				if (Display.args[i].equals(C8Const.VERBOSE_FLAG_1)) {
					verbose = true;
				} else if (Display.args[i].equals(C8Const.VERBOSE_FLAG_2)) {
					verbose = true;
				}
			}
			if (verbose) {
				Display.exploitOutput.add(new JLabel("OS: " + System.getProperty(C8Const.OS_NAME)));
				Display.exploitOutput.add(new JLabel(C8Const.TERMINAL_HEADER + C8Const.EXPLOIT_DEVICE_COMMAND));
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
				if (Display.args[i].equals(C8Const.VERBOSE_FLAG_1)) {
					verbose = true;
				} else if (Display.args[i].equals(C8Const.VERBOSE_FLAG_2)) {
					verbose = true;
				}
			}
			if (verbose) {
				Display.exploitOutput.add(new JLabel("OS: " + System.getProperty(C8Const.OS_NAME)));
				Display.exploitOutput.add(new JLabel(C8Const.TERMINAL_HEADER + C8Const.DUMP_SECUREROM_COMMAND));
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
			int result1 = JOptionPane.showOptionDialog(null, C8Const.WARNING_1, C8Const.WARNING_TITLE, JOptionPane.WARNING_MESSAGE, 0, null, C8Const.BUTTONS_1, C8Const.BUTTONS_2[1]);
			if (result1 == 0 ) {
				int result2 = JOptionPane.showOptionDialog(null, C8Const.WARNING_2, C8Const.WARNING_TITLE, JOptionPane.WARNING_MESSAGE, 0, null, C8Const.BUTTONS_2, C8Const.BUTTONS_2[1]);
				if (result2 == 0) {
					Display.exploitOutput = new JPanel();
					Display.exploitOutput.setLayout(new BoxLayout(Display.exploitOutput, BoxLayout.Y_AXIS));
					Display.FRAME.getContentPane().remove(Display.PANEL);
					Display.FRAME.getContentPane().add(BorderLayout.WEST, Display.exploitOutput);
					Display.exploitOutput.add(Display.EXPLOITING_LABEL);
					Display.refresh();
					boolean verbose = false;
					for (int i = 0; i < Display.args.length; i++) {
						if (Display.args[i].equals(C8Const.VERBOSE_FLAG_1)) {
							verbose = true;
						} else if (Display.args[i].equals(C8Const.VERBOSE_FLAG_2)) {
							verbose = true;
						}
					}
					if (verbose) {
						Display.exploitOutput.add(new JLabel("OS: " + System.getProperty(C8Const.OS_NAME)));
						Display.exploitOutput.add(new JLabel(C8Const.TERMINAL_HEADER + C8Const.DEMOTE_DEVICE_COMMAND));
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
				if (Display.args[i].equals(C8Const.VERBOSE_FLAG_1)) {
					verbose = true;
				} else if (Display.args[i].equals(C8Const.VERBOSE_FLAG_2)) {
					verbose = true;
				}
			}
			if (verbose) {
				Display.exploitOutput.add(new JLabel("OS: " + System.getProperty(C8Const.OS_NAME)));
				Display.exploitOutput.add(new JLabel(C8Const.TERMINAL_HEADER + C8Const.VERBOSE_BOOT_COMMAND));
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
	public class Back implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
			Display.FRAME.getContentPane().remove(Display.exploitOutput);
			Display.renderDisplay();
		}
	}
}