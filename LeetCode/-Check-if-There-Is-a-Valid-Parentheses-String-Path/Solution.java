    rows = len(grid)
    cols = len(grid[0])

    if grid[rows-1][cols-1] == "(" or grid[0][0] == ")":
        return False 

    # let dp[r][c][m] be is there a sequence that ends at r,c with m open parentheses
    dp = [[[False for _ in range(rows + cols +1 )] for _ in range(cols)] for _ in range(rows)]
    dp[0][0][0] = True 


    for r in range( rows) : 
        for c in range( cols) : 
            if r == 0 and c== 0 : 
                continue 
            dp[r][c][0] = dp[r-1][c][1] and grid[r-1][c] == ')' or dp[r][c-1][1] and grid[r][c-1] == ')'
            # the upper bound is the result of the following observation. At any point in our grid, we can't have more open unmatched parenthesis than what we can potentially close with the cells that are left to discover
            for m in range(1, min(r +c+ 1 , rows-r + cols -c ) ):
                up_more = False if r == 0 else dp[r-1][c][m+1] and grid[r-1][c] == ')'
                up_less =  False if r == 0 else dp[r-1][c][m-1] and grid[r-1][c] == '('
                left_more =  False if c == 0 else dp[r][c-1][m+1] and grid[r][c-1] == ')'
                left_less = False if c == 0 else dp[r][c-1][m-1] and grid[r][c-1] == '('
                dp[r][c][m] = up_more or up_less or left_more or left_less
    return dp[-1][-1][1]