FILE_NAME = 'input.txt'
TOTAL_DISTANCE = 10000


def read_file(file_name):
    """
    opens and reads the file, returning the list of lines (strings)
    :param file_name: file to read (each line is a string)
    :return: list of strings
    """
    f = open(file_name, 'r')

    return f.readlines()


def process_destinations(f_lines):
    """
    processes each line, creating a list of destination coordinates (for easier
    understanding flips the input, assigning the fisrt number to y, and the second number to x)
    :param f_lines: lines of the input file
    :return: list of int tuples - destination coordinates
    """
    coords = []

    # iterate over each line
    for line in f_lines:

        # split into 2 numbers, get rid of whitespaces,
        # convert into integers and add the tuple to the list
        ln = line.split(',')

        x = int(ln[1].strip())
        y = int(ln[0].strip())

        coords.append((x, y))

    return coords


def get_edge_values(destinations):
    """
    finds the smallest and the biggest values for x and y
    :param destinations: list of int tuples - destination coordinates
    :return: 4 x int: smallest x, smallest y, biggest x, biggest y
    """
    x_values = []

    y_values = []

    # extract all x values and all y values and add them to the corresponding lists
    for coord in destinations:
        x_values.append(coord[0])
        y_values.append(coord[1])

    # calculate and return the min's and max's

    return min(x_values), min(y_values), max(x_values), max(y_values)


def generate_all_coordinates(destinations):
    """
    given smallest and biggest x and y values, generates a 2d space of coordinates within the set limits
    :param destinations: list of int tuples - destination coordinates
    :return: list of int tuples - all coordinates in the set space
    """
    all_coords = []

    # find the edges
    smallest_x, smallest_y, biggest_x, biggest_y = get_edge_values(destinations)

    # generate the coordinates within the limits of the found edges
    for x in range(smallest_x, biggest_x + 1):
        for y in range(smallest_y, biggest_y + 1):
            all_coords.append((x, y))

    return all_coords


def count_neighbours(locations, destinations):
    """
    counts the neighbours of each destination (uses Manhattan distance; locations that are equally
    far away from 2 destinations are ignored; includes the destination's location as a neighbour)
    :param locations: list of int tuples - locations
    :param destinations: list of int tuples - destinations
    :return: map of destinations and their area sizes
    """
    # dictionary to store the destination coordinates and count of their closest neighbours
    areas = {}

    # add all given destinations to the dictionary with a count of 0 neighbours
    for d in destinations:
        areas[d] = 0

    # iterate over all locations
    for curr_coord in locations:

        # create a map to store a distance and destination(s) at this distance
        curr_distances = {}

        # go over all gestinations
        for dest_coord in destinations:

            # calculate the distance between the current location an this destination
            curr_dist = manhattan_distance(curr_coord, dest_coord)

            # add the distance and the destination at this distance to the map
            if curr_dist in curr_distances:
                curr_distances[curr_dist].append(dest_coord)
            else:
                curr_distances[curr_dist] = [dest_coord]

        # find the smallest distance:
        smallest = min(list(curr_distances.keys()))

        # if the location is equally far away from 2 destinations (distance has more than 1 destination), ignore it
        if len(curr_distances[smallest]) > 1:
            continue

        # get the closest destination
        closest_destination = curr_distances[smallest][0]

        # add the destination to the list (if not there already) and increment the neighbour count
        areas[closest_destination] += 1

    return areas


def manhattan_distance(cur_coord, dest_coord):
    """
    calculates the manhattan distance between 2 given points (in 2 dimensions)
    :param cur_coord: int tuple - first point
    :param dest_coord: int tuple - second point
    :return: int - manhattan distance
    """
    return abs(cur_coord[0] - dest_coord[0]) + abs(cur_coord[1] - dest_coord[1])


def generate_rims(destinations):
    """
    generates a set of coordinates that are located at the edges of the input space
    :param destinations: list of int tuples - destinations
    :return: set of tuples - locations coordinates
    """
    # find the edges
    smallest_x, smallest_y, biggest_x, biggest_y = get_edge_values(destinations)

    # create a list to store all coordinates that are generated at the perimeter of the space
    rims = []

    # left_rim
    rims.extend(generate_vert_rim(smallest_x, biggest_y))
    # bottom_rim
    rims.extend(generate_hor_rim(biggest_x, biggest_y))
    # right
    rims.extend(generate_vert_rim(biggest_x, biggest_y))
    # top
    rims.extend(generate_hor_rim(biggest_x, smallest_y))

    # remove multiple occurrences of the same coordinates
    rim_ccords = set(rims)

    return rim_ccords


def generate_vert_rim(x, y):
    """
    generates coordinates for the y span, with x staying the same
    :param x: int - x value
    :param y: int - y value
    :return: list of tuples - coordinates
    """

    rim = []

    for v in range(y + 1):
        rim.append((x, v))

    return rim


def generate_hor_rim(x, y):
    """
    generates coordinates for the x span, with y staying the same
    :param x: int - x value
    :param y: int - y value
    :return: list of tuples - coordinates
    """
    rim = []

    for v in range(x + 1):
        rim.append((v, y))

    return rim


def find_finite_destinations(destinations):
    """
    finds the finite destinations by determining the closest destinations for coordinates
    at the rims of the 2d space. If rim coordinates do not reach destinations, then these destinations are
    within the space and have a finite count of neighbours
    :param destinations: list of int tuples - destinations
    :return: list of int tuples - coordinates of the finite destinations
    """

    # generate the set of rim coordinates
    rims = generate_rims(destinations)

    # create a list to store finite destinations
    finite_destinations = []

    # to each given destination count their neighbours (only for rims locations)
    rim_destinations = count_neighbours(rims, destinations)

    # if a destination does't have a neighbour at the rims, then it is finite
    for rim_dest, n_count in rim_destinations.items():
        if n_count == 0:
            finite_destinations.append(rim_dest)

    return finite_destinations


def get_finite_distances(finite_destinations, areas):
    """
    extracts the subset of the map - only with relevant keys and values
    :param finite_destinations: list of int tuples - coordinates of the finite destinations
    :param areas: map of destinations and their area sizes
    :return: map of destinations and their area sizes
    """
    fin_distances = {}

    for dest in finite_destinations:
        fin_distances[dest] = areas[dest]

    return fin_distances


def find_shared_area(locations, destinations):
    """
    Finds the size of the region containing all locations which have a total distance to all
    given destinations of less than 10000
    :param locations: list of int tuples - locations
    :param destinations: list of int tuples - destinations
    :return: int - size of the region
    """
    # dictionary to store the destination coordinates and count of their closest neighbours
    shared_region_size = 0

    # iterate over all locations
    for curr_coord in locations:

        # create a map to store a destination and the distance to it
        curr_distances = {}

        # go over all gestinations
        for dest_coord in destinations:

            # calculate the distance between the current location an this destination
            curr_dist = manhattan_distance(curr_coord, dest_coord)

            # add the distance and the destination at this distance to the map
            curr_distances[dest_coord] = curr_dist

        # check if this location has a total distance to all given destinations of less than 10000
        if within_region(curr_distances):
            shared_region_size += 1

    return shared_region_size


def within_region(distances):
    """
    cheks if the sum of all values in the given map is smaller than the Total Distance
    :param distances: map (int, int): int - of destinations and distances to them
    :return: boolean
    """
    is_within = False
    t_sum = 0

    for distance in distances.values():
        t_sum += distance

    if t_sum < TOTAL_DISTANCE:
        is_within = True

    return is_within


# read the lines in the file
input_lines = read_file(FILE_NAME)

# extract the coordinates from the input file
input_destinations = process_destinations(input_lines)

# generate all possible coordinates from the input destination coordinates
all_coordinates = generate_all_coordinates(input_destinations)

# assign neighbour count to each input destination
all_distances = count_neighbours(all_coordinates, input_destinations)

# determine the finite destinations
finite_input_destinations = find_finite_destinations(input_destinations)

# extract the finite destinations and their neighbour count
finite_distances = get_finite_distances(finite_input_destinations, all_distances)

# find the largest neighbouк count
biggest_area = max(finite_distances.values())

print(biggest_area)

# Find the size of the region containing all locations which have a total distance to all given destinations
# of less than 10000
print(find_shared_area(all_coordinates, input_destinations))
