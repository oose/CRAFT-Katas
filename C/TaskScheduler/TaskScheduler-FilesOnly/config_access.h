#ifndef CONFIG_ACCESS_H_
#define CONFIG_ACCESS_H_

typedef struct {
	char key[50];
	char value[50];
} Option;


extern int option_num;
extern Option* options;

const char* get_option_value(const char*);
void set_option_value(const char*, const char*);
void reset_config();
void reload_config();
int get_int_option_value(const char*);

#endif /* CONFIG_ACCESS_H_ */
