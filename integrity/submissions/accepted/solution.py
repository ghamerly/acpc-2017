#!/usr/bin/python3

import sys

def toint(str):
    try:
        val = int(str)
        return val
    except:
        sys.exit(1)

def getline():
    return sys.stdin.readline().rstrip();

def intline():
    return toint(getline());

n=intline();

for case in range(0,n):
    b=intline();
    m=intline();
    k=intline();
    line=""
    for message in range(0,m):
        sum = 0
        input_ok = True
        for str in getline().split():
            x = toint(str)
            if (x<0):
                break
            elif (x>=b):
                input_ok = False
                break
            
            sum = b*sum + x
        if input_ok and sum % k == 0:
            line += "1"
        else:
            line += "0"

    print(line)
        
        
