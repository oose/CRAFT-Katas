#ifndef STOPWATCH_H_
#define STOPWATCH_H_

#include "WatchState.h"

typedef struct StopWatch* StopWatchPtr;

StopWatchPtr createWatch();

void destroyWatch(StopWatchPtr watch);

void startWatch(StopWatchPtr watch);

void stopWatch(StopWatchPtr watch);

#endif
