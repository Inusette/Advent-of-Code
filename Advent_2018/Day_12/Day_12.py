FILE_NAME = 'input.txt'


def read_file(file_name):
    """
    opens and reads the file, returning the list of lines (strings)
    :param file_name: file to read (each line is a string)
    :return: list of strings
    """
    f = open(file_name, 'r')

    return f.readlines()


def extract_rules(lines):
    """
    extracts the relevant rules (the ones that produce a plant) and returns a set of them, for better search time
    :param lines: list of strings - input
    :return: set of strings - rules
    """

    # instantiate the rules set
    rules = set()

    # the rules start on line 3
    for l in range(2, len(lines)):
        line = lines[l].split('=>')
        rule = line[0].strip()
        result = line[1].strip()

        if result == '#':
            rules.add(rule)

    return rules


def extract_initial(lines):
    """
    gets the initial state of the pots - first line of input
    :param lines: list of strings - input
    :return: string
    """
    return lines[0].replace('initial state: ', '').strip()


def predict_generations(initial, rules, n_gens):
    """
    given the initial state of pots, the rules and the number of generations, determines which pots
    will have the plants after the span of the given number of generations. Only returns the pot/plant
    distribution for the last generation
    :param initial: string - initial state
    :param rules: set of strings - rules
    :param n_gens: int - number of generations
    :return: string - pot/plant distribution
    """

    # instantiate the variables
    gen_count = 1
    current_gen = initial

    # loop until the final generation
    while gen_count <= n_gens:

        # append 2 empty pots at the edges
        current_gen = '..' + current_gen + '..'

        # append the same 2 pots to the next generation pots
        next_gen = '..'

        # since the first 2 elements have just been added, start from the third.
        # loop until the one before the last 2 appended empty pots
        for idx in range(2, (len(current_gen) - 2)):

            # extract the pattern for the current pot
            pattern = current_gen[idx - 2] + current_gen[idx - 1] + current_gen[idx] + \
                      current_gen[idx + 1] + current_gen[idx + 2]

            # check if it is in rules and add the corresponding result to the next generation
            if pattern in rules:
                next_gen += '#'
            else:
                next_gen += '.'

            # if it was the last index in the range
            # append the same 2 empty pots to the end of the next generation pots
            if idx == len(current_gen) - 3:
                next_gen += '..'

        # update variables
        gen_count += 1
        current_gen = next_gen

    return current_gen


def generate_pot_numbers(initial, last_gen):
    """
    given that the initial state starts at pot 0, and more pots are added on both sides (negative and positive),
    calculates how many pots were added since the initial state and what values (negative and positive) each of the
    pots have
    :param initial: string - initial state
    :param last_gen: int -the number of generations
    :return: list of ints - pot numbers
    """
    gen_pot_numbers = []

    # I add 4 empty pots to the edges of each generation
    last_gen_addition = last_gen * 4

    # calculate at which index does the 0 pot start
    edge_addition = int(last_gen_addition / 2)

    # starting from the lowest negative number,add the values to the list
    for pot in range(-edge_addition, (len(initial) + edge_addition)):
        gen_pot_numbers.append(pot)

    return gen_pot_numbers


def calculate_plants(nth_generation, gen_pot_numbers):
    """
    given the plant distribution and the list of pot values after the n generations, calculates the
    sum of pot values that have a plant
    :param nth_generation: string - pot/plant distribution
    :param gen_pot_numbers: list of int - pot values
    :return:
    """
    total = 0

    # go over pots in this generation
    for pot_idx in range(len(nth_generation)):

        if nth_generation[pot_idx] == '#':
            total += gen_pot_numbers[pot_idx]

    return total


# read the file
input_lines = read_file(FILE_NAME)

# get the rules
input_rules = extract_rules(input_lines)

# get the initial state
initial_state = extract_initial(input_lines)

# get the plant distribution after 20 generations
generation_20 = predict_generations(initial_state, input_rules, 20)

# find the pot values after 20 generations
generations_pot_numbers = generate_pot_numbers(initial_state, 20)

# calculate the total of the pot values with plants after generation 20.
total_plants = calculate_plants(generation_20, generations_pot_numbers)
print(total_plants)

# --------------- the total is 1991! the year I was born! whaaaaaaa :D ----------------


# ------ part 2

# after printing the total of the plants for each generation (over 200 gens) and the difference between each
# generation total there was a distinguishable pattern - after 120 generations, the difference between the
# gen totals is always 22 for my given input.

# that means that 50000000000 - 120 = 499999998800 (each of these increase the total by 22)
# so 499999998800 * 22 = 109999999360 - this is how much the totals would increase after generation 120
# the total at 120 gens is 3151
# so 3151 + 109999999360 = 1100000000511  - is the the sum of the numbers of all pots which contain a plant after
#                                           50000000000 generations

'''
prev_g = 0

for g in range(1, 200):
    gen_g = predict_generations(initial_state, input_rules, g)
    gen_g_pot_numbers = generate_pot_numbers(initial_state, g)
    total_g = calculate_plants(gen_g, gen_g_pot_numbers)
    print('Generation ', g, ': ', total_g, ' , difference: ', (total_g - prev_g))
    prev_g = total_g
'''
