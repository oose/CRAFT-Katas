#include <time.h>

typedef void (*ChangeTimeNotification)(void* instance, const clock_t* newTime);

typedef struct{
    void* instance;
    ChangeTimeNotification notification;
} TimeObserver;
