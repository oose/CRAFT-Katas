/*
 * timer.c
 *
 *  Created on: 13.12.2017
 *      Author: stefanr
 *
 *  Changes:
 *  	2017-12-13 Created file
 *  	2017-12-14 Added implementations of functions
 *  	2018-01-18 Idle process, priority scheduling
 *
 */
#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <unistd.h>
#include "scheduler.h"
#include "config_access.h"

#ifdef WIN32
#include <windows.h>
#elif _POSIX_C_SOURCE >= 199309L
#include <time.h>   // for nanosleep
#else
#include <unistd.h> // for usleep
#endif

task_t tasks[10];
int ntasks = 0;
unsigned int ticklength;
clock_t startclock;

static void sleep_ms(int milliseconds) // cross-platform sleep function
{
#ifdef WIN32
    Sleep(milliseconds);
#elif _POSIX_C_SOURCE >= 199309L
    struct timespec ts;
    ts.tv_sec = milliseconds / 1000;
    ts.tv_nsec = (milliseconds % 1000) * 1000000;
    nanosleep(&ts, NULL);
#else
    usleep(milliseconds * 1000);
#endif
}

static unsigned int get_system_tick()
{
	clock_t curclock = clock();
	return (curclock - startclock) / ticklength;
}

static unsigned int remaining_tick_time()
{
	unsigned int tick = get_system_tick();
	clock_t curclock = clock();
	return ticklength * (tick + 1) - (curclock - startclock);
}

int f(void){
	printf("f called at tick %u\n", get_system_tick());
	sleep_ms(50);
	return 0;
}

int g(void){
	printf("g called at tick %u\n", get_system_tick());
	sleep_ms(100);
	return 0;
}

int h(void){
	printf("h called at tick %u\n", get_system_tick());
	sleep_ms(300);
	return 0;
}

static int idle(void){
	unsigned int remaining = remaining_tick_time();
	sleep_ms(remaining);
	printf("idle for %u ms\n", remaining);
	return 0;
}

static void init(void)
{
	unsigned int fint = get_int_option_value("f_interval");
	unsigned int gint = get_int_option_value("g_interval");
	unsigned int hint = get_int_option_value("h_interval");

	ticklength = gcd(fint, gcd(gint, hint));
	printf("Tick length = %u ms\n", ticklength);
	tasks[0].func = f;
	tasks[0].interval = fint/ticklength;
	tasks[0].priority = get_int_option_value("f_prio");
	tasks[0].last_exec = 0;
	tasks[1].func = g;
	tasks[1].interval = gint/ticklength;
	tasks[1].priority = get_int_option_value("g_prio");
	tasks[1].last_exec = 0;
	tasks[2].func = h;
	tasks[2].interval = hint/ticklength;
	tasks[2].priority = get_int_option_value("h_prio");
	tasks[2].last_exec = 0;
	tasks[3].func = idle;
	tasks[3].interval = 1;
	tasks[3].priority = 1000;
	tasks[3].last_exec = 0;
	ntasks = 4;
	startclock = clock();
}


static int compare_by_prio(const void* t1, const void* t2)
{

	return t1 == t2? 0 : ((task_t*) t1)->priority < ((task_t*) t2)->priority ? -1 : +1;
}

void execute_schedule(void)
{
	int i = 0;
	init();
	if (ntasks == 0) exit(1);
	qsort(tasks, ntasks, sizeof(task_t), compare_by_prio);

	//Run scheduled tasks
	while(1)
	{
			unsigned int tick = get_system_tick();
			if (tick - tasks[i].last_exec >= tasks[i].interval)
			{
				tasks[i].func();
				tasks[i].last_exec = tick;
			}

			i = (i + 1) % ntasks; //next
	}
}

unsigned int gcd(unsigned int a, unsigned int b)
{
	unsigned int t;
	while (b > 0)
	{
		t = b;
		b = a % b;
		a = t;
	}
	return a;
}
