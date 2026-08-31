class Solution {
public:
    vector<int> nodesBetweenCriticalPoints(ListNode* head) {
        int first = -1;
        int prevCritical = -1;
        int minDist = INT_MAX;

        ListNode* prev = head;
        ListNode* curr = head->next;
        int idx = 1;

        while (curr->next != nullptr) {
            ListNode* next = curr->next;

            bool critical =
                (curr->val > prev->val && curr->val > next->val) ||
                (curr->val < prev->val && curr->val < next->val);

            if (critical) {
                if (first == -1) {
                    first = idx;
                } else {
                    minDist = min(minDist, idx - prevCritical);
                }

                prevCritical = idx;
            }

            prev = curr;
            curr = next;
            idx++;
        }

        if (minDist == INT_MAX)
            return {-1, -1};

        return {minDist, prevCritical - first};
    }
};