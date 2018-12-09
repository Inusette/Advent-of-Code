
FILE_NAME = 'input.txt'


class Node(object):

    def __init__(self, data):
        self.data = data
        self.children = []

    def add_child(self, obj):
        self.children.append(obj)


def read_file(file_name):
    """
    opens and reads the file, returning the string
    :param file_name: file to read
    :return: input string
    """
    f = open(file_name, 'r')

    return f.read()


def construct_data(input_string):

    input_string = input_string.strip()

    split_data = input_string.split(' ')

    tree_numbers = []
    for s in split_data:
        tree_numbers.append(int(s))

    return tree_numbers


def process_tree(tree_log, meta):

    child_count = tree_log[0]

    attribute_count = tree_log[1]

    if not child_count:

        for att in range(2, attribute_count-1):
            meta += att

        tree_log = tree_log[(2 + attribute_count-1):]

        if not tree_log:
            return meta


def process_tree_2(tree_log, meta):

    child_count = tree_log[0]

    attribute_count = tree_log[1]

    if not child_count:

        for att in range(2, 2 + attribute_count):
            meta += tree_log[att]

        new_log = tree_log[(2 + attribute_count):]

        return new_log, meta

    else:
        new_log = tree_log[2:]
        children_sum = 0

        while child_count:
            new_log, children_sum = process_tree_2(new_log, children_sum)
            child_count -= 1

        for att in range(attribute_count):
            meta += new_log[att]

        total = meta + children_sum

        new_log = new_log[attribute_count:]

        return new_log, total





input_file = read_file(FILE_NAME)

input_tree_log = construct_data(input_file)

print(input_tree_log)

metadata = 0

log, total_sum = process_tree_2(input_tree_log, metadata)

print(total_sum)
