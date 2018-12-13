FILE_NAME = 'input.txt'


def read_file(file_name):
    """
    opens and reads the file, returning the string
    :param file_name: file to read
    :return: input string
    """
    f = open(file_name, 'r')

    return f.read()


def calculate_power_levels(serial_number):
    """
    constructs a grid with pre-calculated power levels. {y: [cell, cell, cell,]}, where the index of a cell is x
    :param serial_number: int - input
    :return: dict - {y: [cell, cell, cell]}, where the index of a cell is x
    """
    cells = {}

    # the index starts at 1 and ends at 300
    for y in range(1, 301):

        x_values = []

        # calculate the cells' power and append the cell to x list
        for x in range(1, 301):
            cell = calculate_cell(x, y, serial_number)
            x_values.append(cell)

        # add the x list to the corresponding y in the dictionary
        cells[y] = x_values

    return cells


def calculate_cell(x, y, serial_number):
    """
    uses the given formula to calculate the cell's power
    :param x: int - x coordinate
    :param y: int - y coordinate
    :param serial_number: int - input
    :return: int - power of a cell
    """
    cell = 0

    power = ((x + 10) * y + serial_number) * (x + 10)

    if power >= 100:
        tmp = str(power)[-3]
        cell = int(tmp) - 5

    return cell


def calculate_total(cells, x, y, size):
    """
    calculates a total power in the square grid of the given size. Sums up the values of the cells.
    The square begins at top left corner - given x,y coordinates
    :param cells: dict - {y: [cell, cell, cell]}, where the index of a cell is x
    :param x: int - x coordinate of the top left corner
    :param y: int - y coordinate of the top left corner
    :param size: int - size of the square
    :return: int - total sum of all cells in the square
    """
    # instantiate the total sum
    total = 0

    # starting from the beginning x and y coordinates, go over all x,y positions
    # in the range of the square size
    for cur_x in range(x, x + size):
        for cur_y in range(y, y + size):
            total += cells[cur_y][cur_x]

    return total


def find_highest_bysize(cells, box_size):
    """
    finds the square with the largest total power, given the size range of the square
    :param cells: dict - {y: [cell, cell, cell]}, where the index of a cell is x
    :param box_size: size of the largest possible box
    :return: int - highest total power, (int int) - coordinates of the top left corner of the
    box with highest total power, int - size of the box with highest total power
    """
    # instantiate the final values
    coordinates = ()
    total = 0
    final_size = 1

    # go over each size in the given size range
    for size in range(1, box_size + 1):

        print(size)

        # instantiate the total of the current square size
        size_total = 0

        # find the square with the largest total of this size
        for y in range(1, 300 - size + 1):

            for x in range(300 - size):

                cur_total = calculate_total(cells, x, y, size)

                # if the total of the current square is bigger than the current
                # size total, update it
                if cur_total > size_total:
                    size_total = cur_total

                # if the current square total is bigger than the total across
                # all previous sizes, update the values
                if cur_total > total:
                    total = cur_total
                    coordinates = (x + 1, y)
                    final_size = size

        # at some point the highest total of a current size square will become negative.
        # from then on it will stay negative. No need to iterate further. Break out of the loop
        # and return the values of the square with the highest total across all square sizes so far
        if not size_total:
            break

    return total, coordinates, final_size


# read the input file
input_serial_number = int(read_file(FILE_NAME))

# pre-calculate the cells
power_levels = calculate_power_levels(input_serial_number)

# find the 3x3 square with the largest total power
total_3, coordinates_3, size_3 = find_highest_bysize(power_levels, 3)
print('coordinates: ', total_3, ', size: ', coordinates_3, ', total: ', size_3)

# find the square with the largest total power of any size
any_size_total, any_size_coordinates, highest_size = find_highest_bysize(power_levels, 300)
print('coordinates: ', any_size_coordinates, ', size: ', highest_size, ', total: ', any_size_total)
