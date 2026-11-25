#include "int_list.h"

#include <stdlib.h>

void list_init(IntList *list) {
    if (!list) return;
    list->head = NULL;
    list->tail = NULL;
    list->size = 0;
}

bool list_push_front(IntList *list, int value) {
    if (!list) return false;
    IntNode *node = (IntNode *)malloc(sizeof(IntNode));
    if (!node) return false;
    node->value = value;
    node->next = list->head;
    list->head = node;
    if (!list->tail) {
        list->tail = node;
    }
    list->size++;
    return true;
}

bool list_push_back(IntList *list, int value) {
    if (!list) return false;
    IntNode *node = (IntNode *)malloc(sizeof(IntNode));
    if (!node) return false;
    node->value = value;
    node->next = NULL;
    if (!list->tail) {
        list->head = list->tail = node;
    } else {
        list->tail->next = node;
        list->tail = node;
    }
    list->size++;
    return true;
}

bool list_pop_front(IntList *list, int *out_value) {
    if (!list || !list->head) return false;
    IntNode *first = list->head;
    if (out_value) {
        *out_value = first->value;
    }
    list->head = first->next;
    if (!list->head) {
        list->tail = NULL;
    }
    free(first);
    list->size--;
    return true;
}

void list_reverse(IntList *list) {
    if (!list || list->size < 2) return;
    IntNode *prev = NULL;
    IntNode *curr = list->head;
    list->tail = list->head;
    while (curr) {
        IntNode *next = curr->next;
        curr->next = prev;
        prev = curr;
        curr = next;
    }
    list->head = prev;
}

void list_free(IntList *list) {
    if (!list) return;
    IntNode *curr = list->head;
    while (curr) {
        IntNode *next = curr->next;
        free(curr);
        curr = next;
    }
    list->head = NULL;
    list->tail = NULL;
    list->size = 0;
}
