import java.util.Iterator;

public class Document implements Iterable {
	private Line head; // the first line of the document
	private StringBuffer sbuff = new StringBuffer(); // string buffer for the
														// document

	public Document() {
		head = new Line(null); // creates a dummy node
	}

	// put the selected substring into the string buffer
	public void setSbuff(int from, int to, int line) {
		String piece = getText(line).substring(from, to + 1); // take selected
																// substring
																// from the
																// original line
		sbuff.setContents(piece);
	}

	// return the text in the string buffer
	public String getSbuff() {
		return sbuff.getContents();
	}

	// check to see if the document is empty
	public boolean isEmpty() {
		if (head.next == null) { // empty if no line after the dummy node
			return true;
		}
		return false;
	}

	// insert a new line at the end of the document
	public void insert(String contents) {
		Line p = new Line(contents); // create new line
		// no need to iterate if the document is empty
		if (head.next == null) {
			p.next = head.next; // point the new line to the line after the
								// dummy node (null)
			head.next = p; // point the dummy node to the new line
			p.prev = head; // point the new line to the dummy node
		} else {
			// iterate over the list until current is the last line
			Line current = head;
			Iterator iter = iterator();
			while (iter.hasNext()) {
				iter.next();
				current = current.next; // set current to the next line
			}
			current.next = p; // point current to the new line
			p.prev = current; // point the new line to current
		}
	}

	// insert a new line after a specified line
	public void insertAfter(String contents, int line) {
		Line p = new Line(contents); // create a new line
		// iterate over the list until current is the line after the line
		// entered
		Line current = head;
		Iterator iter = iterator();
		int count = 1;
		while (iter.hasNext()) {
			if (count == line) { // compare count and line before moving to the
									// next line
				break;
			}
			iter.next();
			current = current.next; // set current to the next line
			count++;
		}
		// make sure the line entered us not the last line
		if (current.next != null) {
			current.next.prev = p; // point the line after current to the new
									// line
		}
		p.prev = current; // point the new line to current
		p.next = current.next; // point the new line to the line after current
		current.next = p; // point current to the new line
	}

	// print the document with line numbers
	public void print() {
		// iterate over the list until all lines are printed
		Iterator iter = iterator();
		int count = 1;
		while (iter.hasNext()) {
			System.out.println(count + ": " + iter.next().toString()); // combine
																		// the
																		// line
																		// number
																		// and
																		// the
																		// text
																		// into
																		// one
																		// string
			count++;
		}
	}

	// print the contents of the line buffer
	public void bufferPrint() {
		// iterate over the list until all lines are printed
		Iterator iter = iterator();
		int count = 1;
		while (iter.hasNext()) {
			System.out.println(iter.next().toString()); // get the text of the
														// line
			count++;
		}
	}

	// print a specified line with its line number
	public void printLine(int line) {
		// iterate over the list until iter.next() returns the text of the line
		// entered
		Iterator iter = iterator();
		int count = 1;
		while (iter.hasNext()) {
			if (count == line) { // break loop when count equals the line
									// entered
				System.out.println(count + ": " + iter.next().toString()); // combine
																			// the
																			// line
																			// number
																			// and
																			// the
																			// text
																			// into
																			// one
																			// string
				break;
			}
			iter.next();
			count++;
		}
	}

	// return the text of a specified line without printing anything
	public String getText(int line) {
		// iterate over the list until iter.next() returns the text of the line
		// entered
		Iterator iter = iterator();
		int count = 1;
		String contents = "";
		while (iter.hasNext()) {
			if (count == line) { // break loop when count equals the line
									// entered
				contents = iter.next().toString(); // set contents to the text
													// of the line entered
				break;
			}
			iter.next();
			count++;
		}
		return contents;
	}

	// remove a specified line from the document
	public void deleteLine(int line) {
		// iterate over list until current is the line entered
		Line current = head;
		Iterator iter = iterator();
		int count = 1;
		while (iter.hasNext()) {
			iter.next();
			current = current.next; // set current to the next line
			if (count == line) { // break the loop when count equals the line
									// entered
				break;
			}
			count++;
		}
		// check if there is a line after current
		if (current.next != null) {
			current.next.prev = current.prev; // point the line after current to
												// the line before current
		}
		current.prev.next = current.next; // point the line before current to
											// the line after current
	}

	// print just the text of a specified line
	public void printText(int line) {
		// iterate over the list until iter.next() returns the text of the line
		// entered
		Iterator iter = iterator();
		int count = 1;
		while (iter.hasNext()) {
			if (count == line) {
				System.out.println(iter.next().toString()); // get the text of
															// the line entered
				break;
			}
			iter.next();
			count++;
		}
	}

	// print a specified range of lines
	public void printRange(int from, int to) {
		for (int i = from; i <= to; i++) { // loop through the range entered
			printLine(i); // print each line in the range
		}
	}

	// remove a specified range of lines
	public void deleteRange(int from, int to) {
		for (int i = (to - from); i >= 0; i--) { // loop through the range
													// entered
			deleteLine(from); // remove each line in the range
		}
	}

	// return the length of the document
	public int length() {
		// iterate over the list until the last line
		Iterator iter = iterator();
		int count = 0;
		while (iter.hasNext()) {
			iter.next();
			count++; // increment count to keep track of how many lines are in
						// the document
		}
		return count;
	}

	// print a specified line with its scale for the edit line command
	public void showLine(int line) {
		// iterate over the list until currentline is set to the line entered
		Iterator iter = iterator();
		int count = 1;
		String currentline = "";
		while (iter.hasNext()) {
			if (count == line) { // break the loop when count equals the line
									// entered
				currentline = iter.next().toString(); // get the text of the
														// line entered and set
														// current line to it
				break;
			}
			iter.next();
			count++;
		}
		if (currentline.length() == 0) { // if there is nothing in currentline
											// do not display a scale
			System.out.println("This line is empty.");
		} else {
			String scalenums = "";
			String scale = "";
			for (int i = 0; i < currentline.length(); i++) { // loop through
																// current line
				if (i == 0 || i == 5) { // add scale numbers at the
										// corresponding index if they are
										// single digit
					scalenums += i;
				} else if (((i + 1) % 5) == 0 && (i + 1) != 5 && (i + 1) != currentline.length()) { // add
																									// scale
																									// numbers
																									// one
																									// index
																									// before
																									// if
																									// they
																									// are
																									// double
																									// digit
					scalenums += i + 1;
				} else if ((i % 5) != 0) { // add a space if there is no scale
											// number at this index
					scalenums += " ";
				}
			}
			for (int j = 0; j < currentline.length(); j++) { // loop through
																// current line
				if (j == 0) { // add a bar at index 0
					scale = "|";
				} else if ((j % 5) == 0) { // add a bar or plus sign at every
											// fifth index
					if ((j % 10) == 0) { // add a bar at every tens place
						scale += "|";
					} else { // add a plus sign at every fives place
						scale += "+";
					}
				} else {
					scale += "-"; // add a dash if no bar or plus sign at this
									// index
				}
			}
			System.out.println(scalenums);
			System.out.println(scale);
			System.out.println(currentline);
		}
	}

	// print a cursor indicating the selected substring
	public void printSelection(int from, int to, int line) {
		// iterate over the list until currentline is set to the text of the
		// line entered
		Iterator iter = iterator();
		int count = 1;
		String currentline = "";
		while (iter.hasNext()) {
			if (count == line) { // break the loop when count equals the line
									// entered
				currentline = iter.next().toString(); // get the text of the
														// line entered and set
														// current line to it
				break;
			}
			iter.next();
			count++;
		}
		String selection = "";
		for (int i = 0; i < currentline.length(); i++) { // loop through
															// currentline
			if (i >= from && i <= to) { // if i is in the range entered add a
										// cursor (^) to selection
				selection += "^";
			} else { // add a blank space to selection
				selection += " ";
			}
		}
		System.out.println(selection);
	}

	// remove a substring from a line specified
	public void deleteSubstring(int from, int to, int line) {
		// iterate over the list until current is the line entered
		Line current = head;
		Iterator iter = iterator();
		int count = 1;
		while (iter.hasNext()) {
			iter.next();
			current = current.next; // set current to the next line
			if (count == line) { // break the loop when count equals the line
									// entered
				break;
			}
			count++;
		}
		current.delete(from, to); // call the line delete method
	}

	// insert a substring into a line at a point specified
	public void insertSubstring(int entrypoint, String text, int line) {
		// iterate over the list until current is the line entered
		Line current = head;
		Iterator iter = iterator();
		int count = 1;
		while (iter.hasNext()) {
			iter.next();
			current = current.next; // set current to the next line
			if (count == line) { // break the loop when count equals the line
									// entered
				break;
			}
			count++;
		}
		current.insert(entrypoint, text); // call the line insert method
	}

	public void reset(String text, int line) {
		// iterate over the list until current is the line entered
		Line current = head;
		Iterator iter = iterator();
		int count = 1;
		while (iter.hasNext()) {
			iter.next();
			current = current.next; // set current to the next line
			if (count == line) { // break loop when current is the line entered
				break;
			}
			count++;
		}
		current.setContents(text); // overwrite the contents of the line with
									// the entered text
	}

	// Line class to create objects to fill the document
	private class Line {
		String contents; // holds the text in the line
		int chars; // how many characters are in the line (including spaces)
		Line next, prev; // pointers to the next and previous lines

		public Line(String contents) {
			this.contents = contents; // set the text of the line to the
										// incoming argument
			if (contents != null) {
				chars = contents.length(); // determine how many characters are
											// in the line
			}
		}

		// remove the substring in the selected range
		public void delete(int from, int to) {
			contents = contents.substring(0, from) + contents.substring((to + 1), contents.length()); // merge
																										// substring
																										// before
																										// and
																										// substring
																										// after
																										// selected
																										// range
		}

		// enter a substring at the selected point
		public void insert(int entrypoint, String text) {
			if (entrypoint == contents.length()) { // append to the end if entry
													// point is the length of
													// the string
				contents += text;
			} else {
				contents = contents.substring(0, entrypoint) + text + contents.substring(entrypoint, contents.length()); // insert
																															// substring
																															// between
																															// substrings
																															// before
																															// and
																															// after
																															// selected
																															// point
			}
		}

		// change the contents of the line
		public void setContents(String contents) {
			this.contents = contents;
		}

	}

	// StringBuffer class to hold a string
	private class StringBuffer {
		private String minicontents; // holds the text in the string buffer

		// initialize the string buffer with an empty string
		public StringBuffer() {
			minicontents = "";
		}

		// return the text in the string buffer
		public String getContents() {
			return minicontents;
		}

		// change the contents of the string buffer
		public void setContents(String contents) {
			this.minicontents = contents;
		}
	}

	// implementation of iterator to traverse over the document
	private class DocumentIterator implements Iterator {
		private Line cursor = head.next; // set cursor to the line after the
											// dummy node

		// no more lines if the next line is null
		public boolean hasNext() {
			return cursor != null;
		}

		// return the contents of the current line and move to the next one
		public String next() {
			String retVal = cursor.contents; // save contents before changing
												// cursor
			cursor = cursor.next; // set cursor to the next line
			return retVal;
		}

		// placeholder
		public void remove() {
		}
	}

	// creates a DocumentIterator object
	public Iterator iterator() {
		return new DocumentIterator();
	}
}
