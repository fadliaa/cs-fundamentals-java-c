#include "int_list.h"

#include <assert.h>
#include <stdio.h>

int main(void) {
    IntList list;
    list_init(&list);

    assert(list.size == 0);
    assert(list_push_back(&list, 10));
    assert(list_push_back(&list, 20));
    assert(list_push_front(&list, 5));
    assert(list.size == 3);

    list_reverse(&list);

    int val = 0;
    assert(list_pop_front(&list, &val) && val == 20);
    assert(list_pop_front(&list, &val) && val == 10);
    assert(list_pop_front(&list, &val) && val == 5);
    assert(!list_pop_front(&list, &val));
    assert(list.size == 0);

    list_free(&list);
    printf("All C11 IntList pointer & memory tests PASSED.\n");
    return 0;
}
