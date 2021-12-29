=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=
CIS 120 Game Project README
PennKey: colavijs
=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=

===================
=: Core Concepts :=
===================

- List the four core concepts, the features they implement, and why each feature
  is an appropriate use of the concept. Incorporate the feedback you got after
  submitting your proposal.

  1. 2-D array: The chess board is a 2D array that stores the location of pieces.
                For example, if I have Piece[][] board = new board[8][8]; then I
                can store pieces and iterate through the array to find them.
                This feature was approved as good to go.  It is appropriate
                because each slot on the board can only store up to one piece and
                if it does not have any piece than it is null.

  2.Subtyping: All of the pieces inherit the Piece class which has instance
               variables such as the image, move, paint, and getMoves functions.
               It also has some abstract methods that all the pieces must
               implement such as getName and getMoves.  This feature was
               approved as good to go.  It is appropriate because all the
               pieces have similar attributes and abilities, and I can
               easily store them in a 2-D array.

  3.Collections: I have a method getMoves that returns the legal moves in the
                 form of a TreeSet.  My constraints for this method are that
                 a distinct point will never be stored more than once, and I
                 do not know the amount of moves that I will return.  The
                 TreeSet structure fits these constraints the best because each
                 element can be stored no more than once and the length is not
                 predetermined.  The feedback for this concept was that I should
                 think about what maps to what, however, I ended up not using a
                 map.

  4.JUnit Tests: My tests function to set the game model to a certain state and
                 ensure that the methods work properly and the pieces behave as
                 expected.

=========================
=: Your Implementation :=
=========================

- Provide an overview of each of the classes in your code, and what their
  function is in the overall game.
  GameBoard: This class instantiates a Chess object, which is the model for the game.
  As the user clicks the game board, the model is updated. Whenever the model
  is updated, the game board repaints itself and updates its status JLabel to
  reflect the current state of the model.
  RunChess: This class sets up the top-level frame and widgets for the GUI.
  Chess: This class is a model for Chess.
  Piece: This class is the superclass of all the chess pieces.  It contains the
  methods and variables that the subclasses have in common.  It also functions
  as a way for me to store all the pieces in one data structure.
  Bishop, King, Knight, Pawn, Queen, Rook: The important part of these classes
  is the get moves method which returns a TreeSet of all possible points that they
  can move to.  Otherwise, they have methods specific to that class.  Some of them,
  such as the king, override a method.  The king overrides draw to draw a red circle
  around itself if it is in check.
  MyPoint: stores x and y ints and implements comparable so that it can be stored in
  a TreeSet.

- Were there any significant stumbling blocks while you were implementing your
  game (related to your design, or otherwise)?
  I wanted to implement castling and en passant, but my logic became so twisted.
  I could not figure out how to keep all the variables organized, so I resorted to
  JUnit tests instead.  Also, I forgot that objects stored in TreeSets needed to
  implement comparable, and then I implemented it incorrectly and had really weird
  behavior because points were comparing to each other as equals when they weren't.
  This manifested itself in the code as missing gray circles where I should have
  been able to move my piece.

- Evaluate your design. Is there a good separation of functionality? How well is
  private state encapsulated? What would you refactor, if given the chance?
  There is fairly good separation of functionality because I have everything
  organized in a logical fashion.  However, I think my code is lacking in some
  of the abstract methods in my Piece class.  I am ashamed to say that I defined
  isChecked() as an abstract method, even though I only ever use it for the king.
  I struggled with my understanding of abstract methods for a while, but I feel
  like if I was to do this again then I could make the piece class more organized.
  If I succeeded in that then castling and en passant may be much easier to implement.

========================
=: External Resources :=
========================

- Cite any external resources (images, tutorials, etc.) that you may have used 
  while implementing your game.
  I used images from wikimedia for each of the chess pieces
  (https://commons.wikimedia.org/wiki/Category:PNG_chess_pieces/Standard_transparent).
  These are referenced using their reference path so that my code can run on
  other computers.
