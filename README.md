# Bulls-And-Cows
A Java command line bulls and cows game. Allowing player to choose computer opponent level, reading guesses from a file, and saving result to a file.

![UML](https://github.com/kaminokyoku/Bulls-And-Cows/blob/master/final-uml.jpeg)


Task One: Design<br />
For this assignment, there is very little which is already given to you. Through this
assignment, you’ll gain experience in designing and building a complex program from
scratch. Before starting to code , don’t forget to design your classes and methods (i.e. create
UML class and sequence diagrams)! You should apply the concepts you have learned so far
in the course. Don’t have everything in one class, and try to promote code reuse as much as
possible. You must have at least one use of inheritance in your project.
Using either pen & paper or the diagramming tool of your choice, prepare a UML class
diagram which shows all the classes and important methods of your Bulls & Cows
implementation, along with the appropriate relationships showing how these classes fit
together. Include either a PDF, JPEG, or PNG image of your class diagram in the zip file with
your final submission.
IMPORTANT: You should show your design to one of your lecturers / tutors for feedback on
your design. You may include this feedback as part of your reflection.
Note: It’s OK if your implementation doesn’t match your initial design 100% - things change!
You’ll document this process in Task Eight.


Task Two: The Beginning<br />
Implement the first part of the game allowing the player to guess the computer’s secret code.
The computer randomly generates the secret code at the beginning of the game, which it
then lets the player guess. Remember that when generating the computer’s secret code,
each of the four digits must be different. Note that the player only has seven attempts to
guess the secret code. The prompt for player input, results for each guess and the final
outcome (i.e. whether the player has won the game or not) should be displayed
appropriately to the console.


Task Three: Easy AI<br />
Modify your code so that the player can now also enter a secret code when the game
begins, which the computer must guess. Remember to verify that the player has chosen a
valid secret code. The player and computer each take turns guessing the other’s code. The
game ends when either side successfully guesses the other’s code (resulting in a win for that
side), or when each side has made seven incorrect guesses (resulting in a draw).
For this task, have the player play against an easy AI. When the AI makes a guess, it will
simply generate a random (valid) guess.


Task Four: Medium AI<br />
Modify your code so that at the beginning of the game (before the player enters their own
secret code), they will be asked to select either an easy or medium AI opponent to play
against.
If the player chooses to play against an easy AI, the game should proceed in exactly the
same manner as in Task Three. However, if a medium AI is selected, the AI should keep
track of guesses it has already made. The AI will not make the same guess twice.


Task Five: Hard AI<br />
Modify your code so that the placer can additionally choose to play against a hard AI
opponent.
If the player chooses to play against an easy or medium AI, the game should proceed in
exactly the same manner as in Task Three or Four. However, if a hard AI is selected, the
computer should be much more intelligent when guessing, rather than just choosing at
random. One possible strategy for implementing the hard AI is given below.

Task Six: Reading Guesses from a File<br />
Modify your code so that before the game begins, the player is asked whether they wish to
enter their guesses manually, or to automatically guess based on pre-supplied guesses in a
file.
If the first option is chosen, then the game should progress in the same fashion as in Task
Five above. If the second option is chosen, then the following actions should be taken:
Firstly, the player should be asked to enter a filename. If the player enters an invalid
filename, they should be re-prompted until they enter the name of a file that actually exists.
This file should then be read and interpreted as a text file, where each line contains a
separate guess.
Once the file has been read, then the game should proceed as normal. However, when the
player would be prompted to enter a guess, the next guess in the list of pre-supplied
guesses should automatically be chosen instead. If there are no more pre-supplied guesses
(for example, if the player needs to enter their fifth guess but the file only contained four
guesses), then the player should be prompted as normal.


Task Seven: Saving to a File<br />
Modify your code so that, when the game ends (win, lose, or draw), the player is asked if
they wish to save the results to a text file. If they do, then they’ll be prompted to enter a
filename. The game should then save the following information to the given file:
● The player and computer’s secret number
● Each guess that was made, in the same order as occurred during the game, along
with the result of that guess (i.e. how many bulls & cows it got)
● The identity of the winner (or a message stating that the game was a draw)
The data must be readable when opened in a standard text editor.
