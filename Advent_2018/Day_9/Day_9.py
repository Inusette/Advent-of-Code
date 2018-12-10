from collections import deque


FILE_NAME = 'input.txt'


def read_file(file_name):
    """
    opens and reads the file, returning the string
    :param file_name: file to read
    :return: input string
    """
    f = open(file_name, 'r')

    return f.read()


def extract_data(input_string):
    """
    reads the input string and creates a list of integers
    :param input_string: string to process
    :return: int - number of players, int - highest marble value
    """
    line = input_string.replace(' players; last marble is worth', '').replace(' points', '')

    # split the string into a list of strings
    split_data = line.strip().split(' ')

    players = int(split_data[0])

    last_marble = int(split_data[1])

    return players, last_marble


def play_game(players, last_marble):
    """
    Plays the very confusing marble game. Uses a deque - a double sided linked list/queue thing.
    it has an option to rotate the list at a given index. E.G. dq = [1, 2, 3]  dq.rotate(-1) = [2, 3, 1],
    while (on initial list) dq.rotate(1) = [3, 1, 2]. This logic is used to go clockwise or counterclockwise
    in a circle. Where list is traversable as if it were a circle, and elements can be appended in between other
    elements. The current element that is appended is always at the last index of the deck.
    It's complicated >.>
    :param players: int - number of players
    :param last_marble: int - value of the last marble
    :return: dict - player : score - leaderboard after the game has ended
    """

    # the 0 marble is already on the board, start with 1
    current_marble = 1
    leaderboard = {}
    board = deque([0])

    # run the loop until the last marble value has been placed
    while current_marble <= last_marble:

        # iterate over the players starting from 1
        for plr in range(1, players + 1):

            # if the current node is NOT a multiple of 23
            if current_marble % 23:

                # append between the marbles that are 1 and 2 marbles clockwise of the current marble
                # if the deque is rotated by -1, then the new marble will be appended at the end
                board.rotate(-1)
                board.append(current_marble)

            # in case it is dividable by 23
            else:
                # the marble 7 marbles counter-clockwise from the current marble is removed from the circle
                # rotate the deque by 7, pop the last item
                board.rotate(7)

                # vaue of the removed marble is added to the current player's score
                score = board.pop()

                # The marble located immediately clockwise of the marble that was removed
                # becomes the new current marble. Because we rotated, the marble immediately clockwise is now
                # at the beginning of the deque. Rotate the deque again by -1
                board.rotate(-1)

                # the current player keeps the marble they would have placed, adding it to their score
                score += current_marble

                # add the player and the score to the leaderboard
                if plr in leaderboard.keys():
                    leaderboard[plr] += score
                else:
                    leaderboard[plr] = score

            current_marble += 1

    return leaderboard


def find_highscore(leaderboard):
    """
    finds the maximum value in the given dictionary
    :param leaderboard: dict {int: int} - player : score
    :return: max value in the dict.values
    """
    return max(leaderboard.values())


# read the file
input_info = read_file(FILE_NAME)

# extract the relevant data
n_players, end_marble = extract_data(input_info)

# play the game - create the leaderboard
input_leaderboard = play_game(n_players, end_marble)

# find and print the highscore
print(find_highscore(input_leaderboard))

# part two
end_marble_2 = end_marble * 100

input_leaderboard_2 = play_game(n_players, end_marble_2)

print(find_highscore(input_leaderboard_2))
