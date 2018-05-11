#!/usr/bin/python

import sys

def toint(str):
    try:
        val = int(str)
        return val
    except:
        print("ERROR: "+str)
        sys.exit(1)

def getline():
    return sys.stdin.readline().rstrip('\n')

def intline():
    return toint(getline());

n=intline();

for case in range(0,n):
    b=intline();
    m=intline();
    k=intline();
    if b<2 or b>36:
        print("ERROR: value "+str(b)+" is not in the range [2,36]")
        sys.exit(1)

    for message in range(0,m):
        line = getline().split(' ')
        if line[-1]!="-1":
            print("ERROR: line "+" ".join(line))
            sys.exit(1)

        for str in line:
            x = toint(str)
            if x<0:
                break

sys.exit(42)
