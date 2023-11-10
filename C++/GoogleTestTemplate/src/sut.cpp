#include "sut.h"

#include <iostream>


void SUT::reset() {
	x = 0;
}

void SUT::inc() {
	x++;
}

int SUT::get() {
	return x;
}

void SUT::hello(){
	std::cout << "Hello!" << std::endl;
}