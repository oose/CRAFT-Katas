#include <string.h>
#include <stdlib.h>
#include <stdio.h>
#include "config_parser.h"
#include "config_access.h"

int option_num = 0;
Option* options = NULL;
int option_memory = 0;

const char* get_option_value(const char* key)
{
	const char* v = "";
	if (!option_num)
		parse_config();

	for (int i = 0; i < option_num; i++)
		if (strcmp(key, options[i].key) == 0)
			v = options[i].value;
	return v;
}

void reset_config()
{
	option_num = 0;
}

void reload_config()
{
	reset_config();
	parse_config();
}

void set_option_value(const char* key, const char* value)
{
	for (Option* option = options; option < options + option_num; option++)
	{
		if (!strcmp(option->key, key)) // equal
		{
			strcpy(option->value, value);
			return;
		}
	}
	//Get more memory if necessary
	if (++option_num >= option_memory)
	{
		option_memory += 5;
		options = realloc(options, option_memory * sizeof(Option));
	}
	strcpy(options[option_num - 1].key, key);
	strcpy(options[option_num - 1].value, value);
}

int get_int_option_value(const char* key)
{
	return atoi(get_option_value(key));
}
