
FILE_NAME = 'input.txt'


def read_file(file_name):
    """
    opens and reads the file, returning the list of lines (strings)
    :param file_name: file to read (each line is a string)
    :return: list of strings
    """
    f = open(file_name, 'r')

    return f.readlines()


def process_directions(lines):
    """
    process the input (lines of string) and assemble a map with Steps and their conditions
    S: [A, F]
    :param lines: input lines
    :return: map of String: [String, String]
    """
    # create the map to store the steps and conditions
    steps = {}

    # iterate over the lines, extract the steps and get rid of white spaces
    for line in lines:
        line = line.replace('Step ', '').replace(' must be finished before step', '').replace(' can begin.', '').strip()
        ln = line.split(' ')

        # The second step is the goal step, the first - condition
        goal = ln[1]
        condition = ln[0]

        if goal in steps:
            steps[goal].append(condition)
        else:
            steps[goal] = [condition]

        # if the conditions step doesn't have conditions of its own,
        # assign an empty list to it
        if condition not in steps.keys():
            steps[condition] = []

    return steps


def perform_steps(steps):
    """
    Finds the order in which the steps are performed, deletes the performed steps
    from the directions map until it's empty and the order of steps is assembled
    :param steps: map of String: [String, String] - goals and conditions
    :return: String - steps in order
    """

    # create a set to keep track of conditions that were performed
    performed = set()

    # create the empty string for the order of steps
    order = ''

    # run until the goal/condition map is empty
    while any(steps):

        # find all goals that can be checked out at this stage
        possible = []

        for goal, conditions in steps.items():

            # check if the conditions are cleared out
            if is_available(conditions, performed):
                possible.append(goal)

        # out of the possible steps, find the first one alphabetically
        next_step = sorted(possible)[0]

        # add the first one to the order
        order += next_step

        # delete this step from the map
        del steps[next_step]

        # update the performed set
        performed.add(next_step)

    return order


def is_available(conditions_list, performed):
    """
    if the list is empty or all contents of the conditions list are in the performed set, return true
    :param conditions_list: list of Steps
    :param performed: set of steps that were performed
    :return: boolean
    """
    available = False

    if set(conditions_list).issubset(performed):
        available = True

    return available


# read the file
input_lines = read_file(FILE_NAME)

# process all directions
directions = process_directions(input_lines)

print(directions)

# find the order in which the steps should be performed
directions_order = perform_steps(directions)

print(directions_order)
