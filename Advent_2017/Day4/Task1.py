"""
a valid passphrase must contain no duplicate words.
For example:
aa bb cc dd ee is valid.
aa bb cc dd aa is not valid - the word aa appears more than once.
aa bb cc dd aaa is valid - aa and aaa count as different words.
The system's full passphrase list is available as your puzzle input. How many passphrases are valid?
"""


# open the input file
puzzle_file = open("./puzzle_input")

# instantiate the sum
checksum = 0

# read the file
for line in puzzle_file.readlines():

    # get rid of whitespace and turn the line into a list
    phrase = line.strip().split()

    # instantiate the validity value
    valid = True

    # for every word in the phrase list
    for word in phrase:

        # if the word occurs more than once
        if phrase.count(word) > 1:

            # update the validity to false and break out of the loop
            valid = False
            break

    # if loop finished without the validity changing, update the checksum
    if valid:
        checksum += 1

# print the result
print "valid pass phrases: ", checksum
