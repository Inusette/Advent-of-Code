"""
Now, a valid passphrase must contain no two words that are anagrams of each other - that is, a passphrase is invalid if
any word's letters can be rearranged to form any other word in the passphrase.
For example:
abcde fghij is a valid passphrase.
abcde xyz ecdab is not valid - the letters from the third word can be rearranged to form the first word.
a ab abc abd abf abj is a valid passphrase, because all letters need to be used when forming another word.
iiii oiii ooii oooi oooo is valid.
oiii ioii iioi iiio is not valid - any of these words can be rearranged to form any other word.
Under this new system policy, how many passphrases are valid?
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

    # create the list of sorted words
    re_phrase = []

    # for each word in the phrase
    for word in phrase:

        # split the word into the list of characters
        word_chars = list(word)

        # sort them
        word_chars.sort()

        # assemble the string back
        s_word = ''.join(word_chars)

        # and add to the sorted list
        re_phrase.append(s_word)

        # check if the word occurs more than once in the phrase
        if re_phrase.count(s_word) > 1:

            # update the validity and break out of the loop
            valid = False
            break

    # if the phrase is still valid, update the checksum
    if valid:
        checksum += 1


# print the result
print "valid pass phrases: ", checksum
