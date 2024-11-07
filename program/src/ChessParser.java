import java.util.HashMap;
import java.util.List;

public class ChessParser {
	static HashMap<String, Integer> rows = new HashMap<>();
	static HashMap<String, Integer> cols = new HashMap<>();
	static char pieces[];

	public ChessParser() {
		cols.put("a", 0);
		cols.put("b", 1);
		cols.put("c", 2);
		cols.put("d", 3);
		cols.put("e", 4);
		cols.put("f", 5);
		cols.put("g", 6);
		cols.put("h", 7);

		rows.put("1", 0);
		rows.put("2", 1);
		rows.put("3", 2);
		rows.put("4", 3);
		rows.put("5", 4);
		rows.put("6", 5);
		rows.put("7", 6);
		rows.put("8", 7);

		pieces = new char[] { 'R', 'N', 'B', 'Q', 'K' };

	}

	static boolean isPiece(char c) {
		for (int i = 0; i < 5; i++) {
			if (c == pieces[i]) {
				return true;
			}
		}

		return false;
	}

	public boolean isValidCoord(String txt) {
		String s[] = txt.split("");
		return cols.containsKey(s[0]) && rows.containsKey(s[1]);
	};

	public boolean canParse(String txt) {
		if (txt.equals("O-O") || txt.equals("O-O-O")) {
			return true;
		} else if (txt.length() == 2) {
			// e4
			return isValidCoord(txt);
		} else if (txt.length() == 3) {
			// Nc3
			return isPiece(txt.charAt(0)) && isValidCoord(txt.substring(1));
		} else if (txt.length() == 4) {
			// Bxh7
			boolean p1 = isPiece(txt.charAt(0)) || rows.containsKey(txt.split("")[0]);
			boolean p2 = txt.charAt(1) == 'X' || txt.charAt(1) == 'x';
			boolean p3 = isValidCoord(txt.substring(2));
			return p1 && p2 && p3;
		} else if (txt.length() == 5) {
			// Qh4e1
			boolean p1 = isPiece(txt.charAt(0));
			boolean p2 = isValidCoord(txt.substring(1, 3));
			boolean p3 = isValidCoord(txt.substring(3));
			return p1 && p2 && p3;
		} else if (txt.length() == 6) {
			// Qh4xe1
			boolean p1 = isPiece(txt.charAt(0));
			boolean p2 = isValidCoord(txt.substring(1, 3));
			boolean p3 = txt.charAt(3) == 'X' || txt.charAt(3) == 'X';
			boolean p4 = isValidCoord(txt.substring(4));

			return p1 && p2 && p3 && p4;
		} else {
			return false;
		}
	}

	public boolean moveIsAmbiguous(String txt, Board board, boolean white) {
		String piece = txt.split("")[0];
		if (piece.equals("K")) {
			return false;
		}

		switch (txt.length()) {
		case 3: {
			String coords[] = txt.substring(1).split("");

			int x = cols.get(coords[0]);
			int y = rows.get(coords[1]);

			Spot end = board.getBox(x, y);
			
			return moreThanOneCanMoveThere(piece, board, white, end);

		}
		case 4: {
			String coords[] = txt.substring(2).split("");

			int x = cols.get(coords[0]);
			int y = rows.get(coords[1]);

			Spot end = board.getBox(x, y);
			return moreThanOneCanMoveThere(piece, board, white, end);

		}
		default: {
			return false;
		}
		}
	}

	public Spot getStart(String txt, Board board, Boolean white) {
		if (txt.equals("O-O") || txt.equals("O-O-O")) {
			return board.getTrackedPiece("K", white);
		} else if (txt.length() == 2) {
			// e4
			if (white) {
				int coords[] = getCoords(txt);
				coords[1]--;
				if (board.getBox(coords[0], coords[1]).getPiece() != null) {
					return board.getBox(coords[0], coords[1]);
				} else {
					return board.getBox(coords[0], coords[1] - 1);
				}
			} else {
				int[] coords = getCoords(txt);
				coords[1]++;
				if (board.getBox(coords[0], coords[1]).getPiece() != null) {
					return board.getBox(coords[0], coords[1]);
				} else {
					return board.getBox(coords[0], coords[1] + 1);
				}
			}
		} else if (txt.length() == 3 || txt.length() == 4) {
			// Nc3 // Bxh7 // Kc3
			String type = txt.split("")[0];

			if (type.equals("K")) {
				return board.getTrackedPiece("K", white);
			}

			Spot possibleStarts[] = generatePossibleStarts(type, board, white);
			Spot end = getEnd(txt, board, white);

			for (Spot possibleStart : possibleStarts) {
				if (board.canMove(possibleStart, end)) {
					return possibleStart;
				}
			}

			return new Spot(null, -1, -1);
		}

		else if (txt.length() == 5 || txt.length() == 6) {
			// Qh4e1, Qh4xe1
			int coords[] = getCoords(txt.substring(1, 3));
			return board.getBox(coords[0], coords[1]);
		} else {
			return new Spot(null, 0, 0);
		}

	}

	public int[] getCoords(String txt) {
		int coords[] = new int[2];
		coords[0] = cols.get(txt.split("")[0]);
		coords[1] = rows.get(txt.split("")[1]);
		return coords;
	}

	public Spot getEnd(String txt, Board board, boolean white) {
		if (txt.equals("O-O") || txt.equals("O-O-O")) {
			if (white) {
				if (txt.equals("O-O")) {
					return board.getBox(7, 0);
				} else {
					return board.getBox(0, 0);
				}
			} else {
				if (txt.equals("O-O")) {
					return board.getBox(7, 7);
				} else {
					return board.getBox(0, 7);
				}
			}
		}

		switch (txt.length()) {
		case 2: {
			// e4
			int coords[] = getCoords(txt);
			return board.getBox(coords[0], coords[1]);
		}
		case 3:
		case 4: {
			// Nc3, Bxh7
			int coords[] = getCoords(txt.substring(1));
			return board.getBox(coords[0], coords[1]);
		}
		case 5:
		case 6: {
			// Qh4e1, Qh4xe1
			int coords[] = getCoords(txt.substring(4));
			return board.getBox(coords[0], coords[1]);
		}
		default:
			return new Spot(null, 0, 0);
		}
	}

	public String[] correctMoves(String txt, Board board, boolean white) {
		String x = "";
		if (txt.length() == 4) {
			x = "x";
		}

		String piece = txt.split("")[0];
		String end = txt.substring(txt.length() - 2);

		Spot s1 = board.getTrackedPiece(piece + "1", white);
		Spot s2 = board.getTrackedPiece(piece + "2", white);

		String i1 = piece + formatCoords(s1) + x + end;
		String i2 = piece + formatCoords(s2) + x + end;

		String[] returnStr = new String[] { i1, i2 };
		return returnStr;
	}

	public String formatCoords(Spot s) {
		String s1 = Character.toString((char) (97 + s.getX()));
		String s2 = Integer.toString(s.getY() + 1);

		String s3 = s1 + s2;
		return s3;
	}

	public Spot[] generatePossibleStarts(String type, Board board, boolean white) {
		Spot roughSpots[] = new Spot[5];
		int counter = 0;

		for (int i = 1; i <= 5; i++) {
			if (board.hasTrackedPiece(type + i, white)) {
				roughSpots[counter] = board.getTrackedPiece(type + i, white);
				counter++;
			}
		}

		Spot returnArr[] = new Spot[counter];
		System.arraycopy(roughSpots, 0, returnArr, 0, counter);

		return returnArr;
	}

	public boolean moreThanOneCanMoveThere(String piece, Board board, Boolean white, Spot end) {
		List<Spot> piecesSpots = board.getTrackedPieces(piece, white);

		if (piecesSpots.size() == 1) {
			return false;
		} else {
			int count = 0;
			for (int i = 0; i < piecesSpots.size(); i++) {
				Spot s = piecesSpots.get(i);
				if (board.canMove(s, end)) {
					count++;
				}
			}

			return count > 1;
		}
	}
}
