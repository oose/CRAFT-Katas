#include <stdio.h>
#include "WatchState.h"

static void defaultStart(WatchStatePtr state)
{
	printf("Start event is not supported in current state.\n");
}

static void defaultStop(WatchStatePtr state)
{
	printf("Stop event is not supported in current state.\n");
}

void setDefaultImplementation(WatchStatePtr state)
{
	state->start = defaultStart;
	state->stop = defaultStop;
}
