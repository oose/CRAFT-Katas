#include <string.h>
#include <stdio.h>
#include <stdlib.h>

#include "config_access.h"
#include "config_parser.h"

extern Option* options;
const char* filename;

char* config_text;
size_t config_size;

typedef enum {KEY, VALUE, ASSIGN, INVALID} TOKEN;
typedef enum {ERROR = 0, VALID_ID_CHAR, IGNORE_CHAR, ID_COMPLETE} STATUS;


static int is_whitespace(char c)
{
	return c == ' ' || c == '\t' || c == '\n' || c == '\r' ? 1 : 0;
}

static int is_id_char(char c)
{
	if (( c>= 'a' && c <= 'z')
		|| (c>= 'A' && c <= 'Z')
		|| (c>= '0' && c <= '9')
		|| (c == '_'))
		return 1;
	return 0;
}

int update_exp_token(TOKEN* exp_token, char c, int pos)
{
	switch(*exp_token)
	{
	case KEY:
		if (is_whitespace(c) && pos > 0)
		{
			*exp_token = ASSIGN;
			return ID_COMPLETE;
		}
		else if (c == '=' && pos > 0)
		{
			*exp_token = VALUE;
			return ID_COMPLETE;
		}
		else if (c == '=' && pos == 0)
		{
			*exp_token = INVALID;
			return ERROR;
		}
		else if (is_id_char(c))
		{
			return VALID_ID_CHAR;
		}
		return IGNORE_CHAR;
	case VALUE:
		if (is_whitespace(c) && pos > 0)
		{
			*exp_token = KEY;
			return ID_COMPLETE;
		}
		else if (c == '=')
		{
			*exp_token = INVALID;
			return ERROR;
		}
		else if (is_id_char(c))
		{
			return VALID_ID_CHAR;
		}
		return IGNORE_CHAR;

	case ASSIGN:
		if (c == '=')
		{
			*exp_token = VALUE;
		}
		else if (is_id_char(c)){
			*exp_token = INVALID;
			return ERROR;
		}
		return IGNORE_CHAR;
	case INVALID:
	default:
		break;
	}

	return ERROR;
}

int parse_config(void)
{
	//Open file in read mode
	FILE* f = fopen(filename, "r");
	if (f == NULL)
	{
		fprintf(stderr, "Error reading file.\n");
		return -1;
	}
	//Determine file size
	fseek(f, 0, SEEK_END);
	config_size = ftell(f);
	rewind(f);
	//Get memory for config
	config_text = malloc(config_size);
	//Read entire file into memory
	fread(config_text, config_size, 1, f);
	//Close file
	fclose(f);

	TOKEN exp_token = KEY, i = 0;
	//Get memory for 10 options
	options = malloc(sizeof(Option) * 5);
	int pos = 0;

	//Remove comments
	int comment = 0;
	for (int i = 0; i < config_size; i++)
	{
		if (comment)
		{
			if (config_text[i] != '\n')
				config_text[i] = ' ';
			else
				comment = 0;
		}
		else if (config_text[i] == '#')
		{
			comment = 1;
		}
	}

	//Parsing stuff follows
	while (i < config_size)
	{
		static Option curoption = {"", ""};
		char c = config_text[i];
		STATUS ret;
		switch (exp_token)
		{
		case KEY:
			ret = update_exp_token(&exp_token, c, pos);
			if (ret == ERROR)
				return 1;
			if (ret == VALID_ID_CHAR)
				curoption.key[pos++] = c;
			else if (ret == ID_COMPLETE)
			{
				curoption.key[pos] = '\0';
				pos = 0;
			}
			break;
		case VALUE:
			ret = update_exp_token(&exp_token, c, pos);
			if (ret == ERROR)
				return -2;
			if (ret == VALID_ID_CHAR)
				curoption.value[pos++] = c;
			if (ret == ID_COMPLETE
					|| (ret == VALID_ID_CHAR && (i == config_size - 1)))
			{
				curoption.value[pos] = '\0';
				set_option_value(curoption.key, curoption.value);
				pos = 0;
			}
			break;
		case ASSIGN:
			update_exp_token(&exp_token, c, pos);
			break;
		default:
			break;
		}
		i++;
	}
	free(config_text);
	config_text = config_size = 0;
	return 0;
}


