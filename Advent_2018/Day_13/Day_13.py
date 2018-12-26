
FILE_NAME = 'input.txt'

VELOCITIES = {'up': (-1, 0), 'down': (1, 0), 'right': (0, 1), 'left': (0, -1)}

CARTS = {'^': 'up', 'v': 'down', '>': 'right', '<': 'left'}

INTERSECTIONS = {'left': {'right': 'up', 'left': 'down', 'up': 'left', 'down': 'right'},
                 'right': {'right': 'down', 'left': 'up', 'up': 'right', 'down': 'left'},
                 'straight': {'right': 'right', 'left': 'left', 'up': 'up', 'down': 'down'}}

CURVES = {'/': {'right': 'up', 'left': 'down', 'up': 'right', 'down': 'left'},
          '\\': {'right': 'down', 'left': 'up', 'up': 'left', 'down': 'right'}}


class Cart(object):
    """
    Class to represent a Cart object. Has 2 attributes: position - coordinates tuple (x,y)
    and intersection - string - the direction the cart should take at a next intersection
    """
    def __init__(self, x_pos, y_pos, intersection, direction):
        self.x_pos = x_pos
        self.y_pos = y_pos
        self.intersection = intersection
        self.direction = direction

    def update_intersection(self):
            if self.intersection == 'left':
                self.intersection = 'straight'
            elif self.intersection == 'straight':
                self.intersection = 'right'
            elif self.intersection == 'right':
                self.intersection = 'left'

    def update_position(self, x_pos, y_pos):
        self.x_pos = x_pos
        self.y_pos = y_pos

    def update_direction(self, direction):
        self.direction = direction

    def __repr__(self):
        return '{}: {} {} {} {}'.format(self.__class__.__name__, self.x_pos, self.y_pos,
                                        self.direction, self.intersection)


def read_file(file_name):
    """
    opens and reads the file, returning the list of string lines
    :param file_name: file to read
    :return: input list of strings
    """
    f = open(file_name, 'r')

    return f.readlines()


def process_map(lines):
    """
    reads the map, extracts the street map with the cart symbols, constructs a dictionary with the cart objects,
    and constructs a street map that doesn't have any car symbols on it
    :param lines: list of strings - input
    :return: 2d list with characters - streets with cars; 2d list with characters - empty streets,
            dict {y: [cart, cart, cart]} - dictionary with cart objects
    """

    # instantiate all the necessary lists
    streets = []
    empty_streets = []
    carts_pos = {}

    # go over all the rows
    for y in range(len(lines)):

        line = lines[y].replace('\n', '')

        # go over all the elements in the row
        for x in range(len(line)):

            element = line[x].strip()

            # check if it is a cart, if so - create a cart object
            if element in CARTS.keys():

                # create object
                intersection = 'left'
                direction = CARTS[element]

                # flip x and y axes for better visualisation
                cart = Cart(x, y, intersection, direction)

                if y in carts_pos.keys():
                    carts_pos[y].append(cart)
                else:
                    carts_pos[y] = [cart]

        # replace all the current cart symbols with a uniform cart symbol
        st_with_cars = line.replace('v', 'o').replace('^', 'o').replace('>', 'o').replace('<', 'o')

        # replace all the current cart symbols with the empty street symbols
        empty_st = line.replace('v', '|').replace('^', '|').replace('>', '-').replace('<', '-')

        # append the streets
        streets.append(list(st_with_cars))
        empty_streets.append(list(empty_st))

    return streets, empty_streets, carts_pos


def get_key(cart):
    return cart.x_pos


def sort_carts(carts):
    """
    sorts the cart objects from the smallest y to the biggest y, and from the smallest x to the
    biggest x. So, if there are more carts on the same row, they will be in the ascending order
    :param carts: dict {y: [cart, cart, cart]}
    :return: sorted list [cart, cart, cart]
    """
    # create a list to store cart objects
    sorted_carts = []

    # sort the keys in the cart map
    for y in sorted(carts.keys()):

        # sort the values list and add items to the cart objects list
        cart_list = carts[y]
        cart_list = sorted(cart_list, key=get_key)

        sorted_carts.extend(cart_list)

    return sorted_carts


def make_turn(next_element, cart):
    """
    changes the direction of a cart if it encounters a curve or an intersection
    :param next_element: string - the encountered element
    :param cart: Cart object - current cart
    :return: Cart object - updated cart
    """

    # if the element is an intersection - update the cart's direction and intersection
    if next_element == '+':

        # find the new direction, given the cart's intersection count and it's current direction
        new_direction = INTERSECTIONS[cart.intersection][cart.direction]

        # update the cart's direction with the new one
        cart.update_direction(new_direction)

        # update the intersection count
        cart.update_intersection()

    # if the element is a turn
    else:
        # find the new direction, given the turn and cart's current direction
        new_direction = CURVES[next_element][cart.direction]
        cart.update_direction(new_direction)

    return cart


def crash_all(in_carts, empty, in_streets):
    """
    moves the carts around the street map. If the 2 cars crash, they are removed from the map. Works until
    only 1 cart is left on the map. returns the list with all the collision coordinates, as well as the dictionary
    with the cart object - the last cart left
    :param in_carts: dict {y: [cart, cart, cart]}
    :param empty: 2d list with characters - empty streets
    :param in_streets: 2d list with characters - streets with cars
    :return: list of tuples - collision coordinates, dict {y: [cart]} - last uncrashed cart
    """
    # create a tuple to store collision coordinates
    collision_coords = []

    # keep track of the ticks
    tick = 0

    # make sure to not change the given parameters
    streets = list(in_streets)
    carts = dict(in_carts)

    while len(carts) != 1:

        collision = False

        # go over all carts
        tick += 1
        print(tick)

        # get the list of cart objects in the correct order
        sorted_carts = sort_carts(carts)

        # create a new dictionary to store moved carts
        updated_carts = {}

        # go over all carts
        for cart in sorted_carts:

            # remove the current cart from the street map by getting the element with the same coordinates from
            # the empty street map
            empty_element = empty[cart.y_pos][cart.x_pos]
            streets[cart.y_pos][cart.x_pos] = empty_element

            # check if this car has been crashed into:
            if collision:
                if (cart.x_pos, cart.y_pos) == (collision_coords[len(collision_coords) - 1]):
                    continue

            # find the index of the next element
            velocity = VELOCITIES[cart.direction]
            next_y = cart.y_pos + velocity[0]
            next_x = cart.x_pos + velocity[1]

            # get the next element
            next_element = streets[next_y][next_x]

            # if the next element is another cart - collision happens
            if next_element == 'o':
                collision = True
                collision_coords.append((next_x, next_y))

                # if the crashed into car is in the updated carts dictionary, remove it
                if next_y in updated_carts.keys():
                    if len(updated_carts[next_y]) == 1:
                        del updated_carts[next_y]
                    else:
                        for crt in updated_carts[next_y]:
                            if crt.x_pos == next_x:
                                updated_carts[next_y].remove(crt)

                # remove the crash incident from the map
                empty_crash = empty[next_y][next_x]
                streets[next_y][next_x] = empty_crash
                continue

            elif next_element not in {'-', '|'}:
                cart = make_turn(next_element, cart)

            # update the street map - move the cart
            streets[next_y][next_x] = 'o'

            # update the current position of the cart object
            cart.update_position(next_x, next_y)

            # add the cart object to the new cart dictionary
            if next_y in updated_carts.keys():
                updated_carts[next_y].append(cart)
            else:
                updated_carts[next_y] = [cart]

        # update the cart dictionary
        carts = dict(updated_carts)

    return collision_coords, carts


input_lines = read_file(FILE_NAME)

street_map, empty_map, cart_positions = process_map(input_lines)

all_collisions, left_carts = crash_all(cart_positions, empty_map, street_map)

print(all_collisions)

print('first collision: ', all_collisions[0])
print('coordinates of the only cart left: ', left_carts)
