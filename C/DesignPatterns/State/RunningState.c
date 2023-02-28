#include "StoppedState.h"
#include "RunningState.h"

#include <stdio.h>
#include <time.h>

static void handleStopEvent(WatchStatePtr state)
{
	clock_t now = clock();
	printf("Watch stopped. Elapsed time: %.0f µs\n", (now - state->startTime) / (double)CLOCKS_PER_SEC * 1e6);
	updateEventHandlerInStoppedState(state);
}

void updateEventhandlerInRunningState(WatchStatePtr state)
{
	setDefaultImplementation(state);
	state->stop = handleStopEvent;
}
