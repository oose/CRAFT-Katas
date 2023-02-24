#include <stdio.h>
#include <stdlib.h>
#include <threads.h>

#include "TimeSource.h"
#include "Watch.h"

int main(void) {
	initializeTimeSource();
	WatchPtr watch1 = createWatch();
	WatchPtr watch2 = createWatch();
	WatchPtr watch3 = createWatch();
	thrd_sleep(&(struct timespec){.tv_sec=2}, NULL);
	destroyWatch(watch2);
	thrd_sleep(&(struct timespec){.tv_sec=2}, NULL);
	destroyWatch(watch3);
	WatchPtr watch4 = createWatch();
	thrd_sleep(&(struct timespec){.tv_sec=2}, NULL);
	terminateTimeSource();
	return EXIT_SUCCESS;
}
