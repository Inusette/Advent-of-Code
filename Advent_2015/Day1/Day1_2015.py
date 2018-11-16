"""
He starts on the ground floor (floor 0) and then follows the instructions one character at a time.

An opening parenthesis, (, means he should go up one floor, and a closing parenthesis, ), means he should go down one
floor.

Part 1

For example:
(()) and ()() both result in floor 0.
((( and (()(()( both result in floor 3.
))((((( also results in floor 3.
()) and ))( both result in floor -1 (the first basement level).
))) and )())()) both result in floor -3.

Find destination floor

Part 2:

find the position of the first character that causes him to enter the basement (floor -1).
The first character in the instructions has position 1, the second character has position 2, and so on.

For example:
) causes him to enter the basement at character position 1.
()()) causes him to enter the basement at character position 5.

What is the position of the character that causes Santa to first enter the basement?
"""


def read_file(file_name):
    """
    opens and reads the file, returning the string
    :param file_name: name of the file to read
    :return: string in the file
    """
    f = open(file_name, 'r')

    return f.read()


def find_destination(directions):
    """
    finds the end destination
    :param directions: input string
    :return: the destination int
    """

    # create the number for the destination floor
    destination = 0

    # iterate over the symbols in the input file
    for s in range(len(directions)):

        # increment the destination if '(' occurs
        if directions[s] == '(':
            destination += 1

        # reduce the destination if ')' occurs
        elif directions[s] == ')':
            destination += -1

    return destination


def find_basement_position(directions):
    """
    finds the position of the symbol that makes the destination number negative
    :param directions: input string
    :return: the position - int
    """

    # create the number for the destination floor
    destination = 0

    # create the position tracker
    position = 1

    # iterate over the symbols in the input file
    for s in range(len(directions)):

        # increment the destination if '(' occurs
        if directions[s] == '(':
            destination += 1

        # reduce the destination if ')' occurs
        if directions[s] == ')':
            destination += -1

        # if the destination reaches the basement, break out of the loop
        if destination == -1:
            break

        # increment the position
        position += 1

    return position


# specify the name of the input file
input_file = 'input.txt'

# get the string from the input file
input_string = read_file(input_file)

# follow the instructions, calculate how many floors up and down in total
destination_floor = find_destination(input_string)
print(destination_floor)

# find position of entering the basement
basement_position = find_basement_position(input_string)
print(basement_position)
