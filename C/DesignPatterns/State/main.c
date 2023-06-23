#include <stdio.h>
#include <stdlib.h>

#include "StopWatch.h"

static void busy_loop() {
	int val = 0;
	for (int i = 0; i < 1000000; i++) {
		val++;
	}
}

int main(void) {
	StopWatchPtr watch = createWatch();
	if (NULL == watch)
		return EXIT_FAILURE;

	stopWatch(watch);
	startWatch(watch);
	startWatch(watch);
	busy_loop();
	stopWatch(watch);

	destroyWatch(watch);
	return EXIT_SUCCESS;
}
