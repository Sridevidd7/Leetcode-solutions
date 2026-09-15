class Solution:
    def plusOne(self, digits: List[int]) -> List[int]:
        num = 0
        exp = 1
        for i in range(len(digits) - 1, -1, -1):
            num += digits[i] * exp
            exp *= 10

        num += 1

        return [int(i) for i in str(num)]