"""
The spreadsheet consists of rows of apparently-random numbers.
Need to calculate the spreadsheet's checksum.
For each row, determine the difference between the largest value and the smallest value;
the checksum is the sum of all of these differences.
"""
import numpy as np


# the input captcha
puzzle_file = open("./puzzle_input")

# instantiate the sum
checksum = 0

# read thr lines in the file
for line in puzzle_file.readlines():

    # split the line into a list and convert string values into integers
    numbs = map(int, line.split())

    # create the array out of the number values list
    row_array = np.asarray(numbs)

    # find the max/min difference and update the checksum
    checksum += (np.amax(row_array) - np.amin(row_array))


print "answer: ", checksum
