#!/usr/bin/python3

import sys

stack = []

for line in sys.stdin:
    line = line.strip()
    if (line == "dup"):
        stack.append(stack[-1])
    elif line == "pop":
        stack.pop();
    elif line == "print":
        print(int(stack[-1]))
    elif line == "swap":
        tmp = stack[-1]
        stack[-1] = stack[-2]
        stack[-2] = tmp
    elif line == "quit":
        break
    elif line == "+":
        b = stack.pop()
        a = stack.pop()
        stack.append(a + b)
    elif line == "-":
        b = stack.pop()
        a = stack.pop()
        stack.append(a - b)
    elif line == "*":
        b = stack.pop()
        a = stack.pop()
        stack.append(a * b)
    elif line == "/":
        b = stack.pop()
        a = stack.pop()
        stack.append(a // b)
    elif line == "^":
        b = stack.pop()
        a = stack.pop()
        stack.append(a ** b)
    else:
        stack.append(int(line))
    
