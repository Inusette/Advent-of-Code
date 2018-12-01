"""
consider the digit halfway around the circular list. That is, if your list contains 10 items, only include a digit
in your sum if the digit 10/2 = 5 steps forward matches it. Fortunately, your list has an even number of elements.
"""

# the input captcha
puzzle = open("./puzzle_input", "r")

# read the input file
captcha = puzzle.read()
print(captcha)

# the sum of all relevant digits
sum = 0

# the length of the captcha
length = len(captcha)

# iterate the first half of the input and compare to the second half
for d in range(0, length/2):
    # current digit
    current = int(captcha[d])
    # digit half way forth
    to = int(captcha[d + length/2])

    # if the current equals the halfway digit, add the current to the overall sum
    if current == to:
        # multiply by 2, for the second half of the input compared to the first (same digits)
        sum += current * 2

# print the sum
print "answer: ", sum
