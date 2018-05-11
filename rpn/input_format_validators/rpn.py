#!/usr/bin/python

import sys


stack=[]

def push(val):
    global stack
    stack.append(val)

def pop():
    global stack
    a=stack.pop()
    return a

def top():
    global stack
    return stack[-1]

def isEmpty():
    global stack
    return len(stack)==0


for line in sys.stdin:
    line=line.rstrip('\n')
    #import pdb; pdb.set_trace()

    if line == "dup":
        if isEmpty():
            print("ERROR: DUP but no data on stack")
            sys.exit(1)

        push(top())
        continue
    elif line == "pop":
        if isEmpty():
            print("ERROR: pop but empty stack")
            sys.exit(1)

        pop()
        continue
    elif line == "print":
        if isEmpty():
            print("ERROR: print but no data on stack")
            sys.exit(1)

        #print()
        continue
    elif line == "swap":
        if len(stack)<2:
            print("ERROR: swap but less than two entries in stack")
            sys.exit(1)

    elif line == "quit":
        sys.exit(42)
    elif line == "+":
        if len(stack)<2:
            print("ERROR: + but less than two entries in stack")
            sys.exit(1)

        a=pop()
        b=pop()
        push(a+b)
        continue
    elif line == "-":
        if len(stack)<2:
            print("ERROR: - but less than two entries in stack")
            sys.exit(1)

        a=pop()
        b=pop()
        push(a-b)

    elif line == "*":
        if len(stack)<2:
            print("ERROR: * but less than two entries in stack")
            sys.exit(1)

        a=pop()
        b=pop()
        push(a*b)

        continue
    elif line == "/":
        if len(stack)<2:
            print("ERROR: * but less than two entries in stack")
            sys.exit(1)

        a=pop()
        b=pop()
        if b==0:
            print("ERROR: divided by 0")
            sys.exit(1)

        push(a/b)

        continue
    elif line == "^":
        if len(stack)<2:
            print("ERROR: * but less than two entries in stack")
            sys.exit(1)

        a=pop()
        b=pop()
        push(pow(a,b))
    else:
        try:
            dummy = int(line)
            push(dummy)
            continue
        except BaseException as e:
            print("Error: conversion "+str(dummy)+" "+str(e))
            sys.exit(1)


