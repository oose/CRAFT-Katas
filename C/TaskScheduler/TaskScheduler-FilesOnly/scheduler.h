/*
 * timer.h
 *
 *  Created on: 13.12.2017
 *      Author: stefanr
 *
 *  Changes:
 *  	2017-12-13 Created file
 *  	2017-12-14 Added function prototypes
 *
 */

#ifndef SCHEDULER_SCHEDULER_H_
#define SCHEDULER_SCHEDULER_H_

typedef struct task_t
{
	int (*func)(void);
	unsigned int interval;
	unsigned int priority;
	unsigned int last_exec;
} task_t;


extern task_t tasks[10];
extern int ntasks;
extern int init_done;

void execute_schedule(void);
unsigned int gcd(unsigned int, unsigned int);

/* Init scheduler */
void default_init(void);

//Scheduled functions
int f(void);
int g(void);
int h(void);

#endif /* SCHEDULER_SCHEDULER_H_ */
