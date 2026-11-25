CC ?= gcc
CFLAGS ?= -std=c11 -Wall -Wextra -Werror -O2 -Iinclude

.PHONY: test clean

test:
	$(CC) $(CFLAGS) src/int_list.c tests/test_int_list.c -o test_int_list
	./test_int_list

clean:
	rm -f test_int_list test_int_list.exe
