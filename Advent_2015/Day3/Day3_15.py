"""
Part 1 --------------------------------------------

Santa is delivering presents to an infinite two-dimensional grid of houses.

He begins by delivering a present to the house at his starting location, and then an elf at the North Pole
calls him via radio and tells him where to move next. Moves are always exactly one house to the north (^),
south (v), east (>), or west (<). After each move, he delivers another present to the house at his new location.
However, the elf back at the north pole has had a little too much eggnog, and so his directions are a little off,
and Santa ends up visiting some houses more than once. How many houses receive at least one present?

For example:

 > delivers presents to 2 houses: one at the starting location, and one to the east.
 ^>v< delivers presents to 4 houses in a square, including twice to the house at his starting/ending location.
 ^v^v^v^v^v delivers a bunch of presents to some very lucky children at only 2 houses.

Part 2 ---------------------------------------------

The next year, to speed up the process, Santa creates a robot version of himself, Robo-Santa, to deliver
presents with him. Santa and Robo-Santa start at the same location (delivering two presents to the same starting house),
then take turns moving based on instructions from the elf, who is eggnoggedly reading from the same script as
the previous year. This year, how many houses receive at least one present?

For example:

^v delivers presents to 3 houses, because Santa goes north, and then Robo-Santa goes south.
^>v< now delivers presents to 3 houses, and Santa and Robo-Santa end up back where they started.
^v^v^v^v^v now delivers presents to 11 houses, with Santa going one direction and Robo-Santa going the other.


"""


def read_file(file_name):
    """
    opens and reads the file, returning the string
    :param file_name: name of the file to read
    :return: string in the file
    """
    f = open(file_name, 'r')

    return f.read()


def split_directions(directions):
    """
    splits a string in 2 parts with one item going to the first string and the next to the second string, etc.
    :param directions: string to split
    :return: 2 resulting strings
    """

    # create the new strings
    santa_directions = ''
    robot_directions = ''

    # iterate over the indexes of the given string
    for i in range(len(directions)):

        # if the index is an even number, add to first string, otherwise - to the second
        if i % 2 == 0:
            robot_directions += directions[i]
        else:
            santa_directions += directions[i]

    return santa_directions, robot_directions


def count_visits(directions):
    """
    follows the directions of the given string, creating a map of coordinates and number of visits
    :param directions: string with directions
    :return: map of coordinates (houses) and number of times they were visited
    """
    # coordinates of the current location
    x = 0
    y = 0

    # map the tracks where santa goes. Keys are coordinates, values - visit counts
    track = {(x, y): 1}

    # each house is represented as a tuple of x and y coordinates.
    # '<' and '>' change the x and '^' and 'v' change the y
    # iterate over the characters in the given input string
    for d in directions:

        if d == '>':
            x += 1
        elif d == '^':
            y += 1
        elif d == 'v':
            y += -1
        elif d == '<':
            x += -1

        # if the coordinates are not in the map, add them with the visit count
        if (x, y) not in track.keys():
            track[(x, y)] = 1

        # if the coordinates already exist in the map - update the visit count
        else:
            track[(x, y)] += 1

    return track


# specify the name of the input file
input_file = 'input.txt'

# get the string from the input file
input_string = read_file(input_file)

# calculate the number of houses visited
total_houses = count_visits(input_string)

print('only Santa: ', len(total_houses))

# split the input string into 2, where every other element goes into a sring
santa, robot = split_directions(input_string)

# calculate the number of houses visited by santa
santa_houses = count_visits(santa)

# calculate the number of houses visited by robo-santa
robot_houses = count_visits(robot)

# create a dictionary to store houses visited only by santa
unique_santa_houses = {}

# check if there are overlapping coordinates
for coo in santa_houses:
    if coo not in robot_houses.keys():
        # if the house was not visited by robot, add to unique santa list
        unique_santa_houses[coo] = santa_houses[coo]

# sum up the houses visited by both santa and robo-santa
robo_santa_visits = len(unique_santa_houses) + len(robot_houses)

print('houses visited by santa and robo-santa: ', robo_santa_visits)
