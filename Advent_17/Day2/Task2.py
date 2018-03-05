"""
Goal is to find the only two numbers in each row where one evenly divides the other - that is,
where the result of the division operation is a whole number.
They would like you to find those numbers on each line, divide them, and add up each line's result.
"""
import numpy as np


# open the input file
puzzle_file = open("./puzzle_input")

# instantiate the sum
checksum = 0

# read thr lines in the file
for line in puzzle_file.readlines():

    # split the line into a list and convert string values into integers
    numbs = map(float, line.split())

    # create the array out of the number values list
    row_array = np.asarray(numbs)

    # for each number in the row
    for num in numbs:

        # iterate the row again to find a suitable divisor
        for div in numbs:

            # make sure the divisor is smaller and isn't the same as the number
            if div != num and div < num:

                # do the division
                res = num / div

                # check if the result is a whole number
                if float.is_integer(res):

                    # update the checksum
                    checksum += int(res)

print "answer: ", checksum
