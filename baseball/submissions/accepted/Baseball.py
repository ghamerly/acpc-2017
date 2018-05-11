#do a recursive backtracking approach to trying to place all players 
def placePlayer(pos, ppdict, positions, lineupCard) :
    if pos > 9: #ten positions
        return True
    #trying to find a player for each position   
    for j in ppdict:
        if j not in lineupCard and (positions[pos] in ppdict[j] or positions[pos] == 'DH'):
            lineupCard.append(j)
            if placePlayer(pos+1, ppdict, positions, lineupCard) :
                return True
            else:
                lineupCard.remove(j)
    #iterated all players and couldn't find anyone for this position
    return False

def readPack():
    newDict = {}
    #read in 10 players
    for j in range(10):
        line = raw_input()
        #do some reformatting
        line = line.replace(":",",").replace(" ", "")
        lineList = line.split(",")
        newDict [lineList[0]] = lineList[1:]
    return newDict

positions = ['1B', 'P', '2B', '3B', 'C', 'RF', 'LF', 'CF', 'DH', 'SS']
packDict = readPack()
lineupCard = []
if placePlayer(0, packDict, positions, lineupCard):
    print "True"
else:
    print "False"

