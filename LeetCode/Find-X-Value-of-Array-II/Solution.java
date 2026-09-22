1class Solution {
2    private int k;
3    private int size;
4    private long[] H;
5    private int[] prod;
6
7    private void pull(int i) {
8        int lc = 2 * i;
9        int rc = lc + 1;
10        int lp = prod[lc];
11
12        int bi = i * k;
13        int bl = lc * k;
14        int br = rc * k;
15
16        for (int q = 0; q < k; q++) {
17            H[bi + q] = H[bl + q];
18        }
19
20        for (int q = 0; q < k; q++) {
21            long c = H[br + q];
22
23            if (c != 0) {
24                H[bi + (lp * q) % k] += c;
25            }
26        }
27
28        prod[i] = lp * prod[rc] % k;
29    }
30
31    private void update(int idx, int val) {
32        int nd = size + idx;
33        int base = nd * k;
34
35        for (int q = 0; q < k; q++) {
36            H[base + q] = 0;
37        }
38
39        int v = val % k;
40
41        H[base + v] = 1;
42        prod[nd] = v;
43
44        int cur = nd >> 1;
45
46        while (cur > 0) {
47            pull(cur);
48            cur >>= 1;
49        }
50    }
51
52    public int[] resultArray(int[] nums, int k, int[][] queries) {
53        this.k = k;
54
55        int n = nums.length;
56
57        size = 1;
58        while (size < n) {
59            size <<= 1;
60        }
61
62        H = new long[2 * size * k];
63        prod = new int[2 * size];
64
65        for (int i = 0; i < prod.length; i++) {
66            prod[i] = 1 % k;
67        }
68
69        for (int i = 0; i < n; i++) {
70            int v = nums[i] % k;
71            int nd = size + i;
72
73            H[nd * k + v] = 1;
74            prod[nd] = v;
75        }
76
77        for (int i = size - 1; i >= 1; i--) {
78            pull(i);
79        }
80
81        int[] ans = new int[queries.length];
82
83        for (int qi = 0; qi < queries.length; qi++) {
84            int idx = queries[qi][0];
85            int val = queries[qi][1];
86            int start = queries[qi][2];
87            int x = queries[qi][3];
88
89            update(idx, val);
90
91            int l = start + size;
92            int r = n + size;
93
94            int[] ln = new int[64];
95            int[] rn = new int[64];
96
97            int lnSize = 0;
98            int rnSize = 0;
99
100            while (l < r) {
101                if ((l & 1) != 0) {
102                    ln[lnSize++] = l++;
103                }
104
105                if ((r & 1) != 0) {
106                    rn[rnSize++] = --r;
107                }
108
109                l >>= 1;
110                r >>= 1;
111            }
112
113            long[] res = new long[k];
114            int p = 1 % k;
115
116            for (int i = 0; i < lnSize; i++) {
117                int node = ln[i];
118                int base = node * k;
119
120                for (int q = 0; q < k; q++) {
121                    long c = H[base + q];
122
123                    if (c != 0) {
124                        res[(p * q) % k] += c;
125                    }
126                }
127
128                p = p * prod[node] % k;
129            }
130
131            for (int i = rnSize - 1; i >= 0; i--) {
132                int node = rn[i];
133                int base = node * k;
134
135                for (int q = 0; q < k; q++) {
136                    long c = H[base + q];
137
138                    if (c != 0) {
139                        res[(p * q) % k] += c;
140                    }
141                }
142
143                p = p * prod[node] % k;
144            }
145
146            ans[qi] = (int)res[x];
147        }
148
149        return ans;
150    }
151}