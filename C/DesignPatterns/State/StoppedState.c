#include "RunningState.h"
#include "StoppedState.h"

#include <stdio.h>
#include <time.h>

static void handleStartEvent(WatchStatePtr state)
{
	printf("Watch started.\n");
	state->startTime = clock();
	updateEventhandlerInRunningState(state);
}

void updateEventHandlerInStoppedState(WatchStatePtr state)
{
	setDefaultImplementation(state);
	state->start = handleStartEvent;
}
