
#include <stdio.h>
#include <stdlib.h>

#include "StopWatch.h"
#include "StoppedState.h"

struct StopWatch
{
	struct WatchState state;
};

StopWatchPtr createWatch()
{
	StopWatchPtr watch = malloc(sizeof(struct StopWatch));
	if (NULL != watch)
	{
		updateEventHandlerInStoppedState(&watch->state);
	}
	return watch;
}

void destroyWatch(StopWatchPtr watch)
{
	free(watch);
}

void startWatch(StopWatchPtr watch)
{
	watch->state.start(&watch->state);
}

void stopWatch(StopWatchPtr watch)
{
	watch->state.stop(&watch->state);
}

