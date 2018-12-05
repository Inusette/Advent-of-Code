"""
The polymer is formed by smaller units which, when triggered, react with each other such that
two adjacent units of the same type and opposite polarity are destroyed. Units' types are
represented by letters; units' polarity is represented by capitalization. For instance,
r and R are units with the same type but opposite polarity, whereas r and s are entirely
different types and do not react.

For example:

In aA, a and A react, leaving nothing behind.
In abBA, bB destroys itself, leaving aA. As above, this then destroys itself, leaving nothing.
In abAB, no two adjacent units are of the same type, and so nothing happens.
In aabAAB, even though aa and AA are of the same type, their polarities match, and so nothing happens.
Now, consider a larger example, dabAcCaCBAcCcaDA:

dabAcCaCBAcCcaDA  The first 'cC' is removed.
dabAaCBAcCcaDA    This creates 'Aa', which is removed.
dabCBAcCcaDA      Either 'cC' or 'Cc' are removed (the result is the same).
dabCBAcaDA        No further actions can be taken.
After all possible reactions, the resulting polymer contains 10 units.

How many units remain after fully reacting the polymer you scanned?

--- Part Two ---

One of the unit types is causing problems; it's preventing the polymer from collapsing as much as it should.
Your goal is to figure out which unit type is causing the most problems, remove all instances of it
(regardless of polarity), fully react the remaining polymer, and measure its length.

For example, again using the polymer dabAcCaCBAcCcaDA from above:

Removing all A/a units produces dbcCCBcCcD. Fully reacting this polymer produces dbCBcD, which has length 6.
Removing all B/b units produces daAcCaCAcCcaDA. Fully reacting this polymer produces daCAcaDA, which has length 8.
Removing all C/c units produces dabAaBAaDA. Fully reacting this polymer produces daDA, which has length 4.
Removing all D/d units produces abAcCaCBAcCcaA. Fully reacting this polymer produces abCBAc, which has length 6.
In this example, removing all C/c units was best, producing the answer 4.

What is the length of the shortest polymer you can produce by removing all units of exactly
one type and fully reacting the result?

"""


class Stack:
    """
    Class that implements the stack concept
    """
    def __init__(self):
        self.items = []

    def isEmpty(self):
        return self.items == []

    def push(self, item):
        self.items.append(item)

    def pop(self):
        return self.items.pop()

    def peek(self):
        return self.items[len(self.items) - 1]

    def size(self):
        return len(self.items)


FILE_NAME = 'input.txt'


def read_file(file_name):
    """
    opens and reads the file, returning the list of lines (strings)
    :param file_name: file to read (each line is a string)
    :return: list of strings
    """
    f = open(file_name, 'r')

    return f.read()


def process_polymer(polymer):
    """
    processes polymer by performing all of the reacts and finds the size of the end-polymer
    :param polymer: string - to process
    :return: int - the size of the polymer after all reacts have occurred
    """

    # create a stack object for each polymer
    units = Stack()

    # iterate over the units in the polymer
    for unit in polymer:

        # if the stack is empty, push the first element to the stack, ignore other if/elses
        if units.isEmpty():
            units.push(unit)
            continue

        # get the previous unit
        previous = units.peek()

        # if the previous and the current units share the same type (same letters - case ignored)
        if previous.lower() == unit.lower():

            # check if the 2 units react. If so, remove the previous from the stack,
            # otherwise - add the current to the stack
            if check_react(previous, unit):
                units.pop()
            else:
                units.push(unit)

        # if they are not of the same type, push the element to the stack
        else:
            units.push(unit)

    # return the size of the resulting stack
    return units.size()


def check_react(previous, current):
    """
    Checks if 2 units react: if they are the same letter but different casing
    :param previous: string - unit
    :param current: string - unit
    :return: boolean - if react - true
    """

    polarity = False

    if previous.islower() != current.islower():
        polarity = True

    return polarity


def remove_unit(polymer, pr_unit):
    """
    removes a certain character from the string, both in lower and upper case
    :param polymer: string to change - polymer
    :param pr_unit: string to remove - unit
    :return:
    """
    new_polymer = polymer.replace(pr_unit.lower(), '')
    new_polymer = new_polymer.replace(pr_unit.upper(), '')

    return new_polymer


def find_shortest_polymer(polymer):
    """
    find the length of the shortest polymer that can be produced by removing
    all units of exactly one type and fully reacting the result
    :param polymer: string - the polymer to process
    :return: int - the size of the shortest resulting polymer
    """
    # find all unique units in the polymer
    pol_set = set(list(polymer.lower()))

    # create a list to store all possible polymer sizes
    polymer_sizes = []

    # iterate over all unique units
    for unit in pol_set:

        # remove the current unit from the polymer string
        current_polymer = remove_unit(polymer, unit)

        # determine the size of the updated polymer after reacting
        current_size = process_polymer(current_polymer)

        # append the size to the sizes list
        polymer_sizes.append(current_size)

    # find the shortest size in the list
    shortest_pol_size = min(polymer_sizes)

    return shortest_pol_size


# read the input file
input_polymer = read_file(FILE_NAME)

# perform all the reacting in the given polymer and determine the end size
polymer_size = process_polymer(input_polymer)

print('initial polymer after reacting: ', polymer_size)

# find the length of the shortest polymer that can be produced by removing
# all units of exactly one type and fully reacting the result
shortest = find_shortest_polymer(input_polymer)

print('the shortest polymer: ', shortest)
