
FILE_NAME = 'input.txt'


class Node(object):
    """
    Class to represent a node object. Has 2 attributes: list of metadata and list of children (nodes)
    """
    def __init__(self, data, children):
        self.data = data
        self.children = children

    def add_child(self, obj):
        self.children.append(obj)

    def add_children(self, obj_list):
        self.children.extend(obj_list)

    def add_data(self, data):
        self.data = data

    def get_children(self):
        return self.children

    def get_data(self):
        return self.data


def read_file(file_name):
    """
    opens and reads the file, returning the string
    :param file_name: file to read
    :return: input string
    """
    f = open(file_name, 'r')

    return f.read()


def extract_data(input_string):
    """
    reads the input string and creates a list of integers
    :param input_string: string to process
    :return: list of int
    """
    # split the string into a list of strings
    split_data = input_string.strip().split(' ')

    # convert each element into int
    tree_numbers = []
    for s in split_data:
        tree_numbers.append(int(s))

    return tree_numbers


def create_tree(tree_log):
    """
    recursively processes the input numbers and creates a tree of Node objects,
    where each node has data attribute (metadata) and a list of children (nodes)
    :param tree_log: the list of int - input
    :return: list - updated input, node - root to the created tree
    """

    # extract how many children this node has
    child_count = tree_log[0]

    # extract how many attributes this node has
    attribute_count = tree_log[1]

    # if there are not children
    if not child_count:

        # create a list to store attribute metadata
        metadata_list = []

        # find the metadata at the attribute indices and add to list
        for att in range(2, 2 + attribute_count):
            metadata_list.append(tree_log[att])

        # cut this portion out of the input list
        new_log = tree_log[(2 + attribute_count):]

        # create a child node with the found metadata and an empty children list
        child_node = Node(metadata_list, [])

        # return the updated input list and this node
        return new_log, child_node

    # if this node has children nodes
    else:
        # update the input list
        new_log = tree_log[2:]

        # create lists to store attributes' metadata and children nodes
        meta_list = []
        children_list = []

        # while there are children, for each child:
        while child_count:
            # create a child's node (recursively call this method)
            new_log, child_node = create_tree(new_log)

            # add the child's node to the list of children
            children_list.append(child_node)

            # decrease the child count
            child_count -= 1

        # when all children have been processed, getattributes metadata of this node
        for att in range(attribute_count):
            meta_list.append(new_log[att])

        # create the node with the metadata and the children list
        this_node = Node(meta_list, children_list)

        # cut this portion out of the list
        new_log = new_log[attribute_count:]

        return new_log, this_node


def calculate_metadata_sum(root, metadata_sum):
    """
    recursively parses the tree, summing up all attributes metadata
    :param root: root node
    :param metadata_sum: int - 0
    :return: int - sum of all attributes metadata
    """

    # get the children of this node
    children = root.get_children()

    # if there are not children
    if not children:

        # update the metadata sum by adding metadata of this node
        for att in root.get_data():
            metadata_sum += att

        # return the updated metadata sum
        return metadata_sum

    # if there are children
    else:

        # instantiate the children metadata sum
        children_sum = 0

        # for each child, find its metadata sum by recursively calling the method on the child's
        # node with an updated sum
        for child in children:
            children_sum = calculate_metadata_sum(child, children_sum)

        # when all children nodes have been processed, add up current metadata
        # sum and the attributes metadata of this node
        for att in root.get_data():
            metadata_sum += att

        # combine with the children metadata sum
        total_sum = metadata_sum + children_sum

        return total_sum


def calculate_value(root, value):
    """
    recursively parses the tree and finds the value of the root.
    :param root: root node - tree to parse
    :param value: int - 0
    :return: the value of the root node
    """

    # get the children of this node
    children = root.get_children()

    # if there are not children
    if not children:

        # calculate the value of this node (sum up the attribute metadata)
        for att in root.get_data():
            value += att

        # return updated value
        return value

    # if there are children
    else:

        # instantiate children's value (although it sounds wrong)
        children_value = 0

        # each metadata now refers to a child node
        for att in root.get_data():

            # If such child exists, calculate its value by recursively calling the method on the child's node
            if att <= len(children):
                children_value = calculate_value(children[att - 1], children_value)

        # sum up current value and children's value
        total_value = value + children_value

        return total_value


input_file = read_file(FILE_NAME)

input_tree_log = extract_data(input_file)

_, tree_root = create_tree(input_tree_log)

metadata = calculate_metadata_sum(tree_root, 0)

print(metadata)

root_value = calculate_value(tree_root, 0)

print(root_value)
