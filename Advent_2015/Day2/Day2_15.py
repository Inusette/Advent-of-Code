"""
Part 1

The elves are running low on wrapping paper, and so they need to submit an order for more.
They have a list of the dimensions (length l, width w, and height h) of each present,
and only want to order exactly as much as they need.

Fortunately, every present is a box (a perfect right rectangular prism), which makes calculating
the required wrapping paper for each gift a little easier: find the surface area of the box,
which is 2*l*w + 2*w*h + 2*h*l. The elves also need a little extra paper for each present:
the area of the smallest side.

For example:

A present with dimensions 2x3x4 requires 2*6 + 2*12 + 2*8 = 52 square feet of wrapping paper plus 6
square feet of slack, for a total of 58 square feet.
A present with dimensions 1x1x10 requires 2*1 + 2*10 + 2*10 = 42 square feet of wrapping paper plus
1 square foot of slack, for a total of 43 square feet.
All numbers in the elves' list are in feet. How many total square feet of wrapping paper should they order?

Part 2

The ribbon required to wrap a present is the shortest distance around its sides,
or the smallest perimeter of any one face. Each present also requires a bow made out of ribbon as well;
the feet of ribbon required for the perfect bow is equal to the cubic feet of volume of the present.

For example:

A present with dimensions 2x3x4 requires 2+2+3+3 = 10 feet of ribbon to wrap the present
plus 2*3*4 = 24 feet of ribbon for the bow, for a total of 34 feet.
A present with dimensions 1x1x10 requires 1+1+1+1 = 4 feet of ribbon to wrap the present
plus 1*1*10 = 10 feet of ribbon for the bow, for a total of 14 feet.

How many total feet of ribbon should they order?

"""


def read_file(file_name):
    """
    opens and reads the file
    :param file_name: file path
    :return: the list of box dimensions, where each box is represented as a list of 3 strings - dimensions
    """
    # read the file
    f = open(file_name, 'r')

    # create the dimensions list
    dimensions = []

    # go over each line in the file
    for line in f:
        # split the line and add to the dimensions list
        dimensions.append(line.strip().split('x'))

    return dimensions


def calculate_paper(l, w, h):
    """
    calculate the area needed using the given formula: 2*l*w + 2*w*h + 2*h*l + the area of the smallest side.
    :param l: length
    :param w: width
    :param h: height
    :return: int - the area needed (square feet)
    """
    # calculate the area of each side
    side1 = l*w
    side2 = w*h
    side3 = h*l

    # find the smallest area of the 3
    slack = min(side1, side2, side3)

    # calculate the total area of the box + the slack
    result = 2*side1 + 2*side2 + 2*side3 + slack

    return result


def calculate_ribbon(l, w, h):
    """
    calculate the length of the ribbon needed using the given formula: 2*l+ 2*w + l*w*h
    :param l: length
    :param w: width
    :param h: height
    :return: int - the area needed (square feet)
    """

    # calculate the perimeter of each side
    per1 = 2*l + 2*w
    per2 = 2*w + 2*h
    per3 = 2*h + 2*l

    # find the smallest perimeter of the 3
    smallest_side = min(per1, per2, per3)

    # calculate the cubic feet of volume of the present
    bow = l*w*h

    # calculate the total area of the box + the slack
    result = smallest_side + bow

    return result


def calculate_total(dimen_list):
    """
    calculates the total area of paper
    :param dimen_list: list of box dimensions
    :return:
    """

    # instantiate the total number
    total = 0

    # iterate over the dimensions in the dimensions list, convert to int
    for box in dimen_list:
        l = int(box[0])
        w = int(box[1])
        h = int(box[2])

        # calculate the area needed for each box
        # total += calculate_paper(l, w, h)
        total += calculate_ribbon(l, w, h)

    return total


# specify the name of the input file
input_file = 'input.txt'

# get the string from the input file
dimensions_list = read_file(input_file)

# calculate the total area of the paper / length of the ribbon
total_area = calculate_total(dimensions_list)

print(total_area)
