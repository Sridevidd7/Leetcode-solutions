arr = [3, 2, 4, 3]
target = 3

index:   0   1   2   3
arr:    [3,  2,  4,  3]

First target sum:
          [3]
           ↑
        length = 1

Second target sum:
                   [3]
                    ↑
                 length = 1

prefix best:
index:      0   1   2   3
best:       1   1   1   1

At index 3:
current sub-array = [3]
current length    = 1

previous valid part = best[2] = 1

total = 1 + 1 = 2