#!/usr/bin/python

import sys
import re

names = set()
for _ in range(10):
    line = sys.stdin.readline()
    assert re.match("^[A-Za-z]{1,10}: (([123]B|SS|C|P|[LCR]F), ){1,9}X\n$", line)
    tokens = re.split(":, ", line.strip())
    assert tokens[0] not in names
    names.add(tokens[0])

    assert len(tokens[1:]) == len(set(tokens[1:]))

assert sys.stdin.readline() == ''

sys.exit(42)

