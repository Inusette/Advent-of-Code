"""
A value like +6 means the current frequency increases by 6;
a value like -3 means the current frequency decreases by 3.

For example, if the device displays frequency changes of +1, -2, +3, +1,
then starting from a frequency of zero, the following changes would occur:

+1, +1, +1 results in  3
+1, +1, -2 results in  0
-1, -2, -3 results in -6

Starting with a frequency of zero, what is the resulting frequency after
all of the changes in frequency have been applied?

--- Part Two ---
You notice that the device repeats the same frequency change list over and over.
To calibrate the device, you need to find the first frequency it reaches twice.

For example, using the same list of changes above, the device would loop as follows:

Current frequency  0, change of +1; resulting frequency  1.
Current frequency  1, change of -2; resulting frequency -1.
Current frequency -1, change of +3; resulting frequency  2.
Current frequency  2, change of +1; resulting frequency  3.
(At this point, the device continues from the start of the list.)
Current frequency  3, change of +1; resulting frequency  4.
Current frequency  4, change of -2; resulting frequency  2, which has already been seen.

In this example, the first frequency reached twice is 2. Note that your device might need
to repeat its list of frequency changes many times before a duplicate frequency is found,
and that duplicates might be found while in the middle of processing the list.

Here are other examples:

+1, -1 first reaches 0 twice.
+3, +3, +4, -2, -4 first reaches 10 twice.
-6, +3, +8, +5, -6 first reaches 5 twice.
+7, +7, -2, -7, -4 first reaches 14 twice.

What is the first frequency your device reaches twice?

"""

FILE_NAME = 'input.txt'


def read_file(file_name):
    """
    opens and reads the file, returning the list of numbers
    :param file_name: file to read (each line is a number)
    :return: list of integers
    """
    f = open(file_name, 'r')

    lines = f.readlines()

    # convert the list of strings into the list of ints
    numbers = [int(i) for i in lines]

    return numbers


def find_sum(input):
    """
    adds up all of the integers in the given list
    :param input: list of numbers to sum up
    :return: the result after summing up all numbers
    """
    # the current sum
    sum = 0

    # iterate over the numbers and add them up
    for n in input:
        sum += n

    return sum


def find_first_duplicate(input):
    """
    wrapper method for the recursive method
    :param input: list of numbers
    :return: the first frequency duplicate - int
    """

    # the current sum
    sum = 0

    # the set of frequencies - sums at each addition of a new frequency
    # using set for efficiency purposes - has the constant lookup time
    freqs = {0}

    # call the recursive method
    return find_duplicate(input, freqs, sum)


def find_duplicate(input, freqs, sum):
    """
    recursively iterates over the list of numbers until adding them up produces a sum that has occurred before
    :param input: list of numbers
    :param freqs: set of sums
    :param sum: int - current frequency
    :return: int - the first sum that has occurred before
    """

    # create the parameter variables
    current_sum = sum
    current_freqs = freqs

    # iterate over the numbers in the input
    for n in input:

        # update the sum
        current_sum += n

        # if the new frequency is already in the set, break out of the recursion and return the frequency
        if current_sum in current_freqs:
            return current_sum

        # else add the new frequency to the set
        else:
            current_freqs.add(current_sum)

    # recursively call the method with the updated sum and frequency set
    return find_duplicate(input, current_freqs, current_sum)


def find_duplicate_alt(input):
    """
    an alternative way to find the frequenc that has already occured before. Is not a recursive method
    :param input: list of numbers
    :return: int - the first sum that has occurred before
    """
    # the set of frequencies - sums at each addition of a new frequency
    freqs = set()

    # the current sum/frequency
    sum = 0

    # the current index
    cur_indx = 0

    # stop the loop if the sum is already in the frequency set
    while sum not in freqs:

        # add the current sum to the frequency set
        freqs.add(sum)

        # update the current sum
        sum += input[cur_indx]

        # update the current index
        # when the index reaches the last index of the list, it updates to being 0 again
        # % is the remainder division. So if the last index is 5 this will happen:
        # cur_indx = 5 / remainder(5 / 5) = 5 / 0 = 0
        cur_indx = (cur_indx + 1) % len(input)

    return sum


# read the lines in the file
input_numbers = read_file(FILE_NAME)

# find the sum of all the numbers
result_frequency = find_sum(input_numbers)

print(result_frequency)

# find the first frequency duplicate
first_duplicate = find_first_duplicate(input_numbers)

print(first_duplicate)

# use an alternative non-recursive method to find the duplicate
alt_first_duplicate = find_duplicate_alt(input_numbers)

print(alt_first_duplicate)
