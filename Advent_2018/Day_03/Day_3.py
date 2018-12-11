"""
The whole piece of fabric they're working on is a very large square - at least 1000 inches on each side.

Each Elf has made a claim about which area of fabric would be ideal for Santa's suit.
All claims have an ID and consist of a single rectangle with edges parallel to the edges of the fabric.
Each claim's rectangle is defined as follows:

The number of inches between the left edge of the fabric and the left edge of the rectangle.
The number of inches between the top edge of the fabric and the top edge of the rectangle.
The width of the rectangle in inches.
The height of the rectangle in inches.

The problem is that many of the claims overlap, causing two or more claims to cover part of the same areas.
For example, consider the following claims:

#1 @ 1,3: 4x4
#2 @ 3,1: 4x4
#3 @ 5,5: 2x2
Visually, these claim the following areas:

........
...2222.
...2222.
.11XX22.
.11XX22.
.111133.
.111133.
........

The four square inches marked with X are claimed by both 1 and 2.
(Claim 3, while adjacent to the others, does not overlap either of them.)

If the Elves all proceed with their own plans, none of them will have enough fabric.
How many square inches of fabric are within two or more claims?

--- Part Two ---

Amidst the chaos, you notice that exactly one claim doesn't overlap by even a single
square inch of fabric with any other claim. If you can somehow draw attention to it,
maybe the Elves will be able to make Santa's suit after all!

For example, in the claims above, only claim 3 is intact after all claims are made.

What is the ID of the only claim that doesn't overlap?

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


def process_input(input_lines):
    """
    processes all lines in the input and creates a list of claim 'objects' - dictionaries with claim features
    :param input_lines: list of strings - lines from the file
    :return: list of dictionaries - list of all claims' features and their values
    """

    # a list to store all the claim 'objects'
    claims = []

    # iterate over each line and process it to extract claim features
    for line in input_lines:
        claim = process_claim(line)

        # add the claim to the list
        claims.append(claim)

    return claims


def process_claim(line):
    """
    processes a claim by extracting the following features: claim id, left - inches from the left edge,
    top - inches from the top edge, width, and height. Returns a claim object - a dictionary with the features
    and their values
    :param line: string - claim to process
    :return: dictionary - claim features
    """

    # a dictionary to store a claim's features
    claim = {}

    # split the line with a space
    order = line.split(' ')

    # extract the id of the claim, remove the '#' and convert into int
    id = order[0].replace('#', '')
    claim['id'] = int(id)

    # extract the coordinates of the claim (left and top), remove the ':', ',' and convert into ints
    coord = order[2].replace(':', '').split(',')
    claim['left'] = int(coord[0])
    claim['top'] = int(coord[1])

    # extract the dimensions of the claim (width and height), remove the 'x', '\n' and convert into ints
    dimen = order[3].strip().split('x')
    claim['width'] = int(dimen[0])
    claim['height'] = int(dimen[1])

    return claim


def apply_claims(claims):
    """
    finds the area (coordinates) of all claims and assigns the claim ids to their coordinates.
    One set of coordinates (x,y) can have multiple claim ids, if such ids overlap.
    creates a dictionary that contains all the claimed coordinates and the list of ids that belong to these coordinates.
    :param claims: list of dictionaries - claim features
    :return: dictionary - (x, y) : [id, id, id, ...] - area of all claimed coordinates and claim ids
    """

    # a dictionary to store all claim orders
    # key: coordinates, value: list of claim id's that belong at this coordinate
    claim_area = {}

    for claim in claims:

        id = claim['id']

        # get all the coordinates tuples for this claim
        all_coordinates = get_coordinates(claim)

        # iterate over all the coordinates of this claim
        for coordinates in all_coordinates:

            # check if the coordinates are already in the area, if so, add another claim id to the coordinates
            # otherwise add the coordinates and the claim id to the area map
            if coordinates in claim_area.keys():
                claim_area[coordinates].append(id)
            else:
                claim_area[coordinates] = [id]

    return claim_area


def get_coordinates(claim):
    """
    finds all coordinates of the given claim. The 'top' and 'left' features determine the starting point
    on the x, y axes, the width further adds to the x axe, the height further subtracts from the y axe.
    :param claim: the dictionary of claim features and its values
    :return: a list of tuples - all claimed coordinates
    """

    # a list of x and y tuples to store the coordinates
    coordinates = []

    left = claim['left']
    top = - claim['top']

    # go over each coordinate on the x axe (starting from 1 and up to the width)
    for x in range(1, claim['width'] + 1):

        # go over each coordinate on the y axe (starting from 1 and up to the height)
        for y in range(1, claim['height'] + 1):

            # add the resulting coordinates to the list
            coordinates.append((left + x, top - y))

    return coordinates


def get_overlaps(claim_area):
    """
    creates a list of all ids that overlap at all overlapping coordinates
    :param claim_area: dictionary of coordinates and claim ids that belong to them
    :return: list of id lists - all overlapping coordinates (ids that belong there)
    """

    # create a list to store overlapping ids
    overlaps = []

    # iterate over all the lists of ids in the area dictionary
    for claim_ids in claim_area.values():

        # if the size of the list is more than 1, means that there are 2 ids that claim the same
        # coordinates. Add the list of these ids to the overlapping ids list
        if len(claim_ids) > 1:
            overlaps.append(claim_ids)

    return overlaps


def get_overlapping_ids(overlaps):
    """
    creates a set of all claim ids that overlap with other ids (use the same coordinates)
    :param overlaps: list of id lists - all overlapping ids at certain coordinates
    :return: set of int - ids that overlap with other ids
    """
    # set of claim ids that overlap the area of other claim ids
    overlap_ids = set()

    # iterate over all the lists of overlapping ids
    for ids in overlaps:

        # iterate over all the id's in the ids list
        for id in ids:
            overlap_ids.add(id)

    return overlap_ids


def get_unique_claim(claim_area, overlap_ids):
    """
    finds the id that doesn't have any overlapping coordinates with any other ids
    :param claim_area: dictionary - (x, y) : [id, ...] - area of all claimed coordinates and claim ids
    :param overlap_ids: set of int - ids that overlap with other ids
    :return: int - the id of the unique claim
    """

    unique = 0

    # iterate over all the lists of ids in the area dictionary
    for claim_ids in claim_area.values():

        # find the lists of the size 1
        if len(claim_ids) == 1:

            # check if the id in that list is in the overlapping ids set
            if claim_ids[0] not in overlap_ids:

                # if not, assign it to the unique id and break out of the loop
                unique = claim_ids[0]
                break

    return unique


# read the input file
input_file = read_file(FILE_NAME)

# process each line and get all claims information
input_claims = process_input(input_file)

# apply all claims in the input and construct the area map
input_area = apply_claims(input_claims)

# get the overlapping ids:
input_overlaps = get_overlaps(input_area)

# find the coordinates with overlapping claim ids
overlap_sq_inches = len(input_overlaps)

print(overlap_sq_inches)

# find all the ids that overlap with other claim ids:
overlapping_ids = get_overlapping_ids(input_overlaps)

# find the id that doesn't have an overlapping area with any other claim ids
unique_id = get_unique_claim(input_area, overlapping_ids)

print(unique_id)
