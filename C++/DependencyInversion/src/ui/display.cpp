#include <iostream>

#include "display.h"

namespace ui {

Display::~Display() {
}

void Display::displayMessage(const std::string& message) {
	// The following replaces complex logic for sending the message to the display
	std::cout << message << std::endl;
}

}
