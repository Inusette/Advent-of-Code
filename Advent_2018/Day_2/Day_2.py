"""
To make sure you didn't miss any, you scan the likely candidate boxes again,
counting the number that have an ID containing exactly two of any letter and
then separately counting those with exactly three of any letter. You can multiply
those two counts together to get a rudimentary checksum and compare it to what your
device predicts.

For example, if you see the following box IDs:

abcdef contains no letters that appear exactly two or three times.
bababc contains two a and three b, so it counts for both.
abbcde contains two b, but no letter appears exactly three times.
abcccd contains three c, but no letter appears exactly two times.
aabcdd contains two a and two d, but it only counts once.
abcdee contains two e.
ababab contains three a and three b, but it only counts once.

Of these box IDs, four of them contain a letter which appears exactly twice,
and three of them contain a letter which appears exactly three times.
Multiplying these together produces a checksum of 4 * 3 = 12.

What is the checksum for your list of box IDs?

--- Part Two ---
Confident that your list of box IDs is complete, you're ready to find the boxes full of prototype fabric.

The boxes will have IDs which differ by exactly one character at the same position in both strings.
For example, given the following box IDs:

abcde
fghij
klmno
pqrst
fguij
axcye
wvxyz

The IDs abcde and axcye are close, but they differ by two characters (the second and fourth).
However, the IDs fghij and fguij differ by exactly one character, the third (h and u).
Those must be the correct boxes.

What letters are common between the two correct box IDs? (In the example above,
this is found by removing the differing character from either ID, producing fgij.)
"""


FILE_NAME = 'input.txt'


def read_file(file_name):
    """
    opens and reads the file, returning the list of lines (strings)
    :param file_name: file to read (each line is a string)
    :return: list of strings
    """
    f = open(file_name, 'r')

    return f.readlines()


def calculate_ids_checksum(input_ids):
    """
    counts the id's that have duplicate letters and triplicate letters and multiplies the 2 sums
    :param input_ids: list of strings
    :return: the checksum of ids with duplicates and with triplicates
    """

    # list of ids that have at least 1 duplicate
    duplicates = []

    # list of ids that have at least 1 triplicate
    triplicates = []

    # iterate over the ids in the input
    for id in input_ids:

        # dictionary to store each letter and its count in the id
        letter_count = {}

        # iterate over the letter in the id
        # if the letter already appeared in the id, update the letter count,
        # otherwise - add to map with a count of 1
        for letter in id:
            if letter in letter_count.keys():
                letter_count[letter] += 1
            else:
                letter_count[letter] = 1

        # check if there are any duplicate letters in this id
        if 2 in letter_count.values():
            duplicates.append(id)

        # check if there are any triplicate letters in this id
        if 3 in letter_count.values():
            triplicates.append(id)

    # multiply the sums of ids with duplicates and with triplicates
    return len(duplicates) * len(triplicates)


def get_id_pairs(input_ids):
    """
    extracts all permutations of id pairs
    :param input_ids: list of id strings
    :return: list of id tuples
    """

    pairs = []

    # iterate over all of the ids in the input (minus the last one)
    for cur_idx in range(len(input_ids) - 1):

        # iterate over all of the ids in the input (minus the first one)
        for next_idx in range(1, len(input_ids)):

            # get the id at each index and add a permutation tuple to the list of pairs
            pairs.append((input_ids[cur_idx].strip(), input_ids[next_idx].strip()))

    return pairs


def find_common_letters(pairs_list):
    """
    compares the ids in id tuples, if all except 1 letter are the same - returns the common letters of the id tuple
    :param pairs_list: list of id tuples (strings)
    :return: string - common letters of 2
    """
    common = ''

    # all id's are the same length
    id_length = len(pairs_list[0][0])

    # iterate over each tuple in the id pairs list
    for pair in pairs_list:

        com_letters = []

        first = pair[0]
        second = pair[1]

        # compare the letters at the same index
        for idx in range(len(first)):

            # if the letters are the same, add to the common list
            if first[idx] == second[idx]:
                com_letters.append(first[idx])

        # if the 2 ids only differ by 1 letter
        # (if the length of the list is smaller than the length of any id by 1)
        # then the right ids are found
        # convert list into the string and break out of the loop
        if len(com_letters) == id_length - 1:
            common = ''.join(com_letters)
            break

    return common


# read the lines of the file
input_ids = read_file(FILE_NAME)

# find the checksum of the ids with duplicates and with triplicates
checksum = calculate_ids_checksum(input_ids)

print(checksum)

# get all the permutations of id pairs
id_pairs = get_id_pairs(input_ids)

# find the 2 ids that differ only by one letter (get the common letters)
common_letters = find_common_letters(id_pairs)

print(common_letters)


