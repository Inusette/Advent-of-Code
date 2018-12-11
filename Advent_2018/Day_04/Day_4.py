import datetime
from collections import OrderedDict
import re


FILE_NAME = 'input.txt'


def read_file(file_name):
    """
    opens and reads the file, returning the list of lines (strings)
    :param file_name: file to read (each line is a string)
    :return: list of strings
    """
    f = open(file_name, 'r')

    return f.readlines()


def sort_observations(input_lines):
    """
    sorts all observations according to the date and time
    :param input_lines: the list of strings (lines) from the input file
    :return: an ordered dictionary of the format - 'date time': 'observation'
    """
    # create a dictionary to store dates and their observations:
    # format - datetime object : observation
    date_dict = {}

    for line in input_lines:

        # remove whitespace at the end and the '[' characters
        line = line.strip().replace('[', '')

        # list of the format: ['1518-11-05 00:03', 'Guard #99 begins shift']
        entry = line.strip().split(']')

        # the string that belongs to the date/time : 'Guard #99 begins shift'
        observation = entry[1].strip()

        # create a regex format for the datetime object to parse the string
        date_format = '%Y-%m-%d %H:%M'

        # create the datetime object using the format
        date = datetime.datetime.strptime(entry[0], date_format)
        date = date.strftime(date_format)

        # add the datetime to the dictionary alongside with the observation
        date_dict[date] = observation

    ordered_data = OrderedDict(sorted(date_dict.items(), key=lambda x: datetime.datetime.strptime(x[0], date_format)))

    return ordered_data


def process_observations(ordered_observations):
    """
    processes all observations in the ordered data dictionary, calculates which minutes each guard
    spends asleep (across all days)
    :param ordered_observations: ordered dictionary of the format - 'date time': 'observation'
    :return: a dictionary of the guards and the lists of minutes they spend asleep - 'guard_id': [min, min, min]
    """

    # the id of the current guard
    guard_id = 0

    # the time (min) when the asleep time began
    begin_asleep = ''

    # dictionary to store the minutes the guards spend asleep (across all days)
    # format - 'guard_id': [min, min, min]
    guards_log = {}

    # a regular expression pattern to extract the guard's id
    guard_format = '\d+'

    # iterate over all entries in the ordered observations dictionary
    for date_stamp, observation in ordered_observations.items():

        # if the observation includes a guard arriving, update the guard variable
        if '#' in observation:
            # the id of the current guard
            guard_id = int(find_pattern(guard_format, observation))

        # if the guard falls asleep, update the variable with time the sleep began
        elif 'falls' in observation:
            begin_asleep = date_stamp

        # means that the guard has awaken and we should calculate the time they spent asleep
        else:
            # calculate the sleep time so far
            minutes_asleep = calculate_asleep(begin_asleep, date_stamp)

            # if this guard has been logged, extend his list of minutes
            if guard_id in guards_log.keys():
                guards_log[guard_id].extend(minutes_asleep)

            # otherwise - add him to the log with the current list of minutes
            else:
                guards_log[guard_id] = minutes_asleep

    return guards_log


def calculate_asleep(asleep, awake):
    """
    extracts the start and the end minutes from the given strings and adds the minutes in the range to a list
    :param asleep: string with the timestamp of falling asleep
    :param awake: string with the timestamp of waking up
    :return: list of minutes spent asleep
    """

    # create the pattern for the minute extraction
    min_format = ':\d+'

    # find the exact minutes of falling asleep and waking up
    s_asleep = find_pattern(min_format, asleep).replace(':', '')
    min_asleep = int(s_asleep)

    s_awake = find_pattern(min_format, awake).replace(':', '')
    min_awake = int(s_awake)

    # add all asleep minutes to a list
    total_asleep = list(range(min_asleep, min_awake))

    return total_asleep


def find_pattern(pattern, s):
    """
    a helper method to find a specified by a pattern substring in a given string
    :param pattern: a regular expression pattern
    :param s: string to parse
    :return: the found pattern
    """
    # create the search object using the given regex pattern
    search_obj = re.search(pattern, s)

    found = ''

    # make sure the pattern was found and return it
    if search_obj:
        found = search_obj.group()
    else:
        print("Nothing found in: ", s)

    return found


def calculate_total_asleep(guards_log):
    """
    calculates how many minutes the guards spend asleep
    :param guards_log: dictionary of the guards and the lists of minutes they spend asleep - 'guard_id': [min, min, min]
    :return: dictionary of the format - 'guard_id': int  (count of minutes spent asleep)
    """
    # create a similar dictionary, but instead of a list of minutes, use the length of the list - total
    # minutes of asleep time
    guards_total_asleep = {}

    # iterate over all entries in the dictionary to calculate the length of each min list
    for g_id, mins in guards_log.items():
        guards_total_asleep[g_id] = len(mins)

    return guards_total_asleep


def find_sleepy(guards_total_asleep):
    """
    finds the entry with the largest value of the time spent asleep
    :param guards_total_asleep: dictionary of the format - 'guard_id': int  (count of minutes spent asleep)
    :return: guard id - int
    """
    # the id of the sleepyhead guard
    guard_id = 0

    # find the id with the maximum asleep time
    max_value = max(guards_total_asleep.values())

    # start iterating over the dictionary to find the entry with the right value (max)
    for g_id, count in guards_total_asleep.items():

        # if found, update the variables and break out of the loop
        if count == max_value:
            guard_id = g_id
            break

    return guard_id


def find_most_common_minute(guard_id, guards_log):
    """
    finds an element in the list that occurs most often
    :param guard_id: the id of the guard (key)
    :param guards_log: dictionary of the guards and the lists of minutes they spend asleep - 'guard_id': [min, min]
    :return: the value of the minute the given guard spends asleep the most often - int
    """

    # find the list of minutes that belong to the given guard
    guards_mins = guards_log[guard_id]

    # find the element wih the most occurrences
    most_common = max(guards_mins, key=guards_mins.count)

    return most_common


def determine_frequent_minutes(guards_log):
    """
    determine which minutes each guard spends asleep the most, and the count of these minutes
    :param guards_log: dictionary of the guards and the lists of minutes they spend asleep - 'guard_id': [min, min]
    :return: dictionary of the guards and the minute tuple - 'guard_id': [min, min_count]
    """
    # create a dictionary to store which is the most frequent minute a guard spends asleep
    guards_frequent = {}

    # iterate over all the guard in the guard_log
    for guard_id in guards_log.keys():

        # find the most frequent minute and update the frequent dictionary
        most_frequent = find_most_common_minute(guard_id, guards_log)

        # find the count of this minute
        frequent_count = guards_log[guard_id].count(most_frequent)

        # add the guard is, minute and the minute's coun to the dict
        guards_frequent[guard_id] = (most_frequent, frequent_count)

    return guards_frequent


def find_most_frequent_of_all(guards_frequent):
    """
    Finds, of all guards, which guard is most frequently asleep on the same minute
    :param guards_frequent: dictionary of the guards and the minute tuple - 'guard_id': [min, min_count]
    :return: guard id - int, most frequent minute spent asleep by the guard - int
    """
    # out of the given dictionary, create two: one withe value of the first element in the tuple,
    # the other none with the second value of the tuple
    guards_count = {}

    for guard_id, frequent in guards_frequent.items():
        guards_count[guard_id] = frequent[1]

    # use the already existing method to find the guard id with the largest minute count
    sleepy_id = find_sleepy(guards_count)

    # get the frequent minute of this guard
    sleepy_min = guards_frequent[sleepy_id][0]

    return sleepy_id, sleepy_min


# read the input file
input_lines = read_file(FILE_NAME)

# sort the observations by the date and time they were taken
ordered_observations = sort_observations(input_lines)

# find all guard ids and the minutes they spend asleep
guards_log = process_observations(ordered_observations)

# calculate the total number of minutes each guard spends asleep
guards_total_sleep = calculate_total_asleep(guards_log)

# find the id of the sleepyhead
guard = find_sleepy(guards_total_sleep)

# find the minute that the guard spends asleep the most often
most_common_minute = find_most_common_minute(guard, guards_log)

# multiply the id of the guard and the most likely minute they spend asleep
print(guard, ' * ', most_common_minute, ' = ', guard * most_common_minute)

# find which is the most frequent minute each guard spends asleep
frequent_minute_guards = determine_frequent_minutes(guards_log)

# find, of all guards, which guard is most frequently asleep on the same minute?
sleepyhead, sleepy_min = find_most_frequent_of_all(frequent_minute_guards)

# ID of the guard multiplied by the minute
print(sleepyhead, ' * ', sleepy_min, ' = ', sleepyhead * sleepy_min)
