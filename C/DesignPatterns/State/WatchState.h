#ifndef WATCHSTATE_H_
#define WATCHSTATE_H_

#include <time.h>

typedef struct WatchState* WatchStatePtr;

typedef void (*EventStartFunc)(WatchStatePtr);
typedef void (*EventStopFunc)(WatchStatePtr);

struct WatchState
{
	EventStartFunc start;
	EventStopFunc stop;
	clock_t startTime;
};

void setDefaultImplementation(WatchStatePtr state);

#endif
