#include <stdio.h>
#include <stdlib.h>
#include <assert.h>
#include <time.h>
#include <threads.h>
#include <string.h>
#include "TimeSource.h"
#include "TimeSubject.h"

typedef struct ListNode {
    TimeObserver item;
    struct ListNode* next;
} ListNode;

static ListNode observers = {NULL, NULL};

static mtx_t mutex;

static clock_t currentTime;
static thrd_t timer_thread;
static int terminate = 0;

/* Local helper functions for managing the linked-list (implementation omitted). */
static void appendToList(const TimeObserver* observer){
    ListNode* node = observers.next;
    ListNode* prev = &observers;

    while(NULL != node) {
        if (memcmp(observer, &node->item, sizeof(TimeObserver)) == 0){
            return;
        }
        prev = node;
        node = node->next;
    }

    ListNode* newNode = malloc(sizeof(ListNode));
    memcpy(&newNode->item, observer, sizeof(TimeObserver));
    newNode->next = NULL;
    prev->next = newNode;
}


static void removeFromList(const TimeObserver* observer){
    ListNode* node = observers.next;
    ListNode* prev = &observers;

    while(NULL != node) {
        if (memcmp(observer, &node->item, sizeof(TimeObserver)) == 0){
            prev->next = node->next;
            free(node);
            return;
        }
        prev = node;
        node = node->next;
    }
}


static void error_and_exit(const char* text){
    fprintf(stderr, "%s", text);
    exit(1);
}


static void notifyObservers(clock_t currentTime){
    if (thrd_success != mtx_lock(&mutex)){   
        error_and_exit("error locking mutex\n");
    } 
    ListNode* node = observers.next;
    while(NULL != node) {
        TimeObserver* observer = &node->item;
        observer->notification(observer->instance, &currentTime);
        node = node->next;
    }
    if (thrd_success != mtx_unlock(&mutex)){   
        error_and_exit("error unlocking mutex\n");
    } 
}

static int timer(void* arg){
    while(!terminate){
        currentTime = clock();
        notifyObservers(currentTime);
        thrd_sleep(&(struct timespec){.tv_sec=1}, NULL);
    }
}

/* Implementation of the TimeSubject interface. */
void attach(const TimeObserver* observer){
    assert(NULL != observer);
    if (thrd_success != mtx_lock(&mutex)){   
        error_and_exit("error locking mutex\n");
    }    
    appendToList(observer);
    if (thrd_success != mtx_unlock(&mutex)){   
        error_and_exit("error unlocking mutex\n");
    }    
}

void detach(const TimeObserver* observer){
    assert(NULL != observer);
    if (thrd_success != mtx_lock(&mutex)){   
        error_and_exit("error locking mutex\n");
    }   
    removeFromList(observer);
    if (thrd_success != mtx_unlock(&mutex)){   
        error_and_exit("error unlocking mutex\n");
    }
}

void initializeTimeSource(void){
    if (thrd_success != mtx_init(&mutex, mtx_plain) ||
        thrd_success != thrd_create(&timer_thread, timer, NULL)){
            error_and_exit("Error initializing time source.\n");
    }
    int res = 0;
}

void terminateTimeSource(void){
    terminate = 1;
}

