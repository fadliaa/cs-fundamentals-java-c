#ifndef FADLIA_INT_LIST_H
#define FADLIA_INT_LIST_H

#include <stdbool.h>
#include <stddef.h>

typedef struct IntNode {
    int value;
    struct IntNode *next;
} IntNode;

typedef struct IntList {
    IntNode *head;
    IntNode *tail;
    size_t size;
} IntList;

void list_init(IntList *list);
bool list_push_front(IntList *list, int value);
bool list_push_back(IntList *list, int value);
bool list_pop_front(IntList *list, int *out_value);
void list_reverse(IntList *list);
void list_free(IntList *list);

#endif
