
FILE_NAME = 'input.txt'
ORD_EQLZR = 64
MAX_WORKERS = 5


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


def perform_steps(directions):
    """
    Finds the order in which the steps are performed, deletes the performed steps
    from the directions map until it's empty and the order of steps is assembled
    :param directions: map of String: [String, String] - goals and conditions
    :return: String - steps in order
    """
    # copy the input directions
    steps = dict(directions)

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
        next_step = get_available_steps(steps, performed)[0]

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


def get_available_steps(steps, performed):
    """
    Checks if all conditions for the goal step are already performed. Sort the available steps alphabetically
    :param steps: map of String: [String, String] - goals and conditions
    :param performed: Set of strings - performed steps
    :return:
    """
    # find all goals that can be checked out at this stage
    possible = []

    for goal, conditions in steps.items():

        # check if the conditions are cleared out
        if is_available(conditions, performed):
            possible.append(goal)

    # sort the possible list
    possible = sorted(possible)

    return possible


def calculate_time(directions):
    """
    processes steps at each second until all steps are completed. Distributes the steps in alphabetical order
    to multiple workers that work simultaneously. returns a second that all steps were complete
    :param directions: map of String: [String, String] - goals and conditions
    :return: int - second at which all steps were complete
    """
    # copy the input directions
    steps = dict(directions)

    # create a set to keep track of conditions that were performed
    performed = set()

    # step: [sec]
    in_process = {}

    # actions start at second 0. So all preparations should be done the second before
    current_second = -1

    # while there are no new steps to perform and in process steps
    while any(steps) or any(in_process):

        # check if at this second a step has been finished
        if current_second in in_process.values():

            # if a step has been completed, add to performed and
            # delete the step from the steps list and from the in-process list
            steps_in_process = list(in_process.keys())

            for step in steps_in_process:

                if in_process[step] == current_second:

                    performed.add(step)

                    del steps[step]
                    del in_process[step]

        # find how many workers are available
        idle = MAX_WORKERS - len(in_process)

        # if there are idle workers
        if idle:

            # find all available steps
            possible = get_available_steps(steps, performed)

            # for each available step - assign an idle worker
            for idx in range(len(possible)):

                # if the in process map has less than 5 entries, add new entries
                if len(in_process) < MAX_WORKERS:

                    next_step = possible[idx]

                    # calculate the end second for this step
                    end_sec = get_end_time(current_second, next_step)

                    # if the step is not yet in process, add
                    if next_step not in in_process.keys():
                        in_process[next_step] = end_sec
                else:
                    break

        # update the second count:
        current_second += 1

    return current_second


def get_end_time(current_second, step):
    """
    calculates the second at which the task will end. A step takes 60 seconds plus an amount
    corresponding to its letter: A=1, B=2, C=3, and so on.
    :param current_second: int
    :param step: String
    :return:
    """
    return current_second + 60 + (ord(step) - ORD_EQLZR)


# read the file
input_lines = read_file(FILE_NAME)

# process all directions
input_directions = process_directions(input_lines)

# find the order in which the steps should be performed
directions_order = perform_steps(input_directions)

print(directions_order)

# calculate how long will it take to complete all of the steps with 5 workers
total_step_seconds = calculate_time(input_directions)

print(total_step_seconds)
