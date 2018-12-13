FILE_NAME = 'input.txt'


def read_file(file_name):
    """
    opens and reads the file, returning the list of lines (strings)
    :param file_name: file to read (each line is a string)
    :return: list of strings
    """
    f = open(file_name, 'r')

    return f.readlines()


def parse_input(lines):
    """
    extracts the coordinates from the input file. Creates 4 maps, where the key is the count of a position, and
    value is the x/y value.
    :param lines: list of strings - input
    :return: map of position : x value, position : y value, position : x velocity, position : y velocity,
    """

    # instantiate the dictionaries and the count (a position's key)
    positions_x = {}
    positions_y = {}
    velocities_x = {}
    velocities_y = {}
    count = 1

    # go over lines in the input
    for ln in lines:

        # remove the unnecessary bits, and split
        line = ln.replace('position=<', '').replace('>', '').strip().split(' velocity=<')

        # extract the x and y values of a position
        start_x, start_y = get_x_y(line, 0)
        vel_x, vel_y = get_x_y(line, 1)

        # add this position (count - same across all dictionaries) and the corresponding values to their dictionaries
        positions_x[count] = start_x
        positions_y[count] = start_y

        velocities_x[count] = vel_x
        velocities_y[count] = vel_y

        count += 1  # increment the count

    return positions_x, positions_y, velocities_x, velocities_y


def get_x_y(line_list, index):
    """
    etracts the values from the given string, converts to integer
    :param line_list: list of strings - input line after pre-processing
    :param index: 0 - starting position, 1 - velocity
    :return: int, int - x, y values
    """
    position = line_list[index].split(',')
    x = int(position[0])
    y = int(position[1].strip())

    return x, y


def update_positions(current_x_positions, current_y_positions, velocities_x, velocities_y):
    """
    increases the values in current positions maps by adding the corresponding values from the
    velocity maps
    :param current_x_positions: {pos_count: x}
    :param current_y_positions: {pos_count: y}
    :param velocities_x: {pos_count: x velocity}
    :param velocities_y: {pos_count: y velocity}
    :return:
    """
    # make sure to not edit the maps given
    next_x_positions = dict(current_x_positions)
    next_y_positions = dict(current_y_positions)

    # iterate over all position names (counts), add up the values
    for pos in current_x_positions:
        next_x_positions[pos] += velocities_x[pos]
        next_y_positions[pos] += velocities_y[pos]

    return next_x_positions, next_y_positions


def find_message(x_positions, y_positions, velocities_x, velocities_y):

    # find the positions at the next second
    next_x_pos, next_y_pos = update_positions(x_positions, y_positions, velocities_x, velocities_y)

    # second count
    count = 0

    # make sure to not edit the given maps
    curr_x_pos = dict(x_positions)
    curr_y_pos = dict(y_positions)

    # the message appears when all positions come together, so the x,y values are the smallest, then they drift apart
    # so if the current max x value is smaller than the next max x value, then the message appears at the current second
    while max(curr_x_pos.values()) > max(next_x_pos.values()):

        # update current positions
        curr_x_pos = dict(next_x_pos)
        curr_y_pos = dict(next_y_pos)

        # update next positions
        next_x_pos, next_y_pos = update_positions(next_x_pos, next_y_pos, velocities_x, velocities_y)

        # increment the second count
        count += 1

    return curr_x_pos, curr_y_pos, count


def project_positions(x_positions, y_positions):
    """
    plots the given second positions to a grid, with # noting a position and . being empty
    adds +2 +3 +4 to the edges, so that it's pretty
    :param x_positions: {pos_count: x}
    :param y_positions: {pos_count: y}
    :return: list of lists with strings
    """
    # extract the x and y values
    x_values = list(x_positions.values())
    y_values = list(y_positions.values())

    # find the minimum values
    min_x = min(x_values)
    min_y = min(y_values)

    # increment all positions, so that there are no negative numbers.
    for idx in range(len(x_values)):
        x_values[idx] += abs(min_x) + 2

    for idx in range(len(y_values)):
        y_values[idx] += abs(min_y) + 2

    # create a list with all elements being .
    y_axe = ['.'] * (max(y_values) + 3)

    plot = []

    # create a grid using the y_axe lists
    for i in range(max(x_values) + 4):
        plot.append(list(y_axe))

    # find the position in the 2d list and change it to #
    for idx in range(len(x_values)):
        x = x_values[idx]
        y = y_values[idx]

        plot[x][y] = '#'

    return plot


def write_to_file(plot, sec_count):
    """
    writes the given plot/grid/thing to a file
    :param plot: 2d list
    :param sec_count: the current second
    :return:
    """
    sec_filename = './output.txt'

    # open the file to write in it
    sec_file = open(sec_filename, 'w+')

    title = '\n Second ' + str(sec_count) + ': \n'

    sec_file.write(title)

    # go over all days in that trip
    for line in plot:
        ln = ''.join(line)
        sec_file.write(ln)
        sec_file.write('\n')

    sec_file.close()


# read the file
input_lines = read_file(FILE_NAME)

# get the start and velocity values from the input file
start_x_pos, start_y_pos, x_velocities, y_velocities = parse_input(input_lines)

# find the smallest grid - that's where the message is
msg_x_positions, msg_y_positions, second = find_message(start_x_pos, start_y_pos, x_velocities, y_velocities)

# draw an actual grid using the coordinates - FLIP THE X Y AXES
message_plot = project_positions(msg_y_positions, msg_x_positions)

# write the output to a file
write_to_file(message_plot, second)
