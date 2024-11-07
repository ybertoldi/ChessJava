import java.util.ArrayList;
import java.util.List;

import javax.swing.plaf.synth.SynthScrollPaneUI;

public abstract class Piece {
	private boolean killed = false;
	private boolean white = false;
	private boolean moved;

	private boolean isBeingAttacked = false;
	public List<Spot> attackers = new ArrayList<Spot>();
	public List<Spot> isAttacking = new ArrayList<Spot>();
	private String type;

	public Piece(boolean white) {
		this.white = white;
		this.setMoved(false);
		attackers.clear();
		isAttacking.clear();
	}

	public void clearAttackers() {
		attackers.clear();
	}

	public void addAttacker(Spot s) {
		System.out.printf("----------- %s is being attacked by %s  ----------- \n", this.type, s.getPiece().getType());
		attackers.add(s);
	}

	public void removeAttacker(Spot s) {
		System.out.printf("----------- %s is no longer being attacked by %s  ----------- \n", this.type,
				s.getPiece().getType());
		attackers.remove(s);
	}

	public void stopAttacking(Spot s) {
		isAttacking.remove(s);
	}

	public void startAttacking(Spot s) {
		isAttacking.add(s);
	}

	public void kill() {
		killed = true;
	}

	public boolean isKilled() {
		return killed;
	}

	public boolean isWhite() {
		return white;
	}

	public void setType(String t) {
		this.type = t;
	}

	public String getType() {
		return this.type;
	}

	public void setMoved(boolean b) {
		this.moved = b;
	}

	public boolean hasMoved() {
		return moved;
	}

	public boolean isBeingAttacked() {
		return isBeingAttacked;
	}

	public void setBeingAttacked(boolean isBeingAttacked) {
		this.isBeingAttacked = isBeingAttacked;
	}

	public void updateWhosAttacking(Spot start, Spot end, Board board) {
	};

	public void updateWhosAttacking(Spot curSpot, Board board) {
	};

	public void updateBeingAttacked(Board board, Spot start, Spot end) {

		for (int i = 0; i < attackers.size(); i++) {
			attackers.get(i).getPiece().updateWhosAttacking(attackers.get(i), board);
			System.out.printf("%s is no longer attacking %s", attackers.get(i).getPiece().getType(), this.type);
		}

		System.out.println();

		// peças que bloqueou
		for (int i = 0; i < 4; i++) {
			Spot s = board.findNextSpot(end, i);
			Piece p = s.getPiece();
			if (p != null && s != end) {
				char type = p.getType().charAt(0);
				if (type == 'Q' || type == 'B') {
					p.updateWhosAttacking(s, board);
				}
			}
		}

		for (int i = 4; i < 8; i++) {
			Spot s = board.findNextSpot(end, i);
			Piece p = s.getPiece();
			if (p != null && s != end) {
				char type = p.getType().charAt(0);
				if (type == 'Q' || type == 'R') {
					p.updateWhosAttacking(s, board);
				}
			}
		}

		// peças que descobriu
		List<Spot> sameColourPieces = new ArrayList<Spot>();
		sameColourPieces.addAll(board.getTrackedPieces("B", white));
		sameColourPieces.addAll(board.getTrackedPieces("R", white));
		sameColourPieces.addAll(board.getTrackedPieces("Q", white));

		for (Spot spot : sameColourPieces) {
			if (spot != end) {

				char type = spot.getPiece().getType().charAt(0);
				if (type == 'R' || type == 'Q') {
					if (start.isStraightlyAligned(spot)) {
						spot.getPiece().updateWhosAttacking(spot, board);
					}
				}
				if (type == 'R' || type == 'Q') {
					if (start.isDiagonallyAligned(spot)) {
						spot.getPiece().updateWhosAttacking(spot, board);
					}
				}
			}
		}

	}

	public boolean pathIsClearDiagonally(Board board, Spot start, Spot end) {
		int sX = start.getX();
		int sY = start.getY();

		int eX = end.getX();
		int eY = end.getY();

		if (sX < eX && sY < eY) {
			while (sX < eX - 1 && sY < eY - 1) {
				sX++;
				sY++;
				if (board.getBox(sX, sY).getPiece() != null) {
					return false;
				}
			}
		}

		else if (sX > eX && sY < eY) {

			while (sX > eX + 1 && sY < eY - 1) {
				sX--;
				sY++;
				if (board.getBox(sX, sY).getPiece() != null) {
					return false;
				}
			}
		}

		else if (sX < eX && sY > eY) {

			while (sX < eX - 1 && sY > eY + 1) {
				sX++;
				sY--;
				if (board.getBox(sX, sY).getPiece() != null) {
					return false;
				}
			}
		}

		else if (sX > eX && sY > eY) {

			while (sX > eX + 1 && sY > eY + 1) {
				sX--;
				sY--;
				if (board.getBox(sX, sY).getPiece() != null) {
					return false;
				}
			}
		}

		return true;
	}

	public boolean pathIsClearStraightly(Board board, Spot start, Spot end) {
		int sX = start.getX();
		int sY = start.getY();

		int eX = end.getX();
		int eY = end.getY();

		int deltaX = eX - sX;
		int deltaY = eY - sY;

		if (deltaX == 0 && deltaY > 0) {
			while (sY < eY - 1) {
				sX++;
				if (board.getBox(sX, sY).getPiece() != null) {
					return false;
				}
			}
		} else if (deltaX == 0 && deltaY < 0) {
			while (sY > eY + 1) {
				sX--;
				if (board.getBox(sX, sY).getPiece() != null) {
					return false;
				}
			}
		} else if (deltaX > 0 && deltaY == 0) {
			while (sX < eX - 1) {
				sX++;
				if (board.getBox(sX, sY).getPiece() != null) {
					return false;
				}
			}
		} else if (deltaX < 0 && deltaY == 0) {
			while (sX > eX + 1) {
				sX--;
				if (board.getBox(sX, sY).getPiece() != null) {
					return false;
				}
			}
		}

		return true;
	};

	public boolean moveIsLegal(Board board, Spot start, Spot end) {
		if (this.isBeingAttacked) {

			Spot kingSpot = board.getTrackedPiece("K", white);

			for (int i = 0; i < attackers.size(); i++) {
				Spot attackerSpot = attackers.get(i);
				char type = attackerSpot.getPiece().getType().charAt(0);

				if (type == 'Q' || type == 'B') {
					if (kingSpot.isDiagonallyAligned(start, attackerSpot)
							&& !kingSpot.isDiagonallyAligned(end, attackerSpot)) {
						return false;
					}
				}

				if (type == 'Q' || type == 'R') {
					if (kingSpot.isStraightlyAligned(start, attackerSpot)
							&& !kingSpot.isStraightlyAligned(end, attackerSpot)) {
						return false;
					}
				}
			}
		}

		return true;

	}

	public abstract boolean canMove(Board board, Spot start, Spot end);

}
