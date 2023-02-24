#include <stdio.h>
#include <stdlib.h>
#include <assert.h>

#include "TimeSubject.h"
#include "Watch.h"

struct Watch {
    int id;
    /* Other attributes of the watch, e.g. digital display. */
};


static void updateDisplay (void* instance, const clock_t* newTime){
    long double doubleTime = (long double) *newTime;
    WatchPtr watch = (WatchPtr) instance;
    printf("Watch %d: Time is %.0Lf\n", watch->id, doubleTime);
}

/* Implementation of the function required by the TimeObserver interface. */
static void changedTime(void* instance, const clock_t* newTime) {
    assert(NULL != instance);
    updateDisplay(instance, newTime);
}

WatchPtr createWatch(void){
    static int nextId = 0;
    WatchPtr watch = malloc(sizeof *watch);
    if(NULL != watch){
        watch->id = ++nextId;
        /* Successfully created -> attach to the subject. */
        TimeObserver observer = {.instance = watch, .notification = changedTime};
        attach(&observer);
    }
    return watch;
}

void destroyWatch(WatchPtr watch){
    /* Before deleting the instance we have to detach from the subject. */
    TimeObserver observer = {.instance = watch, .notification = changedTime};
    detach(&observer);
    free(watch);
}
