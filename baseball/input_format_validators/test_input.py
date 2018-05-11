#!/usr/bin/python
import sys
from sets import Set

def validator():
    count=0
    positions=Set(['1B', '2B', 'SS', '3B', 'C', 'P', 'LF', 'CF', 'RF'])
    teams=Set([])

    for line in sys.stdin:
        line=line.strip().replace(":", " ").replace("\t", " ").replace(",","")
        if line=="":
            continue

        count+=1
        if not line.endswith('X'):
            print("ERROR: Wrong record "+line)
            sys.exit(1)

        line=line.split()
        if line[0].upper() in teams:
            print("ERROR: duplicated names")
            sys.exit(1)

        teams.add(line[0].upper())

        for i in range(1, len(line)):
            pos = line[i].upper()
            if not pos in positions and pos !="X":
                print("ERROR: not legal positions " +pos)
                sys.exit(1)

    if count!=10:
        print("ERROR: expected 10 records but see "+str(count)+" players")
        sys.exit(1)

    sys.exit(42)


if __name__ == "__main__":
    validator()
