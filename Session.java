import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

public class Session {

	public static void main(String[] args) {
		Document current = new Document(); // create a document object
		Document linebuffer = new Document(); // create another document object
												// to use as the line buffer
		String file = ""; // initialize an empty string to hold a file name
		int count = 1; // initialize an integer at 1 to keep track of lines
		Scanner in = new Scanner(System.in); // open a scanner to read in input
												// from the terminal

		if (args.length != 0) { // if there are command line arguments
			file = args[0]; // set file to the first argument
			// attempt to read in the file
			try {
				BufferedReader reader = new BufferedReader(new FileReader(file));
				current = new Document(); // clear the existing document
				String text = reader.readLine(); // set text to the first line
													// in the file
				while (text != null) { // if there is text
					current.insert(text); // add the text to the document
					text = reader.readLine(); // set text to the next line in
												// the file
				}
				reader.close();
			} catch (Exception e) {
				System.out.println("File doesn't exist."); // print error if
															// there is an
															// exception
			}
		}
		// print the menu
		System.out.println("   Menu:  m          Delete line:  dl");
		System.out.println("   Load file:  l     Delete range:  dr");
		System.out.println("   Show all:  sa     Copy range:  cr");
		System.out.println("   Show line:  sl    Paste lines:  pl");
		System.out.println("   Show range:  sr   Write to file:  w");
		System.out.println("   New line:  nl     Quit:  q");
		System.out.println("   Edit line:  el    Write and quit:  wq");
		// loop until the program is ended
		while (true) {
			// print the cursor
			System.out.println("->");
			String entry;
			if (in.hasNextLine()) {
				entry = in.nextLine(); // set entry to the text entered
				entry.trim();
				// print the menu
				if (entry.equals("m")) {
					System.out.println("   Menu:  m          Delete line:  dl");
					System.out.println("   Load file:  l     Delete range:  dr");
					System.out.println("   Show all:  sa     Copy range:  cr");
					System.out.println("   Show line:  sl    Paste lines:  pl");
					System.out.println("   Show range:  sr   Write to file:  w");
					System.out.println("   New line:  nl     Quit:  q");
					System.out.println("   Edit line:  el    Write and quit:  wq");
				} else if (entry.equals("l")) {
					System.out.println("Enter a text file to read in.");
					if (in.hasNextLine()) {
						file = in.nextLine(); // set file to the text entered
						file.trim();
						// attempt to read in the file
						try {
							BufferedReader reader = new BufferedReader(new FileReader(file));
							current = new Document(); // clear the existing
														// document
							String text = reader.readLine(); // set text to the
																// first line in
																// the file
							while (text != null) { // if there is text
								current.insert(text); // add the text to the
														// document
								text = reader.readLine(); // set text to the
															// next line in the
															// file
							}
							reader.close();
						} catch (Exception e) {
							System.out.println("File doesn't exist."); // print
																		// error
																		// if
																		// there
																		// is an
																		// exception
						}
					}
				}
				// print the entire document
				else if (entry.equals("sa")) {
					if (current.length() == 0) { // nothing to print if document
													// is empty
						System.out.println("The document is empty.");
					} else {
						current.print(); // call document print method
					}
				}
				// print specified line
				else if (entry.equals("sl")) {
					if (current.length() == 0) { // nothing to print if document
													// is empty
						System.out.println("The document is empty.");
					} else {
						String linenum = " ";
						// loop until a valid line number is entered
						while (true) {
							System.out.println("Enter a line number to show.");
							if (in.hasNextLine()) {
								linenum = in.nextLine(); // set linenum to the
															// text entered
								linenum.trim();
								// attempt to convert the string to an integer
								try {
									// check if the line entered is in the
									// document
									if (Integer.parseInt(linenum) <= 0) {
										System.out.println("Line out of range.");
									} else if (Integer.parseInt(linenum) > current.length()) {
										System.out.println("Line out of range.");
									} else {
										break; // valid line number, exit the
												// loop
									}
								} catch (Exception e) {
									System.out.println("Not a number."); // print
																			// error
																			// if
																			// there
																			// is
																			// an
																			// exception
								}
							}
						}
						current.printLine(Integer.parseInt(linenum)); // call
																		// document
																		// printLine
																		// method
					}
				}
				// print a specified range of lines
				else if (entry.equals("sr")) {
					if (current.length() == 0) { // nothing to print if document
													// is empty
						System.out.println("The document is empty.");
					} else {
						String linefrom = " ";
						String lineto = " ";
						// loop until a valid line number is entered
						while (true) {
							System.out.println("Enter a line to show from.");
							if (in.hasNextLine()) {
								linefrom = in.nextLine(); // set linefrom to the
															// text entered
								linefrom.trim();
								// attempt to convert the string to an integer
								try {
									// check if the line entered is in the
									// document
									if (Integer.parseInt(linefrom) <= 0) {
										System.out.println("Line out of range.");
									} else if (Integer.parseInt(linefrom) > current.length()) {
										System.out.println("Line out of range.");
									} else {
										break; // valid line number, exit the
												// loop
									}
								} catch (Exception e) {
									System.out.println("Not a number."); // print
																			// error
																			// if
																			// there
																			// is
																			// an
																			// exception
								}
							}
						}
						// loop until a valid line number is entered
						while (true) {
							System.out.println("Enter a line to show to.");
							if (in.hasNextLine()) {
								lineto = in.nextLine(); // set lineto to the
														// text entered
								lineto.trim();
								// attempt to convert the string to an integer
								try {
									// check if lineto is at or after linefrom
									if (Integer.parseInt(lineto) < Integer.parseInt(linefrom)) {
										System.out.println("Enter a line at or after: " + linefrom + ".");
									} else {
										break; // valid line number, exit the
												// loop
									}
								} catch (Exception e) {
									System.out.println("Not a number."); // print
																			// error
																			// if
																			// there
																			// is
																			// an
																			// exception
								}
							}
						}
						current.printRange(Integer.parseInt(linefrom), Integer.parseInt(lineto)); // call
																									// document
																									// printRange
																									// method
					}
				}
				// add a new line to the document
				else if (entry.equals("nl")) {
					// if there are no lines in the document
					if (current.isEmpty()) {
						count = 1;
						// loop until n is entered
						while (true) {
							System.out.println("Type line? (y/n):");
							String answer;
							if (in.hasNextLine()) {
								answer = in.nextLine(); // set answer to text
														// entered
								answer.trim();
								if (answer.equals("y")) {
									System.out.println(count + ":"); // print
																		// the
																		// line
																		// number
									count++;
									// loop until the text is 101 characters or
									// less
									while (true) {
										String text = in.nextLine(); // set text
																		// to
																		// the
																		// text
																		// entered
										if (text.length() > 101) { // check if
																	// text is a
																	// valid
																	// length
											System.out.println("Enter a shorter sentence.");
										} else {
											current.insert(text); // call
																	// document
																	// insert
																	// method
											break; // valid text length, exit
													// loop
										}
									}
								} else if (answer.equals("n")) {
									break; // return to main menu
								} else {
									System.out.println("Invalid answer."); // error
																			// message
																			// if
																			// answer
																			// is
																			// not
																			// y/n
								}
							}
						}
					}
					// if there are lines in the document
					else {
						String entrypoint = " ";
						// loop until valid line number is entered
						while (true) {
							System.out.println("Insert after line number:");
							if (in.hasNextLine()) {
								entrypoint = in.nextLine(); // set entrypoint to
															// the text entered
								entrypoint.trim();
								// attempt to convert the string to an integer
								try {
									// check if entrypoint is in the document
									if (Integer.parseInt(entrypoint) < 0) {
										System.out.println("Line out of range.");
									} else if (Integer.parseInt(entrypoint) > current.length()) {
										System.out.println("Line out of range.");
									} else {
										break; // valid line number, exit loop
									}
								} catch (Exception e) {
									System.out.println("Not a number."); // print
																			// error
																			// if
																			// there
																			// is
																			// an
																			// exception
								}
							}
						}
						count = Integer.parseInt(entrypoint); // set count to
																// entrypoint
						if (count == 0) { // if count is 0, inserting before the
											// first line
							System.out.println("Inserting before:");
							current.printText(1); // print the text of the first
													// line with document
													// printText method
						} else {
							System.out.println("Inserting after:");
							current.printText(count); // print text of the line
														// entered with document
														// printText method
						}
						count++; // increment count to start adding text at the
									// next line
						// loop until a valid answer is entered
						while (true) {
							System.out.println("Type line? (y/n):");
							String answer;
							if (in.hasNextLine()) {
								answer = in.nextLine(); // set answer to text
														// entered
								answer.trim();
								if (answer.equals("y")) {
									System.out.println(count + ":"); // print
																		// the
																		// line
																		// number
									count++;
									// loop until text is 101 characters or less
									while (true) {
										String text = in.nextLine(); // set text
																		// to
																		// text
																		// entered
										if (text.length() > 101) { // check if
																	// text is a
																	// valid
																	// length
											System.out.println("Enter a shorter sentence.");
										} else {
											current.insertAfter(text, (count - 1)); // call
																					// document
																					// insertAfter
																					// method
																					// with
																					// count
																					// -
																					// 1
																					// to
																					// account
																					// for
																					// increment
																					// for
																					// printing
											break; // valid text length, exit
													// loop
										}
									}
								} else if (answer.equals("n")) {
									break; // return to main menu
								} else {
									System.out.println("Invalid answer."); // print
																			// error
																			// if
																			// there
																			// is
																			// an
																			// exception
								}
							}
						}
					}
				}
				// edit an existing line in the document
				else if (entry.equals("el")) {
					if (current.length() == 0) { // nothing to edit if document
													// length is 0
						System.out.println("The document is empty.");
					} else {
						String linenum = " ";
						// loop until a valid line number is entered
						while (true) {
							System.out.println("Enter a line number to edit.");
							if (in.hasNextLine()) {
								linenum = in.nextLine(); // set linenum to the
															// text entered
								linenum.trim();
								// attempt to convert the string to an integer
								try {
									// check if linenum is in the document
									if (Integer.parseInt(linenum) <= 0) {
										System.out.println("Line out of range.");
									} else if (Integer.parseInt(linenum) > current.length()) {
										System.out.println("Line out of range.");
									} else {
										break; // valid line number, exit loop
									}
								} catch (Exception e) {
									System.out.println("Not a number."); // print
																			// error
																			// if
																			// there
																			// is
																			// an
																			// exception
								}
							}
						}
						current.showLine(Integer.parseInt(linenum)); // display
																		// line
																		// and
																		// scale
																		// with
																		// document
																		// showLine
																		// method
						// loop until line is exited
						while (true) {
							// display line edit menu
							System.out.println("   Show line:  s");
							System.out.println("   Copy to string buffer:  c");
							System.out.println("   Cut:  t");
							System.out.println("   Paste from string buffer:  p");
							System.out.println("   Enter new substring:  e");
							System.out.println("   Delete substring:  d");
							System.out.println("   Quit line:  q");
							System.out.println("->");
							String subentry;
							if (in.hasNextLine()) {
								subentry = in.nextLine(); // set subentry to the
															// text entered
								subentry.trim();
								// display the line and scale
								if (subentry.equals("s")) {
									current.showLine(Integer.parseInt(linenum)); // call
																					// document
																					// showLine
																					// method
								}
								// copy a substring from the line to the string
								// buffer
								else if (subentry.equals("c")) {
									if (current.getText(Integer.parseInt(linenum)).length() == 0) { // nothing
																									// to
																									// copy
																									// if
																									// line
																									// length
																									// is
																									// 0
										System.out.println("There is nothing to copy.");
									} else {
										String from = " ";
										String to = " ";
										current.showLine(Integer.parseInt(linenum)); // call
																						// document
																						// showLine
																						// method
										// loop until a valid position number is
										// entered
										while (true) {
											System.out.println("Enter a from position.");
											if (in.hasNextLine()) {
												from = in.nextLine(); // set
																		// from
																		// to
																		// the
																		// text
																		// entered
												from.trim();
												// attempt to convert string to
												// an integer
												try {
													// check if from is in the
													// line
													if (Integer.parseInt(from) < 0) {
														System.out.println("Position out of range.");
													} else if (Integer.parseInt(
															from) > (current.getText(Integer.parseInt(linenum)).length()
																	- 1)) {
														System.out.println("Position out of range.");
													} else {
														break; // valid
																// position,
																// exit loop
													}
												} catch (Exception e) {
													System.out.println("Not a number."); // print
																							// error
																							// if
																							// there
																							// is
																							// an
																							// exception
												}
											}
										}
										// loop until a valid position is
										// entered
										while (true) {
											System.out.println("Enter a to position.");
											if (in.hasNextLine()) {
												to = in.nextLine(); // set to to
																	// the text
																	// entered
												to.trim();
												// attempt to convert the string
												// to an integer
												try {
													// check if to is at or
													// after from and in the
													// line
													if (Integer.parseInt(to) < Integer.parseInt(from)) {
														System.out
																.println("Enter a position at or after: " + from + ".");
													} else if (Integer.parseInt(
															to) > (current.getText(Integer.parseInt(linenum)).length()
																	- 1)) {
														System.out.println("Enter a position at or before: "
																+ (current.getText(Integer.parseInt(linenum)).length()
																		- 1)
																+ ".");
													} else {
														break; // valid
																// position,
																// exit loop
													}
												} catch (Exception e) {
													System.out.println("Not a number."); // print
																							// error
																							// if
																							// there
																							// is
																							// an
																							// exception
												}
											}
										}
										current.setSbuff(Integer.parseInt(from), Integer.parseInt(to),
												Integer.parseInt(linenum)); // copy
																			// the
																			// substring
																			// to
																			// the
																			// string
																			// buffer
																			// with
																			// document
																			// setSbuff
																			// method
										System.out.println("Copied:");
										current.showLine(Integer.parseInt(linenum)); // display
																						// the
																						// line
																						// and
																						// scale
										current.printSelection(Integer.parseInt(from), Integer.parseInt(to),
												Integer.parseInt(linenum)); // display
																			// the
																			// copied
																			// selection
																			// with
																			// document
																			// printSelection
																			// method
									}
								}
								// remove a substring from the line and copy it
								// to the string buffer
								else if (subentry.equals("t")) {
									if (current.getText(Integer.parseInt(linenum)).length() == 0) { // nothing
																									// to
																									// cut
																									// if
																									// line
																									// length
																									// is
																									// 0
										System.out.println("There is nothing to cut.");
									} else {
										String from = " ";
										String to = " ";
										boolean cond = true; // extra boolean
																// for specific
																// loop
																// condition
										// loop until substring is cut
										while (cond) {
											current.showLine(Integer.parseInt(linenum)); // display
																							// the
																							// line
																							// and
																							// scale
											// loop until a valid position is
											// entered
											while (true) {
												System.out.println("Enter a from position.");
												if (in.hasNextLine()) {
													from = in.nextLine(); // set
																			// from
																			// to
																			// the
																			// text
																			// entered
													from.trim();
													// attempt to convert the
													// string to an integer
													try {
														// check if from is in
														// the line
														if (Integer.parseInt(from) < 0) {
															System.out.println("Position out of range.");
														} else if (Integer.parseInt(from) > (current
																.getText(Integer.parseInt(linenum)).length() - 1)) {
															System.out.println("Position out of range.");
														} else {
															break; // valid
																	// position,
																	// exit loop
														}
													} catch (Exception e) {
														System.out.println("Not a number."); // print
																								// error
																								// if
																								// there
																								// is
																								// an
																								// exception
													}
												}
											}
											// loop until a valid position is
											// entered
											while (true) {
												System.out.println("Enter a to position.");
												if (in.hasNextLine()) {
													to = in.nextLine();
													to.trim();
													// attempt to convert the
													// string to an integer
													try {
														// check if to is at or
														// after from and in the
														// line
														if (Integer.parseInt(to) < Integer.parseInt(from)) {
															System.out.println(
																	"Enter a position at or after: " + from + ".");
														} else if (Integer.parseInt(to) > (current
																.getText(Integer.parseInt(linenum)).length() - 1)) {
															System.out.println("Enter a position at or before: "
																	+ (current.getText(Integer.parseInt(linenum))
																			.length() - 1)
																	+ ".");
														} else {
															break; // valid
																	// position,
																	// exit loop
														}
													} catch (Exception e) {
														System.out.println("Not a number."); // print
																								// error
																								// if
																								// there
																								// is
																								// an
																								// exception
													}
												}
											}
											// loop until a valid answer is
											// entered
											while (true) {
												System.out.println("Cut this substring?");
												current.showLine(Integer.parseInt(linenum)); // display
																								// the
																								// line
																								// and
																								// scale
												current.printSelection(Integer.parseInt(from), Integer.parseInt(to),
														Integer.parseInt(linenum)); // display
																					// the
																					// selection
																					// to
																					// cut
																					// with
																					// document
																					// printSelection
																					// method
												System.out.println("(y/n):");
												String answer;
												if (in.hasNextLine()) {
													answer = in.nextLine(); // set
																			// answer
																			// to
																			// text
																			// entered
													answer.trim();
													if (answer.equals("y")) {
														cond = false; // change
																		// condition
																		// to
																		// exit
																		// outer
																		// loop
														break; // exit loop to
																// cut substring
													} else if (answer.equals("n")) {
														System.out.println(
																"Enter the substring you want to copy and remove.");
														break; // exit loop back
																// to position
																// selection
													} else {
														System.out.println("Invalid answer.");
													}
												}
											}
										}
										current.setSbuff(Integer.parseInt(from), Integer.parseInt(to),
												Integer.parseInt(linenum)); // copy
																			// the
																			// substring
																			// to
																			// the
																			// string
																			// buffer
										current.deleteSubstring(Integer.parseInt(from), Integer.parseInt(to),
												Integer.parseInt(linenum)); // remove
																			// the
																			// substring
																			// from
																			// the
																			// line
										current.showLine(Integer.parseInt(linenum)); // display
																						// the
																						// line
																						// and
																						// scale
									}
								}
								// paste the contents of the string buffer at a
								// specified point
								else if (subentry.equals("p")) {
									if (current.getText(Integer.parseInt(linenum)).length() == 0) { // if
																									// line
																									// length
																									// is
																									// 0,
																									// no
																									// need
																									// to
																									// specify
																									// a
																									// point
										current.reset(current.getSbuff(), Integer.parseInt(linenum)); // set
																										// the
																										// line
																										// to
																										// the
																										// contents
																										// of
																										// the
																										// string
																										// buffer
																										// with
																										// document
																										// reset
																										// method
										current.showLine(Integer.parseInt(linenum)); // display
																						// the
																						// line
																						// and
																						// scale
									} else {
										String entrypoint = " ";
										current.showLine(Integer.parseInt(linenum)); // display
																						// the
																						// line
																						// and
																						// scale
										// loop until a valid point is entered
										while (true) {
											System.out.println("Enter an insertion point.");
											if (in.hasNextLine()) {
												entrypoint = in.nextLine(); // set
																			// entrypoint
																			// to
																			// the
																			// text
																			// entered
												entrypoint.trim();
												// attempt to convert the string
												// to an integer
												try {
													// check if entry point is
													// in the line
													if (Integer.parseInt(entrypoint) < 0) {
														System.out.println("Point out of range.");
													} else if (Integer.parseInt(entrypoint) > current
															.getText(Integer.parseInt(linenum)).length()) {
														System.out.println("Point out of range.");
													} else {
														break; // valid point,
																// exit loop
													}
												} catch (Exception e) {
													System.out.println("Not a number."); // print
																							// error
																							// if
																							// there
																							// is
																							// an
																							// exception
												}
											}
										}
										current.insertSubstring(Integer.parseInt(entrypoint), current.getSbuff(),
												Integer.parseInt(linenum)); // paste
																			// the
																			// contents
																			// of
																			// the
																			// string
																			// buffer
																			// at
																			// the
																			// entrypoint
																			// with
																			// document
																			// insertSubstring
																			// method
										current.showLine(Integer.parseInt(linenum)); // display
																						// line
																						// and
																						// scale
									}
								}
								// enter text in the line at a specified point
								else if (subentry.equals("e")) {
									if (current.getText(Integer.parseInt(linenum)).length() == 0) { // if
																									// line
																									// length
																									// is
																									// 0,
																									// no
																									// need
																									// to
																									// specify
																									// a
																									// point
										System.out.println("The line is empty, enter some text.");
										String text = " ";
										// loop until text entered is 101
										// characters or less
										while (true) {
											text = in.nextLine(); // set text to
																	// text
																	// entered
											if (text.length() > 101) { // check
																		// if
																		// text
																		// is a
																		// valid
																		// length
												System.out.println("Enter a shorter sentence.");
											} else {
												break; // valid text length,
														// exit loop
											}
										}
										current.reset(text, Integer.parseInt(linenum)); // set
																						// line
																						// to
																						// the
																						// text
																						// entered
										current.showLine(Integer.parseInt(linenum)); // display
																						// line
																						// and
																						// scale
									} else {
										String entrypoint = " ";
										String text = " ";
										current.showLine(Integer.parseInt(linenum)); // display
																						// line
																						// and
																						// scale
										// loop until valid point is entered
										while (true) {
											System.out.println("Enter an insertion point.");
											if (in.hasNextLine()) {
												entrypoint = in.nextLine(); // set
																			// entrypoint
																			// to
																			// text
																			// entered
												entrypoint.trim();
												// attempt to convert the string
												// to an integer
												try {
													// check if entrypoint is in
													// the line
													if (Integer.parseInt(entrypoint) < 0) {
														System.out.println("Point out of range.");
													} else if (Integer.parseInt(entrypoint) > current
															.getText(Integer.parseInt(linenum)).length()) {
														System.out.println("Point out of range.");
													} else {
														break; // valid point,
																// exit loop
													}
												} catch (Exception e) {
													System.out.println("Not a number."); // print
																							// error
																							// if
																							// there
																							// is
																							// an
																							// exception
												}
											}
										}
										// loop until the existing text plus the
										// text entered is 101 characters or
										// less
										while (true) {
											System.out.println("Enter text to insert.");
											text = in.nextLine(); // set text to
																	// text
																	// entered
											if (current.getText(Integer.parseInt(linenum)).length()
													+ text.length() > 101) { // check
																				// if
																				// length
																				// of
																				// texts
																				// combined
																				// is
																				// a
																				// valid
												System.out.println("Enter a shorter substring.");
											} else {
												break; // valid text length,
														// exit loop
											}
										}
										current.insertSubstring(Integer.parseInt(entrypoint), text,
												Integer.parseInt(linenum)); // add
																			// the
																			// substring
																			// to
																			// the
																			// line
																			// with
																			// document
																			// insertSubstring
																			// method
										current.showLine(Integer.parseInt(linenum)); // display
																						// line
																						// and
																						// scale
									}
								}
								// delete a substring from the line
								else if (subentry.equals("d")) {
									if (current.getText(Integer.parseInt(linenum)).length() == 0) { // nothing
																									// to
																									// delete
																									// if
																									// line
																									// length
																									// is
																									// 0
										System.out.println("There is nothing to delete.");
									} else {
										String from = " ";
										String to = " ";
										boolean cond = true; // extra boolean
																// for specific
																// loop
																// condition
										// loop until substring is deleted
										while (cond) {
											current.showLine(Integer.parseInt(linenum)); // display
																							// line
																							// and
																							// scale
											// loop until valid position is
											// entered
											while (true) {
												System.out.println("Enter a from position.");
												if (in.hasNextLine()) {
													from = in.nextLine(); // set
																			// from
																			// to
																			// text
																			// entered
													from.trim();
													// attempt to convert the
													// string to an integer
													try {
														// check if from is in
														// the line
														if (Integer.parseInt(from) < 0) {
															System.out.println("Position out of range.");
														} else if (Integer.parseInt(from) > (current
																.getText(Integer.parseInt(linenum)).length() - 1)) {
															System.out.println("Position out of range.");
														} else {
															break; // valid
																	// position,
																	// exit loop
														}
													} catch (Exception e) {
														System.out.println("Not a number."); // print
																								// error
																								// if
																								// there
																								// is
																								// an
																								// exception
													}
												}
											}
											// loop until valid position is
											// entered
											while (true) {
												System.out.println("Enter a to position.");
												if (in.hasNextLine()) {
													to = in.nextLine(); // set
																		// to to
																		// the
																		// text
																		// entered
													to.trim();
													// attempt to convert the
													// string to an integer
													try {
														// check if to is at or
														// after from and in the
														// line
														if (Integer.parseInt(to) < Integer.parseInt(from)) {
															System.out.println(
																	"Enter a position at or after: " + from + ".");
														} else if (Integer.parseInt(to) > (current
																.getText(Integer.parseInt(linenum)).length() - 1)) {
															System.out.println("Enter a position at or before: "
																	+ (current.getText(Integer.parseInt(linenum))
																			.length() - 1)
																	+ ".");
														} else {
															break; // valid
																	// position,
																	// exit loop
														}
													} catch (Exception e) {
														System.out.println("Not a number."); // print
																								// error
																								// if
																								// there
																								// is
																								// an
																								// exception
													}
												}
											}
											// loop until a valid answer is
											// entered
											while (true) {
												System.out.println("Delete this substring?");
												current.showLine(Integer.parseInt(linenum)); // display
																								// line
																								// and
																								// scale
												current.printSelection(Integer.parseInt(from), Integer.parseInt(to),
														Integer.parseInt(linenum)); // display
																					// the
																					// selection
																					// to
																					// delete
												System.out.println("(y/n):");
												String answer;
												if (in.hasNextLine()) {
													answer = in.nextLine(); // set
																			// answer
																			// to
																			// text
																			// entered
													answer.trim();
													if (answer.equals("y")) {
														cond = false; // change
																		// condition
																		// to
																		// exit
																		// outer
																		// loop
														break; // exit loop to
																// delete
																// substring
													} else if (answer.equals("n")) {
														System.out.println("Enter the substring you want to remove.");
														break; // exit loop back
																// to position
																// selection
													} else {
														System.out.println("Invalid answer.");
													}
												}
											}
										}
										current.deleteSubstring(Integer.parseInt(from), Integer.parseInt(to),
												Integer.parseInt(linenum)); // delete
																			// substring
																			// from
																			// line
																			// with
																			// document
																			// deleteSubstring
																			// method
										current.showLine(Integer.parseInt(linenum)); // display
																						// line
																						// and
																						// scale
									}
								}
								// exit the line
								else if (subentry.equals("q")) {
									break; // exit the line
								} else {
									System.out.println("Invalid menu command.");
								}
							}
						}
					}
				}
				// delete a line in the document
				else if (entry.equals("dl")) {
					if (current.length() == 0) { // nothing to delete if
													// document length is 0
						System.out.println("The document is empty.");
					} else {
						String linenum = " ";
						// loop until a valid line number is entered
						while (true) {
							System.out.println("Enter a line number to delete.");
							if (in.hasNextLine()) {
								linenum = in.nextLine(); // set linenum to the
															// text entered
								linenum.trim();
								// attempt to convert the string to an integer
								try {
									// check if linenum is in the document
									if (Integer.parseInt(linenum) <= 0) {
										System.out.println("Line out of range.");
									} else if (Integer.parseInt(linenum) > current.length()) {
										System.out.println("Line out of range.");
									} else {
										break; // valid line number, exit loop
									}
								} catch (Exception e) {
									System.out.println("Not a number."); // print
																			// error
																			// if
																			// there
																			// is
																			// an
																			// exception
								}
							}
						}
						current.deleteLine(Integer.parseInt(linenum)); // call
																		// document
																		// deleteLine
																		// method
					}
				}
				// delete a range of lines in the document
				else if (entry.equals("dr")) {
					if (current.length() == 0) { // nothing to delete if
													// document length is 0
						System.out.println("The document is empty.");
					} else {
						String linefrom = " ";
						String lineto = " ";
						// loop until a valid line number is entered
						while (true) {
							System.out.println("Enter a line to delete from.");
							if (in.hasNextLine()) {
								linefrom = in.nextLine(); // set linefrom to the
															// text entered
								linefrom.trim();
								// attempt to convert the string to an integer
								try {
									// check if the linefrom is in the document
									if (Integer.parseInt(linefrom) <= 0) {
										System.out.println("Line out of range.");
									} else if (Integer.parseInt(linefrom) > current.length()) {
										System.out.println("Line out of range.");
									} else {
										break; // valid line number, exit the
												// loop
									}
								} catch (Exception e) {
									System.out.println("Not a number."); // print
																			// error
																			// if
																			// there
																			// is
																			// an
																			// exception
								}
							}
						}
						// loop until a valid line numnber is entered
						while (true) {
							System.out.println("Enter a line to delete to.");
							if (in.hasNextLine()) {
								lineto = in.nextLine(); // set lineto to the
														// text entered
								lineto.trim();
								// attempt to convert the string to an integer
								try {
									// check if lineto is at or after linefrom
									// and in the document
									if (Integer.parseInt(lineto) < Integer.parseInt(linefrom)) {
										System.out.println("Enter a line at or after: " + linefrom + ".");
									} else if (Integer.parseInt(lineto) > current.length()) {
										System.out.println("Enter a line at or before: " + current.length() + ".");
									} else {
										break; // valid line number, exit the
												// loop
									}
								} catch (Exception e) {
									System.out.println("Not a number."); // print
																			// error
																			// if
																			// there
																			// is
																			// an
																			// exception
								}
							}
						}
						current.deleteRange(Integer.parseInt(linefrom), Integer.parseInt(lineto)); // call
																									// the
																									// document
																									// deleteRange
																									// method
					}
				}
				// copy a range of lines to the line buffer
				else if (entry.equals("cr")) {
					if (current.length() == 0) { // nothing to copy if document
													// length is 0
						System.out.println("There is nothing to copy.");
					} else {
						String linefrom = " ";
						String lineto = " ";
						// loop until a valid line number is entered
						while (true) {
							System.out.println("Enter a line to copy from.");
							if (in.hasNextLine()) {
								linefrom = in.nextLine(); // set linefrom to the
															// text entered
								linefrom.trim();
								// attempt to convert the string to an integer
								try {
									// check if linefrom is in the document
									if (Integer.parseInt(linefrom) <= 0) {
										System.out.println("Line out of range.");
									} else if (Integer.parseInt(linefrom) > current.length()) {
										System.out.println("Line out of range.");
									} else {
										break; // valid line number, exit loop
									}
								} catch (Exception e) {
									System.out.println("Not a number."); // print
																			// error
																			// if
																			// there
																			// is
																			// an
																			// exception
								}
							}
						}
						// loop until valid line number is entered
						while (true) {
							System.out.println("Enter a line to copy to.");
							if (in.hasNextLine()) {
								lineto = in.nextLine(); // set lineto to the
														// text entered
								lineto.trim();
								// attempt to convert the string to an integer
								try {
									// check if lineto is at or after linefrom
									// and in the document
									if (Integer.parseInt(lineto) < Integer.parseInt(linefrom)) {
										System.out.println("Enter a line at or after: " + linefrom + ".");
									} else if (Integer.parseInt(lineto) > current.length()) {
										System.out.println("Enter a line at or before: " + current.length() + ".");
									} else {
										break; // valid line number, exit loop
									}
								} catch (Exception e) {
									System.out.println("Not a number."); // print
																			// error
																			// if
																			// there
																			// is
																			// an
																			// exception
								}
							}
						}
						linebuffer.deleteRange(1, linebuffer.length()); // clear
																		// the
																		// line
																		// buffer
						for (int i = Integer.parseInt(linefrom); i <= Integer.parseInt(lineto); i++) { // loop
																										// through
																										// the
																										// range
																										// entered
							linebuffer.insert(current.getText(i)); // add the
																	// lines to
																	// the line
																	// buffer
																	// with the
																	// document
																	// insert
																	// and
																	// getText
																	// methods
						}
						System.out.println("Copied to line buffer:");
						linebuffer.bufferPrint(); // display the copied lines
													// with the document
													// bufferPrint method
					}
				}
				// paste the contents of the line buffer after a specified line
				else if (entry.equals("pl")) {
					String entrypoint = " ";
					// loop until a valid line number is entered
					while (true) {
						System.out.println("Insert after line number:");
						if (in.hasNextLine()) {
							entrypoint = in.nextLine(); // set entrypoint to the
														// text entered
							entrypoint.trim();
							// attempt to convert the string to an integer
							try {
								// check if entrypoint is in the document
								if (Integer.parseInt(entrypoint) < 0) {
									System.out.println("Line out of range.");
								} else if (Integer.parseInt(entrypoint) > current.length()) {
									System.out.println("Line out of range.");
								} else {
									break; // valid line number, exit loop
								}
							} catch (Exception e) {
								System.out.println("Not a number."); // print
																		// error
																		// if
																		// there
																		// is an
																		// exception
							}
						}
					}
					count = Integer.parseInt(entrypoint); // set count to
															// entrypoint
					if (count == 0) { // if count is 0, inserting before the
										// first line
						System.out.println("Inserting before:");
						current.printText(1); // print the text of the first
												// line with document printText
												// method
					} else {
						System.out.println("Inserting after:");
						current.printText(count); // print text of the line
													// entered with document
													// printText method
					}
					count++; // increment count to start adding text at the next
								// line
					for (int i = 1; i <= linebuffer.length(); i++) { // loop
																		// through
																		// the
																		// line
																		// buffer
						current.insertAfter(linebuffer.getText(i), count); // add
																			// contents
																			// of
																			// the
																			// line
																			// buffer
																			// to
																			// the
																			// document
																			// with
																			// document
																			// insertAfter
																			// method
						count++;
					}
					current.print(); // display the document
				} else if (entry.equals("w")) {
					if (file.length() == 0) { // prompt a file name if there is
												// none
						System.out.println("Enter a text file to write to.");
						if (in.hasNextLine()) {
							file = in.nextLine(); // set file to text entered
							file.trim();
						}
					}
					// attempt to write to file
					try {
						BufferedWriter writer = new BufferedWriter(new FileWriter(file));
						for (int i = 1; i < current.length() + 1; i++) { // loop
																			// through
																			// the
																			// document
							writer.write(current.getText(i)); // add line to
																// file with
																// document
																// getText
																// method
							writer.newLine(); // move to a new line
						}
						writer.close();
					} catch (Exception e) {
						System.out.println("File doesn't exist."); // print
																	// error if
																	// there is
																	// an
																	// exception
					}
				} else if (entry.equals("wq")) {
					if (file.length() == 0) { // prompt a file name if there is
												// none
						System.out.println("Enter a text file to write to.");
						if (in.hasNextLine()) {
							file = in.nextLine(); // set file to text entered
							file.trim();
						}
					}
					// attempt to write to file
					try {
						BufferedWriter writer = new BufferedWriter(new FileWriter(file));
						for (int i = 1; i < current.length() + 1; i++) { // loop
																			// through
																			// the
																			// document
							writer.write(current.getText(i)); // add line to
																// file with
																// document
																// getText
																// method
							writer.newLine(); // move to a new line
						}
						writer.close();
					} catch (Exception e) {
						System.out.println("File doesn't exist."); // print
																	// error if
																	// there is
																	// an
																	// exception
					}
					in.close(); // close the scanner
					System.out.println("Goodbye!");
					break; // exit the program
				}
				// exit the program
				else if (entry.equals("q")) {
					in.close(); // close the scanner
					System.out.println("Goodbye!");
					break; // exit the program
				} else {
					System.out.println("Invalid menu command.");
				}
			}
		}
	}
}
